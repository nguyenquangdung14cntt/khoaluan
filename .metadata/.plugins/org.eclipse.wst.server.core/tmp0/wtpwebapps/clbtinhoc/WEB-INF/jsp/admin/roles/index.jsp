<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Quản lý chức vụ</h1>
		<c:if test="${param['msg'] eq 'add-success'}">
			<div class="alert alert-success alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Thêm thành công
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'edit-success'}">
			<div class="alert alert-success alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Cập nhập thành công
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'del-success'}">
			<div class="alert alert-success alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Xóa thành công
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'add-error'}">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Thêm không thành công
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'edit-error'}">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Cập nhập không thành công
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'del-error'}">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Xóa không thành công
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'access-denied'}">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Không thể chỉnh sửa hay xóa chức vụ mặc định
			</div>
		</c:if>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Danh sách chức vụ <a style="float: right"
					href="${pageContext.request.contextPath}/admin/roles/add">Thêm</a>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th style="text-align: center;">STT</th>
								<th>Chức vụ</th>
								<th>Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listRoles}" var="objRole" varStatus="loop">
								<tr>
									<td style="text-align: center;">${loop.index + 1}</td>
									<td>${objRole.role}</td>
									<c:set var="editUrl"
										value="${pageContext.request.contextPath}/admin/roles/edit/${objRole.id}"></c:set>
									<c:set var="delUrl"
										value="${pageContext.request.contextPath}/admin/roles/del/${objRole.id}"></c:set>
									<td>
									<a href="${pageContext.request.contextPath}/admin/roles/${objRole.id}/permissions"><img
												src="<c:url value="/resources/admin/image/access.png"/>"
												alt="Cập nhập" width="20" height="20" /></a>
									<c:if
											test="${objRole.role ne 'Thành viên' && objRole.role ne 'Chủ nhiệm'}">
											 | <a href="${editUrl}"><img
												src="<c:url value="/resources/admin/image/icon-update.png"/>"
												alt="Cập nhập" width="20" height="20" /></a> | <a
												onclick="return confirm('Xóa chức vụ này?')"
												href="${delUrl}"><img
												src="<c:url value="/resources/admin/image/icon-delete.png"/>"
												alt="Xóa" width="20" height="20" /></a>
									</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
