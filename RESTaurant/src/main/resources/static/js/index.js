(function() {
	var app = angular.module('index', []);
	
	app.controller('ProfileController', ['$http', '$window', function($http, $window) {
		
		this.logout = function() {
			$http.get("/login/logout").then(function(response) {
				$window.location.href = '/login.html';
			});
			
		}
	}]);
	
	app.controller('IndexController', ['$http', '$window', function($http, $window) {
		
		this.authorize = function() {
			
			$http({
				method: 'POST',
				url: '/login/authorize',
				headers: {
					   'Content-Type': 'text/plain'
					 },
					 data: 'index'
			}).then(function success(response) {
				if (response.data == "Not logged in") {
					$window.location.href = '/login.html';
				}
			});
		}
		
		this.authorize();
	}]);
	
})();

