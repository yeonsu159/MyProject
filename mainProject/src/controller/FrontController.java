package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionFactory;
import action.ActionForward;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// http://localhost:80/mainProject/main.go
		System.out.println("확인");
		
		String requestURI = req.getRequestURI();
		System.out.println("URI주소: " + requestURI);
		
		String contextPath = req.getContextPath();
		System.out.println("contextPath: " + contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command: " + command);
		
		int index = command.lastIndexOf(".");
		command = command.substring(1, index);
		System.out.println("명령어command: " + command);
		// command : main
		
		Action action = null;
		ActionForward forward = null;
		
		ActionFactory factory = ActionFactory.getInstance();
		action = factory.getAction(command);
		if (action != null) {
			try {
				forward = action.execute(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		// 3) 선택된 뷰(JSP)를 실행
		// 이동
		if (forward != null) {
			if (forward.isRedirect()) {
				resp.sendRedirect(forward.getPath());
			} else {
				String path = "WEB-INF/views/" + forward.getPath() + ".jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp); // 핵심!
			}
		}

	}
		
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 한글처리 먼저하고 doGet()을 호출함
		req.setCharacterEncoding("utf-8");
		doGet(req, resp);
	}
	
	
}
