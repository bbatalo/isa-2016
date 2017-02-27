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
	    this.pill = 1;
	    
	    this.isSet = function(checkTab) {
	      return this.tab === checkTab;
	    };

	    this.setTab = function(setTab) {
	      this.tab = setTab;
	    };
	    
	    this.isPill = function(checkPill) {
	    	return this.pill === checkPill;
		};

		this.setPill = function(setPill) {
			this.pill = setPill;
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

		$scope.$watch('tabCtrl.tab', function(newValue) {
			if (newValue == 6) {
				control.form = JSON.parse(JSON.stringify(customerService.getCustomerDetails()));
				control.form.dateBirth = new Date(control.form.dateBirth)
			}
		});

	}]);
	
	app.controller('FriendsController', ['$scope', '$http', '$window', 'customerService', function($scope, $http, $window, customerService) { 
		var control = this;
		control.friends = [];
		
		control.filters = [
		      {id: '1', name: 'Email'},
		      {id: '2', name: 'Name'},
		      {id: '3', name: 'Surname'}
		    ];
		
		control.selected = {id: '1', name: 'Email'};
		control.cst_selected = {id: '1', name: 'Email'};
		
		control.customers = [];
		
		$scope.$watch('tabCtrl.tab', function(newValue) {
			if (newValue == 2) {
				var requestData = {};
				requestData.userID = customerService.getCustomer().userID;
				
				$http({
					method: 'POST',
					url: '/index/loadFriends',
					headers: {
						   'Content-Type': 'application/json',	   
						 },
					data: requestData
				}).then(function success(response) {
					if (response.data.length != 0) {
						control.friends = response.data;
					}
				});
				
				control.loadCustomers()
			}
		});
		
		control.loadCustomers = function() {
			control.customers = [];
			
			$http({
				method: 'GET',
				url: '/index/loadCustomers',
			}).then(function success(response) {
				if (response.data.length != 0) {
					control.customers = response.data;
				}
			});
		}
		
		control.sendRequest = function(id) {
			var requestData = {};
			requestData.senderID = customerService.getCustomer().userID;
			requestData.receiverID = id;
			
			$http({
				method: 'POST',
				url: '/index/sendRequest',
				headers: {
					   'Content-Type': 'application/json',	   
					 },
				data: requestData
			}).then(function success(response) {
				status = response.data;
				if (status == "Repeated") {
					toastr["warning"]('You have already sent a request!');
				} else if (status == 'Inbox') {
					toastr["warning"]('Selected user has sent a request to YOU! Better check your requests inbox.');
				}
					
			});
		}
	}]);
	
	app.controller('RequestsController', ['$scope', '$http', '$window', 'customerService', function($scope, $http, $window, customerService) { 
		var control = this;
		control.incoming = [];
		control.outcoming = [];
		
		$scope.$watch('tabCtrl.tab', function(newValue) {
			if (newValue == 5) {
				
				control.loadRequests()
			}
		});
		
		control.findInd = function(id, type) {
			if (type == 'incoming') {
				length = control.incoming.length;
				for (i = 0; i < length; i++) {
					if (control.incoming[i].requestID == id) {
						return i;
					}
				}
			} else if (type == 'outcoming'){
				length = control.outcoming.length;
				for (i = 0; i < length; i++) {
					if (control.outcoming[i].requestID == id) {
						return i;
					}
				}
			}
			return -1;
		}
		
		control.loadRequests = function() {
			control.incoming = [];
			control.outcoming = [];
			
			$http({
				method: 'GET',
				url: '/index/loadRequests',
			}).then(function success(response) {
				if (response.data.length != 0) {
					control.incoming = response.data[0];
					control.outcoming = response.data[1];
				
				}
			});
		}
		
		control.accept = function(request) {
			$http({
				method: 'POST',
				url: '/index/acceptRequest',
				headers: {
					   'Content-Type': 'application/json',	   
					 },
				data: request
			}).then(function success(response) {
				if (response.data == 'Success') {

				} else if (response.data == "Already accepted") {
					toastr["warning"]('You have already accepted this request!');
				}
			});
		}
		
		control.decline = function(request) {
			$http({
				method: 'POST',
				url: '/index/declineRequest',
				headers: {
					   'Content-Type': 'application/json',	   
					 },
				data: request
			}).then(function success(response) {
				if (response.data == 'Success') {

				} else if (response.data == 'Already declined') {
					toastr["warning"]('You have already declined this request!');
				}
			});
		}
		
		control.setupWebsocket = function() {
			var socket = new SockJS('/stomp');
			var stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				var str = "requests?userID=" + customerService.getCustomer().userID;
				stompClient.subscribe("/topic/" + str, function(data) {
					var message = data.body;
					req = angular.fromJson(message);
					id = customerService.getCustomer().userID;
					if (req.senderID == id) {
						if (req.status == 'Pending') {
							control.outcoming.push(req);
							$scope.$apply();
							toastr["success"]('Request sent to:' + req.receiverMail, "Request sent");
						} else if (req.status == 'Accepted') {
							ind = control.findInd(req.requestID, 'outcoming');
							control.outcoming[ind].status = 'Accepted';
							$scope.$apply();
							toastr["success"]('User:' + req.receiverMail + ' accepted your friendship request.', "Request accepted!");
						} else if (req.status == 'Declined') {
							ind = control.findInd(req.requestID, 'outcoming');
							control.outcoming[ind].status = 'Declined';
							$scope.$apply();
							toastr["error"]('User:' + req.receiverMail + ' declined your friendship request.', "Request declined!");
						}
						
					} else {
						if (req.status == 'Pending') {
							control.incoming.push(req);
							$scope.$apply();
							toastr["info"]('You have received a friendship request.');
						} else if (req.status == 'Accepted') {
							ind = control.findInd(req.requestID, 'incoming');
							control.incoming[ind].status = 'Accepted';
							$scope.$apply();
							toastr["success"]('Friendship request from user:' + req.senderMail + ' accepted.', "Request accepted!");
						} else if (req.status == 'Declined') {
							ind = control.findInd(req.requestID, 'incoming');
							control.incoming[ind].status = 'Declined';
							$scope.$apply();
							toastr["error"]('Friendship request from user:' + req.senderMail + ' declined.', "Request declined!");
						}
					}
				})
			})
		}
		
		this.setupWebsocket();
	}]);
	
})();

