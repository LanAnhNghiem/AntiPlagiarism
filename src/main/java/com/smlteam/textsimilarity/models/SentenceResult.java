package com.smlteam.textsimilarity.models;

import java.text.DecimalFormat;

public class SentenceResult {
    private String testSentenceContent;
    private Double score;
    private String isPlagiarism;
    private String oriSentenceContent;
    private int id;

    public String getTestSentenceContent() {
        return testSentenceContent;
    }

    public void setTestSentenceContent(String testSentenceContent) {
        this.testSentenceContent = testSentenceContent;
    }

    public String getScore() {
        return new DecimalFormat("#.##").format(score * 100);
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getIsPlagiarism() {
        return isPlagiarism;
    }

    public void setIsPlagiarism(String isPlagiarism) {
        this.isPlagiarism = isPlagiarism;
    }

    public String getOriSentenceContent() {
        return oriSentenceContent;
    }

    public void setOriSentenceContent(String oriSentenceContent) {
        this.oriSentenceContent = oriSentenceContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SentenceResult(String testSentenceContent, String oriSentenceContent, Double score, String isPlagiarism, int id) {
        this.testSentenceContent = testSentenceContent;
        this.score = score;
        this.isPlagiarism = isPlagiarism;
        this.oriSentenceContent = oriSentenceContent;
        this.id = id;
    }
}

