package com.hxgz.implement.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Jackson
 * @description 文件上传测试
 * @date 2019/6/14
 */
@RestController
@RequestMapping("index")
public class FileUpload {

    private final Logger logger = LoggerFactory.getLogger(FileUpload.class);

    @RequestMapping(value = "upload.do",method = RequestMethod.POST)
    public Map<String,Object> fileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        logger.info("文件名称："+file.getOriginalFilename());
        String fileSuffer = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        logger.info("文件后缀：" + fileSuffer);

        Map<String,Object> map = new HashMap<String,Object>();
        if(fileSuffer == ".pdf" || ".pdf".equals(fileSuffer)){
            file.transferTo(new File("D:/"+file.getOriginalFilename()));
            map.put("msg","上传成功");
        }else{
            map.put("msg","文件格式不正确");
        }
        return map;
    }
}
