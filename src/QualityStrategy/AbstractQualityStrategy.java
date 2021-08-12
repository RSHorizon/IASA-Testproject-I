package QualityStrategy;

public abstract class AbstractQualityStrategy implements QualityStrategy{
    // Author: Robin Steinwarz
    protected double qualityDeduction;

    public double getQualityDeduction() {
        return qualityDeduction;
    }

    public void setQualityDeduction(double qualityDeduction) {
        this.qualityDeduction = qualityDeduction;
    }
}
