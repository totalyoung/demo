package netty.server;

import io.netty.handler.codec.http.HttpHeaders;

import java.util.List;
import java.util.Map;

public interface ServerHttpRequest<T> {

    T body();

    String path();

    HttpHeaders headers();

    Map<String, List<String>> parameterMap();
}
