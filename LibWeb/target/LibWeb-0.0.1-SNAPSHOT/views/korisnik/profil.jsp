<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Profil</title>

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
							<c:if test="${korisnik.uloga.idUloge == 1}"><li class="cat-1"><a href="<c:url value="/dashboard"/> ">Pocetna</a></li></c:if>
							<c:if test="${korisnik.uloga.idUloge == 2}"><li class="cat-1"><a href="<c:url value="/dashboardAdmin"/> ">Pocetna</a></li></c:if>
							<li class="cat-2"><a href="<c:url value="/oglas/svi"/> ">Svi oglasi</a></li>
							<li class="cat-3"><a href="<c:url value="/oglas/dodajNovi"/> ">Dodaj oglas</a></li>
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

		<!-- section -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				
				<div class="row">
					<div class="col-md-12">
						<div class="section-title">
							<h2>Profil</h2>
						</div>
					</div>
					<!-- post -->
					
					<p>
						<img src="<c:url value="/korisnikSlika/${korisnik.korisnickoIme}"/>" align="left" height="300" width="460"/>
						<br><br><br><br>
							<strong>&emsp;Ime i prezime: ${korisnik.ime} ${korisnik.prezime} <br><br><br>
							&emsp;Korisnicko ime: ${korisnik.korisnickoIme } <br><br><br>
							&emsp;Email: ${korisnik.email } <br><br><br>
							&emsp;Opis: ${korisnik.kratakOpis } <br><br><br>
							
		                        
							</strong>
					</p>
	
					<!-- /post -->
				</div>
				<!-- /row -->
						
			</div>
			<!-- /container -->
		</div>
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
