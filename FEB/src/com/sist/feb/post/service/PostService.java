/**
* <pre>
* com.sist.feb.post.service
* Class Name : PostService.java
* Description:
* Author: 
* Since: 2021/03/10
* Version 0.1
* Copyright (c) by H.R.LIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2021/03/10 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.sist.feb.post.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.sist.feb.category.cmn.DTO;
import com.sist.feb.category.dao.CategoryDao;
import com.sist.feb.category.domain.CategoryVO;
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.picture.dao.PictureDao;
import com.sist.feb.picture.domain.PictureVO;
import com.sist.feb.post.cmn.PostDTO;
import com.sist.feb.post.cmn.PostMsgVO;
import com.sist.feb.post.dao.PostDao;
import com.sist.feb.post.domain.PostPicVO;
import com.sist.feb.post.domain.PostVO;
import com.sist.feb.tag.dao.TagDao;
import com.sist.feb.tag.domain.TagVO;


/**
 * @author khy81
 *
 */
public class PostService {
	
	private final Logger LOG = Logger.getLogger(PostService.class);
	private PostDao postDao;
	private PictureDao picDao;
	private TagDao tagDao;
	private CategoryDao cateDao;
	
	public PostService() {
		postDao = new PostDao();
		picDao = new PictureDao();
		tagDao = new TagDao();
		cateDao = new CategoryDao();
		
	}
	
//	/**
//	 * 게시물 등록하면서 PostVO의 picNO와 PictureVO의 picNO을 동일한 uuid로 지정
//	 * @param param
//	 * @return 성공1 / 실패0
//	 */
//	public int doInsert(PostDTO param) {
//		
//		//set으로 해서 pk값을 정해주기
//		int uuid =  Integer.parseInt(StringUtil.getPK("yyyyMMddHH24mmss"));
//		
//		PostVO inVO = new PostVO();
//		inVO.setPicNo(uuid);
//		
//		PictureVO picVO = new PictureVO();
//		picVO.setPicNo(uuid);
//		
//		return picDao.doInsert(param);
//	}
//	
//	/**
//	 * 태그 등록
//	 * @param param
//	 * @return 성공1 / 실패
//	 */
//	public int tagInsert(PostDTO param) {
//		
//		int getPk =  Integer.parseInt(StringUtil.getPK("yyyyMMddHH24mmss")); 
//		
//		TagVO tagVO = new TagVO();
//		
//		tagVO.setTagNo(getPk);
//		
//		return tagDao.doInsert(param);
//	}
	
	
	/**
	 * 게시글 등록
	 * @param dto
	 * @return message(등록가능 불가능 알림)
	 * 
	 * 테스트는 되는데... 뭔가 말이안된다.. 이미지가 등록돼서 pk를 가져오는걸 하고 싶었는데 picDao.insert()
	 * 를 하니 casting오류가 떠서 pk를 가져오는 메서드를 PostDao에 만들어주고 postDao.doInsert()를 성공
	 * 하면 postVO에 등록된 tag와 picture의 pk를 가져오게 되는데... 의미가 없지 않나
	 */
//	public PostMsgVO doPost(PostDTO param) {
//		PostMsgVO message = new PostMsgVO();
//		
//		PostVO inVO = (PostVO) param;
//		LOG.debug("param: "+param);
//		
//		//이미지등록+태그등록+분야선택 후 각 key값 가져와서 게시물테이블에 저장
//		
//		//1. 이미지등록--------------------------------------------
//		//1.1 이미지Group SEQ조회
//		//1.2 이미지등록 
//		
//		//picDao = new PictureDao();
//		
//		postDao = new PostDao();
//		//1. 이미지 그룹 시퀀스 가져온다
//		int picGroup = 0;//다오에서 조회하기 getPicseq
//		
//		inVO.setPicGroup(picGroup);
//		//2. 이미지 저장
//		int picSuccess = postDao.doInsert(inVO); //picDao에서 postDao로 바꿔줌
//		int picPK = 0;
//		
//		//이미지 등록되지 않을 시
//		if(1 != picSuccess) {
//			message.setMsgId("10");
//			message.setMsgContents("사진이 등록되지 않았습니다 사진을 등록해주세요");
//			return message;//위로 돌아감
//		//등록됐을 시
//		}else {
//			//picPK = param.getPicSeq(); //왜.. 얘만 PostVO로 인식을 못하지 (각VO에 이름이 picNo 같아서 그런걸까봐 PostVO에는 picSeq로 바꿔줬는데 안됌)
//			//com.sist.feb.post.domain.PostVO cannot be cast to com.sist.feb.picture.domain.PictureVO
//			
//			picPK = postDao.getPicPK(inVO);
//		
//		}
//		LOG.debug("=picPK="+picPK);
//		LOG.debug("=picSuccess="+picSuccess);//성공1실패0(insert메서드 리턴값이 1/0이라서)
//		//이미지등록-----------------------------------------------
//		
//		//2. 태그등록----------------------------------------------
//		//2. 태그등록
//		//2.1 태그Group SEQ조회
//		//2.2 태그 등록
//		
//		//tagDao = new TagDao();
//		int tagSuccess = postDao.doInsert(param); //tagDao에서 postDao로 바꿈
//		int tagPK = 0;
//		
//		//태그등록 안됐을 시
//		if(1 != tagSuccess) {
//			message.setMsgId("20");
//			message.setMsgContents("태그가 등록되지 않았습니다 태그를 등록해주세요");
//			return message;
//		//태그등록 됐을 시
//		}else {
//			//tagPK = param.getTagNo();
//			//com.sist.feb.post.domain.PostVO cannot be cast to com.sist.feb.tag.domain.TagVO
//			
//			tagPK = postDao.getTagPK(inVO);
//		}
//		LOG.debug("=tagPK="+tagPK);
//		LOG.debug("=tagSuccess="+tagSuccess);//성공1실패0(insert메서드 리턴값이 1/0이라서)	
//		//태그등록-------------------------------------------------
//		
//		//3. 분야선택----------------------------------------------
////		cateDao = new CategoryDao();
////		int cateSuccess = cateDao.doSelect
//		
//		//분야선택-------------------------------------------------
//		
//		message.setMsgId("0");//데이터가 다 있을때 msgId를 "0"으로 지정
//		message.setMsgContents("포스트가 등록되었습니다.");
//		
//		return message;
//	}
	
