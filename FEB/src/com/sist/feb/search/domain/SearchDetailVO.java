/**
* <pre>
* com.sist.feb.category.domain
* Class Name : SearchDetailVO.java
* Description:
* Author: 임하람
* Since: 2021/03/23
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/23 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.search.domain;

import com.sist.feb.category.cmn.DTO;

/**
 * @author 123wo
 *
 */
public class SearchDetailVO extends DTO {
	
	private String searchDiv; //검색구분(전체 ,제목, 작성자)
	private String searchWord; //검색어
	
	public SearchDetailVO() {
		
	}

	public SearchDetailVO(String searchDiv, String searchWord) {
		super();
		this.searchDiv = searchDiv;
		this.searchWord = searchWord;
	}

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	@Override
	public String toString() {
		return "SearchDetailVO [searchDiv=" + searchDiv + ", searchWord=" + searchWord + ", toString()="
				+ super.toString() + "]";
	}
	
}
