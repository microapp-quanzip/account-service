package com.viettel.account.controller;

import com.viettel.account.service.RoleService;
import com.viettel.account.service.dto.RoleDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Api entry for roles", description = "All operations for")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/roles")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok().body(roleService.getAllRoles());
    }

    @GetMapping(value = "role/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) throws Exception {
        RoleDTO roleDTO = roleService.getRoleById(id);
        if (roleDTO == null) {
            throw new Exception("Role not found by id");
        }
        return ResponseEntity.ok().body(roleDTO);
    }

    @PostMapping(value = "/role")
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO roleDTO) throws Exception {
        if (roleDTO == null) {
            throw new Exception("Cannot save null role!");
        }
        RoleDTO roleDTO1 = roleService.addRole(roleDTO);
        return ResponseEntity.ok().body(roleDTO1);
    }

    @DeleteMapping(value = "/role/{id}")
    public Long deleteRoleById(@PathVariable Long id) throws Exception {
        RoleDTO roleDTO = roleService.getRoleById(id);
        if (roleDTO == null) {
            throw new Exception("Role not found by id");
        }
        roleService.deleteRole(id);
        return id;
    }
}
