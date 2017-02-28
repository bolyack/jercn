package com.bamboo.utils.blockchain;

import java.lang.annotation.*;

/**
 * Created by admin on 2017/2/28.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAttr {
    boolean value() default true;
}
