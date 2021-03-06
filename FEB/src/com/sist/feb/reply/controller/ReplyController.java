package com.sist.feb.reply.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.post.domain.PostVO;
import com.sist.feb.reply.cmn.MessageVO;
import com.sist.feb.reply.domain.ReplyMemberVO;
import com.sist.feb.reply.domain.ReplyVO;
import com.sist.feb.reply.service.ReplyService;

/**
 * Servlet implementation class ReplyController
 */
@WebServlet( urlPatterns = {"/reply/reply.do" })
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger LOG = Logger.getLogger(ReplyController.class);
	ReplyService replyService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyController() {
        super();
        replyService = new ReplyService();
        LOG.debug("-----------------------");
		LOG.debug("--ReplyController()--");
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	request.setCharacterEncoding("UTF-8");
    	String postNo = request.getParameter("post_no");
    	
    	String workDiv = StringUtil.nvl(request.getParameter("work_div"), "doRetrieve");
    	LOG.debug("workDiv :"+workDiv);
    	
    	switch(workDiv) {
			case "doRetrieve": //조회
				doRetrieve(request,response);
				break;
			case "doInsert"://등록
				doInsert(request,response);
				break;
			case "doDelete"://삭제 -  doDelete가 서블릿에 있어서 이름 바꿔서 지정
				doDel(request,response);
				break;
    	}
    	
    }
    
	public void doDel(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		LOG.debug("-----------------------");
		LOG.debug("--service - doDel--"+request);
		LOG.debug("-----------------------");
		
		String replyNo = StringUtil.nvl(request.getParameter("reply_no"), "-1");
		int reply_no = Integer.parseInt(replyNo);
		LOG.debug("--reply_no--:"+reply_no);
		
		ReplyVO param = new ReplyVO();
		param.setReply_no(reply_no);
		LOG.debug("--param--:"+param);
		LOG.debug("-----------------------");
		
		int flag = replyService.doDelete(param);
		LOG.debug("--flag--:"+flag);
		MessageVO msgVO = new MessageVO();
		msgVO.setMsgId(String.valueOf(flag));
		String msg ="";
		if(1==flag){
			msg ="삭제성공";
		}else {
			msg ="삭제실패";
		}
		msgVO.setMsgContents(msg);
		Gson gson = new Gson();
		String jsonString = gson.toJson(msgVO);
		LOG.debug("-jsonString-:"+jsonString);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);
	}//--doDel
    
    public void doInsert(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		LOG.debug("-----------------------");
		LOG.debug("--service-doInsert()--");
		LOG.debug("-----------------------");
		
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("memberInfo");

		
		String postNo = StringUtil.nvl(request.getParameter("post_no"),"");
		String contents =StringUtil.nvl(request.getParameter("contents"),"");
		
		
		LOG.debug("--contents--"+contents);
		
		ReplyVO param = new ReplyVO();
		param.setPost_no(Integer.parseInt(postNo));
		param.setMember_no(loginMember.getMember_no());
		
		param.setContents(contents);
		LOG.debug("-param-:"+param);
		
		int flag = replyService.doInsert(param);
		MessageVO msgVO = new MessageVO();
		msgVO.setMsgId(String.valueOf(flag));
		
		String msg = "";
		if(1==flag){
			msg ="등록성공";
		}else {
			msg ="등록실패";
		}
		
		msgVO.setMsgContents(msg);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(msgVO);
		LOG.debug("-jsonString-:"+jsonString);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
    }
    public void doRetrieve(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
 
    	
    	int postNo = Integer.parseInt(request.getParameter("post_no"));
    	//int postNo = 22;
    	
    	//service에 DTO 전달
    	ReplyVO param = new ReplyVO();
    	param.setPost_no(postNo);
    	LOG.debug("-param:"+param);
    	
    	//List<DTO> return
    	List<ReplyMemberVO> list =replyService.checkReply(param);
    	LOG.debug("-list.size-:"+list.size());
		LOG.debug("-list-:"+list);//debug걸어서 확인

		
		//json으로 변환
		Gson gson = new Gson();
		String jsonString = gson.toJson(list);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		//해당화면에 data 전달
//		request.setAttribute("list",list);
//		
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/reply/reply_list.jsp");
//		dispatcher.forward(request, response);
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
