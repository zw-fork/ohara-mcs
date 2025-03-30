package org.ohara.mcs.server.annotation;

import org.ohara.mcs.server.OHaraMcsServerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(OHaraMcsServerAutoConfiguration.class) // 关键点：直接关联配置类
public @interface EnableOHaraMcsServer {
    boolean enable() default true;
}
