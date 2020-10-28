<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Project</title>
</head>
<style>
	*{margin: 0; padding: 0;}
	body{background: #ecf1f4;font-family: sans-serif;}
	
	
	
	.form-wrap{ width: 320px; background: #3e3d3d; padding:40px 20px; box-sizing: border-box; position: fixed; left: 50%; top: 50%; transform; translate(-50%, -50%)}
	h1{text-align: center; color: #fff; font-weight: normal;margi-bottom: 20px}
	input{ width: 100%; background: none; border: 1px solid #fff; border-radius:3px; padding: 6px 15px;box-sizing: border-box;margin-bottom: 20px;font-size: 14px;}
	input{type="button"}{background:#fff; border:0; cursor: pointer;}
	
	

</style>
<body>


<div class="form-wrap">

<form action="addProject" method="post">

<h1>Create Project</h1>
<input type="text" placeholder="Title" name="title">
<input type="text" placeholder="location" name="location">
<input type="text" placeholder="Description" name="description">
<input type="text" placeholder="Department" name="department">
<button type="submit">SUBMIT</button>
</form>

</div>
</body>
</html>