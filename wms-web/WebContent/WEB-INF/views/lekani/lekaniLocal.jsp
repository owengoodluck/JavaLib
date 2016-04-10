<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!Doctype html>
<html>
<head>
<link rel="stylesheet" href='<c:url value="/resource/css/bootstrap.min.css" />' type="text/css" />
<script src='<c:url value="/resource/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resource/js/bootstrap.min.js"/>'></script>

<link rel="stylesheet" href='<c:url value="/resource/css/jquery-ui.min.css" />' type="text/css" />
<script src='<c:url value="/resource/js/jquery-ui.min.js"/>'></script>

<script type="text/javascript">
$(function() {
    $( "#sendDateFrom" ).datepicker({"dateFormat":"yy-mm-dd"});
    $( "#sendDateTo" ).datepicker({"dateFormat":"yy-mm-dd"});
});
function submitForm(num){
	//getjson() ;
	var currentPage = $('#currentPage').val();
	if(0==num){
		$('#currentPage').val(1);
	}else{
		$('#currentPage').val(Number(currentPage)+Number(num));
	}
	$('#queryForm').submit();
}

function getjson() {  
    $.ajax( {  
        type : "get",  
        url : "/wms-web/lekani/getBrandListByCategoryID",  
        dataType:"json",  
        success : function(msg) {  
            alert("Data Saved: " + msg);
        }  
    });  
}  
function updateStatus(prodID,status){
	$.ajax( {  
        type : "get",  
        url : "/wms-web/lekani/updateStatus?prodID="+prodID+"&status="+status,  
        dataType:"json",  
        success : function(msg) {
        	
        }  
    });  
}

function selectAll(isCheckeced){
	$('[name=prodIDs]').each(function(){
		if($(this).is(':checked')){     
			this.checked=isCheckeced;
		}else{     
			this.checked=isCheckeced;
		}  
	});
}

function batchProcess(method){
	var result = confirm(method+'确定进行批量操作？');  
	if(result){
		$('#discardBtn').attr('disabled',"true");
		$('#selectedBtn').attr('disabled',"true");
		$('#convertedBtn').attr('disabled',"true");
		$('#getLatesStockBtn').attr('disabled',"true");
		
		$('#processMethod').val(method);
		$("#queryForm").attr("action", "/wms-web/lekani/batchProcess");
		//$('#batchProcessForm').submit();
		$('#queryForm').submit();
	}  
}

