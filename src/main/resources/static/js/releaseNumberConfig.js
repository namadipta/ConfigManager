
$(function() {

	var jsGrid = $("#releaseNumberConfigJs").jsGrid({
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
                    url: "/fetchReleaseNumberDetails",
                    dataType: "json"
                }).done(function(response) {
                    d.resolve(response.responseData);
                });
 
                return d.promise();
            },
            
            updateItem: function(updateRequest) { 
            	
            	
            	 $.ajax({
                     url: "/updateReleaseNumber",
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
            		url: "/addNewReleaseNumber",
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
            		url: "/deleteReleaseNumber",
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
            { name: "parmValue", type: "text", title: "Parm Value", width: 70, validate:"required"},
            { name: "parmDesc", type: "text", title: "Parm Desc", width: 200, validate:"required"},
            { name: "source", type: "select", valueField: "Id", textField: "Name",items: [{ Name: " ", Id: "" },{ Name: "D", Id: 'D' },{ Name: "G", Id: 'G' }], title: "Source",  sorting: false,width: 30 }


           
        ]
    });
	
	
	
});




