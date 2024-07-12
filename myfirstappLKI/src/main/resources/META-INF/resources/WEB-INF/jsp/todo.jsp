<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

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
		<form:input type="hidden" path="username"/>
		<form:input type="hidden" path="done"/>
		<form:input type="hidden" path="id"/>
		<input type="submit" class="btn btn-success"/>
	</form:form>
</div>	


<%@ include file="common/footer.jspf" %>
<script type="text/javascript">
	$('#targetDate').datepicker({
	    format: 'dd.mm.yyyy'
	});
</script>