package com.viettel.account.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {
    private Long id;
    private String message;
    private String createBy;
    private Long accountId;
}
