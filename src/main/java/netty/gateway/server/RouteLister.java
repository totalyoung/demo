package netty.gateway.server;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;

public class RouteLister implements TreeCacheListener {


    @Override
    public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {

    }
}
