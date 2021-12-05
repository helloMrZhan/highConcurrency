package com.zjq.concurrency.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target: 注解的作用目标
 *      @Target(ElementType.TYPE) ——接口、类、枚举、注解
 *      @Target(ElementType.FIELD) ——字段、枚举的常量
 *      @Target(ElementType.METHOD) ——方法
 *      @Target(ElementType.PARAMETER) ——方法参数
 *      @Target(ElementType.CONSTRUCTOR) ——构造函数
 *      @Target(ElementType.LOCAL_VARIABLE) ——局部变量
 *      @Target(ElementType.ANNOTATION_TYPE) ——注解
 *      @Target(ElementType.PACKAGE) ——包
 * @Retention 是定义被它所注解的注解保留多久，一共有三种策略，定义在RetentionPolicy枚举中.
 *      source：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；被编译器忽略
 *      class：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期
 *      runtime：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在,我们此处只是用作标记，所以只需要保留在源文件中
 * 用来标记【不推荐】的类或者写法
 * @author zjq
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommend {

    String value() default "";
}
