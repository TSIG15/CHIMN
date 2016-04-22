/**
 * 
 */
package ensg.tsig.chimn.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ensg.tsig.chimn.dao.MetaDataDao;
import ensg.tsig.chimn.dao.ParametersDao;
import ensg.tsig.chimn.entities.MetaData;
import ensg.tsig.chimn.entities.Parameters;
import ensg.tsig.chimn.utils.MsgLog;
import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.HTTPUtils;
import it.geosolutions.geoserver.rest.decoder.RESTDataStoreList;
import it.geosolutions.geoserver.rest.encoder.GSLayerEncoder;
import it.geosolutions.geoserver.rest.encoder.GSResourceEncoder.ProjectionPolicy;
import it.geosolutions.geoserver.rest.encoder.datastore.GSPostGISDatastoreEncoder;
import it.geosolutions.geoserver.rest.encoder.feature.GSFeatureTypeEncoder;

/**This class contains methods related to publication of web services.
 *
 *
 */
public class PublisherController {

	private Parameters parameters;
	private URL  gs_url;
	private GeoServerRESTManager manager;
	private GeoServerRESTReader reader;
	private GeoServerRESTPublisher publisher;
	private final String workspaceName ="chimn_workspace";
	private List<String> dataStoreName=new ArrayList<String>();
	private Map<String,String> layersName = new HashMap<String,String>();
	private List<String> urls_services=new ArrayList<String>() ;
	private Map<Integer,String> mapLayersUrl = new HashMap<Integer,String>();
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(GeoServerRESTPublisher.class);

