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
	  			Do a transaction:
	  	</h1>
	  	<br/><br/><br/>	
	  		<form action="successfully_submitted" method="post" >
	  			<table>
		  			<tr>
				  		<td>Source Account ID:</td>
				  		<td><input type = "text" name = "from_id" required="required"></td>
				  	</tr>
				  	<tr>
			  			<td>Target Account ID:</td>
			  			<td><input type = "text" name = "to_id" required="required"></td>
			  		</tr>
			  		<tr>
			  			<td>Value of this transaction:</td>
			  			<td><input type = "text" name = "amount" required="required"></td>
			  		</tr>	  

					<tr>
						<td colspan="2" id="button_row" ><button class ="buttons" >Submit</button></td>
					</tr>
				</table>		  
			</form>
			<br/><br/><br/>
			
			<a href="/home.jsp"><button class ="buttons" >Back to start</button></a>
  	</div> 	  	
</body>
</html>