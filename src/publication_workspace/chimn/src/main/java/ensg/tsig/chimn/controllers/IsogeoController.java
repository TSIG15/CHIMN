package ensg.tsig.chimn.controllers;


import ensg.tsig.chimn.dao.CriteriaDao;
import ensg.tsig.chimn.dao.MetaDataDao;
import ensg.tsig.chimn.entities.*;
import ensg.tsig.chimn.utils.MsgLog;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Stateful;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public  class IsogeoController {

    private  final static String  authURL = "https://id.api.isogeo.com/oauth/token";
	private  final static String requestURL="https://v1.api.isogeo.com/resources/search?";
    private  String consumerKey;
    private  String consumerSecret;
    
    private  String credentialsEncoded ;
    private  String access_token;
    private  String token_type;
    
    private List<MetaData> gross_metadata=new ArrayList<MetaData>() ;
    private List<String> tags=new ArrayList<String>();
    private Map<String,String> keywords = new HashMap<String,String>();
    
	/**
	 * @param consumerKey
	 * @param consumerSecret
	 */
	public IsogeoController(String consumerKey, String consumerSecret) {
		super();
		
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		
		String credentials = consumerKey + ":" + consumerSecret;
  	  	credentialsEncoded = new String (Base64.encodeBase64(credentials .getBytes()));
  	 
	}

	public void getToken()
	    {
	    	HttpClient client = new DefaultHttpClient();
	    	HttpResponse response;
	    	SSLSocketFactory sf;
	    	TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
			    @Override
			        public boolean isTrusted(X509Certificate[] certificate, String authType) {
			            return true;
			        }
			    };
			    
			try {
					
				    sf = new SSLSocketFactory(acceptingTrustStrategy);
					client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, sf));
					//setting proxy to be intercepted by fiddler
					HttpHost proxy = new HttpHost("localhost", 8888);
					client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);

					
			    	HttpPost post = new HttpPost(authURL);

			    	// add header
			    	post.setHeader("Authorization", "Basic "+credentialsEncoded);
			    	//post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.110 Safari/537.36");
			    	
			    	
			    	//set parameters for body's request
			    	List<org.apache.http.NameValuePair> nameValuePairs = new ArrayList<org.apache.http.NameValuePair>(1);
			    	nameValuePairs.add(new BasicNameValuePair("grant_type","client_credentials"));		    	
					try {
							post.setEntity(new UrlEncodedFormEntity( nameValuePairs));
						} 
					catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							try {
								MsgLog.write(e1.getMessage());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

			    	//send and read the response 
					try {
						response = client.execute(post);
						System.out.println("Response Code POST: " + response.getStatusLine().getStatusCode());
						BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
						String result = new String();
						String line = "";
						while ((line = rd.readLine()) != null) {
			    		result+=(line);
			    		
						}
						JSONObject jsonObject = (JSONObject)new JSONParser().parse(result);
						 access_token=(String) jsonObject.get("access_token");
						 token_type=(String) jsonObject.get("token_type");
						System.out.println(result);
						System.out.println("ceci est le token "+access_token);
						System.out.println("ceci est le type "+token_type);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
				} catch (KeyManagementException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (UnrecoverableKeyException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (NoSuchAlgorithmException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (KeyStoreException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
	    }
	
	    //deprecated : should be replaced by a tag as an entity
	public boolean initializeTags()
	{
		
		JSONObject jsonObject=search_metadata_from_isogeo("", "", "", "", "", "", "", "", 0);
						
						
						String str_tags = jsonObject.get("tags").toString();
						JSONObject jsonTags;
						try {
							jsonTags = (JSONObject)new JSONParser().parse(str_tags);
							System.out.println("voici keywords : "+jsonTags);
							System.out.println("voici tags values  : "+jsonTags.values());
							//get all keywords ????
							Iterator i=jsonTags.values().iterator();
							String tagValue;
							while(i.hasNext())
								{
								tagValue=(String) i.next();
								if(tagValue!=null)
									tags.add(tagValue);
									
								}
							return true;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							e.printStackTrace();
							try {
								MsgLog.write(e.getMessage());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						
		return false;
		
	}
	
	
	public boolean initializeKeyWords(String keywords)
	{
		System.out.println("old keywords"+keywords); 
		keywords=keywords.replace(" ", "%20");
		keywords=keywords.replace(":", "%3A");
		System.out.println("new keywords"+keywords);
		JSONObject jsonObject=search_metadata_from_isogeo(keywords, "", "", "", "", "", "", "", 0);
						
						
						String str_tags = jsonObject.get("tags").toString();
						JSONObject jsonTags;
						try {
							jsonTags = (JSONObject)new JSONParser().parse(str_tags);
							System.out.println("voici keywords : "+jsonTags);
							System.out.println("voici tags values  : "+jsonTags.values());
							
							Set<String> keys = jsonTags.keySet();
							Iterator i=keys.iterator(); // on crée un Iterator pour parcourir notre HashSet
							while(i.hasNext()) // tant qu'on a un suivant
							{
								
								String cle = (String) i.next();
								String val = (String) jsonTags.get(String.valueOf(cle));
							    if(cle.contains("keyword:"))
							    	{
							    		System.out.println("cle=" + cle + ", valeur=" + val);
							    		this.keywords.put(cle, val);
							    	}
							    
							}
							
							return true;
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							
							try {
								MsgLog.write(e.getMessage());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						
		return false;
		
	}
	
	public boolean initializeGrossMetaData(String query,String subResources,String bbox,String poly, String georel, String orderedBy, String orderDir, String pageSize, int offset)
	{
		String name,license,created,modified,idisogeo,geometryType,srs;
		srs="EPSG:4326"; //default value for srs
		String keywords_criteria; //to get from database "creteria"
		boolean license_criteria=false;  //to get from database
    	boolean deleted=false;
    	//getting criteria from chimn database :keywords and license
    		//0) initiate context for crud operations
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        CriteriaDao dao = context.getBean(CriteriaDao.class);
        
        List<Criteria> criteria=dao.findAll();
        
        if(criteria.size()!=1) 
        	
        	return false; //if there is more than one criteria in the table retrn false
        	
    
        keywords_criteria=criteria.get(0).getKeyword();
        keywords_criteria=keywords_criteria.replace(" ", "%20");
        keywords_criteria=keywords_criteria.replace(":", "%3A");
		
        license_criteria=criteria.get(0).isLicense();
        
        context.close();
 
    	
    	
    	JSONObject jsonObject=search_metadata_from_isogeo(keywords_criteria, subResources, bbox, poly, georel, orderedBy, orderDir, pageSize, offset);
		JSONArray jsonResult= (JSONArray) jsonObject.get("results");
		//getting the srs from metadat => tags
		String str_tags = jsonObject.get("tags").toString();
		JSONObject jsonTags;
		try {
			jsonTags = (JSONObject)new JSONParser().parse(str_tags);
			
			Set<String> keys = jsonTags.keySet();
			Iterator i=keys.iterator(); // on crée un Iterator pour parcourir notre HashSet
			while(i.hasNext()) // tant qu'on a un suivant
			{
				
				String cle = (String) i.next();
				String val = (String) jsonTags.get(String.valueOf(cle));
			    if(cle.contains("coordinate-system"))
			    	{
			    		cle=cle.substring(cle.lastIndexOf(":") + 1);
			    		System.out.println("cle=" + cle + ", valeur=" + val);
			    		srs="EPSG:"+cle;
			    	}
			    
			}
		System.out.println("voici results : "+jsonResult);
		for(int j=0; j<jsonResult.size(); j++)
			{
			//getting proproties from isogeo response
			JSONObject jsonObject2;
			try {
				jsonObject2 = (JSONObject) new JSONParser().parse(jsonResult.get(j).toString());
				
				name=jsonObject2.get("name").toString();
				created=jsonObject2.get("_created").toString();
				modified=jsonObject2.get("_modified").toString();
				idisogeo=jsonObject2.get("_id").toString();
				geometryType=jsonObject2.get("geometry").toString();
				System.out.println(geometryType);
				//testing if meta data has conditions
				JSONArray conditions= (JSONArray) jsonObject2.get("conditions");
				if(license_criteria==true) //
				{//add only metadata that has a license
					if(conditions!=null)
					{
						JSONObject condition0 = (JSONObject) new JSONParser().parse(conditions.get(0).toString());
						JSONObject licence = (JSONObject) new JSONParser().parse(condition0.get("license").toString());
						license=licence.get("name").toString();
						//bug to resolve later...
						if(jsonObject2.get("_deleted")!=null)
							deleted=Boolean.parseBoolean(jsonObject2.get("_deleted").toString());						
						gross_metadata.add(new MetaData(name,license,created,modified,deleted,idisogeo,srs,geometryType));
						
	
					}
					
					
				}
				else //add a metadata enven it has not a license
					//bug to resolve later...
					{
					if(jsonObject2.get("_deleted")!=null)
					
						deleted=Boolean.parseBoolean(jsonObject2.get("_deleted").toString());
						
						gross_metadata.add(new MetaData(name,"none",created,modified,deleted,idisogeo,srs,geometryType));
						
			}
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					MsgLog.write(e.getMessage());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}							
			
			}
		}catch (Exception e)
			{
				try {
					MsgLog.write(e.getMessage());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			return true;
	
	}
	
	public JSONObject search_metadata_from_isogeo(String query,String subResources, String bbox,String poly, String georel, String orderedBy, String orderDir, String pageSize, int offset)
	    {
	    	HttpClient client = new DefaultHttpClient();
	    	HttpResponse response;
	    	SSLSocketFactory sf;
	    	TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
			    @Override
			        public boolean isTrusted(X509Certificate[] certificate, String authType) {
			            return true;
			        }
			    };
			    
						try {
							sf = new SSLSocketFactory(acceptingTrustStrategy);
							client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, sf));
							//setting proxy to be intercepted by fiddler or Charles for clem's Mac os x
							HttpHost proxy = new HttpHost("localhost", 8888);
							client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);

						
					    	HttpGet get = new HttpGet(requestURL+"_include="+subResources+"&_limit="+pageSize+"&_offset="+
					    			offset+"&box="+bbox+"&geo="+poly+"&rel="+georel+"&ob="+
					    			orderedBy+"&od="+orderDir+"&q="+query);

					    	// add header
					    	get.setHeader("Authorization", token_type+" "+access_token);
					
								response = client.execute(get);
							 	System.out.println("Response Code of Get Request : " 
					                    + response.getStatusLine().getStatusCode());

					    	BufferedReader rd = new BufferedReader(
					    		new InputStreamReader(response.getEntity().getContent()));

					    	StringBuffer result = new StringBuffer();
					    	String line = "";
					    	while ((line = rd.readLine()) != null) {
					    		result.append(line);
					    	}
					    	
					    	System.out.println(result);
					    	String str_result=result.toString();
					    	JSONObject jsonObject;
							
							jsonObject = (JSONObject) new JSONParser().parse(str_result);
							return jsonObject ;
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							try {
								MsgLog.write(e.getMessage());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						} 
										
						return null;					
	    }
	
	
	public boolean setHistoricalMetaData()
    {
    	//0) initiate context for crud operations
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        MetaDataDao dao = context.getBean(MetaDataDao.class);
 
    	//set all metadata.asked in db to false
        List<MetaData> temList=dao.findAll();
        for(int i=0;i<temList.size();i++)
        {
        	temList.get(i).setAsked(false);
        	dao.save(temList.get(i));
        }
        
       //1) search metadata that verify criteria (keywords, owner)
    	if(!initializeGrossMetaData("", "conditions", "", "", "", "", "", "3", 0))
    		// query, subResources, bbox, poly, georel, orderedBy, orderDir, pageSize, offset
    		// subResources fait appel au paramètre "_include" de la recherche url qui permet de récupérer les 
    		// sous-ressources d'un ressource. Passer "conditions" revient à écrire dans l'url "?_include=conditions"
    		// et dans notre cas cela nous permet d'avoir accès à la licence de la MD.
    		{
    		System.out.println("erreur de mise à jour de l'historique des métadonnées");
    		return false;
    		}
    	
    	//2)  update the table "metadata": 
    		//2-1 create metadata if doesn't exist
    			List<MetaData> lm=new ArrayList <MetaData>();
    			for(int i=0;i<gross_metadata.size();i++)
    			{	 lm=dao.findByIdisogeo(gross_metadata.get(i).getIdisogeo());
    				if(lm.size()==0)
    						{
    							
    							dao.save(gross_metadata.get(i));
    						
    						}
    				else
    					{	
    						//metadata is not modified
    						if(lm.get(0).get_modified().equals(gross_metadata.get(i).get_modified()))
    							{
    								lm.get(0).setChanged(false);
    								lm.get(0).setAsked(true);
        							
        							
    							}
    						else  //2-2 update attributes : _modified, _deleted,_licence, asked
    						{
    							
    							lm.get(0).setChanged(true);
    							lm.get(0).set_modified(gross_metadata.get(i).get_modified());
    							lm.get(0).set_deleted(gross_metadata.get(i).is_deleted());
    							lm.get(0).setlicense(gross_metadata.get(i).getlicense());
    							lm.get(0).setAsked(true);
    							
    						}
    						dao.save(lm.get(0));
    				
    					}
    			}
    			context.close();
    			return true;
    }
	    
	public String getAuthURL() {
		return authURL;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public String getCredentialsEncoded() {
		return credentialsEncoded;
	}

	public void setCredentialsEncoded(String credentialsEncoded) {
		this.credentialsEncoded = credentialsEncoded;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getToken_type() {
		return token_type;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public List<MetaData> getGross_metadata() {
		return gross_metadata;
	}

	public void setGross_metadata(List<MetaData> gross_metadata) {
		this.gross_metadata = gross_metadata;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Map<String,String> getKeywords() {
		return keywords;
	}

	public void setKeywords(Map<String,String> keywords) {
		this.keywords = keywords;
	}
    
}
