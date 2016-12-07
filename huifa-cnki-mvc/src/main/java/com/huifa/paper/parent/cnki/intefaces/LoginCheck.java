package com.huifa.paper.parent.cnki.intefaces;

import java.lang.annotation.*;

/**
 * 用户登录注解
 * Created by kchen on 2016/9/12.
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCheck {
}