package com.viettel.account.service.impl;

import com.viettel.account.common.FileWriter;
import com.viettel.account.entity.Account;
import com.viettel.account.entity.Role;
import com.viettel.account.repository.AccountRepository;
import com.viettel.account.repository.RoleRepository;
import com.viettel.account.service.AccountService;
import com.viettel.account.client.MailService;
import com.viettel.account.service.RoleService;
import com.viettel.account.client.StatisticService;
import com.viettel.account.service.dto.AccountDTO;
import com.viettel.account.service.dto.MailDTO;
import com.viettel.account.service.dto.RoleDTO;
import com.viettel.account.service.dto.StatisticDTO;
import com.viettel.account.service.mapper.AccountMapper;
import com.viettel.account.service.mapper.RoleMapper;
import javafx.scene.canvas.GraphicsContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private MailService mailService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        // todo
    }

    @Override
    public List<AccountDTO> getAllAccount() {
        logger.info("Getting all account");
        List<Account> result = accountRepository.findAll();

        // add logs
        final long userId = 1l;
        final String userName = "quanph20";
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setAccountId(userId);
        statisticDTO.setMessage("Get all account by account: " + userId +  "-" + userName
                + " at " + Instant.now().toString());
        statisticDTO.setCreateBy(userName);
        statisticService.addStatistic(statisticDTO);

        return accountMapper.toDtos(result);
    }

    @Override
    public AccountDTO getAccountById(Long id) throws Exception {
        logger.info("Getting account by id:" + id);
        // add logs
        final long userId = 1l;
        final String userName = "quanph20";
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setAccountId(userId);
        statisticDTO.setMessage("View account info by accountId: " + userId + "-" + userName
                + " at " + Instant.now().toString());
        statisticDTO.setCreateBy(userName);
        statisticService.addStatistic(statisticDTO);

        return accountMapper.toDto(accountRepository.findById(id).orElseThrow(() -> new Exception("not found account of id: " + id)));
    }

    @Override
    public Long addAccount(AccountDTO accountDTO) throws Exception {
        logger.info("Adding an account: "+ accountDTO.getName());
        if (Objects.isNull(accountDTO))
            throw new Exception("Cannot update account by a null");
        Account account = accountMapper.toEntity(accountDTO);
        Account res = accountRepository.save(account);
        long id = res.getId();

        List<Role> roles = account.getRoles();
        roles = roles.stream().peek(entity -> entity.getAccount().setId(id)).collect(Collectors.toList());
        roleService.addRole(roles);

        String role = account.getRoles().stream().map(Role::getRole).collect(Collectors.joining(","));

        // add logs
        final long userId = 1l;
        final String userName = "quanph20";
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setAccountId(userId);
        statisticDTO.setMessage("Add a new account:" + accountDTO.getName() + " by account id: " + userId +  "-" + userName
        + " at " + Instant.now().toString());
        statisticDTO.setCreateBy(userName);
        statisticService.addStatistic(statisticDTO);

        MailDTO mailDTO = new MailDTO();
        mailDTO.setFromMail("quanph1998@gmail.com");
        mailDTO.setContent("Added successfully an account name: " + account.getName() + "(" + id + ")" + " with roles:" + role
         + " at " + Instant.now().toString());
        mailDTO.setSubject("Add new account for mycroapp");
        mailDTO.setToMail(accountDTO.getEmail());
        mailDTO.setFromName("Pham Hong Quan AQT");
        mailDTO.setToName(accountDTO.getName());
        mailService.sendMail(mailDTO);

        FileWriter.writeToFile(accountDTO);
        return id;
    }

    @Override
    @Transactional
    public AccountDTO updateAccount(AccountDTO accountDTO) throws Exception {
        logger.info("Updating an account: " + accountDTO.getName());
        if (accountDTO == null)
            throw new Exception("Can not update a null account");
        Account account = accountMapper.toEntity(accountDTO);
        Account savedAccount = accountRepository.save(account);

        long savedId = savedAccount.getId();
        roleRepository.deleteByAccountId(savedId);
        List<RoleDTO> roleDTOs = accountDTO.getRoleDTOS().stream().peek(role -> role.setAccountId(savedId))
                .collect(Collectors.toList());
        List<Role> roles = roleMapper.toEntities(roleDTOs);
        roleRepository.saveAll(roles);

        // add logs
        final long userId = 1l;
        final String userName = "quanph20";
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setAccountId(userId);
        statisticDTO.setMessage("Updated an account:" + accountDTO.getName() + " by account: " + userId +  "-" + userName
                + " at " + Instant.now().toString());
        statisticDTO.setCreateBy(userName);
        statisticService.addStatistic(statisticDTO);
        return accountDTO;
    }

    @Override
    public Long deleteAccount(Long id) throws Exception {
        logger.info("Deleting an account: " + id);
        accountRepository.deleteById(accountRepository.findById(id).map(Account::getId)
                .orElseThrow(() -> new Exception("Can not delete an non-exist account by id: " + id)));
        // add logs
        final long userId = 1l;
        final String userName = "quanph20";
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setAccountId(userId);
        statisticDTO.setMessage("Deleted an accountId:" + id + " by account: " + userId +  "-" + userName
                + " at " + Instant.now().toString());
        statisticDTO.setCreateBy(userName);
        statisticService.addStatistic(statisticDTO);
        return id;
    }
}
