<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import = "com.owen.wms.common.constant.AppConstant" %>
<!Doctype html>
<html>
<link rel="stylesheet" href='<c:url value="/resource/css/bootstrap.min.css" />' type="text/css" />
<script src='<c:url value="/resource/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resource/js/bootstrap.min.js"/>'></script>

<link rel="stylesheet" href='<c:url value="/resource/css/jquery-ui.min.css" />' type="text/css" />
<script src='<c:url value="/resource/js/jquery-ui.min.js"/>'></script>
<script type="text/javascript">
function submitForm(){
}


</script>
<title>导入Lekani产品</title>
</head>
<body>
	<div>
		<ol class="breadcrumb" align="left">
		  <li ><a href="<spring:url value='/lekani/pageQueryLocal' />">Lekani产品列表</a></li>
		  <li class="active">产品详情   ${prod.productID }</li>
		</ol>
	</div>
	
	<section class="container-fluid" align="left"> 
	<form:form modelAttribute="importForm" enctype="multipart/form-data" action="/wms-web/lekani/importByID">
			<table class="table table-striped">
				<caption>
					产品导入
				</caption>
				<tbody align="left">	
					<tr >
						<td>产品ID</td>  
						<td><form:input path="prodID"/></td>
					</tr>		
					<tr >
						<td>产品SKU</td>  
						<td><form:input path="sku"/></td>
					</tr>	
					<tr >
						<td colspan="2"><input type="submit" value="提交"></td>
					</tr>
				</tbody>
			</table>
	</form:form>
	</section>
</body>
</html>