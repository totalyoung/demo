package netty.gateway.register;

import io.netty.handler.codec.http.HttpMethod;
import netty.gateway.Constants;
import netty.gateway.server.Mapping;
import netty.gateway.server.Server;

public class ServerRegister extends Register {

    private String path;

    public ServerRegister(String hostStr) {
        super(hostStr);
        this.path = createNode(Constants.SERVER_PATH,true);
    }


    public Server getServer(){
        return createServer();
    }

    public Server createServer(){
        Server server = new Server(Constants.LOACL_HOST,Constants.SERVER_PORT,"ume");
        server.addMapping(new Mapping("/get", HttpMethod.GET));
        return server;
    }


    @Override
    public void register() {
        Server server = getServer();
        String namePath = server.getName();
        if(namePath==null || namePath.length() == 0){
            namePath = server.getPath();
        }
        //添加服务节点，/server/namePath
        namePath = this.path+"/"+namePath;
        createNode(namePath, true);
        //添加endpoint节点，/server/namePath/endpoint
        String endpointPath = namePath +Constants.ENDPOINT_PATH;
        createNode(endpointPath,true);
        //添加ip地址节点，/server/namePath/endpoint
        createNode(endpointPath+"/"+server.getHost()+":"+server.getPort(),false);
    }


}
