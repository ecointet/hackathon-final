<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>
	    	Java Demo.
	 </title>
	 <link rel="stylesheet" type="text/css" href="/css/index.css">
</head>
<body>
	<div class = "box_logo">
		<img src= "/images/logo.png" class ="logo"/>
		<img src= "/images/silca.png" class ="logo"/>
	</div>
	<div class ="box_main_part">
	  	<h1>	
	  			Welcome to this Java Web MySQL demo!
	  	</h1>
	  	Before you continue, please create a bank account:
	  	<br/><br/><br/>	
	  		<form action="home" method="post" >
	  			<table>
		  			<tr>
				  		<td>Your Full Name:</td>
				  		<td><input type = "text" name = "name" ></td>
				  	</tr>
				  	<tr>
			  			<td>Your Email:</td>
			  			<td><input type = "text" name = "email"></td>
			  		</tr>
			  		<tr>
			  			<td>Your Password:</td>
			  			<td><input type = "text" name = "pwd"></td>
			  		</tr>
					<tr>
						<td>Your Balance:</td>
						<td><input type = "text" name = "balance"></td>
					</tr>
					<tr>
						<td colspan="2" id="button_row" ><button class ="buttons" >Create a user account</button></td>
					</tr>
				</table>
			</form>
			<br/><br/><br/>	 
  	</div> 	  	
</body>
</html>