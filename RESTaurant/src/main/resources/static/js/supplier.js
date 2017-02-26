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
	
	app.controller('BidsController', ['$scope', '$http', '$window', function($scope, $http, $window) {
		var control = this;
		control.bids = [];
		control.toggleOffer = true;
		control.bidToOffer = {};
		control.drinkOffers = [];
		control.groceryOffers = [];
		control.offer = {};
		control.offer.drinkOffers = [];
		control.offer.groceryOffers = [];
		
		this.loadBids = function(){
			$http.get('/supplier/getBids').then(function success(response) {
				control.bids = response.data;
				
				for(it in control.bids){
					control.bids[it].beginning = new Date(control.bids[it].beginning);
					control.bids[it].end = new Date(control.bids[it].end);
				}
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		}
		

		this.isToggled = function(){
			return control.toggleOffer;
		}
		
		this.createOffer = function(bid){
			control.toggleOffer = false;
			control.bidToOffer = bid;
			for(it in bid.drinks){
				var drinkOffer = {};
				drinkOffer.drink = bid.drinks[it];
				drinkOffer.price = {};
				control.drinkOffers.push(drinkOffer);
			}
			
			for(it in bid.groceries){
				var groceryOffer = {};
				groceryOffer.grocery = bid.groceries[it];
				groceryOffer.price = {};
				control.groceryOffers.push(groceryOffer);
			}
		}
		
		this.addDrinkOffer = function(drinkOffer){
			var index = -1;		
			var drinkOfferArr = eval( control.offer.drinkOffers );
			for( var i = 0; i < drinkOfferArr.length; i++ ) {
				if( drinkOfferArr[i].drink.label === drinkOffer.drink.label ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				control.offer.drinkOffers.push(drinkOffer);
			}
		}
		
		this.addGroceryOffer = function(groceryOffer){
			var index = -1;		
			var groceryOfferArr = eval( control.offer.groceryOffers );
			for( var i = 0; i < groceryOfferArr.length; i++ ) {
				if( groceryOfferArr[i].grocery.label === groceryOffer.grocery.label ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				control.offer.groceryOffers.push(groceryOffer);
			}
		}
		
		this.removeDrinkOffer = function(drinkOffer){
			var index = -1;		
			var drinkOfferArr = eval( control.offer.drinkOffers );
			for( var i = 0; i < drinkOfferArr.length; i++ ) {
				if( drinkOfferArr[i].drink.label === drinkOffer.drink.label ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				alert( "Something gone wrong" );
			}
			control.offer.drinkOffers.splice( index, 1 );
		}
		
		this.removeGroceryOffer = function(groceryOffer){
			var index = -1;		
			var groceryOfferArr = eval( control.offer.groceryOffers );
			for( var i = 0; i < groceryOfferArr.length; i++ ) {
				if( groceryOfferArr[i].grocery.label === groceryOffer.grocery.label ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				alert( "Something gone wrong" );
			}
			control.offer.groceryOffers.splice( index, 1 );
		}

		this.addOffer = function(){
			control.offer.delivery = $scope.offerDelivery.value;
			control.offer.warranty = $scope.offerWarranty.value;
			control.offer.lastsUntil = $scope.offerLastsUntil.value;
			control.offer.bid = control.bidToOffer;
			
			$http.post('/supplier/addOffer', control.offer).then(function success(response){
				alert('Success!');
				control.offer = {};
				control.offer.bid = {};
				control.offer.drinkOffers = [];
				control.offer.groceryOffers = [];
				control.bidToOffer = {};
				control.drinkOffers = [];
				control.groceryOffers = [];
				control.toggleOffer = true;
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		}
		
		this.cancelOffer = function(){
			control.offer = {};
			control.offer.bid = {};
			control.offer.drinkOffers = [];
			control.offer.groceryOffers = [];
			control.bidToOffer = {};
			control.drinkOffers = [];
			control.groceryOffers = [];
			control.toggleOffer = true;
		}
		
		$scope.$watch('tabCtrl.isSet(2)', function() {
			control.loadBids();
		});
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
					if(response.data !== "taken" && response.data !== "No email sent" && response.data !== "same"){
						alert("Sucess.");
						control.form.email = response.data;
						supplierService.setSupplierEmail(response.data);
					}else if(response.data === "taken"){
						alert("Email is already in use.");
					}else if(response.data === "No email sent"){
						alert(response.data);
					}
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
						alert("Sucess.");
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
					alert("Sucess.");
					control.form.label = response.data.label;
					control.form.description = response.data.description;
					supplierService.setSupplierDetails(response.data);
				});
			}
		}

		$scope.$watch('tabCtrl.isSet(4)', function() {
			control.form = JSON.parse(JSON.stringify(supplierService.getSupplierDetails()));
		});

	}]);
})();