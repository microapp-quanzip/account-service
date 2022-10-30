package com.viettel.account.client.impl;

import com.viettel.account.client.StatisticService;
import com.viettel.account.service.dto.StatisticDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StatisticFBServiceImpl implements StatisticService {
    private final Logger logger = LoggerFactory.getLogger(MailFBServiceImpl.class);

    @Override
    public Long addStatistic(StatisticDTO statisticDTO) {
        logger.error("Error when saving log: " + statisticDTO.getMessage());
        return 1L;
    }
}
