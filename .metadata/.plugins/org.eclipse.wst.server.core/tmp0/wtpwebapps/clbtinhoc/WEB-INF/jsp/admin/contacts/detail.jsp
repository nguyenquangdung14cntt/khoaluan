<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Quản lý ý kiến đóng góp</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Ý kiến đóng góp 
			</div>
			<div class="panel-body">
				<c:if test="${objContact ne null}">
					${objContact.content}
					<br>
					Người viết: <a href="${pageContext.request.contextPath}/${objContact.id_user}">${objContact.lastname} ${objContact.firstname}</a>
					<br>
				</c:if>
			</div>
		</div>
	</div>
</div>
