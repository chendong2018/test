package com.jianwu.service;


import com.jianwu.domain.result.Page;
import com.jianwu.domain.result.ResultResponse;

public interface ClientService {

    /*
    分页模糊查询客户列表
     */
    ResultResponse list(Page page, String nickName);

}
