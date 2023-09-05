package netty.gateway.server;

import netty.gateway.Endpoint;
import netty.gateway.Metadata;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Deprecated
public class EndpointCluster {

    private List<Endpoint> endpoints = new CopyOnWriteArrayList<>();

    private String name ;

    private volatile Metadata metadata;

    public EndpointCluster(String name) {
        this.name = name;
    }

//    public void addEndpoint(Endpoint endpoint){
//        endpoints.add(endpoint);
//    }

    public Endpoint addEndpoint(String hostStr){
        Endpoint endpoint = new Endpoint(hostStr);
        endpoints.add(endpoint);
        return endpoint;
    }

    public boolean isEmpty(){
        return endpoints.isEmpty();
    }

    public Endpoint getEndpoint(){
        //TODO 负载均衡算法
        if(endpoints.isEmpty()) throw new RuntimeException("没有endpoint");
        return endpoints.get(0);
    }

    public void setMetadata(Metadata metadata){
        this.metadata = metadata;
    }

    public void removeEndpoint(String hostStr){
        endpoints.remove(new Endpoint(hostStr));
    }

}
