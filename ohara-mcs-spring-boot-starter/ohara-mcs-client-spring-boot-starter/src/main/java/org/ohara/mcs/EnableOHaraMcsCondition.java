package org.ohara.mcs;

import org.ohara.mcs.annotation.EnableOHaraMcsClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class EnableOHaraMcsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            for (String beanName : context.getRegistry().getBeanDefinitionNames()) {
                if (context.getBeanFactory() == null) {
                    continue;
                }

                Object bean = context.getBeanFactory().getBean(beanName);
                Class<?> mainClass = bean.getClass();
                if (mainClass.getAnnotation(SpringBootApplication.class) == null) {
                    continue;
                }

                // 检查主启动类是否有目标注解
                EnableOHaraMcsClient annotation = mainClass.getAnnotation(EnableOHaraMcsClient.class);
                if (annotation != null && annotation.enable()) {
                    return true;
                }
            }
        } catch (Exception ignored) {
        }
        return false;
    }
}