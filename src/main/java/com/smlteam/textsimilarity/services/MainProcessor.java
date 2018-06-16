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

    public ParagraphResult compare(boolean isEN) {
        Preprocesser preprocesser = new Preprocesser(isEN);
        List<String> originContent = preprocesser.getPureContentFromFile(Constants.ORIGIN);
        List<String> originResult = preprocesser.getOriginSentences();

        List<String> testContent = preprocesser.getPureContentFromFile(Constants.TEST);
        List<String> testResult = preprocesser.getOriginSentences();

        ParagraphResult finalResult = new ParagraphResult();

        Indexer indexer = new Indexer();
        CalcTFIDF calcTFIDF = new CalcTFIDF();

        int numOfSentence = testContent.size();
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
                //If is plagiarism
                if (result > 0.5) {
                    finalResult.getLstSentence().add(new SentenceResult(testResult.get(i), originResult.get(i), result, "yes", i));

                    totalScore += result;
                    break;
                }
                origin++;
            }
        }
        Double finalscore = 0.0;
        if (numOfSentence != 0.0){
            finalscore = totalScore / (double)numOfSentence;
        }
        System.out.println("FINAL SCORE: " + finalscore);
        finalResult.setFinalScore(finalscore);
        return finalResult;
    }

    public static void saveFile(MultipartFile[] files) {
        String UPLOADED_FOLDER = "src/main/resources/test";

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

        new MainProcessor().compare(true);
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
