<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
	<!-- 
			/META-INF/resources/webjars/bootstrap/5.1.3/css/bootstrap.min.css
			/META-INF/resources/webjars/bootstrap/5.1.3/js/bootstrap.min.js
			/META-INF/resources/webjars/jquery/3.6.0/jquery.min.js
	 -->
	<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
	<link href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" rel="stylesheet" >
		<title>Welcome Todo List</title>
	</head>
	<body>
		<div class="container">
			<h1>Enter Todo Details</h1>
			<!-- modelAttribute fuer "GET" und "POST" -->
			<form:form method="post" modelAttribute="todo">
				<fieldset class="mb-3">
					<form:label path="description">Description</form:label>
					<form:input type="text" path="description" required="required"/>
					<!-- Validation Message. instead of "class" attribute use cssClass, because its bootstrap -->
					<form:errors path="description" cssClass="text-warning"/>
				 </fieldset>
				 
				 <fieldset class="mb-3">
					<form:label path="targetDate">Target Date</form:label>
					<form:input type="text" path="targetDate" required="required"/>
					<form:errors path="targetDate" cssClass="text-warning"/>
				 </fieldset>
				 
				 
				<!-- vervollstaendige TODO, damit es keine NPE gibt -->
				<form:input type="hidden" path="done"/>
				<form:input type="hidden" path="id"/>
				<input type="submit" class="btn btn-success"/>
			</form:form>
		</div>	
		<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
		<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
		<script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
		<script type="text/javascript">
			$('#targetDate').datepicker({
			    format: 'dd.mm.yyyy'
			});
		</script>
		
	</body>
</html>