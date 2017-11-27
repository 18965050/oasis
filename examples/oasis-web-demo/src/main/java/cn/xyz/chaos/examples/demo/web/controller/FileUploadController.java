package cn.xyz.chaos.examples.demo.web.controller;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/fileupload")
public class FileUploadController {

    @RequestMapping(value = "upload")
    public ResponseEntity<String> upload(@RequestParam("fileUpload") MultipartFile multiFile) throws Exception {
        byte[] bytes = multiFile.getBytes();
        String fileName = System.currentTimeMillis() + "-" + RandomStringUtils.randomAlphabetic(10) + "-"
                + multiFile.getOriginalFilename();
        String appPath = System.getProperty("web.root");
        File file = new File(appPath + "/WEB-INF/upload/" + fileName);
        FileUtils.writeByteArrayToFile(file, bytes);
        return new ResponseEntity<String>("{\"fileName\":\"" + fileName + "\"}", HttpStatus.OK);
    }
}
