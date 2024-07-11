<html>
	<head>
		<title>Login -JSP</title>
	</head>
	<body>
		<div class="container">
			<h1>Login</h1>
			Hello to Login Page
			<pre>${loginFailed}</pre>
			<form method="post">
				Name: <input type="text" name="name">
				Password: <input type="password" name="password">
				<input type="submit">
			</form>
		</div>
	</body>
</html>