package kz.message_project.userProject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ClientAspect {

    @Around("(execution(* kz.message_project.userProject.client.FileSystemClient.*(..)))")
    public Object logClientMethodsExeption(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodsName = joinPoint.getSignature().getName();
        log.info("Connect to File System client with method: {}", methodsName);
        Object result = null;
        try {
            result = joinPoint.proceed();
            log.info("Method successfully completed");
        } catch (Exception e){
            log.error("Error when try to connect to FileSystem, {}", e.getMessage());
        }
        return result;
    }
}
