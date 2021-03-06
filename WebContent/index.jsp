<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
import="asst.MapLanguagesToTables
" errorPage="runError.jsp" %>
<!DOCTYPE html>
<html>
<head>
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-180864298-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'UA-180864298-1');
</script>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>One Bible Version</title>
<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
<script type="text/javascript" src="javascript/autosuggest.js"></script>
<script type="text/javascript" src="javascript/bookNames.js"></script>
<!-- jQuery (necessary for Bootstrap’s JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js">
</script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="javascript/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.col-md-1, .col-md-2, .col-md-3, .col-md-4, .col-md-6, .col-md-8 {
color: black;
border: 1px solid black;
}
label { white-space: nowrap }
</style>
</head>
<%
	 String name = request.getServerName();
  String basePath = "http" + "://" + name
		+ ((request.getServerPort() != 80) ? ":" + request.getServerPort() : "")
		+ /* request.getContextPath() + */ "/";
%>
<body>
<p>The companion site 
<a href="<%= basePath + "corpus" %>"><%= basePath + "corpus"%></a> can display
Bible verses from more than 100 different translations in parallel.
This site displays verses from any one of these translations.</p>
<p>Clicking one of the references below takes you to a single page for that
translation.  Each translation page can display English in parallel with
the chosen language to help you teach English using the Word of God.
Many missionaries have found that offering to teach English for free helps
spread the Gospel.</p>
<p>The pages for each translation can also download each Bible as one
searchable .html file which you can print or save and read offline. The
searchable pages have a Go button which shows red until the download finishes,
and then it turns green.</p>
<%= MapLanguagesToTables.getHrefs() %>
</body>
</html>