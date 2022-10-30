package com.viettel.account.service.mapper;

import com.viettel.account.entity.Account;
import com.viettel.account.service.dto.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface AccountMapper extends EntityMapper<Account, AccountDTO> {
    @Mapping(source = "roleDTOS", target = "roles")
    Account toEntity(AccountDTO accountDTO);


    @Mapping(source = "roles", target = "roleDTOS")
    AccountDTO toDto(Account account);

    default Account fromId(Long id) {
        Account account = new Account();
        account.setId(id);
        return account;
    }
}
