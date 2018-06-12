package com.smlteam.textsimilarity.services;

import java.util.HashMap;
import java.util.List;

public class MainProcessor {

    public static void compare(boolean isEN, String originFilePath, String testFilePath){
        Preprocesser preprocesser = new Preprocesser(isEN);
        List<String> originContent = preprocesser.getPureContentFromFile(originFilePath);
        List<String> testContent = preprocesser.getPureContentFromFile(testFilePath);
        Indexer indexer = new Indexer();
        CalcTFIDF calcTFIDF = new CalcTFIDF();
        for(int i = 0; i < testContent.size(); i++){
            int origin = 0;
            for(String docO: originContent){
                indexer.indexer(docO, testContent.get(i),String.valueOf(i));
                List<HashMap<String, Double>> listVector = calcTFIDF.calcAllTFIDF(String.valueOf(i));
                CosineSimilarity cs = new CosineSimilarity();
                Double result = cs.calcCosine(listVector.get(0), listVector.get(1));
                System.out.print("Test: "+i+", Origin: "+ origin +", % plagiarism: "+result +"\n");
                origin ++;
            }
        }
    }
    public static void main(String[] args){

        MainProcessor.compare(false, Constants.ORIGIN, Constants.TEST);
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
