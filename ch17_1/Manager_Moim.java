package ch17_1;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Manager_Moim {

	private DBConnectionMgr pool;

	public Manager_Moim() {
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
			sql = "select usid from moim_admin where usid = ?";
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
			sql = "select * from moim_post where area3 like ?";
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
			sql = "select usid from moim_admin where usid=? and uspw=? and stat='승인' ";
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
	
	
	// 회원 정보 ===============================================================================
	// 회원 추가
	public boolean insertMember(Bean_Admin bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert moim_admin(moim,usid,uspw,gubn,stat,name,telp,gend,brth,mail,post"
					+ ",addr,hobb,jobb)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getMoim());
			pstmt.setString(2, bean.getUsid());
			pstmt.setString(3, bean.getUspw());
			pstmt.setString(4, bean.getGubn());
			pstmt.setString(5, bean.getStat());
			pstmt.setString(6, bean.getName());
			pstmt.setString(7, bean.getTelp());
			pstmt.setString(8, bean.getGend());
			pstmt.setString(9, bean.getBrth());
			pstmt.setString(10, bean.getMail());
			pstmt.setString(11, bean.getPost());
			pstmt.setString(12, bean.getAddr());
			String hobby[] = bean.getHobb();
			char hb[] = { '0', '0', '0', '0', '0' };
			String lists[] = { "독서", "등산", "낚시", "여행", "영화" };
			for (int i = 0; i < hobby.length; i++) {
				for (int j = 0; j < lists.length; j++) {
					if (hobby[i].equals(lists[j]))
						hb[j] = '1';
				}
			}
			pstmt.setString(13, new String(hb));
			pstmt.setString(14, bean.getJobb());
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
			String sql = "select * from moim_admin where usid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Admin();
				bean.setNumb(rs.getInt("numb"));
				bean.setMoim(rs.getString("moim"));
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
			String sql = "select * from moim_admin where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Admin();
				bean.setNumb(rs.getInt("numb"));
				bean.setMoim(rs.getString("moim"));
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
				String sql = "select * from moim_admin where numb = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, numb);
			} else {
				String sql = "select * from moim_admin where usid = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, usid);
			}
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Admin();
				bean.setNumb(rs.getInt("numb"));
				bean.setMoim(rs.getString("moim"));
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
			String sql = "update moim_admin set uspw=?, gubn=?, stat=?, name=?, telp=?, gend=?, brth=?,"
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
	public int getTotalCount(String gubn, String moim, String check) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		
		try {
			con = pool.getConnection();
			if ( (gubn.equals("S")) && (check.equals("N")) ) {
			       sql = "select count(numb) from moim_admin ";
				   pstmt = con.prepareStatement(sql);
				} else if ( (gubn.equals("S")) && (check.equals("Y")) ) {
				   sql = "select count(numb) from moim_admin where stat='미승인' ";
				   pstmt = con.prepareStatement(sql);
				} else if ( (gubn.equals("S")) && (check.equals("J")) ) {
				   sql = "select count(numb) from moim_admin where stat='승인' ";
				   pstmt = con.prepareStatement(sql);
				} else if ( (gubn.equals("A")) && (check.equals("N")) ) {
					   sql = "select count(numb) from moim_admin where moim=? ";
					   pstmt = con.prepareStatement(sql);
					   pstmt.setString(1, moim);
				} else if ( (gubn.equals("A")) && (check.equals("Y")) ) {
					   sql = "select count(numb) from moim_admin where stat='미승인' and moim=? ";
					   pstmt = con.prepareStatement(sql);
					   pstmt.setString(1, moim);
				} else if ( (gubn.equals("A")) && (check.equals("J")) ) {
					   sql = "select count(numb) from moim_admin where stat='승인' and moim=? ";
					   pstmt = con.prepareStatement(sql);
					   pstmt.setString(1, moim);
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
	public Vector<Bean_Admin> getMemberList(String gubn, String moim, String check) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Admin> vlist = new Vector<Bean_Admin>();
		//System.out.println(check);
		try {
			con = pool.getConnection();
			if ( (gubn.equals("S")) && (check.equals("N")) ) {
				sql = "select * from moim_admin ";
			   	pstmt = con.prepareStatement(sql);
			} else if ( (gubn.equals("S")) && (check.equals("Y")) ) {
				sql = "select * from moim_admin where stat='미승인' ";
			   	pstmt = con.prepareStatement(sql);
			} else if ( (gubn.equals("S")) && (check.equals("J")) ) {
				sql = "select * from moim_admin where stat='승인' ";
			   	pstmt = con.prepareStatement(sql);
			} else if ( (gubn.equals("A")) && (check.equals("N")) ) {
				sql = "select * from moim_admin where moim=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, moim);
			} else if ( (gubn.equals("A")) && (check.equals("Y")) ) {
				sql = "select * from moim_admin where stat='미승인' and moim=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, moim);
			} else if ( (gubn.equals("A")) && (check.equals("J")) ) {
				sql = "select * from moim_admin where stat='승인' and moim=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, moim);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Admin bean = new Bean_Admin();
				bean.setNumb(rs.getInt("numb"));
				bean.setMoim(rs.getString("moim"));
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

	// 같은 모임에 해당하는 회원 조회
	public Vector<Bean_Admin> getAdminList(String moim) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Admin> vlist = new Vector<Bean_Admin>();
		//System.out.println(check);
		try {
			con = pool.getConnection();
		    sql = "select * from moim_admin where moim=? ";
		    pstmt = con.prepareStatement(sql);
		    pstmt.setString(1, moim);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Admin bean = new Bean_Admin();
				bean.setNumb(rs.getInt("numb"));
				bean.setMoim(rs.getString("moim"));
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
			sql = "delete from moim_admin where numb=?";
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
				String sql = "update moim_admin set stat='승인' where numb=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, recnum);
			} else {
				String sql = "update moim_admin set stat='미승인' where numb=?";
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

	// 모임 정보 ===============================================================================
	// 모임 내역 신규 추가
	public boolean Moim_Insert(Bean_Moim bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert moim_moim(m_gubn,m_code,m_name,m_jang,m_tel,m_jangso,m_bigo) "
				+ "values (?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getM_gubn());
			pstmt.setString(2, bean.getM_code());
			pstmt.setString(3, bean.getM_name());
			pstmt.setString(4, bean.getM_jang());
			pstmt.setString(5, bean.getM_tel());
			pstmt.setString(6, bean.getM_jangso());
			pstmt.setString(7, bean.getM_bigo());

			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// 모임 내역 아이디 조건으로 조회
	public Vector<Bean_Moim> getMoimList(String m_gubn_y) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Moim> vlist = new Vector<Bean_Moim>();
		try {
			con = pool.getConnection();
			if (m_gubn_y.equals("XX")) {
				sql = "select * from moim_moim ";
			    pstmt = con.prepareStatement(sql);
			} else {
				sql = "select * from moim_moim where substr(m_code,1,2) = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, m_gubn_y);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Moim bean = new Bean_Moim();
				bean.setNumb(rs.getInt("numb"));
				bean.setM_gubn(rs.getString("m_gubn"));
				bean.setM_code(rs.getString("m_code"));
				bean.setM_name(rs.getString("m_name"));
				bean.setM_jang(rs.getString("m_jang"));
				bean.setM_tel(rs.getString("m_tel"));
				bean.setM_jangso(rs.getString("m_jangso"));
				bean.setM_bigo(rs.getString("m_bigo"));

				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}

	// 모임 자료 전체 가져오기
	public int getMoimCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = pool.getConnection();
			sql = "select count(numb) from moim_moim";
		    pstmt = con.prepareStatement(sql);
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

	// 모임 자료 수정을 위해 자료 가져오기
	public Bean_Moim getMoim(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bean_Moim bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from moim_moim where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Moim();
				bean.setNumb(rs.getInt("numb"));
				bean.setM_gubn(rs.getString("m_gubn"));
				bean.setM_code(rs.getString("m_code"));
				bean.setM_name(rs.getString("m_name"));
				bean.setM_jang(rs.getString("m_jang"));
				bean.setM_tel(rs.getString("m_tel"));
				bean.setM_jangso(rs.getString("m_jangso"));
				bean.setM_bigo(rs.getString("m_bigo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}

	// 모임 자료 수정을 위해 자료 가져오기
	public Bean_Moim getMoim1(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bean_Moim bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from moim_moim where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Moim();
				bean.setNumb(rs.getInt("numb"));
				bean.setM_gubn(rs.getString("m_gubn"));
				bean.setM_code(rs.getString("m_code"));
				bean.setM_name(rs.getString("m_name"));
				bean.setM_jang(rs.getString("m_jang"));
				bean.setM_tel(rs.getString("m_tel"));
				bean.setM_jangso(rs.getString("m_jangso"));
				bean.setM_bigo(rs.getString("m_bigo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}

	// 모임 내역 수정
	public boolean updateMoim(Bean_Moim bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update moim_moim set m_gubn=?, m_code=?, m_name=?, m_jang=?, "
				+ "m_tel=?, m_jangso=?, m_bigo=? where numb=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getM_gubn());
			pstmt.setString(2, bean.getM_code());
			pstmt.setString(3, bean.getM_name());
			pstmt.setString(4, bean.getM_jang());
			pstmt.setString(5, bean.getM_tel());
			pstmt.setString(6, bean.getM_jangso());
			pstmt.setString(7, bean.getM_bigo());
			pstmt.setInt(8, bean.getNumb());
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
	
	// 모임 정보 삭제
	public void deleteMoim(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "delete from moim_moim where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
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
			if (check.equals("J")) {
				sql = "select * from moim_code where substr(code,1,1)='J' ";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("A")) {
				sql = "select * from moim_code where substr(code,1,1)='A' ";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("C")) {
				sql = "select * from moim_code where substr(code,1,1)='C' ";
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
			sql = "insert moim_code(usid,code,gubn)	values (?,?,?)";
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
				sql = "select count(numb) from moim_code ";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("A")) {
				sql = "select count(numb) from moim_code where usid=? ";
			    pstmt = con.prepareStatement(sql);
				pstmt.setString(1, usid);
			} else if (check.equals("B")) {
				sql = "select count(numb) from moim_code where "  + keyField + " like ? ";
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
				sql = "select * from moim_code ";
			    pstmt = con.prepareStatement(sql);
			} else if (check.equals("A")) {
				sql = "select * from moim_code where usid=? ";
			    pstmt = con.prepareStatement(sql);
				pstmt.setString(1, usid);
			} else if (check.equals("B")) {
				sql = "select * from moim_code where "  + keyField + " like ? ";
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
			String sql = "select * from moim_code where numb = ?";
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
			sql = "update moim_code set usid=?, code=?, gubn=? where numb=?";
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
			sql = "delete from moim_code where numb=?";
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
				sql = "select * from moim_code where substr(code,1,1)='1' and substr(code,5,1)!='0' ";
			    pstmt = con.prepareStatement(sql);
			} else if (keyField.equals("j_menu")) {
				sql = "select * from moim_code where substr(code,1,1)='2' and substr(code,5,1)!='0' ";
			    pstmt = con.prepareStatement(sql);
			} else if (keyField.equals("h_menu")) {
				sql = "select * from moim_code where substr(code,1,1)='3' and substr(code,5,1)!='0' ";
			    pstmt = con.prepareStatement(sql);
			} else if (keyField.equals("c_menu")) {
				sql = "select * from moim_code where substr(code,1,1)='4' and substr(code,5,1)!='0' ";
			    pstmt = con.prepareStatement(sql);
			} else if (keyField.equals("b_menu")) {
				sql = "select * from moim_code where substr(code,1,1)='5' and substr(code,5,1)!='0' ";
			    pstmt = con.prepareStatement(sql);
			} else if (keyField.equals("m_gubn")) {
				sql = "select * from moim_code where substr(code,1,1)='A' ";
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

	// 명산 정보 ===============================================================================
	// 명산 자료 수
	public int getSanCount(String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = pool.getConnection();
			sql = "select count(numb) from moim_sanlist where s_usid=? ";
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

	// 명산 내역 아이디 조건으로 조회
		public Vector<Bean_San> getSanList(String usid) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<Bean_San> vlist = new Vector<Bean_San>();
			try {
				con = pool.getConnection();
				sql = "select * from moim_sanlist where s_usid=? ";
			    pstmt = con.prepareStatement(sql);
				pstmt.setString(1, usid);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Bean_San bean = new Bean_San();
					bean.setNumb(rs.getInt("numb"));
					bean.setS_usid(rs.getString("s_usid"));
					bean.setS_code(rs.getString("s_code"));
					bean.setS_name(rs.getString("s_name"));
					bean.setS_juso(rs.getString("s_juso"));
					bean.setS_high(rs.getString("s_high"));
					bean.setS_levl(rs.getString("s_levl"));
					bean.setS_bigo(rs.getString("s_bigo"));
					vlist.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return vlist;
		}

		// 명산 등록 내역 조회 (numb)
		public Bean_San getSan1(int numb) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Bean_San bean = null;
			try {
				con = pool.getConnection();
				String sql = "select * from moim_sanlist where numb = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, numb);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					bean = new Bean_San();
					bean.setNumb(rs.getInt("numb"));
					bean.setS_usid(rs.getString("s_usid"));
					bean.setS_code(rs.getString("s_code"));
					bean.setS_name(rs.getString("s_name"));
					bean.setS_juso(rs.getString("s_juso"));
					bean.setS_high(rs.getString("s_high"));
					bean.setS_levl(rs.getString("s_levl"));
					bean.setS_bigo(rs.getString("s_bigo"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con);
			}
			return bean;
		}
	
		// 명산 내역 수정
		public boolean updateSan(Bean_San bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "update moim_sanlist set s_usid=?, s_code=?, s_name=?, "
					+ "s_juso=?, s_high=?, s_levl=?, s_bigo=? where numb=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getS_usid());
				pstmt.setString(2, bean.getS_code());
				pstmt.setString(3, bean.getS_name());
				pstmt.setString(4, bean.getS_juso());
				pstmt.setString(5, bean.getS_high());
				pstmt.setString(6, bean.getS_levl());
				pstmt.setString(7, bean.getS_bigo());
				pstmt.setInt(8, bean.getNumb());
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
	
		// 명산 내역 신규 추가
		public boolean San_Insert(Bean_San bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "insert moim_sanlist(s_usid,s_code,s_name, s_juso, s_high,s_levl, "
					+ "s_bigo) values (?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getS_usid());
				pstmt.setString(2, bean.getS_code());
				pstmt.setString(3, bean.getS_name());
				pstmt.setString(4, bean.getS_juso());
				pstmt.setString(5, bean.getS_high());
				pstmt.setString(6, bean.getS_levl());
				pstmt.setString(7, bean.getS_bigo());
				if (pstmt.executeUpdate() == 1)
					flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flag;
		}
	
		// 모임(등산) 내역 정보 ===============================================================================
		// 모임(등산) 내역 자료 가져오기
		public int getMeetCount(String usid) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int totalCount = 0;
			try {
				con = pool.getConnection();
				sql = "select count(numb) from moim_meet where t_usid=?";
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

		// 모임(등산) 내역 자료 가져오기 아이디 조건으로 조회
		public Vector<Bean_Meet> getMeetList(String usid) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<Bean_Meet> vlist = new Vector<Bean_Meet>();
			try {
				con = pool.getConnection();
				sql = "select * from moim_meet where t_usid=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, usid);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Bean_Meet bean = new Bean_Meet();
					bean.setNumb(rs.getInt("numb"));
					bean.setT_usid(rs.getString("t_usid"));
					bean.setT_m_code(rs.getString("t_m_code"));
					bean.setT_m_cha(rs.getString("t_m_cha"));
					bean.setT_s_code(rs.getString("t_s_code"));
					bean.setT_m_date(rs.getString("t_m_date"));
					bean.setT_m_jangso(rs.getString("t_m_jangso"));
					bean.setT_m_bigo(rs.getString("t_m_bigo"));
					vlist.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return vlist;
		}

		// 모임(등산) 내역 자료 수정을 위해 자료 가져오기
		public Bean_Meet getMeet(int numb) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Bean_Meet bean = null;
			try {
				con = pool.getConnection();
				String sql = "select * from moim_meet where numb = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, numb);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					bean = new Bean_Meet();
					bean.setNumb(rs.getInt("numb"));
					bean.setT_usid(rs.getString("t_usid"));
					bean.setT_m_code(rs.getString("t_m_code"));
					bean.setT_m_cha(rs.getString("t_m_cha"));
					bean.setT_s_code(rs.getString("t_s_code"));
					bean.setT_m_date(rs.getString("t_m_date"));
					bean.setT_m_jangso(rs.getString("t_m_jangso"));
					bean.setT_m_bigo(rs.getString("t_m_bigo"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con);
			}
			return bean;
		}

		// 모임(등산) 내역 수정
		public boolean updateMeet(Bean_Meet bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "update moim_meet set t_usid=?, t_m_code=?, t_m_cha=?, t_s_code=?, "
					+ "t_m_date=?, t_m_jangso=?, t_m_bigo=? where numb=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getT_usid());
				pstmt.setString(2, bean.getT_m_code());
				pstmt.setString(3, bean.getT_m_cha());
				pstmt.setString(4, bean.getT_s_code());
				pstmt.setString(5, bean.getT_m_date());
				pstmt.setString(6, bean.getT_m_jangso());
				pstmt.setString(7, bean.getT_m_bigo());
				pstmt.setInt(8, bean.getNumb());
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

		// 모임(등산) - 모임 자료에서 모임명칭 가져오기 
		public Bean_Moim getMeet(String m_code) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Bean_Moim bean = null;
			try {
				con = pool.getConnection();
				String sql = "select * from moim_moim where m_code = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, m_code);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					bean = new Bean_Moim();
					bean.setNumb(rs.getInt("numb"));
					bean.setM_gubn(rs.getString("m_gubn"));
					bean.setM_code(rs.getString("m_code"));
					bean.setM_name(rs.getString("m_name"));
					bean.setM_jang(rs.getString("m_jang"));
					bean.setM_tel(rs.getString("m_tel"));
					bean.setM_jangso(rs.getString("m_jangso"));
					bean.setM_bigo(rs.getString("m_bigo"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con);
			}
			return bean;
		}
		
		// 모임(등산) - 명산 자료에서 명산명칭 가져오기 
		public Bean_San getMeet1(String s_code) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Bean_San bean = null;
			try {
				con = pool.getConnection();
				String sql = "select * from moim_sanlist where s_code = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, s_code);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					bean = new Bean_San();
					bean.setNumb(rs.getInt("numb"));
					bean.setS_usid(rs.getString("s_usid"));
					bean.setS_code(rs.getString("s_code"));
					bean.setS_name(rs.getString("s_name"));
					bean.setS_juso(rs.getString("s_juso"));
					bean.setS_high(rs.getString("s_high"));
					bean.setS_levl(rs.getString("s_levl"));
					bean.setS_bigo(rs.getString("s_bigo"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con);
			}
			return bean;
		}

		// 모임(등산) 정보 삭제
		public void deleteMeet(int numb) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			ResultSet rs = null;
			try {
				con = pool.getConnection();
				sql = "delete from moim_meet where numb=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, numb);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		}
		
		// 모임(등산) 추가
		public boolean Meet_Insert(Bean_Meet bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "insert moim_meet(t_usid, t_m_code, t_m_cha, t_s_code, t_m_date, "
					+ "t_m_jangso, t_m_bigo) values (?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getT_usid());
				pstmt.setString(2, bean.getT_m_code());
				pstmt.setString(3, bean.getT_m_cha());
				pstmt.setString(4, bean.getT_s_code());
				pstmt.setString(5, bean.getT_m_date());
				pstmt.setString(6, bean.getT_m_jangso());
				pstmt.setString(7, bean.getT_m_bigo());
				if (pstmt.executeUpdate() == 1)
					flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flag;
		}
	
	// 참석자 정보 ===============================================================================
	// 참석자 내역 아이디 조건으로 조회
		public Vector<Bean_List> getChamList(String moim, String cha) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<Bean_List> vlist = new Vector<Bean_List>();
			try {
				con = pool.getConnection();
				sql = "select * from moim_list where l_moim=? and l_cha=? ";
			    pstmt = con.prepareStatement(sql);
				pstmt.setString(1, moim);
				pstmt.setString(2, cha);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Bean_List bean = new Bean_List();
					bean.setNumb(rs.getInt("numb"));
					bean.setL_moim(rs.getString("l_moim"));
					bean.setL_cha(rs.getString("l_cha"));
					bean.setL_usid(rs.getString("l_usid"));
					bean.setL_name(rs.getString("l_name"));
					bean.setL_telp(rs.getString("l_telp"));
					bean.setL_cham(rs.getString("l_cham"));
					vlist.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return vlist;
		}

		// 참석자 명단 생성 
		public boolean List_Insert(String moim, String cha, String usid, String name, String telp) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "insert moim_list(l_moim,l_cha,l_usid,l_name,l_telp,l_cham) "
					+ "values (?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, moim);
				pstmt.setString(2, cha);
				pstmt.setString(3, usid);
				pstmt.setString(4, name);
				pstmt.setString(5, telp);
				pstmt.setString(6, "미참석");

				if (pstmt.executeUpdate() == 1)
					flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flag;
		}

		// 참석자 명단 생성 (존재여부) 
		public boolean Cham_List(String moim, String cha, String usid) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "select l_usid from moim_list where l_moim=? and l_cha=? and l_usid=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, moim);
				pstmt.setString(2, cha);
				pstmt.setString(3, usid);
				rs = pstmt.executeQuery();
				flag = rs.next();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return flag;
		}
		
		
		// 참석여부 
		public boolean updateCham(int numb, String perm) {
			Connection con = null;
			PreparedStatement pstmt = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				if (perm.equals("미참석")) {
					String sql = "update moim_list set l_cham='참석' where numb=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, numb);
				} else {
					String sql = "update moim_list set l_cham='미참석' where numb=?";
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
		
	// 회비 납부 정보 ===============================================================================
	// 회비 납부 내역 아이디 조건으로 조회
		public Vector<Bean_Don> getDonList(String moim) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			Vector<Bean_Don> vlist = new Vector<Bean_Don>();
			try {
				con = pool.getConnection();
				sql = "select * from moim_don where d_moim=? ";
			    pstmt = con.prepareStatement(sql);
				pstmt.setString(1, moim);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Bean_Don bean = new Bean_Don();
					bean.setNumb(rs.getInt("numb"));
					bean.setD_moim(rs.getString("d_moim"));
					bean.setD_usid(rs.getString("d_usid"));
					bean.setD_d01(rs.getInt("d_d01"));
					bean.setD_d02(rs.getInt("d_d02"));
					bean.setD_d03(rs.getInt("d_d03"));
					bean.setD_d04(rs.getInt("d_d04"));
					bean.setD_d05(rs.getInt("d_d05"));
					bean.setD_tot(rs.getInt("d_tot"));
					vlist.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return vlist;
		}

		// 회비 납부 대상자 명단 생성 (존재여부) 
		public boolean Don_List(String moim, String usid) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "select d_usid from moim_don where d_moim=? and d_usid=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, moim);
				pstmt.setString(2, usid);
				rs = pstmt.executeQuery();
				flag = rs.next();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
			return flag;
		}

		// 회비 납부 대상자 명단 생성
		public boolean Don_Insert(String moim, String usid) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "insert moim_don(d_moim,d_usid) values (?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, moim);
				pstmt.setString(2, usid);

				if (pstmt.executeUpdate() == 1)
					flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flag;
		}
		
		// 회비 납부자 합계 금액 계산
		public boolean Don_Update(int numb, int d_tot) {
			Connection con = null;
			PreparedStatement pstmt = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				String sql = "update moim_don set d_tot=? where numb=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, d_tot);
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
		
		// 회비 납부 내역 레코드 번호 조건으로 조회(수정 화면)
		public Bean_Don getDon(int numb) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Bean_Don bean = null;
			try {
				con = pool.getConnection();
				String sql = "select * from moim_don where numb = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, numb);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					bean = new Bean_Don();
					bean.setNumb(rs.getInt("numb"));
					bean.setD_moim(rs.getString("d_moim"));
					bean.setD_usid(rs.getString("d_usid"));
					bean.setD_d01(rs.getInt("d_d01"));
					bean.setD_d02(rs.getInt("d_d02"));
					bean.setD_d03(rs.getInt("d_d03"));
					bean.setD_d04(rs.getInt("d_d04"));
					bean.setD_d05(rs.getInt("d_d05"));
					bean.setD_tot(rs.getInt("d_tot"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con);
			}
			return bean;
		}

		// 회비 납부 내역 수정
		public boolean updateDon(Bean_Don bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "update moim_don set d_moim=?, d_usid=?, d_d01=?, d_d02=?, "
					+ "d_d03=?, d_d04=?, d_d05=?, d_tot=? where numb=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getD_moim());
				pstmt.setString(2, bean.getD_usid());
				pstmt.setInt(3, bean.getD_d01());
				pstmt.setInt(4, bean.getD_d02());
				pstmt.setInt(5, bean.getD_d03());
				pstmt.setInt(6, bean.getD_d04());
				pstmt.setInt(7, bean.getD_d05());
				pstmt.setInt(8, bean.getD_tot());
				pstmt.setInt(9, bean.getNumb());
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
		
		// 회비 납부 내역  추가
		public boolean Don_Insert(Bean_Don bean) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "insert moim_don(d_moim, d_usid, d_d01, d_d02, d_d03, "
					+ "d_d04, d_d05) values (?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getD_moim());
				pstmt.setString(2, bean.getD_usid());
				pstmt.setInt(3, bean.getD_d01());
				pstmt.setInt(4, bean.getD_d02());
				pstmt.setInt(5, bean.getD_d03());
				pstmt.setInt(6, bean.getD_d04());
				pstmt.setInt(7, bean.getD_d05());
				pstmt.setInt(8, bean.getD_tot());
				if (pstmt.executeUpdate() == 1)
					flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt);
			}
			return flag;
		}

		// 회비 납부 내역 삭제
		public void Don_delete(int numb) {
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = null;
			ResultSet rs = null;
			try {
				con = pool.getConnection();
				sql = "delete from moim_don where numb=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, numb);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con, pstmt, rs);
			}
		}
		
	// 출납 정보 ===============================================================================
	// 출납 자료 수
	public int getChulCount(String moim) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = pool.getConnection();
			sql = "select count(numb) from moim_chul where c_moim=? ";
		    pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moim);
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
	public Vector<Bean_Chul> getChulList(String moim) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Chul> vlist = new Vector<Bean_Chul>();
		try {
			con = pool.getConnection();
			sql = "select * from moim_chul where c_moim=? ";
		    pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moim);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Chul bean = new Bean_Chul();
				bean.setNumb(rs.getInt("numb"));
				bean.setC_moim(rs.getString("c_moim"));
				bean.setC_usid(rs.getString("c_usid"));
				bean.setC_date(rs.getString("c_date"));
				bean.setC_code(rs.getString("c_code"));
				bean.setC_su(rs.getInt("c_su"));
				bean.setC_ji(rs.getInt("c_ji"));
				bean.setC_jan(rs.getInt("c_jan"));
				bean.setC_bigo(rs.getString("c_bigo"));
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
	public Bean_Chul getChul1(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bean_Chul bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from moim_chul where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Chul();
				bean.setNumb(rs.getInt("numb"));
				bean.setC_moim(rs.getString("c_moim"));
				bean.setC_usid(rs.getString("c_usid"));
				bean.setC_date(rs.getString("c_date"));
				bean.setC_code(rs.getString("c_code"));
				bean.setC_su(rs.getInt("c_su"));
				bean.setC_ji(rs.getInt("c_ji"));
				bean.setC_jan(rs.getInt("c_jan"));
				bean.setC_bigo(rs.getString("c_bigo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}

	// 출납 내역 수정
	public boolean updateChul(Bean_Chul bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "update moim_chul set c_moim=?, c_usid=?, c_date=?,"
				+ " c_code=?, c_su=?, c_ji=?, c_jan=?, c_bigo=? where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getC_moim());
			pstmt.setString(2, bean.getC_usid());
			pstmt.setString(3, bean.getC_date());
			pstmt.setString(4, bean.getC_code());
			pstmt.setInt(5, bean.getC_su());
			pstmt.setInt(6, bean.getC_ji());
			pstmt.setInt(7, bean.getC_jan());
			pstmt.setString(8, bean.getC_bigo());
			pstmt.setInt(9, bean.getNumb());
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
			sql = "delete from moim_chul where numb=?";
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
	public boolean Chul_Insert(Bean_Chul bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert moim_chul(c_moim,c_usid,c_date,c_code,c_su,c_ji,c_jan,"
				+ "c_bigo)	values (?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getC_moim());
			pstmt.setString(2, bean.getC_usid());
			pstmt.setString(3, bean.getC_date());
			pstmt.setString(4, bean.getC_code());
			pstmt.setInt(5, bean.getC_su());
			pstmt.setInt(6, bean.getC_ji());
			pstmt.setInt(7, bean.getC_jan());
			pstmt.setString(8, bean.getC_bigo());

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
	public boolean updateJan(int numb, int jan) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "update moim_chul set c_jan=? where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, jan);
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

	// 모임 회비 전체 가져오기(모임별로)
	public int Habgum(String moim) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalGum = 0;
		try {
			con = pool.getConnection();
			sql = "select sum(d_tot) from moim_don where d_moim=? ";
		    pstmt = con.prepareStatement(sql);
			pstmt.setString(1, moim);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalGum = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return totalGum;
	}
	
	// 회비 합계 금액
	public boolean updateGum(int moim_gum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "update moim_chul set c_su=? where c_code='B0001' ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, moim_gum);
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

	// 코드 조회 (code)
	public Bean_Code getCode(String c_code) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bean_Code bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from moim_code where code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_code);
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
	
	// IoT 자료 ===============================================================================
	// IoT 자료 조회 (전체)
	public Bean_Iot getIot() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bean_Iot bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from green_device where usma = '3C:61:05:D3:08:08' ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Iot();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsma(rs.getString("usma"));
				bean.setPin(rs.getString("pin"));
				bean.setIpdate(rs.getString("ipdate"));
				bean.setT01(rs.getString("T01"));
				bean.setT02(rs.getString("T02"));
				bean.setV01(rs.getString("V01"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}

	// 해당 조건의 Iot 조회
	public Vector<Bean_Iot> getIotList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Iot> vlist = new Vector<Bean_Iot>();
		//System.out.println(check);
		try {
			con = pool.getConnection();
			sql = "select * from green_device where usma = '3C:61:05:D3:08:08' ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Iot bean = new Bean_Iot();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsma(rs.getString("usma"));
				bean.setPin(rs.getString("pin"));
				bean.setIpdate(rs.getString("ipdate"));
				bean.setT01(rs.getString("T01"));
				bean.setT02(rs.getString("T02"));
				bean.setV01(rs.getString("V01"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
	// IoT 제어값 
	public boolean updateIot(int recnum, String perm) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			if (perm.equals("OFFOO")) {
				String sql = "update green_device set V01='ONOOO' where numb=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, recnum);
			} else {
				String sql = "update green_device set V01='OFFOO' where numb=?";
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

	// 해당 조건의 Iot (온도, 습도, 조도) 조회
	public Vector<Bean_IotList> getIotList2() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_IotList> vlist = new Vector<Bean_IotList>();
		//System.out.println(check);
		try {
			con = pool.getConnection();
			sql = "select * from green_list2 where usma = '3C:61:05:D3:08:08' order by numb DESC limit 15 ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_IotList bean = new Bean_IotList();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsma(rs.getString("usma"));
				bean.setIpdate(rs.getString("ipdate"));
				bean.setA01(rs.getString("A01"));
				bean.setA02(rs.getString("A02"));
				bean.setA03(rs.getString("A03"));
				vlist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vlist;
	}
	
}