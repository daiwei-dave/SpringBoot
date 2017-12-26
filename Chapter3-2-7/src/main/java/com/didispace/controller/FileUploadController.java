package com.didispace.controller;
import com.didispace.dao.UploadFileDao;
import com.didispace.entity.UploadFile;
import com.gitee.common.constants.ErrorCode;
import com.gitee.common.entity.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;



/**
 * Created by daiwei on 2017/12/25.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    UploadFileDao uploadFileDao;


    /**
     * 图片上传
     *<P>
     *     返回图片地址，不存入数据库
     *     参考：@sees https://www.cnblogs.com/LEARN4J/p/5426980.html
     *</P>
     * @param
     * @return 图片地址
     */
    @RequestMapping("/uploadImage")
    @ResponseBody
    public ResultData uploadImage(MultipartFile file, HttpServletRequest request) {
        if (file != null) {// 判断上传的文件是否为空
            String path = null;// 文件路径
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:" + fileName);
            // 判断文件类型
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
                    path = realPath + trueFileName;
                    System.out.println("存放图片文件的路径:" + path);
                    // 转存文件到指定的路径
                    try {
                        file.transferTo(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("文件成功上传到指定目录下");
                    return ResultData.newResultData(ErrorCode.SUCCESS,ErrorCode.SUCCESS_MSG,path);
                } else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                    return null;
                }
            } else {
                System.out.println("文件类型为空");
                return null;
            }
        } else {
            System.out.println("没有找到相对应的文件");
            return null;
        }
    }



    /**
     * 图片上传
     *<P>
     *     将图片地址存入数据库
     *</P>
     * @param
     * @return
     */
    @RequestMapping("/uploadLogo")
    @ResponseBody
    public ResultData uploadLogo(MultipartFile file, HttpServletRequest request) {
        if (file != null) {// 判断上传的文件是否为空
            String path = null;// 文件路径
            String type = null;// 文件类型
            String fileName = file.getOriginalFilename();// 文件原名称
            long fileSize = file.getSize();  // 文件大小
            System.out.println("上传的文件原名称:" + fileName);
            // 判断文件类型
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            if (type != null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
                    // 设置存放图片文件的路径
                    path = realPath + trueFileName;
                    System.out.println("存放图片文件的路径:" + path);
                    // 转存文件到指定的路径
                    try {
                        file.transferTo(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("文件成功上传到指定目录下");
                    UploadFile uploadFile=new UploadFile();
                    uploadFile.setName(fileName);
                    uploadFile.setPath(path);
                    uploadFile.setType(type);
                    uploadFile.setSize(fileSize);
                    uploadFileDao.add(uploadFile);
                    return ResultData.newSuccessResultData();
                } else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                    return null;
                }
            } else {
                System.out.println("文件类型为空");
                return null;
            }
        } else {
            System.out.println("没有找到相对应的文件");
            return null;
        }
    }

}

