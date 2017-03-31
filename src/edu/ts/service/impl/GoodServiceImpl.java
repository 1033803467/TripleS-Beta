package edu.ts.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ts.dao.GoodDao;
import edu.ts.dao.impl.GoodDaoImpl;
import edu.ts.entity.Good;
import edu.ts.entity.Page;
import edu.ts.service.GoodService;

public class GoodServiceImpl implements GoodService{
	GoodDao gd = new GoodDaoImpl();
	
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
            		Good good=new Good();
            		good.setgCatagory(item[0]);
            		good.setgName(item[1]);
            		good.setgPrice(Double.parseDouble(item[2]));
            		good.setgPic(item[3]);
            		good.setgDesc(item[4]);
            		good.setgSpecification(item[5]);
            		good.setgBrand(item[6]);
            		GoodDao goodDao=new GoodDaoImpl();
            		goodDao.add(good);           	
            }
            reader.close();
            flag=true;
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
		List<Good> listSet = new ArrayList<Good>();
		int currentPg=(currentPage==null?1:Integer.parseInt(currentPage));
		gd.getGoodByPage(null);
		int record = gd.getGoodByPage(null).size();
		Page page=new Page(currentPg,pageSize,record);
		listSet=gd.getGoodByPage(page);
		obj[0]=listSet;
		obj[1]=page;
		return obj;
	}

	@Override
	public List<Good> getAll() {
		return gd.getAll();
	}

	@Override
	public List<Good> getById(int id) {
		return gd.getBygId(id);
	}

	@Override
	public Object[] searchGood(String content, String currentPage, int pageSize) {
		Object obj[] =  new Object[2];
		List<Good> listSet = new ArrayList<Good>();
		int currentPg=(currentPage==null?1:Integer.parseInt(currentPage));
		int record = gd.search(content,null).size();
		Page page=new Page(currentPg,pageSize,record);
		listSet=gd.search(content,page);
		obj[0]=listSet;
		obj[1]=page;
		return obj;
	}

}
