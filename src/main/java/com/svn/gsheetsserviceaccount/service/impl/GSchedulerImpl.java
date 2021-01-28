package com.svn.gsheetsserviceaccount.service.impl;

import com.svn.gsheetsserviceaccount.config.GSheetsRequestScopeAttr;
import com.svn.gsheetsserviceaccount.service.DataTransferService;
import com.svn.gsheetsserviceaccount.service.GScheduler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
@Service
@RequiredArgsConstructor
public class GSchedulerImpl implements GScheduler {
    private final DataTransferService dataTransferService;

    @Scheduled(fixedDelay = 20000)
    @Override
    public void transferData(){
      //log.info("--- Transferind data from google sheets.");
        try {
            RequestContextHolder.setRequestAttributes(new GSheetsRequestScopeAttr());
            dataTransferService.transfer();
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace().toString());
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }
}
