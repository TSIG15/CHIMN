package ensg.tsig.chimn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ensg.tsig.chimn.controllers.IsogeoController;
import ensg.tsig.chimn.controllers.PublisherController;
import ensg.tsig.chimn.dao.ParametersDao;
import ensg.tsig.chimn.entities.Parameters;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
@Stateful

public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	@EJB
	private IsogeoController isogeo;
	private PublisherController publisher;
	

	public void initializeIsogeo()
	{
		//getting isogeoParameters
 		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");/*ouvre la connexion bdd*/
    	
    	/*action faites sur la table*/
    	ParametersDao dao = context.getBean(ParametersDao.class);/* dao permet transaction CRUD*/
    	List<Parameters>param=dao.findAll(); 
 		
 		//create a new isogeoclient from parameters stored
    	isogeo=new IsogeoController(param.get(0).getIsid(),param.get(0).getIssecret());
    	isogeo.getToken();
    	context.close();
    	
	}
	public void initializePublisher()
	{
		//Connect to publisher
    	 publisher=new PublisherController();
    	publisher.initializeParameters();
    	System.out.println(publisher.getParameters().toString());
    	//test connexion to geoserver
    	if(publisher.setDataSource())
    		System.out.println("connexion to geoserver ok ");
    	else 
    		System.out.println("connexion to geoserver KO :/ ");
	}
	 	@GET
	    @Path("/run/")
	    @Produces( MediaType.TEXT_PLAIN)
	public String  getRun()
	    {	
	    	//initialize attributes for the current instance
	    	initializeIsogeo();
	    	initializePublisher();
	    	
		//test updating metadata in chimn database
		    if(isogeo!=null)
		    	{
		    		if(!isogeo.setHistoricalMetaData())
		    		   return "error updating metadata :/ ";
		    		System.out.println("Hitorical was updated successfully :)");
		    	}
		    
		
		    
		 // publish OGC services
		    if(publisher!=null)
		    {
		    	//publisher.publish();
		    	System.out.println("Services were published successfully :)"); 
		    }
		    //run python
		    
		    
		    return "the end of get run!";
	    }

	//method post to get the parameters

    @POST
    @Path("/parameters/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response postParameters(
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
    	myParam.setGspsw(gspsw);
    	myParam.setGsuser(gsuser);
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
    	
    }
    
    @POST
    @Path("/tags/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getTags(@FormParam("q") String q)
    {

    	initializeIsogeo();
    	if(isogeo==null) return null;
    	if( isogeo.initializeKeyWords(q))
    		{
    			JSONObject j=new JSONObject();
    			j.putAll(isogeo.getKeywords());
    			return j;
    		}
    	
    		 
    	 return null;
    
    }
    @POST
    @Path("/authentification/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces( MediaType.TEXT_PLAIN)
    public String postAuthentification (@FormParam("username") String login,
    		@FormParam("password") String mdp)
    {
    	
    	 return "authentification ok!"+login+mdp;
    
    }
    
    @GET
    @Path ("/data/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getData(@QueryParam("word") String word)
    {

    	IsogeoController isogeo=new IsogeoController("projet-ensg-d2e472b0f92940ee87f9d1ac6e3e90d0","jvdMBbVJXiiOSQshFxHFPdlZCNhfvCdJlSkKrZA3npEHns9zOBY1bQuYqtV3xLTd");
   	 	//we catch the token
    	isogeo.getToken();
   	 	
    	isogeo.search_metadata_from_isogeo(word, "conditions", "", "", "", "", "", "3", 0);
    	
    	return null;
	}
	
 }
