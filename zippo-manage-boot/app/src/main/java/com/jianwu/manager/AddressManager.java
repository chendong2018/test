package com.jianwu.manager;

import com.jianwu.domain.request.ZipAddressRequest;
import com.jianwu.domain.result.ResultResponse;

/**
 * 地址业务逻辑接口
 *
 * @Author chendong
 * @Create 2018/6/2
 * @Time 13:53
 **/
public interface AddressManager {

    /**
     * @ClassName: AddressManager
     * @Description: 获取用户地址列表
     * @Author: chenDong
     * @Date: 2018/6/2 13:53
     * @Remark:
     */
    ResultResponse findAddressByWeChatUserId(String weChatId);

    /**
     * @ClassName: AddressManager
     * @Description: 新增地址
     * @Author: chenDong
     * @Date: 2018/6/2 15:50
     * @Remark:
     */
    ResultResponse addAddress(ZipAddressRequest zipAddressRequest);

    /**
     * @ClassName: AddressManager
     * @Description: 修改地址
     * @Author: chenDong
     * @Date: 2018/6/2 15:50
     * @Remark:
     */
    ResultResponse updateAddress(ZipAddressRequest zipAddressRequest);

    /**
     * @ClassName: AddressManager
     * @Description: 删除或设置默认地址
     * @Author: chenDong
     * @Date: 2018/6/2 15:50
     * @Remark:
     */
    ResultResponse deleteAddress(Integer id, Integer status);




}
