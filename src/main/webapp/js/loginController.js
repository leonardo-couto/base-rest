(function() {
	// TODO: declaracao do modulo, deve ser feita uma unica vez, colocar em um arquivo separado
	var auth = angular.module("auth", []);
	
	auth.controller("LoginController", ["$scope", function LoginController($scope) {
		
		var domainURL = function() {
			var host = document.location.host;
			return "https://" + host + "/";
		};
		
		var forwardURL = function(args) {
			var i = args.indexOf("next=");
			var next = args.slice(i+5);
			if (next.indexOf("&") > -1) {
				next = next.slice(0, next.indexOf("&"));
			}
			return decodeURIComponent(next);
		};
		
		var redirectURL = function() {
			var args = document.location.search;
			if (args == null || args.length === 0)  {
				return domainURL();
			}
			return forwardURL(args);
		}
		
		$scope.redirect = redirectURL();	
	}]);
	
})();

