(function() {
	var auth = angular.module("auth");
	
	auth.controller("SignUpController", ["$scope", "$http", function SignUpController($scope, $http) {
		
		$scope.submit = function(user) {
			var userRequest = {
					"key" : user.email,
					"password" : user.password
			};
			
			$http.post('/auth/user', userRequest).success(function(data) {
				console.log(data);
			});
		};
		
	}]);
	
})();