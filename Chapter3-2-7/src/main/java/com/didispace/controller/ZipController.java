package com.didispace.controller;

import com.gitee.common.constants.ErrorCode;
import com.gitee.common.entity.ResultData;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by daiwei on 2017/11/20.
 * @sees https://blog.csdn.net/sinat_32849651/article/details/77098161
 */
@Controller
@RequestMapping("/zip")
public class ZipController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());




    /**
     * 根  http://localhost:8050/zip/filesDownloadToZip
     * @return
     */
    @RequestMapping("/filesDownloadToZip")
    @ResponseBody
    public ResultData<Object> filesDownloadToZip(HttpServletResponse response) {
        try {
            ResultData<Object> resultData = fileUrlsToZip(response);
            return resultData;
        } catch (Exception e) {
            logger.error("下载失败", e);
            return ResultData.newResultData(ErrorCode.QUERY_FAILOR, "下载失败");
        }
    }

    private ResultData<Object> fileUrlsToZip( HttpServletResponse response) {
        try {
            List<String> urlList=new ArrayList<>();
            urlList.add("http://10.112.101.86:7480/cdim-backstage-500/1535077299101.jpg");
            urlList.add("http://10.112.101.86:7480/cdim-performance-500/1535439325441.jpg");
            String fileName = new String("xx.zip".getBytes("UTF-8"), "ISO8859-1");//控制文件名编码
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ZipOutputStream zos = new ZipOutputStream(bos);
            int idx = 1;
            if (CollectionUtils.isEmpty(urlList)){
                return null;
            }
            for (String url:urlList) {
                String urlFileName = null;
                if (null == url || url.trim().length() == 0 || !url.startsWith("http")) {
                    return null;
                }
                if (null == urlFileName || urlFileName.trim().length() == 0) {
                    urlFileName = UUID.randomUUID().toString() + "." + getExtensionName(url);
                }
                zos.putNextEntry(new ZipEntry(urlFileName));
                byte[] bytes = httpConverBytes(url);
                zos.write(bytes, 0, bytes.length);
                zos.closeEntry();
                idx++;
            }
            zos.close();
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
            OutputStream os = response.getOutputStream();
            os.write(bos.toByteArray());
            os.close();
            return ResultData.newSuccessResultData();
        } catch (Exception e) {
            logger.error("下载失败", e);
            return ResultData.newResultData(ErrorCode.FAILOR, "下载失败");
        }
    }


    public static String getExtensionName(String filename) {
        if (filename != null && filename.length() > 0) {
            int dot = filename.lastIndexOf('.');
            if (dot > -1 && dot < filename.length() - 1) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }



    /**
     * @param path
     * @return
     * @MethodName httpConverBytes
     * @Description http路径文件内容获取
     */
    public byte[] httpConverBytes(String path) {
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        URLConnection conn = null;

        try {
            URL url = new URL(path);
            conn = url.openConnection();

            in = new BufferedInputStream(conn.getInputStream());

            out = new ByteArrayOutputStream(1024);
            byte[] temp = new byte[1024];
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            byte[] content = out.toByteArray();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
