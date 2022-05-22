package com.nhnacademy.jdbc.board.web;

import com.nhnacademy.jdbc.board.post.service.PostService;
import java.io.File;
import java.io.FileInputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
public class DownloadController {
    private static final String UPLOAD_DIR = "C:/file/tmp/";
    private final PostService postService;

    public DownloadController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/download")
    public ModelAndView download(@RequestParam("postNo") Long postNo,
                                 HttpServletResponse response){
        ModelAndView mav = new ModelAndView("redirect:showPost?="+postNo);
        String fileName = postService.findFileName(postNo).get().getFileName();
        String filePath = UPLOAD_DIR + fileName;

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        File file = new File(filePath + fileName);
        try (InputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {

            int su = -1;
            byte[] buffer = new byte[4096];

            while (-1 != (su = is.read(buffer))) {
                os.write(su);
            }
//            while (-1 != (n = is.read(buffer))) {
//                os.write(buffer, 0, n);
//                os.write(su);
//            }
        } catch (IOException ex) {
        }

        return mav;
    }
}
