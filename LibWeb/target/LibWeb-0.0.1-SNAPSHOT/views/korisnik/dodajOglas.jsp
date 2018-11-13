<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>Dodaj oglas</title>

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
							<li><a href="<c:url value="/oglas/dodajNovi"/> ">Dodaj oglas</a></li>
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
							<h2>Dodaj oglas</h2>
						</div>
					</div>
					<!-- post -->
					<p style="red" align="center">
						<c:if test="${!empty naslovPrazan}">${naslovPrazan }</c:if><br>
						<c:if test="${!empty modelPrazan}">${modelPrazan }</c:if><br>
					</p>
					<br>
					<form:form action="/LibWeb/oglas/dodajNovi" method="post" enctype="multipart/form-data">
							<div class="form-group row">
							
							
							Brend:&nbsp;<select name="brend">
								<c:forEach var="b" items="${brendovi }">
									<option value="${b.idBrenda }">
										${b.naziv }
									</option>
								</c:forEach>
							</select>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
							
							Naslov:&nbsp;<input type="text" placeholder="naslov" name="naslov" />&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
							
							Model:&nbsp;<input type="text" placeholder="npr. A6 1.6 TDI" name="model" /><br><br>
						
							Cena:&nbsp;<input type="text" placeholder="pocetna cena" name="minPonuda"/>&nbsp;€ ;&emsp;&ensp;&nbsp;
							
							Boja:&nbsp;<select class="selectpicker" name="boja">
									  <option>Bela</option>
									  <option>Bez</option>
									  <option>Bordo</option>
									  <option>Braon</option>
									  <option>Crna</option>
									  <option>Crvena</option>
									  <option>Siva</option>
									  <option>Plava</option>
									  <option>Zelena</option>
									  <option>Zuta</option>
									  <option>Narandzasta</option>
									  <option>Ljubicasta</option>
								  </select>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;
							
							Godiste:&nbsp;<select class="selectpicker" name="godiste">
									  <option>2018</option>
									  <option>2017</option>
									  <option>2016</option>
									  <option>2015</option>
									  <option>2014</option>
									  <option>2013</option>
									  <option>2012</option>
									  <option>2011</option>
									  <option>2010</option>
									  <option>2009</option>
									  <option>2008</option>
									  <option>2007</option>
									  <option>2006</option>
									  <option>2005</option>
									  <option>2004</option>
									  <option>2003</option>
									  <option>2002</option>
									  <option>2001</option>
									  <option>1999</option>
									  <option>1998</option>
									  <option>1997</option>
									  <option>1996</option>
									  <option>1995</option>
								  </select><br><br>
	
							Tip goriva:&nbsp;<select class="selectpicker" name="tipGoriva">
									  <option>Dizel</option>
									  <option>Benzin</option>
									  <option>Gas</option>
									  <option>Benzin + gas</option>
									  <option>Dizel + gas</option>
									  <option>Elektricni</option>
								  </select>&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;	
							
							Snaga:&nbsp;<input type="text" placeholder="snaga" name="snaga" />&nbsp;KS &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&ensp;
							
							Kilometraza:&nbsp;<input type="text" placeholder="kilometraza" name="kilometraza" />&nbsp;km<br><br>				
							
							Menjac:&nbsp;<select class="selectpicker" name="menjac">
									  <option>4 brzine</option>
									  <option>5 brzina</option>
									  <option>6 brzina</option>
									  <option>Vise brzina</option>
									  <option>Automatski</option>
									  <option>Poluautomatski</option>    
								  </select>	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;
							
							Pogon:&nbsp;<select class="selectpicker" name="pogon">
									  <option>Prednji</option>
									  <option>Zadnji</option>
									  <option>4x4</option>	 
								  </select>	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
							
							Sedista:&nbsp;<select class="selectpicker" name="sedista">
									  <option>2</option>
									  <option>3</option>
									  <option>4</option>
									  <option>5</option>
									  <option>6</option>
									  <option>7</option>  
								  </select>	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;			
							
							Vrata:&nbsp;<select class="selectpicker" name="vrata">
									  <option>2</option>
									  <option>3</option>
									  <option>4</option>
									  <option>5</option>  
								  </select>	<br><br>		
							
							Slika:&nbsp;<input type="file" name="file"><br>
				
							Opis:&nbsp;<textarea class="form-control" name="tekst" rows="3"></textarea>
						</div>
						<input type="submit" value="DODAJ" />
					</form:form>
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
