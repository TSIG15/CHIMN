/**
 * 
 */
package ensg.tsig.chimn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import ensg.tsig.chimn.controllers.IsogeoController;
import ensg.tsig.chimn.controllers.PublisherController;

/**
 * @author hanane
 *
 */
public class MainTest {
	public static void main(String[] args) {
		Path currentRelativePath = Paths.get("");
		String st = currentRelativePath.toAbsolutePath().toString();
		System.out.println("Current relative path is: " + st);
		
		
		////Test IsoGeo
    IsogeoController isogeo=new IsogeoController("projet-ensg-d2e472b0f92940ee87f9d1ac6e3e90d0","jvdMBbVJXiiOSQshFxHFPdlZCNhfvCdJlSkKrZA3npEHns9zOBY1bQuYqtV3xLTd");
    System.out.println("credential encoded: "+isogeo.getCredentialsEncoded());
    isogeo.getToken();
    System.out.println("token type : "+isogeo.getToken_type());
    System.out.println("token access : "+isogeo.getAccess_token());
    
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
    String cmd = "python D:\\3eme_ENSG\\projet_industriel\\src\\publication_workspace\\chimn\\src\\main\\java\\ensg\\tsig\\chimn\\helloworld.py  ";
    String s = null;
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
            System.out.println(s);
        }
         
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
