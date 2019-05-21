<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-news"/>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Cập nhật bài viết</title>
</head>

<body>
	<div class="main-content">
		<form id="formSubmit">
			<div class="main-content-inner">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<c:if test="${not empty message }">
								<div class="alert alert-${alert }">${message }</div>
							</c:if>
							<div class="form-group">
								<br /> <br /> <label
									class="col-sm-3 control-label no-padding-right">Thể
									loại</label>
								<div class="col-sm-9">
									<c:if test="${empty model.categoryCode }">
										<select class="form-control" id="categoryCode"
											name="categoryCode">
											<option>Chọn thể loại</option>
											<c:forEach var="item" items="${ categories}">
												<option value="${item.code }">${item.name }</option>
											</c:forEach>
										</select>
									</c:if>
									<c:if test="${not empty model.categoryCode }">
										<select class="form-control" id="categoryCode"
											name="categoryCode">
											<option>Chọn thể loại</option>
											<c:forEach var="item" items="${ categories}">
												<%-- <c:if test="${item.code == model.categoryCode }">
													<option value="${item.code }" selected="selected">${item.name }</option>
												</c:if>
												<c:if test="${item.code != model.categoryCode }">
													<option value="${item.code }">${item.name }</option>
												</c:if> --%>

												<option value="${item.code }"
													<c:if test="${item.code == model.categoryCode }">
													selected="selected"</c:if>>${item.name }</option>
											</c:forEach>
										</select>
									</c:if>
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Tiêu
									đề</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="title" name="title"
										value="${model.title }" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Hình
									đại diện</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="thumbnail"
										name="thumbnail" value="${model.thumbnail }" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Mô
									tả ngắn</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="shortdescription"
										name="shortdescription" value="${model.shortdescription }" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Nội
									dung</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="content"
										name="content" value="${model.content }" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<div class="col-sm-3">
									<c:if test="${not empty model.id}">
										<input type="button" class="form-control"
											class="btn btn-white btn-warning btn-bold"
											id="btnAddOrUpdate" value="Cập nhật bài viết" />
									</c:if>
									<c:if test="${empty model.id }">
										<input type="button" class="form-control"
											class="btn btn-white btn-warning btn-bold"
											id="btnAddOrUpdate" value="Thêm bài viết" />
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
	<!-- /.main-content -->
	<script>
		$('#btnAddOrUpdate').click(function(e) {
			e.preventDefault();
			var data = {};
			var formData = $("#formSubmit").serializeArray();
			$.each(formData, function(i, v) {
				data["" + v.name + ""] = v.value;
			});
			
			var id = $("#id").val();
			if(id == ""){
				addNews(data);
			} else {
				updateNews(data);
			}
		});
		
		function addNews(data){
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
		
		function updateNews(data){
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