package vo;

import java.sql.Timestamp;

/*
 	CREATE TABLE board(
		num INT PRIMARY KEY,
		name VARCHAR(20),
		password VARCHAR(15),
		subject VARCHAR(50),
		content VARCHAR(2000),
		filename VARCHAR(50),
		re_ref INT,
		re_lev INT,
		re_seq INT,
		readcount INT,
		reg_date TIMESTAMP, 
		ip VARCHAR(30)
	);
*/

public class Board {

	private int num;			// 글 번호
	private String name;		// 작성자명
	private String password;	// 글 비밀번호	
	private String subject;		// 글 제목
	private String content;		// 글 내용
	private String filename;	// 업로드한 파일명
	private int re_ref;			// 글 그룹번호
	private int re_lev;			// 글 들여쓰기(답글) 레벨
	private int re_seq;			// 글 그룹내에서의 순서
	private int readcount;		// 조회수
	private Timestamp reg_date;	// 글 등록날짜
	private String ip;			// 작성자 ip주소
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
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
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
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
		builder.append("Board [num=").append(num).append(", name=").append(name).append(", password=").append(password)
				.append(", subject=").append(subject).append(", content=").append(content).append(", filename=")
				.append(filename).append(", re_ref=").append(re_ref).append(", re_lev=").append(re_lev)
				.append(", re_seq=").append(re_seq).append(", readcount=").append(readcount).append(", reg_date=")
				.append(reg_date).append(", ip=").append(ip).append("]");
		return builder.toString();
	}
	
	
	
}
