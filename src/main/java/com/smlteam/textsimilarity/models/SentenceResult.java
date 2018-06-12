package com.smlteam.textsimilarity.models;

public class SentenceResult {
    String sentenceContent;
    Double score;
    String isPlagiarism;

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

    public SentenceResult(String sentenceContent, Double score, String isPlagiarism) {
        this.sentenceContent = sentenceContent;
        this.score = score;
        this.isPlagiarism = isPlagiarism;
    }
}
