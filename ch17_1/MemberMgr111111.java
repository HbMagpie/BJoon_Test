package ch17_1;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class MemberMgr111111 {

	private DBConnectionMgr pool;

	public MemberMgr111111() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ID 중복 체크
	public boolean checkId(String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "select usid from jsp_admin where usid = ?";
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
	public Vector<ZipcodeBean> zipcodeRead(String area3) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ZipcodeBean> vlist = new Vector<ZipcodeBean>();
		try {
			con = pool.getConnection();
			sql = "select * from chul_post where area3 like ?";
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

	// 회원 추가
	public boolean insertMember(MemberBean1111 bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert jsp_admin(usid,uspw,levl,sosk,name,telp,gend,brth,mail,post"
					+ ",addr,hobb,jobb)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsid());
			pstmt.setString(2, bean.getUspw());
			pstmt.setString(3, bean.getLevl());
			pstmt.setString(4, bean.getSosk());
			pstmt.setString(5, bean.getName());
			pstmt.setString(6, bean.getTelp());
			pstmt.setString(7, bean.getGend());
			pstmt.setString(8, bean.getBrth());
			pstmt.setString(9, bean.getMail());
			pstmt.setString(10, bean.getPost());
			pstmt.setString(11, bean.getAddr());
			String hobby[] = bean.getHobb();
			char hb[] = { '0', '0', '0', '0', '0' };
			String lists[] = { "인터넷", "여행", "게임", "영화", "운동" };
			for (int i = 0; i < hobby.length; i++) {
				for (int j = 0; j < lists.length; j++) {
					if (hobby[i].equals(lists[j]))
						hb[j] = '1';
				}
			}
			pstmt.setString(12, new String(hb));
			pstmt.setString(13, bean.getJobb());

			//System.out.print("uspw : ");
			//System.out.println(bean.getUspw());

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
			sql = "select usid from jsp_admin where usid = ? and uspw = ?";
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
	
	// 회원 조회 (ID)
	public MemberBean1111 getMember(String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean1111 bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from jsp_admin where usid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new MemberBean1111();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setUspw(rs.getString("uspw"));
				bean.setLevl(rs.getString("levl"));
				bean.setSosk(rs.getString("sosk"));
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
	public MemberBean1111 getMember2(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean1111 bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from jsp_admin where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new MemberBean1111();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setUspw(rs.getString("uspw"));
				bean.setLevl(rs.getString("levl"));
				bean.setSosk(rs.getString("sosk"));
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
	public boolean updateMember(MemberBean1111 bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "update jsp_admin set uspw=?, levl=?, sosk=?, name=?, telp=?, gend=?, brth=?,"
					+ "mail=?, post=?, addr=?, hobb=?, jobb=? where usid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUspw());
			pstmt.setString(2, bean.getLevl());
			pstmt.setString(3, bean.getSosk());
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
				String list[] = { "인터넷", "여행", "게임", "영화", "운동" };
				for (int i = 0; i < hobbys.length; i++) {
					for (int j = 0; j < list.length; j++)
						if (hobbys[i].equals(list[j]))
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
	public int getTotalCount(String levl, String sosk, int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = pool.getConnection();
			if (levl.equals("S")) {
				sql = "select count(numb) from jsp_admin";
				pstmt = con.prepareStatement(sql);
			} else if (levl.equals("A")) {
				sql = "select count(numb) from jsp_admin where sosk=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, sosk);
			} else if (levl.equals("B")) {
				sql = "select count(numb) from jsp_admin where numb=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, numb);
			} else {
				sql = "select count(numb) from jsp_admin where levl=?";
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

	// 해당 조건의 회원 조회
	public Vector<MemberBean1111> getMemberList(String levl, String sosk, int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<MemberBean1111> vlist = new Vector<MemberBean1111>();
		try {
			con = pool.getConnection();
			if (levl.equals("S")) {
		       sql = "select * from jsp_admin";
			   pstmt = con.prepareStatement(sql);
			} else if (levl.equals("A")) {
			   sql = "select * from jsp_admin where sosk=?";
			   pstmt = con.prepareStatement(sql);
			   pstmt.setString(1, sosk);
			} else if (levl.equals("B")) {
			   sql = "select * from jsp_admin where numb=?";
			   pstmt = con.prepareStatement(sql);
			   pstmt.setInt(1, numb);
			} else {
			   sql = "select * from jsp_admin where levl=?";
			   pstmt = con.prepareStatement(sql);
			   pstmt.setString(1, "NULL");
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberBean1111 bean = new MemberBean1111();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setUspw(rs.getString("uspw"));
				bean.setLevl(rs.getString("levl"));
				bean.setSosk(rs.getString("sosk"));
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
			sql = "delete from jsp_admin where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	// 현금 출납부   =================================================================
	// 현금 출납 내역 추가
	public boolean chul_insert(ChulBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert jsp_chulnab(usid,nalja,gubun,conte,deal"
					+ ",suip,chul,bigo)values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsid());
			pstmt.setString(2, bean.getNalja());
			pstmt.setString(3, bean.getGubun());
			pstmt.setString(4, bean.getConte());
			pstmt.setString(5, bean.getDeal());
			pstmt.setInt(6, bean.getSuip());
			pstmt.setInt(7, bean.getChul());
			pstmt.setString(8, bean.getBigo());

			//System.out.print("uspw : ");
			//System.out.println(bean.getUspw());

			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// 출납 내역 조회 (usid)
	public ChulBean getChulnab(String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ChulBean bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from jsp_chulnab where usid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new ChulBean();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setNalja(rs.getString("nalja"));
				bean.setGubun(rs.getString("gubun"));
				bean.setConte(rs.getString("conte"));
				bean.setDeal(rs.getString("deal"));
				bean.setSuip(rs.getInt("suip"));
				bean.setChul(rs.getInt("chul"));
				bean.setJgum(rs.getInt("jgum"));
				bean.setBigo(rs.getString("bigo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}

	// 출납 내역 조회 (numb)
	public ChulBean getChulnab1(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ChulBean bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from jsp_chulnab where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new ChulBean();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setNalja(rs.getString("nalja"));
				bean.setGubun(rs.getString("gubun"));
				bean.setConte(rs.getString("conte"));
				bean.setDeal(rs.getString("deal"));
				bean.setSuip(rs.getInt("suip"));
				bean.setChul(rs.getInt("chul"));
				bean.setJgum(rs.getInt("jgum"));
				bean.setBigo(rs.getString("bigo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}

	// 출납 내역 아이디 조건으로 조회
	public Vector<ChulBean> getChulList(String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<ChulBean> vlist = new Vector<ChulBean>();
		try {

//			System.out.print("usid : ");
//			System.out.println(usid);
			
			con = pool.getConnection();
		    sql = "select * from jsp_chulnab where usid = ?";
		    pstmt = con.prepareStatement(sql);
		    pstmt.setString(1, usid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ChulBean bean = new ChulBean();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setNalja(rs.getString("nalja"));
				bean.setGubun(rs.getString("gubun"));
				bean.setConte(rs.getString("conte"));
				bean.setDeal(rs.getString("deal"));
				bean.setSuip(rs.getInt("suip"));
				bean.setChul(rs.getInt("chul"));
				bean.setJgum(rs.getInt("jgum"));
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

	// 해당 자료 수
	public int getChulCount(String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = pool.getConnection();
		    sql = "select count(numb) from jsp_chulnab where usid = ?";
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
	
	// 출납내역 수정
	public boolean updateChul(ChulBean bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "update jsp_chulnab set usid=?, nalja=?, gubun=?, conte=?, deal=?,"
					+ "suip=?, chul=?, jgum=?, bigo=? where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsid());
			pstmt.setString(2, bean.getNalja());
			pstmt.setString(3, bean.getGubun());
			pstmt.setString(4, bean.getConte());
			pstmt.setString(5, bean.getDeal());
			pstmt.setInt(6, bean.getSuip());
			pstmt.setInt(7, bean.getChul());
			pstmt.setInt(8, bean.getJgum());
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

	// 출납내역 삭제
	public void deleteChul(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "delete from jsp_chulnab where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// 잔액 계산
	public boolean updateJgum(int numb, int jgum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "update jsp_chulnab set jgum=? where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, jgum);
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

	
	// 차량 유류 관리   =================================================================
	// 차량 유류 내역 추가
	public boolean car_insert(Bean_Car bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert jsp_car(usid,nalja,jang,jong,gum"
					+ ",liter,nukm,bigo)values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsid());
			pstmt.setString(2, bean.getNalja());
			pstmt.setString(3, bean.getJang());
			pstmt.setString(4, bean.getJong());
			pstmt.setInt(5, bean.getGum());
			pstmt.setFloat(6, bean.getLiter());
			pstmt.setInt(7, bean.getNukm());
			pstmt.setString(8, bean.getBigo());

			//System.out.print("uspw : ");
			//System.out.println(bean.getUspw());

			if (pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		return flag;
	}
	
	// 차량 유류 내역 조회 (usid)
	public Bean_Car getCar(String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bean_Car bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from jsp_car where usid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, usid);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Car();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setNalja(rs.getString("nalja"));
				bean.setJang(rs.getString("jang"));
				bean.setJong(rs.getString("jong"));
				bean.setGum(rs.getInt("gum"));
				bean.setLiter(rs.getFloat("liter"));
				bean.setLiter_gum(rs.getFloat("liter_gum"));
				bean.setNukm(rs.getInt("nukm"));
				bean.setJukm(rs.getInt("jukm"));
				bean.setKm_liter(rs.getFloat("km_liter"));
				bean.setKm_gum(rs.getFloat("km_gum"));
				bean.setBigo(rs.getString("bigo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}

	// 차량 유류 내역 조회 (numb)
	public Bean_Car getCar1(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Bean_Car bean = null;
		try {
			con = pool.getConnection();
			String sql = "select * from jsp_car where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				bean = new Bean_Car();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setNalja(rs.getString("nalja"));
				bean.setJang(rs.getString("jang"));
				bean.setJong(rs.getString("jong"));
				bean.setGum(rs.getInt("gum"));
				bean.setLiter(rs.getFloat("liter"));
				bean.setLiter_gum(rs.getFloat("liter_gum"));
				bean.setNukm(rs.getInt("nukm"));
				bean.setJukm(rs.getInt("jukm"));
				bean.setKm_liter(rs.getFloat("km_liter"));
				bean.setKm_gum(rs.getFloat("km_gum"));
				bean.setBigo(rs.getString("bigo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return bean;
	}

	// 차량 유류 내역 아이디 조건으로 조회
	public Vector<Bean_Car> getCarList(String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Vector<Bean_Car> vlist = new Vector<Bean_Car>();
		try {

//			System.out.print("usid : ");
//			System.out.println(usid);
			
			con = pool.getConnection();
		    sql = "select * from jsp_car where usid = ?";
		    pstmt = con.prepareStatement(sql);
		    pstmt.setString(1, usid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Bean_Car bean = new Bean_Car();
				bean.setNumb(rs.getInt("numb"));
				bean.setUsid(rs.getString("usid"));
				bean.setNalja(rs.getString("nalja"));
				bean.setJang(rs.getString("jang"));
				bean.setJong(rs.getString("jong"));
				bean.setGum(rs.getInt("gum"));
				bean.setLiter(rs.getFloat("liter"));
				bean.setLiter_gum(rs.getFloat("liter_gum"));
				bean.setNukm(rs.getInt("nukm"));
				bean.setJukm(rs.getInt("jukm"));
				bean.setKm_liter(rs.getFloat("km_liter"));
				bean.setKm_gum(rs.getFloat("km_gum"));
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

	// 차량 유류 해당 자료 수
	public int getCarCount(String usid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int totalCount = 0;
		try {
			con = pool.getConnection();
		    sql = "select count(numb) from jsp_car where usid = ?";
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
	
	// 차량 유류 내역 수정
	public boolean updateCar(Bean_Car bean) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "update jsp_car set usid=?, nalja=?, jang=?, jong=?, gum=?,"
					+ "liter=?, nukm=?, bigo=? where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getUsid());
			pstmt.setString(2, bean.getNalja());
			pstmt.setString(3, bean.getJang());
			pstmt.setString(4, bean.getJong());
			pstmt.setInt(5, bean.getGum());
			pstmt.setFloat(6, bean.getLiter());
			pstmt.setInt(7, bean.getNukm());
			pstmt.setString(8, bean.getBigo());
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

	// 차량 유류 내역 삭제
	public void deleteCar(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;
		try {
			con = pool.getConnection();
			sql = "delete from jsp_car where numb=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// 차량 유류 주행거리 계산
	public boolean updateJukm(int numb, int jukm) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "update jsp_car set jukm=? where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, jukm);
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

	// 차량 유류 각종 계산
	public boolean updateGum(int numb) {
		Connection con = null;
		PreparedStatement pstmt = null;
		boolean flag = false;
		try {
			con = pool.getConnection();
			String sql = "update jsp_car set liter_gum=gum/liter, km_liter=jukm/liter, "
					   + "km_gum=gum/jukm where numb = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, numb);
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