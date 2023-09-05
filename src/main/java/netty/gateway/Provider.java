package netty.gateway;

import com.google.gson.Gson;
import netty.common.RouteUtil;
import netty.gateway.listener.Listener;
import netty.gateway.listener.ZKListener;
import netty.gateway.register.Client;
import netty.gateway.register.PathResolver;
import netty.gateway.register.Subscriber;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCache;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Provider implements Subscriber {

    private String nodePath;

    private Map<RouteServer,RouteServer> routeMap = new TreeMap<>();

    private Metadata metadata;

    private Client client;

    private Map<String,Endpoint> endpointMap = new HashMap<>();

    public Provider(String nodePath,Client client) {
        this.nodePath = nodePath;
        this.client = client;
    }


    @Override
    public void subscribe(Listener listener) {
        TreeCache cache = new TreeCache((CuratorFramework) this.client.getClient(), nodePath);
        try {
            cache.start();
            cache.getListenable().addListener((ZKListener)listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RouteServer addRouteServerIfAbsent(String routeName){
        Route route = RouteUtil.fromJson(routeName);
        RouteServer rs = new RouteServer(route);
        RouteServer routeServer = routeMap.get(rs);
        if (routeServer == null) {
            routeMap.put(rs,rs);
            routeServer = rs;
        }
        return routeServer;
    }

    public Endpoint addEndpointIfAbsent(String hostStr){
        Endpoint endpoint;
        if((endpoint =endpointMap.get(hostStr))==null){
            endpoint = new Endpoint(hostStr);
            endpointMap.put(hostStr,endpoint);
        }
        return endpoint;
    }

    public void setMetadata(PathResolver resolver) {
        Gson g = new Gson();
        Metadata metadata;
        if (resolver.getData() == null || (metadata = g.fromJson(new String(resolver.getData()), Metadata.class)) == null) {
            metadata = new Metadata();
        }
        this.metadata = metadata;
    }


    @Override
    public String toString() {
        return "Provider{" +
                "nodePath='" + nodePath + '\'' +
                ", routeMap=" + routeMap +
                ", metadata=" + metadata +
                ", endpointMap=" + endpointMap +
                '}';
    }
}
