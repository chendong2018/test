package com.jianwu.service.impl;


import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;
import com.jianwu.manager.ClientManager;
import com.jianwu.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientManager clientManager;


    @Override
    public ResultResponse list(Page page, String nickName) {
        return clientManager.list(page,nickName);
    }
}
