package web;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import dao.DeptDao;
import model.Dept;
import model.PageBean;
import util.DbUtil;
import util.JsonUtil;
import util.ResponseUtil;

public class DeptDeleteServlet extends HttpServlet{
    DbUtil dbUtil=new DbUtil();
    DeptDao deptDao=new DeptDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String delIds=request.getParameter("delIds");
        Connection con=null;
        try {
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            int delNums=deptDao.deptDelete(con,delIds);
            if (delNums>0){
                result.put("success","true");
                result.put("delNums",delNums);
            }else{
                result.put("errorMsg","删除失败");
            }
            ResponseUtil.write(response,result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    }
