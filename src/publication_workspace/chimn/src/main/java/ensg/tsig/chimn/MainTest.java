/**
 * 
 */
package ensg.tsig.chimn;

import ensg.tsig.chimn.controllers.IsogeoController;

/**
 * @author hanane
 *
 */
public class MainTest {
	public static void main(String[] args) {
    IsogeoController isogeo=new IsogeoController("projet-ensg-d2e472b0f92940ee87f9d1ac6e3e90d0","jvdMBbVJXiiOSQshFxHFPdlZCNhfvCdJlSkKrZA3npEHns9zOBY1bQuYqtV3xLTd");
    System.out.println("credential encoded: "+isogeo.getCredentialsEncoded());
    isogeo.getToken();
    System.out.println("token type: "+isogeo.getToken_type());
    System.out.println("token access: "+isogeo.getAccess_token());
    
    isogeo.setHistoricalMetaData();
	}
}
