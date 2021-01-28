package com.svn.gsheetsserviceaccount.service;

import com.svn.gsheetsserviceaccount.config.GSheetsRequestScopeAttr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Slf4j
@Service
public class GScheduler {

    @Autowired
    private DataTransferService dataTransferService;

    @Scheduled(fixedDelay = 20000)
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
