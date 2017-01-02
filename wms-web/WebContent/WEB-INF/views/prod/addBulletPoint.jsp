<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
</script>
<title>AddPordBulletPoint</title>
</head>
<body>
	<div>
		<ul class="nav nav-tabs">
		  <li ><a href="#" onclick="submitFormAndGoTo('addTitle')">产品基本信息 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPicture')">产品图片 </a></li>
		  <li class="active"><a href="#">产品特性描述 </a> </li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addKeyword')">搜索关键字 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPrice')">价格和库存 </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addOtherinfo')">其他信息  </a></li>
		  <li ><a href="#" onclick="submitFormAndGoTo('addPurchaseUrl')">进货信息  </a></li>
		</ul>
	</div>
	
	<section class="container-fluid">
		<form:form modelAttribute="productsForm" enctype="multipart/form-data" action='/wms-web/prod/saveTab'>
			<input id="tabName" name="tabName" type="hidden" value ="addBulletPoint"/>
			<table id="myTable" class="table table-striped">
				<caption>
					<input type="button" id="btnAdd" class="btn btn-primary" value="保存"  onclick="submitFormAndGoTo()" />
					<input type="checkbox" id="synchronizeBox" >同步更新后续子产品</input>
				</caption>
				<thead>
					<tr>
						<th>SKU</th> 
						<th>缩略图</th> 
						<th>特性描述(1-5)</th>
					</tr>
				</thead>
				<tbody id="tbody" align="left">
					<c:if test="${productsForm.list != null }">
						<c:forEach items="${productsForm.list}" var="prod" varStatus="status">
							<tr>
								<input type="hidden" id="list${status.index}.itemSku" name='list[${status.index}].itemSku'  value="${prod.itemSku}"/>
								<td width="6%"><b>${prod.itemSku}</b></td>
								<td width="5%">
										<c:if test="${fn:indexOf(prod.mainImageUrl,'pfhoo.com')>-1 }">
											<img src="${ prod.mainImageUrl}"  height="100" onclick='window.open("${ prod.mainImageUrl}")'/>
										</c:if>
										<c:if test="${fn:indexOf(prod.mainImageUrl,'pfhoo.com') == -1 }">
											<c:if test="${ prod.getLocalImagePath() !=null }">
												<img src="/wms-web/img${prod.getLocalImagePath()}"  height="100" onclick='window.open("/wms-web/img${prod.getLocalImagePath()}")'/> 
											</c:if>
										</c:if>
								</td>
								<td width="51%">
									<input id="list${status.index}.bulletPoint1" name='list[${status.index}].bulletPoint1' type="text"  style="width:100%" type='text' value="${prod.bulletPoint1}" />
									<input id="list${status.index}.bulletPoint2" name='list[${status.index}].bulletPoint2' type="text"  style="width:100%" type='text' value="${prod.bulletPoint2}" />
									<input id="list${status.index}.bulletPoint3" name='list[${status.index}].bulletPoint3' type="text"  style="width:100%" type='text' value="${prod.bulletPoint3}" />
									<input id="list${status.index}.bulletPoint4" name='list[${status.index}].bulletPoint4' type="text"  style="width:100%" type='text' value="${prod.bulletPoint4}" />
									<input id="list${status.index}.bulletPoint5" name='list[${status.index}].bulletPoint5' type="text"  style="width:100%" type='text' value="${prod.bulletPoint5}" />
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