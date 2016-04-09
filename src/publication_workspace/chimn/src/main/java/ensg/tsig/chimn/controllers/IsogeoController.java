package ensg.tsig.chimn.controllers;


import ensg.tsig.chimn.dao.MetaDataDao;
import ensg.tsig.chimn.entities.*;
import java.util.List;

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
    
    private  List<MetaData> gross_metadata=new ArrayList<MetaData>() ;
    
    
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
					//settng proxy to be intercepted by feddler
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
						System.out.println("ceci est le token"+access_token);
						System.out.println("ceci est le type"+token_type);
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
	    
	public void search_metadata_from_isogeo(String query,String subResources, String bbox,String poly, String georel, String orderedBy, String orderDir, String pageSize, int offset, String wholeShare, String prot)
	    {
	    	HttpClient client = new DefaultHttpClient();
	    	HttpResponse response;
	    	SSLSocketFactory sf;
	    	String title,license,created,modified,idisogeo;
	    	boolean deleted=false;
	    	TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
			    @Override
			        public boolean isTrusted(X509Certificate[] certificate, String authType) {
			            return true;
			        }
			    };
			    
			
					
				    try {
						sf = new SSLSocketFactory(acceptingTrustStrategy);
						client.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, sf));
						//settng proxy to be intercepted by feddler
						HttpHost proxy = new HttpHost("localhost", 8888);
						client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);

						
				    	HttpGet get = new HttpGet(requestURL+"_include="+subResources+"&_limit="+pageSize+"&_offset="+
				    			offset+"&box="+bbox+"geo="+poly+"&rel="+georel+"&ob="+
				    			orderedBy+"&od="+orderDir+"&q="+query);

				    	// add header
				    	get.setHeader("Authorization", token_type+" "+access_token);
				    	
				    	
						try {
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
						try {
							jsonObject = (JSONObject)new JSONParser().parse(str_result);
							JSONArray jsonResult= (JSONArray) jsonObject.get("results");
							System.out.println("voici results : "+jsonResult);
							for(int i=0; i<jsonResult.size(); i++)
								{
								//getting proproties from isogeo response
								JSONObject jsonObject2 = (JSONObject)new JSONParser().parse(jsonResult.get(i).toString());							
								JSONArray conditions= (JSONArray) jsonObject2.get("conditions");
								
								JSONObject condition0 = (JSONObject)new JSONParser().parse(conditions.get(0).toString());
								
								JSONObject licence = (JSONObject)new JSONParser().parse(condition0.get("license").toString());
								 
								
								title=jsonObject2.get("title").toString();
								created=jsonObject2.get("_created").toString();
								modified=jsonObject2.get("_modified").toString();
								idisogeo=jsonObject2.get("_id").toString();
								license=licence.get("name").toString();
								//bug to resolve later...
								if(jsonObject2.get("_deleted")!=null)
									deleted=Boolean.parseBoolean(jsonObject2.get("_deleted").toString());
								
								gross_metadata.add(new MetaData(title,license,created,modified,deleted,idisogeo));

								System.out.println(gross_metadata.get(i).isChanged());
								
								}
								
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 
						} catch (ClientProtocolException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    	
					} catch (KeyManagementException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnrecoverableKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (KeyStoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			
	    
	    }
	
	public void setHistoricalMetaData()
    {
    	//0) initiate context for crud operations
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        MetaDataDao dao = context.getBean(MetaDataDao.class);
 
    	
    	//1) search metadata that verify criteria (keywords, owner)
    			search_metadata_from_isogeo("", "conditions", "", "", "", "", "", "", 0, "", "");
    	
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
    						if(lm.get(0).get_modified()==gross_metadata.get(i).get_modified())
    							lm.get(0).setChanged(false);
    						else  //2-2 update attributes : _modified, _deleted,_licence
    						{
    							lm.get(0).setChanged(true);
    							lm.get(0).set_modified(gross_metadata.get(i).get_modified());
    							lm.get(0).set_deleted(gross_metadata.get(i).is_deleted());
    							lm.get(0).setlicense(gross_metadata.get(0).getlicense());
    							
    						}
    				
    					}
    			}
    			context.close();
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



    
 
    
}
