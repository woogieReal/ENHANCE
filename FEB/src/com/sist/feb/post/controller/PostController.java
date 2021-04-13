package com.sist.feb.post.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.sist.feb.category.domain.CatePostVO;
import com.sist.feb.category.domain.SearchVO;
import com.sist.feb.category.service.CategoryService;
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.picture.domain.PictureVO;
import com.sist.feb.post.cmn.PostMsgVO;
import com.sist.feb.post.domain.PostPicVO;
import com.sist.feb.post.domain.PostVO;
import com.sist.feb.post.service.PostService;
import com.sist.feb.tag.domain.TagVO;

/**
 * Servlet implementation class PostController
 */
@WebServlet("/post/post.do")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final Logger LOG = Logger.getLogger(PostController.class);
	
	PostService postService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostController() {
        super();
        postService = new PostService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request에 들어오는 encoding을 일괄로 UTF-8로 지정해줌
		request.setCharacterEncoding("UTF-8");
		
		String workType = request.getParameter("work_type");
		LOG.debug("--------------------------------------------------");
		LOG.debug("-work_type-"+workType);
		LOG.debug("--------------------------------------------------");
		
		switch(workType) {
			case "doSelectOne" : //단건조회
				doSelectOne(request,response);
			break;
			case "doInsert" : //등록
				doInsert(request,response);
			break;
			case "doUpdate" : //수정하기
				doUpdate(request,response);
			break;
			case "doDelete" : //수정하기
				doDeletePost(request,response);
			break;

		}
		
	}
	
	
	public void doUpdate(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		LOG.debug("--------------------------------------------------");
		LOG.debug("|                     doUpdate                   |");
		LOG.debug("--------------------------------------------------");
	
		
	}
	
	public void doDeletePost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		LOG.debug("--------------------------------------------------");
		LOG.debug("|                    doDeletePost                 |");
		LOG.debug("--------------------------------------------------");
		
		String postNo = StringUtil.nvl(request.getParameter("post_no"),"");
		
		LOG.debug("--------------------------------------------------");
		LOG.debug("-post_no- "+postNo);
		LOG.debug("--------------------------------------------------");
		
		PostPicVO param = new PostPicVO();
		param.setPostNo(Integer.parseInt(postNo));
		LOG.debug("=param="+param);
	
		int flag = postService.doDelete(param);
		
		if(1 == flag) {
			LOG.debug("========================");
			LOG.debug("=게시물 삭제 성공=");
			LOG.debug("========================");
		} else {
			LOG.debug("XXXXXXXXXXXXXXXXXXXXXXXX");
			LOG.debug("=게시물 삭제 실패=");
			LOG.debug("XXXXXXXXXXXXXXXXXXXXXXXX");
		}
		
