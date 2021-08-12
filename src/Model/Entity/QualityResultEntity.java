package Model.Entity;

public class QualityResultEntity {
    // Author: Robin Steinwarz
    private int storyID;
    private double qualityDeduction;
    private String detail;
    private String hint;

    public int getStoryID() {
        return storyID;
    }

    public void setStoryID(int storyID) {
        this.storyID = storyID;
    }

    public double getQualityDeduction() {
        return qualityDeduction;
    }

    public void setQualityDeduction(double qualityDeduction) {
        this.qualityDeduction = qualityDeduction;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public QualityResultEntity(int storyID, double qualityDeduction, String detail, String hint){
        this.storyID = storyID;
        this.qualityDeduction = qualityDeduction;
        this.detail = detail;
        this.hint = hint;
    }
}
