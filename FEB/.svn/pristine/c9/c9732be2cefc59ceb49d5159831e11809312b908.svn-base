package com.sist.feb.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.member.dao.MemberRegisterDao;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.member.service.MemberRegisterService;

/**
 * Servlet implementation class MemberRegisterController
 */
@WebServlet(urlPatterns = { "/member/member.register" })
public class MemberRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger LOG = Logger.getLogger(MemberRegisterController.class);   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberRegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	String register = StringUtil.nvl(request.getParameter("register"), "register");
    	LOG.debug("register :"+register);
    	
		LOG.debug("-----------------------");
		LOG.debug("--register--");
		
		String email = StringUtil.nvl(request.getParameter("email"),"");
		String name = StringUtil.nvl(request.getParameter("name"),"");
		String pw =StringUtil.nvl(request.getParameter("pw"),"");
		String location =StringUtil.nvl(request.getParameter("locationName"),"");
    	
		LOG.debug("-----------------------");
		LOG.debug("--email : "+email);
		LOG.debug("--name : "+name);
		LOG.debug("--pw : "+pw);
		LOG.debug("--location : "+location);
		LOG.debug("-----------------------");
		
		MemberVO param = new MemberVO();
		
		param.setEmail(email);
		param.setName(name);
		param.setPw(pw);
		param.setLocation(location);
		
		MemberRegisterService regDao = new MemberRegisterService();
		regDao.doRegister(param);
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_login.jsp");
		dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
