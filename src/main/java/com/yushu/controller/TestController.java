package com.yushu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

@Controller
public class TestController {
    @RequestMapping(value = "/forward.do",method = RequestMethod.GET)
    public ModelAndView handleRequest() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        // 这里虽然new了一个ModelAndView,但是也包含了前个页面传递过来的model数据
        modelAndView.addObject("helloForward", "helloForward");
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping("/upload.do")
    public void upload(@RequestParam MultipartFile file) throws Exception{
        // 文件上传的类型 - image/png
        String contentType = file.getContentType();
        String filePath = "C/" + file.getOriginalFilename();
        File file1 = new File(filePath,file.getOriginalFilename());
//        在spring mvc中支持两种上传文件的方式：
//
//        使用apache的commons-io和commons-fileupload实现文件上传
//        使用servlet3.0实现文件上传
        file.transferTo(file1);
    }
}
