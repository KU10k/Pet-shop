package com.ku10k.petshop.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, MODER, ADMIN;
    @Override
    public String getAuthority() {
        return name();
    }
}
