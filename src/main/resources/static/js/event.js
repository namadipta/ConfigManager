

$(function() {
	

	$(".eventtable tr").click(function(){
		if($(this).hasClass('highlight')){
			$('.highlight').removeClass('highlight');

		}else{
			$('.highlight').removeClass('highlight');
			$(this).addClass('highlight');

		}

	});
	
	$(".eventClone").click(function(){

		var eventRequest= $(".eventtable .highlight").closest('tr').find('input[type="hidden"]').val();
		if(eventRequest != null && eventRequest != undefined){
			$.ajax({
				url: "/fetchEventDetailsForClone",
				type : 'POST',
				dataType:'html',
				data:{'eventRequest':eventRequest},
				success: function(result){

					var modalHeader='<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style=" float: right;-webkit-appearance: none;padding: 0; cursor: pointer;background: 0 0; border: 0;float: right;font-size: 21px;font-weight: 700;line-height: 1;color: #000;text-shadow: 0 1px 0 #fff;filter: alpha(opacity=20); opacity: .2;">&times;</button>'+
					'<h4 class="modal-title" style=" margin-right: 36%;">Clone Event Details</h4>';
					var modalFooter= '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'+
					'<button type="button" id="cloneEventDetails" class="btn btn-primary">Clone</button>';
					$('#modal-header').empty().append(modalHeader);
					$('#modal-footer').empty().append(modalFooter);
					$('#modal-body').empty().append(result);
					$('#myAnchor')[0].click();

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
		}else{
			toastr.error('Please Select a Event','',{
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

	
	$(".eventAdd").click(function(){
		var eventRequest= $(this).siblings('.addEventInfo').val();
		if(eventRequest != null && eventRequest != undefined){
			addNewEventPopUp(eventRequest,'details');
		}
	});
	
	
	$(".eventDelete").click(function(){
		var eventRequest= $(".eventtable .highlight").closest('tr').find('input[type="hidden"]').val();
		if(eventRequest != null && eventRequest != undefined){
			swal({
				title: "Are you sure to delete ?",
				text: "You will not be able to recover this !!",
				type: "warning",
				showCancelButton: true,
				confirmButtonColor: "#DD6B55",
				confirmButtonText: "Yes, delete it !!",
				closeOnConfirm: true
			},
			function(){
				$.ajax({
					url: "/deleteAnEvent",
					type : 'POST',
					dataType:'html',
					data:{'eventRequest':eventRequest},
					success: function(result){
						$('table tr.highlight').remove();
						swal("Deleted !!", "Event has been deleted successfully!!", "success");

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
		}else{
			toastr.error('Please Select a Event','',{
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
	$(".eventEdit").click(function(){
		var eventRequest= $(".eventtable .highlight").closest('tr').find('input[type="hidden"]').val();
		if(eventRequest != null && eventRequest != undefined){
			$.ajax({
				url: "/fetchEventDetails",
				type : 'POST',
				dataType:'html',
				data:{'eventRequest':eventRequest},
				success: function(result){

					var modalHeader='<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style=" float: right;-webkit-appearance: none;padding: 0; cursor: pointer;background: 0 0; border: 0;float: right;font-size: 21px;font-weight: 700;line-height: 1;color: #000;text-shadow: 0 1px 0 #fff;filter: alpha(opacity=20); opacity: .2;">&times;</button>'+
					'<h4 class="modal-title" style=" margin-right: 36%;">Event Details</h4>';
					var modalFooter= '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'+
					'<button type="button" id="updateEventDetail" class="btn btn-primary">Save</button>';
					$('#modal-header').empty().append(modalHeader);
					$('#modal-footer').empty().append(modalFooter);
					$('#modal-body').empty().append(result);
					$('#myAnchor')[0].click();

				},
				error : function(error){
					alert("Request: "+JSON.stringify(error));
				}
			});
		}else{
			toastr.error('Please Select a Event','',{
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

	$(".eventtable tr").dblclick(function(){

		var eventRequest=$(this).find('input[type="hidden"]').val();

		$.ajax({
			url: "/fetchEventDetails",
			type : 'POST',
			dataType:'html',
			data:{'eventRequest':eventRequest},
			success: function(result){

				var modalHeader='<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style=" float: right;-webkit-appearance: none;padding: 0; cursor: pointer;background: 0 0; border: 0;float: right;font-size: 21px;font-weight: 700;line-height: 1;color: #000;text-shadow: 0 1px 0 #fff;filter: alpha(opacity=20); opacity: .2;">&times;</button>'+
				'<h4 class="modal-title" style=" margin-right: 36%;">Event Details</h4>';
				var modalFooter= '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'+
				'<button type="button" id="updateEventDetail" class="btn btn-primary">Save</button>';
				$('#modal-header').empty().append(modalHeader);
				$('#modal-footer').empty().append(modalFooter);
				$('#modal-body').empty().append(result);
				$('#myAnchor')[0].click();

			},
			error : function(error){
				alert("Request: "+JSON.stringify(error));
			}
		});

	});
	
	$(".addEvent").click(function(){
		var eventRequest=  $('#scenarioId').val() +'###'+  $('#shipmentId').val();
		addNewEventPopUp(eventRequest,'create');
		
	});
	
});


$(document).on('click', '#updateEventDetail', function(e) {
	$.ajax({
		url: "/updateEventDetail",
		type : 'POST',
		dataType:'json',
		data: $("#updateEventDetails").serialize(),
		success: function(result){
			if(result.responseData != 'undefined' && result.responseData != null && result.responseData === 'success'){
				toastr.success(result.responseData,'Event Updated SuccessFully !! ',{
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
			toastr.error('System Internal Error','System Internal Error',{
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
})


$(document).on('click', '#cloneEventDetails', function(e) {
	var result = addOrCloneEvent('cloneButton');
	if(result != ''){
		$('#myAnchor')[0].click();
		var tableData ="<tr> <input type='hidden' name='eventId' value= "+ result.responseData.scenarioId +"###"+ result.responseData.shipmentId +"###"+ result.responseData.eventId +" /> <td > "+ result.responseData.eventDesc +" </td> <td > "+ result.responseData.inputDesc +" </td>  <td > "+ result.responseData.eventCommentDesc +" </td> <td > "+ result.responseData.shipmentStatusName +" </td> </tr>";
		$('#row-select'+ result.responseData.shipmentId +' tbody ').append(tableData);
	}

})



$(document).on('blur', '#eventType', function(e) {
	prepopulateEvent();
})

$(document).on('blur', '#scanTypeCd', function(e) {
	prepopulateEvent();
})
$(document).on('click', '#addNewEvent', function(e) {
	var result = addOrCloneEvent('addNewButton');
	if(result != ''){
		$('#myAnchor')[0].click();
		var tableData ="<tr> <input type='hidden' name='eventId' value= "+ result.responseData.scenarioId +"###"+ result.responseData.shipmentId +"###"+ result.responseData.eventId +" /> <td > "+ result.responseData.eventDesc +" </td> <td > "+ result.responseData.inputDesc +" </td>  <td > "+ result.responseData.eventCommentDesc +" </td> <td > "+ result.responseData.shipmentStatusName +" </td> </tr>";
		$('#row-select'+ result.responseData.shipmentId +' tbody ').append(tableData);
	}
	
})

$(document).on('click', '#addNewEventForCreate', function(e) {
	var result = addOrCloneEvent('addNewButton');
	if(result != ''){
		$('#myAnchor')[0].click();
		var tableData ="<tr> <td > "+ result.responseData.eventDesc +" </td> <td > "+ result.responseData.inputDesc +" </td>  <td > "+ result.responseData.eventCommentDesc +" </td> <td > "+ result.responseData.shipmentStatusName +" </td> </tr>";
		$('#row-select tbody ').append(tableData);
	}
	
})
function prepopulateEvent(){
	var input={"scanTypeCd": $("#scanTypeCd").val(),"eventType" : $('#eventType').val()};
	$.ajax({
		url: "/fetchEventPreConfig",
		type : 'POST',
		contentType: "application/json",
		dataType:'json',
		data: JSON.stringify(input),
		success: function(result){
			if(result.responseData != 'undefined' && result.responseData != null){
				  $('#revFlag').val(result.responseData.revFlag);
				  $('#pofFlag').val(result.responseData.pofFlag);
				  $('#lparFlag').val(result.responseData.lparFlag);
				  $('#resubFlag').val(result.responseData.resubFlag);
				  $('#profileFlag').val(result.responseData.profileFlag);
				  $('#nedFlag').val(result.responseData.nedFlag);
				
			}else{
				  $('#revFlag').val("");
				  $('#pofFlag').val("");
				  $('#lparFlag').val("");
				  $('#resubFlag').val("");
				  $('#profileFlag').val("");
				  $('#nedFlag').val("");
			}
		},
		error : function(error){
			toastr.error('System Internal Error','Please contact Support Team',{
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
			
			  $('#revFlag').val("");
			  $('#pofFlag').val("");
			  $('#lparFlag').val("");
			  $('#resubFlag').val("");
			  $('#profileFlag').val("");
			  $('#nedFlag').val("");
		}
	}); 
}
function addOrCloneEvent(locationOfCall) {  
	var data = '';
	$.ajax({
		url: "/cloneEventDetails",
		type : 'POST',
		dataType:'json',
		async : false,
		data: $("#addNewEventDetails").serialize(),
		success: function(result){
			if(result.responseData != 'undefined' && result.responseData != null){
				if(locationOfCall === 'addNewButton'){
					toastr.success('Event ID - '+ result.responseData.eventId,' Added SuccessFully !! ',{
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
					
				}
				else if(locationOfCall === 'cloneButton'){
					toastr.success('Event ID - '+ result.responseData.eventId,' Cloned SuccessFully !! ',{
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
				}
				data = result;
				
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
			toastr.error('System Internal Error','Please contact Support Team',{
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
	return data;
}


function addNewEventPopUp(eventRequest,locationOfInvocation) {  
	$.ajax({
		url: "/addNewEventPopUp",
		type : 'POST',
		dataType:'html',
		data:{'eventRequest':eventRequest},
		success: function(result){

			var modalHeader='<button type="button" class="close" data-dismiss="modal" aria-hidden="true" style=" float: right;-webkit-appearance: none;padding: 0; cursor: pointer;background: 0 0; border: 0;float: right;font-size: 21px;font-weight: 700;line-height: 1;color: #000;text-shadow: 0 1px 0 #fff;filter: alpha(opacity=20); opacity: .2;">&times;</button>'+
			'<h4 class="modal-title" style=" margin-right: 36%;">Add Event Details</h4>';
			if(locationOfInvocation === 'details'){
				var modalFooter= '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'+
				'<button type="button" id="addNewEvent" class="btn btn-primary">Save</button>';
			}else if(locationOfInvocation === 'create'){
				var modalFooter= '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'+
				'<button type="button" id="addNewEventForCreate" class="btn btn-primary">Save</button>';
			}
			
			$('#modal-header').empty().append(modalHeader);
			$('#modal-footer').empty().append(modalFooter);
			$('#modal-body').empty().append(result);
			$('#myAnchor')[0].click();
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
