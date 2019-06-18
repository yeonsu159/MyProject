package board;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import dao.BoardDao;
import vo.Board;

public class BoardDeleteProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardDeleteProcessAction");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pw = request.getParameter("pw");
		
		BoardDao boardDao = BoardDao.getInstance();
		
		boolean result = boardDao.checkBoard(num, pw);
		
		
		if(result == false) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 패스워드가 틀립니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		// 비밀번호 일치 시 파일삭제처리
		
		Board board = boardDao.getBoard(num);
		String filename = board.getFilename();
		
		//웹어플리케이션상의 절대경로를 구할수 있는 ServletContext()

		ServletContext application = request.getServletContext();
		String realPath = application.getRealPath("/upload");
		String deleteFilePath = realPath +"/" + filename;
		System.out.println("삭제할 파일경로 : "+deleteFilePath);
		
		File file = new File(deleteFilePath);
		if(file.exists()) {
			file.delete();
		}
		
		System.out.println("비밀번호 확인 후");
		//[DB테이블 내용 삭제] 메소드 호출 deleteBoard(num,pw)
		boolean isSuccess = boardDao.deleteBoard(num, pw);
		
		// 페이지 번호 파라미터 가져오기
		String pageNum = request.getParameter("pageNum");
		
		// 삭제 성공시
		ActionForward forward = new ActionForward();
		forward.setPath("board.do?pageNum="+pageNum);
		forward.setRedirect(true);
		
		return forward;
	}

}
