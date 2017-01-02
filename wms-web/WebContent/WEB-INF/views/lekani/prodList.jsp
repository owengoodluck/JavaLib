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
	$('#queryForm').submit();
}
function loadProdByCategoryAndBrand(){
	$('#currentPage').val(1);
	$("#queryForm").attr("action", "/wms-web/lekani/loadProdByCategoryAndBrand");
	$('#queryForm').submit();
}
function cleanForm(){
	$('#prodID').val(null);
	$('#orderID').val(null);
	$('#receiver').val(null);
	$('#sendDateFrom').val(null);
	$('#sendDateTo').val(null);
	$('#channel').val(null);
}

</script>
<title>Lekani官网产品列表</title>
</head>
<body>
	<div>
		<ol class="breadcrumb" align="left">
		  <!-- <li><a href="#">Home</a></li> -->
		  <li class="active">Lekani官网产品列表</li>
		</ol>
	</div>
	
	<div class="container-fluid">
	   <div class="row">
	      	<form:form modelAttribute="queryForm" enctype="multipart/form-data" action="/wms-web/lekani/pageQuery">
		      	<div align="left">
			      	分类类别:
			      	<form:select path="categoryID" onchange="submitForm(0)">
			      		<form:option value="0">请选择</form:option>
						<form:option value="216">项链216</form:option>
						<form:option value="207">手链207</form:option>
						<form:option value="217">吊坠217</form:option>
			      		<form:option value="201">戒指201</form:option>
						<form:option value="104">LEKANI 纯银首饰104</form:option>
						<form:option value="218">长款项链218</form:option>
						<form:option value="205">项链&吊坠205</form:option>
						<form:option value="219">链条配链219</form:option>
						<form:option value="210">耳钉210</form:option>
						<form:option value="225">钥匙扣225</form:option>
						<form:option value="226">钱夹226</form:option>
						<form:option value="242">手拿包242</form:option>
						<form:option value="243">单肩包243</form:option>
						<form:option value="245">太阳镜245</form:option>
						<form:option value="247">偏光镜247</form:option>
						<form:option value="248">眼镜盒248</form:option> 
						<%-- 
						--%>
						<form:option value="211">耳圈211</form:option>
						<form:option value="202">耳饰202</form:option>
						<form:option value="212">耳钩&耳坠212</form:option>
						<form:option value="213">耳夹&耳扣213</form:option>
						<form:option value="208">手镯208</form:option>
						<form:option value="220">脚链220</form:option>
						<form:option value="215">耳饰花托215</form:option>
						<form:option value="204">饰品套装204</form:option>
						<form:option value="222">胸针222</form:option>
						<form:option value="244">时尚眼镜244</form:option>
						<%-- 
						<form:option value="241">时尚包包241</form:option> --%>
						<form:option value="221">领带夹&袖扣221</form:option>
						<form:option value="224">钥匙扣&钱夹224</form:option>
						<%-- <form:option value="223">头饰、发饰223</form:option>
						<form:option value="54">包装&展件54</form:option> --%>
			      	</form:select>
			      	品牌列表:
			      	<form:select path="brandID" onchange="submitForm(0)">
			      		<form:option value="0">请选择</form:option>
			      		<form:option value="29">潘多拉系列29</form:option>
						<form:option value="37">钛钢系列37</form:option>
						<form:option value="27">婚饰系列27</form:option>
						<form:option value="13">古玛雅13</form:option>
						<form:option value="17">香芭拉17</form:option>
						<form:option value="21">时尚K金产品21</form:option>
						<form:option value="21">珍姿美21</form:option>
						<form:option value="28">民族风系列28</form:option>
						<form:option value="28">夸张大牌产品28</form:option>
						<form:option value="28">依娜丽饰28</form:option>
						<form:option value="28">威妮华28</form:option>
						<form:option value="28">天然石系列28</form:option>
						<form:option value="28">品牌A28</form:option>
						<form:option value="10">又一银10</form:option>
						<form:option value="20">K金锆石类20</form:option>
						<form:option value="31">爆款品类31</form:option>
						<form:option value="30">韩风30</form:option>
						<form:option value="14">法伯丽14</form:option>
						<form:option value="22">时尚银饰产品22</form:option>
						<form:option value="19">品牌B19</form:option>
						<form:option value="19">眼镜系列19</form:option>
						<form:option value="99">其它99</form:option>
			      	</form:select>
					<input type="submit" id="btnAdd" class="btn btn-primary" value="查询" onclick="submitForm(0)"/>
					<input type="submit" id="btnAdd" class="btn btn-primary" value="下载" onclick="loadProdByCategoryAndBrand()"/>
					每页显示：<form:input path="pageSize" size="2"/>
					总页数 <b>${page.totalPage }</b>： 总条数<b>${page.totalCount }</b>
		      		<input type="button" value="上一页" class="btn btn-primary" <c:if test='${!page.hasPrePage }'>disabled="disabled"</c:if> onclick="submitForm(-1)"/>
					当前页：<form:input path="currentPage" size="2"/>
					<input type="button" value="下一页" class="btn btn-primary" <c:if test='${!page.hasNextPage }'>disabled="disabled"</c:if> onclick="submitForm(1)"/>
		      	</div>
			</form:form>
	   </div>
	   
	<section class="container-fluid ">
		<table class="table table-hover" >
			<thead>
				<tr >
					<th>序号</th>
					<th>图片</th>
					<th>ID</th>
					<th>官网链接</th>
					<th>分类</th>
					<th>品牌</th>
					<th>售价</th>
					<th>名称</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="prod" varStatus="status">
					<tr align="left">
						<td>${status.index+1}</td>
						<td>
							<c:if test="${ prod.mainImage !=null }">
								<img src="${prod.mainImage}"  height="100"  onclick='window.open("${prod.mainImage}")'> 
							</c:if>
						</td>
						<td>${prod.productID}</td>
						<td>${prod.catName}</td>
						<td>${prod.brandName}</td>
						<td>${prod.price}</td>	
						<td>${prod.name}</td>
						<%-- <td>${prod.description}</td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</section>
</body>
</html>