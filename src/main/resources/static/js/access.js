
$(function() {

	$("#accessRequestJs").jsGrid({
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
                    url: "/fetchAllAccessRequest",
                    dataType: "json"
                }).done(function(response) {
                    d.resolve(response.responseData);
                });
 
                return d.promise();
            },
            
            updateItem: function(updateRequest) { 
            	
            	
            	 $.ajax({
                     url: "/updateAccessRequest",
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
        },
 
        fields: [
        	{ type: "control", deleteButton: false },
            { name: "currentStatus", type: "select", valueField: "Id", textField: "Name",

        		items: [
        	       
        	        { Name: "Approve", Id: 'A' },
        	        { Name: "Reject", Id: 'R' },
        	        { Name: "Pending", Id: 'P' }
        	    ], title: "Action",  sorting: false,width: 120 , validate:"required"},
            { name: "requestedRole", type: "select",valueField: "Id", textField: "Name",
        	    	items: [
            	        
            	        { Name: "ADMIN", Id: 'ADMIN' },
            	        { Name: "USER", Id: 'USER' }
            	    ], title: "Requested Role",  sorting: false,width: 100 , validate:"required"},
            { name: "userName", type: "text", title: "User Name", width: 200 , validate:"required"},
           	{ name: "employeeId", type: "text", title: "Employee Id", width: 150,type: "disabled" },
            { name: "assignedTo", type: "text", title: "Assigned To"  ,type: "disabled", width: 150},
            { name: "lastUpdDate" ,type: "text", title: "Requested Date" ,type: "disabled", width: 150},
            { name: "comments" ,type: "textarea", title: "Notes" ,type: "disabled", width: 200}
           
        ]
    });
	
	$('.ti-plus').hide();
	
	
});




