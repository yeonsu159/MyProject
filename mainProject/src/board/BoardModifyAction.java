package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.BoardDao;
import vo.Board;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// int num 파라미터 가져오기
		int num = Integer.parseInt(request.getParameter("num"));
		
		// DB 객체 준비
		BoardDao boardDao = BoardDao.getInstance();
		Board board = boardDao.getBoard(num);
		
		// request 영역객체에 저장
		request.setAttribute("board", board);
		
		// 디스패치로 방식으로 jsp뷰로 이동할때
		// 기존의 동일 request객체가 전달됨!
		// request.setAttribute("num", num); -> 이렇게 해도 되지만 비효율적이다.
		// 글번호 num을 영역객체에 따로 저장할 필요가 없음
		// 동일 request객체이므로 파라미터로 바로 찾을 수 있음!
		
		
		
		// update.jsp로 이동
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("board/content_update");
		forward.setRedirect(false);		// 디스패치 방식이동
		
		return forward;
	}

}
