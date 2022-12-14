/*
 * Copyright 2002-2020 the original author or authors.
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

package org.springframework.core.metrics;

/**
 * Instruments the application startup phase using {@link StartupStep steps}.
 * <p>The core container and its infrastructure components can use the {@code ApplicationStartup}
 * to mark steps during the application startup and collect data about the execution context
 * or their processing time.
 *使用 {@link StartupStep steps} 检测应用程序启动阶段。 <p>核心容器及其基础架构组件可以使用 {@code ApplicationStartup} 来标记应用程序启动期间的步骤，并收集有关执行上下文或其处理时间的数据。
 * @author Brian Clozel
 * @since 5.3
 */
public interface ApplicationStartup {

	/**默认“无操作”{@code ApplicationStartup} 实现。 <p>此变体旨在将开销降至最低，并且不记录数据。
	 * Default "no op" {@code ApplicationStartup} implementation.
	 * <p>This variant is designed for minimal overhead and does not record data.
	 */
	ApplicationStartup DEFAULT = new DefaultApplicationStartup();

	/**
	 * Create a new step and marks its beginning.
	 * <p>A step name describes the current action or phase. This technical
	 * name should be "." namespaced and can be reused to describe other instances of
	 * the same step during application startup.创建一个新步骤并标记它的开始。 <p>步骤名称描述当前操作或阶段。这个技术名称应该是"."命名空间，并且可以在应用程序启动期间重用于描述同一步骤的其他实例。 @param name 步骤名称
	 * @param name the step name
	 */
	StartupStep start(String name);

}
