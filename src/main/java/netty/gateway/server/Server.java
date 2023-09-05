package netty.gateway.server;

import netty.gateway.Host;

import java.util.HashMap;
import java.util.Map;

public class Server extends Host {

   private String name;

   private Map<String, Mapping> mappingMap;

   public Server(String host, int port, String name) {
      super(host,port);
      this.name = name;
      this.mappingMap = new HashMap<>();
   }

   public void addMapping(Mapping mapping){
      mappingMap.put(mapping.path(),mapping);
   }

   public String getName() {
      return name;
   }

   public Map<String, Mapping> getMappingMap() {
      return mappingMap;
   }

}
