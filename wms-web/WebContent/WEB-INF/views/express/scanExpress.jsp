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
	$(document).ready(function() {
		$('#expressNumber').focus();

		$("#expressNumber").keypress(function(event) {
			//alert(event.keyCode);
			if (event.keyCode == "13" || event.keyCode == "9" ||event.keyCode == 13 || event.keyCode == 9) {
				submitForm();
			}
		});

		$(window).keypress(function(event) {
			if(event.target && event.target.id){
				if("expressWeight" == event.target.id || "previousExpressNumber" == event.target.id || "updateBtn" == event.target.id)
				return;
			}
			getFocus();
		});
		
		$(window).mousedown(function(event) {
			if(event.target && event.target.id){
				if("expressWeight" == event.target.id || "previousExpressNumber" == event.target.id || "updateBtn" == event.target.id){
					return;
				}
			}
			event.preventDefault();
			getFocus();
			
		});
	});

	function getFocus() {
		$('#expressNumber').focus();
	}

	function submitForm() {
		var expressNumber = $('#expressNumber').val();
		if (expressNumber != null && expressNumber.length > 0) {
			$('#expressScanForm').submit();
		}
	}
	
	function updateWeight(){
		var expressNumber = $("#previousExpressNumber").val();
		var expressWeight = $("#expressWeight").val();
		$.ajax({url:"/wms-web/express/updateWeight?weight="+expressWeight+"&expressNumber="+expressNumber,
				success: function(data) {
					if("OK"==data){
			         	$("#updateLabel").text("Update success:"+new Date());
					}else{
						$("#updateLabel").text("Update faile:"+data);
					}
			      }  
		});
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
			<form:form modelAttribute="expressScanForm" method="post" action="/wms-web/express/scan" >
				<table class="table table-hover" border="2">
					<tr>
						<td>快递单号</td>
						<td width="90%"><form:input path="expressNumber" type="text" style="width:100%" /></td>
					</tr>
				</table>
				<table class="table table-hover" border="2">
					<tbody>
						<tr align="left">
							<td colspan="${expressScanForm.orderItemSet.size()+1}">
								快递单号: <input id="previousExpressNumber" type="text" value=" ${expressScanForm.previousExpressNumber }">
								&nbsp;&nbsp;&nbsp;&nbsp;
								购买日期: ${ expressScanForm.order.purchaseDate}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								重量自称:<input id="expressWeight" type="text"> <input id="updateBtn" type="button" value="修改" onclick="updateWeight()">
								<span id="updateLabel"></span>
							</td>
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