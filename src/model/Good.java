package model;

import java.util.Date;

public class Good {
    private int gId;
    private  String pN;
    private  String gName;
    private  String isInBound;
    private Date inBoundDate;
    private int deptNo;
    private  String madeIn;
    private  String gDesc;

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public String getpN() {
        return pN;
    }

    public void setpN(String pN) {
        this.pN = pN;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getIsInBound() {
        return isInBound;
    }

    public void setIsInBound(String isInBound) {
        this.isInBound = isInBound;
    }

    public Date getInBoundDate() {
        return inBoundDate;
    }

    public void setInBoundDate(Date inBoundDate) {
        this.inBoundDate = inBoundDate;
    }

    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public String getgDesc() {
        return gDesc;
    }

    public void setgDesc(String gDesc) {
        this.gDesc = gDesc;
    }
}
