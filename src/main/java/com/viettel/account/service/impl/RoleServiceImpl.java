package com.viettel.account.service.impl;

import com.viettel.account.entity.Role;
import com.viettel.account.repository.RoleRepository;
import com.viettel.account.service.RoleService;
import com.viettel.account.service.dto.RoleDTO;
import com.viettel.account.service.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @PostConstruct
    public void init() {
        // do init value
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleMapper.toDtos(roleRepository.findAll());
    }

    @Override
    public RoleDTO getRoleById(Long id) throws Exception {
        return roleMapper.toDto(roleRepository.findById(id).orElseThrow(() -> new Exception("Can not find Role by id: " + id)));
    }

    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        Role role = roleMapper.toEntity(roleDTO);
        roleRepository.save(role);
        return roleDTO;
    }

    @Override
    public Long deleteRole(Long id) throws Exception {
        roleRepository.deleteById(roleRepository.findById(id).map(Role::getId).orElseThrow(() -> new Exception("Can not find Role by id: " + id)));
        return id;
    }

    @Override
    public void addRole(List<Role> roles) {
        roleRepository.saveAll(roles);
    }
}
