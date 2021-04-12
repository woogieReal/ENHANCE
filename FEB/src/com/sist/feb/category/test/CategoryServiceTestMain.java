/**
* <pre>
* com.sist.feb.category.test
* Class Name : CategoryServiceTestMain.java
* Description:
* Author: 임하람
* Since: 2021/03/12
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/12 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.category.test;

import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.category.cmn.DTO;
import com.sist.feb.category.domain.CatePostVO;
import com.sist.feb.category.domain.CategoryVO;
import com.sist.feb.category.domain.SearchVO;
import com.sist.feb.category.service.CategoryService;

/**
 * @author user
 *
 */
public class CategoryServiceTestMain {
	
	private final Logger LOG  = Logger.getLogger(CategoryServiceTestMain.class);
	
	
	private CategoryService categoryService;
	

	private static SearchVO search01;
	private static SearchVO search02;
	
	private SearchVO searchVO;
	
	public CategoryServiceTestMain(){
		
		
		search01 = new SearchVO("");
		search02 = new SearchVO("illustration");
				
		categoryService = new CategoryService();
	}//--CategoryServiceTestMain
	
	
	public void doCateRetrieve(DTO param) {
		LOG.debug("-----------------");
		LOG.debug("***CategoryServiceTestMain***doCateRetrieve()***-");
		LOG.debug("---------------");
		
		
		List<CatePostVO> outVO  = categoryService.doCateRetrieve(search01);
		LOG.debug("outVO:"+outVO);
		
		
	}//--doCateRetrieve
	
	
	
	
	public static void main(String[] args) {
		CategoryServiceTestMain serMain  = new CategoryServiceTestMain();
		serMain.doCateRetrieve(search02);

	}//--main

}//--class
