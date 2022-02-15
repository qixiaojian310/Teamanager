<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC - JSON Request Sample</title>
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
</head>
<body>
	<h2>Enter Employee Details</h2>
	<table>
		<tr>
			<td>Id</td>
			<td><input type="text" id="id"></td>
		</tr>
		<tr>
			<td>First Name</td>
			<td><input type="text" id="firstName"></td>
		</tr>
		<tr>
			<td>Last Name</td>
			<td><input type="text" id="lastName"></td>
		</tr>
		<tr>
			<td>Age</td>
			<td><input type="text" id="age"></td>
		</tr>
		<tr>
			<td>Salary</td>
			<td><input type="text" id="salary"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" id="submit" value="Submit" /></td>
		</tr>
	</table>
	
	<hr/>
	<div id="displayDiv" style="display:none"><h3>JSON Data returned from Server after processing</h3>
		<div id="processedData"></div>
	</div>
<script>
	jQuery(document).ready(function($) {

		$("#submit").click(function(){
			var employeeData = {};
			employeeData["id"] = $("#id").val();
			employeeData["firstName"] = $("#firstName").val();
			employeeData["lastName"] = $("#lastName").val();
			employeeData["age"] = $("#age").val();
			employeeData["salary"] = $("#salary").val();
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "postEmployee",
				data : JSON.stringify(employeeData),
				dataType : 'json',				
				success : function(data) {
					$('#processedData').html(JSON.stringify(data));
					$('#displayDiv').show();
				}
			});
		});

	});
</script>	

</body>
</html>