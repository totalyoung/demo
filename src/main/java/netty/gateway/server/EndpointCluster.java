package netty.gateway.server;

import netty.gateway.Endpoint;
import netty.gateway.Metadata;

import java.util.LinkedList;
import java.util.List;

public class EndpointCluster {

    private List<Endpoint> endpoints = new LinkedList<>();

    private String name ;

    private Metadata metadata;

    public EndpointCluster(String name) {
        this.name = name;
    }

    public void addEndpoint(Endpoint endpoint){
        endpoints.add(endpoint);
    }

    public boolean isEmpty(){
        return endpoints.isEmpty();
    }

    public Endpoint getEndpoint(){
        //TODO 负载均衡算法
        return endpoints.get(0);
    }

    public void setMetadata(Metadata metadata){
        this.metadata = metadata;
    }

}
