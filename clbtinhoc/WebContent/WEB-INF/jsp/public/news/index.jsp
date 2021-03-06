<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="content-grids">
	<div class="col-md-8 content-main">
		<h1 class="title">${objNews.title}</h1>
			<c:choose>
				<c:when test="${objNews.created_at < objNews.updated_at}">
					<h5 class="date-view">Cập nhập ngày: ${objNews.updated_at} - Lượt xem:
						${objNews.view}</h5>
				</c:when>
				<c:otherwise>
					<h5 class="date-view">Đăng ngày: ${objNews.created_at} - Lượt xem:
						${objNews.view}</h5>
				</c:otherwise>
			</c:choose>
		<div class="news-content">
			${objNews.content}
		</div>
	</div>

	<div class="col-md-4 content-main-right">
		<div class="search">
						 <h3>TÌM KIẾM TIN TỨC</h3>
						<form action="${pageContext.request.contextPath}/tim-kiem-tin-tuc" method="get">
							<input type="text" name="search">
							<input type="submit" value="">
						</form>
				 </div>

		<div class="categories">
			<h3>Tin tức mới nhất</h3>
			<ul>
				<c:forEach items="${newList}" var="newObj">
					<li><a href="${pageContext.request.contextPath}/${slug.makeSlug(newObj.name_cat)}-${newObj.id_cat}/${slug.makeSlug(newObj.title)}-${newObj.id}">${newObj.title}</a></li>
				</c:forEach>
			</ul>
		</div>
		  <div class="categories">
					 <h3>Thông tin liên hệ</h3>
					 <p>Chủ nhiệm câu lạc bộ: ${chunhiem.lastname} ${chunhiem.firstname}</p>
					 <p>Email: ${chunhiem.email}</p>
					 <p>SĐT: ${chunhiem.phone}</p>
					 <p>Địa chỉ sinh hoạt: Trường đại học Sư Phạm Đà Nẵng</p>
				 </div>
	</div>
	<div class="clearfix"></div>
</div>