package ensg.tsig.chimn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import org.json.simple.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ensg.tsig.chimn.controllers.IsogeoController;
import ensg.tsig.chimn.controllers.PublisherController;
import ensg.tsig.chimn.dao.ParametersDao;
import ensg.tsig.chimn.dao.PreferenceFormatDao;
import ensg.tsig.chimn.dao.PreferenceSRSDao;
import ensg.tsig.chimn.entities.Parameters;
import ensg.tsig.chimn.entities.PreferenceFormat;
import ensg.tsig.chimn.entities.PreferenceSRS;
import ensg.tsig.chimn.utils.MsgLog;

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
	public String getRun()
	    {	
		try {
			
			MsgLog.write("happy run");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	    	//initialize attributes for the current instance
	    	initializeIsogeo();
	    	initializePublisher();
	    	
	    	//test updating metadata in chimn database
		    /*if(isogeo!=null)
		    	{
		    		if(!isogeo.setHistoricalMetaData())
		    		   return "error updating metadata :/ ";
		    		System.out.println("Hitorical was updated successfully :)");
		    	}	*/   

		 // publish OGC services
		    if(publisher!=null)
		    {	//clean workspace: remove layers which are not asked by administrator
		    	publisher.cleanWorkspace();
		    	publisher.publish();
		    	System.out.println("Services were published successfully :)"); 
		    }
		    //run python

		  // runPythonScript("D:\\3eme_ENSG\\projet_industriel\\src\\publication_workspace\\chimn\\src\\main\\java\\ensg\\tsig\\chimn\\helloworld.py")
		    

		    return "the end of get run!";
	    }

	public void runPythonScript(String path)
	{
		String cmd = "python "+path; 
	    String s = null;
	    String errors="/*********Python Errors*******/";
	    try {
	    	Process p = Runtime.getRuntime().exec(cmd);
	        
	        BufferedReader stdInput = new BufferedReader(new
	             InputStreamReader(p.getInputStream()));

	        BufferedReader stdError = new BufferedReader(new
	             InputStreamReader(p.getErrorStream()));

	        // read the output from the command
	        System.out.println("Here is the standard output of the command:\n");
	        while ((s = stdInput.readLine()) != null) {
	            System.out.println(s);
	        }
	         
	        // read any errors from the attempted command
	        System.out.println("Here is the standard error of the command (if any):\n");
	        
	        while ((s = stdError.readLine()) != null) {
	            
	        	errors+=s;
	        }
	         
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
    @Produces(MediaType.TEXT_PLAIN)
    public String postAuthentification (
    		@FormParam("username") String login,
    		@FormParam("password") String mdp)
    			     
    {   	
    	String loginAdmin = "admin";
    	String mdpAdmin = "MaDdPmin";
    	String success = "success";
    	String failure = "failure";
    	
    	System.out.println(login.equals(loginAdmin)); 
    	System.out.println(mdp.equals(mdpAdmin)); 
    	
    	int logInt = (login.equals(loginAdmin)) ? 1 : 0;
    	int mdpInt = (mdp.equals(mdpAdmin)) ? 1 : 0;
    	
    	if((logInt==1) && (mdpInt==1))
    		return success;
    	else
    		return failure;
    }

    
    
	/*@GET
    @Path ("/data/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getData(@QueryParam("word") String word)
    {

    	initializeIsogeo();
    	if(isogeo==null) return null;
    	isogeo.search_metadata_from_isogeo(word,"conditions", "", "", "", "", "", "3", 0);
    	
    	return null;
	}*/

    @POST
    @Path("/formats/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String formatCheckedToDB(
    		@FormParam("shp") String shpVal ,
    		@FormParam("dxf") String dxfVal,
    		@FormParam("gml") String gmlVal,
    		@FormParam("geotiff") String geotiffVal,
    		@FormParam("png") String pngVal,
    		@FormParam("jpeg") String jpegVal
    		) {
    	
    	/*PreferenceFormat myShp = new PreferenceFormat();   crée un nouvel objet paramètres
    	myShp.setNameformat("shp");
    	myShp.setActivateformat(Boolean.valueOf(shpVal));
    	
    	PreferenceFormat myDxf=new PreferenceFormat();   crée un nouvel objet paramètres
    	myShp.setNameformat("dxf");
    	myShp.setActivateformat(Boolean.valueOf(dxfVal));*/
    	
     	
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"); /*ouvre la connexion bdd*/
    	
    	/*action faites sur la table*/
    	PreferenceFormatDao dao0 = context.getBean(PreferenceFormatDao.class);/* dao permet transaction CRUD*/
    	PreferenceFormatDao dao1 = context.getBean(PreferenceFormatDao.class);
    	PreferenceFormatDao dao2 = context.getBean(PreferenceFormatDao.class);
    	PreferenceFormatDao dao3 = context.getBean(PreferenceFormatDao.class);
    	PreferenceFormatDao dao4 = context.getBean(PreferenceFormatDao.class);
    	PreferenceFormatDao dao5 = context.getBean(PreferenceFormatDao.class);
    	
    	
    	List<PreferenceFormat> listshp = dao0.findByNameformat("shp");   
    	List<PreferenceFormat> listdxf = dao1.findByNameformat("dxf");
    	List<PreferenceFormat> listgml = dao2.findByNameformat("gml");
    	List<PreferenceFormat> listgeotiff = dao3.findByNameformat("geotiff");
    	List<PreferenceFormat> listpng = dao4.findByNameformat("png");
    	List<PreferenceFormat> listjpeg = dao5.findByNameformat("jpeg");
    	
    	
    	if(listdxf!=null) listshp.get(0).setActivateformat(Boolean.valueOf(shpVal));
    	if(listdxf!=null) listdxf.get(0).setActivateformat(Boolean.valueOf(dxfVal));
    	if(listgml!=null) listgml.get(0).setActivateformat(Boolean.valueOf(gmlVal));
    	if(listgeotiff!=null) listgeotiff.get(0).setActivateformat(Boolean.valueOf(geotiffVal));
    	if(listshp!=null) listpng.get(0).setActivateformat(Boolean.valueOf(pngVal));
    	if(listjpeg!=null) listjpeg.get(0).setActivateformat(Boolean.valueOf(jpegVal));
    	
    	
    	dao0.save(listshp.get(0));
    	dao1.save(listdxf.get(0));
    	dao2.save(listgml.get(0));
    	dao3.save(listgeotiff.get(0));
    	dao4.save(listpng.get(0));
    	dao5.save(listjpeg.get(0));
    	
    	context.close();
    	return null;
    }
    
    @POST
    @Path("/srs/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String srsCheckedToDB(
    		@FormParam("WebMercator") String webMVal ,
    		@FormParam("l93") String lambertVal,
    		@FormParam("wgs84") String wgsVal
    		){
    	
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"); /*ouvre la connexion bdd*/
    	
    	
    	PreferenceSRSDao dao0 = context.getBean(PreferenceSRSDao.class);
    	PreferenceSRSDao dao1 = context.getBean(PreferenceSRSDao.class);
    	PreferenceSRSDao dao2 = context.getBean(PreferenceSRSDao.class);
    	
    	
    	List<PreferenceSRS> listwebM = dao0.findByEpsg("3857");
    	List<PreferenceSRS> listlam = dao1.findByEpsg("2154");
    	List<PreferenceSRS> listwgs = dao2.findByEpsg("32631");
    	
    	
    	if(listwebM!=null) listwebM.get(0).setActivatesrs(Boolean.valueOf(webMVal));
    	if(listlam!=null) listlam.get(0).setActivatesrs(Boolean.valueOf(lambertVal));
    	if(listwgs!=null) listwgs.get(0).setActivatesrs(Boolean.valueOf(wgsVal));
    	
    	dao0.save(listwebM.get(0));
    	dao1.save(listlam.get(0));
    	dao2.save(listwgs.get(0));
    	
    	context.close();
    	
    	return null;
    }
    
    @GET
    @Path ("/televersement/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTeleversement() {
		
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");/*ouvre la connexion bdd*/
    	
    	/*action faites sur la table*/
    	ParametersDao dao = context.getBean(ParametersDao.class);/* dao permet transaction CRUD*/
    	List<Parameters> listeParams = dao.findAll();
    	if (listeParams.size()!= 1){
    		return null;
    	}
    	
    	String tlurl = listeParams.get(0).getTlurl();
    	
    	context.close(); /*ferme connexion bdd*/

		return tlurl;
    }
    
    
 }

