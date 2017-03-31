package test.edu.ts.dao.impl; 

import edu.ts.dao.impl.CustomerDaoImpl;
import edu.ts.entity.Customer;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/** 
* CustomerDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 22, 2017</pre> 
* @version 1.0 
*/
@RunWith(Parameterized.class)
public class CustomerDaoImplTest {
    Customer customer;

    static CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();

    public CustomerDaoImplTest(Customer customer) {
        this.customer = customer;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Customer[] data = new Customer[100];
        Customer customer = new Customer();
        customer.setcGender(1);
        customer.setcName("asdf");
        customer.setcMail("sdfsdf@qq.com");
        customer.setcPassword("123456");

        Random random = new Random();
        int i=1000;
        for(int k=1;k<100;k++){
            String ctel="138";
            i++;
            customer.setcId(i);

            for (int j=0;j<8;j++){
                ctel = ctel+random.nextInt(9);
            }
            System.out.println(ctel);
            customer.setcTel(ctel);
            data[k]=(customer);
        }
        Collection<Object[]> resdata= new ArrayList<Object[]>();
        resdata.add(data);
        return resdata;
    }



@Before
public void before() throws Exception { 

    //customer.setcId(i);

}

@After
public void after() throws Exception {
    //i++;
}

    @BeforeClass
public static  void setUpBeforeClass() throws Exception {


    }
    @AfterClass
    public static void setUpAfterClass() throws Exception {

    }

/** 
* 
* Method: addCustomer(Customer customer) 
* 
*/ 
@Test
public void testAddCustomer() throws Exception {


        assertEquals(true,customerDaoImpl.addCustomer(customer)) ;


} 

/** 
* 
* Method: deleteCustomer(int cId) 
* 
*/ 
@Test
public void testDeleteCustomer() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: modifyCustomer(Customer customer) 
* 
*/ 
@Test
public void testModifyCustomer() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCustomerList() 
* 
*/ 
@Test
public void testGetCustomerList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCustomerByTel(String cTel) 
* 
*/ 
@Test
public void testGetCustomerByTel() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCustomerById(int id) 
* 
*/ 
@Test
public void testGetCustomerById() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCustomerByPage(Page page) 
* 
*/ 
@Test
public void testGetCustomerByPage() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCustomerByPersonid(String personId) 
* 
*/ 
@Test
public void testGetCustomerByPersonid() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCustomerByOpenid(String openId) 
* 
*/ 
@Test
public void testGetCustomerByOpenid() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: addCustomerWeixin(Customer customer) 
* 
*/ 
@Test
public void testAddCustomerWeixin() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: modifyCustomerWeixin(Customer customer) 
* 
*/ 
@Test
public void testModifyCustomerWeixin() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getCustomerPaging(DataTableRequest dtr) 
* 
*/ 
@Test
public void testGetCustomerPaging() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: CheckCustomerModify(String tel, String c_id) 
* 
*/ 
@Test
public void testCheckCustomerModify() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: UpdateRec(int cid, String rec) 
* 
*/ 
@Test
public void testUpdateRec() throws Exception { 
//TODO: Test goes here... 
} 


} 
