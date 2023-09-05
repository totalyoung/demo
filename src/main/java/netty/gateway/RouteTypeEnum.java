package netty.gateway;

public enum RouteTypeEnum {

    PREFIX_PATH(1,PrefixPathRoute.class)
    ;
    private int id;

    private Class<? extends Route> type;

    RouteTypeEnum(int id, Class<? extends Route> type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public Class<? extends Route> getType() {
        return type;
    }

    public static RouteTypeEnum getById(int id){
        for (RouteTypeEnum value : values()) {
            if(value.id==id) return value;
        }
        return null;
    }
}
