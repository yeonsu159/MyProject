package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Board;
import vo.BoardComment;

public class BoardCommentDao {

	// ============== 싱글톤 패턴 적용 =================
	private static BoardCommentDao boardCommentDao = new BoardCommentDao();

	private BoardCommentDao() {

	}

	public static BoardCommentDao getInstance() {
		return boardCommentDao;
	}
	// ============== 싱글톤 패턴 적용 =================

	public int insertComment(BoardComment boardComment) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rowCount = 0;

		try {
			con = DBUtil.getConnection();

			int num = 0;

			StringBuilder sb = new StringBuilder();

			sb.append("SELECT MAX(num) FROM board_comment");

			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1;
			} else {
				num = 1;
			}

			sb.setLength(0);
			pstmt.close();

			// 답글쓰기
			sb.append("INSERT INTO board_comment (num, board_num, id, password, content, re_ref, re_lev, re_seq, ip, reg_date)");
			sb.append(" VALUES(?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)");

			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, num);
			pstmt.setInt(2, boardComment.getBoard_num());
			pstmt.setString(3, boardComment.getId());
			pstmt.setString(4, boardComment.getPassword());
			pstmt.setString(5, boardComment.getContent());
			pstmt.setInt(6, num); // re_ref == num 주글일때는 글그룹번호와 글번호가 같음
			pstmt.setInt(7, 0); // re_lev 들여쓰기 레벨
			pstmt.setInt(8, 0); // re_seq글그룹 내에서는 오름차순 정렬. 최상단에 주글이 옴
			pstmt.setString(9, boardComment.getIp()); // ip주소

			rowCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}

		return rowCount;
	}

	// insertComment

	public List<BoardComment> getCommentList(int num) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";

		List<BoardComment> list = new ArrayList<>();

		try {
			con = DBUtil.getConnection();

			sql = "SELECT * FROM board_comment where board_num = ? ORDER BY re_ref DESC, re_seq ASC";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardComment bc = new BoardComment();

				bc.setNum(rs.getInt("num"));
				bc.setBoard_num(rs.getInt("board_num"));
				bc.setId(rs.getString("id"));
				bc.setPassword(rs.getString("password"));
				bc.setContent(rs.getString("content"));
				bc.setRe_ref(rs.getInt("re_ref"));
				bc.setRe_lev(rs.getInt("re_lev"));
				bc.setRe_seq(rs.getInt("re_seq"));
				bc.setReg_date(rs.getTimestamp("reg_date"));
				bc.setIp(rs.getString("ip"));

				list.add(bc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}

		return list;
	}//getCommentList
	
	
	public void replyInsert(BoardComment bc) {
		
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// DB연결작업으로 커넥션 객체 가져옴.
			// 가져온 커넥션 객체는 autocmmit(자동커밋)이 설정되어있음
			// 자동커밋을 수동커밋으로 직접 트랜잭션 처리함
			
			con = DBUtil.getConnection();
			// 자동커밋을 수동커밋으로 직접 트랜잭션 처리함!
			con.setAutoCommit(false);
			
			StringBuilder sb = new StringBuilder();
		
			// 1) 기존 답글들의 순서(re_seq) 재배치
			sb.append("UPDATE board_comment ");
			sb.append("SET re_seq = re_seq+1 ");
			sb.append("WHERE re_ref=? ");
			sb.append("AND re_seq > ?"); 
		
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, bc.getRe_ref()); //답글 달 글의 부모글 번호
			pstmt.setInt(2, bc.getRe_seq());// 답글 달 글의 게시판에서 보여지는  순서
			//UPDATE문 실행
			pstmt.executeUpdate(); //commit이 자동처리 안함!!
			
			// 2) 글번호 만들기 
			pstmt.close(); // UPDATE문을 가진 pstmt객체 닫기
			
			sb.setLength(0); //기존 StringBuilder 객체 초기화
			// 최댓값 구하기
			sb.append("SELECT MAX(num) FROM board_comment");
			
			pstmt = con.prepareStatement(sb.toString());
			
			// SELECT문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				// 답글 글번호 생성해서 board 글번호 필드에 저장
				bc.setNum(rs.getInt(1) + 1); 
			}
			
			// 3) 답글 insert
			pstmt.close(); // SELECT문을 가진 pstmt 객체 닫기
			
			sb.setLength(0); //기존 StringBuilder 객체 초기화
			
			sb.append("INSERT INTO board_comment (num, board_num, id, password, content, re_ref, re_lev, re_seq, ip, reg_date)");
			sb.append(" VALUES(?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)");
			// INSERT문을 가진 pstmt 객체 생성
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, bc.getNum());
			pstmt.setInt(2, bc.getBoard_num());
			pstmt.setString(3, bc.getId());
			pstmt.setString(4, bc.getPassword());
			pstmt.setString(5, bc.getContent());
			pstmt.setInt(6, bc.getRe_ref());		// re_ref 는 답글을 다는 대상글의 글 그룹번호와 동일.
			pstmt.setInt(7, bc.getRe_lev() + 1);		// re_lev는 답글을 다는 대상글의 들여쓰기값 +1
			pstmt.setInt(8, bc.getRe_seq() + 1);		// re_seq답글을 다는 대상글의  글그룹 순번 + 1
			pstmt.setString(9, bc.getIp()); // ip주소

			// Insert문 실행
			pstmt.executeUpdate();
			
			// 수동 커밋처리 해줌
			con.commit();
			
			// 자동커밋으로 다시 초기화
			con.setAutoCommit(true);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				// rollback() sql구문에서 이전에 커밋되기전으로 돌려준다.
				con.rollback(); // try블럭 중간에 예외발생시 롤백처리함
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		}//replyInsert(board)
	

}
