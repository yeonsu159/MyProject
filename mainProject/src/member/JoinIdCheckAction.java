package member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.MemberDao;
import vo.Member;

public class JoinIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("JoinIdCheckAction");
		
		String userid = request.getParameter("userid");
		
		MemberDao memberDao = MemberDao.getInstance();
		int count = memberDao.countById(userid);
		
		boolean isDup = false;
		if(count > 0) {
			isDup = true;
		}
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println(isDup);
		out.close();
		
		return null;
	}

}
