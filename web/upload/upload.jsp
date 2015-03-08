<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>Grupo CYSSA</title>
<link rel="stylesheet" type="text/css" href="plupload/jquery.plupload.queue/css/jquery.plupload.queue.css" type="text/css" media="screen" />
<script type="text/javascript" src="js/jquery-1.6.2.min.js" ></script>
<script type="text/javascript" src="plupload/plupload.full.js"></script>
<script type="text/javascript" src="plupload/jquery.plupload.queue/jquery.plupload.queue.js"></script>
<script type="text/javascript" src="plupload/i18n/cn.js"></script>
<script type="text/javascript">

// Convert divs to queue widgets when the DOM is ready
$(function() {
	function plupload(){
		$("#uploader").pluploadQueue({
        // General settings
        runtimes : 'html5,gears,browserplus,silverlight,flash,html4',
        url : 'FileUpload.action',
        max_file_size : '90mb',
        chunk_size : '10mb',
        unique_names : true,
        // redirect mod
        preinit : attachCallbacks,
        // Resize images on clientside if we can
    //    resize : {width : 320, height : 240, quality : 90},
 
        // Specify what files to browse for
			filters : [
				{title: "Opus DBF files", extensions: "DBF"},
				{title: "Opus FPT files", extensions: "FPT"}
			],
 
        // Flash settings
			flash_swf_url : 'plupload/plupload.flash.swf',
			// Silverlight settings
			silverlight_xap_url : 'plupload/plupload.silverlight.xap'
 
    });
 }
 plupload();
	$('#clear').click(function(){
		plupload();
	});
	
    // Client side form validation
    $('form').submit(function(e) {
        var uploader = $('#uploader').pluploadQueue();
 
        // Files in queue upload them first
        if (uploader.files.length > 0) {
            // When all files are uploaded submit form
            uploader.bind('StateChanged', function() {
                if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
                    $('form')[0].submit();
                }
            });
                 
            uploader.start();
        } else {
            alert('You must queue at least one file.');
        }
 
        return false;
    });
});


// added redirect function after uploaded
function attachCallbacks(uploader) {
uploader.bind('FileUploaded', function(Up, File, Response) {
    if( (uploader.total.uploaded + 1) == uploader.files.length)
         {
        window.location = 'CargarDatosEnAdempiere.action';
          }
    })
}

</script>

</head>
<%@ include file="../main.jsp" %>
<body>
	<div>
		<div style="width: 750px; margin: 0px auto">
			<form id="formId" action="Submit.action" method="post">
				<div id="uploader">
					<p>Su navegador no tiene instalado Flash, Silverlight, Gears, BrowserPlus o carece de soporte HTML5 .</p>
				</div>
				<input type="button" value="Limpiar" id="clear"/>
			</form>
		</div>
	</div>
</body>

</html>