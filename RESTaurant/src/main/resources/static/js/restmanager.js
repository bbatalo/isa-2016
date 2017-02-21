(function(){
	var app = angular.module('restmanager', []);
	
	app.controller("TabController", function(){
	    this.tab = 1;

	    this.isSet = function(checkTab) {
	      return this.tab === checkTab;
	    };

	    this.setTab = function(setTab) {
	      this.tab = setTab;
	    };
	});
	
	app.service('restaurantService', function(){
		var restaurant = {};
		
		var setRestaurant = function(newRestaurant) {
			restaurant = newRestaurant;
		}
		
		var getRestaurant = function() {
			return restaurant;
		}
		
		var getRestaurantMenu = function(){
			return restaurant.menu;
		}
		
		var getRestaurantDetails = function() {
			var tmp = {};
			tmp.restaurantID = restaurant.restaurantID;
			tmp.name = restaurant.name;
			tmp.type = restaurant.type;
			tmp.description = restaurant.description;
			return tmp;
		}
		
		var setRestaurantName = function(name) {
			restaurant.name = name;
		}
		
		var setRestaurantDetails = function(details) {
			restaurant.type = details.type;
			restaurant.description = details.description;
		}
		
		return {
		    setRestaurant: setRestaurant,
		    getRestaurant: getRestaurant,
		    getRestaurantDetails: getRestaurantDetails,
		    setRestaurantName: setRestaurantName,
		    setRestaurantDetails: setRestaurantDetails,
		    getRestaurantMenu: getRestaurantMenu
		  };
	});
	
	app.controller('RestManagerController', ['$http', '$window', 'restaurantService', function($http, $window, restaurantService) {
		
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
		
		this.logout = function() {
			$http.get("/login/logout").then(function(response) {
				$window.location.href = '/login.html';
			});
			
		}
		
		this.loadUser = function() {
			
			$http({
				method: 'GET',
				url: '/restmanager/load'
			}).then(function success(response) {
				if (response.data.email != null) {
					restaurantService.setRestaurant(response.data.restaurant);
				}
			});
		}
				
		this.authorize();
		this.loadUser();
	}]);
	
	app.controller('RestaurantController', ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService) {
		var control = this;
		control.form = {};
		
		this.update = function(type) {
			var requestData = {};
			
			
			if (type == 0) {
				requestData.restaurantID = control.form.restaurantID;
				requestData.name = control.form.name;
				$http({
					method: 'POST',
					url: '/restmanager/updateRestaurantName',
					headers: {
						   'Content-Type': 'application/json',	   
						 },
						 data: requestData
				}).then(function success(response) {
					if(response.data === "same"){
						//qwe
					}else if(response.data === "taken"){
						alert("Restaurant name is already taken.");
					}else if(response.data === "No name sent"){
						alert(response.data);
					}else{
						alert("Success.");
						control.form.name = response.data;
						restaurantService.setRestaurantName(response.data);
					}
				});
			} else if (type == 1) {
				requestData.restaurantID = control.form.restaurantID;
				requestData.type = control.form.type;
				requestData.description = control.form.description;
				$http({
					method: 'POST',
					url: '/restmanager/updateRestaurantDetails',
					headers: {
						   'Content-Type': 'application/json',	   
						 },
						 data: requestData
				}).then(function success(response) {
					alert("Success.");
					control.form.type = response.data.type;
					control.form.description = response.data.description;
					restaurantService.setRestaurantDetails(response.data);
				});
			}
		}

		$scope.$watch('tab.isSet(2)', function() {
			control.form = JSON.parse(JSON.stringify(restaurantService.getRestaurantDetails()));
		});

	}]);
	
	app.controller('MenuController', ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService) {
		var control = this;
		control.dish = {};
		control.dishes = [];
		control.result = "";
		
		this.register = function(){
			control.dish.menu = restaurantService.getRestaurantMenu();
			$http.post('/restmanager/addDish', this.dish).then(function success(response) {
				control.result = response.data;
				if(control.result === "OK"){
					control.dishes.push(control.dish);
					control.dish = {};
				}
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		};
		
		this.getDishes = function(){
			$http.post('/restmanager/getDishes', restaurantService.getRestaurantMenu()).then(function success(response){
				control.dishes = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
			
		};
		
		$scope.$watch('tab.isSet(3)', function() {
			control.getDishes();
		});
		
		this.removeDish = function(dish){
			$http.post('/restmanager/removeDish', dish).then(function success(response){
				alert(response.data);
				var index = -1;		
				var dishArr = eval( control.dishes );
				for( var i = 0; i < dishArr.length; i++ ) {
					if( dishArr[i].label === dish.label ) {
						index = i;
						break;
					}
				}
				if( index === -1 ) {
					alert( "Something gone wrong" );
				}
				control.dishes.splice( index, 1 );
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		}
	}]);
})();