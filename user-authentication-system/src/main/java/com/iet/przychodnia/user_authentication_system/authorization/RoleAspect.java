package com.iet.przychodnia.user_authentication_system.authorization;

import com.iet.przychodnia.user_authentication_system.jwt.JwtValidator;
import lombok.val;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.iet.przychodnia.user_authentication_system.Globals.AUTHORIZATION_HEADER;


@Aspect
@Component
public class RoleAspect {

    @Pointcut("within(@com.iet.przychodnia.user_authentication_system.authorization.Role *)")
    public void classWithRole() {
    }

    @Pointcut("execution(@com.iet.przychodnia.user_authentication_system.authorization.Role * *.*(..))")
    public void methodWithRole() {
    }

    @Pointcut("execution(public * * (..))")
    public void publicMethod() {
    }

    @Before("classWithRole() && publicMethod()")
    public void authorizeClass(JoinPoint jp) {
        val role = jp.getTarget().getClass().getAnnotation(Role.class);
        val methodAnnotations = Arrays.asList(((MethodSignature) jp.getSignature()).getMethod().getDeclaredAnnotations());

        if (containsRole(methodAnnotations)) {
            val methodRole = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(Role.class);
            val combinedRoles = Stream.concat(Arrays.stream(role.value()), Arrays.stream(methodRole.value()))
                    .toArray(AuthorizationRole[]::new);

            authorize(combinedRoles);
        } else {
            authorize(role.value());
        }
    }

    @Before("methodWithRole()")
    public void authorizeMethod(JoinPoint jp) {
        val role = ((MethodSignature) jp.getSignature()).getMethod().getAnnotation(Role.class);

        authorize(role.value());
    }

    private void authorize(AuthorizationRole[] roles) {
        val request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        val authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

        JwtValidator.validateWithRoles(authorizationHeader, Arrays.asList(roles));
    }

    private boolean containsRole(List<Annotation> annotations) {
        for (Annotation annotation: annotations) {
            if (annotation instanceof Role) {
                return true;
            }
        }

        return false;
    }

}
