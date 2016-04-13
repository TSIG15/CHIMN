package ensg.tsig.chimn;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ensg.tsig.chimn.controllers.IsogeoController;
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
	
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createFromPost(
    		@FormParam("hoteBDD") String dbhote,
    		@FormParam("portBDD") String dbport,
    		@FormParam("bdd") String dbname,
    		@FormParam("userBDD") String dbuser,
    		@FormParam("passwordBDD") String dbpsw,
    		@FormParam("hoteGS") String gshote,
    		@FormParam("portGS") String gsport,
    		@FormParam("userGS") String gsuser,
    		@FormParam("passwordGS") String gspsw,
    		@FormParam("idI") String isid,
    		@FormParam("secretI") String issecret,
    		@FormParam("groupeTI") String isgroupetravail,
    		@FormParam("urlSD") String tlurl) 
    {
        
    	Parameters myParam=new Parameters();/*crée un nouvel objet paramètres*/
    	myParam.setDbhote(dbhote);
    	myParam.setDbport(dbport);
    	myParam.setDbname(dbname);
    	myParam.setDbuser(dbuser);
    	myParam.setDbpsw(dbpsw);
    	myParam.setGshote(gshote);
    	myParam.setGsport(gsport);
    	myParam.setGspsw(gsuser);
    	myParam.setGsuser(gspsw);
    	myParam.setIsid(isid);
    	myParam.setIssecret(issecret);
    	myParam.setIsgroupetravail(isgroupetravail);
    	myParam.setTlurl(tlurl);
    	
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");/*ouvre la connexion bdd*/
    	
    	/*action faites sur la table*/
    	ParametersDao dao = context.getBean(ParametersDao.class);/* dao permet transaction CRUD*/
    	dao.deleteAll(); /*supprime les autres entrées de la table*/
    	dao.save(myParam); /*acte la transaction*/
    	
    	context.close(); /*ferme connexion bdd*/
    	
		return null;
    	
    	/*return "Got it ! "+ id + "; "+ sec + "; " + groupe;*/
        
    }
    
    @GET
    @Path("/tags/")
    @Produces( MediaType.TEXT_PLAIN)
    public String  getTags()
    {
    	 IsogeoController isogeo=new IsogeoController("projet-ensg-d2e472b0f92940ee87f9d1ac6e3e90d0","jvdMBbVJXiiOSQshFxHFPdlZCNhfvCdJlSkKrZA3npEHns9zOBY1bQuYqtV3xLTd");
    	 isogeo.getToken();
    	 if( isogeo.initializeTags())
    		 return isogeo.getTags().toString();
    	 return null;
    
    }
    
}
