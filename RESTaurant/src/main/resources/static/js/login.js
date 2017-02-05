(function() {
	var app = angular.module('login', []);
	
	app.controller('AuthorizationController', ['$http', '$window', function($http, $window) {
		
		this.authorize = function() {
			
			$http({
				method: 'POST',
				url: '/login/authorize',
				headers: {
					   'Content-Type': 'text/plain'
					 },
					 data: 'login'
			}).then(function success(response) {
				if (response.data == "Logged in") {
					$window.location.href = '/index.html';
				}
			});
		}
		
		this.authorize();
	}]);
	
	app.controller('LoginController', [ '$http', '$window', function($http, $window) {
		var control = this;
		control.user = {};
		control.result = "";
		
		this.login = function() {
			$http.post('/login/login', this.user).then(function success(response) {
				if (response.data == "Invalid credentials") {
					control.result = "Invalid credentials";
				} else {
					$window.location.href = '/index.html';
				}
				
				
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		}
		
		
	}]);
	
})();