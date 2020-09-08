package org.acme;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.HashMap;
import java.util.Map;

/*
    source => database,XML,RESTApi
 */

public class InMemoryConfigSource implements ConfigSource {

    private Map<String, String> props = new HashMap<>();

    public InMemoryConfigSource() {
        props.put("greeting.color", "red");
    }

    @Override
    public Map<String, String> getProperties() {
        return props;
    }

    // system properties => 400
    // envi properties   => 300


    @Override
    public int getOrdinal() {
        return 500;
    }

    @Override
    public String getName() {
        return "MemoryConfigSource";
    }

    @Override
    public String getValue(String propertyName) {
        return props.get(propertyName);
    }
}
