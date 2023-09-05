package netty.spring;

import netty.gateway.Constants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PeachServerProperties.class)
@ConditionalOnProperty(prefix = Constants.PEACH_PREFIX,name = "enabled",havingValue = "true")
public class PeachAutoConfiguration {
}
