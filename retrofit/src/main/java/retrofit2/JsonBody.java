package retrofit2;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.HashMap;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class JSONBody {

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private HashMap<String, Object> map = new HashMap<>();

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");

    private final Gson gson = new Gson();

    public Builder addPart(String key, Object val) {
      map.put(key, val);
      return this;
    }

    public RequestBody build() {
      JsonElement jsonElement = gson.toJsonTree(new Empty());
      JsonObject jsonObject = jsonElement.getAsJsonObject();
      for (String key : map.keySet()) {
        JsonElement jsonElement1 = gson.toJsonTree(map.get(key));
        jsonObject.add(key, jsonElement1);
      }

      String content = gson.toJson(jsonElement);

      return RequestBody.create(MEDIA_TYPE, content);
    }

    public static class Empty {

    }
  }
}
