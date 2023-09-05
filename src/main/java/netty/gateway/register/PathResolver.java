package netty.gateway.register;

import netty.common.StringUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static netty.gateway.Constants.*;

public class PathResolver {

    private String path;

    private String[] splits;

    private int length;

    private byte[] data;
    
    private final static String IP_REGEX = "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(:([0-9]|[1-9]\\d|[1-9]\\d{2}|[1-9]\\d{3}|[1-5]\\d{4}|6[0-4]\\d{2}|655[0-2]\\d|6553[0-5])$)";

    public PathResolver(String path) {
        this.path = path;
        String[] split = this.path.split(FORWARD_SLASH);
        for (int i = 0; i < split.length; i++) {
            if(StringUtil.hasLength(split[i])){
                split[i] =  decodeNodePath(split[i]);
            }
        }
        this.splits = split;
        this.length = splits.length;
    }

    public PathResolver(String path, byte[] data) {
        this(path);
        this.data = data;
    }

    public boolean isMetaPath() {
        return lastPath().equals(META);
    }

    public boolean isProviderPath() {
        return lastPath().equals(PROVIDER);
    }

    public boolean isProxyPath() {
        return lastPath().equals(PROXY);
    }

    public boolean isEndpointPath() {
        return lastPath().equals(ENDPOINT);
    }

    public boolean isServerPath() {
        return length== 3 && getSplit(PROVIDER_CHILD_INDEX).equals(PROVIDER);
    }

    public boolean isRoutePath(){
        return length== 4 && !isEndpointPath();
    }
    
    public boolean isHostPath(){
        return lastPath().matches(IP_REGEX);
    }


    public String lastPath() {
        return splits[length - 1];
    }

    public String getSplit(int index) {
        if (length <= index) {
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

    public String getProviderSplit() {
        return getSplit(PROVIDER_CHILD_INDEX);
    }


    public String getHostSplit(){
        return getSplit(HOST_CHILD_INDEX);
    }

    public String getRouteSplit(){
        return getSplit(ROUTE_CHILD_INDEX);
    }


    public byte[] getData() {
        return data;
    }

    public static String encodeNodePath(String nodePath){
        try {
            return URLEncoder.encode(nodePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decodeNodePath(String nodePath){
        try {
            return URLDecoder.decode(nodePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public int getLength(){
        return length;
    }
}
