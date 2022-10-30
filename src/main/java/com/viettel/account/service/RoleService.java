package com.viettel.account.service;

import com.viettel.account.entity.Role;
import com.viettel.account.service.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllRoles();

    RoleDTO getRoleById(Long id) throws Exception;

    RoleDTO addRole(RoleDTO roleDTO);

    Long deleteRole(Long id) throws Exception;

    void addRole(List<Role> roles);
}
