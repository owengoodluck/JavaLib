<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
$(document).ready(function(){
	//setNameOfEnglish();
	//$('#nameChinese').change(function(){
		//setNameOfEnglish();
	//});
});

function setNameOfEnglish(){
	var nameCh = $('#nameChinese').val();
	var nameEn = '';
	if ( nameCh == '不锈钢首饰-吊坠'){
		nameEn = 'Stainless Steel Accessories Necklace Pendant';
	}else if ( nameCh == '不锈钢首饰-手链'){
		nameEn = 'Stainless Steel Accessories Bracelet';
	}else if ( nameCh == '不锈钢首饰-指环'){
		nameEn = 'Stainless Steel Accessories Rings';
	}else if ( nameCh == '时尚首饰-吊坠'){
		nameEn = 'Fashion Accessories Necklace Pendant';
	}else if ( nameCh == '时尚首饰-手链'){
		nameEn = 'Fashion Accessories Bracelet';
	}else if ( nameCh == '时尚首饰-指环'){
		nameEn = 'Fashion Accessories Rings';
	}else if ( nameCh == '时尚首饰-耳环'){
		nameEn = 'Fashion Accessories Earrings';
	}else if ( nameCh == '时尚服饰-连衣裙'){
		nameEn = 'Fashion Clothes Dress';
	}
	$('#nameEnglish').val(nameEn);
}

function submitForm(){
	var amazonOrderID = $('#amazonOrderID').val();
	var nameChinese = $('#nameChinese').val();
	var channel = $('#channel').val();
	
	if(amazonOrderID == null || amazonOrderID.length == 0){
		alert('亚马逊订单号不能为空');
		$('#amazonOrderID').focus();
		return;
	}
	if(channel == null || channel.length == 0){
		alert('请选择发货渠道');
		$('#channel').focus();
		return;
	}
	if(nameChinese == null || nameChinese.length == 0){
		alert('请选择中文品名');
		$('#nameChinese').focus();
		return;
	}
	$('#express').submit();
}
</script>
<title>创建快递单</title>
</head>
<body>
	
	<section class="container">
	<legend align="left">创建快递单</legend>
		<c:if test='${ fn:indexOf(createSuccessIndicator, "快递单创建成功")  > 0 }'>
			<span class="label label-success">${createSuccessIndicator}</span>
		</c:if>
		<c:if test='${ fn:indexOf(createSuccessIndicator, "快递单创建成功")  < 0 }'>
			<span class="label label-danger">${createSuccessIndicator}</span>
		</c:if>
		<table border="1">
			<thead>
				<tr>
					<th>itemSku</th> <th>sizeName</th>  <th>colorName</th> <th>image</th>
				</tr>
			</thead>
			<c:forEach items="${express.amazonOrder.orderItemList}" var="item" >
				<tr>
					<td>${item.sellerSKU.itemSku}</td>
					<td>${item.sellerSKU.sizeName}</td>
					<td>${item.sellerSKU.colorName}</td>
					<td><img src="/wms-web/img${item.getSellerSKU().getLocalImagePath()}"  height="60" onclick='window.open("/wms-web/img${item.getSellerSKU().getLocalImagePath()}")'></td>
				</tr>
			</c:forEach>
		</table>
		<form:form modelAttribute="express" enctype="multipart/form-data" >
			<table  border="1"  cellspacing="10">
				<tr>
					<td>亚马逊订单号</td> 
					<td width="90%"> 
						<form:input path="amazonOrderID" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>序列号（可选）</td> 
					<td width="90%"> 
						<form:input path="sequenceNo" type="text" style="width:100%"/>
					</td>
				</tr>
				
				<tr>
					<td>发货方式</td> 
					<td> 
						<form:select path="channel">
								<form:option value="中邮广州E邮宝(线下)">中邮广州E邮宝(线下)</form:option>
								<form:option value="中邮广州挂号小包">中邮广州挂号小包</form:option>
								<form:option value="中邮北京E邮宝(线下)">中邮北京E邮宝(线下)</form:option>
								<form:option value="中邮北京平邮小包">中邮北京平邮小包</form:option>
								<form:option value="中邮上海E邮宝(线下)">中邮上海E邮宝(线下)</form:option>
								<form:option value="中邮北京挂号小包">中邮北京挂号小包</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>发货日期</td> 
					<td> 
						<form:input path="sendDate" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>中文品名</td> 
					<td> 
						<form:input path="nameChinese" type="text" style="width:100%"/>
						<%-- <form:select path="nameChinese">
								<form:option value=""></form:option>
								<form:option value="不锈钢首饰-吊坠">不锈钢首饰-吊坠</form:option>
								<form:option value="不锈钢首饰-手链">不锈钢首饰-手链</form:option>
								<form:option value="不锈钢首饰-指环">不锈钢首饰-指环</form:option>
								<form:option value="时尚首饰-吊坠">时尚首饰-吊坠</form:option>
								<form:option value="时尚首饰-手链">时尚首饰-手链</form:option>
								<form:option value="时尚首饰-指环">时尚首饰-指环</form:option>
								<form:option value="时尚首饰-耳环">时尚首饰-耳环</form:option>
								<form:option value="时尚服饰-连衣裙">时尚服饰-连衣裙</form:option>
						</form:select> --%>
					</td>
				</tr>
				<tr>
					<td>英文品名</td> 
					<td> 
						<form:input path="nameEnglish" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>货品数量</td> 
					<td> 
						<form:input path="quantity" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td> 总重量(g)</td> 
					<td> 
						<form:input path="weight" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>货币种类</td> 
					<td> 
						<form:input path="declaredCurrency" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<c:if test="${express.declaredValue<=20 }">
						<td> 
							申报价值
						</td> 
						<td> 
							<form:input path="declaredValue" type="text" style="width:100%;" />
						</td>
					</c:if>
					<c:if test="${express.declaredValue>20 }">
						<td> 
							<span class="label label-danger">申报价值>20</span>
						</td> 
						<td> 
							<form:input path="declaredValue" type="text" style="width:100%;color:#FF0000;" />
						</td>
					</c:if>
				</tr>
				<tr>
					<td>收件人姓名</td> 
					<td> 
						<form:input path="receiver.name" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>收件人电话</td> 
					<td> 
						<form:input path="receiver.phone" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>国家</td> 
					<td> 
						<form:input path="receiver.country" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>州</td> 
					<td> 
						<form:input path="receiver.state" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>城市</td> 
					<td> 
						<form:input path="receiver.city" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>收件人地址1</td> 
					<td> 
						<form:input path="receiver.address1" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>收件人地址2</td> 
					<td> 
						<form:input path="receiver.address2" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td>邮编</td> 
					<td> 
						<form:input path="receiver.postcode" type="text" style="width:100%"/>
					</td>
				</tr>
				<%-- <tr>
					<td>订单信息获取方式</td> 
					<td> 
						<form:select path="methodToGetOrder">
								<form:option value="localDB" >从本地同步数据库读取</form:option>
								<form:option value="remoteAmz">从亚马逊网站读取</form:option>
						</form:select>
					</td>
				</tr> --%>
				<tr>
					<td>快递单保存地址</td> 
					<td width="90%"> 
						<form:input path="downloadPath" type="text" style="width:100%"/>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="right"> 
						<c:if test="">
							
						</c:if>
						<input type="button" id="btnAdd" class="btn btn-primary" value="创建快递单"  onclick="submitForm()" />
					</td>
				</tr>
			</table>
		</form:form>
	</section>
</body>
</html>