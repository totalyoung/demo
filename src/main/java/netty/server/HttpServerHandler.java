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
package netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.netty.buffer.Unpooled.copiedBuffer;

public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private static final Logger logger = Logger.getLogger(HttpServerHandler.class.getName());

    private HttpRequest request;

    private final StringBuilder responseContent = new StringBuilder();

    private StringHttpRequest completeHttpRequest;

    private static final HttpDataFactory factory =
            new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE); // Disk if size exceed

    private HttpPostRequestDecoder decoder;


    ExecutorService executorService = new ThreadPoolExecutor(1, 12, 1, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        if (decoder != null) {
//            decoder.cleanFiles();
//        }
        System.out.println("连接断开"+"id:"+ctx.channel().id());
//        super.channelInactive(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("连接:"+System.currentTimeMillis()+"id:"+ctx.channel().id());
//        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("注册:"+System.currentTimeMillis()+"id:"+ctx.channel().id());
        super.channelRegistered(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        this.request = msg;
        Map<String, List<String>> parameters = new LinkedHashMap<>();
        URI uri = new URI(msg.uri());
        String path = uri.getPath();
        //解析param
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri.toString());
        Map<String, List<String>>  queryParameters = queryStringDecoder.parameters();
        parameters.putAll(queryParameters);

        //解析header
        HttpHeaders headers = msg.headers();
        String contentType = headers.get(HttpHeaders.Names.CONTENT_TYPE).trim();
        String bodyString="";
        if(isStringType(contentType)){
            ByteBuf content = msg.content();
            byte[] bytes = new byte[content.readableBytes()];
            content.readBytes(bytes);
            bodyString = new String(bytes);
            QueryStringDecoder formDataDecoder = new QueryStringDecoder(bodyString,false);
            parameters.putAll(formDataDecoder.parameters());
        }
        StringHttpRequest completeHttpRequest = new StringHttpRequest(parameters, bodyString, msg.headers(),path);
        HandlerTask handlerTask = new HandlerTask(completeHttpRequest);
        Future<HandlerResult> submit = executorService.submit(handlerTask);
        HandlerResult handlerResult = submit.get();
        //处理结果集
        Object result = handlerResult.result();
        ByteBuf byteBuf = Unpooled.copiedBuffer(result.toString(), CharsetUtil.UTF_8);
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(msg.protocolVersion(), HttpResponseStatus.OK, byteBuf);

        //合并响应头
        HttpHeaders resultHeader = handlerResult.headers();
        response.headers().add(resultHeader);

        // 解析attr
        // 解析文件
        writeResponse(ctx.channel());
    }

    public boolean isStringType(String contentType){
        if(HttpHeaders.Values.APPLICATION_X_WWW_FORM_URLENCODED.equals(contentType)){
            return true;
        }
        return false;
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
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
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
