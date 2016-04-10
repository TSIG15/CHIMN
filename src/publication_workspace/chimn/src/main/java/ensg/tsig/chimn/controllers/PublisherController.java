/**
 * 
 */
package ensg.tsig.chimn.controllers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ensg.tsig.chimn.dao.MetaDataDao;
import ensg.tsig.chimn.dao.ParametersDao;
import ensg.tsig.chimn.entities.MetaData;
import ensg.tsig.chimn.entities.Parameters;
import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;
import it.geosolutions.geoserver.rest.HTTPUtils;
import it.geosolutions.geoserver.rest.encoder.datastore.GSPostGISDatastoreEncoder;

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
    
   
	public boolean initializeParameters()
	{
		setParameters(new Parameters());
		//0) initiate context for crud operations
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
    	ParametersDao dao = context.getBean(ParametersDao.class);
    	
    	if(dao.findAll().size()!=1)
    		return false;
    	setParameters(dao.findAll().get(0));
    	try {
			gs_url = new URL("http://"+parameters.getGshote()+":"+parameters.getGsport()+"/geoserver");
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
         			if(reader.getWorkspaceNames()!=null && reader.getWorkspaceNames().contains("chimn_workspace"))
             		{
         				System.out.print("Chimn workspace was created !");
         				             			
             		}
             			
         			else 
         			{
         				System.out.print("An error occured when trying to create Chimn workspace :/");
         				return false;
         			}
         		}
         		
         		
         		//now let's check if the associated datastore exists already !
 				if(reader.getDatastore("chimn_workspace", "chimn_datastore"+parameters.getDbname())!=null)
 					{
 					System.out.print("Chimn_datastore"+parameters.getDbname()+" already exists !");
 					dataStoreName="Chimn_datastore"+parameters.getDbname();
 					}
 				else
 				{
 					//create a new postgis data store
 	            	GSPostGISDatastoreEncoder storeencoder=new GSPostGISDatastoreEncoder("Chimn_datastore"+parameters.getDbname());
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
    	//List<MetaData> layers=dao.findByAsked(true);
    	layersName=new ArrayList<String>();
    	String title;
    	for(int i=0;i<layers.size();i++)
    		{   
    			System.out.println(layers.get(i).isAsked());
    			title=layers.get(i).getName();
    			//get only table's name
    			layersName.add(title.substring(title.lastIndexOf(".") + 1));
    			System.out.println(title.substring(title.lastIndexOf(".") + 1));
    			layers.get(i).setAsked(false);
    			System.out.println(layers.get(i).isAsked());
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
