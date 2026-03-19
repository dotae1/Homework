package com.example.homework.member.dto;

import com.example.homework.member.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateRoleRequestDTO {

    private Role role;
}
