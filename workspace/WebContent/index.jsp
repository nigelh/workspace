<!DOCTYPE html>
<html lang="en">
<head>
<title>Nigel Hole Workspace - Home</title>
<meta name="description"
	content="Nigel hole Java consultant working within the North West of england" />
<meta name="keywords"
	content="Nigel Hole, mobile development, mobile, Android, jquery, workspace, consultant , Sun certified, contractor, Open source, software, J2EE " />
<meta name="robots" content="index, follow" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link href="templates/md_globalbiz/css/template_css.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="styles/core.css">
<link rel="stylesheet" type="text/css" href="styles/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="styles/jquery-ui.structure.css">
<link rel="stylesheet" type="text/css" href="styles/jquery.popeye.css"
	media="screen" />
<link rel="stylesheet" type="text/css"
	href="styles/jquery.popeye.style.css" media="screen" />

<script type="text/javascript" src="scripts/jquery-1.11.3.js"></script>
<script type="text/javascript" src="scripts/jquery-ui.js"></script>
<script type="text/javascript" src="scripts/jquery.cookie.js"></script>
<script type="text/javascript" src="scripts/jqia2.support.js"></script>
<script src="scripts/angular.min.js"></script>
<script src="scripts/promise-tracker.js"></script>
<script src="scripts/controller/Controllers.js"></script>
<script src="scripts/controller/footballController.js"></script>


<script type="text/javascript" src="https://www.google.com/jsapi"></script>

<script type="text/javascript" src="scripts/jquery.popeye-2.1.min.js"></script>

<script type="text/javascript">
	google.load("feeds", "1");

	var entries;
	var ct = 0;
	function initialize() {

		var feed = new google.feeds.Feed("http://feeds.feedburner.com/jquery/");
		feed.setNumEntries(20);
		feed.load(function(result) {
			if (!result.error) {
				entries = result.feed.entries;
				rotatefeed();
			}

		});
	};

	function rotatefeed() {
		if (entries.length > 0) {
			ct = (ct == entries.length - 1 ? 0 : ct + 1);
			$("#feed").html(entries[ct].title);
			$("#link")
					.html(
							"<a target='_blank' href='"+entries[ct].link+"'>read more...</a>");
			$('#rotate').fadeIn().delay(2000).fadeOut(4000, rotatefeed);
		}
	};
	google.setOnLoadCallback(initialize);
</script>



</head>

<body data-ng-app="Workspace" >
	<!-- Banner -->
	<div class="imagebanner" style="width: 100%;">
		<div id="surround" style="float: right; width: 10%;">

			<div class="newsflash" align="right">
				<div class="moduletable">
					<h3>jQuery RSS Feed</h3>
					<table class="contentpaneopen">
						<tr>
							<td valign="top" colspan="0"><span id="rotate"><span
									id="feed">...loading</span><br /> <span id="link"></span></span></td>
						</tr>
					</table>
				</div>
			</div>


		</div>





	</div>
	<div
		style="float: left; position: relative; top: 30px; background: url('images/nigelhole.jpg'); width: 100%">
	</div>
	<!-- Banner End -->

	<!--  Navigation -->
	<div id="tabset">
		<ul>
			<li><a href="#Home">Home</a></li>
			<li><a href="all-things-java.jsp">All Things Java</a></li>
			<li><a href="#projects">Projects</a></li>
			<li><a href="consultancy.jsp">Consultancy</a></li>
			<li><a href="cv.jsp">Curriculum Vitae</a></li>
			<li><a href="#Register">Register Your Interest</a></li>
			<li><a href="news.jsp">News</a></li>
			<li><a href="#football">Football(SIS)</a></li>
		</ul>
		<div id="projects">
			<div id="project-tabs">
				<ul>
					<li><a href="jobtracker.jsp">JobTracker</a></li>
					<li><a href="jobtracker.jsp">Future Projects</a></li>
				</ul>
			</div>
		</div>
		
		<div id="Home" >
			<%@include file="home.html"%>
		</div>
		<div id="Register" >
			<%@include file="register.html"%>
		</div>
		<div id="football" >
			<%@include file="football.html"%>
		</div>

	</div>





	<!-- Events -->
	<script>
		$(document).ready(function() {
			$("#players-dialog").dialog({
				autoOpen: false
			});
			
			$("#project-tabs").tabs();
			$("#tabset").tabs({
				activate : function(event, ui) {
					if (ui.newTab.index() == 0) {
						$('#Home').show();
					} else {
						$('#Home').hide();
					}
					if (ui.newTab.index() == 5) {
						$('#Register').show();
					} else {
						$('#Register').hide();
					}
					if (ui.newTab.index() == 7) {
						populateTeamsTable();
						$('#football').show();
					} else {
						$('#football').hide();
					}

				}
			});
		});
	</script>

</body>
</html>