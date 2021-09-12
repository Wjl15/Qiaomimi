package web;

import dao.DeptDao;
import model.Dept;
import model.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.DbUtil;
import util.JsonUtil;
import util.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;
import java.io.IOException;
import java.sql.Connection;

public class DeptServlet extends HttpServlet {
    DbUtil dbUtil=new DbUtil();
    DeptDao deptDao=new DeptDao();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=request.getParameter("page");
        String rows=request.getParameter("rows");
        String deptName=request.getParameter("deptName");
        if (deptName==null){
            deptName="";
        }
        Dept dept=new Dept();
        dept.setDeptName(deptName);
        PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
        Connection con=null;
        try {
            con=dbUtil.getCon();
            JSONObject result=new JSONObject();
            JSONArray jsonArray= JsonUtil.formatRsToJsonArray(deptDao.deptList(con,pageBean,dept));
            int total=deptDao.deptCount(con,dept);
            result.put("rows",jsonArray);
            result.put("total",total);
            ResponseUtil.write(response,result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                dbUtil.closeCon(con);
            }catch (Exception e){
                e.printStackTrace();
            }
        }}}
