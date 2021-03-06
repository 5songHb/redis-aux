package com.xl.redisaux.limiter.autoconfigure.normal;

import com.xl.redisaux.common.consts.LimiterConstants;
import com.xl.redisaux.limiter.annonations.EnableLimiter;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author: lele
 * @date: 2020/1/2 下午4:10
 */
@SuppressWarnings("unchecked")
public class RedisLimiterRegistar implements ImportBeanDefinitionRegistrar {
    public static boolean delayConnect=true;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attributes = importingClassMetadata
                .getAnnotationAttributes(EnableLimiter.class.getCanonicalName());
        //如果开启限流，则扫描组件、初始化对应的限流器和切面
        ClassPathBeanDefinitionScanner scanConfigure =
                new ClassPathBeanDefinitionScanner(registry, true);
        String path=LimiterConstants.SCAPATH;

        if(!(Boolean) attributes.get("enableGroup")){
            path=path+".normal";
            delayConnect=(Boolean)attributes.get("delayConnect");
        }
        scanConfigure.scan(path);
    }

}