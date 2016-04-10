/**
 * 
 */
package ensg.tsig.chimn.controllers;

import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ensg.tsig.chimn.dao.MetaDataDao;
import ensg.tsig.chimn.dao.ParametersDao;
import ensg.tsig.chimn.entities.Parameters;
import it.geosolutions.geoserver.rest.GeoServerRESTManager;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTReader;

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
	
		//publisher.createWorkspace("hanane_workspace"))
         //if (reader.existsWorkspace("hanane_workspace"))
         	{
         		if(reader.getWorkspaceNames()!=null && reader.getWorkspaceNames().contains("chimn_workspace"))
         			System.out.print("hanane's workspace exists!");
         		else
         		{
         			System.out.print("Creating chimn workspace...");
         			publisher.createWorkspace("chimn_workspace");
         			if(reader.getWorkspaceNames()!=null && reader.getWorkspaceNames().contains("chimn_workspace"))
             			System.out.print("Chimn workspace was created !");
         			else 
         			{
         				System.out.print("An error occured when trying create Chimn workspace :/");
         				return false;
         			}
         		}
         	}
	
		return true;
	}
	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
}
