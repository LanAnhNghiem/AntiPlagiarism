package com.smlteam.textsimilarity.services;

import com.smlteam.textsimilarity.models.ParagraphResult;
import com.smlteam.textsimilarity.models.SentenceResult;
import sun.applet.Main;

import java.util.HashMap;
import java.util.List;

public class MainProcessor {

    public ParagraphResult[] compare(boolean isEN) {
        Preprocesser preprocesser = new Preprocesser(isEN);
        List<String> originContent = preprocesser.getPureContentFromFile(Constants.ORIGIN);
        List<String> testContent = preprocesser.getPureContentFromFile(Constants.TEST);

        ParagraphResult originResult = new ParagraphResult();
        ParagraphResult testResult = new ParagraphResult();
        Indexer indexer = new Indexer();
        CalcTFIDF calcTFIDF = new CalcTFIDF();
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
                    break;
                }
                origin++;
            }
        }
        return new ParagraphResult[]{originResult, testResult};
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
