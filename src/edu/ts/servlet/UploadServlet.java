package edu.ts.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.ts.util.FTPUtil;

public class UploadServlet extends HttpServlet {
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String url = "http://118.89.31.153:8080/";
		//获取请求类型
		int type = Integer.parseInt(request.getParameter("type"));
        boolean flag = ServletFileUpload.isMultipartContent(request);
        String path=request.getServletContext().getRealPath("upload");
        if(flag){
			FileItemFactory fileFactory=new DiskFileItemFactory();
			ServletFileUpload sfu=new ServletFileUpload(fileFactory);
			String uploadPath = getServletContext().getRealPath("./") + File.separator + "upload";
			File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }
        	try {
        		List<FileItem> list = sfu.parseRequest(request);
				int y=0;
				for(int i=0;i<list.size();i++){
				FileItem item=list.get(i);
				if(item.isFormField()){//普通的文本 域
				}
				else{
					String filename=item.getName();
					File file = new File(uploadPath+ File.separator + filename);
					item.write(file);
					switch(type){
						case 1:{
							request.getSession().setAttribute("orderCSV", path+ File.separator + filename);
							break;
							}
						case 2:{
							request.getSession().setAttribute("goodCSV", path+ File.separator + filename);
							break;
							}
						case 3:{
							InputStream input = new FileInputStream(file);				
							FTPUtil.uploadFile(filename, input);
							request.getSession().setAttribute("filename", filename);
							break;
						}
						default:{}
					}
				}
				}

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

        }
	}

}
