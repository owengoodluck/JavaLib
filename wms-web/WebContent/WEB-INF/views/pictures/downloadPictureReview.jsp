<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var clipboard = new Clipboard('.copy-button');

clipboard.on('success', function(e) {
    console.log(e);
});

clipboard.on('error', function(e) {
    console.log(e);
});

$(document).ready(function(){
	$("img").each(function(){
		//this.removeAttr("width"); 
		//this.removeAttr("height");
		
		var pic_real_width = this.width;
		var pic_real_height = this.height;
		
		//this.setAttribute("height", pic_real_height);
		//this.setAttribute("width", pic_real_width);
		
		//this.width = pic_real_width;
		//this.height=pic_real_height
		//this.addAttr("width" , pic_real_width);
		//this.addAttr("height",pic_real_height);
	});
	
});
function submitForm(){
	$('#submitButton').val("正在下载...");
	$('#submitButton').attr('disabled',"true");
	$('#cleanButton').attr('disabled',"true");
	$('#picPackage').submit();
}
function clearForm(){
	var result = confirm('确定要清空？');  
	if(!result){  
		return;
	}
	$(":text").each(function(){
		var idStr = this.id;
		if(idStr.indexOf('urlList')>-1){
			$( document.getElementById(idStr) ).val(null);
		}
	});
}

	
</script>
<title>图片下载Review</title>
</head>
<body>
	<div>
	
		<legend align="left">图片下载Review</legend>
		<form:form modelAttribute="picPackage" enctype="multipart/form-data" >
			<table  border="1"  cellspacing="10">
				<tr>
					<td>图片大于多少K</td> 
					<td > 
						<form:input path="picSize" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>图片保存地址</td> 
					<td> 
						<form:input path="downloadPath" type="text" style="width:100%"/>
					</td>
				</tr>
				
				<tr>
					<td>下载链接</td> 
					<td width="90%"> 
						<form:input path="urlList[0].url" type="text" style="width:100%" ondblclick="this.select()"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"> 
						<c:if test="${msg !=null }">
							<span class="label label-success">${msg}</span>
						</c:if>
						<input id="cleanButton" type="button" value="清空" class="btn btn-primary" onclick="clearForm()" />
						<input id="submitButton" type="button" value="预览" class="btn btn-primary" onclick="submitForm()"/>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
	<div>
		<c:forEach items="${picList}" var="pic" varStatus="status">
			<input id="pic_${status.index}" type="text" value="${pic}" size="80%" data-clipboard-target="this" ondblclick="selectAndCopy(this)">
			<button class="copy-button" data-clipboard-target="#pic_${status.index}" >复制图片地址 </button>
			<p>
				<img src="${pic}"  onclick='window.open("${pic}")'  alt="undefined"/> 
			</p>
		</c:forEach>
	
	</div>
</body>
</html>