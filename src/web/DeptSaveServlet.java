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
import util.StringUtil;

public class DeptSaveServlet extends HttpServlet{
	DbUtil dbUtil=new DbUtil();
	DeptDao deptDao=new DeptDao();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String deptName=request.getParameter("deptName");
		String deptDesc=request.getParameter("deptDesc");
		String deptno=request.getParameter("deptno");
		Dept dept=new Dept(deptName,deptDesc);
		if(StringUtil.isNotEmpty(deptno)){
			dept.setDeptNo(Integer.parseInt(deptno));
		}
		Connection con=null;
		try{
			con=dbUtil.getCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(StringUtil.isNotEmpty(deptno)){
				saveNums=deptDao.deptModify(con, dept);
			}else{
				saveNums=deptDao.deptAdd(con, dept);
			}
			if(saveNums>0){
				result.put("success", "true");
			}else{
				result.put("success", "true");
				result.put("errorMsg", "保存失败");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
}
