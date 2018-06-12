package com.smlteam.textsimilarity.models;

import java.util.List;

public class ParagraphResult {
    Double finalScore;
    List<SentenceResult> lstSentence;

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

    public ParagraphResult(Double finalScore, List<SentenceResult> lstSentence) {
        this.finalScore = finalScore;
        this.lstSentence = lstSentence;
    }
}
