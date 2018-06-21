package com.hlct.bbsservice.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring5.context.SpringContextUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;


@RestController
@RequestMapping(value = "/upload")
class FileUploadController {

    private final static Logger log = LoggerFactory.getLogger(FileUploadController.class);
    private final ServiceProperties serviceProperties;

    @Autowired
    public FileUploadController(ServiceProperties serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

    @GetMapping
    public String upload(){
        return "upload";
    }

    @PostMapping(value = "/singleUpload")
    public ResultInfo<String> upload1(@RequestParam("file") MultipartFile file) throws IOException {
        log.info("[文件类型] - [{}]", file.getContentType());
        log.info("[文件名称] - [{}]", file.getOriginalFilename());
        log.info("[文件大小] - [{}]", file.getSize());
        // TODO 将文件写入到指定目录（具体开发中有可能是将文件写入到云存储/或者指定目录通过 Nginx 进行 gzip 压缩和反向代理，此处只是为了演示故将地址写成本地电脑指定目录）
        file.transferTo(new File(serviceProperties.getUploadPath() + serviceProperties.getImagePath()+ file.getOriginalFilename()));
//        Map<String, String> result = new HashMap<>(16);
//        result.put("contentType", file.getContentType());
//        result.put("filePath","http://192.168.1.104:8080/images/" + file.getOriginalFilename());
//        result.put("fileName", file.getOriginalFilename());
//        result.put("fileSize", file.getSize() + "");
        ResultInfo<String> resultInfo = new ResultInfo<>();
        resultInfo.setCode(ResultInfo.RESULT_SUCCESS);
        resultInfo.setMessage("上传成功");
        resultInfo.setData(serviceProperties.getServiceDomain()
                + serviceProperties.getImagePath() + file.getOriginalFilename());
        //resultInfo.setData("http://192.168.1.104:8080/images/" + file.getOriginalFilename());
        return resultInfo;
    }

    @PostMapping(value = "/multipleUpload")
    public List<Map<String, String>> upload2(@RequestParam("file") MultipartFile[] files) throws IOException {
        if (files == null || files.length == 0) {
            return null;
        }
        List<Map<String, String>> results = new ArrayList<>();
        for (MultipartFile file : files) {
            // TODO Spring Mvc 提供的写入方式
            file.transferTo(new File(serviceProperties.getUploadPath() + file.getOriginalFilename()));
            Map<String, String> map = new HashMap<>(16);
            map.put("contentType", file.getContentType());
            map.put("fileName", file.getOriginalFilename());
            map.put("fileSize", file.getSize() + "");
            results.add(map);
        }
        return results;
    }

    @PostMapping(value = "/base64Upload")
    public void upload2(String base64) throws IOException {
        // TODO BASE64 方式的 格式和名字需要自己控制（如 png 图片编码后前缀就会是 data:image/png;base64,）
        final File tempFile = new File("F:\\app\\bbs\\test.jpg");
        // TODO 防止有的传了 data:image/png;base64, 有的没传的情况
        String[] d = base64.split("base64,");
        final byte[] bytes = Base64Utils.decodeFromString(d.length > 1 ? d[1] : d[0]);
        FileCopyUtils.copy(bytes, tempFile);

    }
}

