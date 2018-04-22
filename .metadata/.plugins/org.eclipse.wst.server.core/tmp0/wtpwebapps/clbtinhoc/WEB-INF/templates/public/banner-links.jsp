<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="banner-links">
	<ul class="nav-links">
		<li><a href="${pageContext.request.contextPath}/">Trang
				chủ</a></li>
		<li><div class="dropdown" style="float: left;">
				<a class="dropbtn">Tin tức, sự kiện</a>
				<div class="dropdown-content" style="left: 0;">
					<c:forEach items="${lCat}" var="oCat">
						<a href="${pageContext.request.contextPath}/${slug.makeSlug(oCat.name_cat)}-${oCat.id}">${oCat.name_cat}</a>
					</c:forEach>
				</div>
			</div></li>
		<li><div class="dropdown" style="float: left;">
				<a class="dropbtn">Hoạt động</a>
				<div class="dropdown-content1" style="left: 0;">
					<a href="${pageContext.request.contextPath}/danh-sach-hoat-dong">Danh sách các hoạt động</a> <a href="#">Lịch sử
						tham gia</a>
				</div>
			</div></li>
		<li><a href="${pageContext.request.contextPath}/noi-quy">Nội
				quy</a></li>
		<li><a href="${pageContext.request.contextPath}/y-kien">Đóng
				góp ý kiến</a></li>
		<sec:authorize access="!isAuthenticated()">
			<li style="float: right;"><a
				href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
				<li style="float: right;"><a
				href="${pageContext.request.contextPath}/register">Đăng ký</a></li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<li style="float: right;"><a
				href="${pageContext.request.contextPath}/logout">Đăng xuất</a></li>
			<li style="float: right;"><a href="#">${logged.lastname}
					${logged.firstname}</a></li>
		</sec:authorize>
		<div class="clearfix"></div>
	</ul>
	<script type="text/javascript">
		$(function() {
			$('.nav-links a').filter(function() {
				return this.href == location.href
			}).parent().addClass('active').siblings().removeClass('active')
			$('.nav-links a').click(
					function() {
						$(this).parent().addClass('active').siblings()
								.removeClass('active')
					})
		})
	</script>

</div>