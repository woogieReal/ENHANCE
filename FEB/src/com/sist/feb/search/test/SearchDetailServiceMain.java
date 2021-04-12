/**
* <pre>
* com.sist.feb.search.test
* Class Name : SearchDetailServiceMain.java
* Description:
* Author: 임하람
* Since: 2021/03/24
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/24 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.search.test;

import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.category.domain.CatePostVO;
import com.sist.feb.search.dao.SearchDetailDao;
import com.sist.feb.search.domain.SearchDetailVO;
import com.sist.feb.search.service.SearchDetailService;

/**
 * @author 123wo
 *
 */
public class SearchDetailServiceMain {
	
	final static Logger LOG = Logger.getLogger(SearchDetailServiceMain.class);
	
	private SearchDetailVO searchDetailVO;
	private SearchDetailService searchDetailService;
	
	public SearchDetailServiceMain() {
		
		searchDetailVO = new SearchDetailVO("제목", "코닥");
		searchDetailService = new SearchDetailService();
		
	}
	

	public static void main(String[] args) {
		
		SearchDetailServiceMain serviceTest = new SearchDetailServiceMain();
		serviceTest.doCateRetrieve();
		
	}
	
	public void doCateRetrieve() {
		
		LOG.debug("----------------------------------------------");
		LOG.debug("==SearchDetailServiceMain==doCateRetrieve()==");
		LOG.debug("----------------------------------------------");
		
		LOG.debug("searchDetailVO: "+searchDetailVO);
		List<CatePostVO> resultList = (List<CatePostVO>)searchDetailService.doCateRetrieve(searchDetailVO);
		LOG.debug("resultList :"+resultList);
		LOG.debug("doCateRetrieve 건수 :"+resultList.size());
		
	}
	
	
	

}
