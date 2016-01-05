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
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />" type="text/css">
<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script> -->
<script src="<c:url value="/resource/js/scripts.js" />"type="text/javascript"></script>
<title>图片管理</title>
</head>
<body>
	<section class="container">
	<legend align="left">图片下载</legend>
		<form:form modelAttribute="picPackage" enctype="multipart/form-data" >
			<table  border="1"  cellspacing="10">
				<tr>
					<td>下载源</td> 
					<td width="90%"> 
						<form:radiobutton path="picSource" value="Alibaba" />阿里巴巴/淘宝
						<form:radiobutton path="picSource" value="Amazon"/>亚马逊
					</td>
				</tr>
				
				<tr>
					<td>图片保存地址</td> 
					<td> 
						<form:input path="downloadPath" type="text" style="width:100%"/>
					</td>
				</tr>
				
				<tr>
					<td>下载链接1</td> 
					<td> 
						<form:input path="urlList[0].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接2</td> 
					<td> 
						<form:input path="urlList[1].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接3</td> 
					<td> 
						<form:input path="urlList[2].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接4</td> 
					<td> 
						<form:input path="urlList[3].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接5</td> 
					<td> 
						<form:input path="urlList[4].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接6</td> 
					<td> 
						<form:input path="urlList[5].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接7</td> 
					<td> 
						<form:input path="urlList[6].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接8</td> 
					<td> 
						<form:input path="urlList[7].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接9</td> 
					<td> 
						<form:input path="urlList[8].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>下载链接10</td> 
					<td> 
						<form:input path="urlList[9].url" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"> 
						<c:if test="">
							
						</c:if>
						<input type="submit" id="btnAdd" class="btn btn-primary" value="下载" />
					</td>
				</tr>
			</table>
		</form:form>
	</section>
</body>
</html>