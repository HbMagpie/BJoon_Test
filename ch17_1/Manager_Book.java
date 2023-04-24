package ch17_1;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;


public class Manager_Book {

	private DBConnectionMgr pool;

	public Manager_Book() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 공통 정보 ===============================================================================
	// ID 중복 체크
	public boolean checkId(String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select usid from chul_admin where usid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usid);
			flag = pstmt.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}

	// 우편번호 찾기
	public Vector<Bean_Postcode> zipcodeRead(String area3) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Postcode> vlist = new Vector<Bean_Postcode>();
		try {
			con = pool.getConnection();
			sql = "select * from chul_post where area3 like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + area3 + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Postcode bean = new Bean_Postcode();
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

	// 로그인
	public boolean loginMember(String usid, String uspw) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select usid from chul_admin where usid=? and uspw=? and stat='승인' ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usid);
			pstmt.setString(2, uspw);
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return flag;
	}

	// 패스워드 암호화(임시)
	public void getPass(int numb, String pass_w) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "update chul_admin set pass=SHA2('" + pass_w + "',256) where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	// 회원 정보 ===============================================================================
	// 회원 추가
	public boolean insertMember(Bean_Admin bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert chul_admin(usid,uspw,gubn,stat,name,telp,gend,brth,mail,post"
					+ ",addr,hobb,jobb)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsid());
			pstmt.setString(2, bean.getUspw());
			pstmt.setString(3, bean.getGubn());
			pstmt.setString(4, bean.getStat());
			pstmt.setString(5, bean.getName());
			pstmt.setString(6, bean.getTelp());
			pstmt.setString(7, bean.getGend());
			pstmt.setString(8, bean.getBrth());
			pstmt.setString(9, bean.getMail());
			pstmt.setString(10, bean.getPost());
			pstmt.setString(11, bean.getAddr());
			String hobby[] = bean.getHobb();
			char hb[] = { '0', '0', '0', '0', '0' };
			String lists[] = { "독서", "등산", "낚시", "여행", "영화" };
			for (int i = 0; i < hobby.length; i++) {
				for (int j = 0; j < lists.length; j++) {
					if (hobby[i].equals(lists[j]))
						hb[j] = '1';
				}
			}
			pstmt.setString(12, new String(hb));
			pstmt.setString(13, bean.getJobb());
			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

	// 회원 조회 (usid)
		public Bean_Admin getMember(String usid) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Bean_Admin bean = null;
			try {
				con = pool.getConnection();
				String sql = "select * from chul_admin where usid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, usid);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					bean = new Bean_Admin();
					bean.setNumb(rs.getInt("numb"));
					bean.setUsid(rs.getString("usid"));
					bean.setUspw(rs.getString("uspw"));
					bean.setGubn(rs.getString("gubn"));
					bean.setStat(rs.getString("stat"));
					bean.setName(rs.getString("name"));
					bean.setTelp(rs.getString("telp"));
					bean.setGend(rs.getString("gend"));
					bean.setBrth(rs.getString("brth"));
					bean.setMail(rs.getString("mail"));
					bean.setPost(rs.getString("post"));
					bean.setAddr(rs.getString("addr"));
					String hobbys[] = new String[5];
					String hobb = rs.getString("hobb");// 01001
					for (int i = 0; i < hobbys.length; i++) {
						hobbys[i] = hobb.substring(i, i + 1);
					}
					bean.setHobb(hobbys);
					bean.setJobb(rs.getString("jobb"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con);
			}
			return bean;
		}

		// 회원 조회 (numb)
		public Bean_Admin getMember2(int numb) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Bean_Admin bean = null;
			try {
				con = pool.getConnection();
				String sql = "select * from chul_admin where numb = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, numb);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					bean = new Bean_Admin();
					bean.setNumb(rs.getInt("numb"));
					bean.setUsid(rs.getString("usid"));
					bean.setUspw(rs.getString("uspw"));
					bean.setGubn(rs.getString("gubn"));
					bean.setStat(rs.getString("stat"));
					bean.setName(rs.getString("name"));
					bean.setTelp(rs.getString("telp"));
					bean.setGend(rs.getString("gend"));
					bean.setBrth(rs.getString("brth"));
					bean.setMail(rs.getString("mail"));
					bean.setPost(rs.getString("post"));
					bean.setAddr(rs.getString("addr"));
					String hobbys[] = new String[5];
					String hobb = rs.getString("hobb");// 01001
					for (int i = 0; i < hobbys.length; i++) {
						hobbys[i] = hobb.substring(i, i + 1);
					}
					bean.setHobb(hobbys);
					bean.setJobb(rs.getString("jobb"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con);
			}
			return bean;
		}
	
		// 회원 조회 (numb)
		public Bean_Admin getMember3(String usid, int numb) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Bean_Admin bean = null;
			try {
				con = pool.getConnection();
				if (numb > 0) {
					String sql = "select * from chul_admin where numb = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, numb);
				} else {
					String sql = "select * from chul_admin where usid = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, usid);
				}
				
				rs = pstmt.executeQuery();
				if (rs.next()) {
					bean = new Bean_Admin();
					bean.setNumb(rs.getInt("numb"));
					bean.setUsid(rs.getString("usid"));
					bean.setUspw(rs.getString("uspw"));
					bean.setGubn(rs.getString("gubn"));
					bean.setStat(rs.getString("stat"));
					bean.setName(rs.getString("name"));
					bean.setTelp(rs.getString("telp"));
					bean.setGend(rs.getString("gend"));
					bean.setBrth(rs.getString("brth"));
					bean.setMail(rs.getString("mail"));
					bean.setPost(rs.getString("post"));
					bean.setAddr(rs.getString("addr"));
					String hobbys[] = new String[5];
					String hobb = rs.getString("hobb");// 01001
					for (int i = 0; i < hobbys.length; i++) {
						hobbys[i] = hobb.substring(i, i + 1);
					}
					bean.setHobb(hobbys);
					bean.setJobb(rs.getString("jobb"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con);
			}
			return bean;
		}

		// 회원 정보 수정
		public boolean updateMember(Bean_Admin bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				String sql = "update chul_admin set uspw=?, gubn=?, stat=?, name=?, telp=?, gend=?, brth=?,"
						+ "mail=?, post=?, addr=?, hobb=?, jobb=? where usid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getUspw());
				pstmt.setString(2, bean.getGubn());
				pstmt.setString(3, bean.getStat());
				pstmt.setString(4, bean.getName());
				pstmt.setString(5, bean.getTelp());
				pstmt.setString(6, bean.getGend());
				pstmt.setString(7, bean.getBrth());
				pstmt.setString(8, bean.getMail());
				pstmt.setString(9, bean.getPost());
				pstmt.setString(10, bean.getAddr());
				char hobby[] = { '0', '0', '0', '0', '0' };
				if (bean.getHobb() != null) {
					String hobbys[] = bean.getHobb();
					String lists[] = { "독서", "등산", "낚시", "여행", "영화" };
					for (int i = 0; i < hobbys.length; i++) {
						for (int j = 0; j < lists.length; j++)
							if (hobbys[i].equals(lists[j]))
								hobby[j] = '1';
					}
				}
				pstmt.setString(11, new String(hobby));
				pstmt.setString(12, bean.getJobb());
				pstmt.setString(13, bean.getUsid());
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
	
		// 회원 총 수
		public int getTotalCount(String gubn, String check) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int totalCount = 0;
			
			try {
				con = pool.getConnection();
				if ( (gubn.equals("S")) && (check.equals("N")) ) {
				       sql = "select count(numb) from chul_admin where gubn='S' or gubn='A' ";
					   pstmt = con.prepareStatement(sql);
					} else if ( (gubn.equals("S")) && (check.equals("Y")) ) {
					   sql = "select count(numb) from chul_admin where gubn='S' or gubn='A' ";
					   pstmt = con.prepareStatement(sql);
					} else if ( (gubn.equals("S")) && (check.equals("J")) ) {
						   sql = "select count(numb) from chul_admin where gubn='S' or gubn='A' ";
						   pstmt = con.prepareStatement(sql);
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

		// 해당 조건의 회원 조회
		public Vector<Bean_Admin> getMemberList(String gubn, String check) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<Bean_Admin> vlist = new Vector<Bean_Admin>();
			//System.out.println(check);
			try {
				con = pool.getConnection();
				if ( (gubn.equals("S")) && (check.equals("N")) ) {
			       sql = "select * from chul_admin where gubn='S' or gubn='A' ";
				   pstmt = con.prepareStatement(sql);
				} else if ( (gubn.equals("S")) && (check.equals("Y")) ) {
				   sql = "select * from chul_admin where stat='미승인' and (gubn='S' or gubn='A') ";
				   pstmt = con.prepareStatement(sql);
				} else if ( (gubn.equals("S")) && (check.equals("J")) ) {
				   sql = "select * from chul_admin where stat='승인' and (gubn='S' or gubn='A') ";
				   pstmt = con.prepareStatement(sql);
				}
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Bean_Admin bean = new Bean_Admin();
					bean.setNumb(rs.getInt("numb"));
					bean.setUsid(rs.getString("usid"));
					bean.setUspw(rs.getString("uspw"));
					bean.setGubn(rs.getString("gubn"));
					bean.setStat(rs.getString("stat"));
					bean.setName(rs.getString("name"));
					bean.setTelp(rs.getString("telp"));
					vlist.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return vlist;
		}
	
		// 회원 정보 삭제
		public void deleteMember(int numb) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			ResultSet rs = null;
			try {
				con = pool.getConnection();
				sql = "delete from chul_admin where numb=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, numb);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		}

		// 회원 승인 여부 
		public boolean updatePerm(int recnum, String perm) {
			Connection con = null;
			PreparedStatement pstmt = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				if (perm.equals("미승인")) {
					String sql = "update chul_admin set stat='승인' where numb=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, recnum);
				} else {
					String sql = "update chul_admin set stat='미승인' where numb=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, recnum);
				}
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

		// 코드 정보 ===============================================================================
		// 코드 내역 아이디 조건으로 조회
		public Vector<Bean_Code> getCodeList(String check) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<Bean_Code> vlist = new Vector<Bean_Code>();
			try {
				con = pool.getConnection();
				if (check.equals("N")) {
					sql = "select * from chul_code";
				    pstmt = con.prepareStatement(sql);
				} else if (check.equals("Sm")) {
					sql = "select * from chul_code where substr(code,1,1)='1' and substr(code,5,1)!='0' ";
				    pstmt = con.prepareStatement(sql);
				} else if (check.equals("Jm")) {
					sql = "select * from chul_code where substr(code,1,1)='2' and substr(code,5,1)!='0' ";
				    pstmt = con.prepareStatement(sql);
				} else if (check.equals("Hm")) {
					sql = "select * from chul_code where substr(code,1,1)='3' and substr(code,5,1)!='0' ";
				    pstmt = con.prepareStatement(sql);
				} else if (check.equals("Cm")) {
					sql = "select * from chul_code where substr(code,1,1)='4' and substr(code,5,1)!='0' ";
				    pstmt = con.prepareStatement(sql);
				} else if (check.equals("Bm")) {
					sql = "select * from chul_code where substr(code,1,1)='5' and substr(code,5,1)!='0' ";
				    pstmt = con.prepareStatement(sql);
				} else if (check.equals("J")) {
					sql = "select * from chul_code where substr(code,1,1)='6' and substr(code,5,1)!='0' ";
				    pstmt = con.prepareStatement(sql);
				}
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Bean_Code bean = new Bean_Code();
					bean.setNumb(rs.getInt("numb"));
					bean.setUsid(rs.getString("usid"));
					bean.setCode(rs.getString("code"));
					bean.setGubn(rs.getString("gubn"));
					vlist.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return vlist;
		}

		// 코드 내역 신규 추가
		public boolean Code_Insert(Bean_Code bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "insert chul_code(usid,code,gubn)	values (?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getUsid());
				pstmt.setString(2, bean.getCode());
				pstmt.setString(3, bean.getGubn());
				if (pstmt.executeUpdate() == 1)
					flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flag;
		}
		
		// 코드 자료 수
		public int getCodeCount(String check, String keyWord, String keyField, String usid) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int totalCount = 0;
			try {
				con = pool.getConnection();
				if (check.equals("S")) {
					sql = "select count(numb) from chul_code ";
				    pstmt = con.prepareStatement(sql);
				} else if (check.equals("A")) {
					sql = "select count(numb) from chul_code where usid=? ";
				    pstmt = con.prepareStatement(sql);
					pstmt.setString(1, usid);
				} else if (check.equals("B")) {
					sql = "select count(numb) from chul_code where "  + keyField + " like ? ";
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
		
		// 코드 내역 아이디 조건으로 조회
		public Vector<Bean_Code> getCodeList(String check, String keyWord, String keyField, String usid) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<Bean_Code> vlist = new Vector<Bean_Code>();
			try {
				con = pool.getConnection();
				if (check.equals("S")) {
					sql = "select * from chul_code ";
				    pstmt = con.prepareStatement(sql);
				} else if (check.equals("A")) {
					sql = "select * from chul_code where usid=? ";
				    pstmt = con.prepareStatement(sql);
					pstmt.setString(1, usid);
				} else if (check.equals("B")) {
					sql = "select * from chul_code where "  + keyField + " like ? ";
				    pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%" + keyWord + "%");
				}
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Bean_Code bean = new Bean_Code();
					bean.setNumb(rs.getInt("numb"));
					bean.setUsid(rs.getString("usid"));
					bean.setCode(rs.getString("code"));
					bean.setGubn(rs.getString("gubn"));
					vlist.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return vlist;
		}

		// 코드 등록 내역 조회 (numb)
		public Bean_Code getCode1(int numb) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Bean_Code bean = null;
			try {
				con = pool.getConnection();
				String sql = "select * from chul_code where numb = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, numb);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					bean = new Bean_Code();
					bean.setNumb(rs.getInt("numb"));
					bean.setUsid(rs.getString("usid"));
					bean.setCode(rs.getString("code"));
					bean.setGubn(rs.getString("gubn"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con);
			}
			return bean;
		}

		// 코드 내역 수정
		public boolean updateCode(Bean_Code bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "update chul_code set usid=?, code=?, gubn=? where numb=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getUsid());
				pstmt.setString(2, bean.getCode());
				pstmt.setString(3, bean.getGubn());
				pstmt.setInt(4, bean.getNumb());
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
		
		// 코드 정보 삭제
		public void deleteCode(int numb) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			ResultSet rs = null;
			try {
				con = pool.getConnection();
				sql = "delete from chul_code where numb=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, numb);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		}
		
		// 코드 내역 아이디 조건으로 조회
		public Vector<Bean_Code> getKeyWord(String keyField, String usid) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<Bean_Code> vlist = new Vector<Bean_Code>();
			try {
				con = pool.getConnection();
				if (keyField.equals("s_menu")) {
					sql = "select * from chul_code where substr(code,1,1)='1' and substr(code,5,1)!='0' ";
				    pstmt = con.prepareStatement(sql);
				} else if (keyField.equals("j_menu")) {
					sql = "select * from chul_code where substr(code,1,1)='2' and substr(code,5,1)!='0' ";
				    pstmt = con.prepareStatement(sql);
				} else if (keyField.equals("h_menu")) {
					sql = "select * from chul_code where substr(code,1,1)='3' and substr(code,5,1)!='0' ";
				    pstmt = con.prepareStatement(sql);
				} else if (keyField.equals("c_menu")) {
					sql = "select * from chul_code where substr(code,1,1)='4' and substr(code,5,1)!='0' ";
				    pstmt = con.prepareStatement(sql);
				} else if (keyField.equals("b_menu")) {
					sql = "select * from chul_code where substr(code,1,1)='5' and substr(code,5,1)!='0' ";
				    pstmt = con.prepareStatement(sql);
				}
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Bean_Code bean = new Bean_Code();
					bean.setNumb(rs.getInt("numb"));
					bean.setUsid(rs.getString("usid"));
					bean.setCode(rs.getString("code"));
					bean.setGubn(rs.getString("gubn"));
					vlist.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return vlist;
		}
	
	// 도서 정보 ===============================================================================
	// 도서 자료 수
	public int getBookCount(String check, String keyWord, String keyField) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = pool.getConnection();
			if (check.equals("N")) {
				sql = "select count(numb) from book_booklist";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("J")) {
				sql = "select count(numb) from book_booklist where b_state = '대출중' ";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("G")) {
				sql = "select count(numb) from book_booklist where b_state = '대출가능' ";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("S")) {
				sql = "select count(numb) from book_booklist where "  + keyField + " like ? ";
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
	
	// 도서 내역 아이디 조건으로 조회
	public Vector<Bean_Booklist> getBookList(String check, String keyWord, String keyField) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Booklist> vlist = new Vector<Bean_Booklist>();
		try {
			con = pool.getConnection();
			if (check.equals("N")) {
				sql = "select * from book_booklist";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("J")) {
				sql = "select * from book_booklist where b_state = '대출중' ";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("G")) {
				sql = "select * from book_booklist where b_state = '대출가능' ";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("S")) {
				sql = "select * from book_booklist where "  + keyField + " like ? ";
			    pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + keyWord + "%");
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Booklist bean = new Bean_Booklist();
				bean.setNumb(rs.getInt("numb"));
				bean.setIsbn(rs.getString("isbn"));
				bean.setBookname(rs.getString("bookname"));
				bean.setAuthor(rs.getString("author"));
				bean.setChulpan(rs.getString("chulpan"));
				bean.setBookyear(rs.getString("bookyear"));
				bean.setPage(rs.getString("page"));
				bean.setPrice(rs.getString("price"));
				bean.setB_state(rs.getString("b_state"));
				bean.setBigo(rs.getString("bigo"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

	// 도서 내역 신규 추가
	public boolean Book_Insert(Bean_Booklist bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert book_booklist(isbn,bookname,author,chulpan,bookyear"
					+ ",page,price,b_state, bigo)values(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getIsbn());
			pstmt.setString(2, bean.getBookname());
			pstmt.setString(3, bean.getAuthor());
			pstmt.setString(4, bean.getChulpan());
			pstmt.setString(5, bean.getBookyear());
			pstmt.setString(6, bean.getPage());
			pstmt.setString(7, bean.getPrice());
			pstmt.setString(8, bean.getB_state());
			pstmt.setString(9, bean.getBigo());
			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// 도서 등록 내역 조회 (numb)
	public Bean_Booklist getBook1(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bean_Booklist bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from book_booklist where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Booklist();
				bean.setNumb(rs.getInt("numb"));
				bean.setIsbn(rs.getString("isbn"));
				bean.setBookname(rs.getString("bookname"));
				bean.setAuthor(rs.getString("author"));
				bean.setChulpan(rs.getString("chulpan"));
				bean.setBookyear(rs.getString("bookyear"));
				bean.setPage(rs.getString("page"));
				bean.setPrice(rs.getString("price"));
				bean.setB_state(rs.getString("b_state"));
				bean.setBigo(rs.getString("bigo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}

	// 도서 내역 수정
	public boolean updateBook(Bean_Booklist bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update book_booklist set isbn=?, bookname=?, author=?, "
					     + "chulpan=?, bookyear=?, page=?, price=?, b_state=?, "
	   				     + "bigo=? where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getIsbn());
			pstmt.setString(2, bean.getBookname());
			pstmt.setString(3, bean.getAuthor());
			pstmt.setString(4, bean.getChulpan());
			pstmt.setString(5, bean.getBookyear());
			pstmt.setString(6, bean.getPage());
			pstmt.setString(7, bean.getPrice());
			pstmt.setString(8, bean.getB_state());
			pstmt.setString(9, bean.getBigo());
			pstmt.setInt(10, bean.getNumb());
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
	
	// 도서 정보 삭제
	public void deleteBook(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "delete from book_booklist where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	// 대출 정보 ===============================================================================
	// 도서 대출 자료 수
	public int getDaeCount(String gubn, String usid, String check) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = pool.getConnection();
			if ( (gubn.equals("S")) && (check.equals("N")) ){
			       sql = "select count(numb) from book_daechul";
				   pstmt = con.prepareStatement(sql);
				} else if ( (gubn.equals("S")) && (check.equals("Y")) ){
				   sql = "select count(numb) from book_daechul where nalsu > 0";
				   pstmt = con.prepareStatement(sql);
				} else if ( (gubn.equals("S")) && (check.equals("M")) ){
					   sql = "select count(numb) from book_daechul where b_date is null";
					   pstmt = con.prepareStatement(sql);
				} else if ( (gubn.equals("S")) && (check.equals("B")) ){
					   sql = "select count(numb) from book_daechul where b_date is not null";
					   pstmt = con.prepareStatement(sql);
				} else if (gubn.equals("A")) {
					   sql = "select count(numb) from book_daechul where usid=?";
					   pstmt = con.prepareStatement(sql);
					   pstmt.setString(1, usid);
				} else if (gubn.equals("B")) {
					   sql = "select count(numb) from book_daechul where usid=?";
					   pstmt = con.prepareStatement(sql);
					   pstmt.setString(1, usid);
				} else {
					   sql = "select count(numb) from book_daechul where gubn=?";
					   pstmt = con.prepareStatement(sql);
					   pstmt.setString(1, "NULL");
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

	// 도서 대출 내역 아이디 조건으로 조회
	public Vector<Bean_Daechul> getDaeList(String gubn, String usid, String check) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Daechul> vlist = new Vector<Bean_Daechul>();
		try {
			con = pool.getConnection();
			if ( (gubn.equals("S")) && (check.equals("N")) ){
			    sql = "select * from book_daechul";
				pstmt = con.prepareStatement(sql);
			} else if ( (gubn.equals("S")) && (check.equals("Y")) ){
				sql = "select * from book_daechul where nalsu > 0";
				pstmt = con.prepareStatement(sql);
			} else if ( (gubn.equals("S")) && (check.equals("M")) ){
				sql = "select * from book_daechul where b_date is null";
				pstmt = con.prepareStatement(sql);
			} else if ( (gubn.equals("S")) && (check.equals("B")) ){
				sql = "select * from book_daechul where b_date is not null";
				pstmt = con.prepareStatement(sql);
			} else if (gubn.equals("A")) {
				sql = "select * from book_daechul where usid=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, usid);
			} else if (gubn.equals("B")) {
				sql = "select * from book_daechul where usid=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, usid);
			} else {
				sql = "select * from book_daechul where gubn=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "NULL");
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Daechul bean = new Bean_Daechul();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setIsbn(rs.getString("isbn"));
				bean.setD_date(rs.getString("d_date"));
				bean.setY_date(rs.getString("y_date"));
				bean.setB_date(rs.getString("b_date"));
				bean.setNalsu(rs.getInt("nalsu"));
				bean.setBigo(rs.getString("bigo"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

	// 도서명 조회 (isbn)
	public Bean_Booklist getBook2(String isbn) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bean_Booklist bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from book_booklist where isbn = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Booklist();
				bean.setNumb(rs.getInt("numb"));
				bean.setIsbn(rs.getString("isbn"));
				bean.setBookname(rs.getString("bookname"));
				bean.setAuthor(rs.getString("author"));
				bean.setChulpan(rs.getString("chulpan"));
				bean.setBookyear(rs.getString("bookyear"));
				bean.setPage(rs.getString("page"));
				bean.setPrice(rs.getString("price"));
				bean.setB_state(rs.getString("b_state"));
				bean.setBigo(rs.getString("bigo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}
	
	// 도서 대출 내역 신규 추가
	public boolean Dae_Insert(Bean_Daechul bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert book_daechul(usid,isbn,d_date,y_date,b_date,"
					+ "bigo) values (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsid());
			pstmt.setString(2, bean.getIsbn());
			pstmt.setString(3, bean.getD_date());
			pstmt.setString(4, bean.getY_date());
			pstmt.setString(5, bean.getB_date());
			pstmt.setString(6, bean.getBigo());
			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// 도서 대출 반납 내역 조회 (numb)
	public Bean_Daechul getDae1(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bean_Daechul bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from book_daechul where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Daechul();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setIsbn(rs.getString("isbn"));
				bean.setD_date(rs.getString("d_date"));
				bean.setY_date(rs.getString("y_date"));
				bean.setB_date(rs.getString("b_date"));
				bean.setNalsu(rs.getInt("nalsu"));
				bean.setBigo(rs.getString("bigo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}

	// 도서 대출 반납 내역 수정
	public boolean updateDae(Bean_Daechul bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update book_daechul set usid=?, isbn=?, d_date=?, y_date=?, "
				     + "b_date=?, bigo=? where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsid());
			pstmt.setString(2, bean.getIsbn());
			pstmt.setString(3, bean.getD_date());
			pstmt.setString(4, bean.getY_date());
			pstmt.setString(5, bean.getB_date());
			pstmt.setString(6, bean.getBigo());
			pstmt.setInt(7, bean.getNumb());
			
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
	
	// 도서 대출 정보 삭제
	public void deleteDae(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "delete from book_daechul where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// 도서 찾기
	public Vector<Bean_Booklist> IsbnRead(String book_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Booklist> vlist = new Vector<Bean_Booklist>();
		try {
			con = pool.getConnection();
			sql = "select numb, isbn, bookname, author, chulpan, b_state "
					+ "from book_booklist where bookname like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + book_name + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Booklist bean = new Bean_Booklist();
				bean.setNumb(rs.getInt(1));
				bean.setIsbn(rs.getString(2));
				bean.setBookname(rs.getString(3));
				bean.setAuthor(rs.getString(4));
				bean.setChulpan(rs.getString(5));
				bean.setB_state(rs.getString(6));
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

	// 아이디 찾기
	public Vector<Bean_Admin> UsidRead(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Admin> vlist = new Vector<Bean_Admin>();
		try {
			con = pool.getConnection();
			sql = "select numb, stat, usid, name, telp from book_admin where name like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Admin bean = new Bean_Admin();
				bean.setNumb(rs.getInt(1));
				bean.setStat(rs.getString(2));
				bean.setUsid(rs.getString(3));
				bean.setName(rs.getString(4));
				bean.setTelp(rs.getString(5));
				vlist.addElement(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

	// 반납 예정일자 계산
	public boolean updateYdate(int numb, String y_date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "update book_daechul set y_date=? where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, y_date);
			pstmt.setInt(2, numb);
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

	// 연체 일수 계산
	public boolean updateNalsu(int numb, long nal) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			if (nal > 0) {
				   String sql = "update book_daechul set nalsu=? where numb=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setLong(1, nal);
					pstmt.setInt(2, numb);
			} else {
				   String sql = "update book_daechul set nalsu=0 where numb=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, numb);
			}
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

	// 미반납 도서에 대출중으로 표시 
	public boolean updateMibannab(String isbn, String b_date) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
	   	//System.out.print(isbn);
	   	//System.out.println(b_date);
		
		try {
			con = pool.getConnection();
			if (b_date == null) {
				   String sql = "update book_booklist set b_state='대출중' where isbn=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, isbn);
			} else {
				   String sql = "update book_booklist set b_state='대출가능' where isbn=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, isbn);
			}
			
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
	
	// 연체자 표시 
	public boolean updateYun(String usid, int nalsu) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
	   	//System.out.print(usid);
	   	//System.out.println(nalsu);
		
		try {
			con = pool.getConnection();
			if (nalsu > 0) {
				   String sql = "update book_admin set sang=? where usid=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "연체 " + nalsu);
					pstmt.setString(2, usid);
			} else {
				   String sql = "update book_admin set sang='정상' where usid=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, usid);
			}
			
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

	// 버튼 연습 
	public boolean updateButton(String usid, String bigo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			String sql = "update book_daechul set bigo=? where usid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bigo);
			pstmt.setString(2, usid);
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
	
}