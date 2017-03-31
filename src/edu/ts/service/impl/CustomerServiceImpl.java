package edu.ts.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.ConnectionPoolDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import edu.ts.dao.CustomerDao;
import edu.ts.entity.DataTableRequest;
import edu.ts.dao.FeedbackDao;
import edu.ts.dao.GoodDao;
import edu.ts.dao.OrderDao;
import edu.ts.dao.OrderDetailDao;
import edu.ts.dao.OrderTableItemDao;
import edu.ts.dao.PersonDao;
import edu.ts.dao.impl.CustomerDaoImpl;
import edu.ts.dao.impl.FeedbackDaoImpl;
import edu.ts.dao.impl.GoodDaoImpl;
import edu.ts.dao.impl.OrderDaoImpl;
import edu.ts.dao.impl.OrderDetailDaoImpl;
import edu.ts.dao.impl.OrderTableItemDaoImpl;
import edu.ts.dao.impl.PersonDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.entity.CustomerTableItem;
import edu.ts.entity.Feedback;
import edu.ts.entity.Good;
import edu.ts.entity.MostPurchasedGood;
import edu.ts.entity.Order;
import edu.ts.entity.OrderDetail;
import edu.ts.entity.OrderTableItem;
import edu.ts.entity.Page;
import edu.ts.entity.RecommInfo;
import edu.ts.service.CustomerService;
import edu.ts.util.DBUtil;

public class CustomerServiceImpl implements CustomerService{
	CustomerDao cd = new CustomerDaoImpl();
	OrderTableItemDao otid = new OrderTableItemDaoImpl();
	GoodDao gd = new GoodDaoImpl();
	@Override
	public boolean add(Customer customer) {
		PersonDao personDao = new PersonDaoImpl();
		String personId = personDao.createPerson(PersonDao.PERSON_GROUP_ID, customer.getcName(), customer.getcTel());
		customer.setcPersonid(personId);
		return cd.addCustomer(customer);
	}

	@Override
	public boolean modify(Customer customer) {
		return cd.modifyCustomer(customer);
	}
    
	@Override
	public boolean delete(int cId) {
		return cd.deleteCustomer(cId);
	}

	@Override
	public Object[] queryByPage(String currentPage,int pageSize) {
		Object obj[] =  new Object[2];
		List<CustomerTableItem> listSet = new ArrayList<CustomerTableItem>();
		int currentPg=(currentPage==null?1:Integer.parseInt(currentPage));
		cd.getCustomerByPage(null);
		int record = cd.getCustomerByPage(null).size();
		Page page=new Page(currentPg,pageSize,record);
		listSet=cd.getCustomerByPage(page);
		obj[0]=listSet;
		obj[1]=page;
		return obj;
	}

	@Override
	public List<Customer> getAll() {
		return cd.getCustomerList();
	}


	@Override
	public List<Customer> getByTel(String cTel) {
		return cd.getCustomerByTel(cTel);
	}

	@Override
	public List<Customer> getById(int id) {
		return cd.getCustomerById(id);
	}

	@Override
	public List<Customer> getByPersonid(String personId) {
		return cd.getCustomerByPersonid(personId);
	}

	@Override
	public List<Customer> getByOpenid(String openId) {
		return cd.getCustomerByOpenid(openId);
	}

	@Override
	public List<Feedback> getFeedbackByCustomerId(int cId) {
		FeedbackDao fd = new FeedbackDaoImpl();
		return fd.getByCustomerId(cId);
	}

	@Override
	public List<Good> getRecBycId(int cId) {
		// TODO 放在Sprint3完成
		return null;
	}

	@Override
	public List<OrderTableItem> getRecentOrderBycId(int cId) {
		return otid.getRecentBycId(cId);
	}

	@Override
	public List<MostPurchasedGood> getCustomerLoved(int cId) {
		return gd.getMostPurchasedBycId(cId);
	}
	
	@Override
    public boolean bindCustomer(Customer customer, String openId) {
        customer.setcOpenid(openId);
        CustomerDao customerDao = new CustomerDaoImpl();
        return customerDao.modifyCustomerWeixin(customer);
   }

	@Override
   public boolean registerCustomerByWx(Customer customer) {
		PersonDao personDao = new PersonDaoImpl();
		String personId = personDao.createPerson(PersonDao.PERSON_GROUP_ID, customer.getcName(), customer.getcTel());
		customer.setcPersonid(personId);
       CustomerDao customerDao = new CustomerDaoImpl();
       return customerDao.addCustomerWeixin(customer);

   }

	@Override
   public boolean modifyPasswordByOpenid(String openid, String password) {
       CustomerDao customerDao = new CustomerDaoImpl();
       List<Customer > lists  = customerDao.getCustomerByOpenid(openid);
       if(lists.size()==0) return false;
       Customer customer = lists.get(0);
       customer.setcPassword(password);
       return customerDao.modifyCustomerWeixin(customer);
   }

	@Override
   public boolean modifyPersonidByOpenid(String openid, String personid) {
       CustomerDao customerDao = new CustomerDaoImpl();
       List<Customer > lists  = customerDao.getCustomerByOpenid(openid);
       if(lists.size()==0) return false;
       Customer customer = lists.get(0);
       customer.setcPersonid(personid);
       customer.setcIsrec(1);
       return customerDao.modifyCustomer(customer);
   }

	@Override
	public boolean importCSV(String fileName) {
		File file = new File(fileName);
		Boolean flag=false;
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
               
            	String [] item=tempString.split("[,]");           	
            		Customer customer=new Customer();
            		customer.setcName(item[0]);
            		customer.setcGender(Integer.parseInt(item[1]));
            		Date date=new Date(item[2]);
            		customer.setcBirth(date);
            		customer.setcTel(item[3]);
            		customer.setcMail(item[4]);
            		customer.setcPersonid(item[5]);
            		customer.setcOpenid(item[6]);
            		CustomerDao cd=new CustomerDaoImpl();
            		cd.addCustomer(customer);       	
            }
            reader.close();
            flag=true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
		return flag;
	}

	@Override
	public List<CustomerTableItem> getCustomerPaging(DataTableRequest dtr) {
		return cd.getCustomerPaging(dtr);
	}

	@Override
	public boolean CheckCustomerModify(String tel, String c_id) {
		boolean flag=false;
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		flag = cdi.CheckCustomerModify(tel,c_id);
		return flag;
	}

	@Override
	public boolean UpdateRecBycId(int id, String rec) {
		return cd.UpdateRec(id, rec);
	}
}
