<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Cập nhập danh mục</h1>
			<c:if test="${param['msg'] eq 'exist-name'}">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Tên danh mục đã tồn tại
			</div>
		</c:if>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Danh mục tin tức</div>
			<div class="panel-body">
				<div class="row">
					<form action="${pageContext.request.contextPath}/admin/categories/edit/${objCat.id}"
						method="post">
						<div class="col-lg-12 ">
							<div class="form-group">
								<label>Tên danh mục</label> 
								<input  class="form-control" type="text" value="${objCat.name_cat}" name="name_cat">
							</div>
							<p style='color:red'><form:errors path="objCat.name_cat"></form:errors></p>
							<button type="submit" class="btn btn-default">Cập nhập</button>
						</div>
					</form>
					<script type="text/javascript">
						var editor = CKEDITOR.replace("ckeditor");
						CKFinder.setupCKEditor(editor, '${pageContext.request.contextPath}/resources/ckfinder/');
					</script>
				</div>
			</div>
		</div>
	</div>
</div>
