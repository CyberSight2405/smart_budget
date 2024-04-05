package kz.message_project.userProject.aop;

import jakarta.servlet.http.HttpServletRequest;
import kz.message_project.userProject.utils.LogUtils;
import kz.message_project.userProject.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Component
@Slf4j
@Aspect
public class ControllerAspect {
    @Around("(execution(* kz.message_project.userProject.controller.*.*(..))) ")
    public Object logControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        var path = request.getRequestURI();
        var clientIP = request.getRemoteAddr();
        var methodName = joinPoint.getSignature().getName();
        var operationStart = LocalDateTime.now();

        try {

            Object result = joinPoint.proceed();
            var currentUser = UserUtils.getCurrentUsername();

            var operationEnd = LocalDateTime.now();

            log.info(
                    LogUtils.createLog(currentUser, clientIP, path, methodName, operationStart, operationEnd)
            );
            return result;
        }catch (Exception e) {
            var currentUser = UserUtils.getCurrentUsername();
            var operationEnd = LocalDateTime.now();
            var errorMessage = e.getMessage();
            log.error(
                    LogUtils.createLog(currentUser, clientIP, path, methodName, operationStart, operationEnd, errorMessage)
            );
            throw e;
        }
    }
}
