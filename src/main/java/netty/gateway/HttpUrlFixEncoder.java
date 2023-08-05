package netty.gateway;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestEncoder;

public class HttpUrlFixEncoder extends HttpRequestEncoder {

    private Endpoint endpoint;

    public HttpUrlFixEncoder(Endpoint endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    protected void encodeInitialLine(ByteBuf buf, HttpRequest request) throws Exception {
        String uri = request.uri();
        if(uri.isEmpty()) {

        }
        super.encodeInitialLine(buf, request);
    }
}
