package sg.edu.np.mad.madfit.Model;

public class Plan {
    public int planId;
    public String planTitle;
    public String planDescription;
    public String planType;
    public String planDuration;
    public Plan(){}

    public Plan(int planId, String planTitle, String planDescription, String planType, String planDuration) {
        this.planId = planId;
        this.planTitle = planTitle;
        this.planDescription = planDescription;
        this.planType = planType;
        this.planDuration = planDuration;
    }

    public Plan(String planTitle, String planDescription, String planType, String planDuration) {
        this.planTitle = planTitle;
        this.planDescription = planDescription;
        this.planType = planType;
        this.planDuration = planDuration;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
    }

    public String getPlanDescription() {
        return planDescription;
    }

    public void setPlanDescription(String planDescription) {
        this.planDescription = planDescription;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getPlanDuration() {
        return planDuration;
    }

    public void setPlanDuration(String planDuration) {
        this.planDuration = planDuration;
    }
}
