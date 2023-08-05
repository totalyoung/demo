package netty.gateway;

import netty.gateway.register.PathResolver;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;

import static org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type.*;

public class ProxyLister implements TreeCacheListener {

    private EndpointManager endpointManager;

    public ProxyLister(EndpointManager endpointManager) {
        this.endpointManager = endpointManager;
    }

    @Override
    public void childEvent(CuratorFramework client, TreeCacheEvent event){
        ChildData data = event.getData();
        String path = data.getPath();
        if(isNodeEvent(event)){
            data.getData();
            PathResolver resolver = new PathResolver(path);
            if(resolver.isServerPath()){
                endpointManager.addEndpointCluster(resolver.getServerSplit());
            }
            if(resolver.isEndpointPath()){
                endpointManager.addEndpoint(resolver);
            }
        }
        System.out.println(event.getType().name() +":"+ path);
    }

    public boolean isNodeEvent(TreeCacheEvent event){
        TreeCacheEvent.Type type = event.getType();
        return type.equals(NODE_ADDED) || type.equals(NODE_REMOVED) ||type.equals(NODE_UPDATED);
    }



}
