package edu.ts.test.serviceTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ts.entity.Customer;
import edu.ts.entity.Page;
import edu.ts.service.CustomerService;
import edu.ts.service.impl.CustomerServiceImpl;

public class CustomerServiceImplTest {

	@Before
	public void setUp() throws Exception {
	}
	CustomerService cs = new CustomerServiceImpl();
	@Test
	public void testAdd() {
		assertEquals(true, cs.add(new Customer("zz",0,"1234")));
	}

	@Test
	public void testModify() {
		assertEquals(true, cs.add(new Customer(7,"test",0,"test")));
	}

	@Test
	public void testDelete() {
		assertEquals(true, cs.delete(0));
	}

	@Test
	public void testQueryByPage() {
		List<Customer> listSet=new ArrayList<Customer>();
		String currentPage="9";//从页面获取当前的页数
        listSet = (List<Customer>) cs.queryByPage(currentPage,20)[0];
        Page page = (Page) cs.queryByPage(currentPage,20)[1];
        System.out.println(listSet.size());
        System.out.println(page.getCurrentPage());
        System.out.println(page.getPageNumber());
        System.out.println(page.getpageSize());
        System.out.println(page.getRecord());
//        assertEquals(11, listSet.size());
//        assertEquals(11, page.getCurrentPage());
//        assertEquals(11, page.getPageNumber());
//        assertEquals(20, page.getpageSize());
//        assertEquals(211, page.getRecord());
	}

}
