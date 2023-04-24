package ch17_1;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import ch17_1.Bean_Admin;


public class Manager_Chul {

	private DBConnectionMgr pool;

	public Manager_Chul() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 회원 정보 ===============================================================================
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

	// 출납 정보 ===============================================================================
	// 출납 자료 수
	public int getChulCount(String check, String keyWord, String keyField, String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = pool.getConnection();
			if (check.equals("S")) {
				sql = "select count(numb) from chul_chul ";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("A")) {
				sql = "select count(numb) from chul_chul where usid=? ";
			    pstmt = con.prepareStatement(sql);
				pstmt.setString(1, usid);
			} else if (check.equals("B")) {
				sql = "select count(numb) from chul_chul where "  + keyField + " like ? ";
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
	
	// 출납 내역 아이디 조건으로 조회
	public Vector<Bean_Chulnab> getChulList(String check, String keyWord, String keyField, String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Chulnab> vlist = new Vector<Bean_Chulnab>();
		try {
			con = pool.getConnection();
			if (check.equals("S")) {
				sql = "select * from chul_chul ";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("A")) {
				sql = "select * from chul_chul where usid=? ";
			    pstmt = con.prepareStatement(sql);
				pstmt.setString(1, usid);
			} else if (check.equals("B")) {
				sql = "select * from chul_chul where "  + keyField + " like ? ";
			    pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%" + keyWord + "%");
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Chulnab bean = new Bean_Chulnab();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setC_date(rs.getString("c_date"));
				bean.setS_menu(rs.getString("s_menu"));
				bean.setJ_menu(rs.getString("j_menu"));
				bean.setH_menu(rs.getString("h_menu"));
				bean.setC_menu(rs.getString("c_menu"));
				bean.setB_menu(rs.getString("b_menu"));
				bean.setJ_chul(rs.getString("j_chul"));
				bean.setSuip(rs.getInt("suip"));
				bean.setJichul(rs.getInt("jichul"));
				bean.setJangum(rs.getInt("jangum"));
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

	// 출납 등록 내역 조회 (numb)
	public Bean_Chulnab getChul1(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bean_Chulnab bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from chul_chul where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Chulnab();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setC_date(rs.getString("c_date"));
				bean.setS_menu(rs.getString("s_menu"));
				bean.setJ_menu(rs.getString("j_menu"));
				bean.setH_menu(rs.getString("h_menu"));
				bean.setC_menu(rs.getString("c_menu"));
				bean.setB_menu(rs.getString("b_menu"));
				bean.setJ_chul(rs.getString("j_chul"));
				bean.setSuip(rs.getInt("suip"));
				bean.setJichul(rs.getInt("jichul"));
				bean.setJangum(rs.getInt("jangum"));
				bean.setBigo(rs.getString("bigo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}

	// 출납 내역 수정
	public boolean updateChul(Bean_Chulnab bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update chul_chul set usid=?, c_date=?, s_menu=?, j_menu=?, h_menu=?, "
				+ "c_menu=?, b_menu=?, j_chul=?, suip=?, jichul=?, jangum=?, bigo=? where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsid());
			pstmt.setString(2, bean.getC_date());
			pstmt.setString(3, bean.getS_menu());
			pstmt.setString(4, bean.getJ_menu());
			pstmt.setString(5, bean.getH_menu());
			pstmt.setString(6, bean.getC_menu());
			pstmt.setString(7, bean.getB_menu());
			pstmt.setString(8, bean.getJ_chul());
			pstmt.setInt(9, bean.getSuip());
			pstmt.setInt(10, bean.getJichul());
			pstmt.setInt(11, bean.getJangum());
			pstmt.setString(12, bean.getBigo());
			pstmt.setInt(13, bean.getNumb());
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
	
	// 출납 정보 삭제
	public void deleteChul(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "delete from chul_chul where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	// 출납 내역 신규 추가
	public boolean Chul_Insert(Bean_Chulnab bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert chul_chul(usid,c_date,s_menu,j_menu,h_menu,c_menu,b_menu,j_chul,"
				+ "suip,jichul,jangum,bigo)	values (?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsid());
			pstmt.setString(2, bean.getC_date());
			pstmt.setString(3, bean.getS_menu());
			pstmt.setString(4, bean.getJ_menu());
			pstmt.setString(5, bean.getH_menu());
			pstmt.setString(6, bean.getC_menu());
			pstmt.setString(7, bean.getB_menu());
			pstmt.setString(8, bean.getJ_chul());
			pstmt.setInt(9, bean.getSuip());
			pstmt.setInt(10, bean.getJichul());
			pstmt.setInt(11, bean.getJangum());
			pstmt.setString(12, bean.getBigo());

			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// 출납 잔액 계산
	public boolean updateJangum(int numb, int jangum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "update chul_chul set jangum=? where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, jangum);
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
	
	// 전기 수도 가스 요금 계산 ===============================================================================
	// 요금 계산 자료 수
	public int getYoCount(String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = pool.getConnection();
			sql = "select count(numb) from chul_gum where usid=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usid);
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
	
	// 요금 계산 내역 아이디 조건으로 조회
	public Vector<Bean_Gum> getYoList(String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Gum> vlist = new Vector<Bean_Gum>();
		try {
			con = pool.getConnection();
			sql = "select * from chul_gum where usid=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Gum bean = new Bean_Gum();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setJong(rs.getString("jong"));
				bean.setSa01(rs.getInt("sa01"));
				bean.setSa02(rs.getInt("sa02"));
				bean.setChong(rs.getInt("chong"));
				bean.setGum(rs.getInt("gum"));
				bean.setVat(rs.getInt("vat"));
				bean.setNabbu(rs.getInt("nabbu"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

	// 요금 계산 내역 신규 추가
	public boolean Gum_Insert(Bean_Gum bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert chul_gum(usid,jong,sa01,sa02)	values (?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsid());
			pstmt.setString(2, bean.getJong());
			pstmt.setInt(3, bean.getSa01());
			pstmt.setInt(4, bean.getSa02());

			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}

	// 요금 계산 등록 내역 조회 (numb)
	public Bean_Gum getGum1(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bean_Gum bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from chul_gum where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Gum();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setJong(rs.getString("jong"));
				bean.setSa01(rs.getInt("sa01"));
				bean.setSa02(rs.getInt("sa02"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}
	
	// 요금 계산 내역 수정
	public boolean updateGum(Bean_Gum bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update chul_gum set usid=?, jong=?, sa01=?, sa02=? where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsid());
			pstmt.setString(2, bean.getJong());
			pstmt.setInt(3, bean.getSa01());
			pstmt.setInt(4, bean.getSa02());
			pstmt.setInt(5, bean.getNumb());
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
	
	// 요금 계산 내역 삭제
	public void deleteGum(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "delete from chul_gum where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// 요금 계산(총사용량 계산, chong = sa02 - sa01)
	public boolean updateCGum(int numb, int chong, double gum8, double gum9, double gum10) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "update chul_gum set chong=?, gum=?, vat=?, "
					+ " nabbu=? where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, chong);
			pstmt.setDouble(2, gum8);
			pstmt.setDouble(3, gum9);
			pstmt.setDouble(4, gum10);
			pstmt.setInt(5, numb);
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
				sql = "select count(numb) from jsp_book_booklist";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("J")) {
				sql = "select count(numb) from jsp_book_booklist where b_state = '대출중' ";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("G")) {
				sql = "select count(numb) from jsp_book_booklist where b_state = '대출가능' ";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("S")) {
				sql = "select count(numb) from jsp_book_booklist where "  + keyField + " like ? ";
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
	

	
}