package edu.wx.servlet;

import edu.ts.dao.PersonDao;
import edu.ts.dao.PersonGroupDao;
import edu.ts.dao.impl.PersonDaoImpl;
import edu.ts.dao.impl.PersonGroupDaoImpl;
import edu.ts.entity.Customer;
import edu.ts.entity.PersonGroup;
import edu.ts.service.CustomerService;
import edu.ts.service.FaceService;
import edu.ts.service.impl.CustomerServiceImpl;
import edu.ts.service.impl.FaceServiceImpl;
import edu.ts.util.FTPUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ListModel;

import java.io.*;
import java.util.List;

/**
 * Created by jose on 2017/3/8.
 */
public class UploadFaceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        String openid = request.getParameter("openid");
        //String openid = "oYJFu1INCd5ez2vNN07yyz5ZqJHk";
        System.out.println(openid);
        String url = "http://118.89.31.153:8080/";
        String faceId = null;
        String personId = null;
        boolean flag = ServletFileUpload.isMultipartContent(request);
        if(flag){
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            ServletFileUpload  sfu = new ServletFileUpload(fileItemFactory);
            String uploadPath = getServletContext().getRealPath("/")+"upload";
            File uploadDir = new File(uploadPath);
            System.out.println(uploadDir.exists());
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            try {
                List<FileItem> list =  sfu.parseRequest(request);
                for (int i = 0; i < list.size(); i++) {
                    FileItem item = list.get(i);
                    if(item.isFormField()){//如果是普通的文本域
                    }else{
                        String filename = item.getName();
                        System.out.println(filename);
                        String newfilename = openid+".jpg";
                        File file = new File(uploadPath+File.separator+newfilename);
                        item.write(file);
                        InputStream input = new FileInputStream(file);
                        FTPUtil.uploadFile(newfilename,input);
                        FaceService fs = new FaceServiceImpl();
                        url+=newfilename;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("上传成功");
            CustomerService customerService = new CustomerServiceImpl();
            List<Customer> list = customerService.getByOpenid(openid);
            Customer customer = list.get(0);
            customer.setcPic(url);
            customerService.modify(customer);
//            personId = customer.getcPersonid();
//            PersonDao personDao = new PersonDaoImpl();
//            System.out.println("正在添加");
//            personDao.addFaceToPerson("1", personId, list.get(0).getcTel(), url);
//            PersonGroupDao personGroupDao = new PersonGroupDaoImpl();
//            System.out.println("正在训练");
//            personGroupDao.trainPersongroup("1");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
