package netty.gateway;

import com.google.gson.*;

import java.lang.reflect.Type;

public class RouteJsonDeserializer implements JsonDeserializer<Route> {

    @Override
    public Route deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jo = json.getAsJsonObject();
        int id = jo.get("id").getAsInt();
        Class<? extends Route> type = RouteTypeEnum.getById(id).getType();
        return new Gson().fromJson(json,type);
    }
}
