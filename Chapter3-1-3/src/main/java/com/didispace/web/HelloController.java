package com.didispace.web;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Hashtable;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 * @sees JAVA生成二维码链接(扫描二维码后进行指定链接跳转
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "index";
    }

    @RequestMapping("/createWeChatCode")
    public void createWeChatCode(HttpServletRequest request, HttpServletResponse response) {

        String codeUrl = request.getParameter("codeUrl");


        if (!StringUtils.isEmpty(codeUrl)) {
            int width = 300;
            int height = 300;
            // 二维码的图片格式
            String format = "jpg";
            Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
            // 内容所使用编码
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix bitMatrix;
            try {
                bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, width, height, hints);
                // 生成二维码
                MatrixToImageWriter.writeToStream(bitMatrix, format, response.getOutputStream());
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }

}