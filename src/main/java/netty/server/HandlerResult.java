package netty.server;

import io.netty.handler.codec.http.HttpHeaders;

public class HandlerResult {

    private HttpHeaders headers;

    private Object result;

    public HandlerResult(HttpHeaders headers, Object result) {
        this.headers = headers;
        this.result = result;
    }

    public Object result(){
        return result;
    }

    public HttpHeaders headers(){
        return headers;
    }


}
