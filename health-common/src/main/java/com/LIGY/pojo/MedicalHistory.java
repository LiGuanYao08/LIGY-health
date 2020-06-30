package com.LIGY.pojo;

public class MedicalHistory {
    private String anamnesis; //既往病史
    private String familyHistory;//家族病史
    private String allergy;//过敏病史
    private String seriousDiseases;//重大病史

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getSeriousDiseases() {
        return seriousDiseases;
    }

    public void setSeriousDiseases(String seriousDiseases) {
        this.seriousDiseases = seriousDiseases;
    }
}
