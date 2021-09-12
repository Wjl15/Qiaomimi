package dao;


import model.PageBean;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GoodDao {
    public ResultSet goodList(Connection con, PageBean pageBean)throws Exception{
        StringBuffer sb=new StringBuffer("select * from t_good s,t_dept g where s.deptNo=g.deptno");
        if(pageBean!=null){
            sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
        }
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));

        return pstmt.executeQuery();
    }

    public int goodCount(Connection con)throws Exception{
        StringBuffer sb=new StringBuffer("select count(*) as total from t_good s,t_dept g where s.deptNo=g.deptno");
        PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
        ResultSet rs=pstmt.executeQuery();
        if(rs.next()){
            return rs.getInt("total");
        }else{
            return 0;
        }


    }
    public int goodDelete(Connection con, String delIds) throws Exception{
        String sql="delete from t_good where gid in("+delIds+")";
        PreparedStatement pstmt;
        pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }
}
