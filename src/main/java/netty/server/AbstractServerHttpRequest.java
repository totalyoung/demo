package netty.server;

import io.netty.handler.codec.http.HttpHeaders;

import java.util.List;
import java.util.Map;

public abstract  class AbstractServerHttpRequest<T> implements ServerHttpRequest<T>{

    private  Map<String, List<String>> parameterMap;

    private  T body;

    private  HttpHeaders headers;

    private  String path;

//    public AbstractServerHttpRequest() {
//    }

    protected AbstractServerHttpRequest(Map<String, List<String>> parameterMap, T body, HttpHeaders headers, String path) {
        this.parameterMap = parameterMap;
        this.body = body;
        this.headers = headers;
        this.path = path;
    }

    @Override
    public T body() {
        return body;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public HttpHeaders headers() {
        return headers;
    }

    @Override
    public Map<String, List<String>> parameterMap() {
        return parameterMap;
    }
}
