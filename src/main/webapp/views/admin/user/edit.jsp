<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-admin-user" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý Account</title>
</head>
<body>
	<div class="main-content">
		<form id="formSubmit">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<br/>
							<br/>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">User Name</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="username" name="username" value="${model.username }"/>
								</div>
							</div>
							<br/>
							<br/>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Password</label>
								<div class="col-sm-9">
									<input type="password" class="form-control" id="password" name="password" value="${model.password }"/>
								</div>
							</div>
							<br/>
							<br/>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Full Name</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="fullname" name="fullname" value="${model.fullname }"/>
								</div>
							</div>
							<br/>
							<br/>
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Role</label>
								<div class="col-sm-9">
									<c:if test="${empty model.roleid }">
										<select class="form-control" id="roleid" name="roleid">
											<option>Chọn quyền</option>
											<c:forEach var="item" items="${roles }">
												<option value="${item.id }">${item.name }</option>
											</c:forEach>
										</select>
									</c:if>
									<c:if test="${not empty model.roleid}">
										<select class="form-control" id="roleid" name="roleid">
											<option>Chọn quyền</option>
											<c:forEach var="item" items="${roles }">
												<option value="${item.id }" <c:if test="${model.roleid == item.id }">
												selected="selected"</c:if>>${item.name }
												</option>
											</c:forEach>
										</select>
									</c:if>
								</div>
							</div>
							<br/>
							<br/>
							<div class="form-group">
								<div class="col-sm-3">
									<c:if test="${not empty model.id }">
										<input type="button" class="form-control" class="btn btn-white btn-warning btn-bold"
										id="btnAddOrUpdate" value="Cập nhật"/>
									</c:if>
									<c:if test="${empty model.id }">
										<input type="button" class="form-control" class="btn btn-white btn-warning btn-bold"
										id="btnAddOrUpdate" value="Thêm mới"/>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="id" name="id" value="${model.id }" />
		</form>
	</div>
	<script type="text/javascript">
		$('#btnAddOrUpdate').click(function(e){
			e.preventDefault();
			var data={};
			var formData =$('#formSubmit').serializeArray();
			$.each(formData, function(i,v){
				data[""+v.name+""]=v.value;
			});
			var id=$('#id').val();
			if(id == ""){
				addUser(data);
			} else {
				updateUser(data);
			}
		});
		
		function addUser(data){
			$.ajax ({
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
				},
			});
		}
		
		function updateUser(data){
			$.ajax ({
				url: '${APIurl}',
				type: 'PUT',
				contentType: 'application/json',
				data: JSON.stringify(data),
				dataType: 'json',
				success: function(result){
					console.log(result);
				},
				error: function(error){
					console.log(error);
				},
			});
		}
	</script>
</body>
</html>