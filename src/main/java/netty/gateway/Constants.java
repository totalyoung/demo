package netty.gateway;

public interface Constants {
    String FORWARD_SLASH = "/";

    String ZK_HOST = "192.168.117.100:2181";

    String PROXY = "proxy";

    String PROVIDER = "provider";

    String ENDPOINT = "endpoint";

    String CONFIG = "config";

    String META = "meta";

    String PROXY_PATH = FORWARD_SLASH + PROXY;

    String SERVER_PATH = FORWARD_SLASH + PROVIDER;

    String ENDPOINT_PATH = FORWARD_SLASH + ENDPOINT;

    String CONFIG_PATH = FORWARD_SLASH + CONFIG;

    String META_PATH = FORWARD_SLASH + META;

    String LOACL_HOST = "127.0.0.1";

    int SERVER_PORT = 8808;

    int SERVER_CHILD_INDEX = 2;

    int ENDPOINT_CHILD_INDEX = 4;

    int META_CHILD_INDEX = ENDPOINT_CHILD_INDEX;

    int HOST_CHILD_INDEX = 5;

    int ROUTE_CHILD_INDEX = 3;

    int PROVIDER_CHILD_INDEX = 1;

    String AMPERSAND = "&";

    String PEACH_PREFIX = "peach";

    String DEFAULT = "default";

}
