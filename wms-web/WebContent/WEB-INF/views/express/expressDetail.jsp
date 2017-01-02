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
<title>快递单详情</title>
</head>
<body>
	<section class="container">
	<legend align="left">快递单详情</legend>
			<table  border="1"  cellspacing="10">
				<tr>
					<td>快递单号</td> 
					<td width="90%"> 
						${expressEntity.epcode } 
						<a href="https://tools.usps.com/go/TrackConfirmAction?qtc_tLabels1=${expressEntity.epcode }" target="_blank">查看跟踪信息</a>
					</td>
				</tr>
				<tr>
					<td>亚马逊订单号</td> 
					<td width="90%"> 
						${expressEntity.userOrderNumber }
					</td>
				</tr>
				
				<tr>
					<td>发货方式</td> 
					<td> 
						${expressEntity.channel }
					</td>
				</tr>
				<tr>
					<td>发货日期</td> 
					<td> 
						${expressEntity.sendDate }
					</td>
				</tr>
				<tr>
					<td>中文品名</td> 
					<td> 
						${expressEntity.nameCh }
					</td>
				</tr>
				<tr>
					<td>英文品名</td> 
					<td> 
						${expressEntity.nameEn }
					</td>
				</tr>
				<tr>
					<td> 总重量(g)</td> 
					<td> 
						${expressEntity.weight }
					</td>
				</tr>
				<tr>
					<td>货币种类</td> 
					<td> 
						${expressEntity.declaredCurrency }
					</td>
				</tr>
				<tr>
					<td>申报价值</td> 
					<td> 
						${expressEntity.declaredValue }
					</td>
				</tr>
				<tr>
					<td>收件人姓名</td> 
					<td> 
						${expressEntity.name }
					</td>
				</tr>
				<tr>
					<td>收件人电话</td> 
					<td> 
						${expressEntity.phone }
					</td>
				</tr>
				<tr>
					<td>国家</td> 
					<td> 
						${expressEntity.country }
					</td>
				</tr>
				<tr>
					<td>州</td> 
					<td> 
						${expressEntity.state }
					</td>
				</tr>
				<tr>
					<td>城市</td> 
					<td> 
						${expressEntity.city }
					</td>
				</tr>
				<tr>
					<td>收件人地址1</td> 
					<td> 
						${expressEntity.address1 }
					</td>
				</tr>
				<tr>
					<td>收件人地址2</td> 
					<td> 
						${expressEntity.address2 }
					</td>
				</tr>
				<tr>
					<td>邮编</td> 
					<td> 
						${expressEntity.postcode }
					</td>
				</tr>
			
			</table>
	</section>
</body>
</html>