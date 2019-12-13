package com.nixo.compiler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//在类级别上才可以使用的注解
@Target(ElementType.TYPE)
//只在源码中存在，字节码不存在
@Retention(RetentionPolicy.SOURCE)
public  @interface AutoParcel {
}
