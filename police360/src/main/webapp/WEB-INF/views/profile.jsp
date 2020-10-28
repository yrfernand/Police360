<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import = "java.util.*" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${empty loggedInuser or loggedInuser eq null}">
<c:redirect url="/login?expired=Session expired please login"/>
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8"><meta http-equiv="refresh"  CONTENT="<%= session.getMaxInactiveInterval() %>;URL='${pageContext.request.contextPath}/expired'" />
<!--[if IE]>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<![endif]-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Favicons Icon -->

<title>My Account</title>

<!-- Mobile Specific -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

<!-- CSS Style -->

 <link rel="stylesheet" href="static/assets/css/SideBarMenuStyles.css">
 
 
 
 
 
 
 
<!-- <link rel="stylesheet" type="text/css"href="static/assets/css/ProfileTopBar.css"> -->


<!-- Google Fonts -->

<script src="https://kit.fontawesome.com/5d677237b2.js" crossorigin="anonymous"></script>




</head>

<body>


<div class="wrapper">
<div class="sidebar">
	<h2>Welcome Officer Fernand</h2>
	<ul>
	<li><a href="http://localhost:2000/capstone/project"><i class="fas fa-project-diagram"></i>Projects</a></li>
	<li><a href="#"><i class="fas fa-calendar-week"></i>Events</a></li>
	<li><a href="#"><i class="fas fa-info"></i>Resources</a></li>
	<li><a href="http://localhost:2000/capstone/index"><i class="fas fa-home"></i>Home page</a></li>
	<li><a href="#"><i class="far fa-calendar"></i>Logout</a></li>
	
	</ul>
</div>
	<div class="main_content" style="width: 90%; margin-left: 250px; margin-top: 25px;">
		
	<div style="display: flex; justify-content: flex-end;">
	<img src="static/assets/img/LOGOpolice360.png" style="width: 200px; height: 200px; margin-right: 25px;">
	</div>
		<div class="header"></div>

		
		<div class="info"></div>
		<div style="color: red; margin:auto; text:align-center">
		<c:forEach var="project" items="${allProjects}">
			<h1>Project Name: ${project.pname}</h1>
			<h3>Project Location: ${project.location1}</h3>
			<h3>Project Description: ${project.description}</h3>
			<br>
		</c:forEach>
</div>
<div></div>
</div>
</div>

<div id="parent">
<script src="https://apps.elfsight.com/p/platform.js" defer></script>
<div class="elfsight-app-a6d5abed-7167-42af-9c74-fc6d7942c116"></div>
</div>

</body>
</html>
	


 

 
 