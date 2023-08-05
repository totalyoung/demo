package netty.gateway.register;

import com.google.gson.Gson;
import netty.gateway.Constants;
import netty.gateway.Metadata;
import netty.gateway.server.Server;

public class ServerRegister extends Register {

    private String path = Constants.SERVER_PATH;

    private Server server;

    private String namePath;

    private String endpointPath;

    private String metaPath;

    private boolean isRegister;


    public ServerRegister(String hostStr) {
        super(hostStr);
    }


    public Server getServer() {
        return createServer();
    }

    public Server createServer() {
        return createServer(Constants.LOACL_HOST, Constants.SERVER_PORT, "ume");
    }

    public Server createServer(String host, int port, String name) {
        Server server = new Server(host, port, name);
        this.server = server;
        if (name == null || name.length() == 0) {
            name = server.getPath();
        }
        this.namePath = this.path + "/" + name;
        this.endpointPath = this.namePath + Constants.ENDPOINT_PATH;
        this.metaPath = this.namePath + Constants.META_PATH;
        return this.server;
    }


    @Override
    public void register() {
        Server server = this.server;
        //添加根节点
        createNode(Constants.SERVER_PATH, true);
        //添加服务节点，/server/namePath
        createNode(this.namePath, true);
        //添加endpoint节点，/server/namePath/endpoint
        createNode(this.endpointPath, true);
        //添加ip地址节点，/server/namePath/endpoint
        createNode(this.endpointPath + "/" + server.getHost() + ":" + server.getPort(), false);
        //添加endpoint节点，/server/namePath/meta
        createNode(this.metaPath, true);
        isRegister = true;
    }

    @Override
    public void setMetadata(Metadata data) {
        if(isRegister){
            setNodeData(this.metaPath,new Gson().toJson(data).getBytes());
        }else{
            //TODO throw exception

        }
    }
}
