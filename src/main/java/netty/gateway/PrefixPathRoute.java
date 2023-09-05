package netty.gateway;

import netty.common.StringUtil;

import java.util.Objects;

public class PrefixPathRoute extends AbstractRoute{

    private int id = RouteTypeEnum.PREFIX_PATH.getId();

    public PrefixPathRoute(String prefix) {
        super(prefix);
    }

    public PrefixPathRoute(String prefix, int order) {
        super(prefix,order);
    }



    @Override
    public boolean match(Object object) {
        if(!(object instanceof String)) return false;
        String path = (String) object;
        if(! StringUtil.hasLength(path)) return false;
        return path.startsWith(prefix);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrefixPathRoute that = (PrefixPathRoute) o;
        return id == that.id && prefix.equals(that.prefix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,prefix);
    }
}
