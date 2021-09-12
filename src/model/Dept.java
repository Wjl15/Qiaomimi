package model;


public class Dept {

    private int deptNo;
    private String deptName;
    private String deptDesc;
    public Dept()
    {
        super();
    }

    public Dept(String deptName,String deptDesc)
    {
        super();
        this.deptName = deptName;
        this.deptDesc = deptDesc;
    }
    public int getDeptNo() {
        return deptNo;
    }
    public void setDeptNo(int deptNo) {
        this.deptNo = deptNo;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getDeptDesc() {
        return deptDesc;
    }
    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }

}
