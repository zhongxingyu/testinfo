package org.example.project.memGraph;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.google.gson.JsonObject;
import org.example.project.DBMSSpecificOptions;
import org.example.project.OracleFactory;
import org.example.project.common.oracle.TestOracle;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Parameters(separators = "=", commandDescription = "MemGraph (default port: " + MemGraphOptions.DEFAULT_PORT
        + ", default host: " + MemGraphOptions.DEFAULT_HOST)
public class MemGraphOptions implements DBMSSpecificOptions {

    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 7687; //todo 改


    @Parameter(names = "--host")
    public String host = DEFAULT_HOST;

    @Parameter(names = "--port")
    public int port = DEFAULT_PORT;

    @Parameter(names = "--username")
    public String username = "sqlancer";

    @Parameter(names = "--password")
    public String password = "sqlancer";

    @Parameter(names = "--restart-command")
    public String restartCommand = "";

    public static MemGraphOptions parseOptionFromFile(JsonObject jsonObject){
        MemGraphOptions options = new MemGraphOptions();
        if(jsonObject.has("host")){
            options.host = jsonObject.get("host").getAsString();
        }
        if(jsonObject.has("port")){
            options.port = jsonObject.get("port").getAsInt();
        }
        if(jsonObject.has("username")){
            options.username = jsonObject.get("username").getAsString();
        }
        if(jsonObject.has("password")){
            options.password = jsonObject.get("password").getAsString();
        }
        if(jsonObject.has("restart-command")){
            options.restartCommand = jsonObject.get("restart-command").getAsString();
        }
        return options;
    }

    public String getHost() {
        return host;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

}
