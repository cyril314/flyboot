package com.fit.core.util;

import com.fit.config.properties.FitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 验证码工具类
 */
@Component("KaptchaUtil")
public class KaptchaUtil {

    /**
     * 获取验证码开关
     */
    @ConditionalOnProperty(prefix = "fit", name = "kaptcha-open", havingValue = "false")
    public static Boolean getKaptchaOnOff() {
        return SpringContextHolder.getBean(FitProperties.class).getKaptchaOpen();
    }
}