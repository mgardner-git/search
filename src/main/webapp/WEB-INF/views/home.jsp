<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@page import="model.*" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp"/>

<script type="text/javascript">
	
	var app = angular.module('allegiantApp');
	app.controller('homeController', function($scope, $http, $timeout) {
		
	});
</script>

<div ng-controller="homeController">
hello world
</div>

<jsp:include page="footer.jsp"/>
