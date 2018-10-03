package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Application;

import dao.ChessDAO;
import dao.UserLoginDAO;
import entity.ChessModel;
import entity.UserLoginModel;

public class ChessServlet extends HttpServlet {
		private ChessDAO chessDao;
	/**
		 * Constructor of the object.
		 */
	public ChessServlet() {
		super();
		chessDao=new ChessDAO();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				this.doPost(request, response);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action;
		action=request.getParameter("action");
		if(action.equals("��¼")){
			this.dologin(request, response);						//��¼
		}
		if(action.equals("ע��")){
			this.register(request, response);
		}
		if(action.equals("addchess")){
			this.addchess(request, response);
		}
	}
	
	
	public void dologin(HttpServletRequest request, HttpServletResponse response)
																							throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		UserLoginModel loginModel=new UserLoginModel();
		loginModel.setUsername(username);
		loginModel.setPassword(password);
		UserLoginDAO loginDao=new UserLoginDAO();
		boolean islogin=loginDao.dologin(loginModel.getUsername(), loginModel.getPassword());//�����Ƿ�ɹ���½
		if(islogin){				//����ɹ���ת��׼��ҳ��
			request.setAttribute("username", username);
			request.getRequestDispatcher("/GameReady.jsp").forward(request, response);
		}else{						//ʧ������ת����½ҳ��
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response)
																							throws ServletException, IOException{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		UserLoginDAO loginDao=new UserLoginDAO();
		boolean isregister=loginDao.register(username, password);
		if(isregister){
			request.setAttribute("isRegister","true" );
			request.getRequestDispatcher("/doRegister.jsp").forward(request, response);
		}else{
			request.setAttribute("isRegister","false" );
			request.getRequestDispatcher("/doRegister.jsp").forward(request, response);
		}
	}
	
	public void addchess(HttpServletRequest request, HttpServletResponse response)
																								throws ServletException, IOException{
		response.setCharacterEncoding("utf-8");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out=response.getWriter();
		String color=request.getParameter("color");
		int row=Integer.parseInt(request.getParameter("row"));
		int col=Integer.parseInt(request.getParameter("col"));
		ChessModel chessModel=chessDao.getChessModel();
		String nextstep=chessDao.nextstep(chessModel.getStep());
		//�����û������������һ�������ɫ���ö��ϣ�����Ӹ����ӵ�����
		if(nextstep.equals(color)){
			chessDao.AddChess(color, row, col);
			chessModel.setStep(chessModel.getStep()+1);										//������һ
		}
		String blacklist=this.mapTostring(chessModel.getBlackChess());		//�������mapװ���ַ���
		out.print(blacklist+".");																									//�����ݴ���ǰ��
		String whitelist=this.mapTostring(chessModel.getWhiteChess());	//�������mapװ���ַ���
		out.print(whitelist+".");																								//�����ݴ���ǰ��
		if(getServletContext().getAttribute("isEnd")!=null&&
				getServletContext().getAttribute("isEnd").equals("black")){
			out.print("black");
		}
		if(getServletContext().getAttribute("isEnd")!=null&&
				getServletContext().getAttribute("isEnd").equals("white")){
			out.print("white");
		}
		if(color.equals("black")){
			if(chessDao.isEnd(chessModel.getBlackChess(), row, col)){
					getServletContext().setAttribute("isEnd", "black");				//������־,����ǰ��
					out.print("black");
			}
		}else if(color.equals("white")){
			if(chessDao.isEnd(chessModel.getWhiteChess(), row, col)){
				getServletContext().setAttribute("isEnd", "white");		   		 	//������־,����ǰ��	
				out.print("white");
			}
		}
	}
	
				//��mapת���ַ���
	public String mapTostring(ArrayList<HashMap<Integer, Integer>> map){
		String chesslist="";
		Iterator<HashMap<Integer, Integer>> it=map.iterator();
		while(it.hasNext()){
			HashMap<Integer, Integer> hashmap=it.next();
			Iterator<Integer> itr=hashmap.keySet().iterator();
			while(itr.hasNext()){
				int key=itr.next();
				int value=hashmap.get(key);
				chesslist+=key+","+value+",";
			}
		}
		return chesslist;
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
