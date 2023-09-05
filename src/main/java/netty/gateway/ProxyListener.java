package netty.gateway;

import netty.gateway.listener.ProviderListener;
import netty.gateway.listener.ZKListener;
import netty.gateway.register.PathResolver;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;

import static org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type.*;

public class ProxyListener extends ZKListener {

    private ProviderManager providerManager;

    public ProxyListener(ProviderManager providerManager) {
        this.providerManager = providerManager;
    }



    public boolean isNodeEvent(TreeCacheEvent event){
        TreeCacheEvent.Type type = event.getType();
        return type.equals(NODE_ADDED) || type.equals(NODE_REMOVED) ||type.equals(NODE_UPDATED);
    }

    public void nodeAdded(CuratorFramework client, TreeCacheEvent event){
        String path = event.getData().getPath();
        PathResolver resolver = new PathResolver(path);
        if(resolver.isServerPath()){
            Provider provider = providerManager.addProviderIfabsent(path);
            ProviderListener providerListener = new ProviderListener(provider);
            provider.subscribe(providerListener);
        }
//        if(resolver.isRoutePath()){
//            Provider provider = providerManager.getProvider(resolver.getServerSplit());
//
//        }
//        if(resolver.isEndpointPath()){
//            providerManager.addEndpoint(resolver);
//        }
    }

//    public void nodeUpdated(TreeCacheEvent event){
//        String path = event.getData().getPath();
//        PathResolver resolver = new PathResolver(path,event.getData().getData());
//        if(resolver.isMetaPath()) {
//            providerManager.setMetadata(resolver);
//        }
//    }
//
//    public void nodeRemoved(TreeCacheEvent event){
//        String path = event.getData().getPath();
//        PathResolver resolver = new PathResolver(path);
//        if(resolver.isProviderPath()){
//            providerManager.removeEndpoint(resolver);
//        }
//    }




}
