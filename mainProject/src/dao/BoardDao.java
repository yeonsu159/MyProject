package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.Board;

public class BoardDao {

	// ============== 싱글톤 패턴 적용 =================
	private static BoardDao boardDao = new BoardDao();
	
	private BoardDao() {
		
	}
	
	public static BoardDao getInstance() {
		return boardDao;
	}
	// ============== 싱글톤 패턴 적용 =================
	
	public int insert(Board board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rowCount = 0; // INSERT 된 행
		
		try {
			con = DBUtil.getConnection();
			
			int num = 0;
			
			StringBuilder sb = new StringBuilder();
			//board테이블에서 num의 최대값을 출력
			sb.append("SELECT MAX(num) FROM board");
			
			pstmt = con.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			// rs 데이터 있으면 num = 최대값 + 1;
			//		      없으면 num = 1
			 if(rs.next()) {
				 num = rs.getInt(1) + 1;
			 } else {
				 num = 1;
			 }
			 
			 sb.setLength(0); // StringBuilder문 내용 삭제 , 재사용
			 pstmt.close();// 앞에 사용했던 select문 닫기
			 
			 // 글쓰기 작업
			 sb.append("INSERT INTO board (num, name, password, subject, content, filename, re_ref, re_lev, re_seq, readcount, ip, reg_date)");
			 sb.append(" VALUES(?,?,?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP)");
			 
			 pstmt = con.prepareStatement(sb.toString());
			 pstmt.setInt(1, num);
			 pstmt.setString(2, board.getName());
			 pstmt.setString(3, board.getPassword());
			 pstmt.setString(4, board.getSubject());
			 pstmt.setString(5, board.getContent());
			 pstmt.setString(6, board.getFilename());
			 pstmt.setInt(7, num);	// re_ref == num 주글일때는 글그룹번호와 글번호가 같음
			 pstmt.setInt(8, 0);		// re_lev 들여쓰기 레벨
			 pstmt.setInt(9, 0);		// re_seq글그룹 내에서는 오름차순 정렬. 최상단에 주글이 옴
			 pstmt.setInt(10, 0); // readcount 조회수
			 pstmt.setString(11, board.getIp()); // ip주소
			
			// 실행
			rowCount = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		return rowCount;
	}//insert
	
	// 글목록 데이터를 list로 가져오기
	public List<Board> getBoards(int startRow, int pageSize, String search){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Board> list = new ArrayList<>();
		
		try {
			con = DBUtil.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT * ");
			sb.append("FROM board ");
			if(search != null && !search.equals("")) {
				sb.append("WHERE subject LIKE ? ");
			}
			sb.append("ORDER BY re_ref DESC, re_seq ASC ");
			sb.append("OFFSET ? LIMIT ? ");
			pstmt = con.prepareStatement(sb.toString());
			
			// 검색어가 있을때
			if(search != null && !search.equals("")) { 
				pstmt.setString(1, "%"+search+"%");
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, pageSize);
			} else {
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, pageSize);
			}
			
			rs = pstmt.executeQuery();
			// 레코드(행) 갯수만큼 반복
			while(rs.next()) {
				Board board = new Board();
				board.setNum(rs.getInt("num"));
				board.setName(rs.getString("name"));
				board.setPassword(rs.getString("password"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setFilename(rs.getString("fileName"));
				board.setRe_lev(rs.getInt("re_ref"));
				board.setRe_lev(rs.getInt("re_lev"));
				board.setRe_seq(rs.getInt("re_seq"));
				board.setReadcount(rs.getInt("readcount"));
				board.setIp(rs.getString("ip"));
				board.setReg_date(rs.getTimestamp("reg_date"));
				
				list.add(board); // 레코드(행) 한 개를 리스트에 추가
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		return list;
	}//getBoards
	
	// board 테이블 전체 레코드(행) 갯수 구하기
		public int getBoardCount() {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int count = 0;
			
			try {
				con = DBUtil.getConnection();
				
				pstmt = con.prepareStatement("SELECT COUNT(*) FROM board");
				
				// 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeJDBC(con, pstmt, rs);
			}
			
			return count;
		}//getBoardCount()
		
		// board 테이블 전체 레코드(행) 갯수 구하기
		// 검색어가 적용된 행 구하기
		public int getBoardCount(String search) {// 메소드 오버로딩
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int count = 0;
			
			try {
				con = DBUtil.getConnection();
				
				pstmt = con.prepareStatement("SELECT COUNT(*) FROM board WHERE subject LIKE ?");
				pstmt.setString(1, "%"+search+"%");
				// 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBUtil.closeJDBC(con, pstmt, rs);
			}
			
			return count;
		}//getBoardCount()
	
	// 조회수 증가하기
	public void updateReadcount(int num){
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			
			String sql = "UPDATE board SET readcount = readcount + 1 WHERE num=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		
	}//updateReadcount
	
	// 글번호에 해당하는 글 전체(상세)내용 가져오기
	public Board getBoard(int num){
		
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Board board = null;
		
		try {
			con = DBUtil.getConnection();
			
			String sql = "SELECT * FROM board WHERE num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				board.setNum(rs.getInt("num"));
				board.setName(rs.getString("name"));
				board.setPassword(rs.getString("password"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setFilename(rs.getString("filename"));
				board.setRe_ref(rs.getInt("re_ref"));
				board.setRe_lev(rs.getInt("re_lev"));
				board.setRe_seq(rs.getInt("re_seq"));
				board.setReadcount(rs.getInt("readcount"));
				board.setIp(rs.getString("ip"));
				board.setReg_date(rs.getTimestamp("reg_date"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		return board;
	}//getBoard
	
	// 글  비밀번호 체크
	public boolean checkBoard(int num, String pw){
		
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean result = false;
		
		try {
			con = DBUtil.getConnection();
			
			String sql = "SELECT password FROM board WHERE num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			// DB에있는 비밀번호랑 글 수정시 비밀번호가 맞는 지 check
			if(rs.next()) {
				if(pw.equals(rs.getString("password"))) {
					result = true;
				} else {
					result = false;
				}
			} //if문
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		return result;
	}//checkBoard
	
	// 글 수정
	public boolean updateBoard(Board board){
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean isSuccess = false;
		String sql = "";
		
		try {
			con = DBUtil.getConnection();
			
			sql = "SELECT password FROM board WHERE num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board.getNum());
			
			rs = pstmt.executeQuery();
			// rs 데이터 있으면 글 비밀번호 비교
			//	    맞으면 update문 수행, isSuccess = true
			//	    틀리면 isSuccess = false
			
			if(rs.next()) {
				if(board.getPassword().equals(rs.getString("password"))) {
					rs.close();
					pstmt.close();
					
					sql = "UPDATE board ";
					sql += "SET name=?, subject=?, content=?, filename=? ";
					sql += "WHERE num=? ";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, board.getName());
					pstmt.setString(2, board.getSubject());
					pstmt.setString(3, board.getContent());
					pstmt.setString(4, board.getFilename());
					pstmt.setInt(5, board.getNum());
					
					pstmt.executeUpdate();
					
					isSuccess = true;
					
				} else {
					isSuccess = false;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		return isSuccess;
	}//updateBoard
	
	// 게시판 삭제하기
	public boolean deleteBoard(int num, String pw){
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		boolean isSuccess = false;
		
		try {
			con = DBUtil.getConnection();
			
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT password FROM board WHERE num = ? ");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			// rs 데이터 있으면 글 비밀번호 비교
			//	    맞으면 delete문 수행, isSuccess = true
			//	    틀리면 isSuccess = false
			
			if(rs.next()) {
				if(pw.equals(rs.getString("password"))) {
					sb.setLength(0);
					pstmt.close();
					sb.append("DELETE FROM board WHERE num = ? ");
					pstmt = con.prepareStatement(sb.toString());
					pstmt.setInt(1, num);
					
					pstmt.executeUpdate();
					
					sb.setLength(0);
					pstmt.close();
					
					sb.append("DELETE FROM board_comment WHERE board_num = ? ");
					pstmt = con.prepareStatement(sb.toString());
					pstmt.setInt(1, num);
					
					pstmt.executeUpdate();
					
					
					isSuccess = true;
					
					
				} else {
					isSuccess = false;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		return isSuccess;
	}//deleteBoard
	
	
}
