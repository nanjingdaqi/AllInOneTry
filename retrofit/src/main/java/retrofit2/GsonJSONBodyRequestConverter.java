package retrofit2;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GsonJSONBodyRequestConverter implements Converter<Map<String, Object>, RequestBody> {
  private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

  private final Gson gson = new Gson();

  @Override public RequestBody convert(Map<String, Object> map) throws IOException {
    if (map == null || map.size() == 0) {
      return RequestBody.create(MEDIA_TYPE, new byte[0]);
    }

    JsonElement jsonElement = gson.toJsonTree(new Empty());
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    for (String key : map.keySet()) {
      JsonElement jsonElement1 = gson.toJsonTree(map.get(key));
      jsonObject.add(key, jsonElement1);
    }

    String content = gson.toJson(jsonElement);

    return RequestBody.create(MEDIA_TYPE, content);
  }

  private static class Empty {

  }
}
