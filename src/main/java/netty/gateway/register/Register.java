package netty.gateway.register;

import netty.gateway.Constants;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import static netty.gateway.Constants.ZK_HOST;

public class Register {

    private String hostStr;

    protected CuratorFramework client;

    protected String serverPath = Constants.SERVER_PATH;

    public Register(String hostStr) {
        this.hostStr = hostStr;
        connect();
    }


    public void connect(){
        RetryPolicy retryPolicy  = new ExponentialBackoffRetry(1000,3);
        client = CuratorFrameworkFactory.builder()
                .connectString(hostStr)
                .sessionTimeoutMs(3000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .namespace("peach")
                .build();
        client.start();
    }

    public void register(){

    }

    public void subscribe(TreeCacheListener listener){}


    public String createNode(String path) {
        return createNode(path, true);
    }

    public String createNode(String path, boolean isPersistent){
        try {
            CreateMode createMode  = CreateMode.EPHEMERAL;
            if(isPersistent){
                createMode=CreateMode.PERSISTENT;
            }
            Stat stat = client.checkExists().forPath(path);
            if(stat==null){
                client.create().withMode(createMode).forPath(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getCause());
        }
        return path;
    }

    public CuratorFramework client(){
        return client;
    }

    //-Djava.net.preferIPv4Stack=true，解决默认使用IP6 InetAddress.getHostName() 阻塞问题
    public static void main(String[] args) {
        Register register = new Register(ZK_HOST);
        register.connect();
        System.out.println();
    }


}
