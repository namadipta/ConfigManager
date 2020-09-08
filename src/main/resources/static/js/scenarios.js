
$(function() {

	$('#event-tab').hide();

	$('.nav-link').click(function(){
		$('#event-tab').show();
		$(".eventtable").prop('title', 'Double click to edit');

	});

	$('#scenarioDesc').keyup(function(e) {
		var txtVal = $(this).val();
		$('#inputDesc').val(txtVal);
		$('#commentDesc').val(txtVal);
	});

	$('.statusNameDropDown').change(function(e) {
		var txtVal = $(this).val();
		$(this).siblings('.statusName').val(txtVal);


	});

	$(".scenarioLock").click(function(){
		swal({
			title: "Are you sure to lock the Scenario ?",
			text: "No User will be able to modify this !!",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "Yes, lock it !!",
			closeOnConfirm: true
		},  function(){
			var scenarioId = $('#scenarioId').text();
			if($('#scenarioId').text() === '' || $('#scenarioId').text() == null){
				scenarioId=$('#scenarioId').val();
			}
			$.ajax({
				url: "/lockScenario",
				type : 'POST',
				dataType:'json',
				data: {'scenarioId': scenarioId.trim()},
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


	$(".scenarioUnLock").click(function(){
		swal({
			title: "Are you sure to unlock the Scenario ?",
			text: "All Users will be able to modify this !!",
			type: "warning",
			showCancelButton: true,
			confirmButtonColor: "#DD6B55",
			confirmButtonText: "Yes, unlock it !!",
			closeOnConfirm: true
		},  function(){
			var scenarioId = $('#scenarioId').text();
			if($('#scenarioId').text() === '' || $('#scenarioId').text() == null){
				scenarioId=$('#scenarioId').val();
			}
			$.ajax({
				url: "/unlockScenario",
				type : 'POST',
				dataType:'json',
				data: {'scenarioId': scenarioId.trim()},
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

	$(".scenarioEdit").click(function(){
		$('#scenarioSave').show();
		$('#shipmentToolBox').hide();
		$('#eventToolBox').hide();
		$('.scenarioDelete').hide();
		$('.scenarioClone').hide();
		$('#scenarioTable :input').prop('disabled', false);
		$('.nav-item').addClass('li-disabled');
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
	});



	$("#saveScenario").click(function(e){	
		var valid = this.form.checkValidity(); 
		if (valid) {
			event.preventDefault();
			e.preventDefault();
			var requestData = $("#updateScenarioDetails").serialize() + '&scenarioId=' + $('#scenarioId').text();
			$.ajax({
				url: "/updateScenarioDetails",
				type : 'POST',
				dataType:'json',
				data: requestData,
				success: function(result){
					if(result.responseData != 'undefined' && result.responseData != null && result.responseData === 'success'){
						$('#scenarioTable :input').prop('disabled', true);
						$('#scenarioSave').hide();
						$('#shipmentToolBox').show();
						$('#eventToolBox').show();
						$('.scenarioDelete').show();
						$('.scenarioClone').show();
						$('.li-disabled').removeClass('li-disabled');
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
	$(".scenarioSave").click(function(e){
		$("#saveScenario").click(); 
	});

	$(".scenarioClone").click(function(e){
		var valid =  this.form.checkValidity(); 
		if (valid) {
			event.preventDefault();
			e.preventDefault();

			swal({
				title: "Are you sure to clone the Scenario??",
				type: "info",
				showCancelButton: true,
				closeOnConfirm: false,
				showLoaderOnConfirm: true,
			},  function(){
				var scenarioId = $('#scenarioId').text();
				if($('#scenarioId').text() === '' || $('#scenarioId').text() == null){
					scenarioId=$('#scenarioId').val();
				}
				$.ajax({
					url: "/cloneScenario",
					type : 'POST',
					dataType:'json',
					data: {'scenarioId': scenarioId.trim()},
					success: function(result){
						if(result.responseData != 'undefined' && result.responseData != null ){
							toastr.success('Scenario Id : '+ result.responseData,' Cloned SuccessFully !! ',{
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
							swal("Cloned !!", "Scenario has been Cloned successfully!!", "success");
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
	});

	$(".scenarioDelete").click(function(e){
		var valid =  this.form.checkValidity(); 
		if (valid) {
			event.preventDefault();
			e.preventDefault();


			swal({
				title: "Are you sure to delete ?",
				text: "You will not be able to recover this !!",
				type: "warning",
				showCancelButton: true,
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "Yes, delete it !!",
				closeOnConfirm: true
			},  function(){
				var scenarioId = $('#scenarioId').text();
				if($('#scenarioId').text() === '' || $('#scenarioId').text() == null){
					scenarioId=$('#scenarioId').val();
				}
				$.ajax({
					url: "/deleteScenario",
					type : 'POST',
					dataType:'json',
					data: {'scenarioId': scenarioId.trim()},
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
							window.location = '/ud/';
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
	});

});


