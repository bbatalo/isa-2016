(function() {
	var app = angular.module('index', []);
	
	app.service('customerService', function() {
		var customer = {};
		
		var setCustomer = function(newCustomer) {
			customer = newCustomer;
		}
		
		var getCustomer = function() {
			return customer;
		}
		
		var getCustomerDetails = function() {
			var tmp = {};
			tmp.userID = customer.userID;
			tmp.email = customer.email;
			tmp.name = customer.name;
			tmp.surname = customer.surname;
			tmp.address = customer.address;
			tmp.dateBirth = customer.dateBirth;
			return tmp;
		}
		
		var setCustomerEmail = function(email) {
			customer.email = email;
		}
		
		var setCustomerDetails = function(details) {
			customer.name = details.name;
			customer.surname = details.surname;
			customer.address = details.address;
			customer.dateBirth = details.dateBirth;
		}
		
		return {
		    setCustomer: setCustomer,
		    getCustomer: getCustomer,
		    getCustomerDetails: getCustomerDetails,
		    setCustomerEmail: setCustomerEmail,
		    setCustomerDetails: setCustomerDetails
		  };
	});
	
	app.controller('IndexController', ['$http', '$window', 'customerService', function($http, $window, customerService) {
		
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
				url: '/index/load'
			}).then(function success(response) {
				if (response.data.email != null) {
					customerService.setCustomer(response.data);
				}
			});
		}
				
		this.authorize();
		this.loadUser();
	}]);
	
	app.controller("TabController", function(){
	    this.tab = 1;

	    this.isSet = function(checkTab) {
	      return this.tab === checkTab;
	    };

	    this.setTab = function(setTab) {
	      this.tab = setTab;
	    };
	});
	
	app.controller('ProfileController', ['$scope', '$http', '$window', 'customerService', function($scope, $http, $window, customerService) {
		var control = this;
		control.form = {};
		
		this.update = function(type) {
			var requestData = {};
			
			
			if (type == 0) {
				requestData.userID = control.form.userID;
				requestData.email = control.form.email;
				$http({
					method: 'POST',
					url: '/index/updateEmail',
					headers: {
						   'Content-Type': 'application/json',	   
						 },
						 data: requestData
				}).then(function success(response) {
					control.form.email = response.data;
					customerService.setCustomerEmail(response.data);
				});
			} else if (type == 1) {
				if (control.form.newPass == control.form.repeatPass) {
					requestData.userID = control.form.userID;
					requestData.currentPass = control.form.currentPass;
					requestData.newPass = control.form.newPass;
					$http({
						method: 'POST',
						url: '/index/updatePassword',
						headers: {
							   'Content-Type': 'application/json',	   
							 },
							 data: requestData
					}).then(function success(response) {
						control.form.currentPass = "";
						control.form.newPass = "";
						control.form.repeatPass = "";
					});
				} else {
					control.form.newPass = "";
					control.form.repeatPass = "";
					control.resultPass = "Passwords must match.";
				}
			} else if (type ==2) {
				requestData.userID = control.form.userID;
				requestData.name = control.form.name;
				requestData.surname = control.form.surname;
				requestData.address = control.form.address;
				requestData.dateBirth = control.form.dateBirth;
				$http({
					method: 'POST',
					url: '/index/updateDetails',
					headers: {
						   'Content-Type': 'application/json',	   
						 },
						 data: requestData
				}).then(function success(response) {
					control.form.name = response.data.name;
					control.form.surname = response.data.surname;
					control.form.address = response.data.address;
					control.form.dateBirth = new Date(response.data.dateBirth);
					customerService.setCustomerDetails(response.data);
				});
			}
		}

		$scope.$watch('tabCtrl.isSet(6)', function() {
			control.form = JSON.parse(JSON.stringify(customerService.getCustomerDetails()));
			control.form.dateBirth = new Date(control.form.dateBirth)
		});

	}]);
	
	
})();

