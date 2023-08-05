package netty.gateway.register;

import static netty.gateway.Constants.*;

public class PathResolver {

    private String path;

    private String[] splits;

    private int length;

    private byte[] data;

    public PathResolver(String path) {
        this.path = path;
        this.splits = path.split(PATH_SEPARATOR);
        this.length = splits.length;
    }

    public PathResolver(String path, byte[] data) {
        this(path);
        this.data = data;
    }

    public boolean isMetaPath() {
        return lastPath().equals(META);
    }

    public boolean isServerPath() {
        return lastPath().equals(SERVER);
    }

    public boolean isProxyPath() {
        return lastPath().equals(PROXY);
    }

    public boolean isEndpointPath() {
        return lastPath().equals(ENDPOINT);
    }

    public boolean isProviderPath() {
        return getSplit(SERVER_CHILD_INDEX).equals(SERVER) && getSplit(ENDPOINT_CHILD_INDEX).equals(ENDPOINT);
    }


    public String lastPath() {
        return splits[length - 1];
    }

    public String getSplit(int index) {
        if (length < index) {
            return null;
        }
        return splits[index];
    }

    public String getServerSplit() {
        return getSplit(SERVER_CHILD_INDEX);
    }

    public String getEndpointSplit() {
        return getSplit(ENDPOINT_CHILD_INDEX);
    }

    public byte[] getData() {
        return data;
    }
}
