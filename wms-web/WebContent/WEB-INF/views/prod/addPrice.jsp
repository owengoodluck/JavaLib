<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- <% Double shippingFee = com.owen.wms.web.constants.AppConstant.shippingFee; %>  --%>
<%@ page import = "com.owen.wms.common.constant.AppConstant" %>
<!Doctype html>
<html>
<script type="text/javascript">
$(document).ready(function(){
	
	$("input").each(function(){
		$(this).keypress(function(){
			copyValue(this);
		});
		$(this).keyup(function(){
			copyValue(this);
		});
		$(this).change(function(){
			copyValue(this);
		});
	});
	
});

function submitForm(preOrNext){
	$('#preOrNext').val(preOrNext);
	$('#productsForm').submit();
}

function loadUPCAll(){
	var result = confirm('确定要加载所有UPC？');  
	if(result){
		$("#productsForm").attr("action", "/wms-web/prod/loadUPCAll");
		$('#productsForm').submit();
	}
}


function cleanUPC(sku,itemID){
	var result = confirm('确定要清除'+sku+'的UPC？');  
	if(result){
		$.get('/wms-web/prod/cleanUPC/'+sku, function(result){
			if( result ){
			    $( document.getElementById(itemID) ).val(null);
			}else{
				alert('return result = '+result);
			}
		});
	}
}
</script>
<title>AddPordPrice</title>
</head>
<body>
	<div>
		<ul class="nav nav-tabs">
		  <li ><a href="#" onclick="submitFormAndGoTo('addTitle')">产品基本信息 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPicture')">产品图片 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addBulletPoint')">产品特性描述 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addKeyword')">搜索关键字 </a></li>
		  <li class="active"><a href="#">价格和库存 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addOtherinfo')">其他信息  </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPurchaseUrl')">进货信息  </a></li>
		</ul>
	</div>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" action='/wms-web/prod/saveTab'>
			<input id="tabName" name="tabName" type="hidden" value ="addPrice"/>
			<table id="myTable" class="table table-striped">
				<caption>
					<input type="button" id="btnAdd" class="btn btn-primary" value="保存"  onclick="submitFormAndGoTo()" />
					<input type="button" id="btnAdd" class="btn btn-primary" value="加载UPC"  onclick="loadUPCAll()" />
					<input type="checkbox" id="synchronizeBox" >同步更新后续子产品</input>
				</caption>
				<thead>
					<tr>
						<th width="10%">SKU</th> 
						<th width="5%">缩略图</th> 
						<th width="12%">UPC</th> 
						<th width="5%">售价(USD)</th>
						<th width="5%">原价(USD)</th> 
						<th width="5%">折扣率</th> 
						<th width="5%">库存数量</th> 
						<th width="8%">实际库存数量</th>
						<th width="8%">进货价格(RMB)</th>
						<th width="8%">运费收入(USD)</th>
						<th width="8%">亚马逊收费(USD)</th>
						<th width="12%">利润(-运费  <%=com.owen.wms.common.constant.AppConstant.ShippingFeePay%> RMB)</th>
						
					</tr>
				</thead>
				<tbody id="tbody" align="left">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
							<tr>
								<input type="hidden" id="list${status.index}.itemSku" name='list[${status.index}].itemSku'  value="${prod.itemSku}"/>
								<td width="8%"><b>${prod.itemSku}</b></td>
								<td width="5%">
										<c:if test="${fn:indexOf(prod.mainImageUrl,'pfhoo.com')>-1 }">
											<img src="${ prod.mainImageUrl}"  height="35" onclick='window.open("${ prod.mainImageUrl}")'/>
										</c:if>
										<c:if test="${fn:indexOf(prod.mainImageUrl,'pfhoo.com') == -1 }">
											<c:if test="${ prod.getLocalImagePath() !=null }">
												<img src="/wms-web/img${prod.getLocalImagePath()}"  height="35" onclick='window.open("/wms-web/img${prod.getLocalImagePath()}")'/> 
											</c:if>
										</c:if>
								</td>
								<td width="12%">
									<nobr>
										<input type="button"   value="clean"  onclick="cleanUPC('${prod.itemSku}','list${status.index}.externalProductId')" />
										<input id="list${status.index}.externalProductId" name='list[${status.index}].externalProductId' type="text"  style="width:50%" type='text' value="${prod.externalProductId}" readonly="true"/>
										<input type="button"  value="load"  onclick="loadUPC()" />
									</nobr>
								</td>
								<td width="5%">
									<input id="list${status.index}.standardPrice" name='list[${status.index}].standardPrice' type="text"  style="width:100%" type='text' value="${prod.standardPrice}" />
								</td>
								<!-- shippingFee and usdRate is defined in Header.jsp -->
								<td width="5%">
									<input id="list${status.index}.listPrice" name='list[${status.index}].listPrice' type="text"  style="width:100%" type='text' value="${prod.listPrice}" />
								</td>
								<td width="5%">
									<c:if test="${prod.standardPrice != null && prod.listPrice != null && prod.listPrice != 0}">
										<input disabled="true" type="text"  style="width:100%" type='text' value='<fmt:parseNumber value="${(100*prod.standardPrice/prod.listPrice)}" integerOnly="true"/>%' />
									</c:if>
								</td>
								<td width="5%">
									<input id="list${status.index}.quantity" name='list[${status.index}].quantity' type="text"  style="width:100%" type='text' value="${prod.quantity==nul?30:prod.quantity}" />
								</td>
								<td width="10%">
									<b><input id="list${status.index}.stockQuantity" name='list[${status.index}].stockQuantity' type="text"  style="width:100%" type='text' value="${prod.stockQuantity}" /></b>
								</td>
								<td width="10%">
									<b><input id="list${status.index}.purchasePrice" name='list[${status.index}].purchasePrice' type="text"  style="width:100%" type='text' value="${prod.purchasePrice}" /></b>
								</td>
								<td width="8%">+${ shippingFee }</td>
								<td width="8%">-${ prod.getAmazonFee() }</td>
								<td width="8%"><b>${ prod.getProfit() }</b></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</form:form>
	</section>
</body>
</html>