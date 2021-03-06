<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <div style="text-align: center">
   	<h1>Danh Sách</h1>
   		<h1>${activity.title}</h1>
   		<div style="margin: 0 auto; width: 500px;">
   		<div style="float:left; text-align: left; font-size: 16px;">
   			<div>Ngày bắt đầu hoạt động: ${activity.start_at}</div>
   			<div>Ngày kết thúc hoạt động: ${activity.end_at}</div>
   		</div>
   		<div style="float:right;text-align: left; font-size: 16px;">
   			<div>Số lượng thành viên đăng ký: ${activity.joined}</div>
   			<div>Phí hoạt động: ${activity.fee}</div>
   		</div>
   		<div style="clear:both"></div>
   		</div>
   		<br>
   		<div>
   			<table align="center" border="1px" width="800px">
   				<tr>
   					<th width="10%">STT</th>
   					<th width="50%">Tên thành viên</th>
   					<th>Lớp</th>
   					<th>Ký tên</th>
   				</tr>
   				<c:set var="i" value="0"></c:set>
   				<c:forEach items="${listUsers}" var="objUser">
   				<tr>
   					<td>${i = i + 1}</td>
   					<td>${objUser.lastname} ${objUser.firstname}</td>
   					<td>${objUser.klass}</td>
   					<td></td>
   				</tr>
   				</c:forEach>
   			</table>
   		</div>
   </div>
<button id = "print" onclick="myFunction()">In trang</button>
<script>
function myFunction() {
	document.getElementById('print').style.visibility= "hidden";
    window.print();
    document.getElementById('print').style.visibility= "visible";
}
</script>