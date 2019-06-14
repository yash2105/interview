<!doctype html>
<html>
<head lang="en">
<meta charset="utf-8">
<title>JSON Upload</title>
<link rel="stylesheet" href="style.css" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- <script type="text/javascript" src="js/script.js"></script> -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">

			<div class="col-md-8">

				<h1>
					<a href="#" target="_blank">
						<img src="logo.png" width="80px" /> JSON FILE UPLOAD
					</a>
				</h1>
				<hr>

				<form id="form" action="ajaxupload.php" method="post" enctype="multipart/form-data">
					<input id="uploadImage" type="file" accept="image/*" name="image" />
					<div id="preview">
						<img src="filed.png" />
					</div>
					<br> <input class="btn btn-success" type="submit" value="Upload">
				</form>

				<div id="err"></div>
				<hr>
			</div>
		</div>
	</div>
</body>
</html>