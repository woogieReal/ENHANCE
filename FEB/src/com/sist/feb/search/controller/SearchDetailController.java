package com.sist.feb.search.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class SearchDetailController
 */
@WebServlet(description = "검색", urlPatterns = { "/search/search.do" })
public class SearchDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger LOG = Logger.getLogger(SearchDetailController.class);
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOG.debug("================================");
		LOG.debug("=SearchDetailController==doPost=");
		LOG.debug("================================");
	
		
	
	}

}
