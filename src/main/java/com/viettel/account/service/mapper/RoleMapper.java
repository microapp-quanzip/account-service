package com.viettel.account.service.mapper;

import com.viettel.account.entity.Account;
import com.viettel.account.entity.Role;
import com.viettel.account.service.dto.AccountDTO;
import com.viettel.account.service.dto.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {})
public interface RoleMapper extends EntityMapper<Role, RoleDTO> {
    @Mapping(source = "accountId", target = "account.id")
    Role toEntity(RoleDTO roleDTO);

    @Mapping(source = "account.id", target = "accountId")
    RoleDTO toDto(Role role);

    default Role fromId(Long id) {
        if (Objects.isNull(id)) {
            return null;
        } else {
            Role role = new Role();
            role.setId(id);
            return role;
        }
    }
}
