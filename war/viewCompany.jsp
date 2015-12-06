<%@ page import="directoryControls.*"%>
<%@ page import="directoryModel.*"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NVTC - Big Data Directory</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/main.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<script type="text/javascript">
	
</script>
</head>



<body>
	<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">NVTC - Big Data Directory</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index.html">Submit Company</a></li>
					<li><a href="browseCompanies.jsp">Browse<span class="sr-only">(current)</span></a></li>
				</ul>

				<form action="/SearchServlet" class="navbar-form navbar-right" role="search" method="post">
					<div class="form-group">
						<input type="text" class="form-control" id="searchBox" name="searchQuery" placeholder="Search the directory"/>
					</div>
					<button type="submit" class="btn btn-default">Search</button>
				</form>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav><!--End NavBar-->
	
	
	<!-- Retrieve object for later displaying of its info -->
	<div class="container"><!-- Holds all the content in this page -->
		<div class="row">
			<div class="col-md-10 col-sm-10 col-xs-12 col-md-offset-1 col-sm-offset-1 "><!-- Limits the  -->
				
						<div class="row">
							<div class="col-md-10 col-sm-10 col-xs-10 col-md-offset-1 col-sm-offset-1 col-xs-offset-1"><!--Keeps input fields 10 columns wide within panel body-->
								<%
									Company c = (Company)request.getAttribute("aCompany");
									PointOfContact poc = c.getPointOfContact();
									Address ad = c.getAddress();
									String pocName = poc.getFirstName() + " " + poc.getLastName();
									String pocEmail = poc.getEmail();
									//get company details
									String companyName = c.getName();
									String website = c.getWebsite();
									String telephone = c.getTelephone();
									String description = c.getDescription().getValue();
									String primaryCategory = c.getPrimaryCategory().getCategoryName();
									String secondaryCategory = c.getSecondaryCategory().getCategoryName();
									String tertiaryCategory = c.getTertiaryCategory().getCategoryName();
									String specialty1 = c.getSpecialty1();
									String specialty2 = c.getSpecialty2();
									String specialty3 = c.getSpecialty3();
									
								%>
								<br/>
								<div class="row">
									<div class="panel panel-default">
          								<div class="panel-body">
											<h1 class="text-center"><%=companyName%></h1>
											<div class="col-md-7 col-sm-12 col-xs-12">
												<div class="panel panel-primary">
													<div class="panel-heading text-center">
													    <h3 class="panel-title"><span class="glyphicon glyphicon-home" aria-hidden="true"></span> Company Information</h3>
													</div>
												   <div class="panel-body text-left">
												   	 <p class="infoGroup"><strong><span class="glyphicon glyphicon-screenshot"></span> Address</strong></p>								  
												     <%=ad.toString() %>
												     <hr/>
												     <p><strong><span class="glyphicon glyphicon-earphone"></span> Telephone: </strong><%=telephone %></p>
												     <p><strong><span class="glyphicon glyphicon-globe"></span> Website: </strong><a class="siteLink" href="<%=website %>"><%=website %></a></p>
												     <hr/>
												     <p class="infoGroup"><strong><span class="glyphicon glyphicon-usd"></span> Primary Source of Revenue: </strong><%=primaryCategory%></p>
												     <p class="infoGroup"><strong><span class="glyphicon glyphicon-usd"></span> Secondary Source of Revenue: </strong><%=primaryCategory%></p>
												     <p class="infoGroup"><strong><span class="glyphicon glyphicon-usd"></span> Tertiary Source of Revenue: </strong><%=primaryCategory%></p>
												     <br/>
												     <p class="infoGroup"><strong><span class="glyphicon glyphicon-info-sign"></span> Specialty 1: </strong><%=specialty1%></p>
												     <p class="infoGroup"><strong><span class="glyphicon glyphicon-info-sign"></span> Specialty 2: </strong><%=specialty2%></p>
												     <p class="infoGroup"><strong><span class="glyphicon glyphicon-info-sign"></span> Specialty 3: </strong><%=specialty3%></p>										     										   
												   </div>
												</div>
											</div>
											
											<div class="col-md-5 col-sm-12 col-xs-12">
												<div class="panel panel-primary">
													<div class="panel-heading text-center">
													    <h3 class="panel-title"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Point Of Contact Information</h3>
													</div>
												   <div class="panel-body text-left">								  
												     <p><strong><span class="glyphicon glyphicon-info-sign"></span> Name: </strong> <%=pocName %></p>
												     <p><strong><span class="glyphicon glyphicon-envelope"></span> Email: </strong> <%=pocEmail %></p>
												   </div>
												</div>
											</div>																		
								
											<div class="row">
												<div class="col-md-12 col-sm-12 col-xs-12">
													<div class="panel panel-primary">
														<div class="panel-heading text-center">
														    <h3 class="panel-title"><span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span> Description</h3>
														</div>
													   <div class="panel-body text-justify">								  
													     <p><%=description%></p>
													   </div>
													</div>
												</div>								
											</div>
							
										</div>
									</div>
								</div>
						</div>
					</div>
		</div>
	  </div>
	</div>
	<hr>
	<footer class="text-center">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<p class="infoGroup">
						<a href="http://www.nvtc.org" class="text-info">Northern Virginia Technology Council</a> | 2214 Rock Hill Road, Suite 300, Herndon, VA 20170 | Phone: 703-904-7878 | Fax: 703-904-8008
					</p>
					<p class="infoGroup">Developed by <a href="http://www.gmu.edu" class="text-info">George Mason University</a> IT 493 Capstone team, December 2015 | Franz Prowant, Luis Velasco, Raj Sheth, Vineet Jindal</p>
				</div>
			</div>
		</div>
	</footer>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../js/jquery-1.11.2.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>