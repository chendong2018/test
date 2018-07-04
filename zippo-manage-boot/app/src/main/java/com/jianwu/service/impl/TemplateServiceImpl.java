package com.jianwu.service.impl;


import com.jianwu.manager.TemplateManager;
import com.jianwu.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateManager templateManager;


}
