package com.sist.feb.profile.image.domain;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.member.service.MemberService;

/**
 * Servlet implementation class ProfileImageController
 */
@WebServlet("/member/profile.do")
public class ProfileImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger LOG = Logger.getLogger(ProfileImageController.class);

	MemberService memberService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileImageController() {
        super();
        memberService = new MemberService();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.debug("=================================");
		LOG.debug("=ProfileImageController==doRegisterProfileImage=");
		LOG.debug("=================================");
	    	
		//파일저장경로
//	    	String savePath = "C:\\20201123_eClass\\02_ORACLE\\workspace_web\\WEB_H01\\WebContent\\upload";
//    	String savePath = "/FEB/WebContent/pictures";
		String savePath = "C:\\20201123_eClass\\02_ORACLE\\workspace_web\\FEB\\WebContent\\pictures";
		
		//파일용량
		int maxSize = 1024*1024*50; //50M
		String encType = "UTF-8";
		
		//동적으로 yyyy
		//동적으로 mm
		
		//save path를 동적생성
		File saveDir = new File(savePath);
		if(saveDir.exists()==false) saveDir.mkdir();
		int isSaveDir = mkDir(savePath);
		
		//2021
		String year = StringUtil.formatDate("yyyy");
		
		//savePath \\yyyy\\mm
		String savePathTemp = savePath + File.separator + year;
		LOG.debug("savePathTemp: "+savePathTemp);
		
		int isYearDir = mkDir(savePathTemp);
		LOG.debug("isYearDir: "+isYearDir);
		
		String month = StringUtil.formatDate("MM");
		String savePathTempMonth = savePathTemp + File.separator + month;
		LOG.debug("savePathTempMonth: "+savePathTempMonth);
		
		int isYearMonthDir = mkDir(savePathTempMonth);
		LOG.debug("isYearMonthDir: "+isYearMonthDir);
		
		savePath = savePathTempMonth;
		
		MultipartRequest multi = new MultipartRequest(request, 
				savePath, 
				maxSize, 
				encType, 
				new DefaultFileRenamePolicy()
		);
		
		int memberNo = 0;
		
		
//    	=============================================================
		
		LOG.debug("프로필 이미지 변경시작 ");
    	LOG.debug("profile_image: "+multi.getParameter("profile_image"));
    	
    	memberNo = Integer.parseInt(multi.getParameter("member_no"));
    	LOG.debug("memberNo: "+ memberNo);
    	
    	MemberVO member = new MemberVO(memberNo, "", "", "", "", "", "");
    	member = memberService.doSelectOne(member);
    	
    	LOG.debug("memberNo: "+memberNo);
    	
    	int flag =  memberService.doCheckProfileImage(member);
    	
    	//이미 프로필 사진이 있을경우만 수행
    	if(flag == 1) {
    		int flag2 = memberService.doDeleteProfileImage(member);
    		LOG.debug("이미 프로필 사진이 있었으면 1, 아니면 0 -> " + flag2);
    	}
		
		
//    	=============================================================
		
		//File Read
		
		List<ProfileImageVO> fileList = new ArrayList<>();
		
		Enumeration<String> files = multi.getFileNames();
		
		while(files.hasMoreElements()) {
			
			String filePK = StringUtil.getPK("yyyyMMddHH24mmss");
			LOG.debug("filePK: "+filePK);
			
			ProfileImageVO imageVO = new ProfileImageVO();
			
			String file = files.nextElement();
			
			//input의 name 속성 값이 들어올 것
			LOG.debug("file: "+file);
			
			//원본파일명
			String orgFileName = multi.getOriginalFileName(file);
			LOG.debug("orgFileName: "+orgFileName);
			
			//저장파일명
			String saveFileName = multi.getFilesystemName(file);
			LOG.debug("saveFileName: "+saveFileName);
			
			if(null == saveFileName) {
				return;
			}
			
			//파일 ID
			imageVO.setFileId(filePK);
			
			//원본파일명
			imageVO.setOrgFileNm(orgFileName);
			
			//저장파일명
			imageVO.setSaveFileNm(saveFileName);
			
			//저장경로
			imageVO.setPath(savePath);
			
			//경로 + 파일이름
			String fileFullPath = savePath + File.separator + saveFileName;
			File tmpFile = new File(fileFullPath);
			
			//파일사이즈
			long size = tmpFile.length();
			imageVO.setSize(size);
			
			//확장자
			String ext = "";
			if(orgFileName.lastIndexOf(".") > 0) {
				int dotIndex = orgFileName.lastIndexOf(".");
				ext = orgFileName.substring(dotIndex + 1);
			}
			
			imageVO.setExt(ext);
			
			LocalDate today = LocalDate.now();
			LOG.debug("오늘 날짜: " + today.toString());
			
			//등록 날짜
			imageVO.setRegDt(today.toString());
			
			//멤버번호
			imageVO.setMemberNo(memberNo);
			
			fileList.add(imageVO);
			
		}
		
		int result = 0;
		
		for(ProfileImageVO vo : fileList) {
			LOG.debug("vo: "+ vo);
			result = memberService.doRegisterProfileImage(vo);
		}
		
		
		if(result == 1) {
			LOG.debug("이미지 등록 성공");
		} else {
			LOG.debug("이미지 등록 실패");
		}
    		
		response.setContentType("text/html; charset=UTF-8");
		request.setAttribute("member_no", memberNo);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_popup2.jsp");
		dispatcher.forward(request, response);
		
	}
	
	/**
	 * path에 해당하는 디렉토리 생성
	 * @param path
	 * @return
	 */
	public int mkDir(String path) {
		int flag = 0;
		
		File saveDir = new File(path);
		if(saveDir.exists()==false) {
			boolean isMkdir = saveDir.mkdir();
			
			if(isMkdir==true) {
				flag = 1;
			}
		}
		
		return flag;
	}

}
