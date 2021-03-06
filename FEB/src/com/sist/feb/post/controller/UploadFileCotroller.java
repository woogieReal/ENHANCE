package com.sist.feb.post.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
//파일 업로드 시 필요한 import패키지-------------------------------
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
//파일 업로드 시 필요한 import패키지-------------------------------
import com.sist.feb.member.cmn.StringUtil;
import com.sist.feb.picture.domain.PictureVO;
import com.sist.feb.post.service.PostService;

/**
 * Servlet implementation class UploadFileCotroller
 */
@WebServlet(description = "파일업로드", urlPatterns = { "/upload/upload.do" })
public class UploadFileCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final Logger LOG = Logger.getLogger(UploadFileCotroller.class);
	PostService postService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFileCotroller() {
        super();
        
        postService = new PostService();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LOG.debug("--------------------------------------------------------------");
		LOG.debug("|		        UploadFileCotroller doPost		            |");
		LOG.debug("--------------------------------------------------------------");
	

		//파일들이 저장될 폴더의 경로
		//파일(upload)을 WebContent에 올려서 workspace폴더에서 확인 할 수 있게 한다.(단점은 바로 refresh가 안된다)
		String savePath= "C:\\20201123_eClass\\02_ORACLE\\workspace_web\\FEB\\WebContent\\pictures";
		
		//파일용량
		int maxSize = 1024*1024*50; //50메가
		String encType = "UTF-8";
		
		//동적으로 upload폴더 하위에 날짜별 폴더 만들기 --------------------------------------------------------------
		//---\\yyyy\\mm	
		//폴더하나에 만들 수 있는 파일의 개수가 제한이 있기 때문에 
//		File saveDir = new File(savePath);//save path의 동적 생성
//		if(saveDir.exists()==false)saveDir.mkdir();
		int isSaveDir = mkDir(savePath); //아래에 mkDir함수 만들어줘서 위의 두 코드가 필요없어짐
		
		//2021
		String year = StringUtil.formatDate("yyyy");
		//--savePath\\yyyy\\mm (File.separator: 이시스템에 맞는 \가 들어옴)
		String savePathTemp = savePath+File.separator+year;
		LOG.debug("=savePathTemp="+savePathTemp);	
		int isYearDir = mkDir(savePathTemp);
		LOG.debug("=isYearDir="+isYearDir);
		
		//03(월)
		String month = StringUtil.formatDate("MM");
		String savePathTempMonth = savePathTemp+File.separator+month;
		LOG.debug("=savePathTempMonth="+savePathTempMonth);
		
		int isYearMonthDir = mkDir(savePathTempMonth);
		savePath = savePathTempMonth;
		
		MultipartRequest multi = new MultipartRequest( 
				request, 
				savePath, 
				maxSize, 
				encType, 
				new DefaultFileRenamePolicy());
		
		//File read
		List<PictureVO> fileList = new ArrayList<>();
		
		//pk는 게시물저장하면서 함께 등록되기 때문에 주지 않는다
		
		//int fileSeq=0;
		Enumeration<String> files = multi.getFileNames();
		
		while(files.hasMoreElements()){
			PictureVO picVO = new PictureVO();
			
			String file = files.nextElement();
			LOG.debug("=file="+file);
			
			//원본파일명
			String orgFileName = multi.getOriginalFileName(file);
			LOG.debug("=orgFileName="+orgFileName);
			
			//저장파일명
			String saveFileName = multi.getFilesystemName(file);
			LOG.debug("=saveFileName="+saveFileName);
			
			if(null == saveFileName) {
				return;
			}
			
			//파일순번
			//int fileSeq = Integer.parseInt(StringUtil.getPK("yyyyMMddHH24mmss"));
			int fileSeq = 0;//0으로 일단 저장하고 service의 doPost실행되면서 0에 시퀀스 입력되는 ..
			//fileSeq++;
			picVO.setPicNo(fileSeq);
			//원본파일명
			picVO.setPicNm(orgFileName);
			//저장파일명
			picVO.setSaveNm(saveFileName);
			//저장경로
			picVO.setPath(savePath);	
			
			fileList.add(picVO);//setter된 데이터들을 fileList에 담는다.
			
		}//--while
		
		Gson gson = new Gson();

		String gsonString = gson.toJson(fileList);
		LOG.debug("=gsonString="+gsonString);
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print(gsonString);
		
		
	}
	
	/**
	 * path에 해당하는 디렉토리 생성
	 * @param path
	 * @return
	 */
	public int mkDir(String path) {
		int flag = 0;
		
		File saveDir = new File(path);//save path의 동적 생성
		if(saveDir.exists()==false) {
			boolean isMkDir= saveDir.mkdir();
		
			if(isMkDir==true)flag=1;
		}
		
		return flag;
	}//mkDir

}
