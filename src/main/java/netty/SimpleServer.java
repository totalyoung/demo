package netty;

/**
 * Created by admin on 2019/7/30.
 */
public class SimpleServer {
    public static void main(String[] args) throws Exception {
//
//        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        ServerBootstrap b = new ServerBootstrap();
//        b.group(bossGroup, workerGroup)
//                .channel(NioServerSocketChannel.class)
//                .option(ChannelOption.SO_REUSEADDR, true)
//                .childHandler(new ChannelInitializer<NioSocketChannel>() {
//                    @Override
//                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        ch.pipeline().addLast(new SimpleServerHandler());
//                    }
//                });
//        b.bind(8090).sync().channel().closeFuture().sync();
    }
}
