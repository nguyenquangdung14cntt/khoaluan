<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Quản lý danh mục tin</h1>
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
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Danh mục tin
					<a style="float: right" href="${pageContext.request.contextPath}/admin/categories/add">Thêm</a>  
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th style="text-align: center;">STT</th>
								<th>Tên danh mục</th>
								<th>Chức năng</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listCat}" var="objCat" varStatus="loop">
								<tr>
									<td style="text-align: center;">${loop.index + 1}</td>
									<td>${objCat.name_cat}</td>
									<c:set var="editUrl"
										value="${pageContext.request.contextPath}/admin/categories/edit/${objCat.id}"></c:set>
									<c:set var="delUrl"
										value="${pageContext.request.contextPath}/admin/categories/del/${objCat.id}"></c:set>
									<td><a href="${editUrl}"><img src="<c:url value="/resources/admin/image/icon-update.png"/>" alt="Cập nhập" width="20" height="20" /></a> | <a
										onclick="return confirm('Xóa danh mục này?')"
										href="${delUrl}"><img src="<c:url value="/resources/admin/image/icon-delete.png"/>" alt="Xóa" width="20" height="20" /></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
