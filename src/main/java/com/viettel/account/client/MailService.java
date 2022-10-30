package com.viettel.account.client;

import com.viettel.account.client.impl.MailClientConfigurationImpl;
import com.viettel.account.service.dto.MailDTO;
import com.viettel.account.client.impl.MailFBServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service", fallback = MailFBServiceImpl.class,
        configuration = MailClientConfigurationImpl.class)
public interface MailService {
    @PostMapping(value = "/send")
    void sendMail(@RequestBody MailDTO mailDTO);
}
