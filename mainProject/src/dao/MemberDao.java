package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

import vo.Member;

public class MemberDao {
	
	// ============== 싱글톤 패턴 적용 =================
	private static MemberDao memberDao = new MemberDao();

	public MemberDao() {
	}
	
	public static MemberDao getInstance() {
		return memberDao;
	}
	// ============== 싱글톤 패턴 적용 =================

	public int insertMember(Member member) {
		int insertedRowCount = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		
		try {
			con = DBUtil.getConnection();
			
			sql = "INSERT INTO member (id,password,name,birthday,gender,email,address,tel,reg_date)";
			sql	+=	 "VALUES(?,?,?,?,?,?,?,?,current_TIMESTAMP)";
			pstmt = con.prepareStatement(sql);
			
			System.out.println("연결확인");
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setDate(4, member.getBirthday());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getAddress());
			pstmt.setString(8, member.getTel());
			
			System.out.println("넣기");
			
			insertedRowCount = pstmt.executeUpdate();
			System.out.println("넣기?");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, null);
		}
		
		return insertedRowCount; //1
	}//insertMember
	
	// 아이디 검색
	public int countById(String id){
		int count = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			con = DBUtil.getConnection();
			
			// id가 (? = 입력한 id)인  사람이 몇갠지 나타내는 문구
			sql = "SELECT COUNT(*) FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		return count;
	}//countById
	
	public int loginCheck(String id, String pw) {
		
		int check = -1;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		try {
			con = DBUtil.getConnection();
			
			sql = "SELECT password FROM member WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //아이디 일치
				if(pw.equals(rs.getString("password"))) {
					// 비밀번호 일치
					check = 1;
				} else {
					check = 0;
				}
			} else {
				check = -1; //아이디 불일치
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		return check;
		
	}//loginCheck
	
	// session값을 받았을때 id값이 아니라 name값을 받기위해서
	
	public String selectName(String id) {
		String name = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			StringBuilder sb = new StringBuilder();
			
			sb.append("select name from member where id = ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				name = rs.getString("name");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		return name;
	}//selectName
	
	// 관리자 회원관리
	public List<Member> getAllMembers() {
		List<Member> list = new ArrayList<>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
	
		try {
			con = DBUtil.getConnection();
			sql = "SELECT * FROM member ORDER BY id";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Member member = new Member();
				
				member.setId(rs.getString("id"));
				member.setPassword(rs.getString("password"));
				member.setName(rs.getString("name"));
				member.setBirthday(rs.getDate("birthday"));
				member.setGender(rs.getString("gender"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
				member.setTel(rs.getString("tel"));
				member.setReg_date(rs.getTimestamp("reg_date"));
				
				
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		return list;
	} // getAllMembers()
	
	// 구글 차트 - 관리자
	public JSONArray getCountPerAddress() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder() ;
		
		JSONArray jsonArray = new JSONArray();
		
		JSONArray colNameArray = new JSONArray();
		colNameArray.add("거주지");
		colNameArray.add("인원수");
		
		jsonArray.add(colNameArray);
		try {
			con = DBUtil.getConnection();
			
			sb.append("SELECT SUBSTRING(address, 1, 2) AS addr, COUNT(*) AS cnt ");
			sb.append("FROM member ");
			sb.append("GROUP BY SUBSTRING(address, 1, 2) ");
			sb.append("ORDER BY addr ");
			
			pstmt = con.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JSONArray rowArray = new JSONArray();
				rowArray.add(rs.getString("addr"));
				rowArray.add(rs.getInt("cnt"));
				
				jsonArray.add(rowArray);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		return jsonArray;
		
	}//getCountPerAddress()
	
	// 내 회원정보보기
	public Member getMember(String id){
		Connection con= null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Member member = null;
		
		try {
			con = DBUtil.getConnection();
			
			String sql = "SELECT * FROM member WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setBirthday(rs.getDate("birthday"));
				member.setEmail(rs.getString("email"));
				member.setAddress(rs.getString("address"));
				member.setTel(rs.getString("tel"));
				member.setReg_date(rs.getTimestamp("reg_date"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		return member;
	}//getMember
	
	// 회원정보 일괄처리삭제
	public void batchDelete(String[] ids) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		
		
		try {
			con = DBUtil.getConnection();
			
			con.setAutoCommit(false);
			sql = "DELETE FROM member WHERE id=?";
			
			pstmt = con.prepareStatement(sql);
			
			for(String id : ids) {
				pstmt.setString(1, id);
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			
			con.commit();
			con.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeJDBC(con, pstmt, rs);
		}
		
		
	} //batchDelete
	
}
