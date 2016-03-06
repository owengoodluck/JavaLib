<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import = "com.owen.wms.web.constants.AppConstant" %>
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
<title>订单详情-${order.amazonOrderId}</title>
</head>
<body>
	<div>
		<ol class="breadcrumb" align="left">
		  <li ><a href="<spring:url value='/order/list' />">Lekani产品列表</a></li>
		  <li class="active">产品详情   ${prod.productID }</li>
		</ol>
	</div>
	
	<section class="container-fluid " align="left">
		<table class="table table-striped">
			<caption>
				产品基本信息
			</caption>
			<thead>
				<tr align="left">
				<!-- 	<th>类别</th>
					<th>值</th> -->
				</tr>
			</thead>
			<tbody align="left">	
				<tr >
					<td><b>ID</b></td>  <td>${prod.productID }</td>
					<td></td>
					<td><b>SKU</b></td>  <td>${prod.SKU }</td>
				</tr>
				<tr>
					<td><b>barCode</b></td>  <td>${prod.barCode }</td>
					<td></td>
					<td><b>分类</b></td>  <td>${prod.catID } - ${prod.catName }</td>
				</tr>
				<tr>
					<td><b>品名</b></td>  <td>${prod.brandID } - ${prod.brandName }</td>
					<td></td>
					<td><b>price</b></td>  <td>${prod.price }</td>
				</tr>
				<tr>
					<td><b>stock</b></td>  <td>${prod.stock }</td>
					<td></td>
					<td><b>isPackage</b></td>  <td>${prod.isPackage }</td>
				</tr>
				<tr>
					<td><b>weight</b></td>  <td>${prod.weight }</td>
					<td></td>
					<td><b>grossWeight</b></td>  <td>${prod.grossWeight }</td>
				</tr>
				<tr>
					<td><b>packageHeight</b></td>  <td>${prod.packageHeight }</td>
					<td></td>
					<td><b>packageLength</b></td>  <td>${prod.packageLength }</td>
				</tr>
				<tr>
					<td><b>packageWidth</b></td>  <td>${prod.packageWidth }</td>
					<td></td>
					<td><b>shopTime</b></td>  <td>${prod.shopTime }</td>
				</tr>
				<tr>
					<td><b>keyWords</b></td>  <td>${prod.keyWords }</td>
					<td></td>
					<td><b>mainImage</b></td>  <td>${prod.mainImage }</td>
				</tr>
				<tr>
					<td><b>name</b></td>  <td>${prod.name }</td>
					<td></td>
					<td></td> <td></td>
				</tr>
			</tbody>
		</table>	
	
		<table class="table table-striped">
			<caption>产品属性详情</caption>
			<thead>
				<tr align="center">
					<th>属性名</th>
					<th>属性值</th>
					<th>isSKU</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${prod.attributes}" var="item" >
					<tr>
						<td>${item.attrName}</td>
						<td>-${item.attrValue}</td>
						<td>-${item.isSKU}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<table class="table table-hover" >
			<tbody>
				<c:forEach items="${images}" var="image" varStatus="status">
					<tr align="center">
						<td>${status.index+1}</td>
						<td>
							<c:if test="${ image !=null }">
								<img src="${image}"> 
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
		${prod.description }
	</section>
</body>
</html>