package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.StringUtil;

import model.Dept;
import  model.PageBean;

public class DeptDao {

    public ResultSet deptList(Connection con,PageBean pageBean,Dept dept)throws Exception{
        StringBuffer sb=new StringBuffer("select * from t_dept");
        if(dept!=null && StringUtil.isNotEmpty(dept.getDeptName())){
            sb.append(" and deptName like '%"+dept.getDeptName()+"%'");
        }
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));

        return pstmt.executeQuery();
    }

    public int deptCount(Connection con,Dept dept)throws Exception{
        StringBuffer sb=new StringBuffer("select count(*) as total from t_dept");
        if(StringUtil.isNotEmpty(dept.getDeptName())){
            sb.append(" and deptName like '%"+dept.getDeptName()+"%'");
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }
    }


    /**
     * delete from tableName where field in (1,3,5)
     * @param con
     * @param delIds
     * @return
     * @throws Exception
     */
    public int deptDelete(Connection con,String delIds)throws Exception{
        String sql="delete from t_dept where deptno in("+delIds+")";
        PreparedStatement pstmt=con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    public int deptModify(Connection con,Dept dept)throws Exception{
        String sql="update t_dept set deptName=?,deptDesc=? where deptno=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, dept.getDeptName());
        pstmt.setString(2, dept.getDeptDesc());
        pstmt.setInt(3, dept.getDeptNo());
        return pstmt.executeUpdate();
    }

    public int deptAdd(Connection con,Dept dept)throws Exception{
        String sql="insert into t_dept values(null,?,?,'QA')";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1, dept.getDeptName());
        pstmt.setString(2, dept.getDeptDesc());
        return pstmt.executeUpdate();
    }
}