package edu.ts.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ts.service.CustomerService;
import edu.ts.service.FaceService;
import edu.ts.service.impl.CustomerServiceImpl;
import edu.ts.service.impl.FaceServiceImpl;

public class DetectFaceServlet extends HttpServlet {

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
		String faceId=null;
		String personId=null;
        String url = "http://118.89.31.153:8080/";
        FaceService fs =new FaceServiceImpl();
		String str =  url+request.getSession().getAttribute("filename");
		faceId = fs.getFaceId(str);
		if(faceId==null){
			request.setAttribute("flag", 0 );//0表示网络连接失败
			request.getRequestDispatcher("jsp/imgUpload.jsp").forward(request, response);
		}
		if(faceId.equals("-1")){
			request.setAttribute("flag", 0 );//0表示未识别人脸
			request.getRequestDispatcher("jsp/imgUpload.jsp").forward(request, response);
		}
		personId = fs.identifyFace("1", faceId);
		CustomerService cs = new CustomerServiceImpl();
		try{
			int id = cs.getByPersonid(personId).get(0).getcId();
			request.setAttribute("cid",id);
			request.getRequestDispatcher("/CustomerDetailServlet").forward(request, response);
		}catch(IndexOutOfBoundsException e){
			request.setAttribute("flag", 0 );//0表示未识别人脸
			request.getRequestDispatcher("jsp/imgUpload.jsp").forward(request, response);
		}
	}

}
