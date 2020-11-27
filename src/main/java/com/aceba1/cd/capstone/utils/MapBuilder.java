package com.aceba1.cd.capstone.utils;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder extends HashMap<String, Object> {

  public MapBuilder() { }

  public MapBuilder(String key, Object value) {
    super.put(key, value);
  }

  public MapBuilder(
    String k1, Object v1,
    String k2, Object v2
  ) {
    super.put(k1, v1);
    super.put(k2, v2);
  }

  public MapBuilder(
    String k1, Object v1,
    String k2, Object v2,
    String k3, Object v3
  ) {
    super.put(k1, v1);
    super.put(k2, v2);
    super.put(k3, v3);
  }

  public static MapBuilder create() {
    return new MapBuilder();
  }

  public static Map<String, Object> one(String key, Object value) {
    Map<String, Object> data = new HashMap<>();
    data.put(key, value);
    return data;
  }

  @Override
  public MapBuilder put(String key, Object value) {
    super.put(key, value);
    return this;
  }
}
