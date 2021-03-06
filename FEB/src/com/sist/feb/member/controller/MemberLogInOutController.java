package com.sist.feb.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.member.service.MemberLogInService;




/**
 * Servlet implementation class MemberLogInOutController
 */
@WebServlet( urlPatterns = {"/member/member.login" })
public class MemberLogInOutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberLogInService memberService;

	
	final Logger LOG = Logger.getLogger(MemberLogInOutController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLogInOutController() {
        super();
        memberService = new MemberLogInService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String workDiv = StringUtil.nvl(request.getParameter("work_div"), "login");
		LOG.debug("workDiv"+workDiv);
		
		switch(workDiv) {
			case "login":
				login(request,response);
				break;
			
			case "logout":			//로그아웃은 메인에 버튼을 추가하고 세션삭제 테스트해야됨!
				logout(request,response);
				break;
			}	
		
	}
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(null != session && null != session.getAttribute("memberInfo")) {
			//session 삭제
					
			session.invalidate();

		}
		
		//나중에 메인으로 경로 설정
		RequestDispatcher dispatcher = request.getRequestDispatcher("/category/category.do?work_div=doCateRetrieve");
		dispatcher.forward(request, response);
		
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		LOG.debug("login");
		
		String email = StringUtil.nvl(request.getParameter("email"),"");
		String pw = StringUtil.nvl(request.getParameter("pw"),"");
	
		LOG.debug("email :"+email);
		LOG.debug("pw : "+pw);
		
		MemberVO inVO = new MemberVO();
		inVO.setEmail(email);
		inVO.setPw(pw);
		LOG.debug("inVO :"+inVO);
		LOG.debug("--------------");

		int flag = memberService.doLoginCheck(inVO);
		
		LOG.debug("=====flag: "+flag);
		
		
		if(flag==1) {		//로그인 성공
			request.setAttribute("flag", flag);
			
			MemberVO loginMember = memberService.doSelectOne(inVO);
		
			HttpSession session = request.getSession();
			session.setAttribute("memberInfo", loginMember);
			
			//나중에 메인 페이지로 경로 바꿔야함!!
			RequestDispatcher dispatcher = request.getRequestDispatcher("/category/category.do?work_div=doCateRetrieve");
			dispatcher.forward(request, response);
			
		}else if (flag == 0) {	//이메일이 없음-> 알림창
			request.setAttribute("flag", flag);
			PrintWriter writer = response.getWriter();
			writer.println("<script type = 'text/javascript'>");
			writer.println("alert('이메일을 확인해주세요')");
			writer.println("history.back();");
			writer.print("</script>");
			writer.flush();
			return;

		}else {			//비밀번호가 안맞음 -> 알림창
			request.setAttribute("flag", flag);
			PrintWriter writer = response.getWriter();
			writer.println("<script type = 'text/javascript'>");
			writer.println("alert('비밀번호를 확인해주세요')");
			writer.println("history.back();");
			writer.print("</script>");
			writer.flush();
			return;
		}

		
		
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
