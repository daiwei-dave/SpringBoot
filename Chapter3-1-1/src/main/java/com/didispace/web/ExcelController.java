package com.didispace.web;
import com.didispace.entity.User;
import com.gitee.excel.userModel.annotations.wraper.ExportExcelWraper;
import com.gitee.excel.userModel.annotations.wraper.ImportExcelWraper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description pdf导出测试
 * @Author daiwei
 * @Date 2017/11/14
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {



    /**
     *test:http://localhost:8050/excel/exportToExcel
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/exportToExcel")
    @ResponseBody
    public void exportToExcel(HttpServletResponse response) throws Exception {
        ExportExcelWraper exportExcelWraper=new ExportExcelWraper();
        exportExcelWraper.exportToExcel(response,null, User.class,getDataList(),"test.xlsx");
    }

    @RequestMapping(value = "/importByExcel")
    @ResponseBody
    public void importByExcel(@RequestParam("file") MultipartFile file) throws Exception {
        ImportExcelWraper importExcelWraper=new ImportExcelWraper();
        List<User> entityList=importExcelWraper.parseExcel(file.getOriginalFilename(),file.getInputStream(), User.class);
        for (int i = 0; i < entityList.size(); i++) {
            System.out.println(entityList.get(i).toString());
        }
    }


    private List<User> getDataList() {
        List<User> userList=new ArrayList<>();
        userList.add(new User("18428385838","daiwei","国美集团","test"));
        return userList;
    }
}
