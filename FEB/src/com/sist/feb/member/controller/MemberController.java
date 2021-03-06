package com.sist.feb.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sist.feb.follow.domain.FollowBasicVo;
import com.sist.feb.follow.domain.FollowVO;
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.member.domain.MemberVO;
import com.sist.feb.member.service.MemberService;
import com.sist.feb.member.test.MemberServiceTestMain;
import com.sist.feb.profile.image.domain.ProfileImageVO;
import com.sist.feb.reply.cmn.MessageVO;
import com.sist.feb.storage.domain.MemberPostVO;
import com.sist.feb.storage.domain.StorageVO;
import com.sist.feb.storage.domain.StoreTwoVO;

/**
 * Servlet implementation class MemberController
 */
@WebServlet(description = "멤버", urlPatterns = { "/member/member.do" })
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final Logger LOG = Logger.getLogger(MemberController.class);
	
	MemberService memberService;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        memberService = new MemberService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//request에 들어오는 encoding을 일괄로 UTF-8로 처리
		request.setCharacterEncoding("UTF-8");
		String workType = request.getParameter("work_type");
		
		LOG.debug("--------------------------------------");
		LOG.debug("workType : "+workType);
		LOG.debug("--------------------------------------");
		
		switch(workType) {
			case "update": doUpdate(request, response); break;
			case "following": doInquireFollowing(request, response); break;
			case "followed": doInquireFollowed(request, response); break;
			case "member_post": doInquirePost(request, response); break;
			case "save": doInquireStorageSave(request, response); break;
			case "like": doInquireStorageLike(request, response); break;
			case "unfollow": doUnfollow(request, response); break;
			case "follow": doFollow(request, response); break;
			case "login": login(request, response); break;
			case "change_pw": doChangePw(request, response); break;
			case "profile_image": doRegisterProfileImage(request, response); break;
			case "followInPost": doFollowInPost(request, response); break;
			case "unfollowInPost": doUnFollowInPost(request, response); break;
			case "saveOrLikePost": doSaveOrLikePost(request, response); break;
			case "canselSaveOrLikePost": doCanselSaveOrLikePost(request, response); break;
		}
		

	
	}
	
	public void doSelectOne(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		MemberVO list = memberService.doSelectOne(new MemberVO(1, "Mark", "mark@gamil.com","mark123","america","I'm Mark!","21/03/04"));
		LOG.debug("-list"+list);
		
		//해당화면에 데이터 전달
		request.setAttribute("list: ", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_profile.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void doUpdate(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		
		MemberVO memForPw = new MemberVO(memberNo, "", "", "", "", "", "");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pw = memForPw.getPw();
		String location = request.getParameter("location");
		String intro = request.getParameter("intro");
		String reg_dt = request.getParameter("reg_dt");
		
		LOG.debug(memberNo);
		LOG.debug(name);
		LOG.debug(email);
		LOG.debug(pw);
		LOG.debug(location);
		LOG.debug(intro);
		LOG.debug(reg_dt);
		
		MemberVO updateMember = new MemberVO(memberNo, name, email, pw, location, intro, reg_dt);
    	MemberServiceTestMain testMain = new MemberServiceTestMain();    
	    testMain.doUpdate(updateMember);
	    
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_main.jsp");
		dispatcher.forward(request, response);
	
	}
	
	public void doInquireFollowing(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		LOG.debug("---------------------------------");
		LOG.debug("-MemberController----doInquireFollowing-");
		LOG.debug("---------------------------------");
		
		//param -> DTO
		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		LOG.debug("memberNo: "+memberNo);
		
		MemberVO memberTmp = new MemberVO(memberNo, "", "", "", "", "", "" );
		MemberVO member = memberService.doSelectOne(memberTmp);
		
		List<FollowVO> list = memberService.doInquireFollowing(member);

//		String arrStr = list.get(0).toString().substring(35);
//		int endNum = arrStr.indexOf(",");
//		
//		int numStr = Integer.parseInt(arrStr.substring(0, endNum));
//		LOG.debug("-numStr : "+numStr);
		
		
		
		//해당화면에 데이터 전달
		request.setAttribute("list", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_main.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void doInquireFollowed(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		LOG.debug("---------------------------------");
		LOG.debug("-MemberController----doInquireFollowed-");
		LOG.debug("---------------------------------");
		
		//param -> DTO
		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		LOG.debug("memberNo: "+memberNo);
		
		MemberVO memberTmp = new MemberVO(memberNo, "", "", "", "", "", "" );
		MemberVO member = memberService.doSelectOne(memberTmp);
		
		List<FollowVO> list = memberService.doInquireFollowed(member);
		LOG.debug("-list.size"+list.size());
		LOG.debug("-list"+list);
		
		//해당화면에 데이터 전달
		request.setAttribute("list", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_main.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void doInquirePost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		LOG.debug("---------------------------------");
		LOG.debug("-MemberController----doInquirePost-");
		LOG.debug("---------------------------------");
		
		//param -> DTO
//		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		LOG.debug("memberNo: "+memberNo);
		
		MemberVO memberTmp = new MemberVO(memberNo, "", "", "", "", "", "" );
		MemberVO member = memberService.doSelectOne(memberTmp);
		
		List<MemberPostVO> list = memberService.doInquirePost(member);
		LOG.debug("-list.size"+list.size());
		LOG.debug("-list"+list);
		
		//해당화면에 데이터 전달
		request.setAttribute("list", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_main.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void doInquireStorageSave(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		LOG.debug("---------------------------------");
		LOG.debug("-MemberController----doInquireStorageSave-");
		LOG.debug("---------------------------------");
		
		//param -> DTO
		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		LOG.debug("memberNo: "+memberNo);
		
		MemberVO memberTmp = new MemberVO(memberNo, "", "", "", "", "", "" );
		MemberVO member = memberService.doSelectOne(memberTmp);
		
		List<StoreTwoVO> list = memberService.doInquireStorageSave(member);
		LOG.debug("-list.size"+list.size());
		LOG.debug("-list"+list);
		
		//해당화면에 데이터 전달
		request.setAttribute("list", list);
		request.setAttribute("work_type", "save");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_main.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void doInquireStorageLike(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		LOG.debug("---------------------------------");
		LOG.debug("-MemberController----doInquireStorageLike-");
		LOG.debug("---------------------------------");
		
		//param -> DTO
		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		LOG.debug("memberNo: "+memberNo);
		
		MemberVO memberTmp = new MemberVO(memberNo, "", "", "", "", "", "" );
		MemberVO member = memberService.doSelectOne(memberTmp);
		
		List<StoreTwoVO> list = memberService.doInquireStorageLike(member);
		LOG.debug("-list.size"+list.size());
		LOG.debug("-list"+list);
		
		//해당화면에 데이터 전달
		request.setAttribute("list", list);
		request.setAttribute("work_type", "like");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_main.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void doUnfollow(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		LOG.debug("---------------------------------");
		LOG.debug("-MemberController----doUnfollow-");
		LOG.debug("---------------------------------");
		
		int followNo = Integer.parseInt(request.getParameter("fol_num"));
		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		LOG.debug("followNo: "+followNo);
		
		FollowVO follow = new FollowVO(followNo, 0, "", "", "", 0);
		
		int flag = memberService.doUnfollow(follow);
		
		if(flag == 1) {
			LOG.debug("---------------------------------");
			LOG.debug("-----------언팔로우 성공");
			LOG.debug("---------------------------------");
		} else {
			LOG.debug("---------------------------------");
			LOG.debug("-----------언팔로우 실패");
			LOG.debug("---------------------------------");
		}
		
		MemberVO memberTmp = new MemberVO(memberNo, "", "", "", "", "", "" );
		MemberVO member = memberService.doSelectOne(memberTmp);
		
		List<FollowVO> list = memberService.doInquireFollowing(member);
		LOG.debug("-list.size"+list.size());
		LOG.debug("-list"+list);
		
		//해당화면에 데이터 전달
		request.setAttribute("list", list);
		
		HttpSession session = request.getSession();
		session.setAttribute("memberInfo", member);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_main.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	public void doFollow(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		LOG.debug("---------------------------------");
		LOG.debug("-MemberController----doFollow-");
		LOG.debug("---------------------------------");
		
		int followingNo = Integer.parseInt(request.getParameter("following_no"));
		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		LOG.debug("followNo: "+followingNo);
		LOG.debug("memberNo: "+memberNo);
		
		FollowVO follow = new FollowVO(0, followingNo, "", "", "", 0);
		MemberVO memberTmp = new MemberVO(memberNo, "", "", "", "", "", "" );
		MemberVO member = memberService.doSelectOne(memberTmp);
		
		int flag = memberService.doFollow(follow, member);
		
		if(flag == 1) {
			LOG.debug("---------------------------------");
			LOG.debug("-----------팔로우 성공");
			LOG.debug("---------------------------------");
		} else {
			LOG.debug("---------------------------------");
			LOG.debug("-----------팔로우 실패");
			LOG.debug("---------------------------------");
		}
		
		List<FollowVO> list = memberService.doInquireFollowed(member);
		LOG.debug("-list.size"+list.size());
		LOG.debug("-list"+list);
		
		//해당화면에 데이터 전달
		request.setAttribute("list", list);
		
		HttpSession session = request.getSession();
		session.setAttribute("memberInfo", member);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_main.jsp");
		dispatcher.forward(request, response);
		
	}
	
	public void doFollowInPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		LOG.debug("---------------------------------");
		LOG.debug("-MemberController----doFollowInPost-");
		LOG.debug("---------------------------------");
		
		int followingNo = Integer.parseInt(request.getParameter("following_no"));
		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		LOG.debug("followNo: "+followingNo);
		LOG.debug("memberNo: "+memberNo);
		
		FollowVO follow = new FollowVO(0, followingNo, "", "", "", 0);
		MemberVO memberTmp = new MemberVO(memberNo, "", "", "", "", "", "" );
		MemberVO member = memberService.doSelectOne(memberTmp);
		
		int flag = memberService.doFollow(follow, member);
		MessageVO msgVO = new MessageVO();
		msgVO.setMsgId(String.valueOf(flag));
		
		String msg = ""; 
		if(flag == 1) {
			LOG.debug("---------------------------------");
			LOG.debug("-----------팔로우 성공");
			LOG.debug("---------------------------------");
			msg ="등록성공";
		} else {
			LOG.debug("---------------------------------");
			LOG.debug("-----------팔로우 실패");
			LOG.debug("---------------------------------");
			msg ="등록실패";
		}
		
		msgVO.setMsgContents(msg);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(msgVO);
		LOG.debug("-jsonString-:"+jsonString);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
	}
	
	public void doUnFollowInPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		LOG.debug("---------------------------------");
		LOG.debug("-MemberController----doUnFollowInPost-");
		LOG.debug("---------------------------------");
		
		int followingNo = Integer.parseInt(request.getParameter("following_no"));
		int followedNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		LOG.debug("followingNo: "+followingNo);
		LOG.debug("followedNo: "+followedNo);
		
		MemberVO followingMem = new MemberVO(followingNo, "", "", "", "", "", "" );
		MemberVO followedMem = new MemberVO(followedNo, "", "", "", "", "", "" );
		
		FollowBasicVo followBasicVO = memberService.doReturnFollowDetail(followingMem, followedMem);
		
		FollowVO follow = new FollowVO(followBasicVO.getFollow_no(), 0, "", "", "", 0);
		int flag = memberService.doUnfollow(follow);
		
		MessageVO msgVO = new MessageVO();
		msgVO.setMsgId(String.valueOf(flag));
		
		String msg = ""; 
		if(flag == 1) {
			LOG.debug("---------------------------------");
			LOG.debug("-----------언팔로우 성공");
			LOG.debug("---------------------------------");
			msg ="언팔로우 성공";
		} else {
			LOG.debug("---------------------------------");
			LOG.debug("-----------언팔로우 실패");
			LOG.debug("---------------------------------");
			msg ="언팔로우 실패";
		}
		
		msgVO.setMsgContents(msg);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(msgVO);
		LOG.debug("-jsonString-:"+jsonString);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		LOG.debug("---------------------------------");
		LOG.debug("-MemberController----login-");
		LOG.debug("---------------------------------");
		
		int loginMemNo = 0;
		
		try {
			loginMemNo = Integer.parseInt(StringUtil.nvl(request.getParameter("login_user"), "0"));
		} catch(NumberFormatException e) {
			loginMemNo = 0;
		} catch(Exception e) {
			loginMemNo = 0;
		}
		
		int memberNo = 0;
		
		try {
			memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), Integer.toString(loginMemNo) ));
		} catch(NumberFormatException e) {
			memberNo = loginMemNo;
		} catch(Exception e) {
			memberNo = loginMemNo;
		}
		
		LOG.debug("-loginMemNo-"+loginMemNo);
		
		MemberVO loginMem = new MemberVO(loginMemNo, "", "", "", "", "", "");
		loginMem = memberService.doSelectOne(loginMem);
		
		//세션이름
		//loginMemInfo -> memberInfo
		//변경해야함
		
		HttpSession session = request.getSession();
		session.setAttribute("memberInfo", loginMem);
		
		RequestDispatcher  dispatcher= request.getRequestDispatcher("/member/member_main.jsp");
		dispatcher.forward(request, response);
		
	}

	public void doChangePw(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		
		MemberVO member = new MemberVO(memberNo, "", "", "", "", "", "");
		member = memberService.doSelectOne(member);
		
		String name = member.getName();
		String email = member.getEmail();
		String location = member.getLocation();
		String intro = member.getIntro();
		String reg_dt = member.getReg_dt();
		
		String newPw =  request.getParameter("new_pw");
		
		LOG.debug(memberNo);
		LOG.debug(name);
		LOG.debug(email);
		LOG.debug(newPw);
		LOG.debug(location);
		LOG.debug(intro);
		LOG.debug(reg_dt);
		
		MemberVO updateMember = new MemberVO(memberNo, name, email, newPw, location, intro, reg_dt);
    	int flag = memberService.doUpdate(updateMember);
    	
    	if(flag == 1) {
    		LOG.debug("비밀번호 변경 성공");
    	}else if(flag != 1) {
    		LOG.debug("비밀번호 변경 실패");
    	}
	    
    	request.setAttribute("member_no", memberNo);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_popup.jsp");
		dispatcher.forward(request, response);
	
	}
	
	public void doRegisterProfileImage(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		LOG.debug("=================================");
		LOG.debug("=MemberController==doRegisterProfileImage=");
		LOG.debug("=================================");
		
		int memberNo = Integer.parseInt(StringUtil.nvl(request.getParameter("member_no"), "0"));
		
		MemberVO member = new MemberVO(memberNo, "", "", "", "", "", "");
		member = memberService.doSelectOne(member);
		LOG.debug("프로필 사진을 삭제한 멤버번호: "+memberNo);
		
		if("" != request.getParameter("profile_image") || "" != request.getParameter("benner_image")) {
	    	
	    	LOG.debug("프로필 이미지 변경시작 ");
	    	LOG.debug("profile_image: "+request.getParameter("profile_image"));
			LOG.debug("프로필 사진을 삭제한 멤버번호: "+memberNo);
			
			int flag = memberService.doDeleteProfileImage(member);
			LOG.debug("이미 프로필 사진이 있었으면 1, 아니면 0 -> " + flag);
    		
			//파일저장경로
//	    	String savePath = "C:\\20201123_eClass\\02_ORACLE\\workspace_web\\WEB_H01\\WebContent\\upload";
//    		String savePath = "/FEB/WebContent/pictures";
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
    		
    		//File Read
    		
    		List<ProfileImageVO> fileList = new ArrayList<>();
    		String filePK = StringUtil.getPK("yyyyMMddHH24mmss");
    		LOG.debug("filePK: "+filePK);
    		
    		Enumeration<String> files = multi.getFileNames();
    		
    		while(files.hasMoreElements()) {
    			
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
    			int result = memberService.doRegisterProfileImage(imageVO);
    			
    			if(result == 1) {
    				LOG.debug("이미지 등록 성공");
    			} else {
    				LOG.debug("이미지 등록 실패");
    			}
    			
    		}
    		
	    }
		
		request.setAttribute("member_no", memberNo);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/member/member_main.jsp");
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
	
	public void doSaveOrLikePost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		LOG.debug("---------------------------------");
		LOG.debug("-MemberController----doSaveOrLikePost-");
		LOG.debug("---------------------------------");
		
		int storeType = Integer.parseInt(request.getParameter("store_type"));
		int PostNo = Integer.parseInt(request.getParameter("post_no"));
		int memberNo = Integer.parseInt(request.getParameter("member_no"));
		int picGroup = Integer.parseInt(request.getParameter("pic_group"));
		
		StorageVO store = new StorageVO(0, storeType, "", PostNo, memberNo, picGroup);
		
		int flag = memberService.doSaveOrLikePost(store);
		
		
		MessageVO msgVO = new MessageVO();
		msgVO.setMsgId(String.valueOf(flag));
		
		String msg = ""; 
		if(flag == 1) {
			LOG.debug("---------------------------------");
			LOG.debug("-----------성공");
			LOG.debug("---------------------------------");
			msg ="등록성공";
		} else {
			LOG.debug("---------------------------------");
			LOG.debug("-----------실패");
			LOG.debug("---------------------------------");
			msg ="등록실패";
		}
		
		msgVO.setMsgContents(msg);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(msgVO);
		LOG.debug("-jsonString-:"+jsonString);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		
	}
	
	public void doCanselSaveOrLikePost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		
		LOG.debug("---------------------------------");
		LOG.debug("-MemberController----doCanselSaveOrLikePost-");
		LOG.debug("---------------------------------");
		
		int storeType = Integer.parseInt(request.getParameter("store_type"));
		int PostNo = Integer.parseInt(request.getParameter("post_no"));
		int memberNo = Integer.parseInt(request.getParameter("member_no"));
		int picGroup = Integer.parseInt(request.getParameter("pic_group"));
		
		StorageVO store = new StorageVO(0, storeType, "", PostNo, memberNo, picGroup);
		
		int flag = memberService.doCanselSaveOrLikePost(store);
		
		MessageVO msgVO = new MessageVO();
		msgVO.setMsgId(String.valueOf(flag));
		
		String msg = ""; 
		if(flag == 1) {
			LOG.debug("---------------------------------");
			LOG.debug("-----------성공");
			LOG.debug("---------------------------------");
			msg ="등록성공";
		} else {
			LOG.debug("---------------------------------");
			LOG.debug("-----------실패");
			LOG.debug("---------------------------------");
			msg ="등록실패";
		}
		
		msgVO.setMsgContents(msg);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(msgVO);
		LOG.debug("-jsonString-:"+jsonString);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonString);
		
		
	}
	
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
