package com.taoyuan.gms.core.sitemanage.account.controller;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.taoyuan.framework.bs.controller.TyBaseController;
import com.taoyuan.framework.common.exception.TyBusinessException;
import com.taoyuan.framework.common.util.TyImageUtil;
import com.taoyuan.gms.api.site.UploadApi;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UploadController extends TyBaseController implements UploadApi {

    @Value("${image.path}")
    private String path;

    @Override
    public String uploadImg(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || StringUtils.isBlank(file.getOriginalFilename())) {
            throw new TyBusinessException("对象不能为空。");
        }

        String contentType = file.getContentType();
        if (!contentType.contains("")) {
            throw new TyBusinessException("文件类型不能为空。");
        }

        String root_fileName = file.getOriginalFilename();
        log.info("上传图片:name={},type={}", root_fileName, contentType);
        log.info("图片保存路径={}", path);
        try {
            return TyImageUtil.saveImg(file, path);
        } catch (IOException e) {
            log.error("图片处理IO异常", e);
        }
        return null;
    }
}
