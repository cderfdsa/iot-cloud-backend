package iot.cloud.backend.webapi.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author weichuang
 */
@Component
@Aspect
@Slf4j
public class AspectForController {
    @Pointcut("execution(* iot.cloud.backend.webapi.modules.*.*Controller.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        log.debug("AOP params = {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
        log.debug("AOP method = {} , return = {} ", joinPoint.getSignature().getName(), ret);
    }
}
