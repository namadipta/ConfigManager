
$(function() {


	$(".shipmentEdit").click(function(){
		toastr.info('Please click on Save to Proceed','',{
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



		$('.tab-pane.active textarea').prop('disabled', false);
		$('.tab-pane.active :input').prop('disabled', false);
		$('#scenarioToolBox').hide();
		$('#eventToolBox').hide();
		$('.shipmentDelete').hide();
		$('.shipmentClone').hide();
		$('.shipmentAdd').hide();
		$('.shipmentSave').parent().show();
		$('.nav-item').addClass('li-disabled');
	});
	
	
	$(".shipmentSave").click(function(e){
		$("#saveShipmentDetails").click(); 
	});

	
	$("#saveShipmentDetails").click(function(e){
		var valid = this.form.checkValidity(); 
		if (valid) {
			event.preventDefault();
			e.preventDefault();
		var requestData = $("#updateScenarioDetails").serialize() + '&scenarioId=' + $('#scenarioId').text();
		$.ajax({
			url: "/updateShipmentDetails",
			type : 'POST',
			dataType:'json',
			data: requestData,
			success: function(result){
				if(result.responseData != 'undefined' && result.responseData != null && result.responseData === 'success'){
					$('.li-disabled').removeClass('li-disabled');
					$('.shipmentSave').parent().hide();
					$('input').prop('disabled', true);
					$('.tab-pane.active :input').prop('disabled', true);
					$('.tab-pane.active textarea').prop('disabled', true);
					$('#scenarioToolBox').show();
					$('.shipmentDelete').show();
					$('.shipmentClone').show();
					$('.shipmentAdd').show();
					$('#eventToolBox').show();
					toastr.success(result.responseData,' Updated SuccessFully !! ',{
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
		}
	});
	$(".shipmentClone").click(function(){
		cloneShipment("scenarioDetailsPage");
	});
	$(".cloneShipment").click(function(e){
		var valid =  this.form.checkValidity(); 
		if (valid) {
			event.preventDefault();
			e.preventDefault();
			cloneShipment("");
		}
		
	});
	$(".shipmentDelete").click(function(){
		deleteShipment("scenarioDetailsPage");
	});
	$(".deleteShipment").click(function(e){
		var valid =  this.form.checkValidity(); 
		if (valid) {
			event.preventDefault();
			e.preventDefault();
			deleteShipment("");
		}
	});
	$("#addNewShipmentData").click(function(e){	
		var valid = this.form.checkValidity(); 
		if (valid) {
			event.preventDefault();
			$.ajax({
				url: "/createNewShipment",
				type : 'POST',
				dataType:'json',
				data: $('#createNewShipmentRequest').serialize(),
				success: function(result){
					if(result.responseData != 'undefined' && result.responseData != null){
						toastr.success('Shipment Id='+result.responseData,' Shipment added SuccessFully !! ',{
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
						$('#event-tab').show();
						$('#addNewShipmentData').hide();
						$('.tab-content textarea').prop('disabled', true);
						$('.tab-content input').prop('disabled', true);
						$('#shipmentId').val(result.responseData);
						$('#newShipmentAdd').show();
						$('#completed').show();
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
		}  
	});
});

function cloneShipment(locationOfInvocation) { 
	swal({
		title: "Are you sure to Clone ??",
        type: "info",
        showCancelButton: true,
        closeOnConfirm: false,
        showLoaderOnConfirm: true,
	},  function(){
		$('.tab-pane.active textarea').prop('disabled', false);
		$('.tab-pane.active :input').prop('disabled', false);
		var requestData = $("#updateScenarioDetails").serialize();
		if( $('#scenarioId').text() != null && $('#scenarioId').text() != ''){
			requestData=requestData + '&scenarioId=' + $('#scenarioId').text();
		}
		$.ajax({
			url: "/cloneShipment",
			type : 'POST',
			dataType:'json',
			data: requestData,
			success: function(result){
				if(result.responseData != 'undefined' && result.responseData != null && result.responseData === 'success'){
					toastr.success(result.responseData,' Updated SuccessFully !! ',{
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
					swal("Cloned !!", "Shipment has been Cloned successfully!!", "success");
					if(locationOfInvocation === 'scenarioDetailsPage'){
						window.location.reload();
					}
					
				}else if(result.faults.errorCode !='undefined' && result.faults.errorCode != null){
					swal.close();
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
				swal.close();
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
}
function deleteShipment(locationOfInvocation) { 
	swal({
		title: "Are you sure to delete ?",
		text: "You will not be able to recover this !!",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: "#DD6B55",
		confirmButtonText: "Yes, delete it !!",
		closeOnConfirm: true
	},  function(){
		$('.tab-pane.active textarea').prop('disabled', false);
		$('.tab-pane.active :input').prop('disabled', false);
		var requestData = $("#updateScenarioDetails").serialize();
		if( $('#scenarioId').text() != null && $('#scenarioId').text() != ''){
			requestData=requestData + '&scenarioId=' + $('#scenarioId').text();
		}
		$.ajax({
			url: "/deleteShipment",
			type : 'POST',
			dataType:'json',
			data:requestData,
			success: function(result){
				if(result.responseData != 'undefined' && result.responseData != null && result.responseData === 'success'){
					toastr.success(result.responseData,' Deleted SuccessFully !! ',{
						"positionClass": "toast-top-full-width",
						timeOut: 5000,
						"closeButton": true,
						"debug": false,
						"newestOnTop": true,
						"progressBar": true,
						"preventDuplicates": true,
						"onclick":null,
						"showDuration": "300",
						"hideDuration": "1000",
						"extendedTimeOut": "1000",
						"showEasing": "swing",
						"hideEasing": "linear",
						"showMethod": "fadeIn",
						"hideMethod": "fadeOut",
						"tapToDismiss": false

					}) ;
					if(locationOfInvocation === 'scenarioDetailsPage'){
						window.location.reload();
					}
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
}
