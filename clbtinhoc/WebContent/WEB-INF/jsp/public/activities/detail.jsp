<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content-grids">
	<div class="col-md-8 content-main">
		<h1 class="title">${objActivity.title}</h1>
		<div class="activity-content">
			<p>Ngày đăng ký: ${objActivity.start_at} - ${objActivity.end_at}</p>
			<p>Số lượng giới hạn: ${objActivity.limited}</p>
			<p>Phí tham gia: ${objActivity.fee} VNĐ</p>
			<p>Nội dung hoạt động:</p>
			${objActivity.content}
			
			<c:choose>
				<c:when test="${objActivity.joined == objActivity.limited}">
					<a onclick="return alert('Đã hết số lượng đăng ký!')"><img alt="Đủ số lượng"
						src='<c:url value="/resources/public/images/dusoluong.png"></c:url>'></a>
				</c:when>
				<c:otherwise>
				<form action="${pageContext.request.contextPath}/hoat-dong/${slug.makeSlug(objActivity.title)}/${objActivity.id}" method="post">
					<button class="btn-register" type="submit"><img alt="Tham gia"
						src='<c:url value="/resources/public/images/thamgia.png"></c:url>'></button>
				</form>
				</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="col-md-4 content-main-right">
		<div class="search">
			<h3>TÌM KIẾM HOẠT ĐỘNG</h3>
			<form>
				<input type="text" value="" onfocus="this.value=''"
					onblur="this.value=''"> <input type="submit" value="">
			</form>
		</div>

		<div class="categories">
			<h3>Hoạt động mới nhất</h3>
			<ul>
				<c:forEach items="${newActivities}" var="newAct">
					<li><a
						href="${pageContext.request.contextPath}/hoat-dong/${slug.makeSlug(newAct.title)}/${newAct.id}">${newAct.title}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="categories">
			<h3>Thông tin liên hệ</h3>
			<p>Chủ nhiệm câu lạc bộ: ${chunhiem.lastname}
				${chunhiem.firstname}</p>
			<p>Email: ${chunhiem.email}</p>
			<p>SĐT: ${chunhiem.phone}</p>
			<p>Địa chỉ sinh hoạt: Trường đại học Sư Phạm Đà Nẵng</p>
		</div>
	</div>
	<div class="clearfix"></div>
</div>