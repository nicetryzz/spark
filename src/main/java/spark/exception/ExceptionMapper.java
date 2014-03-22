/*
 * Copyright 2011- Per Wendel
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package spark.exception;

import java.util.HashMap;
import java.util.Map;

public class ExceptionMapper {
	/**
	 * Holds a default instance for the exception mapper
	 */
	private static ExceptionMapper defaultInstance;

	/**
	 * Returns the default instance for the exception mapper
	 *
	 * @return Default instance
	 */
	public static ExceptionMapper getInstance() {
		if (defaultInstance == null) {
			defaultInstance = new ExceptionMapper();
		}
		return defaultInstance;
	}

	/**
	 * Holds a map of Exception classes and associated handlers
	 */
	private Map<Class<? extends Exception>, ExceptionHandler> exceptionMap;

	/**
	 * Class constructor
	 */
	public ExceptionMapper() {
		this.exceptionMap = new HashMap<>();
	}

	/**
	 * Maps the given handler to the provided exception type. If a handler was already registered to the same type, the
	 * handler is overwritten.
	 *
	 * @param exceptionClass Type of exception
	 * @param handler Handler to map to exception
	 */
	public void map(Class<? extends Exception> exceptionClass, ExceptionHandler handler) {
		this.exceptionMap.put(exceptionClass, handler);
	}

	/**
	 * Returns the handler associated with the provided exception class
	 *
	 * @param exceptionClass Type of exception
	 * @return Associated handler
	 */
	public ExceptionHandler getHandler(Class<? extends Exception> exceptionClass) {
		return this.exceptionMap.get(exceptionClass);
	}

	/**
	 * Returns the handler associated with the provided exception class
	 *
	 * @param exception Exception that occurred
	 * @return Associated handler
	 */
	public ExceptionHandler getHandler(Exception exception) {
		return this.getHandler(exception.getClass());
	}
}