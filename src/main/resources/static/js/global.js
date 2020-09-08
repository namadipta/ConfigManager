

	
$(function() {

	jQuery.ajaxSetup({
		  beforeSend: function() {
		     $('#wait').show();
		     $('#load').show();
		  },
		  complete: function(){
		     $('#wait').hide();
		     $('#load').hide();
		  },
		  success: function() {}
		});
	
	
	
	var dropDownValue= "";
	var textBoxValue= "";
	$("#smartSearchFilter").on('change', function() {
		dropDownValue= $(this).val() ;
		
		if(dropDownValue == "" || dropDownValue == 'undefined'){
			$('#searchButton').prop("disabled", true); 
			$('#searchButton').css("background-color", "#c3c4ca");
			$('#searchButton').css("background", "#c3c4ca");
			$('#searchButton').css('cursor', 'default');
		}
		if(textBoxValue != "" && dropDownValue != ""){
			$('#searchButton').prop("disabled", false); 
			$('#searchButton').css("background-color", "#7383f3f5");
			$('#searchButton').css("background", "#7383f3f5");
			$('#searchButton').css('cursor', 'pointer');
		}
	});
	
	$("#searchBox").on('change keyup paste mouseup', function() {
		textBoxValue= $(this).val();
		if(textBoxValue == "" || textBoxValue == 'undefined'){
			$('#searchButton').prop("disabled", true); 
			$('#searchButton').css("background-color", "#c3c4ca");
			$('#searchButton').css("background", "#c3c4ca");
			$('#searchButton').css('cursor', 'default');
			
		}
		if(textBoxValue != "" && dropDownValue != ""){
			$('#searchButton').prop("disabled", false); 
			$('#searchButton').css("background-color", "#7383f3f5");
			$('#searchButton').css("background", "#7383f3f5");
			$('#searchButton').css('cursor', 'pointer');
		}
		
	});
	
	var modelAttr = $("#modelAttr").val();
	if(modelAttr != null && modelAttr != '' ){
		toastr.error('',modelAttr,{
			"positionClass": "toast-top-full-width",
			timeOut: 15000,
			"closeButton": true,
			"debug": false,
			"newestOnTop": true,
			"progressBar": true,
			"preventDuplicates": true,
			"onclick": null,
			"showDuration": "600",
			"hideDuration": "1000",
			"extendedTimeOut": "1000",
			"showEasing": "swing",
			"hideEasing": "linear",
			"showMethod": "fadeIn",
			"hideMethod": "fadeOut",
			"tapToDismiss": false

		});
	}
	var tab = $("#tab").val();
	
	$("#"+ tab).css("background", "linear-gradient(to right, #5772FE , #6D85FB)");
	$("#"+ tab).parent().parent().parent().addClass("active open");
	$("#"+ tab).parent().parent().css("display" ,"block");
	
	if(!!window.performance && window.performance.navigation.type === 2)
	{
		window.location.reload();
	}
	var _ctx = $("meta[name='ctx']").attr("content");

	// Prepend context path to all jQuery AJAX requests
	$.ajaxPrefilter(function( options, originalOptions, jqXHR ) {
		if (!options.crossDomain) {
			options.url = _ctx + options.url;
		}
	});
});
