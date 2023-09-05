package netty.spring;

import netty.gateway.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = Constants.PEACH_PREFIX)
public class PeachServerProperties {

    private String path;

    private String registerHost;

//    private Boolean enable;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRegisterHost() {
        return registerHost;
    }

    public void setRegisterHost(String registerHost) {
        this.registerHost = registerHost;
    }

}
