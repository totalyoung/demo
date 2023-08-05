package netty.spring;

import netty.common.NetUtil;
import netty.common.StringUtil;
import netty.gateway.register.ServerRegister;
import netty.gateway.Metadata;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Component
@ConditionalOnProperty(prefix = PeachSpringConstants.PEACH_PREFIX,name = "enabled",havingValue = "true")
public class TestApplicationListener implements ApplicationListener<ServletWebServerInitializedEvent> {

    @Resource
    private PeachServerProperties peachServerProperties;

    @Override
    public void onApplicationEvent(ServletWebServerInitializedEvent event) {
        String serverName = PeachSpringConstants.DEFAULT;
        ServletWebServerApplicationContext applicationContext = event.getApplicationContext();
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        if(StringUtil.hasLength(peachServerProperties.getPath())){
            serverName = peachServerProperties.getPath();
        }else if (environment.containsProperty("server.servlet.context-path")){
            serverName = environment.getProperty("server.servlet.context-path");
        }else if (environment.containsProperty("spring.application.name")){
            serverName = environment.getProperty("spring.application.name");
        }

        if(!StringUtils.hasLength(peachServerProperties.getRegisterHost())){
            //TODO throw exception
        }

        ServerRegister serverRegister = new ServerRegister(peachServerProperties.getRegisterHost());
        String localHost = NetUtil.localHostStr();
        serverRegister.createServer(localHost,Integer.parseInt(environment.getProperty("server.port")),serverName);
        serverRegister.register();

        serverRegister.setMetadata(createMetadata());
//        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
//        Map<RequestMappingInfo, HandlerMethod> methods = mapping.getHandlerMethods();
//        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : methods.entrySet()) {
//            RequestMappingInfo key = entry.getKey();
//        }

        System.out.println("web Server start!!!!");
    }

    public Metadata createMetadata(){
        Metadata meta = new Metadata();
        if(StringUtils.hasLength(peachServerProperties.getPath())){
            meta.setFixUrl(true);
        }
        return meta;
    }
}
