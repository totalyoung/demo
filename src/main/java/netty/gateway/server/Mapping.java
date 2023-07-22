package netty.gateway.server;

import io.netty.handler.codec.http.HttpMethod;

public class Mapping {

    private String path;

    private HttpMethod method;

    public Mapping(String path, HttpMethod method) {
        this.path = path;
        this.method = method;
    }

    public String path(){
        return this.path;
    }

}
