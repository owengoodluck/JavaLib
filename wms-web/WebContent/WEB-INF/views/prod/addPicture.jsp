<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!Doctype html>
<html>
<link rel="stylesheet"
	href='<c:url value="/resource/css/bootstrap.min.css" />'
	type="text/css"
/>
<script src='<c:url value="/resource/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resource/js/bootstrap.min.js"/>'></script>
<script src='<c:url value="/resource/js/owen.js"/>'></script>
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
function oppenPicLink(picButton,surfix){
	var id = picButton.id;//pictureButton1
	var imgID='list'+id.substr(13,1)+'.'+surfix;
	var url = $( document.getElementById(imgID) ).val();
	if(url != null && url.length > 0){
		window.open(url)
	}
}
</script>
<title>AddPordotherImageUrl</title>
</head>
<body>
	<div>
		<ul class="nav nav-tabs">
		  <li ><a href="#" onclick="submitFormAndGoTo('addTitle')">产品基本信息 </a></li>
		  <li class="active"><a href="#">产品图片 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addBulletPoint')">产品特性描述 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addKeyword')">搜索关键字 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPrice')">价格和库存 </a></li>
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
					<input type="checkbox" id="synchronizeBox" >同步更新后续子产品</input>
				</caption>
				<thead>
					<tr>
						<th>SKU</th> 
						<th>缩略图</th> 
						<th>产品图片上传地址</th>
					</tr>
				</thead>
				<tbody id="tbody" align="left">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
							<tr>
								<input type="hidden" id="list${status.index}.itemSku" name='list[${status.index}].itemSku'  value="${prod.itemSku}"/>
								<td width="6%"><b>${prod.itemSku}</b></td>
								<td width="5%">
									<c:if test="${ prod.getLocalImagePath() !=null }">
										<img src="/wms-web/img${prod.getLocalImagePath()}"  height="100"  onclick='window.open("/wms-web/img${prod.getLocalImagePath()}")'> 
									</c:if>
								</td>
								<td width="51%">
									<nobr>
										<c:if test="${ prod.mainImageUrl !=null }">
											<img src="${prod.mainImageUrl}"  height="100"  onclick='window.open("${prod.mainImageUrl}")'>
										</c:if>
										<b>0</b><input id="list${status.index}.mainImageUrl" name='list[${status.index}].mainImageUrl' type="text"  style="width:100%" type='text' value="${prod.mainImageUrl}" />
									</nobr>
									<nobr>
										<c:if test="${ prod.mainImageUrl !=null }">
											<img src="${prod.otherImageUrl1}"  height="100"  onclick='window.open("${prod.otherImageUrl1}")'>
										</c:if>
										<b>1</b><input id="list${status.index}.otherImageUrl1" name='list[${status.index}].otherImageUrl1' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl1}" />
									</nobr>
									<nobr>
										<c:if test="${ prod.otherImageUrl2 !=null }">
											<img src="${prod.otherImageUrl2}"  height="100"  onclick='window.open("${prod.otherImageUrl2}")'>
										</c:if>
										<b>2</b><input id="list${status.index}.otherImageUrl2" name='list[${status.index}].otherImageUrl2' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl2}" />
									</nobr>
									<nobr>
										<c:if test="${ prod.otherImageUrl3 !=null }">
											<img src="${prod.otherImageUrl3}"  height="100"  onclick='window.open("${prod.otherImageUrl3}")'>
										</c:if>
										<b>3</b><input id="list${status.index}.otherImageUrl3" name='list[${status.index}].otherImageUrl3' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl3}" />
									</nobr>
									<nobr>
										<c:if test="${ prod.otherImageUrl4 !=null }">
											<img src="${prod.otherImageUrl4}"  height="100"  onclick='window.open("${prod.otherImageUrl4}")'>
										</c:if>
										<b>4</b><input id="list${status.index}.otherImageUrl4" name='list[${status.index}].otherImageUrl4' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl4}" />
									</nobr>
									<nobr>
										<c:if test="${ prod.otherImageUrl5 !=null }">
											<img src="${prod.otherImageUrl5}"  height="100"  onclick='window.open("${prod.otherImageUrl5}")'>
										</c:if>
										<b>5</b><input id="list${status.index}.otherImageUrl5" name='list[${status.index}].otherImageUrl5' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl5}" />
									</nobr>
									<nobr>
										<c:if test="${ prod.otherImageUrl6 !=null }">
											<img src="${prod.otherImageUrl6}"  height="100"  onclick='window.open("${prod.otherImageUrl6}")'>
										</c:if>
										<b>6</b><input id="list${status.index}.otherImageUrl6" name='list[${status.index}].otherImageUrl6' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl6}" />
									</nobr>
									<nobr>
										<c:if test="${ prod.otherImageUrl7 !=null }">
											<img src="${prod.otherImageUrl7}"  height="100"  onclick='window.open("${prod.otherImageUrl7}")'>
										</c:if>
										<b>7</b><input id="list${status.index}.otherImageUrl7" name='list[${status.index}].otherImageUrl7' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl7}" />
									</nobr>
									<nobr>
										<c:if test="${ prod.otherImageUrl8 !=null }">
											<img src="${prod.otherImageUrl8}"  height="100"  onclick='window.open("${prod.otherImageUrl8}")'>
										</c:if>
										<b>8</b><input id="list${status.index}.otherImageUrl8" name='list[${status.index}].otherImageUrl8' type="text"  style="width:100%" type='text' value="${prod.otherImageUrl8}" />
									</nobr>
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</form:form>
	</section>
</body>
</html>