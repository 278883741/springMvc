package com.yushu.controller;

import com.yushu.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AnnotationController {

    @RequestMapping(value = "/annotation.do",method = RequestMethod.GET)
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("hello", "hello first springMvc");
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/baidu.do",method = RequestMethod.GET)
    public String test(){
        return "baidu";
    }

    @RequestMapping(value = "/testModelData.do",method = RequestMethod.GET)
    public String testModelData(Model model){
        // springMvc会自动帮我们new Model，然后把数据绑定上
        return "baidu";
    }

    @RequestMapping(value = "/getData.do",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Object getData(){
        Student student = new Student();
        student.setName("小明");
        student.setAge(10);
        return student;
    }

    /**
     * ModelAndView的显示转发，配置的视图解析器会失效，得写全路径
     * @return
     */
    @RequestMapping(value = "/testForward.do",method = RequestMethod.GET)
    public ModelAndView testForward(String data){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("hello","你好");
        // 这里默认其实也是转发
        // modelAndView.setViewName("hello");
        // 转发到jsp页面
        // modelAndView.setViewName("forward:/jsp/hello.jsp");
        // 转发到其他controller
        // data会被添加到request参数里
        modelAndView.setViewName("forward:/forward.do");

        // 重定向到一个地址，其实是客户端发起了两次请求了，上面的hello参数会以requestUrl的形式拼接在重定向的url中，类的类型则不识别，会忽略
        // data不会被添加到request参数里
        modelAndView.setViewName("redirect:/forward.do");
        return modelAndView;
    }

    @RequestMapping(value = "/testValidate",method = RequestMethod.POST)
    public ModelAndView testValidate(@Validated Student student, BindingResult br) {
        ModelAndView modelAndView = new ModelAndView();

        List<ObjectError> allErrors = br.getAllErrors();
        if (allErrors != null && allErrors.size() > 0) {
            FieldError nameError = br.getFieldError("name");
            FieldError ageError = br.getFieldError("age");

            if (nameError != null) {
                modelAndView.addObject("nameError", nameError.getDefaultMessage());
            }
            if (ageError != null) {
                modelAndView.addObject("ageError", ageError.getDefaultMessage());
            }

            modelAndView.setViewName("testValidate");
            return modelAndView;
        }
        modelAndView.addObject("name", student.getName());
        modelAndView.setViewName("index");
        return modelAndView;

    }
}
