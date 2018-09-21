package automic.utils;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class Config {
	
	public String Hostname;
	public Integer Port;
	//Integer Client;
	public ArrayList<Integer> ClientArray;
	public String User;
	public String Department;
	public String Password;
	public Character Language;
	public Boolean MultipleClients = false;
	
	
	public Config getCredentialsFromFile(String filename) throws IOException, FileNotFoundException, Exception {
		JSONParser parser = new JSONParser();
		
        try
        {
            Object object = parser
                    .parse(new FileReader(filename));
            
            //convert Object to JSONObject
           JSONObject jsonObject = (JSONObject)object;
            
           JSONObject credentials = (JSONObject)jsonObject.get("credentials");
            
           this.Hostname = (String) credentials.get("hostname");
           this.User = (String) credentials.get("user");
           this.Port = (Integer) Integer.parseInt((String) credentials.get("port"));
           this.ClientArray = new ArrayList<Integer>();
           Object item = credentials.get("client");
           if (item instanceof JSONArray) {
        	   this.MultipleClients = true; 
        	   
        	   JSONArray clients = (JSONArray) item;
        	  
        	   for (int i = 0; i < clients.size(); i++) {
        		   int c = (Integer) Integer.parseInt(clients.get(i).toString());
        		   //System.out.println(c+" "+clients.size());
        		   this.ClientArray.add(c);
        	   }
        	   
        	   
           } else {
        	   this.ClientArray.add(Integer.parseInt((String)credentials.get("client")));
           }
           this.Department = (String) credentials.get("department");
           String tmp_pass = (String) credentials.get("password");
           if (tmp_pass != null ) {
        	   this.Password = tmp_pass;
           } else
        	  getPassFromInput(this);
           this.Language = (Character) ((String) credentials.get("language")).charAt(0);
          
	    }
        catch(FileNotFoundException fe)
        {
        	//fe.printStackTrace();
        	getConfigFromConsole(this);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		return null;
      
}
	private Config getPassFromInput(Config cfg) {

		
		  Console console = System.console();
	        if (console == null) {
	            System.out.println("Couldn't get Console instance");
	            System.exit(0);
	        }
	        
	        char passwordArray[] = console.readPassword("No password in config for "+cfg.User +" provided. Please enter it now: ");
	        //console.printf("Password entered was: %s%n", new String(passwordArray));
		
		this.Password = new String(passwordArray);
		
		return cfg;
		
	}
	
	private Config getConfigFromConsole(Config cfg) {
		
		
		Console console = System.console();
        if (console == null) {
            System.out.println("Couldn't get Console instance");
            System.exit(0);
        }
        
        System.out.println("No config file provided. Please enter the connection data.");
        
        String hostname = console.readLine("Hostname: ");
        String port = console.readLine("Port: ");
        String user = console.readLine("User: ");
        String department = console.readLine("Department: ");
        char passwordArray[] = console.readPassword("Password: ");
        String client = console.readLine("Client: ");
        
       
        cfg.Hostname = (String) hostname;
        cfg.User = (String) user;
        cfg.Port = (Integer) Integer.parseInt((String) port);
        cfg.ClientArray.add(Integer.parseInt((String) client));
        //cfg.Client = (Integer) Integer.parseInt((String) client);
        cfg.Department = (String) department;
        cfg.Password = new String(passwordArray);
        cfg.Language = 'E';
	
		
		return null; 
	}
	
	
}
