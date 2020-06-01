package me.redbean88.standAlone.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  //파라미너 제한
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
