/**
* <pre>
* com.sist.feb.category.domain
* Class Name : CategoryVO.java
* Description:
* Author: 임하람
* Since: 2021/03/07
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/07 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.category.domain;

import com.sist.feb.category.cmn.DTO;

/**
 * @author 임하람
 *
 */
public class CategoryVO extends DTO {
	
	private int category_seq;//분야번호
	private String name;//분야이름
	
	public CategoryVO() { }//default 생성자

	

	public CategoryVO(int category_seq, String name) {
		super();
		this.category_seq = category_seq;
		this.name = name;
	}



	//setter & getter
	public int getCategory_seq() {
		return category_seq;
	}


	public void setCategory_seq(int category_seq) {
		this.category_seq = category_seq;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "CategoryVO [category_seq=" + category_seq + ", name=" + name + ", toString()=" + super.toString() + "]";
	}


}//--class
