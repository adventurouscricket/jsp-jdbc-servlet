<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trang chủ</title>
</head>
<body>
<div class="row">

        <div class="col-lg-3">

          <h1 class="my-4">Thể loại</h1>
          <div class="list-group">
            <c:forEach var="item" items="${categories }">
            	<a href="#" class="list-group-item">${item.name }</a>
            </c:forEach>
          </div>

        </div>
        <!-- /.col-lg-3 -->

        <div class="col-lg-9">
			<form action="<c:url value='/home'/>" id="formSubmit" method="get">
			<br/>
			<br/>
	          <div class="row">
				<c:forEach var="item" items="${model.listResult }">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
						    <c:url var="detailURL" value="/home">
								<c:param name="type" value="detail" />
								<c:param name="id" value="${item.id}" />
							</c:url>
							<a href="${detailURL }"><img class="card-img-top" src="${item.thumbnail }" alt=""></a>
							<div class="card-body">
								<h5 class="card-title"><a href="${detailURL }">${item.title }</a></h5>
								<p class="card-text">${item.shortdescription }</p>
							</div>
							<div class="card-footer">
			                  <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
			                </div>
						</div>
					</div>
				</c:forEach>
	          </div>
	          <!-- /.row -->
	          <ul class="pagination" id="pagination"></ul>
			  <input type="hidden" value="" id="page" name="page" />
			  <input type="hidden" value="" id="maxPageItem" name="maxPageItem" />
			  <input type="hidden" value="" id="sortName" name="sortName" />
			  <input type="hidden" value="" id="sortBy" name="sortBy" />
			  <input type="hidden" value="list" id="type" name="type" />
			</form>
        </div>
        <!-- /.col-lg-9 -->

      </div>
      <!-- /.row -->
      
      <script type="text/javascript">
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
      </script>
</body>
</html>