function cleanForm(){
	$('#prodID').val(null);
	$('#stock').val(null);
}
</script>
<title>Lekani本地产品列表</title>
</head>
<body>
	<div>
		<ol class="breadcrumb" align="left">
		  <!-- <li><a href="#">Home</a></li> -->
		  <li class="active">Lekani本地产品列表</li>
		</ol>
	</div>
	
	<div class="container-fluid">
	  <form:form modelAttribute="queryForm" enctype="multipart/form-data" action="/wms-web/lekani/pageQueryLocal">
	   <div class="row">
		      	<div align="left">
		      		<input type="button" class="btn btn-primary" value="清空" onclick="cleanForm()"/>
			      	产品ID：<form:input path="prodID" size="3"/>
			      	类别:
			      	<form:select path="categoryID" onchange="submitForm(0)">
			      		<form:option value="0">请选择</form:option>
						<form:option value="216">项链</form:option>
						<form:option value="207">手链</form:option>
						<form:option value="217">吊坠</form:option>
			      		<form:option value="201">戒指</form:option>
						<form:option value="218">长款项链</form:option>
						<form:option value="205">项链&吊坠</form:option>
						<form:option value="219">链条配链</form:option>
						<form:option value="210">耳钉</form:option>
						<form:option value="211">耳圈</form:option>
						<form:option value="212">耳钩&耳坠</form:option>
						<form:option value="213">耳夹&耳扣</form:option>
						<form:option value="208">手镯</form:option>
						<form:option value="220">脚链</form:option>
						<form:option value="204">饰品套装</form:option>
						<%-- 
						<form:option value="104">LEKANI 纯银首饰</form:option>
						<form:option value="202">耳饰</form:option>
						<form:option value="215">耳饰花托</form:option>
						<form:option value="222">胸针</form:option>
						<form:option value="244">时尚眼镜</form:option>
						<form:option value="225">钥匙扣</form:option>
						<form:option value="226">钱夹</form:option>
						<form:option value="242">手拿包</form:option>
						<form:option value="243">单肩包</form:option>
						<form:option value="245">太阳镜</form:option>
						<form:option value="247">偏光镜</form:option>
						<form:option value="248">眼镜盒</form:option>
						<form:option value="241">时尚包包</form:option>
						<form:option value="221">领带夹&袖扣</form:option>
						<form:option value="224">钥匙扣&钱夹</form:option>
						<form:option value="223">头饰、发饰</form:option>
						<form:option value="54">包装&展件</form:option> 
						--%>
			      	</form:select>
			      	品牌:
			      	<form:select path="brandID" onchange="submitForm(0)">
			      		<form:option value="0">请选择</form:option>
			      		<form:option value="29">潘多拉系列</form:option>
						<form:option value="13">古玛雅</form:option>
						<form:option value="17">香芭拉</form:option>
						<form:option value="21">时尚K金产品</form:option>
						<form:option value="12">珍姿美</form:option>
						<form:option value="28">民族风系列</form:option>
						<form:option value="25">夸张大牌产品</form:option>
						<form:option value="11">依娜丽饰</form:option>
						<form:option value="33">天然石系列</form:option>
						<form:option value="10">又一银</form:option>
						<form:option value="20">K金锆石类</form:option>
						<form:option value="31">爆款品类</form:option>
						<form:option value="30">韩风</form:option>
						<form:option value="14">法伯丽</form:option>
						<form:option value="22">时尚银饰产品</form:option>
						<form:option value="99">其它</form:option>
						<!--  
						<form:option value="19">品牌B</form:option>
						<form:option value="18">品牌A</form:option>
						<form:option value="32">威妮华</form:option>
						<form:option value="37">钛钢系列</form:option>
						<form:option value="27">婚饰系列</form:option>
						-->
			      	</form:select>
			      	状态
			      	<form:select path="status" onchange="submitForm(0)">
			      		<form:option value="selected,null">默认</form:option>
			      		<form:option value="selected">备选</form:option>
			      		<form:option value="discard">丢弃</form:option>
			      		<form:option value="converted">已转换</form:option>
			      		<form:option value="all">全部</form:option>
			      	</form:select>
			      	库存数小于：<form:input path="stock" size="2"/>
					<input type="submit" id="btnAdd" class="btn btn-primary" value="查询" onclick="submitForm(0)"/>
					<input type="button" id="getLatesStockBtn" class="btn btn-primary" value="最新库存" onclick="batchProcess('getLatesStock')"/>
					<input type="button" id="discardBtn" class="btn btn-primary" value="丢弃" onclick="batchProcess('discard')"/>
					<input type="button" id="selectedBtn" class="btn btn-primary" value="备选" onclick="batchProcess('selected')"/>
					<input type="button" id="convertedBtn" class="btn btn-primary" value="转换" onclick="batchProcess('converted')"/>
					每页显示：<form:input path="pageSize" size="2"/>
					总页数 <b>${page.totalPage }</b>： 总条数<b>${page.totalCount }</b>
		      		<input type="button" value="上一页" class="btn btn-primary" <c:if test='${!page.hasPrePage }'>disabled="disabled"</c:if> onclick="submitForm(-1)"/>
					当前页：<form:input path="currentPage" size="2"/>
					<input type="button" value="下一页" class="btn btn-primary" <c:if test='${!page.hasNextPage }'>disabled="disabled"</c:if> onclick="submitForm(1)"/>
		      	</div>
			<%-- </form:form> --%>
	   </div>
	   
	<section class="container-fluid ">
		<%-- <form id="batchProcessForm" enctype="multipart/form-data" action="/wms-web/lekani/batchProcess" method="post"> --%>
		<input id="processMethod" name="processMethod" type="hidden">
		<table class="table table-hover" >
			<thead>
				<tr >
					<th><input type="checkbox" onchange="selectAll(this.checked)"/>全选</th>
					<th>图片</th>
					<th>状态</th>
					<th>ID</th>
					<th>官网链接</th>
					<th>SKU</th>
					<th>分类</th>
					<th>品牌</th>
					<th>在售</th>
					<th>售价</th>
					<th>最新库存</th>
					<th>上次库存</th>
					<th>名称</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="prod" varStatus="status">
					<tr align="left">
						<td>
							${status.index+1}
							<input name="prodIDs" id="prodIDs" type="checkbox" value="${prod.productID}"/>
						</td>
						<td>
							<c:if test="${ prod.mainImage !=null }">
								<img src="${prod.mainImage}"  height="100"  onclick='window.open("${prod.mainImage}")'> 
							</c:if>
						</td>
						<td>
							<c:choose>
								<c:when test="${prod.status == 'converted'}">已转换</c:when>
								<c:otherwise>
									<input type="radio" name="status_${status.index+1}" value="discard" onchange="updateStatus( '${prod.productID}','discard')" <c:if test="${prod.status == 'discard'}">checked="checked"</c:if>/>丢弃 &nbsp;&nbsp;
									<input type="radio" name="status_${status.index+1}" value="selected" onchange="updateStatus('${prod.productID}','selected')"<c:if test="${prod.status == 'selected'}">checked="checked"</c:if>/>备选
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href='<c:url value="/lekani/prodDetail/${prod.productID}" />' target="_blank" >产品详情</a>
						</td>
						<td>
							<a href='<c:url value="http://www.pfhoo.com/p/${prod.productID}.html" />' target="_blank" >${prod.productID}</a>
						</td>
						<td>${prod.SKU}</td>
						<td>${prod.catName}</td>
						<td>
							<c:choose>
								<c:when test="${prod.brandName == 'pandora'}">潘多拉系列</c:when>
								<c:when test="${prod.brandName == 'Titanium series'}">钛钢系列</c:when>
								<c:when test="${prod.brandName == 'GOMAYA'}">古玛雅</c:when>
								<c:when test="${prod.brandName == 'Shambala'}">香芭拉</c:when>
								<c:when test="${prod.brandName == 'JETHMY'}">珍姿美</c:when>
								<c:when test="${prod.brandName == 'Folk-custom'}">民族风系列</c:when>
								<c:when test="${prod.brandName == 'Statement Jewelry'}">夸张大牌产品</c:when>
								<c:when test="${prod.brandName == 'INALIS'}">依娜丽饰</c:when>
								<c:when test="${prod.brandName == 'Viennois'}">威妮华</c:when>
								<c:when test="${prod.brandName == 'Luminous Series'}">夜光系列</c:when>
								<c:when test="${prod.brandName == 'Handbags'}">包包系列</c:when>
								<c:when test="${prod.brandName == 'Nature Stone'}">天然石系列</c:when>
								<c:when test="${prod.brandName == 'A New Brand'}">品牌A</c:when>
								<c:when test="${prod.brandName == 'YUEYIN'}">又一银</c:when>
								<c:when test="${prod.brandName == 'KGP Zircon'}">K金锆石类</c:when>
								<c:when test="${prod.brandName == 'Korean Styles'}">韩风</c:when>
								<c:when test="${prod.brandName == 'FAVOURER'}">法伯丽</c:when>
								<c:when test="${prod.brandName == 'B New Brand'}">品牌B</c:when>
								<c:when test="${prod.brandName == 'Watch series'}">手表系列</c:when>
								<%-- <c:when test="${prod.brandName == 'Other'}">时尚K金产品</c:when>
								<c:when test="${prod.brandName == 'Other'}">婚饰系列</c:when>
								<c:when test="${prod.brandName == 'Other'}">爆款品类</c:when>
								<c:when test="${prod.brandName == 'Other'}">时尚银饰产品</c:when>
								<c:when test="${prod.brandName == 'Other'}">其它</c:when> --%>
								<c:otherwise>
									${prod.brandName}
								</c:otherwise>
							</c:choose>
						</td>
						<td>${prod.onSale}</td>	
						<td>${prod.price}</td>
						<c:choose>
							<c:when test="${prod.stock<1}">
								<td><span class="label label-danger">${prod.stock}</span> </td>
							</c:when>
							<c:when test="${prod.stock >=1 && prod.stock<5}">
								<td><span class="label label-warning">${prod.stock}</span> </td>
							</c:when>
							<c:otherwise> <td>${prod.stock}</td> </c:otherwise>
						</c:choose>
						<td>
							<c:choose>
								<c:when test="${prod.stockPrevious<1}">
									<span class="label label-danger">${prod.stockPrevious}</span> 
								</c:when>
								<c:when test="${prod.stockPrevious >=1 && prod.stockPrevious<5}">
									<span class="label label-warning">${prod.stockPrevious}</span> 
								</c:when>
								<c:otherwise> ${prod.stockPrevious} </c:otherwise>
							</c:choose>
						</td>
						<td>${prod.name}</td>
						<%-- <td>${prod.description}</td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%-- </form> --%>
	</section>
	
	 <div class="row">
     	<input type="button" value="上一页" class="btn btn-primary" <c:if test='${!page.hasPrePage }'>disabled="disabled"</c:if> onclick="submitForm(-1)"/>
		<input type="button" value="下一页" class="btn btn-primary" <c:if test='${!page.hasNextPage }'>disabled="disabled"</c:if> onclick="submitForm(1)"/>
	 </div>
		</form:form>
	 </div>
</body>
</html>