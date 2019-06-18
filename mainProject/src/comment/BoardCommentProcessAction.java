package comment;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.BoardCommentDao;
import dao.BoardDao;
import vo.BoardComment;

public class BoardCommentProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardCommentProcessAction");
		BoardComment bc = new BoardComment();
		// 주 게시판글 받아오기 
		bc.setBoard_num(Integer.parseInt(request.getParameter("boardnum")));
		bc.setId(request.getParameter("reply_id"));
		bc.setPassword(request.getParameter("reply_pw"));
		bc.setContent(request.getParameter("reply_content"));
		
		bc.setIp(request.getRemoteAddr());
		
		BoardCommentDao boardCommentDao = BoardCommentDao.getInstance();
		
		int rowCount = boardCommentDao.insertComment(bc); //  주댓글 insert 완료
		
		
		// 글 목록(boardList.do) 화면으로 이동. 리다이렉트
//		ActionForward forward = new ActionForward();
//		forward.setPath("boardDetail.do?num=" + bc.getBoard_num());
//		forward.setRedirect(true);
//		return forward;
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(true);  // 댓글등록 성공여부
		out.close();
		
		return null;
	}

}
