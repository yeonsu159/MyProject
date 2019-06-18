package admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import dao.MemberDao;
import vo.Member;

public class Ad_MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 관리자만 접근하는 액션
		
		// 세션 구하기
		HttpSession session = request.getSession();
		// 정상 로그인과정 거쳤는지 확인
		String name = (String) session.getAttribute("name");
		
		ActionForward forward = null;
		if (!"관리자".equals(name)) {
			forward = new ActionForward("main.do", true);
			return forward;
		}
		
		MemberDao memberDao = MemberDao.getInstance();
		List<Member> list = memberDao.getAllMembers();
		
		// request 영역객체에 싣으면
		// 디스패치로 이동한 jsp까지 살아서 갑니다.
		request.setAttribute("list", list);
		
		forward = new ActionForward();
		forward.setPath("admin/admin_member");
		
		forward.setRedirect(false);
		return forward;
	}

}
