<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="root" value="${basePath}"/>
<link rel="stylesheet" type="text/css" href="${root}/jslib/bootstrap3/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${root}/jslib/bootstrap3/css/bootstrap-theme.min.css"/>
<script type="text/javascript" src="${root}/jslib/bootstrap3/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${root}/jslib/jquery/jquery-1.12.2.min.js"></script>