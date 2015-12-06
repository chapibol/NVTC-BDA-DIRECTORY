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

<script type = "text/javascript">

 			
</script>
</head>



<body>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
  <div class="container-fluid"> 
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
      <a class="navbar-brand" href="index.html">NVTC - Big Data Directory</a> </div>
    
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="index.html">Submit Company</a> </li>
        <li class="active"><a href="browseCompanies.jsp">Browse<span class="sr-only">(current)</span></a> </li> 
      </ul>     
      
      <form action="/SearchServlet" class="navbar-form navbar-right" role="search" method="post">
        <div class="form-group">
          <input type="text" class="form-control" id="searchBox" name="searchQuery" placeholder="Search the directory">
        </div>
        <button type="submit" class="btn btn-default">Search</button>
      </form>
    </div>
    <!-- /.navbar-collapse --> 
  </div>
  <!-- /.container-fluid --> 
</nav>
<!--End NavBar-->
<%
		//retrieve results from servlet
		List<Company> allCompanies = Utility.getAllCompanies();
  		int numberOfCompanies = 0;
%>


<div class="container"><!-- Holds all the content in this page -->
  <div class="row">
    <div class="col-md-10 col-sm-10 col-xs-12 col-md-offset-1 col-sm-offset-1"><!-- Limits the  -->
    	<div class="panel panel-default">
          <div class="panel-body">
           <div class="row">
            <div class="col-md-10 col-sm-10 col-xs-10 col-md-offset-1 col-sm-offset-1"><!--Keeps input fields 10 columns wide within panel body-->           

				
				<div class="row">
							<div class="col-md-10 col-sm-10 col-xs-10 col-md-offset-1 col-sm-offset-1 col-xs-offset-1"><!--Keeps input fields 10 columns wide within panel body-->
							<%
							if (allCompanies.isEmpty() || allCompanies == null) {//if no reults display error message
							%>
									<div class="col-md-12 col-sm-12 col-xs-12 ">
										<div class="alert alert-danger text-center" role="alert">
											<p><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
									  		<span class="sr-only">Error:</span>No results found</p>										
										</div>																		
									</div>
							<%
							}else{
								numberOfCompanies = allCompanies.size();
								%>
								<div class="row">
									<div class="col-md-12 col-sm-12 col-xs-12 ">
										<p class="text-muted" >Companies in the Directory <%=numberOfCompanies %></p>										
									</div>
								</div>
								<%
								for(Company c: allCompanies){
									long id = c.getKey().getId();
									String companyName = c.getName();
									String descriptionSnippet = Utility.toSnippet(c.getDescription().getValue());
									String website = c.getWebsite();
							%>	
								<div class="row">
									<div class="col-md-12 col-sm-12 col-xs-12">
										<h3 class="searchHeading">
											<strong><a href="/RetrieveCompanyServlet?cId=<%=id%>"><%=companyName%></a></strong>
										</h3>
										<small><a class="searchUrl" href=<%=website%>><%=website%></a></small><br/>
										<p><%=descriptionSnippet%><p>
									</div>								
								</div>
								<br/>
								<br/>
							<%
								}
							}	
							%>
								
							</div><!--End of column that controls input size col-md-10 ...-->
						</div>        
		    </div><!--End of column that controls input size col-md-10 ...-->
		      </div><!--row div within panel-body-->
            
          </div><!--end panel body-->
        </div><!--End Panel Default div-->
      </div><!--End of 10 column div with 1 offset-->
    </div><!--End Row within container(after navbar)-->
  </div><!--End container that holds all contents in between navbar and footer-->
<hr>
<footer class="text-center">
  <div class="container">
    <div class="row">
      <div class="col-md-12 col-sm-12 col-xs-12">
       <p class="infoGroup">
						<a href="http://www.nvtc.org" class="siteLink">Northern Virginia Technology Council</a> | 2214 Rock Hill Road, Suite 300, Herndon, VA 20170 | Phone: 703-904-7878 | Fax: 703-904-8008
					</p>
					<p class="infoGroup">Developed by <a href="http://www.gmu.edu" class="siteLink">George Mason University</a> IT 493 Capstone team, December 2015 | Franz Prowant, Luis Velasco, Raj Sheth, Vineet Jindal</p>
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