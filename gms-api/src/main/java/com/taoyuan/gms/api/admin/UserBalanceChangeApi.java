package com.taoyuan.gms.api.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.taoyuan.framework.common.http.TyResponse;
import com.taoyuan.gms.model.dto.admin.UserBalanceChangeDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Api(value = "账号明细")
@RequestMapping("/balancechg")
public interface UserBalanceChangeApi {

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    IPage<Map<String, Object>> getAllBalanceChangeHistory(@RequestParam("pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize);

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    TyResponse changeBalance(@RequestBody UserBalanceChangeDto userBalanceChangeDto);
}
