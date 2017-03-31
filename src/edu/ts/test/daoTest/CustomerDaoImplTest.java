package edu.ts.test.daoTest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ts.dao.CustomerDao;
import edu.ts.dao.impl.CustomerDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.entity.Page;

public class CustomerDaoImplTest {

	@Before
	public void setUp() throws Exception {
	}
	CustomerDao cd = new CustomerDaoImpl();
	@Test
	public void testAddCustomer() {
		for(int i=0;i<200;i++){
			Date date = null;
			try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse("1994-06-04");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Customer customer=new Customer("lili"+i,0,date,"13145038884","zm@163.com",0);
			cd.addCustomer(customer);
		}
		Customer c = new Customer("zmz",0,"1234");
		assertEquals(true, cd.addCustomer(c));
	}

	@Test
	public void testDeleteCustomer() {
		assertEquals(true, cd.deleteCustomer(0));
	}

	@Test
	public void testModifyCustomer() {
		Customer c = new Customer(7,"test",0,"test");
		assertEquals(true, cd.modifyCustomer(c));
	}

	@Test
	public void testGetCustomerList() {
		List<Customer> list = new ArrayList<Customer>();
		list = cd.getCustomerList();
		assertEquals(true,list.size()!=0);
	}

	@Test
	public void testGetCustomerByTel() {
		List<Customer> list = new ArrayList<Customer>();
		list = cd.getCustomerByTel("1234");
		assertEquals(true,list.size()!=0);
	}

	@Test
	public void testGetCustomerById() {
		List<Customer> list = new ArrayList<Customer>();
		list = cd.getCustomerById(7);
		assertEquals(true,list.size()!=0);
	}

	

}