//		================================================
		
		String selectDiv = "";
		
		LOG.debug("-----------------------");
		LOG.debug("selectDiv :"+selectDiv);
		LOG.debug("-----------------------");
		
		//service에 DTO 전달
		SearchVO param2 = new SearchVO();
		param2.setSelectDiv(selectDiv);
		LOG.debug("-param2-:"+param2);
		
		CategoryService categoryService = new CategoryService();
		
		//List<> return
		List<CatePostVO> list =  categoryService.doCateRetrieve(param2);
		LOG.debug("-list.size-:"+list.size());
		LOG.debug("-list-:"+list);
		
		//해당화면에 data 전달
		request.setAttribute("list",list);
						
		//화면으로 param전달
		request.setAttribute("param", param2);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/main/main.jsp");
		dispatcher.forward(request, response);		
		
	}
	
	public void doSelectOne(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		LOG.debug("--------------------------------------------------");
		LOG.debug("|                    doSelectOne                 |");
		LOG.debug("--------------------------------------------------");
		
		String postNo = StringUtil.nvl(request.getParameter("post_no"),"");
		
		LOG.debug("--------------------------------------------------");
		LOG.debug("-post_no- "+postNo);
		LOG.debug("--------------------------------------------------");
		
		PostPicVO param = new PostPicVO();
		param.setPostNo(Integer.parseInt(postNo));//"63"을 postNo로 바꿔주기
		LOG.debug("=param="+param);
	
		//단건조회 실행
		PostPicVO outVO = postService.doSelectOne(param);
		LOG.debug("-outVO-"+outVO);
		
		List<PictureVO> list = postService.doInquirePictures(outVO);
		for(PictureVO vo : list) {
			LOG.debug("vo: "+vo);
		}
		
		//화면으로 데이터 전달 
		
		request.setAttribute("list", list);
		request.setAttribute("vo", outVO);
		//frm.submit();을 했기 때문에 화면이 refresh되면서 member_mng화면으로 이동한다.
		RequestDispatcher dispacher = request.getRequestDispatcher("/post/post_mng.jsp");
		dispacher.forward(request, response);
		
	}
	
	public void doInsert(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		LOG.debug("--------------------------------------------------");
		LOG.debug("|                    doInsert                     |");
		LOG.debug("--------------------------------------------------");
		HttpSession session = request.getSession();
		MemberVO loginMember = (MemberVO) session.getAttribute("memberInfo");
		

		
		String title = StringUtil.nvl(request.getParameter("title"), "");
		String category = StringUtil.nvl(request.getParameter("category"),"");
		String contents = StringUtil.nvl(request.getParameter("contents"), "");
		//String file = StringUtil.nvl(request.getParameter("file"),"");
		String tags = StringUtil.nvl(request.getParameter("tagArr"),"");
		String fileList =StringUtil.nvl( request.getParameter("fileList"), "");
		

		LOG.debug("--------------------------------------------------");
		LOG.debug("-title- "+title);
		LOG.debug("-category- "+category);
		LOG.debug("-contents- "+contents);
		//LOG.debug("-file- "+file);
		LOG.debug("-tag- "+tags);
		LOG.debug("-fileList-"+fileList);
		LOG.debug("--------------------------------------------------");
		
		//파일변수--------------------------------------------------------------
		Gson gsonFile =new Gson();
		PictureVO[] pictureList = gsonFile.fromJson(fileList, PictureVO[].class );
		for(PictureVO pVO:pictureList) {
		LOG.debug("pVO:"+pVO);
		}
		List<PictureVO> pList=new ArrayList<>();
		if(null !=pictureList && pictureList.length>0) {
			pList = Arrays.asList(pictureList);
		}
		//파일변수--------------------------------------------------------------

		
		//태그변수-------------------------------------------------------------
		List <String> tagList = Arrays.asList(tags.split(",")); 
		LOG.debug("tagList: "+tagList);
		
		List<TagVO> tagVoList = new ArrayList<TagVO>();
		
		int tagNo =0;
		for(String strTag:tagList) {
			
			TagVO inVO = new TagVO();
			tagNo++;
			inVO.setTagName(strTag);
			inVO.setTagNo(tagNo);
			
			tagVoList.add(inVO);
		}
		
		for(TagVO tagVO : tagVoList) {
			LOG.debug("tagVO: "+tagVO);
		}
		//태그변수-------------------------------------------------------------
		
		
		//server에 DTO전달
		PostVO param = new PostVO();
		param.setTitle(title);
		param.setCategorySeq(Integer.parseInt(category));
		param.setContents(contents);
//		param.setPicGroup(Integer.parseInt(file));
//		param.setTagGroup(Integer.parseInt(tagVO));
		param.setMemberNo(loginMember.getMember_no());
		
		
		LOG.debug("-param-"+param);
		LOG.debug("------------------------------------------");
		
		int flag = this.postService.doPost(param,tagVoList,pList);
		
		PostMsgVO messageVO = new PostMsgVO();
		String message = "";
		
		if(1==flag) {
			message = "게시글이 등록되었습니다";
		}else {
			message ="등록 실패";
		}
		messageVO.setMsgContents(message);
		messageVO.setMsgId(String.valueOf(flag));
		
		Gson gson = new Gson();
		String gsonString = gson.toJson(messageVO);
		
		LOG.debug("=gsonString="+gsonString);
		
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(gsonString);
		
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
