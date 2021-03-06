<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Thêm thành viên</h1>
			<c:if test="${param['msg'] eq 'exist-name'}">
			<div class="alert alert-danger alert-dismissable">
				<button type="button" class="close" data-dismiss="alert"
					aria-hidden="true">&times;</button>
				Tên tài khoản đã tồn tại
			</div>
		</c:if>
	</div>
		<div style="color: red;padding: 20px;">
		<c:if test="${objUser ne null}">
			<ul>
				<form:errors path="objUser.firstname" element="p"></form:errors>
				<form:errors path="objUser.lastname" element="p"></form:errors>
				<form:errors path="objUser.username" element="p"></form:errors>
				<form:errors path="objUser.password" element="p"></form:errors>
				<form:errors path="objUser.email" element="p"></form:errors>
				<form:errors path="objUser.phone" element="p"></form:errors>
				<form:errors path="objUser.klass" element="p"></form:errors>
			</ul>
		</c:if>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Thông tin tài khoản</div>
			<div class="panel-body">
				<div class="row">
					<form action="${pageContext.request.contextPath}/admin/users/add"
						method="post">
						<div class="col-lg-12">
							<div class="col-lg-6 ">
								<div class="form-group">
									<label>Tên</label> <input class="form-control" type="text"
										name="firstname">
									<p class="help-block">Ví dụ: Dũng</p>
								</div>
								<div class="form-group">
									<label>Tài khoản</label> <input class="form-control"
										type="text" name="username">
									<p class="help-block">Ví dụ: nguyenquangdung</p>
								</div>
								<div class="form-group">
									<label>Email</label> <input class="form-control" type="text"
										name="email">
									<p class="help-block">Ví dụ: nguyenquangdung5560@gmail.com</p>
								</div>
								<div class="form-group">
									<label>Chức vụ</label> 
									<select class="form-control" name="id_role">
										<c:forEach items="${listRoles}" var="objRole">
											<c:if test="${objRole.role ne 'Chủ nhiệm'}">
												<option value="${objRole.id}">${objRole.role}</option>
											</c:if>
										</c:forEach>
									</select>
									<p class="help-block">Ví dụ: Thành viên</p>
								</div>
							</div>
							<div class="col-lg-6">
								<div class="form-group">
									<label>Họ</label> <input class="form-control" type="text"
										name="lastname">
									<p class="help-block">Ví dụ: Nguyễn Quang</p>
								</div>
								<div class="form-group">
									<label>Mật khẩu</label> <input class="form-control"
										type="password" name="password">
									<p class="help-block">Bao gồm 6 đến 16 ký tự</p>
								</div>
								<div class="form-group">
									<label>Số điện thoại</label> <input class="form-control"
										type="text" name="phone">
									<p class="help-block">Ví dụ: 0935634876</p>
								</div>
								<div class="form-group">
									<label>Lớp</label> <input class="form-control" type="text"
										name="klass">
									<p class="help-block">Ví dụ: 14CNTT</p>
								</div>
							</div>
							<div style="clear: both"></div>
							<button type="submit" class="btn btn-default">Thêm</button>
							<button type="reset" class="btn btn-default">Nhập lại</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
