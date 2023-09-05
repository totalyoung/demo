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

import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslContext;

import java.math.BigDecimal;

public class ProxyServerInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;

    private final ProviderManager providerManager;

    public ProxyServerInitializer(SslContext sslCtx, ProviderManager providerManager) {
        this.sslCtx = sslCtx;
        this.providerManager = providerManager;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        //设置每次解析的长度
        ChannelPipeline pipeline = ch.pipeline();
        ch.config().setRecvByteBufAllocator(new AdaptiveRecvByteBufAllocator(1,10,10));

        if (sslCtx != null) {
            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
        }
        pipeline.addLast(new HttpRequestDecoder());
//        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new HttpServerKeepAliveHandler());
        pipeline.addLast(new HttpObjectAggregator(512*1024));

        // Remove the following line if you don't want automatic content compression.
//        pipeline.addLast(new HttpContentCompressor());

        pipeline.addLast(new ProxyServerHandler(providerManager));

    }

    public static void main(String[] args) {
//        System.out.println(Float.MAX_VALUE);
//        System.out.println(Double.MAX_VALUE);
//        BigDecimal bigDecimal = new BigDecimal(Float.MAX_VALUE);
//        BigDecimal bigDecimal2 = new BigDecimal(Float.MIN_VALUE);
//        BigDecimal bigDecimal3 = new BigDecimal(Float.MIN_NORMAL);
//        System.out.println(bigDecimal.toPlainString());
//        System.out.println(bigDecimal2.toPlainString());
//        System.out.println(bigDecimal2.toPlainString());
//        System.out.println(1-0.9);
//        BigDecimal bigDecimal4 = new BigDecimal(0.2f);
//        System.out.println(bigDecimal4.toEngineeringString());
//        System.out.println(new Float(0.2f));
//        String string = Integer.toBinaryString(Float.floatToIntBits(Float.MAX_VALUE));
////        System.out.println(string);
////        String string2 = Integer.toBinaryString(Float.floatToIntBits(Float.MIN_VALUE));
////        System.out.println(string2) ;
////        String string1 = Integer.toBinaryString(Float.floatToIntBits(0.5f));
////        System.out.println(string1.length() +" "+string1);
////
//        String string3 = Integer.toBinaryString(Float.floatToIntBits(20.125f));
//        System.out.println(string3.length() +" "+string3);
        String string4 = Integer.toBinaryString(Float.floatToIntBits(0.4f));
        System.out.println(string4.length() +" "+string4);
        String string = Long.toBinaryString(Double.doubleToLongBits(0.4d));
        System.out.println(string.length() +" "+string);
        System.out.println(Integer.parseInt("1111101",2));
//        System.out.println(0.2f>0.2);
//        System.out.println(0.4f>0.4);
//        double v = 0.2f - 0.2;
//        System.out.println(v);
//        System.out.println(0.4f-0.4);
        double d = 0.2;
        double f = 0.2f;
        System.out.println(f);
        System.out.println(new BigDecimal(Float.MAX_VALUE));
        System.out.println(new BigDecimal(f));
//        BigDecimal bigDecimal = new BigDecimal(2);
//        BigDecimal pow = bigDecimal.pow(24);
//        System.out.println(pow);
        test();
//        test2();
    }


    public static void test(){
        float sum = 16777216f;
        float c = 0.0f;
        for (int i = 0; i < 20000000; i++) {
            float x = 1.0f;
            float y = x - c;
            float t = sum + y;
            c = (t-sum)-y;
            sum = t;
        }
        System.out.println("sum is " + sum);
    }

    public static void test2(){
        float sum = 0.0f;
        float c = 0.0f;
        for (int i = 0; i < 20000000; i++) {
            float x = 1.0f;
            sum= sum + x;
        }
        System.out.println("sum is " + sum);
//        Float.intBitsToFloat()
    }
}
