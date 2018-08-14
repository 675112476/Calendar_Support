package cn.edu.just.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("UTF-8");
		//设置ContentType字段值
		response.setContentType("text/html;charset=utf-8");
		//获取所要下载的文件名称
		String filename = request.getParameter("filename");
		//下载文件所在目录
		String folder = "/images/";
		//获取response对象的输出流
		response.reset(); 
		OutputStream out = response.getOutputStream();
		//通知浏览器以下载的方式打开
        response.setContentType("application/OCTET-STREAM;charset=UTF-8");
		response.addHeader("Content-type", "appllication/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename="+filename);
		//通知文件流读取文件
		InputStream in = getServletContext().getResourceAsStream(folder+filename);
		byte[] buffer = new byte[1024];
		int len;
		//循环取出流中的数据
		while((len = in.read(buffer)) != -1){
			out.write(buffer,0,len);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
