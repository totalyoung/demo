package netty.server;

import io.netty.handler.codec.http.HttpHeaders;

import java.util.List;
import java.util.Map;

public class StringHttpRequest extends AbstractServerHttpRequest<String>{


    public StringHttpRequest(Map<String, List<String>> parameterMap, String body, HttpHeaders headers, String path) {
        super(parameterMap, body, headers, path);
    }
}
