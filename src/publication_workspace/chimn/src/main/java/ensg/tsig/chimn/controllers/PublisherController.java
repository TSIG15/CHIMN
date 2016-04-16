/**
 * 
 */
package ensg.tsig.chimn.controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ensg.tsig.chimn.dao.MetaDataDao;
import ensg.tsig.chimn.dao.ParametersDao;
import ensg.tsig.chimn.entities.MetaData;
import ensg.tsig.chimn.entities.Parameters;
import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.HTTPUtils;
import it.geosolutions.geoserver.rest.decoder.RESTDataStoreList;
import it.geosolutions.geoserver.rest.encoder.GSLayerEncoder;
import it.geosolutions.geoserver.rest.encoder.GSResourceEncoder.ProjectionPolicy;
import it.geosolutions.geoserver.rest.encoder.datastore.GSPostGISDatastoreEncoder;
import it.geosolutions.geoserver.rest.encoder.feature.GSFeatureTypeEncoder;

/**
 * @author hanane
 *
 */
public class PublisherController {

	private Parameters parameters;
	private URL  gs_url;
	private GeoServerRESTManager manager;
	private GeoServerRESTReader reader;
	private GeoServerRESTPublisher publisher;
	private final String workspaceName ="chimn_workspace";
	private String dataStoreName;
	private List<String> layersName;
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(GeoServerRESTPublisher.class);

   
	public boolean initializeParameters()
	{
		setParameters(new Parameters());
		//0) initiate context for crud operations
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
    	ParametersDao dao = context.getBean(ParametersDao.class);
    	
    	if(dao.findAll().size()!=1)
    		{
    			System.out.println("Error : multiple prameters were found in database ");
    			return false;
    		}
    	setParameters(dao.findAll().get(0));
    	try {
			gs_url = new URL("Http://"+parameters.getGshote()+":"+parameters.getGsport()+"/geoserver");
			manager = new GeoServerRESTManager(gs_url, parameters.getGsuser(), parameters.getGspsw());
			reader = manager.getReader();
			publisher = manager.getPublisher();    
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    	context.close();
    	return true;
	}
	//create workspace and store if don't exist
	public boolean setDataSource()
	{
		if(manager==null || reader==null || publisher == null)
			return false;
	
		
         		if(reader.getWorkspaceNames()!=null && reader.getWorkspaceNames().contains(workspaceName))
         			System.out.print("chimn's workspace exists!");
         		else
         		{
         			System.out.print("Creating chimn workspace...");
         			publisher.createWorkspace(workspaceName);
         			if(reader.getWorkspaceNames()!=null && reader.getWorkspaceNames().contains(workspaceName))
             		{
         				System.out.print("Chimn workspace was created !");
         				             			
             		}
             			
         			else 
         			{
         				System.out.print("An error occured when trying to create Chimn workspace :/");
         				return false;
         			}
         		}
         		
         		
         		//now let's check if the associated datastore already exists  !
         		/*RESTDataStoreList existingDataStore =reader.getDatastores(workspaceName);
         		boolean dsExists=false;
         		for (int i=0;i<existingDataStore.size();i++)
         			{
         			System.out.println(existingDataStore.get(i).getName());
         			System.out.println("chimn_datastore"+parameters.getDbname());
         			if(existingDataStore.get(i).getName().equals("chimn_datastore"+parameters.getDbname()))
         			{    				 	
     					dsExists=true;
     				}
         			}*/
         				
         		
 				if(reader.getDatastore(workspaceName, parameters.getDbname())!=null)
 					{
 					
 					System.out.print(parameters.getDbname()+" already exists !");
 					dataStoreName=parameters.getDbname();
 					}
 				else
 				{
 					//create a new postgis data store
 	            	GSPostGISDatastoreEncoder storeencoder=new GSPostGISDatastoreEncoder(parameters.getDbname());
 	            	storeencoder.setDatabase(parameters.getDbname());
 	            	storeencoder.setPort(Integer.parseInt(parameters.getDbport()));
 	            	storeencoder.setHost(parameters.getDbhote());
 	            	storeencoder.setUser(parameters.getDbuser());
 	            	storeencoder.setPassword(parameters.getDbpsw());
 	            	boolean storecreated=createPostGISDatastore(workspaceName,storeencoder);
 	            	if (storecreated) 
 	            		{
 	            		System.out.print("Chimn_datastore"+parameters.getDbname()+" was created !");
 	            		dataStoreName="Chimn_datastore"+parameters.getDbname();
 	            		}
 	            	else 
 	            		{
 	            		System.out.print("Chimn_datastore"+parameters.getDbname()+" was NOT created :/");
 	            		return false;
 	            		}
 	            	
 				}
         	
	
		return true;
	}
	
    public boolean createPostGISDatastore(String workspace,
            GSPostGISDatastoreEncoder datastoreEncoder) {
    	
        String sUrl = gs_url + "/rest/workspaces/" + workspace + "/datastores/";
        String xml = datastoreEncoder.toString();
        String result = HTTPUtils.postXml(sUrl, xml, parameters.getGsuser(), parameters.getGspsw());
        return result != null;
    }
    public boolean publishDBLayer(String workspace, String storename, String layername, String srs,
            String defaultStyle) {

        final GSFeatureTypeEncoder fte = new GSFeatureTypeEncoder();

        fte.setProjectionPolicy(ProjectionPolicy.REPROJECT_TO_DECLARED);
        fte.addKeyword("KEYWORD");
        fte.setTitle(layername);
        fte.setName(layername);
        fte.setSRS(srs); // srs=null?"EPSG:4326":srs);
        final GSLayerEncoder layerEncoder = new GSLayerEncoder();
        layerEncoder.setDefaultStyle(defaultStyle);
        return publishDBLayer(workspace, storename, fte, layerEncoder);
    }
    public boolean publishDBLayer(final String workspace, final String storename,
            final GSFeatureTypeEncoder fte, final GSLayerEncoder layerEncoder) {
        /*
         * This is the equivalent call with cUrl:
         * 
         * {@code curl -u admin:geoserver -XPOST -H 'Content-type: text/xml' \ -d
         * "<featureType><name>easia_gaul_1_aggr</name><nativeCRS>EPSG:4326</nativeCRS><enabled>true</enabled></featureType>" \
         * Http://localhost:8080/geoserver/rest/workspaces/it.geosolutions/ datastores/pg_kids/featuretypes }
         * 
         * and a PUT to <BR> restURL + "/rest/layers/" workspace + : + layerName
         */
        String ftypeXml = fte.toString();
        System.out.println("gs_url.getPath() "+gs_url.getPath());
        StringBuilder postUrl = new StringBuilder(gs_url.getPath()).append("/rest/workspaces/")
                .append(workspace).append("/datastores/").append(storename).append("/featuretypes");

        final String layername = fte.getName();
        if (layername == null || layername.isEmpty()) {
            if (LOGGER.isErrorEnabled())
                LOGGER.error("GSFeatureTypeEncoder has no valid name associated, try using GSFeatureTypeEncoder.setName(String)");
            return false;
        }

        String configuredResult = HTTPUtils.postXml(postUrl.toString(), ftypeXml, parameters.getGsuser(),
        		 parameters.getGsuser());
        boolean published = configuredResult != null;
        boolean configured = false;

        if (!published) {
            LOGGER.warn("Error in publishing (" + configuredResult + ") " + workspace + ":"
                    + storename + "/" + layername);
        } else {
            LOGGER.info("DB layer successfully added (layer:" + layername + ")");

            if (layerEncoder == null) {
                if (LOGGER.isErrorEnabled())
                    LOGGER.error("GSLayerEncoder is null: Unable to find the defaultStyle for this layer");
                return false;
            }

            configured = configureLayer(workspace, layername, layerEncoder);

            if (!configured) {
                LOGGER.warn("Error in configuring (" + configuredResult + ") " + workspace + ":"
                        + storename + "/" + layername);
            } else {
                LOGGER.info("DB layer successfully configured (layer:" + layername + ")");
            }
        }

        return published && configured;
    }
    
    public boolean configureLayer(final String workspace, final String resourceName,
            final GSLayerEncoder layer) throws IllegalArgumentException {

        if (workspace == null || resourceName == null || layer == null) {
            throw new IllegalArgumentException("Null argument");
        }
        // TODO: check this usecase, layer should always be defined
        if (workspace.isEmpty() || resourceName.isEmpty() || layer.isEmpty()) {
            throw new IllegalArgumentException("Empty argument");
        }

        final String fqLayerName = workspace + ":" + resourceName;

        final String url = gs_url.getPath() + "/rest/layers/" + fqLayerName;

        String layerXml = layer.toString();
        String sendResult = HTTPUtils.putXml(url, layerXml, parameters.getGsuser(), parameters.getGspsw());
        if (sendResult != null) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Layer successfully configured: " + fqLayerName);
            }
        } else {
            if (LOGGER.isWarnEnabled())
                LOGGER.warn("Error configuring layer " + fqLayerName + " (" + sendResult + ")");
        }

        return sendResult != null;
    }

   
    public void publish()
    {	//get license from preferences
    	String license="Licence ouverte ETALAB 1.0";
    	//get list of layers to publish
    	//the list is composed of layer's name of metadata.
    	// a layer to publih is a layer that has : changed=true && asked==true && licence=preference.licence
    
    	//0) initiate context for crud operations
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
    	MetaDataDao dao = context.getBean(MetaDataDao.class);
    	List<MetaData> layers=dao.findByAskedAndChangedAndLicense(true, true, license);
    	
    	layersName=new ArrayList<String>();
    	
    	String title;
    	for(int i=0;i<layers.size();i++)
    		{   
    			System.out.println(layers.get(i).isAsked());
    			
    			
    			title=layers.get(i).getName();
    			//get only table's name
    			layersName.add(title.substring(title.lastIndexOf(".") + 1));
    			System.out.println(title.substring(title.lastIndexOf(".") + 1));
    			
    			 //test publishing a layer
    		   if(layersName.get(i).equals("coursdo"))
    			  publisher.publishDBLayer(workspaceName,dataStoreName,layersName.get(i),"EPSG:4326","line");
    			
    		}
    	
    	context.close();
    	
    }
    
    public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
}
