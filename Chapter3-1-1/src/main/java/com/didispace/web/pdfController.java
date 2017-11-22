package com.didispace.web;

import com.didispace.config.Const;
import com.didispace.entity.User;
import com.dw.wraper.PDFWraper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description pdf导出测试
 * @Author daiwei
 * @Date 2017/11/14
 */
@Controller
@RequestMapping("/pdf")
public class pdfController {


    @RequestMapping(value = "/exportToPDF")
    @ResponseBody
    public void exportToPDF(HttpServletResponse response) throws Exception {
        PDFWraper pdfWraper=new PDFWraper();
        pdfWraper.exportToPDF(response, Const.FILE_NAME, Const.TITLES,Const.FIELDS,getDataList());
    }


    private List<User> getDataList() {
        List<User> userList=new ArrayList<>();
        userList.add(new User("18428385838","daiwei","国美集团","test"));
        return userList;
    }
}
