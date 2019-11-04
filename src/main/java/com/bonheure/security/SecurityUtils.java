package com.bonheure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.bonheure.domain.User;
import com.bonheure.execption.CustomException;
import com.bonheure.repository.PrestataireRepository;
import com.bonheure.repository.UserRepository;

public class SecurityUtils {

    private static UserRepository userRepository;

    @Autowired
    public SecurityUtils(UserRepository userRepository) {
        SecurityUtils.userRepository = userRepository;
    }

    static boolean isEmail(String email) {

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public static boolean checkIfThereIsUserLogged() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }

    public static User getCurrentUser() {

        if (!SecurityUtils.checkIfThereIsUserLogged())
            throw new CustomException("There is already a logged user", HttpStatus.UNPROCESSABLE_ENTITY);

        if (isEmail(SecurityUtils.getCurrentUserLogin())) {
            return userRepository.findOneByEmail(SecurityUtils.getCurrentUserLogin())
                    .orElseThrow(() -> new CustomException("Email does not exist", HttpStatus.UNPROCESSABLE_ENTITY));
        } else {
            return userRepository.findOneByMobileNumber(SecurityUtils.getCurrentUserLogin())
                    .orElseThrow(() -> new CustomException("Email does not exist", HttpStatus.UNPROCESSABLE_ENTITY));
        }

    }

    public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails) {
                UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }

}