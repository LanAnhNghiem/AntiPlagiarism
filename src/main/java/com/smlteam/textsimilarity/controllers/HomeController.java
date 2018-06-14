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
import java.text.DecimalFormat;
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
        //Lưu file
        MainProcessor.saveFile(files);
        ParagraphResult listResult = new MainProcessor().compare(false);
        redirectAttributes.addFlashAttribute("finalScore", new DecimalFormat("#.##").format(listResult.getFinalScore() * 100));
        if (listResult.getFinalScore() > 0.5) {
            redirectAttributes.addFlashAttribute("resultColor", "red");
            redirectAttributes.addFlashAttribute("resultMess", "Plagiarism");

        } else {
            redirectAttributes.addFlashAttribute("resultColor", "green");
            redirectAttributes.addFlashAttribute("resultMess", "No Plagiarism");
        }

        redirectAttributes.addFlashAttribute("testPragraphResult", listResult.getLstSentence());

        return "redirect:/result";
    }
}
