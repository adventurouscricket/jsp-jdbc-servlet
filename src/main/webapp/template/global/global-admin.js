//when web is loaded that everything is start in here 
$(document).ready(function() {
	enableOrDisableBtnDelete();
	autoCheckAllChild();
	autoCheckParent();
});

function enableOrDisableBtnDelete(){
	$('input[type=checkbox]').click(function (){
		if($('input[type=checkbox]:checked').length > 0){
			$('#btnDelete').prop('disabled', false);
		} else {
			$('#btnDelete').prop('disabled', true);
		}
	});	
}

function autoCheckAllChild(){
	$('#checkAll').change(function (){
		if((this).checked){
			$(this).closest('table').find('tbody').find('input[type=checkbox]').prop('checked', true);
		} else {
			$(this).closest('table').find('tbody').find('input[type=checkbox]').prop('checked', false);
			$('#btnDelete').prop('disabled', true);
		}
	});
}

function autoCheckParent() {
	var totalCheckChild = $('#checkAll').closest('table').find('tbody').find('input[type=checkbox]').length;
	$('#checkAll').closest('table').find('tbody').find('input[type=checkbox]').each(function (){
		$(this).on('change', function() {
			var totalCheckChildChecked = $('#checkAll').closest('table').find('tbody').find('input[type=checkbox]:checked').length;
			if(totalCheckChild==totalCheckChildChecked){
				$('#checkAll').prop('checked',true);
			} else {
				$('#checkAll').prop('checked',false);
			}
		});
	});
	
}