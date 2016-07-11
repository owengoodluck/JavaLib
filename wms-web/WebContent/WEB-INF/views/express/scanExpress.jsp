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
<link rel="stylesheet"
	href='<c:url value="/resource/css/bootstrap.min.css" />'
	type="text/css"
/>
<script src='<c:url value="/resource/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resource/js/bootstrap.min.js"/>'></script>
<link rel="stylesheet"
	href='<c:url value="/resource/css/jquery-ui.min.css" />'
	type="text/css"
/>
<script src='<c:url value="/resource/js/jquery-ui.min.js"/>'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#expressNumber').focus();

		$("#expressNumber").keypress(function(event) {
			//alert(event.keyCode);
			if (event.keyCode == "13" || event.keyCode == "9" ||event.keyCode == 13 || event.keyCode == 9) {
				//alert($("#expressNumber").val());
				//alert(event.keyCode);
				submitForm();
			}
		});

		$(window).keypress(function(event) {
			getFocus();
		});

	});

	function getFocus() {
		//alert($('#expressNumber').val());
		$('#expressNumber').focus();
	}

	function submitForm() {
		var expressNumber = $('#expressNumber').val();
		if (expressNumber != null && expressNumber.length > 0) {
			$('#expressScanForm').submit();
		}
	}
</script>
<title>读取快递单信息</title>
</head>
<body>
	<section class="container">
		<div>
			<ol class="breadcrumb" align="left">
				<h1><li class="active">读取快递单信息</li></h1>
			</ol>
		</div>
		<div class="row">
			<form:form modelAttribute="expressScanForm" method="post" action="/wms-web/yanwen/scan" >
				<table class="table table-hover" border="2">
					<tr>
						<td>快递单号</td>
						<td width="90%"><form:input path="expressNumber" type="text" style="width:100%" /></td>
					</tr>
				</table>
				<table class="table table-hover" border="2">
					<tbody>
						<tr align="left">
							<td colspan="${expressScanForm.orderItemSet.size()+1}">快递单号: ${expressScanForm.previousExpressNumber }</td>
						</tr>
						<tr align="left">
							<td width="1%">SKU</td>
							<c:forEach items="${expressScanForm.orderItemSet}" var="item" varStatus="status" >
								<td>${ item.getSellerSKU().itemSku}</td>
							</c:forEach>
						</tr>
						<tr align="left">
							<td width="1%">颜色</td>
							<c:forEach items="${expressScanForm.orderItemSet}" var="item" varStatus="status" >
								<td>${ item.getSellerSKU().colorName}</td>
							</c:forEach>
						</tr>
						<tr align="left">
							<td width="1%">大小</td>
							<c:forEach items="${expressScanForm.orderItemSet}" var="item" varStatus="status" >
								<td>${ item.getSellerSKU().sizeName}</td>
							</c:forEach>
						</tr>
						<tr align="center">
							<td width="1%" align="left">图片</td>
							<c:forEach items="${expressScanForm.orderItemSet}" var="item" varStatus="status" >
								<td width="9%"><img src="/wms-web/img${item.getSellerSKU().getLocalImagePath()}" height="400" ></td>
							</c:forEach>
						</tr>
					</tbody>
				</table>
			</form:form>
		</div>
	</section>
</body>
</html>