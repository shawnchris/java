<%@ page import="java.util.*" %>
<!doctype html>
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link rel="apple-touch-icon" href="apple-touch-icon.png">
        <!-- Place favicon.ico in the root directory -->

        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/vendor/modernizr-2.8.3.min.js"></script>
    </head>
    <body>
        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
<%
String c = request.getParameter("candidates");
String q = request.getParameter("questions");
String name = "", url = "";
if (c != null && q != null && c.length() > 0 && q.length() > 0) {
	String[] ca = c.split("\n");
	String[] qa = q.split("\n");
	Random rand = new Random();
	int r1 = rand.nextInt(ca.length);
	int r2 = rand.nextInt(qa.length);
	name = ca[r1];
	url = qa[r2];
	c = ""; q = "";
	for (int i = 0; i < ca.length; i++) {
		if (i == r1) continue;
		c += ca[i];
		if (i != ca.length - 1)
			c += "\n";
	}
	for (int i = 0; i < qa.length; i++) {
		if (i == r2) continue;
		q += qa[i];
		if (i != qa.length - 1)
			q += "\n";
	}
}
else {
	c = ""; q = "";
}
%>
        <!-- Add your site or application content here -->
        <center><h1>
<%
if (name.length() > 0) {
%>
		<font color="red"><%=name%></font><br><a href="<%=url%>" target="_blank"><%=url%></a>
<%
} else {
%>
		<font color="green">Hello Randomizer!</font>
<%
}
%>
		</h1></center>
		<form name="form1" action="index.jsp" method="post" target="_self" accept-charset="UTF-8" autocomplete="off" novalidate>
		<table style="width:100%">
		<tr><td></td></tr>
		<tr>
			<td><center><b>Candidates
			</center></b></td>
			<td><center><b>Questions
			</center></b></td>
		</tr>
		<tr>
			<td align="center">
			<textarea name="candidates" cols="30" rows="15"><%=c%></textarea>
			</td>
			<td align="center">
			<textarea name="questions" cols="80" rows="15"><%=q%></textarea>
			</td>
		</tr>
		<tr>
			<td></td>
			<td align="center">
			<input type="button" onclick="form1.submit()" value="Let's rock!!!">
			</td>
		</tr>
		</table>
		</form>

        <script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/vendor/jquery-1.12.0.min.js"><\/script>')</script>
        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>

        <!-- Google Analytics: change UA-XXXXX-X to be your site's ID. -->
        <script>
            (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
            function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
            e=o.createElement(i);r=o.getElementsByTagName(i)[0];
            e.src='https://www.google-analytics.com/analytics.js';
            r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
            ga('create','UA-XXXXX-X','auto');ga('send','pageview');
        </script>
    </body>
</html>
