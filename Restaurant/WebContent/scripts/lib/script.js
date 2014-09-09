var myApp = angular.module('myApp', []);
myApp.controller('MainController', ['$scope', '$location', '$anchorScroll', '$http', '$rootScope',
                                    function($scope, $location, $anchorScroll, $http, $rootScope) {

	$scope.gotoBottom = function() {
		// set the location.hash to the id of
		// the element you wish to scroll to.
		console.log('button pressed');
		$location.hash('formSection');

		// call $anchorScroll()
		$anchorScroll();
	};

	$scope.popup = function(opacity, auto, element, display) {
		$scope.myStyle = {
				'opacity': opacity,
				'pointer-events': auto
		};
		switch (element) {
		case 'login':
			$scope.loginPopup = {
				'display': display
		};
			break;
		case 'cnf':
			$scope.cnfPopup = {
				'display': display
		};
			break;
		case 'update':
			$scope.updatePopup =  {
				'display': display
		};
			break;
		case 'info':
			$scope.infoPopup =  {
				'display': display
		};
			break;
		case 'table':
			$scope.tablePopup =  {
				'display': display
		};
			break;
		case 'rest':
			$scope.restPopup =  {
				'display': display
		};
			break;
		case 'email':
			$scope.emailPopup =  {
				'display': display
		};
			break;
		case 'pass':
			$scope.passPopup =  {
				'display': display
		};
			break;
		}
		
	};
	$scope.loginAction = function(message, section){
		$scope.errorMessage={
				'display':message
			};
		$scope.adminSection={
				'display':section
		};
	}
	
	$scope.targetClose = function(target){
		switch(target){
		case 'email':$scope.emailMessage={
				'display': 'none'
				};
			$scope.popup('0','none','email','none');
		break;
		case 'pass':$scope.passMessage={
				'display': 'none'
				}
			$scope.popup('0','none','pass','none');
		break;
		}
	}
	
	$scope.validate = function(){
		$http({
			method: 'POST',
			url: 'rest/admin/validate',
			data: $scope.admin
		})
		.success(function(data, status,headers,config){
				
				switch(data.data){
				case 'true':
					$scope.popup('0','none','login','0');
					$scope.loginAction('none','block');
					break;
				case 'false':
					console.log("wrong credentials");
					$scope.loginAction('block','none');
					break;
				}
		}).error(function(){
			console.log("error fetching tables");
		});
		$scope.admin = null;
	}
	$scope.tableInfo = function(person){
		//console.log("button pressed");
		$scope.info = person;
		$scope.popup('1','auto','info','block');
	}
	
	$scope.selectTable = function(person){
		$scope.person = person;
		$http({
			method: 'GET',
			url: 'rest/table/available'
		})
		.success(function(data, status,headers,config){
			if(data != null){
				$scope.tables = data.data;
				console.log($scope.tables);
				$scope.popup('1','auto','table','block');
			}
			
		}).error(function(){
			console.log("error fetching tables");
		});
	}
	
	$scope.assignTable = function(table_id){
		$http({
			method: 'POST',
			url: 'rest/people/assign/' + $scope.person.id + "/" + table_id
			//data: $scope.person
		}).success(function(data) {
			console.log(data.data);
			$scope.popup('0','none','table','none');
			$scope.updateTable(table_id); 
		}).error(function() {
			/* Act on the event */
			console.log("unable to assign table to reservation " + $scope);
		});
		$scope.person = null;
	}
	$scope.setAutoAssign = function(bool){
		$scope.autoAssign = !$scope.autoAssign;
		console.log($scope.autoAssign);
	}
	
	//console.log($scope.person);
	
	$scope.formSubmit = function(valid) {
		if(valid){
		$scope.person.t_id = 0;
		if ($rootScope.autoAssign) {
			$http({
				method: 'GET',
				url: 'rest/table/get'

			})
			.success(function(data, status, headers, config) {
				if (data !== null) {
					console.log(data.data);
					$scope.person.t_id = data.data.id;
					$scope.addPerson();
				} else {
					console.log("no table available");
				}
			}).error(function() {
				/* Act on the event */
				console.log("error in getting table");
			});
		}
		else{
			$scope.addPerson();
		}
	}
		
	}

	$scope.addPerson = function() {
		// add person to person table 
		console.log($scope.person);
		$http({
			method: 'POST',
			url: 'rest/people/add',
			data: $scope.person
		}).success(function(data) {
			$scope.person = data.data;
			if($scope.person.t_id !== 0)
				$scope.updateTable($scope.person.t_id);
			console.log($scope.person);
			$scope.person = null;
		}).error(function() {
			/* Act on the event */
			console.log("unable to add person");
		});
	}

	$scope.getPerson = function(){
		$http({
			method: 'GET',
			url: 'rest/people/get/' + $scope.cnfNumber
		})
		.success(function(data, status,headers,config){
			if(data != null){
				$scope.person = data.data;
				console.log($scope.person);
				$scope.cnfPopup = {
						'display': 'none'
				};
				$scope.popup('1','auto','update','block');
			}
		}).error(function(){
			console.log("error updating table");
		});
		$scope.cnfNumber = null;
	}

	$scope.updatePerson = function(){
		$http({
			method: 'POST',
			url: 'rest/people/update',
			data: $scope.person
		}).success(function(data) {
			$scope.person = data.data;
			console.log('update successful' + $scope.person);
			$scope.popup('0','none','update','none');
			$scope.person = null;
		}).error(function() {
			/* Act on the event */
			console.log("unable to update person");
		});
	}
	$scope.updateTable = function(id){
		$http({
			method: 'POST',
			url: 'rest/table/update/' + id,
		}).success(function(data) {
			console.log('Table update successful' + data.data);
		}).error(function() {
			/* Act on the event */
			console.log("unable to update table");
		});
	}

	$scope.getSeating = function(){
		$http({
			method: 'GET',
			url: 'rest/people/seating',
		})
		.success(function(data, status,headers,config){
			if(data != null){
				$scope.seating = data.data;
				console.log($scope.seating);
			}
		}).error(function(){
			console.log("error getting seating info");
		});
	}

	$scope.getAll = function(){
		$http({
			method: 'GET',
			url: 'rest/people/all'
		})
		.success(function(data, status,headers,config){
			if(data != null){
				$scope.people = data.data;
			}
		}).error(function(){
			console.log("error getting people info");
		});
	}
	
	$scope.getRst = function(){
		$http({
			method: 'GET',
			url: 'rest/admin/get'
		})
		.success(function(data, status,headers,config){
					$scope.admin = data.data;
		}).error(function(){
			console.log("error getting people info");
		});
	}
	
	
	$scope.updateRst = function(){
		$http({
			method: 'POST',
			url: 'rest/admin/update',
			data: $scope.admin
				
		})
		.success(function(data, status,headers,config){
			$scope.popup('0','none','rest','none');
		}).error(function(){
			console.log("error getting people info");
		});
	}
	
	$scope.getEmail = function(){
		$http({
			method: 'GET',
			url: 'rest/admin/get/' + $scope.login
		})
		.success(function(data, status,headers,config){
			console.log(data);
			console.log($scope.login);
			if(data.data==="true"){
					$scope.popup('0','none','email','none');
					$scope.popup('1','auto','pass','block');
			}
			else{
				$scope.emailMessage={
						'display': 'block'
						}
			}
		}).error(function(){
			console.log("error getting people info");
		});
		$scope.login = null;
	}
	
	$scope.setPass = function(){
		console.log($scope.pass1);
		console.log($scope.pass2);
		if($scope.pass1===$scope.pass2){
		$http({
			method: 'POST',
			url: 'rest/admin/update/pwd',
			data: $scope.pass1
		})
		.success(function(data, status,headers,config){
					$scope.popup('0','none','pass','none');
				
		}).error(function(){
			console.log("error update password");
		});
		}
		else{
			$scope.passMessage = {
					'display':'block'
			}
		}
		$scope.pass1 = null;
		$scope.pass2 = null
	}
//	end of function
}
]);
