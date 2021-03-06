<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Thêm nội quy</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Nội quy câu lạc bộ</div>
			<div class="panel-body">
				<div class="row">
					<form action="${pageContext.request.contextPath}/admin/rules/add" method="post">
						<div class="col-lg-12 ">
							<div class="form-group">
								<label>Nội dung</label> 
								<textarea id="ckeditor" class="form-control"name="content"></textarea>
							</div>
							<p style='color:red'><form:errors path="objRule.content"></form:errors></p>
							<button type="submit" class="btn btn-default">Thêm</button>
							<button type="reset" class="btn btn-default">Nhập lại</button>
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
