//package com.youcode.come2play.security;
//
//import com.youcode.come2play.entities.UserApp;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//public class SecurityUtil {
//    private SecurityUtil(){}
//    public static String getUserEmail(){
//        UserApp authentication =(UserApp) SecurityContextHolder.getContext()
//                .getAuthentication().getPrincipal();
//        if (authentication == null)
//            return null;
//        return authentication.getEmail();
//    }
//}
