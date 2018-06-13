package com.smlteam.textsimilarity.services;

import com.smlteam.textsimilarity.models.ParagraphResult;
import com.smlteam.textsimilarity.models.SentenceResult;
import org.springframework.web.multipart.MultipartFile;
import sun.applet.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class MainProcessor {

    public static ParagraphResult[] compare(boolean isEN) {
        Preprocesser preprocesser = new Preprocesser(isEN);
        List<String> originContent = preprocesser.getPureContentFromFile(Constants.ORIGIN);
        List<String> testContent = preprocesser.getPureContentFromFile(Constants.TEST);

        ParagraphResult originResult = new ParagraphResult();
        ParagraphResult testResult = new ParagraphResult();
        Indexer indexer = new Indexer();
        CalcTFIDF calcTFIDF = new CalcTFIDF();
        Double numOfPlaSentence = 0.0;
        Double totalScore = 0.0;
        for (int i = 0; i < testContent.size(); i++) {
            //add variable
            int origin = 0;
            for (String docO : originContent) {
                indexer.indexer(docO, testContent.get(i), String.valueOf(i));
                List<HashMap<String, Double>> listVector = calcTFIDF.calcAllTFIDF(String.valueOf(i));
                CosineSimilarity cs = new CosineSimilarity();
                Double result = cs.calcCosine(listVector.get(0), listVector.get(1));
                System.out.print("Test: " + i + ", Origin: " + origin + ", % plagiarism: " + result + "\n");
                //If is palagiarism
                if (result > 0.5) {
                    testResult.getLstSentence().add(new SentenceResult(testContent.get(i), result, "yes", i));
                    originResult.getLstSentence().add(new SentenceResult(docO, result, "yes", i));

                    numOfPlaSentence++;
                    totalScore += result;
                    break;
                }
                origin++;
            }
        }

        testResult.setFinalScore(totalScore / numOfPlaSentence);
        return new ParagraphResult[]{originResult, testResult};
    }

    public static void saveFile(MultipartFile[] files) {
        String UPLOADED_FOLDER = "C:\\Users\\Chuong\\Documents\\GitHub\\AntiPlagiarism\\src\\main\\resources\\test\\";

        //Save test file
        if (files[0].isEmpty()) {
            try {
                File file = new File(UPLOADED_FOLDER + "test.txt");
                FileWriter writer = new FileWriter(file);
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                byte[] bytes = files[0].getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + "test.txt");
                Files.write(path, bytes);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //Save origin file
        if (files[1].isEmpty()) {
            try {
                File file = new File(UPLOADED_FOLDER + "origin.txt");
                FileWriter writer = new FileWriter(file);
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                byte[] bytes = files[1].getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + "origin.txt");
                Files.write(path, bytes);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {

        new MainProcessor().compare(false);
//        Preprocesser preprocesser = new Preprocesser(false);
//        List<String> originContent = preprocesser.getPureContentFromFile(Constants.ORIGIN);
//        List<String> testContent = preprocesser.getPureContentFromFile(Constants.TEST);
////        int start, end = 0;
////        int threadNum = 1;
////        for(int i = 0; i< threadNum; i++)
////        {
////            if(i != threadNum - 1)
////                end = (testContent.size()/ threadNum) + start;
////            else
////            end = testContent.size();
////        new MultiTasking(originContent, testContent).Run();
//        Indexer indexer = new Indexer();
//        CalcTFIDF calcTFIDF = new CalcTFIDF();
////        int tmp = 0;
//        for(int i = 0; i < testContent.size(); i++){
//            int origin = 0;
//            for(String docO: originContent){
//                indexer.indexer(docO, testContent.get(i),String.valueOf(i));
//                List<HashMap<String, Double>> listVector = calcTFIDF.calcAllTFIDF(String.valueOf(i));
//                CosineSimilarity cs = new CosineSimilarity();
//                Double result = cs.calcCosine(listVector.get(0), listVector.get(1));
//                System.out.print("Test: "+i+", Origin: "+origin +", % plagiarism: "+result +"\n");
//                origin ++;
//            }
//        }
    }
}
