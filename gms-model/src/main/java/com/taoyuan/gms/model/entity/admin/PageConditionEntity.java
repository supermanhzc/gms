package com.taoyuan.gms.model.entity.admin;

import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;

@Data
public class PageConditionEntity {
    private Integer pageIndex;
    private Integer pageSize;
    private Long id;
    private int status;
}
