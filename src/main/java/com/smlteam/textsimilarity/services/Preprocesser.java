package com.smlteam.textsimilarity.services;

import ai.vitk.tok.Tokenizer;
import ai.vitk.type.Token;
import sun.plugin.javascript.navig.Array;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author NVTC
 */
public class Preprocesser {

    public ArrayList<String> getOriginSentences() {
        return originSentences;
    }

    public void setOriginSentences(ArrayList<String> originSentences) {
        this.originSentences = originSentences;
    }

    private ArrayList<String> originSentences = new ArrayList<>();
    private boolean isEN = false;
    private ArrayList<String> lstStopwordEN = new ArrayList<>();
    private ArrayList<String> lstStopwordVN = new ArrayList<>();

    public Preprocesser(boolean isEN) {
        this.isEN = isEN;
    }

    //    public static void main(String[] args) throws IOException, ParseException {
//        // TODO code application logic here
//        final long startTime = System.nanoTime();
//        System.out.println("TIME: " + (System.nanoTime() - startTime) / 1e6);
//        String path = "/home/lana/IdeaProjects/TextSimilarity/src/main/resources/Demo.txt";
//        String newContent = getPureContentFromFile(path);//vietnamese
//        System.out.println(newContent);
//
//    }
    //file's content to list converting function
    public List<String> fileToList(String content) {
        List<String> arrContent;
        originSentences.addAll(Arrays.asList(content.split(".")));
        content = removeUrl(content);
        content = removeSpecialChar(content);
        arrContent = Arrays.asList(content.split("\\."));
//        try {
//            //Tạo luồng và liên kết luồng
//            BufferedReader br = new BufferedReader(new FileReader(fileName));
//            String line;
//            String doc = "", origin = "";
//
//            while ((line = br.readLine()) != null) {
//                origin += line;
//                String tmp = removeUrl(line);
//                tmp = removeSpecialChar(tmp);
//                doc += tmp;
//            }
////            String[] sentences = doc.split("\\.");
//            originSentences.addAll(Arrays.asList(origin.split(".")));
//            arrContent = Arrays.asList(doc.split("\\."));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return arrContent;
    }

    public List<String> fileToListVN(String content) {
        List<String> arrContent = new ArrayList<>();
        Tokenizer tokenizer = new Tokenizer();
        List<Token> tokenList = new LinkedList<>();
        List<String> lstWord = new ArrayList<>();
        originSentences.addAll(Arrays.asList(content.split("\\.")));

        content = removeUrl(content);
        content = removeSpecialChar(content);
        tokenList.addAll(tokenizer.tokenize(content));

        arrContent = Arrays.asList(content.split("\\."));
//        try{
//            BufferedReader br = new BufferedReader(new FileReader(fileName));
//            String line;
//            Tokenizer tokenizer = new Tokenizer();
//            List<Token> tokenList = new LinkedList<>();
//            List<String> lstWord = new ArrayList<>();
//            String doc = "", origin = "";
//            while ((line = br.readLine()) != null) {
//                origin += line;
//                String result = removeUrl(line);
//                result = removeSpecialChar(result);
//                String tmp = removeUrl(line);
//                tmp = removeSpecialChar(tmp);
//                tokenList.addAll(tokenizer.tokenize(result));
//
//            }
//            originSentences.addAll(Arrays.asList(origin.split("\\.")));
//            for(Token token: tokenList){
//                lstWord.add(token.getWord().replace(" ","_"));
//            }
////            lstWord.removeAll(lstStopwordVN);
//            doc = String.join(" ", lstWord);
//            arrContent = Arrays.asList(doc.split("\\."));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
        return arrContent;
    }

    public ArrayList<String> readFile(String fileName) {
        ArrayList<String> arrContent = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                arrContent.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrContent;
    }

    public String removeSpecialChar(String word) {
        word = word.toLowerCase();
        Pattern pt = null;
        try {
            String reg = "[,/?|\\[\\](){}\\\\^([0-9]+)^!@#$%^&*()`~<>:;+=|\"]";
            pt = Pattern.compile(reg);

            Matcher match = pt.matcher(word);
            while (match.find()) {
                String s = match.group();
                word = word.replace(s, "");
                //remove ...
                word = word.replaceAll("\\.{3,}", "");
            }
            word = word.trim().replaceAll(" +", " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return word;
    }

    public String removeUrl(String commentstr) {
        String urlPattern = "((https?|ftp|gopher|telnet|file|Unsure|http):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern p = Pattern.compile(urlPattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(commentstr);
        int i = 0;
        while (m.find()) {
            commentstr = commentstr.replaceAll(m.group(i), "").trim();
            i++;
        }
        return commentstr;
    }

    public List<String> getPureContentFromFile(String content) {
        List<String> resultContent;
        if (isEN) {
            resultContent = fileToList(content);
//            content.removeAll(lstStopwordEN);
        } else {
            resultContent = fileToListVN(content);
//            content.removeAll(lstStopwordVN);
        }
//        System.out.println("LENG: " + content.size());
//        return String.join(" ", content);
        return resultContent;
    }
}
