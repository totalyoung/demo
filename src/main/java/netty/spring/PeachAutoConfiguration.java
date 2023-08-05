package netty.spring;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PeachServerProperties.class)
@ConditionalOnProperty(prefix = PeachSpringConstants.PEACH_PREFIX,name = "enabled",havingValue = "true")
public class PeachAutoConfiguration {
}
