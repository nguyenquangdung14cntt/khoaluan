<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Cập nhập thành viên</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Thông tin tài khoản</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-6">
						<form
							action="${pageContext.request.contextPath}/admin/users/edit/${objUser.id}"
							method="post">
							<div class="form-group">
								<label>Mật khẩu</label> <input class="form-control"
									type="password" name="password">
								<p class="help-block">Bao gồm 6 đến 16 ký tự</p>
							</div>
							<div class="form-group">
								<label>Quyền hạn</label>
								<select class="form-control" name="id_role">
								<option selected="selected" value="${objUser.id_role}">${objUser.role }</option>
									<c:forEach items="${listRoles}" var="objRole">
										<c:if test="${objRole.role ne 'Chủ nhiệm'}">
											<option value="${objRole.id}">${objRole.role}</option>
										</c:if>
									</c:forEach>
								</select>
								<p class="help-block">Ví dụ: Thành viên</p>
							</div>
							<button type="submit" class="btn btn-default">Cập nhập</button>
							<button type="reset" class="btn btn-default">Nhập lại</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
