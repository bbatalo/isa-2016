(function() {
	var app = angular.module('employee', ['mwl.calendar', 'ngAnimate', 'ui.bootstrap', 'colorpicker.module']);
	var proba = {};
	var type = 0;
	var viewDate = new Date();
	
	app.service('employeeService', function() {
		var employee = {};
		
		var setEmployee = function(newEmployee) {
			employee = newEmployee;
		}
		
		var getEmployee = function() {
			return employee;
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
		    setEmployeeDetails: setEmployeeDetails
		  };
	});
	
	
	
	app.controller('EmployeeController', ['$rootScope', '$http', '$window', 'employeeService', function($rootScope, $http, $window, employeeService) {
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
				
		this.authorize();
		this.loadUser();
	}]);
	
	app.controller("TabController", ['$scope',function($scope){
		$scope.selected = {};
	    $scope.selected.tab = 7;

	    this.isSet = function(checkTab) {
	      return $scope.selected.tab === checkTab;
	    };
	    
	    $scope.isSet = this.isSet;

	    this.setTab = function(setTab) {
	    	if(proba!=6){
	    		$scope.selected.tab = setTab;
	    	} else {
	    		window.alert("It is necessary that you change your password!");
	    	}
	    };
	    
	    $scope.setTab = this.setTab;
	    
	    $scope.$on('handleBroadcast', function() {
		       $scope.selected.tab = 6;
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
	      {
	        title: 'An event',
	        color: calendarConfig.colorTypes.warning,
	        startsAt: moment().startOf('week').subtract(2, 'days').add(8, 'hours').toDate(),
	        endsAt: moment().startOf('week').add(1, 'week').add(9, 'hours').toDate(),
	        draggable: true,
	        resizable: true,
	        actions: actions
	      }, {
	        title: '<i class="glyphicon glyphicon-asterisk"></i> <span class="text-primary">Another event</span>, with a <i>html</i> title',
	        color: calendarConfig.colorTypes.info,
	        startsAt: moment().subtract(1, 'day').toDate(),
	        endsAt: moment().add(5, 'days').toDate(),
	        draggable: true,
	        resizable: true,
	        actions: actions
	      }, {
	        title: 'This is a really long event title that occurs on every year',
	        color: calendarConfig.colorTypes.important,
	        startsAt: moment().startOf('day').add(7, 'hours').toDate(),
	        endsAt: moment().startOf('day').add(19, 'hours').toDate(),
	        recursOn: 'year',
	        draggable: true,
	        resizable: true,
	        actions: actions
	      }
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
	      alert.show('Clicked', event);
	    };

	    vm.eventEdited = function(event) {
	      alert.show('Edited', event);
	    };

	    vm.eventDeleted = function(event) {
	      alert.show('Deleted', event);
	    };

	    vm.eventTimesChanged = function(event) {
	      alert.show('Dropped or resized', event);
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
	
	
	
})();

