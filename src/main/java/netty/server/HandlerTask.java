package netty.server;

import io.netty.handler.codec.http.DefaultHttpHeaders;

import java.util.concurrent.Callable;

public class HandlerTask implements Callable<HandlerResult> {

    private final StringHttpRequest request;

    public HandlerTask(StringHttpRequest request) {
        this.request = request;
    }

    @Override
    public HandlerResult call() throws Exception {
        return new HandlerResult(new DefaultHttpHeaders(),"aaa");
    }
}
