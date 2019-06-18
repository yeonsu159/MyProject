package board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.BoardCommentDao;
import dao.BoardDao;
import vo.Board;
import vo.BoardComment;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 글 번호 파라미터 가져오기
		// int num
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDao boardDao = BoardDao.getInstance();
		
		// 게시판 내용을 들어갈때마다 조회수 1증가 하기
		boardDao.updateReadcount(num);
		
		// 글번호에 해당하는 글 전체(상세)내용 가져오기
		Board board = boardDao.getBoard(num);
		
		BoardCommentDao boardCommentDao = BoardCommentDao.getInstance();
		// 댓글 목록
		List<BoardComment> commentList = boardCommentDao.getCommentList(num);
		
		
		// 글 내용 줄바꿈 처리
		//(1) <pre>태그 처리
		//(2) \r\n -> <br> 바꾸기
		String content = "";
		if(board.getContent() != null){
			content = board.getContent().replace("\r\n", "<br>");
			board.setContent(content);
		}
		
		// 파일 내용 올리기
		String filename = board.getFilename();
		String ext ="";
		if(filename != null) {
			int index = board.getFilename().lastIndexOf('.');
			ext = filename.substring(index+1); // 점 위치 다음부터 가져오기
		}
		
		request.setAttribute("board", board); // 글 번호 해당되는 글 내용
		request.setAttribute("ext", ext); // 파일명 확장자
		// 댓글 -> 화면에 뿌려줄것
		request.setAttribute("commentList", commentList);
		
		// 경로
		ActionForward forward = new ActionForward();
		forward.setPath("board/content_form");
		
		forward.setRedirect(false);
		
		return forward;
	}

}
