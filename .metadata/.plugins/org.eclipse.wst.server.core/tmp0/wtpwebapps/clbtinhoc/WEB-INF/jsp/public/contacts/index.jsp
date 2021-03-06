<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="content-grids">
	<div class="col-md-12 content-main">
	<c:if test="${param['msg'] eq 'add-success'}">
			<div class="alert alert-success alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Gửi thành công
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'add-error'}">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Gửi không thành công
			</div>
		</c:if>
		<c:if test="${param['msg'] eq 'spam'}">
			<div class="alert alert-warning alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Bạn đã gửi ý kiến có cùng nội dung quá nhiều lần. Vui lòng chờ ban quản trị xem xét
			</div>
		</c:if>
	<h1 style="text-align: center;">Ý kiến đóng góp</h1>
	
		<div>
			<form action="${pageContext.request.contextPath}/y-kien" method="post">
			<div class="col-lg-12 ">
				<input type="hidden" name="id_user" value="${logged.id}">
				<div class="form-group">
					<label>Nội dung</label>
					<textarea id="ckeditor" class="form-control" name="content"></textarea>
					<p style='color:red'><form:errors path="objContact.content"></form:errors></p>
				</div>
				<button type="submit" class="btn btn-default">Gửi</button>
				</div>
			</form>
		</div>
		<script type="text/javascript">
						var editor = CKEDITOR.replace("ckeditor");
						CKFinder.setupCKEditor(editor, '${pageContext.request.contextPath}/resources/ckfinder/');
					</script>
	</div>
	<div class="clearfix"></div>
</div>