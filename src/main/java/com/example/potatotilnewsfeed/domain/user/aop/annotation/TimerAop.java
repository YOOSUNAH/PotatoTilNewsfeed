package com.example.potatotilnewsfeed.domain.user.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {
    @Pointcut("execution(* com.example.potatotilnewsfeed.domain.user.aop.controller..*.*(..))")
    private void cut() {}

    //사용자 지정 어노테이션이 붙은 메서드에도 적용!
    @Pointcut("execution(* com.example.potatotilnewsfeed.domain.user.aop.controller..*.*(..))")
    private void enableTimer() {}

    //메서드 전 후로 시간 측정 시작하고 멈추려면 Before, AfterReturning으로는 시간을 공유 할 수가 없음 Around 사용!
    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        //메서드 시작 전
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //메서드가 실행되는 지점
        Object result = joinPoint.proceed();

        //메서드 종료 후
        stopWatch.stop();

        System.out.println("총 걸린 시간: " + stopWatch.getTotalTimeSeconds());
    }
}
