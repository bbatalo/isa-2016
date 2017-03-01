(function() {
	var app = angular.module('employee', ['mwl.calendar', 'ngAnimate', 'ui.bootstrap', 'colorpicker.module']);
	var proba = {};
	var type = 0;
	var viewDate = new Date();
	
	app.service('employeeService', function() {
		var restaurant = {};
		var employee = {};
		var shifts = [];
		
		var setEmployee = function(newEmployee) {
			employee = newEmployee;
		}
		
		var getEmployee = function() {
			return employee;
		}
		
		var setRestaurant = function(newRestaurant) {
			restaurant = newRestaurant;
		}
		
		var getRestaurant = function() {
			return restaurant;
		}
		
		var setShift = function(shift) {
			shifts = shift;
		}
		
		var getShift = function(){
			return shifts;
		}
		
		var getRestaurantSchedule = function(){
			return restaurant.schedule;
		}
		
		var getRestaurantSeating = function(){
			return restaurant.seatingArrangement;
		}
		
		var getRestaurantDetails = function() {
			var tmp = {};
			tmp.restaurantID = restaurant.restaurantID;
			tmp.name = restaurant.name;
			tmp.type = restaurant.type;
			tmp.description = restaurant.description;
			return tmp;
		}
		
		var getRestaurantMenu = function(){
			return restaurant.menu;
		}
		
		var getEmployeeDetails = function() {
			var tmp = {};
			tmp.userID = employee.userID;
			tmp.email = employee.email;
			tmp.name = employee.name;
			tmp.surname = employee.surname;
			tmp.dateBirth = employee.dateBirth;
			tmp.sizeCloth = employee.sizeCloth;
			tmp.sizeShoes = employee.sizeShoes;
			return tmp;
		}
		
		var setEmployeeEmail = function(email) {
			employee.email = email;
		}
		
		var setEmployeeDetails = function(details) {
			employee.name = details.name;
			employee.surname = details.surname;
			employee.dateBirth = details.dateBirth;
			employee.sizeCloth = details.sizeCloth;
			employee.sizeShoes = details.sizeShoes;
		}
		
		return {
		    setEmployee: setEmployee,
		    getEmployee: getEmployee,
		    getEmployeeDetails: getEmployeeDetails,
		    setEmployeeEmail: setEmployeeEmail,
		    setEmployeeDetails: setEmployeeDetails,
		    setRestaurant: setRestaurant,
		    getRestaurant: getRestaurant,
		    getRestaurantDetails: getRestaurantDetails,
		    getRestaurantMenu: getRestaurantMenu,
		    getRestaurantSeating: getRestaurantSeating,
		    getRestaurantSchedule: getRestaurantSchedule,
		    getShift : getShift,
		    setShift : setShift
		  };
	});
	
	
	
	app.controller('EmployeeController', ['$rootScope', '$scope', '$http', '$window', 'employeeService', function($rootScope, $scope, $http, $window, employeeService) {
		var radnik = {};
		this.authorize = function() {
			
			$http({
				method: 'POST',
				url: '/login/authorize',
				headers: {
					   'Content-Type': 'text/plain'
					 },
					 data: 'employee'
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
				url: '/employee/load'
			}).then(function success(response) {
				if (response.data.email != null) {
					employeeService.setEmployee(response.data);
					$scope.rola = response.data.role;
					employeeService.setRestaurant(response.data.restaurant);
					radnik = response.data;
					$window.type = radnik.role;
					$rootScope.type = $window.type;
					if (response.data.passChanged == false && $window.location.href!="http://localhost:8080/passwordChange.html"){
						window.alert("Please, change your password");
						proba = 6;
						$rootScope.$broadcast('handleBroadcast');
					}
				}
			});
		}
		
		$scope.loadShift = function() {
			$scope.shiftUnion = [];
			$http.post('/restmanager/getShifts', employeeService.getRestaurantSchedule()).then(function success(response){
				
				for(it in response.data){
					if (response.data[it].employee.role === employeeService.getEmployee().role){
						var begin = new Date(response.data[it].shiftBegins);
						var end = new Date(response.data[it].shiftEnds);
						var current = new Date();
						if( current > begin && current<end ){
							$scope.shiftUnion.push(response.data[it].segment.label);
						}
					}
				}
				employeeService.setShift($scope.shiftUnion);
			}), function error(response){
			}
		}
				
		this.authorize();
		this.loadUser();
		$scope.$watch('tabCtrl.tab', function(newValue) {
			if(newValue == 2){
				$scope.loadShift();
			}
		});
		
	}]);
	
	app.controller("TabController", ['$scope',function($scope){
		$scope.selected = {};
	    $scope.selected.tab = 7;
	    this.tab = $scope.selected.tab;

	    this.isSet = function(checkTab) {
	      return $scope.selected.tab === checkTab;
	    };
	    
	    $scope.isSet = this.isSet;

	    this.setTab = function(setTab) {
	    	if(proba!=6){
	    		$scope.selected.tab = setTab;
	    		this.tab = $scope.selected.tab;
	    	} else {
	    		window.alert("It is necessary that you change your password!");
	    	}
	    };
	    
	    $scope.setTab = this.setTab;
	    
	    $scope.$on('handleBroadcast', function() {
		       $scope.selected.tab = 6;
		       this.tab = $scope.selected.tab;
		      });

	}]);
	
	app.controller('ProfileController', ['$scope', '$http', '$window', 'employeeService', function($scope, $http, $window, employeeService) {
		var control = this;
		control.form = {};
		
		this.update = function(type) {
			var requestData = {};
			
			
			if (type == 0) {
				requestData.userID = control.form.userID;
				requestData.email = control.form.email;
				$http({
					method: 'POST',
					url: '/employee/updateEmail',
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
						url: '/employee/updatePassword',
						headers: {
							   'Content-Type': 'application/json',	   
							 },
							 data: requestData
					}).then(function success(response) {
						proba = 1;
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
				requestData.sizeCloth = control.form.sizeCloth;
				requestData.sizeShoes = control.form.sizeShoes;
				requestData.dateBirth = control.form.dateBirth;
				$http({
					method: 'POST',
					url: '/employee/updateDetails',
					headers: {
						   'Content-Type': 'application/json',	   
						 },
						 data: requestData
				}).then(function success(response) {
					control.form.name = response.data.name;
					control.form.surname = response.data.surname;
					control.form.sizeCloth = response.data.sizeCloth;
					control.form.sizeShoes = response.data.sizeShoes;
					control.form.dateBirth = new Date(response.data.dateBirth);
					employeeService.setEmployeeDetails(response.data);
				});
			}
		}

		$scope.$watch('tabCtrl.isSet(6)', function() {
			control.form = JSON.parse(JSON.stringify(employeeService.getEmployeeDetails()));
			control.form.dateBirth = new Date(control.form.dateBirth)
		});

	}]);
	
	app.controller('KitchenSinkCtrl', function(moment, alert, calendarConfig) {

	    var vm = this;

	    //These variables MUST be set as a minimum for the calendar to work
	    vm.calendarView = 'month';
	    vm.viewDate = new Date();
	    var actions = [{
	      label: '<i class=\'glyphicon glyphicon-pencil\'></i>',
	      onClick: function(args) {
	        alert.show('Edited', args.calendarEvent);
	      }
	    }, {
	      label: '<i class=\'glyphicon glyphicon-remove\'></i>',
	      onClick: function(args) {
	        alert.show('Deleted', args.calendarEvent);
	      }
	    }];
	    vm.events = [
	    ];

	    vm.cellIsOpen = true;

	    vm.addEvent = function() {
	      vm.events.push({
	        title: 'New event',
	        startsAt: moment().startOf('day').toDate(),
	        endsAt: moment().endOf('day').toDate(),
	        color: calendarConfig.colorTypes.important,
	        draggable: true,
	        resizable: true
	      });
	    };

	    vm.eventClicked = function(event) {
	    };

	    vm.eventEdited = function(event) {
	    };

	    vm.eventDeleted = function(event) {
	    };

	    vm.eventTimesChanged = function(event) {
	    };

	    vm.toggle = function($event, field, event) {
	      $event.preventDefault();
	      $event.stopPropagation();
	      event[field] = !event[field];
	    };

	    vm.timespanClicked = function(date, cell) {

	      if (vm.calendarView === 'month') {
	        if ((vm.cellIsOpen && moment(date).startOf('day').isSame(moment(vm.viewDate).startOf('day'))) || cell.events.length === 0 || !cell.inMonth) {
	          vm.cellIsOpen = false;
	        } else {
	          vm.cellIsOpen = true;
	          vm.viewDate = date;
	        }
	      } else if (vm.calendarView === 'year') {
	        if ((vm.cellIsOpen && moment(date).startOf('month').isSame(moment(vm.viewDate).startOf('month'))) || cell.events.length === 0) {
	          vm.cellIsOpen = false;
	        } else {
	          vm.cellIsOpen = true;
	          vm.viewDate = date;
	        }
	      }

	    };
	});
	
	
	
	// GAGIJEVO SEATING SOKOCALO POBOLJSANO HEHE
	
	app.controller('SeatingController', ['$scope', '$http', '$window', 'employeeService', function($scope, $http, $window, employeeService) {
		var control = this;
		control.segment = {};
		control.segments = [];
		control.segmenti = [];
		control.result = "";
		control.selectedSegment = {};
		control.tables = {};
		control.shifts = $scope.shiftUnion;

		
		//Instanciranje matrice stolova, stolovi su inicajlno prazni(nema stolova)
		this.matrix = function(segment){
			var arr = [];
			for (var i = 0; i < segment.tableRows; ++i){
				var columns = [];
				for (var j = 0; j < segment.tableColumns; ++j){
					var table = {};
					table.status = "empty";
					table.segment = segment;
					table.tableCode = table.segment.label + i + j;
					table.tableRow = i;
					table.tableCol = j;
					table.tableClass = control.setTableClass(table);
					columns[j] = table;
				}
				arr[i] = columns;
			}
			return arr;
		}
		
		
		this.getSegments = function(){
			$http.post('/restmanager/getSegments', employeeService.getRestaurantSeating()).then(function success(response){
				control.segments = response.data;
				control.segmenti = response.data;
				for (var i=0; i<control.segments.length; i++){
					$scope.arrangeTables(control.segments[i],i);
					control.segmenti[i].tables = control.tables;
				}
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		};
		
		
		$scope.$watch('tabCtrl.tab', function(newValue) {
			if(newValue == 2){
				control.getSegments();
			}
		});
		
		
		//Uzimaju se svi stolovi nekog segmenta
		$scope.arrangeTables = function(segment,i){

			var cache = [];
			segment2 = JSON.stringify(segment, function(key, value) {
			    if (typeof value === 'object' && value !== null) {
			        if (cache.indexOf(value) !== -1) {
			            // Circular reference found, discard key
			            return;
			        }
			        // Store value in our collection
			        cache.push(value);
			    }
			    return value;
			});

			control.selectedSegment = segment;
			control.tables = control.matrix(segment);
				
			$http.post('/restmanager/getTables', segment2).then(function success(response){
				for(it in response.data){

					//Svi stolovi iz segmenta se postavljaju na svoje pozicije
					control.segmenti[i].tables[response.data[it].tableRow][response.data[it].tableCol] = response.data[it];
					control.segmenti[i].tables[response.data[it].tableRow][response.data[it].tableCol].tableClass = control.setTableClass(response.data[it]);
				}
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}

			

		}
		
		
		//ovom metodom postavljas klasu buttona koji prikazuje sto
		this.setTableClass = function(table){
			if(table.status === "empty")
				return "btn btn-default";
			else if(table.status === "free")
				return "btn btn-success";
			else if(table.status === "taken")
				return "btn btn-danger";
		}
		
		this.goBack = function(){
			control.selectedSegment = {};
			control.tables = {};
			

		}
	}]);
	
	
	
	// KRAJ GAGIJEVOG SOKOCALA
	
	
	// PUNJENJE SMENAMA
	app.controller("ShiftController", ['$scope', '$http', 'employeeService', function($scope, $http, employeeService){
		var control = this;
		
		//----novi deo
		control.shifts = [];
		control.employee = {};
		control.segment = {};
		control.shiftBegins = new Date();
		control.shiftEnds = new Date();
		control.event = {
			id: 0,
			title: 'New shift.',
	        startsAt: new Date(),
	        endsAt: new Date(),
	        draggable: false,
	        resizable: false,
		};
		//---novi deo
		
		control.events = [];
		
		
		
		$scope.$watch('tabCtrl.tab', function(newValue) {
				
				if(newValue !== 1)
					return;
			
				$http.post('/restmanager/getSegments', employeeService.getRestaurantSeating()).then(function success(response){
					control.segments = response.data;
				}), function error(response){
					control.result = "Unknown error ocurred.";
				}
				
				$http.post('/restmanager/getShifts', employeeService.getRestaurantSchedule()).then(function success(response){
					control.shifts = response.data;
					for(it in response.data){
						if (response.data[it].employee.role === employeeService.getEmployee().role){
							var word;
							if(response.data[it].employee.role=="WAITER"){
								word = response.data[it].employee.name + " " + response.data[it].employee.surname +" at "+response.data[it].segment.label
							} else {
								word = response.data[it].employee.name + " " + response.data[it].employee.surname;
							}
							event = {
							        id: response.data[it].idShift,
									title: word,
							        startsAt: new Date(response.data[it].shiftBegins),
							        endsAt: new Date(response.data[it].shiftEnds),
							        draggable: true,
							        resizable: true,
							      }
							
							control.events.push(event);
						}
					}
				}), function error(response){
					control.result = "Unknown error ocurred.";
				}
		});
		
		
		
		
	}]);
	
	// KONTROLER ZA SOKETE
	
	app.controller('OrderController', ['$scope', '$http', '$window', 'employeeService', function($scope, $http, $window, employeeService) { 
		var control = this;
		control.orders = [];
		control.items = [];
		
		$scope.$watch('tabCtrl.tab', function(newValue) {
			if (newValue == 3) {
				
				control.loadRequests()
			}
		});
		
		
		control.loadRequests = function() {
			control.incoming = [];
			control.outcoming = [];
			
			$http({
				method: 'GET',
				url: '/reservations/loadOrders',
			}).then(function success(response) {
				if (response.data != null) {
					control.orders = response.data;
					for (var i=0; i<control.orders.length; i++){
						var brt = control.orders[i];
						var pr = {};
						$http({
							method: 'POST',
							url: '/reservations/getDishOrder',
							headers: {
								   'Content-Type': 'application/json',	   
								 },
								 data: brt.id
						}).then(function success(response) {
							var lisnato = response.data;
							for(var j=0; j<lisnato.length; j++){
								pr = { name : lisnato[j].dish.label,
										type: 'meal',
										table : lisnato[j].order.table.tableCode,
										ident: lisnato[j].id,
										status: lisnato[j].status
									  };
								
								control.items.push(pr);
							}
							
							
							
						});
						
						$http({
							method: 'POST',
							url: '/reservations/getDrinkOrder',
							headers: {
								   'Content-Type': 'application/json',	   
								 },
								 data: brt.id
						}).then(function success(response) {
							var lisnato = response.data;
							for(var j=0; j<lisnato.length; j++){
								pr = { name : lisnato[j].drink.label,
										type: 'drink',
										table : lisnato[j].order.table.tableCode,
										ident: lisnato[j].id,
										status: lisnato[j].status
									  };
								
								control.items.push(pr);
							}
						
					});
					}
				
				}
			});
		}
		
		
		control.prepare = function(item) {
			control.item = item;
			if(item.type=='meal'){
				$http({
					method: 'POST',
					url: '/reservations/sendMeal',
					headers: {
						   'Content-Type': 'application/json',	   
						 },
					data: item.ident
				}).then(function success(response) {
					toastr["success"]('...', "Sending Meal Request");
				});
			} else if (item.type=='drink'){

					$http({
						method: 'POST',
						url: '/reservations/sendDrink',
						headers: {
							   'Content-Type': 'application/json',	   
							 },
						data: item.ident
					}).then(function success(response) {
						toastr["success"]('...', "Sending Drink Request");
					});
				
			}
			
		}
		
		
		control.delete = function(item) {
			control.item = item;
			if(item.type=='meal'){
				$http({
					method: 'POST',
					url: '/reservations/deleteMeal',
					headers: {
						   'Content-Type': 'application/json',	   
						 },
					data: item.ident
				}).then(function success(response) {
					var index = control.items.indexOf(item);
					control.items.splice(index, 1);
					toastr["success"]('...', "Removing Meal Request");
				});
			} else if (item.type=='drink'){

					$http({
						method: 'POST',
						url: '/reservations/deleteDrink',
						headers: {
							   'Content-Type': 'application/json',	   
							 },
						data: item.ident
					}).then(function success(response) {
						var index = control.items.indexOf(item);
						control.items.splice(index, 1);
						toastr["success"]('...', "Removing Drink Request");
					});
				
			}
			

			
			
		}
		
		
		control.setupWebsocket = function() {
			var socket = new SockJS('/stomp');
			var stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				var str = "ordersw?userID=" + employeeService.getEmployee().userID;
				stompClient.subscribe("/topic/" + str, function(data) {
					var message = data.body;
					req = angular.fromJson(message);
					id = employeeService.getEmployee().userID;
						if (req.status == 'Fresh') {
							control.orders.push(req);
							var pr = {};
							$http({
								method: 'POST',
								url: '/reservations/getDishOrder',
								headers: {
									   'Content-Type': 'application/json',	   
									 },
									 data: req.id
							}).then(function success(response) {
								var lisnato = response.data;
								for(var j=0; j<lisnato.length; j++){
									pr = { name : lisnato[j].dish.label,
											type: 'meal',
											table: lisnato[j].order.table.tableCode,
											ident: lisnato[j].id,
											status: lisnato[j].status};
									
									control.items.push(pr);
								}
							});
							
							$http({
								method: 'POST',
								url: '/reservations/getDrinkOrder',
								headers: {
									   'Content-Type': 'application/json',	   
									 },
									 data: req.id
							}).then(function success(response) {
								var lisnato = response.data;
								for(var j=0; j<lisnato.length; j++){
									pr = { name : lisnato[j].drink.label,
											type: 'drink',
											table : lisnato[j].order.table.tableCode,
											ident: lisnato[j].id,
											status: lisnato[j].status
										  };
									
									control.items.push(pr);
								}
							
						});
							$scope.$apply();
							toastr["info"]('You have received an order.');
						} else if (req.status == 'Updated') {
							for(var i=0; i<control.orders.length; i++){
								if(control.orders[i].id==req.id){
									control.orders[i]=req;
									control.orders[i].status = 'Fresh';
								}
							}
							
							$scope.$apply();
							toastr["info"]('Item successfully deleted.');
							
						}
				})
				
				var str2 = "cooks?userID=" + employeeService.getEmployee().userID;
				stompClient.subscribe("/topic/" + str2, function(data) {
					var message = data.body;
					req = angular.fromJson(message);
					id = employeeService.getEmployee().userID;
						if (req.status == 'Sent') {
							for (var i = 0; i<control.items.length; i++){
								if(control.items[i].ident==req.id && control.items[i].type=='meal'){
									pr = { name : req.dish.label,
											type: 'meal',
											table: req.order.table.tableCode,
											ident: req.id};
									
									control.items[i]=pr;
							
								}
							}
							$scope.$apply();
							toastr["info"]('Meal request successfully sent.');
						} 
				})
				
				var str3 = "bars?userID=" + employeeService.getEmployee().userID;
				stompClient.subscribe("/topic/" + str3, function(data) {
					var message = data.body;
					req = angular.fromJson(message);
					id = employeeService.getEmployee().userID;
						if (req.status == 'Sent') {
							for (var i = 0; i<control.items.length; i++){
								if(control.items[i].ident==req.id && control.items[i].type=='drink'){
									pr = { name : req.drink.label,
											type: 'drink',
											table: req.order.table.tableCode,
											ident: req.id};
									
									control.items[i]=pr;
							
								}
							}
							$scope.$apply();
							toastr["info"]('Drink request successfully sent.');
						} 
				})
			})
		}
		
		this.setupWebsocket();
	}]);
	
	
	app.controller('CookingController', ['$scope', '$http', '$window', 'employeeService', function($scope, $http, $window, employeeService) { 
		var control = this;
		control.orders = [];
		control.items = [];
		
		$scope.$watch('tabCtrl.tab', function(newValue) {
			if (newValue == 4) {
				
				control.loadRequests()
			}
		});
		
		
		control.loadRequests = function() {

			
			$http({
				method: 'GET',
				url: '/reservations/loadCookOrders',
			}).then(function success(response) {
				if (response.data != null) {
					control.orders = response.data;
					
				
				}
			});
		}
		
		
		
		
		control.setupWebsocket = function() {
			var socket = new SockJS('/stomp');
			var stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				var str = "cooks?userID=" + employeeService.getEmployee().userID;
				stompClient.subscribe("/topic/" + str, function(data) {
					var message = data.body;
					req = angular.fromJson(message);
					id = employeeService.getEmployee().userID;
						if (req.status == 'Sent') {
							control.orders.push(req);
							
							$scope.$apply();
							toastr["info"]('You have received a new cooking order.');
						} 
				})
				
				
				
			})
		}
		
		this.setupWebsocket();
	}]);
	
	
	app.controller('DrinkingController', ['$scope', '$http', '$window', 'employeeService', function($scope, $http, $window, employeeService) { 
		var control = this;
		control.orders = [];
		control.items = [];
		
		$scope.$watch('tabCtrl.tab', function(newValue) {
			if (newValue == 5) {
				
				control.loadRequests()
			}
		});
		
		
		control.loadRequests = function() {

			
			$http({
				method: 'GET',
				url: '/reservations/loadBarOrders',
			}).then(function success(response) {
				if (response.data != null) {
					control.orders = response.data;
					
				
				}
			});
		}
		
		
		
		
		control.setupWebsocket = function() {
			var socket = new SockJS('/stomp');
			var stompClient = Stomp.over(socket);
			stompClient.connect({}, function(frame) {
				var str = "bars?userID=" + employeeService.getEmployee().userID;
				stompClient.subscribe("/topic/" + str, function(data) {
					var message = data.body;
					req = angular.fromJson(message);
					id = employeeService.getEmployee().userID;
						if (req.status == 'Sent') {
							control.orders.push(req);
							
							$scope.$apply();
							toastr["info"]('You have received a new drink order.');
						} 
				})
				
				
				
			})
		}
		
		this.setupWebsocket();
	}]);
	
	
})();

