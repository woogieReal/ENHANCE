package com.sist.feb.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.member.dao.MemberLogInDao;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.member.service.MemberLogInService;

/**
 * Servlet implementation class MemberFindPwController
 */        
@WebServlet(
		description = "비밀번호찾기", 
		urlPatterns = { "/member/membermail.do"})
public class MemberFindPwController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;    
      
	private MemberLogInService memberService;

	
	final Logger LOG = Logger.getLogger(MemberFindPwController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFindPwController() {
        super();
        memberService = new MemberLogInService();

		
        LOG.debug("--------------------------------------");
		LOG.debug("MemberFindPwController : ");
		LOG.debug("--------------------------------------");
        
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.setContentType("text/html; charset=UTF-8");
		
		String email = StringUtil.nvl(request.getParameter("email"),"");
		LOG.debug("email :"+email);
		
		MemberVO inVO = new MemberVO();
		inVO.setEmail(email);
		
		
		MemberVO passMember = memberService.doSelectOne(inVO);
		LOG.debug(passMember);
		
		if(passMember ==null) {
			PrintWriter writer = response.getWriter();
			writer.println("<script type = 'text/javascript'>");
			writer.println("alert('이메일을 확인해주세요')");
			writer.println("history.back();");
			writer.print("</script>");
			writer.flush();
			return;
			
		}
		
		String memEmail = passMember.getEmail();
		String memPass = passMember.getPw();
		
		int flag = memberService.sendEmail(memEmail, memPass);
		LOG.debug("flag :"+flag);
	
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
