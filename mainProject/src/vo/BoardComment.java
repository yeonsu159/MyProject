package vo;

import java.sql.Timestamp;

/*
	CREATE TABLE board_comment(
	num INT PRIMARY KEY,
	board_num INT,
	name VARCHAR(20),		
	password VARCHAR(15),	
	content VARCHAR(2000),	
	re_ref INT,				
	re_lev INT,				
	re_seq INT,				
	reg_date TIMESTAMP, 	
	ip VARCHAR(30)
);
*/

public class BoardComment {
	private int num;			// 댓글 글 번호
	private int board_num;		// 게시판 번호
	private String id;			// 댓글 작성자명
	private String password;	// 댓글 비밀번호	
	private String content;		// 댓글 내용
	private int re_ref;			// 글 그룹번호
	private int re_lev;			// 글 들여쓰기(답글) 레벨
	private int re_seq;			// 글 그룹내에서의 순서
	private Timestamp reg_date;	// 글 등록날짜
	private String ip;			// 작성자 ip주소
	
	public BoardComment() {
		super();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRe_ref() {
		return re_ref;
	}

	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}

	public int getRe_lev() {
		return re_lev;
	}

	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}

	public int getRe_seq() {
		return re_seq;
	}

	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoardComment [num=").append(num).append(", board_num=").append(board_num).append(", id=")
				.append(id).append(", password=").append(password).append(", content=").append(content)
				.append(", re_ref=").append(re_ref).append(", re_lev=").append(re_lev).append(", re_seq=")
				.append(re_seq).append(", reg_date=").append(reg_date).append(", ip=").append(ip).append("]");
		return builder.toString();
	}
	
	
	

}



