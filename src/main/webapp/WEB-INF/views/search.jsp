<%@page import="model.*" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp"/>

<script type="text/javascript">
	
	var app = angular.module('allegiantApp');
	app.controller('searchController', function($scope, $http, $timeout) {
		$scope.customers = [];
		$scope.pageNumber = 1;
		$scope.predicateConfig = [
			{
				name: "createDateTime",
				type: "date"
			},
			{
				name: "firstName",
				type: "string"
			},
			{
				name: "lastName",
				type: "string"
			},
			{
				name: "emailAddress",
				type: "string"
			}
		]
	});
</script>

<div ng-controller="searchController">
	<p><search-wrapper predicate-config="predicateConfig" search-results="customers" page-number="pageNumber" search-url="rest/customers/search"></search-wrapper></p>
</div>

<jsp:include page="footer.jsp"/>