<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!Doctype html>
<html>
<head>

<script type="text/javascript">
$(function() {
    $( "#startDateStr" ).datepicker({"dateFormat":"yy-mm"});
    $( "#endDateStr" ).datepicker({"dateFormat":"yy-mm"});
});
    
function submitForm(){
	$('#expressStaticForm').submit();
}

</script>
<title>快递统计</title>
</head>
<body>
	<div class="container">
		<div align="left">
			<ol class="breadcrumb" >
			  <li class="active">快递统计</li>
			</ol>
		</div>
		<div align="left">
		
	  	<form:form modelAttribute="expressStaticForm" enctype="multipart/form-data" action="/wms-web/express/expressStatic">
				<br/>
				
				从<form:input path="startDateStr" size="10"/>
				到<form:input path="endDateStr" size="10"/>
				
				<input type="button" id="btnAdd" class="btn btn-primary" value="统计"  onclick="submitForm()" />
		</form:form>
		</div>
		
		<div>
			<c:if test="${statisResult!=null}">
				<table class="table table-hover">
					<c:forEach items="${statisResult}" var="entry"> 
						<tr>
							<td><c:out value="${entry.key}"/></td> 
							<td><c:out value="${entry.value}"/> </td>
						</tr>
					</c:forEach> 
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>