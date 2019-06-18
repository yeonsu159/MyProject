package board;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import action.Action;
import action.ActionForward;
import dao.BoardDao;
import vo.Board;

public class BoardWriteProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 파일 업로드 
		// 1 request
		
		// 2 upload 폴더 만들고 물리적 경로
		 ServletContext application = request.getServletContext();
		 String realPath = application.getRealPath("/upload");
		 System.out.println("realPath : "+ realPath);
		
		 // 3 파일업로드 최대크기 제한 값
		 int maxPostSize = 1024 * 1024 * 10; //1024byte * 1024 * 5; 5MB
		
		 // 4 한글처리 유니코드로 설정"utf-8"
		
		 // 5 업로드 하는 파일명이 기존 업로드 된 파일명과 같은 경우 => 파일명 자동변경 정책
		
		 // 파일 업로드 처리
		MultipartRequest multi = new MultipartRequest(request, realPath, maxPostSize, "utf-8", new DefaultFileRenamePolicy());
		
		Board board = new Board();
		board.setName(multi.getParameter("name"));
		board.setPassword(multi.getParameter("pw"));
		board.setSubject(multi.getParameter("subject"));
		board.setContent(multi.getParameter("content"));
		
		// 파일
		String originalFilename =  multi.getOriginalFileName("filename");
		System.out.println("원본 파일명 : "+ originalFilename);
		
		String realFilename = multi.getFilesystemName("filename");
		System.out.println("실제 파일명 :" + realFilename);
		
		board.setFilename(realFilename); // 실제 파일명으로 저장
		
		
		board.setIp(request.getRemoteAddr());	//글작성자 IP주소값 저장
		
		BoardDao boardDao = BoardDao.getInstance();
		
		boardDao.insert(board);
		
		ActionForward forward = new ActionForward();
		forward.setPath("board.do");
		forward.setRedirect(true);
		return forward;
	}

}
