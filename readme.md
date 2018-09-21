## Description
Just basic utilities for interaction with Automic. 

## Configuration

There are following sections in the config json:
- credentials - connection to the Automic system
	- hostname
	- port
	- client - array of all clients that should be processed
	- user
	- password - if not provided in the config file you will be asked at runtime
	- department
	- language

If the configuration is not availabe you will be prompted for all the data on runtime. 

### Example config

```
{
"credentials":{
	"hostname":"127.0.0.1",
	"port":"2217",
	"client":[
	"100"
	],
	"user":"AUTOMIC",
	"password":"",
	"department":"AUTOMIC",
	"language":"E"
	}

## Example usage

```
import automic.utils.Config;
import automic.utils.ConnectionManager;
// [...]
cfg.getCredentialsFromFile(filename);
			
			for(Integer client : cfg.ClientArray) {
			
                System.out.println("Attempting to connect to: " + cfg.Hostname);
                
                Connection conn = new ConnectionManager().authenticate(
                        cfg.Hostname, cfg.Port, client, cfg.User, cfg.Department, cfg.Password, cfg.Language);


                // Your code			


                //End
                System.out.println("Closing connection");
                conn.close();
        }
```
