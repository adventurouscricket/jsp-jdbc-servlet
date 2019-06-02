<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-web-user"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<!-- Default form register -->
	<form class="text-center border border-light p-5"
		 id="formSignup" method="post">

		<p class="h4 mb-4">Sign up</p>

		<input type="text" id="fullname" name="fullname" class="form-control"
			placeholder="Full Name" />
		<br/>
		<input type="text" id="username"
			name="username" class="form-control mb-4" placeholder="User Name"/>

		<!-- Password -->
		<input type="password" id="password" name="password"
			class="form-control" placeholder="Password"
			aria-describedby="defaultRegisterFormPasswordHelpBlock"> <small
			id="defaultRegisterFormPasswordHelpBlock"
			class="form-text text-muted mb-4"> At least 8 characters and
			1 digit </small>

		<!-- Sign up button -->
		<button class="btn btn-info my-4 btn-block" id="btnSignin">Sign in</button>
	</form>
	
	<script type="text/javascript">
		$('#btnSignin').click(function(e){
			e.preventDefault();
			var data={};
			var formData =$('#formSignup').serializeArray();
			$.each(formData, function(i,v){
				data[""+v.name+""]=v.value;
			});
			addUser(data);
		});
		
		function addUser(data){
			$.ajax ({
				url: '${APIurl}',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(data),
				dataType: 'json',
				success: function(result){
					console.log(result);
				},
				error: function(error){
					console.log(error);
				},
			});
		}
	</script>
</body>
</html>