/**
 * 
 */
package ensg.tsig.chimn;

import ensg.tsig.chimn.controllers.IsogeoController;
import ensg.tsig.chimn.controllers.PublisherController;

/**
 * @author hanane
 *
 */
public class MainTest {
	public static void main(String[] args) {
		
		////Test IsoGeo
   /* IsogeoController isogeo=new IsogeoController("projet-ensg-d2e472b0f92940ee87f9d1ac6e3e90d0","jvdMBbVJXiiOSQshFxHFPdlZCNhfvCdJlSkKrZA3npEHns9zOBY1bQuYqtV3xLTd");
    System.out.println("credential encoded: "+isogeo.getCredentialsEncoded());
    isogeo.getToken();
    System.out.println("token type: "+isogeo.getToken_type());
    System.out.println("token access: "+isogeo.getAccess_token());
    
    isogeo.setHistoricalMetaData();*/
		//test getting parameters for publisher
		PublisherController publisher=new PublisherController();
		publisher.initializeParameters();
		System.out.println(publisher.getParameters().toString());
		//test connexion to geoserver
		if(publisher.setDataSource())
			System.out.println("connexion to geoserver ok ");
		else 
			System.out.println("connexion to geoserver KO :/ ");
	}
}
