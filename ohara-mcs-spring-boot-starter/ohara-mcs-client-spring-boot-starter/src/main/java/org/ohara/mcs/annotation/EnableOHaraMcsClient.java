package org.ohara.mcs.annotation;

import org.ohara.mcs.OHaraMcsClientAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OHaraMcsClientAutoConfiguration.class) // 关键点：直接关联配置类
public @interface EnableOHaraMcsClient {
    boolean enable() default true;
}