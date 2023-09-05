package netty.gateway.register;

import netty.gateway.Constants;
import netty.gateway.Route;
import netty.gateway.server.Server;

import java.util.LinkedList;
import java.util.List;

public class ServerZKClient extends ZKClient {

    private String path = Constants.SERVER_PATH;

    private Server server;

    private String namePath;

    private String endpointPath;

//    private String metaPath;

    private boolean isRegister;

    private List<Route> routes = new LinkedList<>();

    public ServerZKClient(String hostStr) {
        super(hostStr);
    }

    public ServerZKClient(String hostStr, List<Route> routes) {
        super(hostStr);
        this.routes = routes;
    }

    public Server getServer() {
        return server;
    }

    public Server createServer() {
        return createServer(Constants.LOACL_HOST, Constants.SERVER_PORT, "ume");
    }

    public Server createServer(String host, int port, String name) {
        Server server = new Server(host, port, name);
        this.server = server;
        this.namePath = this.path + "/" + name;
        this.endpointPath = this.namePath + Constants.ENDPOINT_PATH;
//        this.metaPath = this.namePath + Constants.META_PATH;
        return this.server;
    }


    @Override
    public void register() {
        Server server = this.server;
        //添加根节点
        createNode(Constants.SERVER_PATH, true);
        //添加服务节点，/server/{namePath}
        createNode(this.namePath, true);
        //添加meta节点，/server/{namePath}/meta
        createNode(this.namePath + Constants.META_PATH, true,new Object());
        //添加route节点，/server/{namePath}/{route}
        for (Route route : routes) {
            String routeNodePath = this.namePath+route.nodePath();
//            String routeNodePath = this.namePath+Constants.FORWARD_SLASH +22;
            createNode(routeNodePath, true);
            //添加ip地址节点，/server/{namePath}/{route}/{host}
            createNode(routeNodePath + Constants.FORWARD_SLASH + server.getHost() + ":" + server.getPort(), false);

        }
        //添加endpoint节点，/server/{namePath}/endpoint
//        createNode(this.endpointPath , true);
//        createNode(this.endpointPath + Constants.FORWARD_SLASH + server.getHost() + ":" + server.getPort(), false);
        isRegister = true;
    }

    public void addRoute(Route route){
        this.routes.add(route);
    }

    public void addRoutes(List<Route> routes){
        this.routes.addAll(routes);
    }
    
//    public void setMetadata(Metadata data) {
//        if(isRegister){
////            setNodeData(this.metaPath,new Gson().toJson(data).getBytes());
//        }else{
//            //TODO throw exception
//
//        }
//    }
    
    
}
