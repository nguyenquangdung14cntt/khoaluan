<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Quản lý hoạt động</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Hoạt động <a style="float: right"
					href="${pageContext.request.contextPath}/admin/activities/add">Thêm
					hoạt động</a>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Tên hoạt động</th>
								<th>Ngày đăng ký</th>
								<th>Số lượng giới hạn</th>
								<th>Đã đăng ký</th>
								<th>Trạng thái</th>
								<th>Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listActivities}" var="objActivity">
								<tr>
									<td>${objActivity.id}</td>
									<td>${objActivity.title}</td>
									<td>${objActivity.start_at} - ${objActivity.end_at}</td>
									<td>${objActivity.limited}</td>
									<td>${objActivity.joined}</td>
									<td>${objActivity.status}</td>
									<c:set var="editUrl"
										value="${pageContext.request.contextPath}/admin/activities/edit/${objActivity.id}"></c:set>
									<c:set var="delUrl"
										value="${pageContext.request.contextPath}/admin/activities/del/${objActivity.id}"></c:set>
									<td><a href="${editUrl}">Cập nhập</a> | <a
										onclick="return confirm('Xóa hoạt động này?')"
										href="${delUrl}">Xóa</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="text-center">
					<ul class="pagination">
						<c:forEach var="i" begin="1" end="${total}">
							<c:set var="url"
								value="${pageContext.request.contextPath}/admin/activities?p=${i}"></c:set>
							<li><a href="${url}" title="">${i}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>