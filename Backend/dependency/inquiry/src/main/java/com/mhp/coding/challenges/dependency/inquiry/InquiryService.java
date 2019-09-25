package com.mhp.coding.challenges.dependency.inquiry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InquiryService {

    private static final Logger LOG = LoggerFactory.getLogger(InquiryService.class);
    private final List<InquiryServiceInterface> inquiryService;

    @Autowired
    public InquiryService(List<InquiryServiceInterface> inquiryService) {
        this.inquiryService = inquiryService;
    }

    public void create(final Inquiry inquiry) {
        inquiryService.forEach(service -> {
            service.notify(inquiry);
            LOG.info("Transferred {} to service {}", inquiry, service);
        });
    }

}
