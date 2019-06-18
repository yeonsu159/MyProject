package member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.MemberDao;
import vo.Member;

public class MyListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MyListAction");
		
		String id = request.getParameter("id");
		
		MemberDao memberDao = MemberDao.getInstance();
		
		Member member = memberDao.getMember(id);
		
		request.setAttribute("member", member);
		// 경로
		ActionForward forward = new ActionForward();
		forward.setPath("member/mylist");
		
		forward.setRedirect(false);
		
		return forward;
	}

}
