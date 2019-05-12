<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<c:url var="APIurl" value="/api-admin-news"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách bài viết</title>
</head>
<body>
	<div class="main-content">
		<form action="<c:url value='/admin-news'/>" id="formSubmit" method="get">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
							chủ</a></li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="widget-box table-filter">
							<div class="table-btn-controls">
								<div class="pull-right tableTools-container">
									<div class="dt-buttons btn-overlap btn-group">
										<a flag="info"
											class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
											data-toggle="tooltip" title='Thêm bài viết'
											href='<c:url value="/admin-news?type=edit"/>'> <span>
												<i class="fa fa-plus-circle bigger-110 purple"></i>
										</span>
										</a>
										<button id="btnDelete" type="button" disabled
											class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
											data-toggle="tooltip" title='Xóa bài viết'>
											<span> <i class="fa fa-trash-o bigger-110 pink"></i>
											</span>
										</button>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12">
								<div class="table-responsive">
									<table class="table table-bordered">
										<thead>
											<tr>
												<th><input type="checkbox" id="checkAll" class="check-box-element" /></th>
												<th>Tên bài viết</th>
												<th>Mô tả ngắn</th>
												<th>Thao tác</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${model.listResult}">
													<tr>
														<td><input type="checkbox" id="check_${item.id }" name="ids" class="check-box-element" value="${item.id }" /></td>
														<td>${item.title}</td>
														<td>${item.shortdescription}</td>
														<td>
														<c:url var="editURL" value="/admin-news">
															<c:param name="type" value="edit" />
															<c:param name="id" value="${item.id}" />
														</c:url>
															<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
															 title="Cập nhật bài viết" href='${editURL }'><i class="fa fa-pencil-square-o"
															 aria-hiden="true"></i>
															</a>
														</td>
													</tr>
												</c:forEach>
										</tbody>
									</table>
									<ul class="pagination" id="pagination"></ul>
									<input type="hidden" value="" id="page" name="page" />
									<input type="hidden" value="" id="maxPageItem" name="maxPageItem" />
									<input type="hidden" value="" id="sortName" name="sortName" />
									<input type="hidden" value="" id="sortBy" name="sortBy" />
									<input type="hidden" value="list" id="type" name="type" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
	<!-- /.main-content -->
	<script type="text/javascript">
			
	//when web is loaded that everything is start in here 
			/* $(document).ready(function(){
				enableOrDisableBtnDelete();
				autoCheckAllChild();
				autoCheckParent();
			}); */
	
			var totalPages = ${model.totalPage};
			var currentPage = ${model.page};
			var visiblePages = ${model.maxPageItem}
			var limit = 6;
			$(function () {
				window.pagObj = $('#pagination').twbsPagination({
					totalPages: totalPages,
					visiblePages: 10,
					startPage: currentPage,
					onPageClick: function (event, page) {
						if (currentPage != page) {
							$('#maxPageItem').val(limit);
							$('#page').val(page);
							$('#sortName').val('title');
							$('#sortBy').val('desc');
							$('#type').val('list');
							$('#formSubmit').submit();
						}
					}
				});
			});
			
			/* function enableOrDisableBtnDelete(){
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
				
			} */
			
			$('#btnDelete').click(function (e) {
				e.preventDefault();
				var data = {};
				var ids = [];
				
				$.each($('input[name="ids"]:checked'), function (){
					ids.push($(this).val());
					
				});
				
				data[""+'ids'+""] = ids;
				deleteNews(data);
			});
			
			function deleteNews(data) {
				$.ajax ({
					url: '${APIurl}',
					type: 'DELETE',
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