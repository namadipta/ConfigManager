
$(function() {

	
	var dropDownValue= "";
	var textBoxValue= "";
	$("#searchFilter").on('change', function() {
		dropDownValue= $(this).val() ;
		
		if(dropDownValue == "" || dropDownValue == 'undefined'){
			$('#searchBtn').prop("disabled", true); 
			$('#searchBtn').css("background-color", "#c3c4ca");
			$('#searchBtn').css("background", "#c3c4ca");
			$('#searchBtn').css('cursor', 'default');
		}
		if(textBoxValue != "" && dropDownValue != ""){
			$('#searchBtn').prop("disabled", false); 
			$('#searchBtn').css("background-color", "#7383f3f5");
			$('#searchBtn').css("background", "#7383f3f5");
			$('#searchBtn').css('cursor', 'pointer');
		}
	});
	
	$("#inputBox").on('change keyup paste mouseup', function() {
		textBoxValue= $(this).val();
		if(textBoxValue == "" || textBoxValue == 'undefined'){
			$('#searchBtn').prop("disabled", true); 
			$('#searchBtn').css("background-color", "#c3c4ca");
			$('#searchBtn').css("background", "#c3c4ca");
			$('#searchBtn').css('cursor', 'default');
		}
		if(textBoxValue != "" && dropDownValue != ""){
			$('#searchBtn').prop("disabled", false); 
			$('#searchBtn').css("background-color", "#7383f3f5");
			$('#searchBtn').css("background", "#7383f3f5");
			$('#searchBtn').css('cursor', 'pointer');
		}
		
	});
	
	
	$('.row-select').dataTable().fnDestroy();
	 var oTable = $("#row-select").dataTable();
		$(".dataTables_filter").hide();
		$("#row-select_length").hide();
	    $('#select-all').click(function () {
	        $(":checkbox", oTable.fnGetNodes()).each(function () {
	        	
	        	 if(this.checked) {
	        		 this.checked = false; 
	        	 }
	        	 else {
	        		 this.checked = true;  
	        	 }
	        })
	    });
	
	$(".massScenarioLock").click(function(){
		swal({
			title: "Are you sure to lock all the Scenarios ?",
			text: "No User will be able to modify this !!",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "Yes, lock it !!",
			closeOnConfirm: true
		},  function(){
			
			 var val = [];
			 var i=0;
			 $(":checkbox:checked", oTable.fnGetNodes()).each(function () {
		        		val[i] = $(this).val();
		        		i++;
	        })
			 
			$.ajax({
				url: "/massLockScenario",
				type : 'POST',
				dataType:'json',
				data: {'scenarioIds': val , 'activity': 'lock'},
				success: function(result){
					if(result.responseData != 'undefined' && result.responseData != null && result.responseData === 'success'){
						toastr.success(result.responseData,' Locked SuccessFully !! ',{
							"positionClass": "toast-top-full-width",
							timeOut: 5000,
							"closeButton": true,
							"debug": false,
							"newestOnTop": true,
							"progressBar": true,
							"preventDuplicates": true,
							"onclick": null,
							"showDuration": "300",
							"hideDuration": "1000",
							"extendedTimeOut": "1000",
							"showEasing": "swing",
							"hideEasing": "linear",
							"showMethod": "fadeIn",
							"hideMethod": "fadeOut",
							"tapToDismiss": false

						}) ;
						window.location.reload();
					}else if(result.faults.errorCode !='undefined' && result.faults.errorCode != null){
						toastr.error(result.faults.errorCode,result.faults.errorMsg,{
							"positionClass": "toast-top-full-width",
							timeOut: 5000,
							"closeButton": true,
							"debug": false,
							"newestOnTop": true,
							"progressBar": true,
							"preventDuplicates": true,
							"onclick": null,
							"showDuration": "300",
							"hideDuration": "1000",
							"extendedTimeOut": "1000",
							"showEasing": "swing",
							"hideEasing": "linear",
							"showMethod": "fadeIn",
							"hideMethod": "fadeOut",
							"tapToDismiss": false

						});

					}
				},
				error : function(error){
					toastr.error('Internal System Error','Contact Support Team',{
						"positionClass": "toast-top-full-width",
						timeOut: 5000,
						"closeButton": true,
						"debug": false,
						"newestOnTop": true,
						"progressBar": true,
						"preventDuplicates": true,
						"onclick": null,
						"showDuration": "300",
						"hideDuration": "1000",
						"extendedTimeOut": "1000",
						"showEasing": "swing",
						"hideEasing": "linear",
						"showMethod": "fadeIn",
						"hideMethod": "fadeOut",
						"tapToDismiss": false

					});
				}
			});
		});
	});
	

	$(".massScenarioUnLock").click(function(){
		swal({
			title: "Are you sure to unlock all the Scenarios ?",
			text: "All Users will be able to modify this !!",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "Yes, unlock it !!",
			closeOnConfirm: true
		},  function(){
			
			 var val = [];
			 
			 var i=0;
			 $(":checkbox:checked", oTable.fnGetNodes()).each(function () {
		        		val[i] = $(this).val();
		        		i++;
			 })
			$.ajax({
				url: "/massLockScenario",
				type : 'POST',
				dataType:'json',
				data: {'scenarioIds': val , 'activity': 'unLock'},
				success: function(result){
					if(result.responseData != 'undefined' && result.responseData != null && result.responseData === 'success'){
						toastr.success(result.responseData,' Unlocked SuccessFully !! ',{
							"positionClass": "toast-top-full-width",
							timeOut: 5000,
							"closeButton": true,
							"debug": false,
							"newestOnTop": true,
							"progressBar": true,
							"preventDuplicates": true,
							"onclick": null,
							"showDuration": "300",
							"hideDuration": "1000",
							"extendedTimeOut": "1000",
							"showEasing": "swing",
							"hideEasing": "linear",
							"showMethod": "fadeIn",
							"hideMethod": "fadeOut",
							"tapToDismiss": false

						}) ;
						window.location.reload();
					}else if(result.faults.errorCode !='undefined' && result.faults.errorCode != null){
						toastr.error(result.faults.errorCode,result.faults.errorMsg,{
							"positionClass": "toast-top-full-width",
							timeOut: 5000,
							"closeButton": true,
							"debug": false,
							"newestOnTop": true,
							"progressBar": true,
							"preventDuplicates": true,
							"onclick": null,
							"showDuration": "300",
							"hideDuration": "1000",
							"extendedTimeOut": "1000",
							"showEasing": "swing",
							"hideEasing": "linear",
							"showMethod": "fadeIn",
							"hideMethod": "fadeOut",
							"tapToDismiss": false

						});

					}
				},
				error : function(error){
					toastr.error('Internal System Error','Contact Support Team',{
						"positionClass": "toast-top-full-width",
						timeOut: 5000,
						"closeButton": true,
						"debug": false,
						"newestOnTop": true,
						"progressBar": true,
						"preventDuplicates": true,
						"onclick": null,
						"showDuration": "300",
						"hideDuration": "1000",
						"extendedTimeOut": "1000",
						"showEasing": "swing",
						"hideEasing": "linear",
						"showMethod": "fadeIn",
						"hideMethod": "fadeOut",
						"tapToDismiss": false

					});
				}
			});
		});
	});
});
