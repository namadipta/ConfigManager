
$(function() {

	var jsGrid = $("#eventPreConfigJs").jsGrid({
        height: "100%",
        width: "100%",
        filtering: false,
        editing: true,
        inserting: true,
        sorting: true,
        paging: true,
        autoload: true,
        pageSize: 15,
        pageButtonCount: 5,
        controller: {
            loadData: function() {
                var d = $.Deferred();
 
                $.ajax({
                    url: "/fetchEventPreConfigDetails",
                    dataType: "json"
                }).done(function(response) {
                    d.resolve(response.responseData);
                });
 
                return d.promise();
            },
            
            updateItem: function(updateRequest) { 
            	
            	
            	 $.ajax({
                     url: "/updateEventPreConfig",
                     type : 'POST',
                     contentType: "application/json",
                     dataType: "json",
                     data: JSON.stringify(updateRequest)
                 }).done(function(result) {
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
                 });
            },
            
            insertItem: function(insertingClient) {
            	 var d = $.Deferred();
            	$.ajax({
            		url: "/addNewEventPreConfig",
            		type : 'POST',
            		contentType: "application/json",
            		dataType: "json",
            		data: JSON.stringify(insertingClient)
            	}).done(function(result) {
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
            			window.location.reload();
            			
            		}
            	});
            	
            	
            },
            deleteItem: function(deletingClient) {
            	$.ajax({
            		url: "/deleteEventPreConfig",
            		type : 'POST',
            		contentType: "application/json",
            		dataType: "json",
            		data: JSON.stringify(deletingClient)
            	}).done(function(result) {
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
            			window.location.reload();
            		}
            	});
            	
              
            }
        },

        fields: [
        	{ type: "control" },
            { name: "eventType", type: "text", title: "Event Type", width: 60, validate:"required"},
            { name: "scanTypeCd", type: "text", title: "Scan Type Cd", width: 60, validate:"required"},
            { name: "profileFlag", type: "select", valueField: "Id", textField: "Name",items: [{ Name: " ", Id: "" },{ Name: "Y", Id: 'Y' },{ Name: "N", Id: 'N' }], title: "Profile Flag",  sorting: false,width: 50 },
            { name: "revFlag", type: "select", valueField: "Id", textField: "Name",items: [ { Name: " ", Id: '' },{ Name: "Y", Id: 'Y' },{ Name: "N", Id: 'N' }], title: "Rev Flag",  sorting: false,width: 50 },
            { name: "pofFlag", type: "select", valueField: "Id", textField: "Name",items: [ { Name: " ", Id: '' },{ Name: "Y", Id: 'Y' },{ Name: "N", Id: 'N' }], title: "pof Flag",  sorting: false,width: 50 },
            { name: "lparFlag", type: "select", valueField: "Id", textField: "Name",items: [ { Name: " ", Id: '' },{ Name: "Y", Id: 'Y' },{ Name: "N", Id: 'N' }], title: "lpar Flag",  sorting: false,width: 50 },
            { name: "resubFlag", type: "select", valueField: "Id", textField: "Name",items: [ { Name: " ", Id: '' },{ Name: "Y", Id: 'Y' },{ Name: "N", Id: 'N' }], title: "resubFlag",  sorting: false,width: 50 },
            { name: "nedFlag", type: "select", valueField: "Id", textField: "Name",items: [ { Name: " ", Id: '' },{ Name: "Y", Id: 'Y' },{ Name: "N", Id: 'N' }], title: "nedFlag",  sorting: false,width: 50 },
            { name: "comments", type: "text", title: "Comments", width: 100},
            { name: "updatedDate", type: "text", title: "Updated Dt", width: 60 ,type: "disabled"},
            { name: "updatedBy", type: "text", title: "Updated By", width: 50 ,type: "disabled"},


           
        ]
    });
	
	
	
});




