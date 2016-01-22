<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >


<head>
<script type="text/javascript" src="scripts/jquery-1.11.3.js"></script>
<script type="text/javascript" src="scripts/jquery-ui.js"></script>
<script type="text/javascript" src="scripts/jquery.cookie.js"></script>
<script type="text/javascript" src="scripts/jqia2.support.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">

$("#loading").dialog({
    hide: 'slide',
    show: 'slide',
    autoOpen: false
});


google.load("feeds", "1");


function News() {
  var feed = new google.feeds.Feed("http://feeds.encosia.com/Encosia");
  feed.setNumEntries(20);
  $("#loading").dialog('open');
  feed.load(function(result) { 
	$("#loading").dialog('close');  
    if (!result.error) {
    	
    $('#feedContainer').append("<table width=\"100%\" class='contentpane'><tr><td class='contentheading'>Java & jQuery Technology Headlines</td></tr><tr><td><ul>");
    
    for (var i = 0; i < result.feed.entries.length; i++) {
           var entry = result.feed.entries[i];  
    	   var title = entry.title;  
    	   var link =  entry.link;  
    	   var content = entry.contentSnippet;    
    	     
    	   $('#feedContainer').append("<li><a href='"+link+"' target='_blank'><u>"+title+"</u></a> <br/>"+content+"<br/><br/></li>"); 
      };
      $('#feedContainer').append("</ul></td></tr><tr><td><br/></td></tr></table>");
    }
    });
};

News();

</script>

</head>
<body>
    <div id="loading" >
     <p>Content is loading... Please wait</p>
     </div>
    <div id="feedContainer">
    </div>
</body>


</html>
