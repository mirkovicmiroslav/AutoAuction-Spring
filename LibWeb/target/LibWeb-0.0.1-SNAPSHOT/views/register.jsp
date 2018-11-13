<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Dashboard</title>

		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Nunito+Sans:700%7CNunito:300,600" rel="stylesheet"> 

		<!-- Bootstrap -->
		<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/> "/>

		<!-- Font Awesome Icon -->
		<link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/> "/>

		<!-- Custom stlylesheet -->
		<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/style.css"/> "/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

    </head>
	<body>

		<!-- Header -->
		<header id="header">
			<!-- Nav -->
			<div id="nav">
				<!-- Main Nav -->
				<div id="nav-fixed">
					<div class="container">
						<!-- logo -->
						<div class="nav-logo">
							<b class="logo">Aukcije automobila</b>
						</div>
						<!-- /logo -->

						<!-- nav -->
						<ul class="nav-menu nav navbar-nav">
							<li><a href="<c:url value="/dashboard"/> ">Pocetna</a></li>
							<li class="cat-1"><a href="<c:url value="/oglas/svi"/> ">Svi oglasi</a></li>
							<li class="cat-2"><a href="<c:url value="/oglas/dodajNovi"/> ">Dodaj oglas</a></li>
						</ul>
						<!-- /nav -->
						
						<!-- log in, log out -->
						<div class="nav-btns">
							<ul class="nav-menu nav navbar-nav">
								<c:if test="${korisnik.uloga.idUloge != 1 && korisnik.uloga.idUloge != 2}">
					 				<li><a href="<c:url value="/register"/> ">Registruj se!</a></li>
					 				<li><a href="<c:url value="/login"/> ">Prijavi se!</a></li>
								</c:if>
								<c:if test="${korisnik.uloga.idUloge == 1 || korisnik.uloga.idUloge == 2}">
									<li><a href="<c:url value="/profil"/> ">${korisnik.korisnickoIme }</a></li>
									<li><a href="<c:url value="/logout"/> ">Izloguj se</a></li>
								</c:if>
							</ul>
						</div>
						<!-- /log in, log out -->
					</div>
				</div>
				<!-- /Main Nav -->
			</div>
			<!-- /Nav -->
		</header>
		<!-- /Header -->
		
		<c:if test="${usernameExists}">
        	<p style="red">Korisnicko ime vec postoji</p>
    	</c:if>
		<!-- section -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				
				<h1 class="well">Registracija</h1>
				<div class="col-lg-12 well">
					<div class="row">
					<spring:form class="login-container" method="post" enctype="multipart/form-data" modelAttribute="korisnik">
       					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
						<div class="col-sm-12">
							<div class="row">
								<div class="col-sm-6 form-group">
									<label>Ime</label>
									 <spring:input type="text" name="ime" path="ime" placeholder="Unesite ime" class="form-control"/>
									 <spring:errors style="red" path="ime" />
								</div>
								<div class="col-sm-6 form-group">
									<label>Prezime</label>
									 <spring:input type="text" name="prezime" path="prezime" placeholder="Unesite prezime" class="form-control"/>
									 <spring:errors path="prezime" />
								</div>
								<div class="col-sm-6 form-group">
									<label>Korisnicko ime</label>
									<spring:input type="text" name="korisnickoIme" path="korisnickoIme" placeholder="Unesite korisnicko ime" class="form-control"/>
									<spring:errors path="korisnickoIme"/>
								</div>
								<div class="col-sm-6 form-group">
									<label>Email</label>
									<spring:input type="text" name="email" path="email" placeholder="Unesite email" class="form-control"/>
									<spring:errors path="email" />
								</div>
								<div class="col-sm-6 form-group">
									<label>Lozinka</label>
									 <spring:input type="password" name="lozinka" path="lozinka" placeholder="Unesite lozinku" class="form-control"/>
									 <spring:errors path="lozinka" />
								</div>	
								<div class="col-sm-6 form-group">
									<label>Ponovljena lozinka</label>
									<input type="password" placeholder="Ponovite lozinku" name="lozinka2" class="form-control">
									<c:if test="${!empty nisuJednake}">
						        		${nisuJednake }
						    		</c:if>
								</div>	
							</div>
							<div class="form-group">
									<label>Slika</label>
									<input type="file" placeholder="Slika" value="Izaberi sliku..." name="file">
							</div>						
							<div class="form-group">
								<label>Kratak opis</label>
								 <spring:textarea placeholder="Kratak opis..." rows="3" name="kratakOpis" path="kratakOpis" class="form-control"/>
							</div>	
								 	
							<input type="submit" value="Registruj se">					
						</div>
					</spring:form>
				</div>
			</div>
			<div class="col-sm-6 form-group">
            	Već imaš nalog?<a href="/LibWeb/login">&ensp;Prijavi se!</a>
           	</div>
		</div>
			
			</div>
			<!-- /container -->
		<!-- /section -->
		
		

		<!-- Footer -->
		<footer id="footer">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-5">
						<div class="footer-widget">
							<div class="footer-logo">
								<a href="index.html" class="logo"><img src="./img/logo.png" alt=""></a>
							</div>
							<div class="footer-copyright">
								<span>&copy; <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | Aukcije automobila
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></span>
							</div>
						</div>
					</div>

				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</footer>
		<!-- /Footer -->

		<!-- jQuery Plugins -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/main.js"></script>

	</body>
</html>
