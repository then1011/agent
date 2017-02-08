package com.agent.base.proxy.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MainService {
	
	/**
	 * 组件唯一标识
	 */
	String id();
	
	/**
	 * 判断是否需要登录
	 */
	boolean needLogin() default false;
}
