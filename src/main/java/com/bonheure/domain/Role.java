package com.bonheure.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public enum Role {
//		implements GrantedAuthority {

    SUPERADMIN, CLIENT, PRESTATAIRE;

    @JsonCreator
    public static Role fromString(String roleName) {
        if (StringUtils.isEmpty(roleName))
            return CLIENT;
        return Arrays.stream(values()).filter(role -> role.toString().equalsIgnoreCase(roleName)).findFirst()
                .orElse(CLIENT);
    }

}
