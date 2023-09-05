package netty.gateway;

import netty.gateway.register.Client;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class ProviderManager {

    private Client client;

    private Map<String, Provider> providerMap = new HashMap<>();


    public ProviderManager(Client client) {
        this.client = client;
    }

    public void start() {
        client.register();
        client.subscribe(new ProxyListener(this));
    }

    public Provider getProvider(String nodePath){
        return providerMap.get(nodePath);
    }
    public Provider addProviderIfabsent(String nodePath) {
        Provider provider = providerMap.get(nodePath);
        if (provider == null) {
            provider = new Provider(nodePath,client);
            providerMap.put(nodePath, provider);
        }
        return provider;
    }


    public String getHostStr(String endpointPath) {
        try {
            String decode = URLDecoder.decode(endpointPath, "UTF-8");
            return decode.split("/")[0];
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