   /**This method
    * 
    * @return boolean
    */
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
    			try {
					MsgLog.write("Error : multiple prameters were found in database ");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
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
			try {
				MsgLog.write(e.getMessage());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
       
    	context.close();
    	return true;
	}
	
	//create workspace and store if don't exist
	/**This method
	 * 
	 * @return boolean
	 */
	public boolean setDataSource()
	{
		if(manager==null || reader==null || publisher == null)
			return false;
	
		
         		if(reader.getWorkspaceNames()!=null && reader.getWorkspaceNames().contains(workspaceName))
         			{
         			System.out.print("chimn's workspace exists!");
         			try {
    					MsgLog.write("chimn's workspace exists!");
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
         			}
         		else
         		{
         			System.out.print("Creating chimn workspace...");
         			publisher.createWorkspace(workspaceName);
         			if(reader.getWorkspaceNames()!=null && reader.getWorkspaceNames().contains(workspaceName))
             		{
         				System.out.print("Chimn workspace was created !");
         				try {
        					MsgLog.write("Chimn workspace was created !");
        				} catch (IOException e1) {
        					// TODO Auto-generated catch block
        					e1.printStackTrace();
        				}
         				             			
             		}
             			
         			else 
         			{
         				System.out.print("An error occured when trying to create Chimn workspace :/");
         				try {
        					MsgLog.write("An error occured when trying to create Chimn workspace :/");
        				} catch (IOException e1) {
        					// TODO Auto-generated catch block
        					e1.printStackTrace();
        				}
         				return false;
         			}
         		}
         		
         		
         		/***************Add postgis schema as different datastores : deprecated***************/
         		List<String> schemas=new ArrayList<String>();
         		schemas.add("france");
         		schemas.add("idf_data");
         		
         		//now let's check if the associated datastore already exists  !
         		for(int i=0;i<schemas.size();i++)
 				if(reader.getDatastore(workspaceName, schemas.get(i))!=null)
 					{
 					
 					System.out.print(parameters.getDbname()+" already exists !");
 					try {
    					MsgLog.write(parameters.getDbname()+" already exists !");
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
 					
 					dataStoreName.add(schemas.get(i));
 					}
 				else
 				{
 					//create a new postgis data store
 	            	GSPostGISDatastoreEncoder storeencoder=new GSPostGISDatastoreEncoder(schemas.get(i));
 	            	storeencoder.setDatabase(parameters.getDbname());
 	            	storeencoder.setPort(Integer.parseInt(parameters.getDbport()));
 	            	storeencoder.setHost(parameters.getDbhote());
 	            	storeencoder.setUser(parameters.getDbuser());
 	            	storeencoder.setPassword(parameters.getDbpsw());
 	            	storeencoder.setSchema(schemas.get(i));
 	            	boolean storecreated=createPostGISDatastore(workspaceName,storeencoder);
 	            	if (storecreated) 
 	            		{
 	            		System.out.print("Chimn_datastore"+parameters.getDbname()+" was created !");
 	            		try {
 	    					MsgLog.write("Chimn_datastore"+parameters.getDbname()+" was created !");
 	    				} catch (IOException e1) {
 	    					// TODO Auto-generated catch block
 	    					e1.printStackTrace();
 	    				}
 	            		dataStoreName.add(schemas.get(i));
 	            		}
 	            	else 
 	            		{
 	            		System.out.print("Chimn_datastore"+parameters.getDbname()+" was NOT created :/");
 	            		try {
 	    					MsgLog.write("Chimn_datastore"+parameters.getDbname()+" was NOT created :/");
 	    				} catch (IOException e1) {
 	    					// TODO Auto-generated catch block
 	    					e1.printStackTrace();
 	    				}
 	            		return false;
 	            		}
 	            	
 				}
         	
	
		return true;
	}
	
	/**This method
	 * 
	 * @param workspace
	 * @param datastoreEncoder
	 * @return boolean
	 */
    public boolean createPostGISDatastore(String workspace,
            GSPostGISDatastoreEncoder datastoreEncoder) {
    	
        String sUrl = gs_url + "/rest/workspaces/" + workspace + "/datastores/";
        String xml = datastoreEncoder.toString();
        String result = HTTPUtils.postXml(sUrl, xml, parameters.getGsuser(), parameters.getGspsw());
        return result != null;
    }
    
    /**This method
     * 
     * @param workspace
     * @param storename
     * @param layername
     * @param srs
     * @param defaultStyle
     * @return boolean
     */
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
    
    /**This method
     * 
     * @param workspace
     * @param storename
     * @param fte
     * @param layerEncoder
     * @return boolean 
     */
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
        System.out.println("gs_url.getPath() "+gs_url.getAuthority()+gs_url.getFile());
        StringBuilder postUrl = new StringBuilder("http://"+gs_url.getAuthority()+gs_url.getFile()).append("/rest/workspaces/")
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
    
    /**This method
     * 
     * @param workspace
     * @param resourceName
     * @param layer
     * @return
     * @throws IllegalArgumentException
     */
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
    //removes all layers which were not asked by the admin from the workspace
    public boolean cleanWorkspace()
    {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
    	MetaDataDao dao = context.getBean(MetaDataDao.class);
    	List<MetaData> layersToRemove=dao.findByAsked(false);
    	//for each layer : check if it exists and then remove it!!
    	String title;
    	String schema,name;
    	for(int i=0; i<layersToRemove.size();i++)
    		{
    			title=layersToRemove.get(i).getName();
    			schema=title.substring(0,title.indexOf("."));
    			name=title.substring(title.lastIndexOf(".") + 1);
    			unpublishFeatureType(workspaceName,schema,name); //as we the schema's name is the same as the datastorename ==> should be replaced in the next version
    			layersToRemove.get(i).setActivated(false);
    			dao.save(layersToRemove.get(i));
    		}
    	context.close();
    	return true;
    }
    /**
     * Removes the featuretype and the associated layer.
     * <P>
     * You may also want to {@link #removeDatastore(String, String) remove the datastore}.
     * @param workspace
     * @param storename
     * @param layerName
     * @return true if the operation completed successfully.
     */
    public boolean unpublishFeatureType(String workspace, String storename, String layerName) {
        try {

            final String fqLayerName;
            // this null check is here only for backward compatibility.
            // workspace
            // shall be mandatory.
            if (workspace == null) {

                fqLayerName = layerName;

                if (LOGGER.isWarnEnabled()) {
                    LOGGER.warn("Null workspace while configuring layer : " + layerName
                            + " -- This behavior is deprecated.");
                }
            } else {
                fqLayerName = workspace + ":" + layerName;
            }
            // delete related layer
            URL deleteLayerUrl = new URL(gs_url + "/rest/layers/" + fqLayerName);
            boolean layerDeleted = HTTPUtils
                    .delete(deleteLayerUrl.toExternalForm(), parameters.getGsuser(), parameters.getGspsw());
            if (!layerDeleted) {
                LOGGER.warn("Could not delete layer '" + fqLayerName + "'");
                return false;
            }
            // delete the coverage
            URL deleteFtUrl = new URL(gs_url + "/rest/workspaces/" + workspace + "/datastores/"
                    + storename + "/featuretypes/" + layerName);
            boolean ftDeleted = HTTPUtils.delete(deleteFtUrl.toExternalForm(), parameters.getGsuser(), parameters.getGspsw());
            if (!ftDeleted) {
                LOGGER.warn("Could not delete featuretype " + workspace + ":" + storename + "/"
                        + layerName + ", but layer was deleted.");
            } else {
                LOGGER.info("FeatureType successfully deleted " + workspace + ":" + storename + "/"
                        + layerName);
            }

            return ftDeleted;

            // the store is still there: should we delete it?

        } catch (MalformedURLException ex) {
            if (LOGGER.isErrorEnabled())
                LOGGER.error(ex.getLocalizedMessage(), ex);
            return false;
        }
    }
    
    /**This method publishes.
     * 
     */
    public void publish()
    {	//get license from preferences
    	String license="Licence ouverte ETALAB 1.0";
    	//get list of layers to publish
    	//the list is composed of layer's name of metadata.
    	//a layer to publish is a layer that has : changed=true && asked==true && licence=preference.licence
    
    	//0) initiate context for crud operations
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
    	MetaDataDao dao = context.getBean(MetaDataDao.class);
    	MetaDataDao dao2 = context.getBean(MetaDataDao.class); //bug :/
    	List<MetaData> layers=dao.findByAskedAndChanged(true, true);
    	
    	List<MetaData> passivateLayers=dao2.findByAskedAndChanged(true, false); //deprecated : to be optimized in the next version
    	
    	
    	String title;
    	boolean publishDBLayer;
    	for(int i=0;i<layers.size();i++)
    		{   
    			title=layers.get(i).getName();
    			//get only table's name : noooo
    			String tableName=title.substring(title.lastIndexOf(".") + 1);
    			String schemaName=title.substring(0,title.indexOf("."));
    			layersName.put(tableName,schemaName);
    		
    			//layersName.add(title);
    			
    			
    			 //test publishing a layer
    		   //if(layersName.get(i).equals("coursdo"))
    			String style="";
    			  if(layers.get(i).getGeometrytype().equals("LineString"))
    				  style="line";
    			  if(layers.get(i).getGeometrytype().equals("Polygon"))
    				  style="polygon";
    			  if(layers.get(i).getGeometrytype().equals("Point"))
    				  style="point";
    			  
    				  publishDBLayer = publisher.publishDBLayer(workspaceName,schemaName,tableName,layers.get(i).getSrs(),"line");
    			  if(publishDBLayer)
    				  {
    				  	System.out.println(layersName.get(i)+" was successfully published :)");
    				  	layers.get(i).setActivated(true);
    				  	dao.save(layers.get(i));
    				  }
				else
					try {
						MsgLog.write("An error has occured while publishing "+layersName.get(i));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    		}
    	for(int i=0;i<passivateLayers.size();i++)
		{   
    		if(!passivateLayers.get(i).isActivated()) //for publishing desactivated layers which are not changed but asked
    		{
			title=passivateLayers.get(i).getName();
			//get only table's name : noooo
			String tableName=title.substring(title.lastIndexOf(".") + 1);
			String schemaName=title.substring(0,title.indexOf("."));
			layersName.put(tableName,schemaName);
		
			 //test publishing a layer
		   //if(layersName.get(i).equals("coursdo"))
			String style="";
			  if(passivateLayers.get(i).getGeometrytype().equals("LineString"))
				  style="line";
			  if(passivateLayers.get(i).getGeometrytype().equals("Polygon"))
				  style="polygon";
			  if(passivateLayers.get(i).getGeometrytype().equals("Point"))
				  style="point";
			  
				  publishDBLayer = publisher.publishDBLayer(workspaceName,schemaName,tableName,passivateLayers.get(i).getSrs(),"line");
			  if(publishDBLayer)
				  {
				  	System.out.println(layersName.get(i)+" was successfully published :)");
				  	passivateLayers.get(i).setActivated(true);
				  	dao2.save(passivateLayers.get(i));
				  }
			else
				try {
					MsgLog.write("An error has occured while publishing "+layersName.get(i));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		}
	
    	context.close();
    	
    }
    /**
     * 
     * @return parameters
     */
    public Parameters getParameters() {
		return parameters;
	}
    
    /**
     * 
     * @param parameters
     */
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * 
	 * @return urls_services
	 */
	public List<String> getUrls_services() {
		return urls_services;
	}
	
	/**
	 * 
	 * @param urls_services
	 */
	public void setUrls_services(List<String> urls_services) {
		this.urls_services = urls_services;
	}
}
