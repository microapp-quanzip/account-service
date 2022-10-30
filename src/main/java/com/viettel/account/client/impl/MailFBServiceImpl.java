package com.viettel.account.client.impl;

import com.viettel.account.client.MailService;
import com.viettel.account.service.dto.MailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MailFBServiceImpl implements MailService {
    private final Logger logger = LoggerFactory.getLogger(MailFBServiceImpl.class);
    @Override
    public void sendMail(MailDTO mailDTO) {
        logger.error("Server is slow when sending mail to: " + mailDTO.getToName());
    }
}
