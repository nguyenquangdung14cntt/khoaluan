<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Điểm danh</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				${activity_name} <a style="float: right"
					href="${pageContext.request.contextPath}/admin/activities/${id}/create-attendance">Tạo lần điểm danh mới</a>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Lần điểm danh</th>
								<th>Ngày điểm danh</th>
								<th>Chi tiết</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listAtt}" var="objAtt">
								<tr>
									<td>${objAtt.id}</td>
									<td>Lần ${objAtt.times}</td>
									<td>${objAtt.created_at}</td>
									<td><a href="${pageContext.request.contextPath}/admin/activities/${id}/attendance/${objAtt.id}"><img alt="Chi tiết" src='<c:url value="/resources/admin/image/detail-icon.png"/>' width="20px" height="20px"></a></td>
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