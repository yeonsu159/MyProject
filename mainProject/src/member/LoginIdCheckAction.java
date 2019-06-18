package member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.MemberDao;

public class LoginIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("LoginIdCheckAction");
		
		String id = request.getParameter("loginid");
		String pw = request.getParameter("loginpw");
		
		MemberDao memberDao = MemberDao.getInstance();
		int check = memberDao.loginCheck(id, pw);
		String name = memberDao.selectName(id);
		
		if (check == 1) { // 아이디,비밀번호 모두 일치(로그인 성공)했을때만
			
			// 세션 구하기
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			session.setAttribute("id", id);
		}
		
		
		response.setContentType("application/json; charset=UTF-8");
		// 일부 데이터만 전송해준다.
		PrintWriter out = response.getWriter();
		
		out.println(check);
		out.close();
		
		
		
		return null;
	}

}
