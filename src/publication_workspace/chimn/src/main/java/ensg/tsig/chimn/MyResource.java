package ensg.tsig.chimn;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ensg.tsig.chimn.dao.ParametersDao;
import ensg.tsig.chimn.entities.Parameters;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@QueryParam("idI") String id,@QueryParam("secretI") String sec,@QueryParam("groupeTI") String groupe) {
        
    	Parameters myParam=new Parameters();
    	myParam.setIsid(id);
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
    	ParametersDao dao = context.getBean(ParametersDao.class);
    	dao.save(myParam);
    	
    	
    	
    	context.close();
    	
    	
    	
    	return "Got it!"+id;
        
    }
}
