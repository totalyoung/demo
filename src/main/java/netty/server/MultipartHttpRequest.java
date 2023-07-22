package netty.server;

import io.netty.handler.codec.http.HttpHeaders;

import java.util.List;
import java.util.Map;

public class MultipartHttpRequest extends AbstractServerHttpRequest<Multipart> {

    public MultipartHttpRequest(Map<String, List<String>> parameterMap, Multipart body, HttpHeaders headers, String path) {
        super(parameterMap, body, headers, path);
    }
}
