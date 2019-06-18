package member;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import action.Action;
import action.ActionForward;
import dao.MemberDao;
import vo.Member;

public class JoinProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 회원가입 처리 
		// - join.jsp에서 name값을 찾는다.
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String strBirthday = request.getParameter("birthday");
		System.out.println("birthday : "+ strBirthday);
		String gender = request.getParameter("gender");
		
		
		Date birthday = Date.valueOf(strBirthday);
		
		Member member = new Member(id, pw, name, birthday, gender, email, address, phone);
				
		MemberDao memberDao = MemberDao.getInstance();
		memberDao.insertMember(member);
		
		
		// 회원가입 완료후 바로가는게 아니라 alert를 출력하기위해서
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('회원가입이 완료되었습니다.');");
		out.println("location.href = 'login.do';");
		out.println("</script>");
		out.flush();
		out.close();
		
		return null;
	}

}
