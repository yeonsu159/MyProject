package admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.MemberDao;

public class AD_MemberDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String[] values = request.getParameterValues("check");
		
		MemberDao memberDao = MemberDao.getInstance();
		memberDao.batchDelete(values);
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(true);  
		out.close();
		
		return null;
	}

}
