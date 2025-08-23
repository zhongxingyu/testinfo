package org.example.project.neo4j;

import org.example.project.common.query.projectResultSet;
import org.example.project.cypher.CypherConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

public class Neo4jJDBCConnection extends CypherConnection {
    private Neo4jOptions options;

    public Neo4jJDBCConnection(Neo4jOptions options){
        this.options = options;
    }

    @Override
    public String getDatabaseVersion() throws Exception {
        return null;
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void executeStatement(String arg) throws Exception{
        executeStatementAndGet(arg);
    }


    @Override
    public List<projectResultSet> executeStatementAndGet(String arg) throws Exception{
        try (Connection con = DriverManager.getConnection("jdbc:neo4j:bolt://"+options.host, options.username, options.password)) {
            // Querying
            try (PreparedStatement stmt = con.prepareStatement(arg)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    return Arrays.asList(new projectResultSet(rs));
                }
            }
        }
    }
}
