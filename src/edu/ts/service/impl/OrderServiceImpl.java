package edu.ts.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.ts.dao.CustomerDao;
import edu.ts.dao.GoodDao;
import edu.ts.dao.OrderDao;
import edu.ts.dao.OrderDetailDao;
import edu.ts.dao.OrderTableItemDao;
import edu.ts.dao.OrderTableItemDetailDao;
import edu.ts.dao.impl.CustomerDaoImpl;
import edu.ts.dao.impl.GoodDaoImpl;
import edu.ts.dao.impl.OrderDaoImpl;
import edu.ts.dao.impl.OrderDetailDaoImpl;
import edu.ts.dao.impl.OrderTableItemDaoImpl;
import edu.ts.dao.impl.OrderTableItemDetailDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.entity.DataTableRequest;
import edu.ts.entity.Good;
import edu.ts.entity.Order;
import edu.ts.entity.OrderDetail;
import edu.ts.entity.OrderTableItem;
import edu.ts.entity.OrderTableItemDetail;
import edu.ts.entity.Page;
import edu.ts.entity.WxOrderItem;
import edu.ts.service.OrderService;

public class OrderServiceImpl implements OrderService{
	
	OrderDao od = new OrderDaoImpl();
	OrderTableItemDao otid = new OrderTableItemDaoImpl();
	OrderTableItemDetailDao otidd = new OrderTableItemDetailDaoImpl();
	
	@Override
	public List<OrderTableItem> getAllOrderTableItem() {
		return otid.getAll();
	}
	@Override
	public List<OrderTableItem> getOrderTableItemByCustomerId(int cId) {
		return otid.getByCustomerId(cId);
	}
	@Override
	public List<OrderTableItemDetail> getOrderTableItemDetailByoId(int oId) {
		return otidd.getOrderTableItemDetailByoId(oId);
	}
	@Override
	public List<OrderTableItemDetail> getOrderTableItemDetailBygId(int gId) {
		return otidd.getOrderTableItemDetailBygId(gId);
	}
	@Override
	public boolean importCSV(String fileName) {
		Boolean flag=false;
		File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) 
            {               
            	String [] item=tempString.split("[,]");     	
            		OrderDao orderDao=new OrderDaoImpl();
            		if(item[3].equals("0"))
            		{
            			Order od=new Order(Integer.parseInt(item[0]),Integer.parseInt(item[1]),item[2]);
            			if(!orderDao.add(od)){
            				break;
            			}
            			//orderDao.getMaxOrderId();
            		}
            		else if(item[3].equals("1"))
            		{
            			OrderDetail ods=new OrderDetail(Integer.parseInt(item[0]),orderDao.getMaxOrderId(),Integer.parseInt(item[2]),Integer.parseInt(item[3]));
            			OrderDetailDao odd=new OrderDetailDaoImpl();
            			if(!odd.add(ods)){
            				break;
            			}
            		}else{
            			break;
            		}
            }
            if((tempString = reader.readLine()) == null){
            	flag=true;
            }
            reader.close();
        }catch(NumberFormatException e){
        	e.printStackTrace();
            return flag;
        } 
        catch (IOException e) {
            e.printStackTrace();
            return flag;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                	return flag;
                }
            }
        }
		return flag;
	}
	@Override
	public Object[] queryByPage(String currentPage, int pageSize) {
		Object obj[] =  new Object[2];
		List<OrderTableItem> listSet = new ArrayList<OrderTableItem>();
		int currentPg=(currentPage==null?1:Integer.parseInt(currentPage));
		otid.getOrderTableItemByPage(null);
		int record = otid.getOrderTableItemByPage(null).size();
		Page page=new Page(currentPg,pageSize,record);
		listSet=otid.getOrderTableItemByPage(page);
		obj[0]=listSet;
		obj[1]=page;
		return obj;
	}
	@Override
	public List<Order> getOrderByoId(int oId) {
		return od.getByOrderId(oId);
	}
	@Override
	public List<OrderTableItem> getOrderPaging(DataTableRequest dtr) {
		return otid.getOrderPaging(dtr);
	}
	@Override
	public List<WxOrderItem> getAllWxOrderItem(String openid) {
		return od.getAllWxOrderItem(openid);
	}
	
}
