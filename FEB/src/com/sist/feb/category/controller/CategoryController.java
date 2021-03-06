package com.sist.feb.category.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sist.feb.category.domain.CatePostVO;
import com.sist.feb.category.domain.SearchVO;
import com.sist.feb.category.service.CategoryService;
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.search.domain.SearchDetailVO;
import com.sist.feb.search.service.SearchDetailService;

/**
 * Servlet implementation class CategoryController
 */
@WebServlet(description = "카테고리 컨트롤러", urlPatterns = {"/category/category.do"})
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger LOG  = Logger.getLogger(CategoryController.class);
	
	CategoryService categoryService;//객체선언
	SearchDetailService searchDetailService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryController() {//Controller는 서비스만 바라봄!
        super();
        categoryService = new CategoryService();
        searchDetailService = new SearchDetailService();
        
    }//--생성자

    
    
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 카데고리 목록조회 기능에 대한 분기처리
        LOG.debug("----------------------");
        LOG.debug("***CategoryController()***service***");
        LOG.debug("----------------------");
        
        //request에 들어오는 encoding을 일괄로 UTF-8
        request.setCharacterEncoding("UTF-8");
    
        String workDiv = StringUtil.nvl(request.getParameter("work_div"),"doCateRetrieve");
        LOG.debug("----------------------");
        LOG.debug("-workDiv-:"+workDiv);
        LOG.debug("----------------------");
        
        
        //service -> dao <-> DB
        switch(workDiv) {
        
        case "doCateRetrieve":
        	doCateRetrieve(request,response);
        	break;
        case "doSearch":
        	doSearch(request,response);
        	break;
        	
        default:
        	LOG.debug("----------------------");
            LOG.debug("-카테고리 분야를 선택 유/무를 확인하세요-:"+workDiv);
            LOG.debug("----------------------");
            break;
        }//--switch
	
		
	}//--service

	public void doSearch(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		LOG.debug("-----------------------");
		LOG.debug("***CategoryController()***doSearch***");
		LOG.debug("-----------------------");
		
		String searchDiv = StringUtil.nvl(request.getParameter("search_select"),"");
		String searchWord = StringUtil.nvl(request.getParameter("search_word"),"");
		
		LOG.debug("searchDiv: "+searchDiv);
		LOG.debug("searchWord: "+searchWord);
		
		SearchDetailVO param = new SearchDetailVO();
		param.setSearchDiv(searchDiv);
		param.setSearchWord(searchWord);
		LOG.debug("param: "+param);
		
		List<CatePostVO> list = searchDetailService.doCateRetrieve(param);
		LOG.debug("-list.size-:"+list.size());
		LOG.debug("-list-:"+list);

		//해당화면에 data 전달
		request.setAttribute("list",list);
		
		//화면으로 param전달
		request.setAttribute("searchParam", param);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/main/main.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
/**
 * 카테고리 목록조회
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 */
	public void doCateRetrieve(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	    
		LOG.debug("-----------------------");
		LOG.debug("***CategoryController()***doCateRetrieve***");
		LOG.debug("-----------------------");
		
		//param ->DTO 
		String selectDiv = StringUtil.nvl(request.getParameter("selectDiv"),"");
		
		LOG.debug("-----------------------");
		LOG.debug("selectDiv :"+selectDiv);
		LOG.debug("-----------------------");
		
		//service에 DTO 전달
		SearchVO param = new SearchVO();
		param.setSelectDiv(selectDiv);
		LOG.debug("-param-:"+param);
		
		//List<> return
		List<CatePostVO> list =  categoryService.doCateRetrieve(param);
		LOG.debug("-list.size-:"+list.size());
		LOG.debug("-list-:"+list);
		
		//해당화면에 data 전달
		request.setAttribute("list",list);
						
		//화면으로 param전달
		request.setAttribute("param", param);
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/main/main.jsp");
		dispatcher.forward(request, response);
		
		
	}//--doCateRetrieve

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}//--doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);//--doPost
	}

}
