package com.aceba1.cd.capstone.utils;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder {

  private final Map<String, Object> data = new HashMap<>();

  private MapBuilder() { }

  public static MapBuilder create() {
    return new MapBuilder();
  }

  public MapBuilder put(String key, Object value) {
    data.put(key, value);
    return this;
  }

  public Map<String, Object> done() {
    return data;
  }
}
