/**
* <pre>
* com.sist.feb.search.service
* Class Name : SearchDetailService.java
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
package com.sist.feb.search.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.category.cmn.DTO;
import com.sist.feb.category.domain.CatePostVO;
import com.sist.feb.search.dao.SearchDetailDao;

public class SearchDetailService {
	
	private final Logger LOG = Logger.getLogger(SearchDetailService.class);
	private SearchDetailDao searchDetailDao;
	
	public SearchDetailService() {
		
		searchDetailDao = new SearchDetailDao();
	
	}
	
	public List<CatePostVO> doCateRetrieve(DTO param) {
		
		LOG.debug("----------------------------------------------");
		LOG.debug("==SearchDetailService==doCateRetrieve()==");
		LOG.debug("----------------------------------------------");
		
		return searchDetailDao.doCateRetrieve(param);
		
	}
	
}
