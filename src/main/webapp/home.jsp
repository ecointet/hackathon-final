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
	</div>
	<div class ="box_main_part">
	  	<h1>	
	  			Welcome to this Java Web MySQL demo!
	  	</h1>			  
				<a href="/do_transaction.jsp"><button class ="buttons" >Do a transaction</button></a>
			<br/><br/><br/>
			<form action="see_transactions" method="get">
	  				<a href="/see_transactions.jsp"><button class ="buttons" >See all transactions on this server</button></a>
	  		</form>  	
	  		<form action="see_bankaccounts" method="get">
	  				<button class="buttons">See all bank accounts on this server</button>
	  		</form> 
  	</div> 	  	
</body>
</html>