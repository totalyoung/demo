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

public class Endpoint {

    private String host;

    private int port;

    private Channel clientChanel;

    private Channel remoteChannel;

    public Endpoint(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Endpoint(String hostStr){
        String[] split = hostStr.split(":");
        this.host=split[0];
        this.port=Integer.valueOf(split[1]);
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

}
