package app.home.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;

import app.common.base.ExecuteResult;
import app.home.model.User;

@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {
	private String viewPath = "App/OA/Article/";
	private ExecuteResult executeResult = new ExecuteResult();
	
	@RequestMapping("/uploadFileInit")
	public String uploadFileInit(HttpServletRequest request){
		return viewPath + "uploadFile";
	}

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public Object upload(HttpServletRequest request, HttpServletResponse response) {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("myFile");

		System.out.println(multipartRequest.getParameter("note"));
		
		String fileName = file.getName();
		String originalFilename =  file.getOriginalFilename();
		
		long fileSize = file.getSize();
		if(fileSize>32505856){
			return executeResult.jsonReturn(300, "文件过大，上传文件大小限制为31M！");
		}

		// 上传后记录的文件...
		try {
			File pathFile =new File("d:/ssm/upload/");
			if (!pathFile.isDirectory()) {
				pathFile.mkdirs();
			}
			
			File imageFile = new File("d:/ssm/upload/"+originalFilename);
			file.transferTo(imageFile);
			return executeResult.jsonReturn(200);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return executeResult.jsonReturn(300, e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return executeResult.jsonReturn(300, e.getMessage());
		}

	}

}
