package com.taoyuan.gms.api.admin;

import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.admin.account.GoldChangeRequest;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "会员金币管理")
@RequestMapping("/usergold")
public interface UserGoldApi
{
    /**
     * 扣除金币
     * @param request
     * @return
     */
    TyResponse deduct(@RequestBody GoldChangeRequest request);

    /**
     * 增加金币
     * @param request
     * @return
     */
    TyResponse add(@RequestBody GoldChangeRequest request);
}
