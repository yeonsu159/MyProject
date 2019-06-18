package action;

import java.util.HashMap;
import java.util.Map;

import admin.ChartListAction;
import admin.ChartListFormAction;
import admin.EmailAction;
import admin.EmailProcessAction;
import admin.AD_MemberDelete;
import admin.Ad_MemberListAction;
import board.BoardAction;
import board.BoardDeleteAction;
import board.BoardDeleteProcessAction;
import board.BoardDetailAction;
import board.BoardModifyAction;
import board.BoardModifyProcessAction;
import board.BoardWriteAction;
import board.BoardWriteProcessAction;
import comment.BoardCommentProcessAction;
import comment.BoardReCommentProcessAction;
import member.JoinAction;
import member.JoinIdCheckAction;
import member.JoinProcessAction;
import member.LoginAction;
import member.LoginIdCheckAction;
import member.LogoutAction;
import member.MyListAction;

public class ActionFactory {

	// 싱글톤 패턴 적용
	private static ActionFactory factory = new ActionFactory();

	public static ActionFactory getInstance() {
		return factory;
	}
	// 싱글톤 패턴 적용
	
	private Map<String, Action> map;
	
	private ActionFactory(){
		map = new HashMap<>();
		// member
		map.put("main", new MainAction());
		map.put("login", new LoginAction());
		map.put("join", new JoinAction());
		map.put("joinProcess", new JoinProcessAction());
		map.put("joinIdCheck", new JoinIdCheckAction());
		map.put("loginCheck", new LoginIdCheckAction());
		map.put("logout", new LogoutAction());
		map.put("mylist", new MyListAction());
		
		// board
		map.put("board", new BoardAction());
		map.put("boardDetail", new BoardDetailAction());
		map.put("boardWrite", new BoardWriteAction());
		map.put("boardWriteProcess", new BoardWriteProcessAction());
		map.put("boardModify", new BoardModifyAction());
		map.put("boardModifyProcess", new BoardModifyProcessAction());
		map.put("boardDelete", new BoardDeleteAction());
		map.put("boardDeleteProcess", new BoardDeleteProcessAction());
		
		// comment
		map.put("boardCommentProcess", new BoardCommentProcessAction());
		map.put("re_CommentProcess", new BoardReCommentProcessAction());
		// admin
		map.put("memberList", new Ad_MemberListAction());
		map.put("chart", new ChartListFormAction());
		map.put("chartList", new ChartListAction());
		map.put("email", new EmailAction());
		map.put("emailProcess", new EmailProcessAction());
		map.put("ad_Delete", new AD_MemberDelete());
		
		
	}
	
	public Action getAction(String command) {
		//Action클래스를 불러와서 getAction함수를 만들어 command인자 값을 받게한다.
		
		
		return map.get(command);
	}//getAction

}
