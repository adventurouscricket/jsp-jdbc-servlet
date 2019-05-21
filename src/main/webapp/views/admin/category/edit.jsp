<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-admin-category"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa thể loại</title>
</head>
<body>
	<div class="main-content">
	<form id="formSubmit">
		<div class="main-content-inner">
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="form-group">
							<br/>
							<br>
							<label class="col-sm-3 control-label no-padding-right">Tên thể loại</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="name" name="name" value="${model.name }"/>
							</div>
						</div>
						<div class="form-group">
							<br/>
							<br/>
							<label class="col-sm-3 control-label no-padding-right">Code</label>
							<div class="col-sm-9">
								<input type="text" class="form-control" id="code" name="code" value="${model.code }"/>
							</div>
						</div>
						<br/>
						<br/>
						<div class="form-group">
							<div class="col-sm-12">
								<c:if test="${not empty model.id }" >
									<input type="button" class="btn btn-white btn-warning btn-bold" id="btnAddOrUpdate" value="Cập nhật" />
								</c:if>
								<c:if test="${empty model.id }" >
									<input type="button" class="btn btn-white btn-warning btn-bold" id="btnAddOrUpdate" value="Thêm mới" />
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" id="id" value="${model.id }" name="id" />
		</form>
	</div>
	<script>
		$('#btnAddOrUpdate').click(function(e){
			e.preventDefault();
			var data ={};
			var formData = $("#formSubmit").serializeArray();
			$.each(formData, function(i,v){
				data[""+v.name+""] = v.value;
			});
			
			var id=$("#id").val();
			if(id == ""){
				addCategory(data);
			} else {
				updateCategory(data);
			}
		});
		
		function addCategory(data){
			$.ajax({
				url: '${APIurl}',
				type: 'POST',
				contentType: 'application/json',
				data: JSON.stringify(data),
				dataType: 'json',
				success: function(result){
					console.log(result);
				},
				error: function(error){
					console.log(error);
				}
			});
		}
		
		function updateCategory(data){
			$.ajax({
				url:'${APIurl}',
				type:'PUT',
				contentType:'application/json',
				data: JSON.stringify(data),
				dataType:'json',
				success: function(result){
					console.log(result);
				},
				error: function(error){
					console.log(error);
				}
			});
		}
	</script>
</body>
</html>