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
		var manager = {};
		
		var setRestaurantManager = function(newRestaurantManager) {
			manager = newRestaurantManager;
		}
		
		var getRestaurantManager = function() {
			return manager;
		}
		
		var setRestaurant = function(newRestaurant) {
			restaurant = newRestaurant;
		}
		
		var getRestaurant = function() {
			return restaurant;
		}
		
		var getRestaurantMenu = function(){
			return restaurant.menu;
		}
		
		var getRestaurantDrinksMenu = function(){
			return restaurant.drinksMenu;
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
		    getRestaurantMenu: getRestaurantMenu,
		    getRestaurantDrinksMenu: getRestaurantDrinksMenu,
		    setRestaurantManager: setRestaurantManager,
		    getRestaurantManager: getRestaurantManager,
		    getRestaurantSeating: getRestaurantSeating
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
					restaurantService.setRestaurantManager(response.data);
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
	
	app.controller('DrinksMenuController', ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService) {
		var control = this;
		control.drink = {};
		control.drinks = [];
		control.result = "";
		
		this.register = function(){
			control.drink.drinksMenu = restaurantService.getRestaurantDrinksMenu();
			$http.post('/restmanager/addDrink', this.drink).then(function success(response) {
				control.result = response.data;
				if(control.result === "OK"){
					control.drinks.push(control.drink);
					control.drink = {};
				}
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		};
		
		this.getDrinks = function(){
			$http.post('/restmanager/getDrinks', restaurantService.getRestaurantDrinksMenu()).then(function success(response){
				control.drinks = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
			
		};
		
		$scope.$watch('tab.isSet(4)', function() {
			control.getDrinks();
		});
		
		this.removeDrink = function(drink){
			$http.post('/restmanager/removeDrink', drink).then(function success(response){
				alert(response.data);
				var index = -1;		
				var drinkArr = eval( control.drinks );
				for( var i = 0; i < drinkArr.length; i++ ) {
					if( drinkArr[i].label === drink.label ) {
						index = i;
						break;
					}
				}
				if( index === -1 ) {
					alert( "Something gone wrong" );
				}
				control.drinks.splice( index, 1 );
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		}
	}]);
	

	app.controller('BidsController', ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService){
		var control = this;
		control.drinks = [];
		control.groceries = [];
		control.bid = {};
		control.bid.drinks = [];
		control.bid.groceries = [];
		
		$scope.bidBegin = {
		         value: new Date()
		};
		
		this.getAllDrinks = function(){
			$http.get('/restmanager/getAllDrinks').then(function success(response){
				control.drinks = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		}
		
		this.getAllGroceries = function(){
			$http.get('/restmanager/getAllGroceries').then(function success(response){
				control.groceries = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		}
		
		this.addDrink = function(drink){
			var index = -1;		
			var drinkArr = eval( control.bid.drinks );
			for( var i = 0; i < drinkArr.length; i++ ) {
				if( drinkArr[i].label === drink.label ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				control.bid.drinks.push(drink);
			}
		}
		
		this.addGrocery = function(grocery){
			var index = -1;		
			var groceryArr = eval( control.bid.groceries );
			for( var i = 0; i < groceryArr.length; i++ ) {
				if( groceryArr[i].label === grocery.label ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				control.bid.groceries.push(grocery);
			}
		}
		
		this.removeDrink = function(drink){
			var index = -1;		
			var drinkArr = eval( control.bid.drinks );
			for( var i = 0; i < drinkArr.length; i++ ) {
				if( drinkArr[i].label === drink.label ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				alert( "Something gone wrong" );
			}
			control.bid.drinks.splice( index, 1 );
		}
		
		this.removeGrocery = function(grocery){
			var index = -1;		
			var groceryArr = eval( control.bid.groceries );
			for( var i = 0; i < groceryArr.length; i++ ) {
				if( groceryArr[i].label === grocery.label ) {
					index = i;
					break;
				}
			}
			if( index === -1 ) {
				alert( "Something gone wrong" );
			}
			control.bid.groceries.splice( index, 1 );
		}
		
		this.addBid = function(){
			control.bid.beginning = $scope.bidBegin.value;
			control.bid.end= $scope.bidEnd.value;
			control.bid.manager = restaurantService.getRestaurantManager();
			
			if(control.bid.beginning < control.bid.end)
				if(control.bid.drinks.length !== 0 || control.bid.groceries.length !== 0)
					$http.post('/restmanager/addBid', control.bid).then(function success(response){
						control.bid = {};
						control.bid.drinks = [];
						control.bid.groceries = [];
						alert("Success!");
					}), function error(response){
						control.result = "Unknown error ocurred.";
					}
				else{
					alert('Grocery or drink list is empty.');
				}
			else
				alert('End date needs to be after begin date.');
		}
		
		$scope.$watch('tab.isSet(5)', function() {
			control.getAllDrinks();
			control.getAllGroceries();
		});
	}]);
	
	app.controller('SeatingController', ['$scope', '$http', '$window', 'restaurantService', function($scope, $http, $window, restaurantService) {
		var control = this;
		control.segment = {};
		control.segments = [];
		control.toggleArrange = false;
		control.result = "";
		control.selectedSegment = {};
		control.tables = {};
		
		this.isToggled = function(){
			return control.toggleArrange;
		}
		
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
		
		this.register = function(){
			control.segment.seating = restaurantService.getRestaurantSeating();
			$http.post('/restmanager/addSegment', this.segment).then(function success(response) {
				control.result = response.data;
				if(control.result === "OK"){
					control.segments.push(control.segment);
					control.segment = {};
				}
			}, function error(response) {
				control.result = "Unknown error ocurred."
			});
		};
		
		this.getSegments = function(){
			$http.post('/restmanager/getSegments', restaurantService.getRestaurantSeating()).then(function success(response){
				control.segments = response.data;
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		};
		
		$scope.$watch('tab.isSet(7)', function() {
			control.getSegments();
		});
		
		this.removeSegment = function(segment){
			$http.post('/restmanager/removeSegment', segment).then(function success(response){
				alert(response.data);
				var index = -1;		
				var segmentArr = eval( control.segments );
				for( var i = 0; i < segmentArr.length; i++ ) {
					if( segmentArr[i].label === segment.label ) {
						index = i;
						break;
					}
				}
				if( index === -1 ) {
					alert( "Something gone wrong" );
				}
				control.segments.splice( index, 1 );
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
		}
		
		//Uzimaju se svi stolovi nekog segmenta
		this.arrangeTables = function(segment){
			control.selectedSegment = segment;
			control.tables = control.matrix(segment);
				
			$http.post('/restmanager/getTables', segment).then(function success(response){
				for(it in response.data){
					//Svi stolovi iz segmenta se postavljaju na svoje pozicije
					control.tables[response.data[it].tableRow][response.data[it].tableCol] = response.data[it];
					control.tables[response.data[it].tableRow][response.data[it].tableCol].tableClass = control.setTableClass(response.data[it]);
				}
			}), function error(response){
				control.result = "Unknown error ocurred.";
			}
			
			control.toggleArrange = true;
		}
		
		this.manageTable = function(table){
			//Klikom na sto switchuje se izmedju slobodnog stola i praznine
			//kad ti budes rezervisao sto bice crveni pa necu moci da ih diram
			if(table.status === "empty"){
				table.status = "free";
				table.tableClass = control.setTableClass(table);
				
				var restTable = {};
				restTable.tableCode = table.tableCode;
				restTable.status = table.status;
				restTable.segment = table.segment;
				restTable.tableRow = table.tableRow;
				restTable.tableCol = table.tableCol;
				
				$http.post('/restmanager/addTable', restTable).then(function success(response){
					
					alert(response.data);
				}), function error(response){
					control.result = "Unknown error ocurred.";
				}
			}else if(table.status === "free"){
				table.status = "empty";
				table.tableClass = control.setTableClass(table);
				
				var restTable = {};
				restTable.tableCode = table.tableCode;
				restTable.status = table.status;
				restTable.segment = table.segment;
				restTable.tableRow = table.tableRow;
				restTable.tableCol = table.tableCol;
				
				$http.post('/restmanager/removeTable', restTable).then(function success(response){
					alert(response.data);
				}), function error(response){
					control.result = "Unknown error ocurred.";
				}
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
			
			control.toggleArrange = false;
		}
	}]);
})();