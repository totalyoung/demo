package netty.gateway.listener;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;

import static org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type.*;
import static org.apache.curator.framework.recipes.cache.TreeCacheEvent.Type.INITIALIZED;

public abstract class ZKListener implements Listener, TreeCacheListener {

    @Override
    public void childEvent(CuratorFramework client, TreeCacheEvent event) {
        if (event.getType().equals(NODE_ADDED)) {
            nodeAdded(client,event);
        }
        if (event.getType().equals(NODE_UPDATED)) {
            nodeUpdated(event);
        }
        if (event.getType().equals(NODE_REMOVED)) {
            nodeRemoved(event);
        }
        if (event.getType().equals(INITIALIZED)) return;
        System.out.println(event.getType().name() + ":" + event.getData().getPath());
    }

    public void nodeAdded(CuratorFramework client, TreeCacheEvent event) {

    }

    public void nodeUpdated(TreeCacheEvent event){

    }

    public void nodeRemoved(TreeCacheEvent event){

    }

}
