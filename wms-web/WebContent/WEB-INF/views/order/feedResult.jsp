<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!Doctype html>
<html>
<head>

<script type="text/javascript">


</script>
<title>获取提交结果</title>
</head>
<body>
	<div class="container">
		<div align="left">
			<ol class="breadcrumb" >
			  <li ><a href="<spring:url value='/order/list' />">所有订单</a></li>
			  <li class="active">获取提交结果</li>
			</ol>
		</div>
		<div align="left">
		
	  	<form action="/wms-web/order/feedResult" method="post">
				feedSubmissionId ： 
				<input type="text" id="feedSubmissionId"  name="feedSubmissionId">
				
				<input type="submit" value="submit" />
		</form>
		<div>
			<p>${feedSubmissionId }</p>
			<c:if test="${result !=null }">
				${result}
			</c:if>
		</div>
		</div>
	</div>
</body>
</html>