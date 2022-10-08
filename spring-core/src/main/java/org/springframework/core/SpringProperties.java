/*
 * Copyright 2002-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.springframework.lang.Nullable;

/**
 * Static holder for local Spring properties, i.e. defined at the Spring library level.
 *
 * <p>Reads a {@code spring.properties} file from the root of the Spring library classpath,
 * and also allows for programmatically setting properties through {@link #setProperty}.
 * When checking a property, local entries are being checked first, then falling back
 * to JVM-level system properties through a {@link System#getProperty} check.
 *
 * <p>This is an alternative way to set Spring-related system properties such as
 * "spring.getenv.ignore" and "spring.beaninfo.ignore", in particular for scenarios
 * where JVM system properties are locked on the target platform (e.g. WebSphere).
 * See {@link #setFlag} for a convenient way to locally set such flags to "true".
 *
 * @author Juergen Hoeller
 * @since 3.2.7
 * @see org.springframework.beans.CachedIntrospectionResults#IGNORE_BEANINFO_PROPERTY_NAME
 * @see org.springframework.context.index.CandidateComponentsIndexLoader#IGNORE_INDEX
 * @see org.springframework.core.env.AbstractEnvironment#IGNORE_GETENV_PROPERTY_NAME
 * @see org.springframework.expression.spel.SpelParserConfiguration#SPRING_EXPRESSION_COMPILER_MODE_PROPERTY_NAME
 * @see org.springframework.jdbc.core.StatementCreatorUtils#IGNORE_GETPARAMETERTYPE_PROPERTY_NAME
 * @see org.springframework.jndi.JndiLocatorDelegate#IGNORE_JNDI_PROPERTY_NAME
 * @see org.springframework.objenesis.SpringObjenesis#IGNORE_OBJENESIS_PROPERTY_NAME
 * @see org.springframework.test.context.NestedTestConfiguration#ENCLOSING_CONFIGURATION_PROPERTY_NAME
 * @see org.springframework.test.context.TestConstructor#TEST_CONSTRUCTOR_AUTOWIRE_MODE_PROPERTY_NAME
 * @see org.springframework.test.context.cache.ContextCache#MAX_CONTEXT_CACHE_SIZE_PROPERTY_NAME
 */
public final class SpringProperties {

	private static final String PROPERTIES_RESOURCE_LOCATION = "spring.properties";

	private static final Properties localProperties = new Properties();


	static {
		try {
			ClassLoader cl = SpringProperties.class.getClassLoader();
			URL url = (cl != null ? cl.getResource(PROPERTIES_RESOURCE_LOCATION) :
					ClassLoader.getSystemResource(PROPERTIES_RESOURCE_LOCATION));//如果classloader不为空则从该classloader获取资源否则通过静态方法获取系统的资源（获取名称为spring.properties的资源）
			if (url != null) {//如果资源不为空则将其中的值读取到localProperties
				try (InputStream is = url.openStream()) {
					localProperties.load(is);
				}
			}
		}
		catch (IOException ex) {
			System.err.println("Could not load 'spring.properties' file from local classpath: " + ex);
		}
	}


	private SpringProperties() {//单例模式
	}


	/**
	 * 以编程方式设置本地属性，覆盖 {@code spring.properties} 文件中的条目（如果有）
	 * {@code spring.properties} file (if any).
	 * @param key the property key
	 * @param value the associated property value, or {@code null} to reset it
	 */
	public static void setProperty(String key, @Nullable String value) {//设置property，如果value为空则代表删除该值
		if (value != null) {
			localProperties.setProperty(key, value);
		}
		else {
			localProperties.remove(key);
		}
	}

	/**
	 * 检索给定键的属性值，首先检查本地 Spring 属性并回退到 JVM 级别的系统属性。
	 * properties first and falling back to JVM-level system properties.
	 * @param key the property key
	 * @return the associated property value, or {@code null} if none found
	 */
	@Nullable
	public static String getProperty(String key) {
		String value = localProperties.getProperty(key);
		if (value == null) {//如果localProperties中没有则从系统中获取
			try {
				value = System.getProperty(key);
			}
			catch (Throwable ex) {
				System.err.println("Could not retrieve system property '" + key + "': " + ex);
			}
		}
		return value;
	}

	/**
	 * 以编程方式将本地标志设置为“true”，覆盖 {@code spring.properties} 文件（如果有）中的条目
	 * entry in the {@code spring.properties} file (if any).
	 * @param key the property key
	 */
	public static void setFlag(String key) {//设置一个flag，默认为true
		localProperties.put(key, Boolean.TRUE.toString());
	}

	/**
	 * 检索给定属性键的标志
	 * @param key the property key
	 * @return {@code true} if the property is set to "true",
	 * {@code} false otherwise
	 */
	public static boolean getFlag(String key) {
		return Boolean.parseBoolean(getProperty(key));
	}

}
