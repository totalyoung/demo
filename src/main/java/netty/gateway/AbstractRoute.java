package netty.gateway;

import com.google.gson.Gson;
import netty.common.StringUtil;
import netty.gateway.register.PathResolver;

public abstract class AbstractRoute implements Route{

    protected int order = Integer.MIN_VALUE;

    protected String prefix;


    public AbstractRoute(String prefix) {
        this.prefix = format(prefix);
    }

    public AbstractRoute(String prefix, int order) {
        this.order = order;
        this.prefix = format(prefix);
    }

    @Override
    public int compareTo(Route r) {
        return r.getOrder() > this.getOrder() ? 1 : -1;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public String replace(String path) {
        return path.replaceFirst(prefix, "");
    }

    public String format(String str){
        if(StringUtil.hasLength(str) && !str.startsWith(Constants.FORWARD_SLASH)){
            return Constants.FORWARD_SLASH+str;
        }
        return str;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public String nodePath(){
        return format(PathResolver.encodeNodePath(toString()));
    }
}
