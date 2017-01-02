<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!Doctype html>
<html>
<head>

<script type="text/javascript">
$(function() {
    $( "#sendDateFrom" ).datepicker({"dateFormat":"yy-mm-dd"});
    $( "#sendDateTo" ).datepicker({"dateFormat":"yy-mm-dd"});
});
function submitForm(num){
	var currentPage = $('#currentPage').val();
	if(0==num){
		$('#currentPage').val(1);
	}else{
		$('#currentPage').val(Number(currentPage)+Number(num));
	}
	$('#expressQueryForm').submit();
}

function cleanForm(){
	$('#expressID').val(null);
	$('#orderID').val(null);
	$('#receiver').val(null);
	$('#sendDateFrom').val(null);
	$('#sendDateTo').val(null);
	$('#channel').val(null);
}

</script>
<title>快递列表</title>
</head>
<body>
	<div>
		<ol class="breadcrumb" align="left">
		  <!-- <li><a href="#">Home</a></li> -->
		  <li class="active">所有快递</li>
		</ol>
	</div>
	
	<div class="container-fluid">
	   <div class="row">
	      	<form:form modelAttribute="expressQueryForm" enctype="multipart/form-data" action="/wms-web/express/pageQuery">
		      	<div align="left">
			      	快递单号:<form:input path="expressID" size="10"/>
			      	订单号:<form:input path="orderID" size="10"/>
			      	收件人:<form:input path="receiver" size="10"/>
			      	发货日期 从:<form:input path="sendDateFrom" size="10"/>
			      	到:<form:input path="sendDateTo" size="10"/>
			      	发货渠道:
			      	<form:select path="channel">
			      		<form:option value="">全部</form:option>
			      		<form:option value="中邮北京E邮宝(线下)">中邮北京E邮宝(线下)</form:option>
			      		<form:option value="中邮北京平邮小包">中邮北京平邮小包</form:option>
			      		<form:option value="中邮上海E邮宝(线下)">中邮上海E邮宝(线下)</form:option>
			      	</form:select>
			      	<input type="button" value="清空条件" class="btn btn-primary" onclick="cleanForm()"/>
					<input type="submit" id="btnAdd" class="btn btn-primary" value="查询" onclick="submitForm(0)"/>
					每页显示：<form:input path="pageSize" size="3"/>
					总页数 <b>${page.totalPage }</b>： 总条数<b>${page.totalCount }</b>
		      		<input type="button" value="上一页" class="btn btn-primary" <c:if test='${!page.hasPrePage }'>disabled="disabled"</c:if> onclick="submitForm(-1)"/>
					当前页：<form:input path="currentPage" size="3"/>
					<input type="button" value="下一页" class="btn btn-primary" <c:if test='${!page.hasNextPage }'>disabled="disabled"</c:if> onclick="submitForm(1)"/>
		      	</div>
			</form:form>
	   </div>
	   
	<section class="container-fluid ">
		<table class="table table-hover" >
			<thead>
				<tr >
					<th>快递单号</th>
					<th>订单号</th>
					<th>是否发货</th>
					<th>创建日期</th>
					<th>发货渠道</th>
					<th>收件人</th>
					<th>电话</th>
					<th>州</th>
					<th>城市</th>
					<th>自量(克)</th>
					<th>称重(克)</th>
					<th>运费</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="express" >
					<tr align="left">
						<td >${express.epcode}</td>
						<td align="left"><a href='<c:url value="/order/detail/${express.userOrderNumber}" />' target="_blank" >${express.userOrderNumber}</a></td>
						<td>
							<c:if test='${ express.scanedConfirmedDeliver }'>
								<span class="label label-success">已扫描</span>
							</c:if>
							<c:if test='${ !express.scanedConfirmedDeliver }'>
								<span class="label label-danger">未扫描</span>
							</c:if>
						</td>
						<td><fmt:formatDate value="${express.sendDate}" pattern="yyyy-MM-dd "/></td>
						<td>${express.channel}</td>
						<td>${express.name}</td>
						<td>${express.phone}</td>
						<td>${express.state}</td>
						<td>${express.city}</td>
						<td>
							<c:if test='${express.weight!=50 }'>
								${express.weight}
							</c:if>
						</td>
						<td>
							<c:choose>
								<c:when test="${express.weight!=50 &&express.weight!=0 && express.weightByYanwen - express.weight >10 }">
									<span class="label label-danger">${express.weightByYanwen}</span>
								</c:when>
								<c:otherwise>
									${express.weightByYanwen}
								</c:otherwise>
							</c:choose>
						</td>
						<td>${express.feeTotal}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</body>
</html>