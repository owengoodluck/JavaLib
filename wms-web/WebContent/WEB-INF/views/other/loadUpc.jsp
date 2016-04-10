<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<c:url value="/resource/css/styles.css" />" type="text/css">
<link rel="stylesheet" href="<c:url value="/resource/css/bootstrap.min.css" />" type="text/css">
<script src="<c:url value="/resource/js/scripts.js" />" type="text/javascript"></script>
<script src="<c:url value="/resource/js/jquery.min.js" />" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	 
});

</script>
<title>加载UPC文件</title>
</head>
<body>
	<section class="container">
	<legend align="left">加载UPC文件</legend>
		<form action="<c:url value='/prod/loadUPCFile2DB'/>" method="post">
			<table  border="1"  cellspacing="10">
				<tr>
					<td>UPC文件地址</td> 
					<td width="90%"> 
						<input type="text" style="width:100%" name="upcFilePath" value="${upcFilePath }"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"> 
						<input type="submit" id="btnAdd" class="btn btn-primary" value="加载UPC"  onclick="submitForm()" />
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>