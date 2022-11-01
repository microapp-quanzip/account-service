package com.viettel.account.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
public class AccountDTO {
//    @JsonIgnore
    private Long id;
    private String name;
    private String login;
//    @JsonIgnore
    private String passW;
    private String email;
    private List<RoleDTO> roleDTOS;
    private int status;
}
