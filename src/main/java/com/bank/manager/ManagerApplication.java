package com.bank.manager;

import java.io.IOException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ManagerApplication {
//	private static final String CLIENT_ID = "3da988b9-e388-4ecd-a666-368283b93774";
//    private static final String CLIENT_SECRET = "GOz8Q~Ys5t0rBb0PSYw6CTgUjMzvEqMn8k-HxbHg";
//    private static final String TENANT_ID = "1c530c5e-04f2-4407-b634-3d5a12fccd9c";
//    private static final String AUTHORITY = "https://login.microsoftonline.com/" + TENANT_ID;
//    private static final String RESOURCE = "https://java-spring-api-bank.azurewebsites.net/";
	public static void main(String[] args) throws Exception  {
		
	    
//        // Step 1: Get the Bearer Token
//        String token = getBearerToken();
//        
//        // Step 2: Use the Bearer Token to make API requests
//        HttpGet httpGet = new HttpGet("https://java-spring-api-bank.azurewebsites.net/accounts");
//        httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
//        
//        // Add any additional headers or parameters as needed
//        httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
//        
//        // Execute the API request and process the response
//        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
//            CloseableHttpResponse response = httpClient.execute(httpGet);
//            System.out.println("response :- "+response);
//            // Process response
//        }
		SpringApplication.run(ManagerApplication.class, args);
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		
//		Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","ram","tiger");
//		
//		Statement st=con.createStatement();
//		ResultSet rs=st.executeQuery("Select * from my_table");
//		while(rs.next()) {
//			System.out.println(rs.getInt(1)+" "+rs.getString(2));
//		}
//		rs.close();
//		st.close();

    
    
	    
	   
	        
	} 
//	private static String getBearerToken() throws IOException {
//        // Use the ADAL4J library to obtain a Bearer Token
//        AuthenticationContext context = new AuthenticationContext(AUTHORITY, false, Executors.newFixedThreadPool(1));
//        ClientCredential credential = new ClientCredential(CLIENT_ID, CLIENT_SECRET);
//        Future<AuthenticationResult> future = context.acquireToken(RESOURCE, credential, null);
//        try {
//            AuthenticationResult result = future.get();
//            System.out.println("here");
//            return result.getAccessToken();
//        } catch (Exception e) {
//        	return null;
//        }
//        	}

}
