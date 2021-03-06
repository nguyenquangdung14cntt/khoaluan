<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Danh sách đăng ký hoạt động <a style="float: right"
					href="${pageContext.request.contextPath}/admin/activities/${id}/attendance">Điểm danh</a></h1> 
</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				${activity_name} 
					<a style="float: right"
					href="${pageContext.request.contextPath}/admin/activities/${id}/print">In danh sách</a>
			</div>
			<div class="panel-body">
				<div class="table-responsive">
				<c:if test="${listActivityUsers ne null}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th style="text-align: center;">STT</th>
								<th>Tên thành viên</th>
								<th style="text-align: center;">Lớp</th>
								<th style="text-align: center;">Ngày đăng ký</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listActivityUsers}" var="objActivityUser" varStatus="loop" >
								<tr>
									<td style="text-align: center;" >${loop.index + 1}</td>
									<td>${objActivityUser.lastname} ${objActivityUser.firstname}</td> 
									<td style="text-align: center;">${objActivityUser.klass}</td>
									<td style="text-align: center;">${objActivityUser.joined_at}</td>
								</tr>
							</c:forEach>
							
						</tbody>
					</table>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
