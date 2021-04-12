/**
* <pre>
* com.sist.feb.category.test
* Class Name : CategoryTestMain.java
* Description:
* Author: 임하람
* Since: 2021/03/08
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/08 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.category.test;

import java.util.List;

import org.apache.log4j.Logger;


import com.sist.feb.category.dao.CategoryDao;
import com.sist.feb.category.domain.CatePostVO;
import com.sist.feb.category.domain.CategoryVO;
import com.sist.feb.category.domain.SearchVO;



public class CategoryJdbcTestMain {
	
	final static Logger LOG = Logger.getLogger(CategoryJdbcTestMain.class);

	//객체선언
	private CategoryDao categoryDao;
	
	private SearchVO searchVO;
	
	
	//private CategoryVO categ01;
	
	
	public CategoryJdbcTestMain() {
		
		//생성되어 있는  데이터 넣고 테스트
		
		//categ01 = new CategoryVO(4, "illustration");
		

		categoryDao = new CategoryDao();
		searchVO = new SearchVO();
	}//--생성자
	

	
	//카테고리 목록조회
	public void doCateRetrieve() {
		LOG.debug("-------------");
		LOG.debug("doRetrieve()");
		LOG.debug("-------------");
		
		searchVO.setSelectDiv("");
		
		List<CatePostVO> resultList = (List<CatePostVO>)categoryDao.doCateRetrieve(searchVO);
		LOG.debug("resultList :"+resultList);
		LOG.debug("doCateRetrieve 건수 :"+resultList.size());

	}//--doCateRetrieve()
	
	
	
	
	public static void main(String[] args) {
		CategoryJdbcTestMain cateMain  = new CategoryJdbcTestMain();
		cateMain.doCateRetrieve();
		
	}//--main
	
}//--class
