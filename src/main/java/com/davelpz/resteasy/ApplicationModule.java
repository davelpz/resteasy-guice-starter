package com.davelpz.resteasy;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApplicationModule {
    String value() default "";
}
