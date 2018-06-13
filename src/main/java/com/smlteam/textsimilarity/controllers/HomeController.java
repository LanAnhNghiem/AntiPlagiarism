package com.smlteam.textsimilarity.controllers;


import com.smlteam.textsimilarity.models.ParagraphResult;
import com.smlteam.textsimilarity.services.MainProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String home(ModelMap model) {
        model.addAttribute("A", "3");
        return "index";
    }

    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }

    @RequestMapping(value = "/help")
    public String help() {
        return "help";
    }

    @PostMapping(value = "/progress")
    public String progress() {
        return "progress";
    }

    @RequestMapping(value = "/result")
    public String result() {
        return "result";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String multiFileUpload(@RequestParam("files") MultipartFile[] files,
                                  RedirectAttributes redirectAttributes) {
//        StringJoiner sj = new StringJoiner(" , ");

//        for (MultipartFile file : files) {
//
//            if (file == null) {
//                continue; //next pls
//            }
//            try {
//                byte[] bytes = file.getBytes();
//                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
//                Files.write(path, bytes);
//
//                sj.add(file.getOriginalFilename());
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        String uploadedFileName = sj.toString();
//        if (StringUtils.isEmpty(uploadedFileName)) {
//            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
//        } else {
//            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + uploadedFileName + "'");
//        }
//            redirectAttributes.addFlashAttribute("resultColor", "green");
        //Lưu file
        //Xử lý file
        ParagraphResult[] listResult = MainProcessor.compare(false);
        redirectAttributes.addFlashAttribute("listReuslt", listResult);
        return "redirect:/result";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }


}
