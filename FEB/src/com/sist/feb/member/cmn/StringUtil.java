/**
* <pre>
* com.sist.eclass.cmn
* Class Name : StringUtil.java
* Description:
* Author: sist
* Since: 2021/03/05
* Version 0.1
* Copyright (c) by H.R.KIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/05 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.member.cmn;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.member.service.MemberService;
import com.sist.feb.profile.image.domain.ProfileImageVO;
/**
 * @author sist
 *
 */
public class StringUtil {
	public static String nvl(String val,String rep) {
		if(null == val) {
			val = rep;
		}
		return val.trim();
	}
	
	
	/**
	 * PK 생성: 
	 * ex)20210119032917e68207e33a1746769b13138d664a329f
	 * @param format
	 */
	public static String getPK(String format) {
		return formatDate(format)+getUUID();
	}
	/**
	 * 32bit uuid생성
	 * @return String
	 */
	public static String getUUID() {
		String uuidStr = "";
		UUID  uuId=UUID.randomUUID();
		//uuid생성, "-" 구분 기호 제거.
		uuidStr = uuId.toString().replaceAll("-", "");
		return uuidStr;
	}
	/**
	 * 형식날짜 출력
	 * @param format(yyyy/MM/dd)
	 * @return 
	 */
	public static String formatDate(String format) {
		//null or "" 기본값 yyyy/MM/dd
		if(Objects.equals(format, "")) {
			format = "yyyy/MM/dd";
		}
		SimpleDateFormat  sdf=new SimpleDateFormat(format);	
		return sdf.format(new Date());
	}
	
	public static String changePath(int memberNo) {
		String image_path = "/FEB/pictures/2021/03/nothing.jpg";
		MemberVO member = new MemberVO(memberNo, "", "", "", "", "", "");
		MemberService service = new MemberService();
		
		ProfileImageVO imageVO = service.doInquireProfileImage(member);
		int flag = service.doCheckProfileImage(member);
		
		//등록한 프로필 이미지가 있을 때만 수행
		if(flag == 1){
			image_path = "/FEB" + imageVO.getPath().substring(imageVO.getPath().indexOf("pictures") - 1).replace(File.separator, "/") + File.separator + imageVO.getSaveFileNm();
		}
		
		return image_path;
	}
	
	public static String changePathForPost(String fullPath) {
		String image_path = "";
		//C:\20201123_eClass\02_ORACLE\workspace_web\FEB\WebContent\pictures\2021\03/안경1.png
		image_path = "/FEB" + fullPath.substring(fullPath.indexOf("pictures") - 1).replace(File.separator, "/");
		
		return image_path;
	}
	
	public static String changeName(int memberNo) {
		String changeName = "";
		
		MemberVO member = new MemberVO(memberNo, "", "", "", "", "", "");
		MemberService service = new MemberService();
		member = service.doSelectOne(member);
		changeName = member.getName();
		
		System.out.println(changeName);
		
		return changeName;
	}
	
	
	
	
}