package com.viettel.account.controller;

import com.viettel.account.service.AccountService;
import com.viettel.account.service.dto.AccountDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "")
@Tag(name = "API entry for accounts", description = "Containing all operations for accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize(value = "#oauth2.hasScope('read') && hasRole('ADMIN')")
    @GetMapping(value = "/accounts")
    @Operation(description = "Get All accounts that available", responses = {@ApiResponse(responseCode = "200", description = "Get all accounts successfully")
            , @ApiResponse(responseCode = "403", description = "Not authorized")})
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        return ResponseEntity.ok().body(accountService.getAllAccount());
    }

    @PreAuthorize(value = "#oauth2.hasScope('read') && isAuthenticated()")
    @PostAuthorize(value = "returnObject.body.username == authentication.name || hasRole('ADMIN')")
    @GetMapping(value = "/account/{id}")
    @Operation(description = "Get account that by id", responses = {@ApiResponse(responseCode = "200", description = "Get account successfully")
            , @ApiResponse(responseCode = "403", description = "Not authorized")})
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok().body(accountService.getAccountById(id));
    }

    @PostMapping(value = "/account")
    @PreAuthorize(value = "#oauth2.hasScope('write')")
    @Operation(description = "Saved an account", responses = {@ApiResponse(responseCode = "200", description = "Saved account successfully")
            , @ApiResponse(responseCode = "403", description = "Not authorized")
            , @ApiResponse(responseCode = "401", description = "Token expired")})
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO) throws Exception {
        accountDTO.setPassW(passwordEncoder.encode("123"));
        accountService.addAccount(accountDTO);
        return ResponseEntity.ok().body(accountDTO);
    }

    @PreAuthorize(value = "#oauth2.hasScope('write') && hasRole('ADMIN')")
    @PostMapping(value = "/account/edit")
    @Operation(description = "Updatew an account", responses = {@ApiResponse(responseCode = "200", description = "Updated account successfully")
            , @ApiResponse(responseCode = "403", description = "Not authorized")})
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody AccountDTO accountDTO) throws Exception {
        accountService.updateAccount(accountDTO);
        return ResponseEntity.ok().body(accountDTO);
    }

//    @PreAuthorize(value = "#oauth2.hasScope('write') && hasRole('ADMIN')")
    @Secured(value = "ROLE_ADMIN")
    @PostMapping(value = "/account/delete/{id}")
    @Operation(description = "Save an account", responses = {@ApiResponse(responseCode = "200", description = "Saved account successfully")
            , @ApiResponse(responseCode = "403", description = "Not authorized")})
    public ResponseEntity<Long> deleteAccount(@PathVariable Long id) throws Exception {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping(value = "/me")
    public Principal getPrincipals(Principal principal){
        return principal;
    }
}
