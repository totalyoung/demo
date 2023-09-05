package netty.gateway;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestEncoder;

import java.util.Objects;

public class Endpoint extends Host{

    private Channel clientChanel;

    private Channel remoteChannel;

//    private EndpointCluster cluster;

    //TODO 健康检查

    public Endpoint(String host, int port) {
       super(host,port);
    }

    public Endpoint(String hostStr){
        super(hostStr);
    }

    public Channel connect(Channel clientChanel){
        Bootstrap b = new Bootstrap();
        b.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>(){
                             @Override
                             protected void initChannel(SocketChannel ch) throws Exception {
                                 ch.pipeline().addLast(new HttpRequestEncoder());
                                 ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                                     @Override
                                     public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                         System.out.println(msg);
                                         clientChanel.writeAndFlush(msg);
                                     }
                                 });
                             }
                         }
                );

        try {
            this.remoteChannel = b.connect(host, port).sync().channel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.clientChanel=clientChanel;
        return remoteChannel;
    }

    public void remoteClose(){
        if(remoteChannel.isActive()) remoteChannel.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endpoint endpoint = (Endpoint) o;
        return port == endpoint.port &&
                Objects.equals(host, endpoint.host);
    }


    @Override
    public String toString() {
        return "Endpoint{" +
                "host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}
