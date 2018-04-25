<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="content-grids">
	<c:if test="${param['msg'] eq 'add-error'}">
			<div class="alert alert-danger alert-dismissable">
				Đăng ký tham gia thất bại
			</div>
		</c:if>
			<c:if test="${param['msg'] eq 'full'}">
			<div class="alert alert-warning alert-dismissable">
				Đã hết số lượng tham gia
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'registed'}">
			<div class="alert alert-warning alert-dismissable">
				Đã đăng ký tham gia hoạt động này
			</div>
		</c:if>
	<div class="col-md-8 content-main">
		<div class="row">
			<h1 class="title">Danh sách các hoạt động đã đăng ký</h1>
			<table class="table">
				<tr>
					<th style="text-align: center;">STT</th>
					<th>Tên hoạt động</th>
					<!-- <th
						style="text-align: center;">Ngày mở đăng ký</th>
						<th style="text-align: center;">Ngày đóng đăng ký</th> -->
					<th style="text-align: center;">Phí tham gia (VNĐ)</th>
					<th style="text-align: center;">Ngày đăng ký</th>
					<th style="text-align: center;">Chức năng</th>
				</tr>
				<c:forEach items="${listActivities}" var="objActivity"
					varStatus="loop">
					<tr>
						<td style="text-align: center;">${loop.index + 1}</td>
						<td><a
							href="${pageContext.request.contextPath}/hoat-dong/${slug.makeSlug(objActivity.title)}/${objActivity.id}">${objActivity.title}</a></td>
						<%-- <td style="text-align: center;"><fmt:parseDate value="${objActivity.start_at}" var="start" pattern="dd-MM-yyyy" />
						<fmt:formatDate pattern = "dd-MM-yyyy" 
         value = "${start}" />
						</td>
						<td style="text-align: center;"><fmt:parseDate value="${objActivity.end_at}" var="end" pattern="dd-MM-yyyy" />
						<fmt:formatDate pattern = "dd-MM-yyyy" 
         value = "${end}" /></td> --%>
						<td style="text-align: center;">${objActivity.fee}</td>
						<td style="text-align: center;">${objActivity.limited}</td>
						<td style="text-align: center;">${objActivity.joined}</td>
						<td style="text-align: center;">
						<c:choose>
								<c:when test="${objActivity.joined == objActivity.limited}">
									<a onclick="return alert('Hết số lượng đăng ký!')"><img alt="Đủ số lượng"
						src='<c:url value="/resources/public/images/dusoluong.png"></c:url>'></a>
								</c:when>
								<c:otherwise>
									<form
										action="${pageContext.request.contextPath}/hoat-dong/${slug.makeSlug(objActivity.title)}/${objActivity.id}"
										method="post">
										<button class="btn-register" type="submit">
											<img alt="Tham gia"
												src='<c:url value="/resources/public/images/thamgia.png"></c:url>'>
										</button>
									</form>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table>
			<div class="text-center">
				<ul class="pagination">
					<c:forEach var="i" begin="1" end="${total}">
						<c:set var="url"
							value="${pageContext.request.contextPath}/danh-sach-hoat-dong?p=${i}"></c:set>
						<li><a href="${url}" title="">${i}</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

	<div class="col-md-4 content-main-right">
		<div class="search">
			<h3>TÌM KIẾM HOẠT ĐỘNG</h3>
			<form action="${pageContext.request.contextPath}/tim-kiem-hoat-dong" method="get">
				<input type="text" name="search"> <input type="submit" value="">
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