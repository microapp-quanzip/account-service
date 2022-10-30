package com.viettel.account.client;

import com.viettel.account.client.impl.StatisticClientConfiguration;
import com.viettel.account.service.dto.StatisticDTO;
import com.viettel.account.client.impl.StatisticFBServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "statistic-service", fallback = StatisticFBServiceImpl.class
, configuration = StatisticClientConfiguration.class)
public interface StatisticService {
    @PostMapping(value = "/statistic")
    Long addStatistic(@RequestBody StatisticDTO statisticDTO);
}


