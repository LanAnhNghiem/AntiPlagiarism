package com.smlteam.textsimilarity.models;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;


public class ParagraphResult {

    private Double finalScore;
    boolean isPla;
    private List<SentenceResult> lstSentence;


    public Double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }
    public List<SentenceResult> getLstSentence() {
        return lstSentence;
    }

    public void setLstSentence(List<SentenceResult> lstSentence) {
        this.lstSentence = lstSentence;
    }

    public ParagraphResult(Double finalScore, boolean isPla, List<SentenceResult> lstSentence) {
        this.finalScore = finalScore;
        this.isPla = isPla;
        this.lstSentence = lstSentence;
    }

    public boolean isPla() {
        return isPla;
    }

    public void setPla(boolean pla) {
        isPla = pla;
    }

    //
    public ParagraphResult() {
        this.lstSentence = new ArrayList<>();
        this.finalScore = 0.0;
        this.isPla = false;
    }
}
