package com.taoyuan.gms.api.site;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "上传服务")
@RequestMapping("/sitemgnt/upload")
public interface UploadApi {

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    String uploadImg(@RequestParam("file") MultipartFile file);
}
