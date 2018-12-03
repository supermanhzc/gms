package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.admin.account.QueryAccountBalanceRequest;
import com.taoyuan.gms.model.dto.admin.account.UpdateAccountBalanceRequest;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Api(value = "账号明细")
@RequestMapping("/balancechg")
public interface UserBalanceChangeApi {

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    TyResponse getAllBalanceChangeHistory(@RequestBody QueryAccountBalanceRequest queryAccountBalanceRequest);

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    TyResponse changeBalance(@RequestBody UpdateAccountBalanceRequest updateAccountBalanceRequest);
}
