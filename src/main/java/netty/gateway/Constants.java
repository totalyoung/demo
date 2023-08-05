package netty.gateway;

public class Constants {
    public static final String PATH_SEPARATOR = "/";

    public static final String ZK_HOST = "192.168.117.100:2181";

    public static final String PROXY = "proxy";

    public static final String SERVER = "server";

    public static final String ENDPOINT = "endpoint";

    public static final String CONFIG = "config";

    public static final String META = "meta";

    public static final String PROXY_PATH = PATH_SEPARATOR+PROXY;

    public static final String SERVER_PATH = PATH_SEPARATOR+SERVER;

    public static final String ENDPOINT_PATH = PATH_SEPARATOR+ENDPOINT;

    public static final String CONFIG_PATH = PATH_SEPARATOR+CONFIG;

    public static final String META_PATH = PATH_SEPARATOR+META;

    public static final String LOACL_HOST = "127.0.0.1";

    public static final int SERVER_PORT = 8808;

    public static final int SERVER_CHILD_INDEX = 2;

    public static final int ENDPOINT_CHILD_INDEX = 4;

    public static final int META_CHILD_INDEX = ENDPOINT_CHILD_INDEX;


}
