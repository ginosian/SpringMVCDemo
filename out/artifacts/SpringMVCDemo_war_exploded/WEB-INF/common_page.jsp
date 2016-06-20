
<html>
<head>
	<title></title>
</head>
<body>
<p>
	<c:foreach items="${projects}" var="project"> </c:foreach></p>
<table border="0" cellpadding="1" cellspacing="1" style="width: 100%; height: 100%">
	<tbody>
	<tr>
		<td style="text-align: left; vertical-align: top; background-color: #DCEDC8">
			<span style="font-size: 36px;">User Name</span></td>
	</tr>
	<tr>
	</tr>
	</tbody>
</table>
<p>
	<sec:authorize access="hasRole('USER')"> </sec:authorize><c:foreach items="${tasks}" var="task"> </c:foreach><sec:authorize access="hasRole('User')"> </sec:authorize></p>
<table class="table table-hover">
	<thead>
	<tr>
		<th>
			Project name</th>
		<th width="100">
			&nbsp;</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td>
			${task.getName}</td>
		<td>
			${task.getUsername}</td>
		<td>
			<a class="btn btn-success custom-width" href="&lt;c:url value='/edit-task-${task}' /&gt;">edit</a></td>
	</tr>
	</tbody>
</table>
<p>
	&nbsp;</p>
</body>
</html>

