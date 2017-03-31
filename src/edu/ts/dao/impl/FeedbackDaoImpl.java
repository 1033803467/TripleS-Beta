package edu.ts.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ts.dao.BaseDao;
import edu.ts.dao.FeedbackDao;
import edu.ts.entity.Feedback;
import edu.ts.util.DBUtil;

public class FeedbackDaoImpl extends BaseDao implements FeedbackDao{

	@Override
	public boolean add(Feedback f) {
		conn=DBUtil.getConnection();
		boolean flag=false;
		String sql="insert into feedbacks(c_id,f_message,f_date) values(?,?,?)";
		Object[] obj={f.getcId(),f.getfMessage(),f.getfDate()};
		if(conn!=null){
			if(update(sql, obj)>=1){
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public boolean process(int fId) {
		conn=DBUtil.getConnection();
		boolean flag=false;
		String sql="update feedbacks set f_state=1 where f_id=?";
		Object[] obj={fId};
		if(conn!=null){
			if(update(sql, obj)>=1){
				flag=true;
			}
		}
		return flag;
	}

	@Override
	public List<Feedback> getAllUnprocessed() {
		conn=DBUtil.getConnection();
		List<Feedback> list=new ArrayList<Feedback>();
		String sql="select * from feedbacks where f_state=0";
		Object[] obj={};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int fid = rs.getInt(1);
				int cid = rs.getInt(2);
				String fmessage = rs.getString(3);
				String fdate = rs.getString(4);
				int fstate = rs.getInt(5);
				Feedback f = new Feedback(fid,cid, fmessage, fdate, fstate);
				list.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<Feedback> getByCustomerId(int cId) {
		conn=DBUtil.getConnection();
		List<Feedback> list=new ArrayList<Feedback>();
		String sql="select * from feedbacks where c_id=?";
		Object[] obj={cId};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int fid = rs.getInt(1);
				int cid = rs.getInt(2);
				String fmessage = rs.getString(3);
				String fdate = rs.getString(4);
				int fstate = rs.getInt(5);
				Feedback f = new Feedback(fid,cid, fmessage, fdate, fstate);
				list.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<Feedback> getByState(int state) {
		conn=DBUtil.getConnection();
		List<Feedback> list=new ArrayList<Feedback>();
		String sql="select * from feedbacks where f_state=?";
		Object[] obj={state};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int fid = rs.getInt(1);
				int cid = rs.getInt(2);
				String fmessage = rs.getString(3);
				String fdate = rs.getString(4);
				int fstate = rs.getInt(5);
				Feedback f = new Feedback(fid,cid, fmessage, fdate, fstate);
				list.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

	@Override
	public List<Feedback> getByFid(int fId) {
		conn=DBUtil.getConnection();
		List<Feedback> list=new ArrayList<Feedback>();
		String sql="select * from feedbacks where f_id=?";
		Object[] obj={fId};
		try {
			ResultSet rs = query(sql, obj);
			while(rs.next()){
				int fid = rs.getInt(1);
				int cid = rs.getInt(2);
				String fmessage = rs.getString(3);
				String fdate = rs.getString(4);
				int fstate = rs.getInt(5);
				Feedback f = new Feedback(fid,cid, fmessage, fdate, fstate);
				list.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}

}
