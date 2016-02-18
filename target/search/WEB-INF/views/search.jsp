<%@page import="model.*" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="header.jsp"/>

<script type="text/javascript">
	
	var app = angular.module('allegiantApp');
	app.controller('searchController', function($scope, $http, $timeout) {
		$scope.searchResponse = {};
		$scope.pageNumber = 1;
		$scope.searchRequest = {
				pageNumber: 1,
				itemsPerPage: 20,
				sorts: [],
				predicates:[
		
				{
					name: "longitude",
					label: "Longitude",
					type: "NUMBER"
				},
				{
					name: "firstName",
					label: "First Name",
					type: "STRING"
				},
				{
					name: "lastName",
					label : "Last Name",
					type: "STRING"
				},
				{
					name: "email",
					label : "Email",
					type: "STRING"
				}
				]
		};
	});
</script>

<div ng-controller="searchController">

	<p><search-wrapper search-request="searchRequest" search-response="searchResponse" search-url="rest/customer/search"></search-wrapper></p>
</div>

<jsp:include page="footer.jsp"/>