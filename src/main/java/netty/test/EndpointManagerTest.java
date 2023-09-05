package netty.test;

import netty.gateway.ProviderManager;
import netty.gateway.register.PathResolver;
import org.junit.Before;
import org.junit.Test;

public class EndpointManagerTest {

    private ProviderManager providerManager;

    @Test
    public void setMetadata(){
        PathResolver resolver = new PathResolver("/server/ume","".getBytes());
        providerManager.setMetadata(resolver);
    }

    @Before
    public void newInstance(){
        providerManager = new ProviderManager(null);
    }
}
