<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Svi oglasi</title>

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
							<li><a href="<c:url value="/oglas/svi"/> ">Svi oglasi</a></li>
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

		<!-- section -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				
				<div class="row">
					<div class="col-md-12">
						<div class="section-title">
							<h2>Svi oglasi</h2>
						</div>
					</div>
					<!-- post -->
					
					<spring:form action="/LibWeb/oglas/rezultatiPretrage" method="post" modelAttribute="oglasSearchDto">
						<p align="center">
							<select name="izabranBrend">
								<c:forEach var="brend" items="${brendovi }">
									<option value="${brend.idBrenda}">${brend.naziv}</option>
								</c:forEach>
							</select>
							<input type="text" id="naslov" name="naslov" placeholder="Naslov"/>
							<input type="text" id="model" name="model" placeholder="Model"/>
							<input type="text" id="boja" name="boja" placeholder="Boja"/><br><br>
							<input class="search-button" type="submit" value="Pretrazi">
						</p>
					</spring:form>
					
					<form action="/LibWeb/reports/sviOglasi.pdf" method="get">
						<p align="center">Prikaz oglasa po datumu postavljanja(pdf):<br>
							Datum od: <input type="date" name="datumOd"/>
							Datum do: <input type="date" name="datumDo"/>
							<input type="submit" value="Pikazi"/>
						</p>
					</form>
					<br><br>
					
					<c:forEach items="${oglasi }" var="oglas"> 
						<div class="col-md-4">
							<div class="post">
								<a class="post-img" href="<c:url value="/oglas/detaljiOglasa?id_oglas=${oglas.idOgla}"/>"><img src="<c:url value="/oglas/oglasSlika/${oglas.idOgla}"/>" height="180" width="130" alt=""></a>
								<div class="post-body">
									Aktuelna ponuda: <c:if test="${ponuda.ponudaPare == 0.0 }"><strong>${oglas.minPonuda }&nbsp;€</strong></c:if><c:if test="${ponuda.ponudaPare != 0.0 }"><strong>${ponuda.ponudaPare }&nbsp;€</strong></c:if>
									<div class="post-meta">
										<a class="post-category cat-1" href="<c:url value="/oglas/detaljiOglasa?id_oglas=${oglas.idOgla}"/>">${oglas.naslov }</a>
										<span class="post-date"><strong>${oglas.datumVreme }</strong></span>
									</div>
									<h3 class="post-title">${oglas.tekst }</h3>
								</div>
							</div>
						</div>
					</c:forEach>
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
