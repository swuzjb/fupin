package com.ty.fuping.controller;

import com.ty.fuping.properties.BackUpProperties;
import com.ty.fuping.util.DataBaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author ZhanJingbo
 * @version 1.0.0
 * Created on 2018/5/29
 */
@Controller
public class BackUpController {
    @Autowired
    private BackUpProperties backUpProperties;

    @RequestMapping("/backup")
    public void backUp(HttpServletResponse response) {

        response.setHeader("content-type", "text/plain");
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".sql");
        boolean result = DataBaseUtil.exportDatabase(backUpProperties.getHost(),
                backUpProperties.getPort(), backUpProperties.getUsername(),
                backUpProperties.getPassword(), backUpProperties.getDatabase(), backUpProperties.getFile());
        if (!result) {
            throw new RuntimeException();
        }

        try {
            FileInputStream inputStream = new FileInputStream(backUpProperties.getFile());
            byte[] data = new byte[2048];
            while (inputStream.read(data) != -1) {
                response.getOutputStream().write(data);
            }
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/recover", method = RequestMethod.POST)
    @ResponseBody
    public Object recover(@RequestParam("file") MultipartFile backFile) {
        try {
            DataBaseUtil.importDatabase(backUpProperties.getHost(), backUpProperties.getPort(),
                    backUpProperties.getUsername(), backUpProperties.getPassword(), backUpProperties.getDatabase(), backFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
