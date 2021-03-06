<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Danh sách điểm danh lần ${times}</h1> 
</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				${activity_name} 
			</div>
			<div class="panel-body">
				<div class="table-responsive">
					<form action="${pageContext.request.contextPath}/admin/activities/${id}/attendance/add" method="post">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>ID</th>
								<th>Tên thành viên</th>
								<th>Lớp</th>
								<th style="text-align: center">Điểm danh</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listUsers}" var="objUser" >
								<tr>
									<td>${objUser.id}</td>
									<td>${objUser.lastname} ${objUser.firstname}</td> 
									<td>${objUser.klass}</td>
									<td style="text-align: center;"><input type="checkbox" name="ids" value="${objUser.id_user}"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input type="submit" class="btn btn-default" value="Điểm danh"/> 
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
