package netty.test;

import netty.gateway.EndpointManager;
import netty.gateway.register.PathResolver;
import org.junit.Before;
import org.junit.Test;

public class EndpointManagerTest {

    private EndpointManager endpointManager;

    @Test
    public void setMetadata(){
        PathResolver resolver = new PathResolver("/server/ume","".getBytes());
        endpointManager.setMetadata(resolver);
    }

    @Before
    public void newInstance(){
        endpointManager = new EndpointManager(null);
    }
}
