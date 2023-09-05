package netty.gateway.listener;

import netty.gateway.Provider;
import netty.gateway.RouteServer;
import netty.gateway.register.PathResolver;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;

public class ProviderListener extends ZKListener {

    private Provider provider;

    public ProviderListener(Provider provider) {
        this.provider = provider;
    }

    @Override
    public void nodeAdded(CuratorFramework client, TreeCacheEvent event) {
        String path = event.getData().getPath();
        PathResolver resolver = new PathResolver(path,event.getData().getData());
        if(resolver.isRoutePath()){
            String routeName = resolver.lastPath();
            provider.addRouteServerIfAbsent(routeName);
        }
        if(resolver.isHostPath()){
            String hostStr = resolver.lastPath();
            provider.addEndpointIfAbsent(hostStr);
            RouteServer routeServer = provider.addRouteServerIfAbsent(resolver.getRouteSplit());
            routeServer.addHost(hostStr);
        }
        if(resolver.isMetaPath()){
            if(resolver.isMetaPath()) {
                provider.setMetadata(resolver);
            }
        }
    }

}
