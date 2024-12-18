package kafka.properties;

import java.util.Properties;

public class KafkaProperties {
	/**
	 * Configuration parameter for kafka
	 * @return
	 */
	public static Properties getInstance() {
		Properties prop = new Properties();
		prop.put("bootstrap.servers", "localhost:9092");
		prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		return prop;
	}
}
