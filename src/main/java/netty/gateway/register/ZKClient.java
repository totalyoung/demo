package netty.gateway.register;

import com.google.gson.Gson;
import netty.gateway.Constants;
import netty.gateway.listener.Listener;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public abstract class ZKClient implements Client<CuratorFramework> {

    private String hostStr;

    protected CuratorFramework client;

    protected String rootPath = Constants.SERVER_PATH;

    public ZKClient(String hostStr) {
        this.hostStr = hostStr;
        connect();
    }

    @Override
    public void connect(){
        RetryPolicy retryPolicy  = new ExponentialBackoffRetry(1000,3);
        client = CuratorFrameworkFactory.builder()
                .connectString(hostStr)
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace(Constants.PEACH_PREFIX)
                .build();
        client.start();
    }

    @Override
    public void subscribe(Listener listener) {

    }

    @Override
    public void init() {

    }

    public String createNode(String path) {
        return createNode(path, true);
    }

    public String createNode(String path, boolean isPersistent){
        return createNode(path,isPersistent,null);
    }

    public String createNode(String path, boolean isPersistent,Object object){
        try {
            CreateMode createMode  = CreateMode.EPHEMERAL;
            if(isPersistent){
                createMode=CreateMode.PERSISTENT;
            }
            Stat stat = client.checkExists().forPath(path);
            if(stat==null){
                if(object==null){
                    client.create().withMode(createMode).forPath(path);
                }else{
                    client.create().withMode(createMode).forPath(path,new Gson().toJson(object).getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
        return path;
    }

    public void setNodeData(String path,byte[] bytes){
        try {
            Stat stat = client.setData().forPath(path, bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
    }

    public byte[] getNodeData(String path){
        byte[] bytes;
        try {
            bytes = client.getData().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
        return bytes;
    }

    @Override
    public CuratorFramework getClient() {
        return client;
    }
}
