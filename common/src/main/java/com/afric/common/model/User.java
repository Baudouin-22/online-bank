package com.afric.common.model;

import com.afric.common.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String id;
    private String username;
    private String password;
    private Set<Role> roles;


}
