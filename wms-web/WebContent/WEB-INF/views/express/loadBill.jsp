<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!Doctype html>
<html>
<head>
<script type="text/javascript">
	
</script>
<title>加载燕文账单文件</title>
</head>
<body>
	<section class="container">
		<div>
			<ol class="breadcrumb" align="left">
				<h1><li class="active">加载燕文账单文件</li></h1>
			</ol>
		</div>
		<div class="row">
			<form method="post" action="/wms-web/express/loadBill" enctype="multipart/form-data">
				<table class="table table-hover" border="2">
					<tr>
						<td>账单文件</td>
						<td><input name="billFile" type="file" style="width:100%" /></td>
						<td><input type="submit" value="提交"/></td>
					</tr>
				</table>
			</form>
			
			<table class="table table-hover" border="2">
				<c:if test="${list != null }">
					<c:forEach items="${list}" var="dataRow" >
						<tr>
							<c:forEach items="${dataRow}" var="dataCol" >
								<td>${dataCol}</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</section>
</body>
</html>