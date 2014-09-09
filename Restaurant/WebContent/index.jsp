<!DOCTYPE html>
<html ng-app="myApp">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Online Reservation System</title>
<link rel="stylesheet" type="text/css"
	href="styles/libs/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="styles/libs/bootstrap-theme.min.css">
<link rel="stylesheet" href="styles/style.css">
</head>

<body ng-controller="MainController">
	<div>
		<h1 class="page-title">Restaurant Reservation</h1>
	</div>
	<section>
		<div class="image">
			<div class="buttons">
				<a ng-click="gotoBottom()" class="btn user">Make Reservation</a> <a
					id="admin_popup" class="btn admin"
					ng-click="popup('1','auto','login','block')">Administrator</a>
			</div>
		</div>
		<div class="popup" ng-style="myStyle">
			<!--Admin Login popup-->
			<div id="login_popup" ng-style="loginPopup">
				<p id="loginError" ng-style="errorMessage">Wrong credentials
				<p>
				<div>
					<label for="email">Login</label> <input type="text" id="email"
						ng-model="admin.login" value="" placeholder="email"
						style="margin: 0 padding:2px" />
				</div>
				<div>
					<label for="pass">Password</label> <input type="password" id="pass"
						ng-model="admin.password" placeholder="password" value="" />
				</div>
				<div style="margin: 0px auto">
					<input type="button" value="Sign Up" ng-click="validate()" /> <a
						id="close_popup" ng-click="popup('0','none','login','none')"
						class="close">close</a>
				</div>

			</div>

			<!--Confirmation number popup-->
			<div id="cnf_popup" ng-style="cnfPopup">
				<div style="text-align: center">
					<span>Please enter the confirmation number</span>
					<hr>
					<input type="number" id="cnf_no" placeholder="Confimation number"
						ng-model="cnfNumber" />
				</div>
				<div>
					<input type="button" value="OK" ng-click="getPerson()"> <a
						class="close" ng-click="popup('0','none','cnf','none')">close</a>
				</div>
			</div>

			<!--Update reservation popup-->
			<div id="update_popup" ng-style="updatePopup">
				<span>Please edit the following details and press Ok</span>
				<hr>
				<div>
					<label for="updateDate">Date</label> <input type="date"
						ng-model="person.date" id="updateDate" placeholder="MM/DD/YYYY">
				</div>
				<div>
					<label for="updateTime">Time</label> <input type="time"
						id="updateTime" ng-model="person.time" placeholder="HH:MM" />
				</div>
				<div>
					<label for="updatePartysize">Party Size</label> <input
						type="number" id="updatePartysize" ng-model="person.size"
						placeholder="# Guests" />
				</div>
				<div>
					<input type="button" value="OK" ng-click="updatePerson()">
					<a class="close" ng-click="popup('0','none','update','none')">close</a>
				</div>
			</div>
			<!-- 			display table info
 -->
			<div id="info_popup" ng-style="infoPopup">
				<div>
					<nav class="left-nav">
						<p>Date:</p>
						<p>Phone:</p>
						<p>Email:</p>
						<p>Event:</p>
					</nav>
					<nav>
						<p>{{info.date}}</p>
						<p>{{info.phone}}</p>
						<p>{{info.email}}</p>
						<p>{{info.event}}</p>
					</nav>
				</div>
				<div style="overflow: hidden">
					<a class="close" ng-click="popup('0','none','info','none')">close</a>
				</div>
			</div>
			<!-- Select Table -->
			<div id="selectTable_popup" ng-style="tablePopup">
				<span>Select a table from the list</span>
				<div class="dropdown">
					<button class="dropdown-toggle" type="button" id="tableList"
						data-toggle="dropdown">
						Tables <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu" aria-labelledby="tableList">
						<li ng-repeat="t in tables" role="presentation"><a
							role="menuitem" tabindex="-1" ng-click="assignTable(t.id)">{{t.id}}</a></li>
					</ul>
				</div>
				<div style="overflow: hidden">
					<a class="close" ng-click="popup('0','none','table','none')">close</a>
				</div>
			</div>
			<!--Edit restaurant popup-->
			<div id="rest_popup" ng-style="restPopup">
				<span>Please edit the following details and press Ok</span>
				<hr>
				<div>
					<label for="updateName">Name</label> <input type="text"
						ng-model="admin.name" id="updateName" placeholder="Name">
				</div>
				<div>
					<label for="updateAdress1">Address1</label> <input type="text"
						ng-model="admin.address1" id="updateAdress1"
						placeholder="Address1">
				</div>
				<div>
					<label for="updateAdress2">Address2</label> <input type="text"
						ng-model="admin.address2" id="updateAdress2"
						placeholder="Address2">
				</div>
				<div>
					<label for="updateState">State</label> <input type="text"
						ng-model="admin.state" id="updateState" placeholder="State">
				</div>
				<div>
					<label for="updateZip">Zip</label> <input type="text"
						ng-model="admin.zip" id="updateZip" placeholder="Zip">
				</div>
				<div>
					<label for="updateWeekDayAm">Mon-Fri:AM</label> <input type="time"
						id="updateWeekDayAm" ng-model="admin.weekdayAm"
						placeholder="HH:MM" />
				</div>
				<div>
					<label for="updateWeekDayPm">Mon-Fri:PM</label> <input type="time"
						id="updateWeekDayPm" ng-model="admin.weekdayPm"
						placeholder="HH:MM" />
				</div>
				<div>
					<label for="updateWeekEndAm">Sat-Sun:AM</label> <input type="time"
						id="updateWeekEndAm" ng-model="admin.weekendAm"
						placeholder="HH:MM" />
				</div>
				<div>
					<label for="updateWeekEndPm">Sat-Sun:PM</label> <input type="time"
						id="updateWeekEndPm" ng-model="admin.weekendPm"
						placeholder="HH:MM" />
				</div>

				<div>
					<input type="button" value="OK" ng-click="updateRst()"> <a
						class="close" ng-click="popup('0','none','rest','none')">close</a>
				</div>
			</div>
			<!--Email popup-->
			<div id="email_popup" ng-style="emailPopup">
				<p id="loginError" ng-style="emailMessage">Wrong credentials
				<p>
				<div>
					<label for="email">Login</label> <input type="email"
						id="email" ng-model="login" value="" placeholder="email"
						style="margin: 0 padding:2px" />
				</div>
				<div style="margin: 0px auto">
					<input type="button" value="Ok" ng-click="getEmail()" /> <a
						id="close_popup" ng-click="targetClose('email')" class="close">close</a>
				</div>

			</div>
			<!-- Password popup -->
			<div id="pass_popup" ng-style="passPopup">
				<p id="loginError" ng-style="passMessage">Passwords don't match!
				</p>
				<div>
					<label for="pwd1">Password</label> <input type="text"
						id="pwd1" ng-model="pass1" value="" placeholder="Password"
						style="margin: 0 padding:2px" />
				</div>
				<div>
					<label for="pwd2">Confirm Password</label> <input type="text"
						id="pwd2" ng-model="pass2" value="" placeholder="Password"
						style="margin: 0 padding:2px" />
				</div>
				<div style="margin: 0px auto">
					<input type="button" value="Ok" ng-click="setPass()" /> <a
						id="close_popup" ng-click="targetClose('pass')" class="close">close</a>
				</div>

			</div>
		</div>
	</section>
	<section id="formSection">
		<form class="form container" name="userForm" ng-submit= "formSubmit(userForm.$valid)" novalidate>
            <h2 class="form_title">Please enter the following details:</h2>
            <div class="row 1">
                <div class="col-xs-6">
                    <label for="reserveFirstName">First Name</label> <input type="text"
                        name="firstName" ng-model="person.firstName" ng-disabled="able"
                        class="form-control" id="reserveFirstName"
                        placeholder="Enter Your First Name" required>
                        <p ng-show="userForm.firstName.$invalid && userForm.firstName.$dirty" class="help-block">First Name is required.</p>
                </div>
                <div class="col-xs-6">
                    <label for="reserveLastName">Last Name</label> <input type="text"
                        name="lastName" ng-model="person.lastName" ng-disabled="able" class="form-control"
                        id="reserveLastName" placeholder="Enter Your Last Name" required>
                        <p ng-show="userForm.lastName.$invalid && userForm.lastName.$dirty" class="help-block">Last Name is required.</p>
                </div>
            </div>
            <div class="row 2">
                <div class="col-xs-6">
                    <label for="reserveEmail">Email</label> <input type="email"
                        name="email" ng-model="person.email" ng-disabled="able" class="form-control"
                        id="reserveEmail" placeholder="Enter email" required>
                        <p ng-show="userForm.email.$invalid && userForm.email.$dirty" class="help-block">Email is required.</p>
                </div>
                <div class="col-xs-6">
                    <label for="reservePhone">Phone Number</label> <input type="tel"
                        name="phone" ng-model="person.phone" ng-disabled="able" class="form-control"
                        id="reservePhone" placeholder="Enter Your Phone Number" required ng-pattern="/^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]\d{3}[\s.-]\d{4}$/">
                        <p ng-show="userForm.phone.$error.required && userForm.phone.$dirty" class="help-block">Phone number is required.</p>
                        <p ng-show="userForm.phone.$error.pattern && userForm.phone.$dirty" class="help-block">Invalid pattern</p>
                </div>
            </div>
            <div class="row 3">
                <div class="col-xs-6">
                    <label for="reserveDate">Date</label> <input type="date"
                        name="date" ng-model="person.date" class="form-control" id="reserveDate"
                        placeholder="MM/DD/YYYY" required>
                        <p ng-show="userForm.date.$invalid && userForm.date.$dirty" class="help-block">Date is required.</p>
                </div>
                <div class="col-xs-6">
                    <label for="reserveTime">Time</label> <input type="time"
                        name="time" ng-model="person.time" class="form-control" id="reserveTime"
                        placeholder="HH:MM" required>
                        <p ng-show="userForm.time.$invalid && userForm.time.$dirty" class="help-block">Time is required.</p>
                </div>
            </div>
            <div class="row 4">
                <div class="col-xs-6">
                    <label for="reserveSize">Party Size</label> <input type="number"
                        name="size" ng-model="person.size" class="form-control" id="reserveSize"
                        placeholder="# Guests" required>
                        <p ng-show="userForm.size.$invalid && userForm.size.$dirty" class="help-block">Party size is required.</p>
                </div>
                <div class="col-xs-6">
                    <label for="reserveEvent">Event (optional)</label> <input
                        type="text" ng-model="person.event" class="form-control"
                        id="reserveEvent" placeholder="ex: Birthday,Graduation...etc">
                </div>

            </div>
            <div class="reserve">
                <button typ="submit" class="btn submit" ng-disabled="userForm.$invalid">Reserve</button>
                <a class="change-rsv" ng-click="popup('1','auto','cnf','block')">Change
                    Existing Reservation</a>
            </div>
        </form>
	</section>
	<section ng-style="adminSection" class="bottom">
		<div class="admin-info container">
			<ul class="nav nav-tabs nav-justified" role="tablist">
				<li><a class="tab-text" href="#tab-reservation"
					ng-click="getAll()" data-toggle="tab">Reservations</a></li>
				<li><a class="tab-text" ng-click="getSeating()"
					href="#tab-seating" data-toggle="tab">Seating Arrangement</a></li>
				<li><a class="tab-text" ng-click="getRst()" href="#tab-profile" data-toggle="tab">Profile
						& Settings</a></li>
				<li><a class="tab-text" ng-click="getAll()"
					href="#tab-contacts" data-toggle="tab">Contacts</a></li>
			</ul>
			<div class="tab-content">
				<div class="content" id="tab-reservation">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>CNF#</th>
								<th>Name</th>
								<th>Date</th>
								<th>Time</th>
								<th>Party Size</th>
								<th>Table #</th>
								<th>Details</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="p in people">
								<td>{{p.id}}</td>
								<td>{{p.firstName}} {{p.lastName}}</td>
								<td>{{p.date}}</td>
								<td>{{p.time}}</td>
								<td>{{p.size}}</td>
								<td>{{p.t_id}}</td>
								<td>
									<button class="btn-primary" ng-click="tableInfo(p)"
										id="table-info" title="Open Details">
										<span class="glyphicon glyphicon-info-sign"></span> <span
											class="btn-text">Table info</span>
									</button>
								</td>
								<td>
									<div class="dropdown">
										<button class="btn-default dropdown-toggle" type="button"
											id="dropdownMenu1" data-toggle="dropdown">
											Table Settings <span class="caret"></span>
										</button>
										<ul class="dropdown-menu" role="menu"
											aria-labelledby="dropdownMenu1">
											<li role="presentation"><a role="menuitem" tabindex="-1"
												ng-click="selectTable(p)">Change/Assign table</a></li>
										</ul>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="content" id="tab-seating">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Table #</th>
								<th>CNF</th>
								<th>Name</th>
								<th>Party Size</th>
								<th>Since</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="s in seating">
								<td>{{s.t_id}}</td>
								<td>{{s.id}}</td>
								<td>{{s.firstName}} {{s.lastName}}</td>
								<td>{{s.size}}</td>
								<td>{{s.time}}</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="content" id="tab-profile">
					<div class="profile-view">
						<ul class="profile-content">
							<li class="list left">
								<ul>
									<li class="name">
										<p class="heading name">Name:</p> <span
										class="restaurant name">{{admin.name}}</span>
									</li>
									<!-- line separator-->
									<hr>
									<li class="address">
										<p class="heading address">Address:</p>
										<address>
											{{admin.address1}} <br>{{admin.address2}} <br>{{admin.state}}
											{{admin.zip}}<br>
										</address>
									</li>
								</ul>
							</li>
							<li class="list right">
								<ul>
									<li class="timings">
										<p class="heading timings">Timings:</p> Mon-Fri:
										{{admin.weekdayAm}} to {{admin.weekdayPm}} <br>Sat-Sun:
										{{admin.weekendAm}} to {{admin.weekendPm}}
									</li>
									<!-- line separator-->
									<hr>
									<li>
										<div class="btn-group">
											<button type="button" class="btn btn-default dropdown-toggle"
												id="auto-assign" data-toggle="dropdown">
												Auto-Assign <span class="caret"></span>
											</button>
											<ul class="dropdown-menu" role="menu">
												<li><a ng-click="setAutoAssign()">On</a></li>
												<li><a ng-click="setAutoAssign()">Off</a></li>
											</ul>
										</div>
									</li>
									<hr>
									<li><a ng-click="popup('1','auto','rest','block')" class="editRestaurant">Edit
											Restaurant Details</a> <a
										ng-click="popup('1','auto','email','block')" class="editPassword"
										style="margin-left: 20%">Edit Password</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
				<div class="content" id="tab-contacts">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>CNF#</th>
								<th>Name</th>
								<th>Email</th>
								<th>phone #</th>
							</tr>
						</thead>
						<tbody>
							<tr ng-repeat="p in people">
								<td>{{p.id}}</td>
								<td>{{p.firstName}} {{p.lastName}}</td>
								<td>{{p.email}}</td>
								<td>{{p.phone}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section>

	<!--JS scripts !-->
	<script type="text/javascript" src="scripts/lib/angular.min.js"></script>
	<!--<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.0-beta.18/angular.min.js"></script>-->
	<script type="text/javascript" src="scripts/lib/jquery-1.11.0.min.js"></script>
	<script type="text/javascript" src="scripts/lib/bootstrap.min.js"></script>
	<script src="scripts/lib/script.js"></script>

</body>

</html>

