package kz.message_project.userProject.aop;

import kz.message_project.userProject.utils.CustomResponse;
import kz.message_project.userProject.utils.CustomStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static kz.message_project.userProject.utils.CustomStatus.EXCEPTION;

@Component
@Slf4j
@Aspect
public class ServiceAspect {

    @Around("execution(* kz.message_project.userProject.services.*.*(..))")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Начало метода: {}", methodName);

        Object result = null;
        try {
            result = joinPoint.proceed();
            log.info("Успешный конец метода: {}", methodName);
        } catch (Throwable e){
            log.info("ERROR конец метода: {}", methodName);
            log.error(e.getMessage(), e);
            result = new CustomResponse<>(null, EXCEPTION);
        }
        return result;
    }
}
