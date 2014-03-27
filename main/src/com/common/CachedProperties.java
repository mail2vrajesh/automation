package com.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CachedProperties {

	private static CachedProperties cachedProperties;
	private static CachedProperties dataProperties;
	

	// currently not capable of handling multithreaded scenarios.
	public static CachedProperties instance() {
		if (cachedProperties == null) {
			try {
				cachedProperties = new CachedProperties();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cachedProperties;
	}

	public static CachedProperties dataInstance() {
        if ( dataProperties == null ) {
            try {
            	dataProperties = new CachedProperties("data.properties");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataProperties;
    }

	// currently not capable of handling multithreaded scenarios.
	public static CachedProperties instance(InputStream stream) {
		if (cachedProperties == null) {
			try {
				cachedProperties = new CachedProperties(stream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return cachedProperties;
	}

	
	private Properties properties;
	public static void main(String[] args) throws IOException {
		CachedProperties properties = new CachedProperties();
	}



	private CachedProperties() throws IOException {
		// Bad way of doing things in constructor but since we do not use any
		// injection
		// and we do not care about testing it should be fine. <Nilesh>
		properties = new Properties();
		InputStream iStream = this.getClass().getClassLoader()
				.getResourceAsStream("custom.properties");
		properties.load(iStream);
	
	}

	private CachedProperties(String resource) throws IOException {
		// Bad way of doing things in constructor but since we do not use any
		// injection
		// and we do not care about testing it should be fine. <Nilesh>
		properties = new Properties();
		InputStream iStream = this.getClass().getClassLoader()
				.getResourceAsStream(resource);
		properties.load(iStream);
	}

	private CachedProperties(InputStream stream) throws IOException {
		properties = new Properties();
		properties.load(stream);

	}

	
	public String value(String key) {
		return properties.getProperty(key);
	}
}