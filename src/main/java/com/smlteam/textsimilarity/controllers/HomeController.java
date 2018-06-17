package com.smlteam.textsimilarity.controllers;


import com.smlteam.textsimilarity.models.ParagraphResult;
import com.smlteam.textsimilarity.services.Constants;
import com.smlteam.textsimilarity.services.MainProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.StringJoiner;

@Controller
public class HomeController {
    ServletContext context;
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

    @RequestMapping(value = "/progress")
    public String progress() {
        return "progress";
    }

    @RequestMapping(value = "/result")
    public String result() {
        return "result";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String multiFileUpload(@RequestParam("files") MultipartFile[] files, @RequestParam("isEN") String language,
                                  RedirectAttributes redirectAttributes) {
        String testString = "";
        String oriString = "";
        try {
            testString = new String(files[0].getBytes(), "UTF-8");
            oriString = new String(files[1].getBytes(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean isEN = false;
        if(language.equalsIgnoreCase("en")){
            isEN = true;
        }
        if (files[0].isEmpty() || files[1].isEmpty()){
            redirectAttributes.addFlashAttribute("finalScore", "0");
            redirectAttributes.addFlashAttribute("resultColor", "green");
            redirectAttributes.addFlashAttribute("resultMess", "No Plagiarism");
            return "redirect:/result";
        }
//        try {
//            MainProcessor.saveFile(files);
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("resultColor", "red");
//            redirectAttributes.addFlashAttribute("resultMess", "Uploading error !");
//            return "redirect:/result";
//        }


        ParagraphResult listResult = new MainProcessor().compare(isEN, testString, oriString);
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

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public String textUpoad() {
        return "text_upload";
    }

    @RequestMapping(value = "/text_upload", method = RequestMethod.POST)
    public String textProcess(@RequestParam("testText") String testText, @RequestParam("oriText") String oriText, @RequestParam("isEN") String language, RedirectAttributes redirectAttributes) {

        String test = testText;
        String ori = oriText;

        boolean isEN = false;
        if(language.equalsIgnoreCase("en")){
            isEN = true;
        }
//        //Write test file
//        try {
//            File file = new File(Constants.TEST);
//            FileWriter writer = new FileWriter(file);
//            writer.write(testText);
//            writer.close();
//        } catch (IOException e) {
//            redirectAttributes.addFlashAttribute("resultColor", "red");
//            redirectAttributes.addFlashAttribute("resultMess", "Error - TEST");
//            return "redirect:/result";
//        }
//
//        //Rite origin file
//        try {
//            File file = new File(Constants.ORIGIN);
//            FileWriter writer = new FileWriter(file);
//            writer.write(oriText);
//            writer.close();
//        } catch (IOException e) {
//            redirectAttributes.addFlashAttribute("resultColor", "red");
//            redirectAttributes.addFlashAttribute("resultMess", "Error - ORIGIN");
//            return "redirect:/result";
//        }

        //Main processing
        ParagraphResult listResult = new MainProcessor().compare(isEN, testText, oriText);
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
