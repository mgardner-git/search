<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="/search/resources/js/angular.js"></script>
<script type="text/javascript" src="/search/resources/js/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="/search/resources/js/jquery-ui-1.11.4.custom/jquery-ui.min.css"/>
<link rel="stylesheet" type="text/css" href="/search/resources/style/search.css"/>
<link rel="stylesheet" type="text/css" href="/search/resources/js/jquery-ui-timepicker-addon.css"/>
<script type="text/javascript" src="/search/resources/js/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
<script type="text/javascript" src="/search/resources/js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="/search/resources/js/search.js"></script>
<script type="text/javascript" src="/search/resources/js/directives/date-time.js"></script>
<script type="text/javascript" src="/search/resources/js/directives/numeric.js"></script>
<script type="text/javascript" src="/search/resources/js/directives/searchWrapper.js"></script>
<script type="text/javascript" src="/search/resources/js/directives/predicate.js"></script>
<script type="text/javascript" src="/search/resources/js/directives/results.js"></script>
<script type="text/javascript" src="/search/resources/js/directives/searchInput.js"></script>
<script type="text/javascript" src="/search/resources/js/directives/pagination.js"></script>
<title>Monte Gardner - G4 UX Challenge</title>
<meta name="description" content="Monte Gardner's Allegiant Challenge">
<meta name="viewport" content="width=device-width, initial-scale=1">


</head>
<body ng-app="allegiantApp" ng-controller="allegiantController">
<div id="wrap">
	<a href="/search">
		<header>
			
		</header>
	</a>
	<jsp:include page="topNav.jsp"/>
    <div id="leftColumn">
    	<jsp:include page="nav.jsp"/>
    </div>
    <div id="content">
    
    
    
    