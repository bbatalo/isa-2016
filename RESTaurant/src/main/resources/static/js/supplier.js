(function(){
	var app = angular.module('supplier', []);
	
	app.controller("TabController", function(){
	    this.tab = 1;

	    this.isSet = function(checkTab) {
	      return this.tab === checkTab;
	    };

	    this.setTab = function(setTab) {
	      this.tab = setTab;
	    };
	});
	
	app.service('supplierService', function() {
		var supplier = {};
		
		var setSupplier = function(newSupplier) {
			supplier = newSupplier;
		}
		
		var getSupplier = function() {
			return supplier;
		}
		
		var getSupplierDetails = function() {
			var tmp = {};
			tmp.userID = supplier.userID;
			tmp.email = supplier.email;
			tmp.label = supplier.label;
			tmp.description = supplier.description;
			return tmp;
		}
		
		var setSupplierEmail = function(email) {
			supplier.email = email;
		}
		
		var setSupplierDetails = function(details) {
			supplier.label = details.label;
			supplier.description = details.description;
		}
		
		return {
		    setSupplier: setSupplier,
		    getSupplier: getSupplier,
		    getSupplierDetails: getSupplierDetails,
		    setSupplierEmail: setSupplierEmail,
		    setSupplierDetails: setSupplierDetails
		  };
	});
	
	app.controller('SupplierController', ['$http', '$window', 'supplierService', function($http, $window, supplierService) {
		
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
				url: '/supplier/load'
			}).then(function success(response) {
				if (response.data.email != null) {
					supplierService.setSupplier(response.data);
				}
			});
		}
				
		this.authorize();
		this.loadUser();
	}]);
	
	app.controller('ProfileController', ['$scope', '$http', '$window', 'supplierService', function($scope, $http, $window, supplierService) {
		var control = this;
		control.form = {};
		
		this.update = function(type) {
			var requestData = {};
			
			
			if (type == 0) {
				requestData.userID = control.form.userID;
				requestData.email = control.form.email;
				$http({
					method: 'POST',
					url: '/supplier/updateEmail',
					headers: {
						   'Content-Type': 'application/json',	   
						 },
						 data: requestData
				}).then(function success(response) {
					control.form.email = response.data;
					supplierService.setSupplierEmail(response.data);
				});
			} else if (type == 1) {
				if (control.form.newPass == control.form.repeatPass) {
					requestData.userID = control.form.userID;
					requestData.currentPass = control.form.currentPass;
					requestData.newPass = control.form.newPass;
					$http({
						method: 'POST',
						url: '/supplier/updatePassword',
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
				requestData.label = control.form.label;
				requestData.description = control.form.description;
				$http({
					method: 'POST',
					url: '/supplier/updateDetails',
					headers: {
						   'Content-Type': 'application/json',	   
						 },
						 data: requestData
				}).then(function success(response) {
					control.form.label = response.data.label;
					control.form.description = response.data.description;
					supplierService.setSupplierDetails(response.data);
				});
			}
		}

		$scope.$watch('tabCtrl.isSet(3)', function() {
			control.form = JSON.parse(JSON.stringify(supplierService.getSupplierDetails()));
		});

	}]);
})();