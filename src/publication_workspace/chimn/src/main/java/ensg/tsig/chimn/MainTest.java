/**
 * 
 */
package ensg.tsig.chimn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import ensg.tsig.chimn.controllers.IsogeoController;
import ensg.tsig.chimn.controllers.PublisherController;
import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.MailBuilder;

/**
 *
 *
 */
public class MainTest {
	public static void main(String[] args) {
		/**testing path for logs
		 
		 
		String st = System.getenv("CATALINA_HOME");
		       System.out.println("Current relative path is: " + st);*/
		
		Configuration configuration = new Configuration()
			    .domain(".mailgun.org")
			    .apiKey("key-")
			    .from("Test account", "postmaster@.mailgun.org");
		
		MailBuilder.using(configuration)
	    .to("@gmail.com")
	    .subject("This is the subject")
	    .text("Hello world!")
	    .build()
	    .send();
		
		////Test IsoGeo
    /*IsogeoController isogeo=new IsogeoController("projet-ensg-d2e472b0f92940ee87f9d1ac6e3e90d0","jvdMBbVJXiiOSQshFxHFPdlZCNhfvCdJlSkKrZA3npEHns9zOBY1bQuYqtV3xLTd");
    System.out.println("credential encoded: "+isogeo.getCredentialsEncoded());
    isogeo.getToken();
    System.out.println("token type : "+isogeo.getToken_type());
    System.out.println("token access : "+isogeo.getAccess_token());*/
    
    //test updating metadata in chimn database
    
    //isogeo.setHistoricalMetaData();
    
    //test search a word in the metadata in chimn database
    
    //isogeo.search_metadata_from_isogeo("parole", "conditions", "", "", "", "", "", "3", 0);       
    // query, subResources, bbox, poly, georel, orderedBy, orderDir, pageSize, offset
    
    
		/*//test getting parameters for publisher
		 
		PublisherController publisher=new PublisherController();
		publisher.initializeParameters();
		System.out.println(publisher.getParameters().toString());
		//test connexion to geoserver
		if(publisher.setDataSource())
			System.out.println("connexion to geoserver ok ");
		else 
			System.out.println("connexion to geoserver KO :/ ");
		//test getting appropriate layers for publisher
		
		publisher.publish();*/
    
    //test getting tags
    
    //isogeo.initializeTags();
    //test getting keywords
    
    //isogeo.initializeKeyWords("");

   }
}
