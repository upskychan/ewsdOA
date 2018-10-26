<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传文件</title>
<link
	href="<%=request.getContextPath()%>/Static/BJUI/themes/css/style.css"
	rel="stylesheet">
<script
	src="<%=request.getContextPath()%>/Static/BJUI/js/jquery-1.7.2.min.js"></script>
<script
	src="<%=request.getContextPath()%>/Static/BJUI/js/jquery.cookie.js"></script>
<script src="<%=request.getContextPath()%>/Static/js/jquery.md5.js"></script>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
</head>
<body>
	<div class="bjui-pageContent">
		<form method="post" enctype="multipart/form-data"
			action="<%=request.getContextPath()%>/upload/uploadFile.do"
			data-toggle="validate" data-reload-navtab="true">
			<div class="pageFormContent" data-layout-h="0">
				<table class="table table-condensed table-hover" width="100%">
					<tbody>
						<tr>
							<td colspan="2">File to upload: <input type="file" id="myFile" 
								name="myFile" onchange="uploadCheck(this)"><br /> Notes
								about the file: <input type="text" name="note"><br /> <br />
								<input type="submit" value="提交"> to upload the file!
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
	</div>
	<div class="bjui-pageFooter">
		<ul>
			<!--         <li><button type="button" class="btn-close" data-icon="close">关闭</button></li> -->
			<!--         <li><button type="submit" class="btn-default" data-icon="save">保存</button></li> -->
		</ul>
	</div>
</body>
<script type="text/javascript">
	function uploadCheckBak(target){
		var fileSize =  target.files[0].size;
		alert(fileSize);
	}
	
	function uploadCheck(target){
		if(getFileSize("myFile") == -1){
			alert("文件错误！");
			return;
		}else if(getFileSize("myFile") == 0){
			alert("文件不能为空！");
			return;
		}else if(getFileSize("myFile") > 30){
			alert("文件不能超过30M！");
			return;
		}
	}
	
	//获取上传文件的大小
	function getFileSize(eleId) {
		try {
			var size = 0;
			size = $('#' + eleId)[0].files[0].size;//byte
			size = size / 1024;//kb
			size = size / 1024;//mb
			//alert('上传文件大小为' + size + 'M');
			return size;
		} catch (e) {
			alert("错误：" + e);
			return -1;
		}
	}
</script>
</html>
