/**
* <pre>
* com.sist.feb.category.service
* Class Name : CategoryService.java
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
package com.sist.feb.category.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.category.cmn.DTO;
import com.sist.feb.category.dao.CategoryDao;
import com.sist.feb.category.domain.CatePostVO;


/**
 * @author 임하람
 *
 */
public class CategoryService {
	
	private final Logger LOG = Logger.getLogger(CategoryService.class);
	private CategoryDao dao;
	
	public CategoryService() {
		dao= new CategoryDao();
	}//--생성자
	
	
	/**
	 * 카테고리 목록조회
	 * @param param
	 * @return
	 */
	public List<CatePostVO> doCateRetrieve(DTO param) {
		
		LOG.debug("----------------------------------------");
		LOG.debug("--CategoryService-doCateRetrieve() 실행--");
		LOG.debug("----------------------------------------");
		
		return dao.doCateRetrieve(param);
	}//--doCateRetrieve

}//--class
