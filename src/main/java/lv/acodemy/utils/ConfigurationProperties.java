package lv.workathome.utils;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ConfigurationProperties {
    public ConfigurationProperties() {
    }

    public static PropertiesConfiguration getConfiguration() {
        try {
            return (new Configurations()).properties("configuration.properties");
        } catch (ConfigurationException var1) {
            throw new RuntimeException(var1);
        }
    }
}
