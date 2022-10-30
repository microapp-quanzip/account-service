package com.viettel.account.service;

import com.viettel.account.service.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    List<AccountDTO> getAllAccount();

    AccountDTO getAccountById(Long id) throws Exception;

    Long addAccount(AccountDTO accountDTO) throws Exception;

    AccountDTO updateAccount(AccountDTO accountDTO) throws Exception;

    Long deleteAccount(Long id) throws Exception;
}
