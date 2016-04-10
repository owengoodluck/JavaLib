<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!Doctype html>
<html>
<head>
<link rel="stylesheet"
	href='<c:url value="/resource/css/bootstrap.min.css" />'
	type="text/css"
/>
<script src='<c:url value="/resource/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resource/js/bootstrap.min.js"/>'></script>
<script src='<c:url value="/resource/js/owen.js"/>'></script>

<script type="text/javascript">
$(document).ready(function(){
	$('li.dropdown').mouseover(function() {
	     $(this).addClass('open');    
	});
	$('li.dropdown').mouseout(function() {        
		$(this).removeClass('open');    
	}); 
})

function openUrl(str){
	 window.location.assign(str)
}
</script>
</head>
<c:set var="shippingFee" scope="session" value="4.85"></c:set>
<c:set var="USDRate" scope="session" value="6.4"></c:set>
<nav class="navbar navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
  
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
    </div>
    
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li <c:if test="${currentMenu == 'order' }">class="dropdown active"</c:if> 
        	<c:if test="${currentMenu != 'order' }">class="dropdown"</c:if>>
          <a class="dropdown-toggle" data-toggle="dropdown" href="<spring:url value="/order/list" />" onclick="openUrl('<c:url value="/order/list" />')">订单管理<span class="caret"></span></a>
          <ul class="dropdown-menu" >
            <li><a href="<spring:url value="/order/list" />">所有订单</a> </li>
            <li><a href="<spring:url value="/order/synchronzieOrders" />">订单同步</a> </li>
            <li><a href="<spring:url value="/order/statistics" />">销售统计</a> </li>
          </ul>
        </li>
        
        <li <c:if test="${currentMenu == 'prod' }">class="dropdown active"</c:if> 
        	<c:if test="${currentMenu != 'prod' }">class="dropdown"</c:if>>
          <a class="dropdown-toggle" data-toggle="dropdown" href="<spring:url value="/prod/listAll" />" onclick="openUrl('<c:url value="/prod/listAll" />')">产品管理 <span class="caret"></span></a>
          <ul class="dropdown-menu" >
            <li><a href="<spring:url value="/prod/listAll" />">所有产品</a> </li>
            <li><a href="<spring:url value="/prod/addTitle" />">添加产品</a> </li>
            <li><a href="<spring:url value="/prod/loadUPCFile2DB" />">加载UPC</a> </li>
          </ul>
        </li>
        
        <li <c:if test="${currentMenu == 'express' }">class="dropdown active"</c:if> 
        	<c:if test="${currentMenu != 'express' }">class="dropdown"</c:if>>
          <a class="dropdown-toggle" data-toggle="dropdown" href="<spring:url value="/yanwen/list" />" onclick="openUrl('<c:url value="/yanwen/list" />')">快递管理 <span class="caret"></span></a>
          <ul class="dropdown-menu" >
            <li><a href="<spring:url value="/yanwen/list" />">所有快递</a> </li>
            <li><a href="<spring:url value="/yanwen/create" />">创建新快递</a> </li>
          </ul>
        </li>
        
        <li <c:if test="${currentMenu == 'lekani' }">class="dropdown active"</c:if> 
        	<c:if test="${currentMenu != 'lekani' }">class="dropdown"</c:if>>
          <a class="dropdown-toggle" data-toggle="dropdown" href="<spring:url value="/lekani/pageQueryLocal" />" onclick="openUrl('<c:url value="/lekani/pageQueryLocal" />')">Lekani<span class="caret"></span></a>
          <ul class="dropdown-menu" >
            <li><a href="<spring:url value="/lekani/pageQueryLocal" />" />Lekani已导入</a> </li>
            <li><a href="<spring:url value="/lekani/importByID" />" />Lekani单个导入</a> </li>
            <li><a href="<spring:url value="/lekani/pageQuery" />">Lekani官网</a> </li>
          </ul>
        </li>
        
        <li <c:if test="${currentMenu == 'pic' }">class="active"</c:if> ><a href="<spring:url value="/picture/download" />">图片管理</a> </li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </ul>
    </div>
    
    
  </div>
</nav>