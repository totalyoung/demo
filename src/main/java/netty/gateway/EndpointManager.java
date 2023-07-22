package netty.gateway;

import netty.gateway.register.Register;
import netty.gateway.server.EndpointCluster;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class EndpointManager {

    private Map<String, EndpointCluster> endpointMap = new HashMap<>();

    private Register proxyRegister ;


    public EndpointManager(Register proxyRegister) {
        this.proxyRegister = proxyRegister;
    }

    public Endpoint getEndpoint(String str){
        EndpointCluster endpointCluster = endpointMap.get(str);
        if(endpointCluster == null || endpointCluster.isEmpty()){
            //TODO 告警
            return  null;
        }
        return endpointCluster.getEndpoint();
    }

    public void start(){
        proxyRegister.register();
        proxyRegister.subscribe(new ProxyLister());
    }

    public EndpointCluster  addEndpointCluster(String name){
        EndpointCluster endpointCluster = endpointMap.get(name);
        if(endpointCluster==null){
            endpointCluster = new EndpointCluster(name);
            endpointMap.put(name, endpointCluster);
        }
        return endpointCluster;
    }

    public Endpoint addEndpoint(String path){
        String[] split = path.split("/");
        EndpointCluster endpointCluster = addEndpointCluster(split[Constants.SERVER_CHILD_INDEX]);
        String hostStr = split[Constants.ENDPOINT_CHILD_INDEX];
        Endpoint endpoint = new Endpoint(hostStr);
        endpointCluster.addEndpoint(endpoint);
        return endpoint;
    }

    public class ProxyLister implements TreeCacheListener {

        @Override
        public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
            String path = event.getData().getPath();
            String[] split = path.split("/");
            int length = split.length;
            switch (event.getType()){
                case NODE_ADDED:
                    if(length==3){
                        addEndpointCluster(split[Constants.SERVER_CHILD_INDEX]);
                    }else if(length == 5){
                        addEndpoint(path);
                    }
                    System.out.println("NODE_ADDED:" + path);
                    break;
                case NODE_REMOVED:
                    System.out.println("NODE_REMOVED:" + path);
                    break;
                case NODE_UPDATED:
                    System.out.println("NODE_UPDATED:" + path);
                    break;
                case CONNECTION_LOST:
                    System.out.println ("CONNECTION_LOST:" + path);
                    break;
                case CONNECTION_RECONNECTED:
                    System.out.println("CONNECTION_RECONNECTED:" + path);
                    break;
                case CONNECTION_SUSPENDED:
                    System.out.println("CONNECTION_SUSPENDED:" + path);
                    break;
                case INITIALIZED:
                    System.out.println("INITIALIZED:" + path);
                    break;
                default:
                    break;
            }
        }
    }

    public String getHostStr(String endpointPath){
        try {
            String decode = URLDecoder.decode(endpointPath, "UTF-8");
            return  decode.split("/")[0];
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
