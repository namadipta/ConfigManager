
$( document ).ready(function() {
	var selectedModuleId = $('.active').find('span').attr('id');
	$('#selectedModule').val(selectedModuleId);
});
$('#selectedProfile').change(function(e) {
	  $('#selectedLabel').prop('selectedIndex',0);
});
$(document).on("change",".compareSource",function(){
	  $("option[value=" + this.value + "]", this)
	  .attr("selected", true).siblings()
	  .removeAttr("selected")
});

$('.compareSource').change(function(e) {
	 $('#sourceSelectedPropVersion').prop('selectedIndex',0);
	 if($('#sourceSelectedModule').val() == null || $('#sourceSelectedModule').val() == ''){
		 return ;
	 }
	 if($('#sourceSelectedProfile').val() == null || $('#sourceSelectedProfile').val() == ''){
		 return ;
	 }
	 if($('#sourceSelectedLabel').val() == null || $('#sourceSelectedLabel').val() == ''){
		 return ;
	 } 
	 e.preventDefault();
	 $.ajax({
	        type: "GET",
	        contentType: "application/json",
	        url: "/fetchpropversion?selectedModule=" + $('#sourceSelectedModule').val() + '&selectedProfile='+$('#sourceSelectedProfile').val() + '&selectedLabel='+$('#sourceSelectedLabel').val() + '&appId='+$('#appId').val(),
	        dataType: 'json',
	        success: function (data) {

	        	$.each(data,function(i,obj)
	                    {
	                     	var div_data="<option value="+obj.id+">"+obj.text+"</option>";
	                   		 $(div_data).appendTo('#sourceSelectedPropVersion'); 
	                    });  
            },
	        error: function (e) {
	           alert(e);

	        }
	    });
	 
});

$('.compareTarget').change(function(e) {
	  $('#targetSelectedPropVersion').prop('selectedIndex',0);
		 if($('#targetSelectedModule').val() == null || $('#targetSelectedModule').val() == ''){
			 return ;
		 }
		 if($('#targetSelectedProfile').val() == null || $('#targetSelectedProfile').val() == ''){
			 return ;
		 }
		 if($('#targetSelectedLabel').val() == null || $('#targetSelectedLabel').val() == ''){
			 return ;
		 } 
	  e.preventDefault();
	  var requestData = $('#compareprops').serialize() + '&query=target';
	  $.ajax({
		  	type: "GET",
	        contentType: "application/json",
	        url: "/fetchpropversion?selectedModule=" + $('#targetSelectedModule').val() + '&selectedProfile='+$('#targetSelectedProfile').val() + '&selectedLabel='+$('#targetSelectedLabel').val() + '&appId='+$('#appId').val(),
	        dataType: 'json',
	        success: function (data) {

	        	$.each(data,function(i,obj)
	                    {
	                     	var div_data="<option value="+obj.id+">"+obj.text+"</option>";
	                   		 $(div_data).appendTo('#targetSelectedPropVersion'); 
	                    });  
            },
	        error: function (e) {
	           alert(e);

	        }
	    });
});

$('#editProp').click(function(e) {
	$('.edittool').show();
	$('.edittool').css("cursor", "pointer");
	$(this).hide();
	$('#saveProp').toggle();
});

$(".fa-minus-circle").click(function(e) {
	  $(this).closest('tr').remove();
});

$("#addProperties").click(function(e) {
	var nextRowId = parseInt($('#counter').val())+1 ;
	var $clonedRow = $('tbody tr:first').clone();
    $clonedRow.find('td').each(function(){
        var el = $(this).find(':first-child');
        var id = el.attr('id') || null;
        if(id != null && id.indexOf(".id") != -1) {
            var i = id.substr(id.length-4);
            var prefix = id.substr(0, (id.length-4));
            el.attr('id', prefix+"["+nextRowId+"].id");
            el.attr('name', prefix+"["+nextRowId+"].id");
        }
   	   if(id != null && id.indexOf(".text") != -1) {
           var i = id.substr(id.length-6);
           var prefix = id.substr(0, (id.length-6));
           el.attr('id', prefix+"["+nextRowId+"].text");
           el.attr('name', prefix+"["+nextRowId+"].text");
       }
   	  if(id != null && id.indexOf(".hide1") != -1) {
           var i = id.substr(id.length-6);
           var prefix = id.substr(0, (id.length-6));
           el.attr('id', prefix+"["+nextRowId+"].hide");
           el.attr('name', prefix+"["+nextRowId+"].hide");
      }
    });
	$clonedRow.find('textarea').val('');
	$('#propDetailsTable tbody').append($clonedRow); 
	$('#counter').val(nextRowId);
});
function myFunction() {
	  // Declare variables
	  var input, filter, table, tr, td, td1, i, txtValue;
	  input = document.getElementById("myInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("propDetailsTable");
	  tr = table.getElementsByTagName("tr");

	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[0];
	    td1 = tr[i].getElementsByTagName("td")[1];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    } 
	    if (td1) {
		      txtValue = td1.textContent || td1.innerText;
		      if (txtValue.toUpperCase().indexOf(filter) > -1) {
		        tr[i].style.display = "";
		      } else {
		        tr[i].style.display = "none";
		      }
		 }
	  }
	}

function searchUser() {
	  // Declare variables
	  var input, filter, table, tr, td, td1, i, txtValue;
	  input = document.getElementById("myInput");
	  filter = input.value.toUpperCase();
	  table = document.getElementById("userDetailsTable");
	  tr = table.getElementsByTagName("tr");

	  // Loop through all table rows, and hide those who don't match the search query
	  for (i = 0; i < tr.length; i++) {
	    td = tr[i].getElementsByTagName("td")[0];
	    if (td) {
	      txtValue = td.textContent || td.innerText;
	      if (txtValue.toUpperCase().indexOf(filter) > -1) {
	        tr[i].style.display = "";
	      } else {
	        tr[i].style.display = "none";
	      }
	    } 
	  }
	}