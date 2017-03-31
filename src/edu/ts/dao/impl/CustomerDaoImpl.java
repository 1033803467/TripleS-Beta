package edu.ts.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.ts.dao.BaseDao;
import edu.ts.dao.CustomerDao;
import edu.ts.entity.DataTableRequest;
import edu.ts.entity.Customer;
import edu.ts.entity.CustomerTableItem;
import edu.ts.entity.Page;
import edu.ts.util.DBUtil;
import edu.ts.util.StringUtil;

public class CustomerDaoImpl extends BaseDao implements CustomerDao{
		
		@Override
		public boolean addCustomer(Customer customer) {
			conn=DBUtil.getConnection();
			boolean flag=false;
			String sql="insert into customers(c_name,c_gender,c_birth,c_tel,c_email,c_personid,c_openid) values(?,?,?,?,?,?,?)";
			Object[] obj={customer.getcName(),customer.getcGender(),customer.getcBirth(),customer.getcTel(),customer.getcMail(),customer.getcPersonid(),customer.getcOpenid()};
			if(conn!=null){
				if(update(sql, obj)>=1){
					flag = true;
				}
			}
			return flag;
		}
		@Override
		public boolean deleteCustomer(int cId) {
			conn=DBUtil.getConnection();
			boolean flag=false;
			String sql="update customers set c_state=0 where c_id=?";
			Object[] obj={cId};
			if(conn!=null){
				if(update(sql, obj)>=1){
					flag=true;
				}
			}		
			return flag;
		}
		@Override
		public boolean modifyCustomer(Customer customer) {
			conn=DBUtil.getConnection();
			boolean flag=false;
			String sql="update customers set c_name=?,c_birth=?,c_tel=?,c_email=?,c_personid=?,c_openid=?,c_isrec=?,c_pic=? where c_id=?";
			Object[] obj={customer.getcName(),customer.getcBirth(),customer.getcTel(),customer.getcMail(),customer.getcPersonid(),customer.getcOpenid(),customer.getcIsrec(),customer.getcPic(),customer.getcId()};
			if(conn!=null){
				if(update(sql, obj)>=1){
					flag=true;
				}
			}
			return flag;
		}
		@Override
		public List<Customer> getCustomerList() {
			conn=DBUtil.getConnection();
			List<Customer> list=new ArrayList<Customer>();
			String sql="select * from customers where c_state=1";
			Object[] obj={};	
			try {
				ResultSet rs = query(sql, obj);
				while(rs.next()){
					int cid = rs.getInt("c_id");
					String cname = rs.getString("c_name");
					int cgender=rs.getInt("c_gender");
					Date cbirth=rs.getDate("c_birth");
					String ctel=rs.getString("c_tel");
					String cmail=rs.getString("c_email");
					String cpic=rs.getString("c_pic");
					int cscore=rs.getInt("c_score");
					String cpassword=rs.getString("c_password");
					String cpersonid = rs.getString("c_personid");
					String copenid = rs.getString("c_openid");
					int cisrec = rs.getInt("c_isrec");
					int cstate = rs.getInt("c_state");
					String cdate = rs.getString("c_date");
					Customer customer = new Customer(cid,cname, cgender, cbirth, ctel,cmail,cpic,cscore,cpassword,cpersonid,copenid,cisrec,cstate,cdate);
					list.add(customer);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(conn, ps, rs);
			}
			return list;
		}
		@Override
		public List<Customer> getCustomerByTel(String cTel) {
			conn=DBUtil.getConnection();
			List<Customer> list=new ArrayList<Customer>();
			String sql="select * from customers where c_tel=? and c_state=1";
			Object[] obj={cTel};
			try {
				ResultSet rs = query(sql, obj);
				while(rs.next()){
					int cid = rs.getInt("c_id");
					String cname = rs.getString("c_name");
					int cgender=rs.getInt("c_gender");
					Date cbirth=rs.getDate("c_birth");
					String ctel=rs.getString("c_tel");
					String cmail=rs.getString("c_email");
					String cpic=rs.getString("c_pic");
					int cscore=rs.getInt("c_score");
					String cpassword=rs.getString("c_password");
					String cpersonid = rs.getString("c_personid");
					String copenid = rs.getString("c_openid");
					int cisrec = rs.getInt("c_isrec");
					int cstate = rs.getInt("c_state");
					String cdate = rs.getString("c_date");
					Customer customer = new Customer(cid,cname, cgender, cbirth, ctel,cmail,cpic,cscore,cpassword,copenid,cpersonid,cisrec,cstate,cdate);
					list.add(customer);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(conn, ps, rs);
			}
			return list;
		}
		@Override
		public List<Customer> getCustomerById(int id) {
			conn=DBUtil.getConnection();
			List<Customer> list=new ArrayList<Customer>();
			String sql="select * from customers where c_id=? and c_state=1";
			Object[] obj={id};
			try {
				ResultSet rs = query(sql, obj);
				while(rs.next()){
					int cid = rs.getInt("c_id");
					String cname = rs.getString("c_name");
					int cgender=rs.getInt("c_gender");
					Date cbirth=rs.getDate("c_birth");
					String ctel=rs.getString("c_tel");
					String cmail=rs.getString("c_email");
					String cpic=rs.getString("c_pic");
					int cscore=rs.getInt("c_score");
					String cpassword=rs.getString("c_password");
					String cpersonid = rs.getString("c_personid");
					String copenid = rs.getString("c_openid");
					int cisrec = rs.getInt("c_isrec");
					int cstate = rs.getInt("c_state");
					String cdate = rs.getString("c_date");
					String crec = rs.getString("c_rec");
					Customer customer = new Customer(cid,cname, cgender, cbirth, ctel,cmail,cpic,cscore,cpassword,copenid,cpersonid,crec,cisrec,cstate,cdate);
					list.add(customer);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(conn, ps, rs);  
			}
			return list;
		}

		@Override
		public List<CustomerTableItem> getCustomerByPage(Page page) {
			conn=DBUtil.getConnection();
			List<CustomerTableItem> list=new ArrayList<CustomerTableItem>();
			String sql="select * from customers where c_state=1";
			Object[] obj={};
			if(page!=null){
				sql+=" limit "+((page.getCurrentPage()-1)*page.getpageSize())+","+page.getpageSize();
			}
			try {
				ResultSet rs=query(sql, obj);
				while(rs.next()){
					int cid = rs.getInt("c_id");
					String cname = rs.getString("c_name");
					int cgender=rs.getInt("c_gender");
					Date cbirth=rs.getDate("c_birth");
					String ctel=rs.getString("c_tel");
					String cmail=rs.getString("c_email");
					String cpic=rs.getString("c_pic");
					int cscore=rs.getInt("c_score");
					CustomerTableItem customer = new CustomerTableItem(cid,cname,cgender,cbirth,ctel,cmail,cpic,cscore,cid);
					list.add(customer);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(conn, ps, rs);
			}
			return list;
		}
		@Override
		public List<Customer> getCustomerByPersonid(String personId) {
			conn=DBUtil.getConnection();
			List<Customer> list=new ArrayList<Customer>();
			String sql="select * from customers where c_personid=? and c_state=1";
			Object[] obj={personId};
			try {
				ResultSet rs = query(sql, obj);
				while(rs.next()){
					int cid = rs.getInt("c_id");
					String cname = rs.getString("c_name");
					int cgender=rs.getInt("c_gender");
					Date cbirth=rs.getDate("c_birth");
					String ctel=rs.getString("c_tel");
					String cmail=rs.getString("c_email");
					String cpic=rs.getString("c_pic");
					int cscore=rs.getInt("c_score");
					String cpassword=rs.getString("c_password");
					String cpersonid = rs.getString("c_personid");
					String copenid = rs.getString("c_openid");
					int cisrec = rs.getInt("c_isrec");
					int cstate = rs.getInt("c_state");
					String cdate = rs.getString("c_date");
					Customer customer = new Customer(cid,cname, cgender, cbirth, ctel,cmail,cpic,cscore,cpassword,copenid,cpersonid,cisrec,cstate,cdate);
					list.add(customer);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(conn, ps, rs);
			}
			return list;
		}
		@Override
		public List<Customer> getCustomerByOpenid(String openId) {
			conn=DBUtil.getConnection();
			List<Customer> list=new ArrayList<Customer>();
			String sql="select * from customers where c_openid=? and c_state=1";
			Object[] obj={openId};
			try {
				ResultSet rs = query(sql, obj);
				while(rs.next()){
					int cid = rs.getInt("c_id");
					String cname = rs.getString("c_name");
					int cgender=rs.getInt("c_gender");
					Date cbirth=rs.getDate("c_birth");
					String ctel=rs.getString("c_tel");
					String cmail=rs.getString("c_email");
					String cpic=rs.getString("c_pic");
					int cscore=rs.getInt("c_score");
					String cpassword=rs.getString("c_password");
					String cpersonid = rs.getString("c_personid");
					String copenid = rs.getString("c_openid");
					int cisrec = rs.getInt("c_isrec");
					int cstate = rs.getInt("c_state");
					String cdate = rs.getString("c_date");
					Customer customer = new Customer(cid,cname, cgender, cbirth, ctel,cmail,cpic,cscore,cpassword,copenid,cpersonid,cisrec,cstate,cdate);
					list.add(customer);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(conn, ps, rs);
			}
			return list;
		}
		@Override
		public boolean addCustomerWeixin(Customer customer) {
			conn=DBUtil.getConnection();
			boolean flag=false;
			String sql="insert into customers(c_name,c_gender,c_birth,c_tel,c_email,c_personid,c_openid,c_password) values(?,?,?,?,?,?,?,?)";
			Object[] obj={customer.getcName(),customer.getcGender(),customer.getcBirth(),customer.getcTel(),customer.getcMail(),customer.getcPersonid(),customer.getcOpenid(),customer.getcPassword()};
			if(conn!=null){
				if(update(sql, obj)>=1){
					flag = true;
				}
			}
			return flag;
		}
		@Override
		public boolean modifyCustomerWeixin(Customer customer) {
			conn=DBUtil.getConnection();
			boolean flag=false;
			String sql="update customers set c_name=?,c_birth=?,c_tel=?,c_email=?,c_personid=?,c_openid=?,c_password=? where c_id=?";
			Object[] obj={customer.getcName(),customer.getcBirth(),customer.getcTel(),customer.getcMail(),customer.getcPersonid(),customer.getcOpenid(),customer.getcPassword(),customer.getcId()};
			if(conn!=null){
				if(update(sql, obj)>=1){
					flag=true;
				}
			}
			return flag;
		}
		@Override
		public List<CustomerTableItem> getCustomerPaging(DataTableRequest dtr) {
			conn=DBUtil.getConnection();
			List<CustomerTableItem> list=new ArrayList<CustomerTableItem>();
			String baseSql="select * from customers where c_state=1";
			String whereSql="";
			String limitSql="";
			String orderSql="";
			if(dtr.getSearch()!=null && !dtr.getSearch().equals("")){
				whereSql="c_id LIKE '%"+dtr.getSearch().toString()+"%' or c_name LIKE '%"+dtr.getSearch().toString()+"%' or c_tel LIKE '%"+dtr.getSearch().toString()+"%' or c_email LIKE '%"+dtr.getSearch().toString()+"%' or c_score LIKE '%"+dtr.getSearch().toString()+"%'";
				if(!StringUtil.isChinese(dtr.getSearch().toString())){
					whereSql+=" or c_birth LIKE '%"+dtr.getSearch().toString()+"%'";
				}else{
					whereSql+=" ";
				}
				if(dtr.getSearch().toString().equals("男")){
					whereSql+=" or c_gender=0";
				}
				if(dtr.getSearch().toString().equals("女")){
					whereSql+=" or c_gender=1";
				}
				whereSql = " and("+whereSql+")";
			}
			if(dtr.getStart()!=null && !dtr.getLength().equals("-1")){
				limitSql=" limit "+dtr.getStart().toString()+","+dtr.getLength().toString();
			}
			if(dtr.getOrderDir()!=null && dtr.getOrderColumn()!=null){
				int i = Integer.parseInt(dtr.getOrderColumn().toString());
				switch(i){
//					case 0: orderSql = "";break;//" order by c_pic "+dtr.getOrderDir();break;
					case 1: orderSql = " order by c_id "+dtr.getOrderDir();break;
					case 2: orderSql = " order by c_name "+dtr.getOrderDir();break;
					case 3: orderSql = " order by c_birth "+dtr.getOrderDir();break;
					case 4: orderSql = " order by c_gender "+dtr.getOrderDir();break;
					case 5: orderSql = " order by c_tel "+dtr.getOrderDir();break;
					case 6: orderSql = " order by c_email "+dtr.getOrderDir();break;
					case 7: orderSql = " order by c_score "+dtr.getOrderDir();break;
					default: orderSql = "";
				}
			}
			try {
				System.out.println(baseSql+whereSql+orderSql+limitSql);
				ResultSet rs=query(baseSql+whereSql+orderSql+limitSql);
				while(rs.next()){
					int cid = rs.getInt("c_id");
					String cname = rs.getString("c_name");
					int cgender=rs.getInt("c_gender");
					Date cbirth=rs.getDate("c_birth");
					String ctel=rs.getString("c_tel");
					String cmail=rs.getString("c_email");
					String cpic=rs.getString("c_pic");
					int cscore=rs.getInt("c_score");
					CustomerTableItem customer = new CustomerTableItem(cid,cname, cgender, cbirth, ctel,cmail,cpic,cscore,cid);
					list.add(customer);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBUtil.close(conn, ps, rs);
			}
			return list;
		}
		@Override
		public boolean CheckCustomerModify(String tel, String c_id) {
			conn= DBUtil.getConnection();
			boolean flag=false;
			String sql="select * from customers where c_tel=? and c_id!=? and c_state=1";
			Object[] obj={tel,c_id};
			if(conn!=null){
				try{
					rs = query(sql,obj);
					if (rs.next()==false)flag=true;
				}
				catch (SQLException e){
					e.printStackTrace();
				}finally {
					DBUtil.close(conn,ps,rs);
				}
			}
			return flag;
		}
		@Override
		public boolean UpdateRec(int cid, String rec) {
			conn=DBUtil.getConnection();
			boolean flag=false;
			String sql="update customers set c_rec=? where c_id=?";
			Object[] obj={rec,cid};
			if(conn!=null){
				if(update(sql, obj)>=1){
					flag=true;
				}
			}
			return flag;
		}
}
