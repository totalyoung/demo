package netty.gateway.register;

import netty.gateway.Constants;
import netty.gateway.listener.Listener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;

public class ProxyZKClient extends ZKClient {

    private String proxyPath = Constants.PROXY_PATH;


    public ProxyZKClient(String hostStr) {
        super(hostStr);
    }

    @Override
    public void register(){
        createNode(proxyPath);
        createNode(rootPath);
    }


    @Override
    public void subscribe(Listener listener){
        try {
            final TreeCache cache = new TreeCache(client, rootPath);
            cache.start();
            cache.getListenable().addListener((TreeCacheListener)listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
