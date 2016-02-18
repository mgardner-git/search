/**
 * A Directive to show a table containing the results of a search.
 */
app.directive("results",function(){
	return{ 
		restrict: "E",
		templateUrl:"resources/js/directives/templates/results.html",
		scope:{			
			searchRequest : "=",
			searchResponse: "=",			
		},
		link: function($scope, element, attrs){
		} ,
		controller: function($scope){

			$scope.formatResults = function (predicate,result){
				if (predicate.type == "DATE"){
					var time = parseInt(result);
					var date = new Date(time);
					var formattedDate = date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
					formattedDate = formattedDate  + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
					return formattedDate;
				}else{
					return result;
				}
			}
		}
	};
});