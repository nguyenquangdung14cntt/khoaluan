<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Thêm tin tức</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Nội dung tin tức</div>
			<div class="panel-body">
				<div class="row">
					<form action="${pageContext.request.contextPath}/admin/news/add" method="post">
						<div class="col-lg-12 ">
							<div class="form-group">
								<label>Tiêu đề</label> 
								<input  class="form-control" name="title">
							</div>
							<p style='color:red'><form:errors path="objNews.title"></form:errors></p>
							<div class="form-group">
								<label>Loại tin</label> 
								<select class="form-control" name="id_cat">
									<c:forEach items="${listCat}" var="objCat">
                                    	<option value="${objCat.id}">${objCat.name_cat}</option>
                                    </c:forEach>
                                </select>
							</div>
							<div class="form-group">
								<label>Nội dung</label> 
								<textarea id="ckeditor" class="form-control" name="content"></textarea>
							</div>
							<p style='color:red'><form:errors path="objNews.content"></form:errors></p>
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
