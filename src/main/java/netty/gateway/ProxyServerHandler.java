/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package netty.gateway;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.netty.buffer.Unpooled.copiedBuffer;

public class ProxyServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger logger = Logger.getLogger(ProxyServerHandler.class.getName());

    private HttpRequest request;

    private final StringBuilder responseContent = new StringBuilder();

    private HttpPostRequestDecoder decoder;

    ExecutorService executorService = new ThreadPoolExecutor(1, 12, 1, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    private ProviderManager providerManager;

    private Endpoint endpoint;

    public ProxyServerHandler(ProviderManager providerManager) {
//        super();
        this.providerManager = providerManager;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        if (decoder != null) {
//            decoder.cleanFiles();
//        }
        System.out.println("连接断开"+"id:"+ctx.channel().id());
        if(endpoint != null){
            endpoint.remoteClose();
        }
//        super.channelInactive(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("连接:"+System.currentTimeMillis()+"id:"+ctx.channel().id());
//        super.channelActive(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        this.request = msg;
        URI uri = new URI(msg.uri());
        String path = uri.getPath();
        String[] split = path.split("/");
        String s = split[1];
        //TODO 路由处理
        //TODO 选择endpoint
        Provider provider = providerManager.getProvider(s);
        if (provider == null) {
            writeResponse(ctx.channel(),true);
            return;
        }
        //
        msg.retain();

        Channel serverChannel = endpoint.connect(ctx.channel());
//        DefaultFullHttpRequest defaultFullHttpRequest =  new DefaultFullHttpRequest(msg.protocolVersion(),msg.method(),msg.uri(), Unpooled.EMPTY_BUFFER,msg.headers(),msg.trailingHeaders());
        ChannelFuture channelFuture = serverChannel.writeAndFlush(msg);

        boolean success = channelFuture.isSuccess();
        System.out.println();
    }




    private void reset() {
        request = null;

        // destroy the decoder to release all resources
        decoder.destroy();
        decoder = null;
    }

    private void writeResponse(Channel channel) {
        writeResponse(channel, false);
    }

    private void writeResponse(Channel channel, boolean forceClose) {
        responseContent.setLength(0);

//        // new getMethod
//        Set<Cookie> cookies;
//        String value = request.headers().get(HttpHeaderNames.COOKIE);
//        if (value == null) {
//            cookies = Collections.emptySet();
//        } else {
//            cookies = ServerCookieDecoder.STRICT.decode(value);
//        }
//        for (Cookie cookie : cookies) {
//            responseContent.append("COOKIE: " + cookie + "\r\n");
//        }
//        responseContent.append("\r\n\r\n");
        responseContent.append("It is oK！！！\r\n\r\n");
        // Convert the response content to a ChannelBuffer.
        ByteBuf buf = copiedBuffer(responseContent.toString(), CharsetUtil.UTF_8);



//        responseContent.append("\r\n\r\nEND OF GET CONTENT\r\n");
        // Decide whether to close the connection or not.
        boolean keepAlive = HttpUtil.isKeepAlive(request) && !forceClose;

        // Build the response object.
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        response.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, buf.readableBytes());

        if (!keepAlive) {
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
        } else if (request.protocolVersion().equals(HttpVersion.HTTP_1_0)) {
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

//        if (!cookies.isEmpty()) {
//            // Reset the cookies if necessary.
//            for (Cookie cookie : cookies) {
//                response.headers().add(HttpHeaderNames.SET_COOKIE, ServerCookieEncoder.STRICT.encode(cookie));
//            }
//        }
        // Write the response.
        ChannelFuture future = channel.writeAndFlush(response);
        // Close the connection after the write operation is done if necessary.
        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.log(Level.WARNING, responseContent.toString(), cause);
        ctx.channel().close();
    }


}
