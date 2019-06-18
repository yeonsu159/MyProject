package admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import action.Action;
import action.ActionForward;
import dao.MemberDao;

public class ChartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDao memberDao = MemberDao.getInstance();

		JSONArray jsonArray = memberDao.getCountPerAddress();
		System.out.println(jsonArray);

		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(jsonArray);
		out.close();
	
		
		return null;
	}

}
