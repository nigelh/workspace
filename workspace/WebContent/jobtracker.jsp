<html>
<head>
<style>
.underline {
	text-decoration: underline;
}
</style>
</head>

<body>
	<table class="blog" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="top">
				<div>
					<table class="contentpaneopen">
						<tr>
							<td class="contentheading" width="100%">The JobTracker
								Project</td>
						</tr>
					</table>

					<table class="contentpaneopen">
						<tr>
							<td valign="top" colspan="2">
							    <div style="float:right; padding:10px;">
                                   <img id="jobtracker_image" src="images/show_on_map.png" width="350" height="600" alt="JobTracker" />
                                 </div>
								<div>
									The JobTracker project is a practical exercise in the use and
									deployment of a mobile application based on jQuery Mobile
									technology. The project is based on real life requirements of a
									reporting system centred around a company's job reporting
									system. The system allows a travelling engineer to perform the
									followings tasks: <br />
									<br />
									<div>
										<ul style="padding-left: 60px">
											<li id="log-on">Log-on by entering a user-name and password</li><br/>
											<li id="allocated_jobs">Review a set of allocated jobs.</li><br/>
											<li id="lookup">Lookup and communicate with team members.</li><br/>
											<li id="map_location">View the job's map location.</li><br/>
											<li id="track">Track job progress through the stages of "allocated","started" and "completed".</li><br/>
											<li id="notes">Add notes to the job's progress.</li><br/>
											<li id="signoff">Complete the job where the customer enters a
												signs-off signature</li><br/>
										</ul>
									</div>



								</div> <br />

							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td valign="top"><table width="100%" cellpadding="0"
					cellspacing="0">
					<tr>
						<td valign="top" width="100%">
							<table class="contentpaneopen">
								<tr>
									<td class="contentheading" width="100%">The Technology
										Stack</td>
								</tr>
							</table>

							<table class="contentpaneopen">
								<tr>
									<td valign="top" colspan="2">
										<div>
											The cloud based implementation is purely browser based using
											a combination of both Javascript <a href="http://jquery.com/"
												target="blank"><span class="underline">JQuery</span></a> and
											<a href="http://jquerymobile.com/" target="blank"><span
												class="underline">jQuery Mobile(JQM)</span></a> widgets
											packages. The application will run on any suitable mobile
											phone or tablet device that supports a HTML5 compliant
											browser - such as Opera <a href="http://mobilehtml5.org/"
												target="blank">(<span class="underline">see
													compatibility table</span>)
											</a>. <br /> The mobile devices communicates to a central <a
												href="http://www.mysql.com/" target="blanck"><span
												class="underline">MySQL</span></a> database located on cloud
											based server using a Restful interface implemented using
											Java's <a href="http://jersey.java.net/" target="blank"><span
												class="underline">Jersy JAX-RS package</span></a>. Java's <a
												href="http://www.hibernate.org/" target="blank"><span
												class="underline">Hibernate</span></a> ORM package is utilised
											for database CRUD operations using suitable connection pool.
											The application runs on a <a href="http://tomcat.apache.org/"
												target="blank"><span class="underline">Tomcat</span></a>
											J2EE compliant server (version 7).

										</div> <br /> <br />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td valign="top" width="100%">
							<table class="contentpaneopen">
								<tr>
									<td class="contentheading" width="100%">JobTracker SlideShow</td>
								</tr>
							</table>
							<table class="contentpaneopen">
								<tr>
									<td valign="top" colspan="2">
										<div>
											A <a href="#" id="slideshow"><span class="underline">Slide Show Popup Window</span></a> that demonstrates the various features of the JobTracker application can be viewed here.
											In the case where your browser prohibits pop-ups then please following the following <a target="blank" href="http://nigelhole/jobtracker_slideshow/slideshow.html"><span class="underline">link<span></span></a>.
											The slide show shows the JobTacker application running within the Opera Browser simulator. 
										</div> <br /> <br />
									</td>
								</tr>
							</table>

							
						</td>
					</tr>
					<tr>
						<td valign="top" width="100%">
							<table class="contentpaneopen">
								<tr>
									<td class="contentheading" width="100%">Try it Out for Yourself!</td>
								</tr>
							</table>
							<table class="contentpaneopen">
								<tr>
									<td valign="top" colspan="2">
										<div>
											If you have a suitable mobile device with a HTML5 compatible browser 
											(most devices have) then you can try out JobTracker by opening up your browser and
											point it to the address 
											<a id="jobtracker" href="#" target="blank"><span class="underline">http://nigelhole.com/jobtracker/login.html"</span></a>.
											Since JQM also works well on any desktop browser - then just follow the link.
										</div> <br /> <br />
									</td>
								</tr>
							</table>

							
						</td>
					</tr>
					
				</table></td>
		</tr>
	</table>

	<script type="text/javascript">
	
		var host = window.location.hostname;
		var port = window.location.port;

		$("#jobtracker").attr("href",
				"http://" + host + ":" + port + "/jobtracker/login.html");

		$("#slideshow").click(function() {
			popupwindow();
		});

		function popupwindow(url, title, w, h) {
			var host = window.location.hostname;
			var port = window.location.port;
			var url = "http://" + host + ":" + port
					+ "/jobtracker_slideshow/slideshow.html";
			var title = "JobTracker SlideShow";
			var w = "900px";
			var h = "800px";
			var left = (screen.width / 2) - (w / 2);
			var top = (screen.height / 2) - (h / 2);
			
			return window
					.open(
							url,
							title,
							'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='
									+ w
									+ ', height='
									+ h
									+ ', top='
									+ top
									+ ', left=' + left);
		}
		var image = $("#jobtracker_image");
		$( "#log-on" ).mouseover(function() {
			image.attr("src","images/login.png");
		});
		$( "#allocated_jobs" ).mouseover(function() {
			image.attr("src","images/assigned_job_details.png");
		});
		$( "#lookup" ).mouseover(function() {
			image.attr("src","images/work_contacts.png");
		});
		$( "#map_location" ).mouseover(function() {
			image.attr("src","images/show_on_map.png");
		});
		$( "#track" ).mouseover(function() {
			image.attr("src","images/job_assignments.png");
		});
		$( "#notes" ).mouseover(function() {
			image.attr("src","images/note_entry.png");
		});
		$( "#signoff" ).mouseover(function() {
			image.attr("src","images/sign_off.png");
		});
		
	</script>

</body>

</html>