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
	          <!-- <div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
	            <ol class="carousel-indicators">
	              <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
	              <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
	              <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
	            </ol>
	            <div class="carousel-inner" role="listbox">
	              <div class="carousel-item active">
	                <img class="d-block img-fluid" src="https://edecorati.com/data/out/226/pictures-of-palm-trees_292628072.jpg" alt="First slide">
	              </div>
	              <div class="carousel-item">
	                <img class="d-block img-fluid" src="http://irishfungalsociety.org/wp-content/uploads/2018/02/IMG_2024-900x350.jpg" alt="Second slide">
	              </div>
	              <div class="carousel-item">
	                <img class="d-block img-fluid" src="http://destinationkenya.co.ke/wp-content/uploads/2016/07/19366-boats-on-zanzibar-beach-1920x1200-beach-wallpaper-900x350.jpg" alt="Third slide">
	              </div>
	            </div>
	            <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
	              <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	              <span class="sr-only">Previous</span>
	            </a>
	            <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
	              <span class="carousel-control-next-icon" aria-hidden="true"></span>
	              <span class="sr-only">Next</span>
	            </a>
	          </div> -->
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