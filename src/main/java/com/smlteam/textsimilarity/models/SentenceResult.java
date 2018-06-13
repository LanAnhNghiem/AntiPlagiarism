package com.smlteam.textsimilarity.models;

public class SentenceResult {
    private String sentenceContent;
    private Double score;
    private String isPlagiarism;
    private int id;

    public String getSentenceContent() {
        return sentenceContent;
    }

    public void setSentenceContent(String sentenceContent) {
        this.sentenceContent = sentenceContent;
    }

    public Double getScore() {
        return score;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SentenceResult(String sentenceContent, Double score, String isPlagiarism, int id) {
        this.sentenceContent = sentenceContent;
        this.score = score;
        this.isPlagiarism = isPlagiarism;
        this.id = id;
    }
}

