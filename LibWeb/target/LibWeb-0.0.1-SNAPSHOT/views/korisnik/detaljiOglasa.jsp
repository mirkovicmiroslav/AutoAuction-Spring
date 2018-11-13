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

		<title>Detalji oglasa</title>

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
							<h2>Detalji oglasa</h2>
						</div>
					</div>
					<!-- post -->
					<c:forEach items="${oglasi }" var="oglas"> 
						<div class="col-md-4">
							<div class="post">
								<a class="post-img" href="<c:url value="/oglas/detaljiOglasa?id_oglas=${oglas.idOgla}"/>"><img src="<c:url value="/oglas/oglasSlika/${oglas.idOgla}"/>" height="180" width="130" alt=""></a>
								<div class="post-body">
									<div class="post-meta">
										<a class="post-category cat-1" href="<c:url value="/oglas/detaljiOglasa?id_oglas=${oglas.idOgla}"/>">${oglas.naslov }</a>
										<span class="post-date">${oglas.datumVreme }</span>
									</div>
									<h3 class="post-title">${oglas.tekst }</h3>
								</div>
							</div>
						</div>
					</c:forEach>
					<p>
						<img src="<c:url value="/oglas/oglasSlika/${oglas.idOgla}"/>" align="left" height="400" width="600"/>
						<br><br><br><br><br><br>
							<strong>&ensp;Korisnik: ${oglas.korisnik.ime} ${oglas.korisnik.prezime} <br><br><br>
							&ensp;Naslov: ${oglas.naslov }<br><br><br>
							<c:if test="${!empty ponuda }">
								&ensp;Vreme posljednje ponude:<c:if test="${ponuda.ponudaPare == 0.0 }"> ${oglas.datumVreme }</c:if><c:if test="${ponuda.ponudaPare != 0.0 }"> ${ponuda.datumVreme} </c:if><br><br><br>
							    &ensp;Aktuelna ponuda: <c:if test="${ponuda.ponudaPare == 0.0 }">${oglas.minPonuda }&nbsp;€</c:if><c:if test="${ponuda.ponudaPare != 0.0 }">${ponuda.ponudaPare }&nbsp;€</c:if>
							</c:if>
							    <c:if test="${(korisnik.idKorisnika == oglas.korisnik.idKorisnika && korisnik.uloga.idUloge == 1) || (korisnik.idKorisnika == oglas.korisnik.idKorisnika && korisnik.uloga.idUloge == 2)}">
		                            <c:if test="${oglas.aktivan gt 0}">
		                                 <br><br><a	href="<c:url value="/oglas/prihvatiPonudu/${oglas.idOgla}"/>"><button>Prihvati ponudu</button></a>		                                
		                            </c:if>
		                        </c:if>
		                        
		                         <c:if test="${korisnik.uloga.idUloge == 2}">
		                            <c:if test="${oglas.aktivan gt 0}">
		                                <br><br>
		                                    <a href="<c:url value="/oglas/izbrisiOglas/${oglas.idOgla}"/>"><button>Izbrisi oglas</button></a>
		                                
		                            </c:if>
		                        </c:if>
		                        
		                        <c:if test="${korisnik.idKorisnika != oglas.korisnik.idKorisnika && korisnik.uloga.idUloge != 2}">
		                            <c:if test="${oglas.aktivan gt 0}">
		                                
		                                    <form action="/LibWeb/oglas/dodajPonudu/${oglas.idOgla}" method="post">
		                                    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		
		                                        <c:if test="${ponuda.ponudaPare == 0.0 }">
		                                        	<input name="ponudaPare" placeholder="mininimalno ${oglas.minPonuda }" type="text"/>
		                                        </c:if>
		                                        <c:if test="${ponuda.ponudaPare != 0.0 }">
		                                        	<input name="ponudaPare" placeholder="mininimalno ${ponuda.ponudaPare }" type="text"/>
		                                        </c:if>
		                                        <input type="submit" value="Nova ponuda"/>
											</form>
		                          
		                            </c:if>
		                        </c:if>
		                         <c:if test="${!empty ponudaDodata && !ponudaDodata}">
		                            <p style="color:red"> Morate dodati ponudu vecu od postojece</p>
								</c:if>
							</strong>
					</p>
					<br>
					<table class="table table-striped" >
					  <tbody>
					    <tr>
					      <td>Brend:&emsp;&emsp;&emsp;&emsp; ${oglas.automobil.brend.naziv }</td>
					    </tr>
					    <tr>
					      <td>Model: &emsp;&emsp;&emsp;&emsp;${oglas.automobil.model }</td>
					    </tr>
					    <tr>
					      <td>Boja: &emsp;&emsp;&emsp;&emsp;&ensp;${oglas.automobil.boja }</td>
					    </tr>
					    <tr>
					      <td>Godiste: &emsp;&emsp;&emsp;${oglas.automobil.godiste }</td>
					    </tr>
					    <tr>
					      <td>Kilometraza: &emsp;&nbsp;${oglas.automobil.kilometraza }&nbsp;km</td>
					    </tr>
					    <tr>
					      <td>Tip goriva:&emsp;&emsp; ${oglas.automobil.tipGoriva }</td>
					    </tr>
					    <tr>
					      <td>Snaga: &emsp;&emsp;&emsp;&nbsp; ${oglas.automobil.snaga }&nbsp;KS</td>
					    </tr>
					    <tr>
					      <td>Sedista: &emsp;&emsp;&emsp;${oglas.automobil.sedista }</td>
					    </tr>
					    <tr>
					      <td>Vrata: &emsp;&emsp;&emsp;&emsp;${oglas.automobil.vrata }</td>
					    </tr>
					     <tr>
					      <td>Pogon: &emsp;&emsp;&emsp; ${oglas.automobil.pogon }</td>
					    </tr>
					     <tr>
					      <td>Menjac:&emsp;&emsp;&emsp; ${oglas.automobil.menjac }</td>
					    </tr>
					     <tr>
					      <td>Opis: &emsp;&emsp;&emsp;&emsp;${oglas.tekst }</td><td></td> <td></td><td></td> <td></td><td></td> <td></td><td></td> 
					    </tr>
					  </tbody>
					</table>
					<p align="center">
						<h2>Komentari</h2>    
					    <br>
					    <c:if test="${!empty komentari }">
					        <c:forEach var="komentar" items="${komentari }">
					        
					        	<div class="comments-list">
		                       		<div class="media">
		                            	<p class="pull-right"><small>${komentar.datumVreme }</small></p>
		                          		<div class="media-body">
		                                	<h4 class="media-heading user_name">${komentar.korisnik.korisnickoIme }</h4>
		                              		${komentar.sadrzaj }
		                            	</div>
		                          	</div>
		                       </div>
		                       <hr>
					            <br>
					        </c:forEach>
					    </c:if>
					    <br>
					   
					   	<c:if test="${oglas.aktivan == 1}">
						    <form:form action="/LibWeb/oglas/dodajKomentar/${oglas.idOgla }" method="post" modelAttribute="komentar">
						        <textarea class="form-control" name="sadrzaj" rows="4"></textarea>
						        <input type="submit" value="Komentarisi">
						    </form:form>
					   	</c:if>
					
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
