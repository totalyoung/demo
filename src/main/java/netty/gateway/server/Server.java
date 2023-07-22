package netty.gateway.server;

import java.util.HashMap;
import java.util.Map;

public class Server {

   private String host;

   private int port;

   private String path;

   private String name;

   private Map<String, Mapping> mappingMap;

   public Server(String host, int port, String name) {
      this.host = host;
      this.port = port;
      this.name = name;
      this.mappingMap = new HashMap<>();
   }

   public void addMapping(Mapping mapping){
      mappingMap.put(mapping.path(),mapping);
   }

   public String getHost() {
      return host;
   }

   public int getPort() {
      return port;
   }

   public String getPath() {
      return path;
   }

   public String getName() {
      return name;
   }

   public Map<String, Mapping> getMappingMap() {
      return mappingMap;
   }
}
