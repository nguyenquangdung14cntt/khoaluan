<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Quản lý tài khoản</h1>
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
		<c:if test="${param['msg'] eq 'rule-exist'}">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Nội quy đã tồn tại
			</div>
		</c:if>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Nội quy câu lạc bộ 
				<c:if test="${objRule eq null}">
					<a style="float: right" href="${pageContext.request.contextPath}/admin/rules/add">Thêm</a>  
				</c:if>
			</div>
			<div class="panel-body">
				<c:if test="${objRule ne null}">
					${objRule.content}
					<br>
					Người viết:<a href="#">${writer.lastname} ${writer.firstname}</a>
					<br>
					<div style="float:right">
						<a href="${pageContext.request.contextPath}/admin/rules/edit/${objRule.id}"><img src="<c:url value="/resources/admin/image/icon-update.png"/>" alt="Xóa" width="20" height="20" /></a>
						 | 
						<a onclick="return confirm('Bạn chắc chắn muốn xóa nội dung này?')" href="${pageContext.request.contextPath}/admin/rules/del/${objRule.id}"><img src="<c:url value="/resources/admin/image/icon-delete.png"/>" alt="Xóa" width="20" height="20" /></a>
					</div>
				</c:if>
				<c:if test="${objRule eq null}">
					Không có quy định nào.
				</c:if>
				
			</div>
		</div>
	</div>
</div>