	/**
	 * 이미지등록+태그등록+분야선택 후 각 key값 가져와서 게시물테이블에 저장
	 * @param param
	 * @return
	 */
	public int doPost(PostDTO param,List<TagVO> tagList, List<PictureVO> pictureList ) {
		PostVO inVO = (PostVO) param;
		//1. 이미지등록--------------------------------------------
		//1.1 이미지Group SEQ조회
		picDao = new PictureDao();
		PictureVO picPK = picDao.getPicSeq(inVO);//dual테이블에서 시퀀스 가져와 picPK에 담기
		//inVO.setPicGroup(picPK.getPicGroup());
		//1.2 이미지등록 
		for( PictureVO picVO:pictureList) {
			picVO.setPicGroup(picPK.getPicGroup());
			picDao.doInsert(picVO);
		}
		
		//2. 태그등록----------------------------------------------
		//2.1 태그Group SEQ조회
		tagDao = new TagDao();
		TagVO tagPK = tagDao.getTagSeq(inVO);//dual테이블에서 시퀀스 가져와 tagPK에 담기
		//inVO.setTagGroup(tagPK.getTagGroup());
		//2.2 태그 등록
		for( TagVO tagVO:tagList) {
			tagVO.setTagGroup(tagPK.getTagGroup());
			tagDao.doInsert(tagVO);
		}
		
		
		//3. 게시물 등록--------------------------------------------
		
		inVO.setPicGroup(picPK.getPicGroup());
		if( pictureList.size()>0 ) {
			
//			int picNO = postDao.doInquireCurrval()-pictureList.size();
			int picNO = postDao.doInquireCurrval()-1;
			//★★★★★★★★★★★★★★★★★
			inVO.setPicNo(picNO);
			//★★★★★★★★★★★★★★★★★
		}
		inVO.setTagGroup(tagPK.getTagGroup());
		
		return postDao.doInsert(param);
	}
	
	/**
	 * 게시글수정
	 * @param param
	 * @return 성공1 / 실패0
	 */
	public int doUpdate(PostDTO param) {
		return postDao.doUpdate(param);
	}
	
	/**
	 * 게시글 삭제
	 * @param param
	 * @return 성공1 / 실패0
	 */
	public int doDelete(PostDTO param) {
		return postDao.doDelete(param);
	}
	
	/**
	 * 단건조회 + 조회cnt 증가
	 * PostPicVO생성하면서 PostPicVO로 바꿔줌
	 * @param param
	 * @return
	 */
	public PostPicVO doSelectOne(PostDTO param) {
		
		PostPicVO inVO = (PostPicVO) param;
		int flag = postDao.doReadCnt(inVO);
		LOG.debug("====================================");
		LOG.debug("=          조회COUNT flag            = " + flag);
		LOG.debug("====================================");	
		
		PostPicVO outData = postDao.doSelectOne(param);
		outData.setMsgFlag(flag);
		
		if(1==flag) {
			outData.setMsg("조회 Count 증가 성공");
		}else {
			outData.setMsg("조회 Count 증가 실패");
		}
		
		return outData;
	}//--doSelectOne
	
	
	public List<PictureVO> doInquirePictures(PostDTO param){
		List<PictureVO> list = picDao.doInquirePictures(param);
		return list;
	}


	/**
	 * 이미지파일등록
	 * @param param
	 * @return
	 */
	public int doUpload(PostDTO param) {
		return picDao.doInsert(param);
	}
	
	/**
	 * 태그 등록
	 * @param param
	 * @return
	 */
	public int doTagInsert(TagVO param) {
		return tagDao.doInsert(param);
	}

}

















