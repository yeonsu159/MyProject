package comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.BoardCommentDao;
import vo.Board;
import vo.BoardComment;

public class BoardReCommentProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BoardComment bc = new BoardComment();
		// 사용자 직접 입력값 파라미터 가져와서 저장
		bc.setBoard_num(Integer.parseInt(request.getParameter("re_boardnum")));
		bc.setId(request.getParameter("re_reply_id"));
		bc.setPassword(request.getParameter("re_reply_pw"));
		bc.setContent(request.getParameter("re_reply_content"));
		// [답글을 다는 대상글]의 답글관련 정보 파라미터 가져와서 저장
		bc.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		bc.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		bc.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		
		bc.setIp(request.getRemoteAddr());
		
		BoardCommentDao boardCommentDao = BoardCommentDao.getInstance();
		// 메소드 호출 replyInsert(board) 답글쓰기
		boardCommentDao.replyInsert(bc);
		
		// 답글쓰기 작업이 완료되면 기존 글목록 화면으로 이동
		ActionForward forward = new ActionForward();
		forward.setPath("boardDetail.do?num=" + bc.getBoard_num());
		forward.setRedirect(true);
		
		return forward;
	}

}
