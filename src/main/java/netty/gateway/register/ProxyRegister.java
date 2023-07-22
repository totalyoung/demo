package netty.gateway.register;

import netty.gateway.Constants;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;

public class ProxyRegister extends Register {

    private String proxyPath = Constants.PROXY_PATH;


    public ProxyRegister(String hostStr) {
        super(hostStr);
    }

    @Override
    public void register(){
        createNode(proxyPath);
        createNode(serverPath);
    }


    public void subscribe(TreeCacheListener listener){
        try {
            final TreeCache cache = new TreeCache(client, serverPath);
            cache.start();
            cache.getListenable().addListener(listener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
