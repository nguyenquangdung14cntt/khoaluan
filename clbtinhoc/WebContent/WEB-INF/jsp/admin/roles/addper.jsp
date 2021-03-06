<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Thêm quyền truy cập</h1>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Danh sách quyền</div>
			<div class="panel-body">
				<div class="row">
						<form
							action="${pageContext.request.contextPath}/admin/roles/${id}/permissions/add"
							method="post">
							<div class="col-lg-12 ">
								<div class="form-group">
									<label>Quyền truy cập</label>
									<div class="checkbox-container">

										<c:forEach items="${listPermis}" var="objPer">
											<c:if test="${objPer.name eq 'ACCESS_ADMIN'}">
												<c:set var="first" value="${objPer}" />
											</c:if>
											<c:if test="${objPer.name eq 'ATTENDANCE'}">
												<c:set var="second" value="${objPer}" />
											</c:if>
										</c:forEach>

										<ul>
										<c:if test="${first ne null}">
											<li class="first"><input type="checkbox"
												name="id_permission" value="${first.id}">${first.name}
												<ul>
													<c:forEach items="${listPermis}" var="objPer">
														<c:if
															test="${objPer.name ne 'ACCESS_ADMIN' && objPer.name ne 'ATTENDANCE'}">
															<li><input class="check" type="checkbox"
																name="id_permission" value="${objPer.id}">${objPer.name}</li>
														</c:if>
													</c:forEach>
												</ul></li>
										</c:if>
										<c:if test="${second ne null}">
											<li class="first"><input type="checkbox"
												name="id_permission" value="${second.id}">${second.name}
											</li>
										</c:if>
										</ul>
									</div>
								</div>
								<button type="submit" class="btn btn-default">Thêm</button>
							</div>
						</form>
					<script type="text/javascript">
						$(document)
								.ready(
										function() {
											$(
													".checkbox-container input[type='checkbox']")
													.change(
															function() {
																if ($(this)
																		.is(
																				':checked')) {
																	$(this)
																			.parent(
																					"li")
																			.children(
																					"ul")
																			.show(
																					600);
																} else {
																	$('.check')
																			.prop(
																					'checked',
																					false);
																	$(this)
																			.parent(
																					"li")
																			.children(
																					"ul")
																			.hide(
																					600);
																}
															});
										});
					</script>
				</div>
			</div>
		</div>
	</div>
</div>
