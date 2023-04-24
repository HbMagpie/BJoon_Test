package ch17_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.oreilly.servlet.MultipartRequest;	// 파일 업로드
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberMgr {

	private DBConnectionMgr pool;
	private static final String  SAVEFOLDER = "C:/Jsp/myapp/WebContent/ch17_1/fileupload";
	private static final String ENCTYPE = "UTF-8";
	private static int MAXSIZE = 5*1024*1024;
	
	public MemberMgr() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ID 중복확인 
	public boolean checkId(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select id from tblMember where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			flag = pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
		
	// 우편번호 검색
	public Vector<ZipcodeBean> zipcodeRead(String area3) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ZipcodeBean> vlist = new Vector<ZipcodeBean>();
		try {
			con = pool.getConnection();
			sql = "select * from tblZipcode where area3 like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + area3 + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ZipcodeBean bean = new ZipcodeBean();
				bean.setZipcode(rs.getString(1));
				bean.setArea1(rs.getString(2));
				bean.setArea2(rs.getString(3));
				bean.setArea3(rs.getString(4));
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

	//  회원가입
	public boolean insertMember(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert tblMember(id,pwd,lv,sign,name,birthday,email,zipcode"
					+ ",address,hobby,job,part)values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getPwd());
			pstmt.setString(3, bean.getLv());
			pstmt.setString(4, bean.getSign());
			pstmt.setString(5, bean.getName());
			pstmt.setString(6, bean.getBirthday());
			pstmt.setString(7, bean.getEmail());
			pstmt.setString(8, bean.getZipcode());
			pstmt.setString(9, bean.getAddress());
			String hobby[] = bean.getHobby();
			char hb[] = { '0', '0', '0', '0', '0' };
			String lists[] = { "인터넷", "여행", "게임", "영화", "운동" };
			for (int i = 0; i < hobby.length; i++) {
				for (int j = 0; j < lists.length; j++) {
					if (hobby[i].equals(lists[j]))
						hb[j] = '1';
				}
			}
			pstmt.setString(10, new String(hb));
			pstmt.setString(11, bean.getJob());
			pstmt.setString(12, bean.getPart());
			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

	// 로그인
	public boolean loginMember(String id, String pwd) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select id from tblMember where id = ? and pwd = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}
	
	/*ch17 */
	

	// 회원 가입
	public MemberBean getMember(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from tblMember where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new MemberBean();
				bean.setId(rs.getString("id"));
				bean.setPwd(rs.getString("pwd"));
				bean.setLv(rs.getString("lv"));
				bean.setSign(rs.getString("sign"));
				bean.setName(rs.getString("name"));			
				bean.setBirthday(rs.getString("birthday"));
				bean.setEmail(rs.getString("email"));
				bean.setZipcode(rs.getString("zipcode"));
				bean.setAddress(rs.getString("address"));
				String hobbys[] = new String[5];
				String hobby = rs.getString("hobby");// ex.01001
				for (int i = 0; i < hobbys.length; i++) {
					hobbys[i] = hobby.substring(i, i + 1);
				}
				bean.setHobby(hobbys);
				bean.setJob(rs.getString("job"));
				bean.setPart(rs.getString("part"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}

	// 회원 정보 수정
	public boolean updateMember(MemberBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "update tblMember set pwd=?, lv=?, sign=?, name=?, gender=?, birthday=?,"
					+ "email=?, zipcode=?, address=?, hobby=?, job=?, part=? where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getPwd());
			pstmt.setString(2, bean.getLv());	
			pstmt.setString(3, bean.getSign());
			pstmt.setString(4, bean.getName());
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getBirthday());
			pstmt.setString(7, bean.getEmail());
			pstmt.setString(8, bean.getZipcode());
			pstmt.setString(9, bean.getAddress());
			char hobby[] = { '0', '0', '0', '0', '0' };
			if (bean.getHobby() != null) {
				String hobbys[] = bean.getHobby();
				String list[] = { "인터넷", "여행", "게임", "영화", "운동" };
				for (int i = 0; i < hobbys.length; i++) {
					for (int j = 0; j < list.length; j++)
						if (hobbys[i].equals(list[j]))
							hobby[j] = '1';
				}
			}
			pstmt.setString(10, new String(hobby));
			pstmt.setString(11, bean.getJob());
			pstmt.setString(12, bean.getPart());
			pstmt.setString(13, bean.getId());
			int count = pstmt.executeUpdate();
			if (count > 0)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	
	/* 회원관리부분 */
	
	// 총 회원 수
			public int getTotalCount(String keyField, String keyWord) {
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String sql = null;
				int totalCount = 0;
				try {
					con = pool.getConnection();
					if (keyWord.equals("null") || keyWord.equals("")) {
						sql = "select count(id) from tblMember";
						pstmt = con.prepareStatement(sql);
					} else {
						sql = "select count(id) from  tblMember where " + keyField + " like ? ";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, "%" + keyWord + "%");
					}
					rs = pstmt.executeQuery();
					if (rs.next()) {
						totalCount = rs.getInt(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					pool.freeConnection(con, pstmt, rs);
				}
				return totalCount;
			}
			
	// 회원 리스트
		public Vector<MemberBean> getMemberList(String keyField, String keyWord,
				int start, int end) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<MemberBean> vlist = new Vector<MemberBean>();
			try {
				con = pool.getConnection();
				if (keyWord.equals("null") || keyWord.equals("")) {
					sql = "select * from tblMember order by ref desc, pos limit ?, ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
				} else {
					sql = "select * from  tblMember where " + keyField + " like ? ";
					sql += "order by ref desc, pos limit ? , ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%" + keyWord + "%");
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				}
				rs = pstmt.executeQuery();
				while (rs.next()) {
					MemberBean bean = new MemberBean();
					bean.setId(rs.getString("id"));
					bean.setPwd(rs.getString("pwd"));
					bean.setLv(rs.getString("lv"));
					bean.setSign(rs.getString("sign"));
					bean.setName(rs.getString("name"));			
					bean.setBirthday(rs.getString("birthday"));
					bean.setEmail(rs.getString("email"));
					bean.setZipcode(rs.getString("zipcode"));
					bean.setAddress(rs.getString("address"));
					String hobbys[] = new String[5];
					String hobby = rs.getString("hobby");// ex.01001
					for (int i = 0; i < hobbys.length; i++) {
						hobbys[i] = hobby.substring(i, i + 1);
					}
					bean.setHobby(hobbys);
					bean.setJob(rs.getString("job"));
					bean.setPart(rs.getString("part"));
					vlist.add(bean);		
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return vlist;
		}
		
		// 회원 입력
		public void insertMember(HttpServletRequest req) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			MultipartRequest multi = null;
			int filesize = 0;
			String filename = null;
			try {
				con = pool.getConnection();
				sql = "select max(num) from tblMember";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				int ref = 1;
				if (rs.next())
					ref = rs.getInt(1) + 1;
				File file = new File(SAVEFOLDER);
				if (!file.exists())
					file.mkdirs();
				multi = new MultipartRequest(req, SAVEFOLDER,MAXSIZE, ENCTYPE,
						new DefaultFileRenamePolicy());

				if (multi.getFilesystemName("filename") != null) {
					filename = (String) multi.getFilesystemName("filename");
					filesize = (int) multi.getFile("filename").length();
				}
				String content = multi.getParameter("content");
				if (multi.getParameter("contentType").equalsIgnoreCase("TEXT")) {
					content = UtilMgr.replace(content, "<", "&lt;");
				}
				
	
				sql = "insert tblMember(id,pwd,lv,sign,name,birthday,email,zipcode,address,hobby,job,part)";
				sql += "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("id"));
				pstmt.setString(2, multi.getParameter("pwd"));
				pstmt.setString(3, multi.getParameter("lv"));
				pstmt.setString(4, multi.getParameter("sign"));
				pstmt.setString(5, multi.getParameter("name"));
				pstmt.setString(6, multi.getParameter("birthday"));
				pstmt.setString(7, multi.getParameter("email"));
				pstmt.setString(8, multi.getParameter("zipcode"));
				pstmt.setString(9, multi.getParameter("address"));
				pstmt.setString(10, multi.getParameter("hobby"));
				pstmt.setString(11, multi.getParameter("job"));
				pstmt.setString(12, multi.getParameter("part"));
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		}
		
		// 게시물 리턴
		public MemberBean getMember(int num) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			MemberBean bean = new MemberBean();
			try {
				con = pool.getConnection();
				sql = "select * from tblMember where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					bean.setId(rs.getString("id"));
					bean.setPwd(rs.getString("pwd"));
					bean.setLv(rs.getString("lv"));
					bean.setSign(rs.getString("sign"));
					bean.setName(rs.getString("name"));			
					bean.setBirthday(rs.getString("birthday"));
					bean.setEmail(rs.getString("email"));
					bean.setZipcode(rs.getString("zipcode"));
					bean.setAddress(rs.getString("address"));
					String hobbys[] = new String[5];
					String hobby = rs.getString("hobby");// ex.01001
					for (int i = 0; i < hobbys.length; i++) {
						hobbys[i] = hobby.substring(i, i + 1);
					}
					bean.setHobby(hobbys);
					bean.setJob(rs.getString("job"));
					bean.setPart(rs.getString("part"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return bean;
		}

		// 조회수 증가
		public void upCount(int num) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				con = pool.getConnection();
				sql = "update tblMember set count=count+1 where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
		}

		// 게시물 삭제
		public void deleteMember(int num) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			ResultSet rs = null;
			try {
				con = pool.getConnection();
				sql = "select filename from tblMember where id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if (rs.next() && rs.getString(1) != null) {
					if (!rs.getString(1).equals("")) {
						File file = new File(SAVEFOLDER + "/" + rs.getString(1));
						if (file.exists())
							UtilMgr.delete(SAVEFOLDER + "/" + rs.getString(1));
					}
				}
				sql = "delete from tblMember where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		}

		// 게시물 수정
		public void updateMemberMG(MemberBean bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				con = pool.getConnection();
				sql = "update tblMember set pwd=?, lv=?, sign=?, name=?,"
						+ "birthday=?, email=?, zipcode=?,address=?,hobby=?,job=?,part=? where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getPwd());
				pstmt.setString(2, bean.getLv());
				pstmt.setString(3, bean.getSign());
				pstmt.setString(4, bean.getName());
				pstmt.setString(5, bean.getBirthday());
				pstmt.setString(6, bean.getEmail());
				pstmt.setString(7, bean.getZipcode());
				pstmt.setString(8, bean.getAddress());
				char hobby[] = { '0', '0', '0', '0', '0' };
				if (bean.getHobby() != null) {
					String hobbys[] = bean.getHobby();
					String list[] = { "인터넷", "여행", "게임", "영화", "운동" };
					for (int i = 0; i < hobbys.length; i++) {
						for (int j = 0; j < list.length; j++)
							if (hobbys[i].equals(list[j]))
								hobby[j] = '1';
					}
				}
				pstmt.setString(9, new String(hobby));
				pstmt.setString(10, bean.getJob());
				pstmt.setString(11, bean.getPart());
				pstmt.setString(12, bean.getId());
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
		}

		/*// 게시물 답변
		public void replyMember(MemberBean bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				con = pool.getConnection();
				sql = "insert tblMember (name,content,subject,ref,pos,depth,regdate,pass,count,ip)";
				sql += "values(?,?,?,?,?,?,now(),?,0,?)";
				int depth = bean.getDepth() + 1;
				int pos = bean.getPos() + 1;
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getName());
				pstmt.setString(2, bean.getContent());
				pstmt.setString(3, bean.getSubject());
				pstmt.setInt(4, bean.getRef());
				pstmt.setInt(5, pos);
				pstmt.setInt(6, depth);
				pstmt.setString(7, bean.getPass());
				pstmt.setString(8, bean.getIp());
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
		}

		// 답변에 위치값 증가
		public void replyUpMember(int ref, int pos) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				con = pool.getConnection();
				sql = "update tblMember set pos = pos + 1 where ref=? and pos > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, pos);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
		}

		// 파일 다운로드
			public void downLoad(HttpServletRequest req, HttpServletResponse res,
					JspWriter out, PageContext pageContext) {
				try {
					String filename = req.getParameter("filename");
					File file = new File(UtilMgr.con(SAVEFOLDER + File.separator+ filename));
					byte b[] = new byte[(int) file.length()];
					res.setHeader("Accept-Ranges", "bytes");
					String strClient = req.getHeader("User-Agent");
					if (strClient.indexOf("MSIE6.0") != -1) {
						res.setContentType("application/smnet;charset=euc-kr");
						res.setHeader("Content-Disposition", "filename=" + filename + ";");
					} else {
						res.setContentType("application/smnet;charset=euc-kr");
						res.setHeader("Content-Disposition", "attachment;filename="+ filename + ";");
					}
					out.clear();
					out = pageContext.pushBody();
					if (file.isFile()) {
						BufferedInputStream fin = new BufferedInputStream(
								new FileInputStream(file));
						BufferedOutputStream outs = new BufferedOutputStream(
								res.getOutputStream());
						int read = 0;
						while ((read = fin.read(b)) != -1) {
							outs.write(b, 0, read);
						}
						outs.close();
						fin.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		// 페이징 및 블럭 테스트를 위한 게시물 저장 메소드
		public void post1000(){
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			try {
				con = pool.getConnection();
				sql = "insert tblMember(name,content,subject,ref,pos,depth,regdate,pass,count,ip,filename,filesize)";
				sql+="values('aaa', 'bbb', 'ccc', 0, 0, 0, now(), '1111',0, '127.0.0.1', null, 0);";
				pstmt = con.prepareStatement(sql);
				for (int i = 0; i < 1000; i++) {
					pstmt.executeUpdate();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
		}
		
		//main
		public static void main(String[] args) {
			new MemberMgr().post1000();
			System.out.println("SUCCESS");
		}*/
}