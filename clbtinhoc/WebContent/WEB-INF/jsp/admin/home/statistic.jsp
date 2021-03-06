<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Thống kê tài khoản</h1>
	</div>
</div>
<div class="row">
<div class="col-lg-12">
	<div id='chartContainer'></div>
</div>
<script type="text/javascript">
$(function () {
    var chart = new CanvasJS.Chart("chartContainer", {
        theme: "light2",
        zoomEnabled: true,
        animationEnabled: true,
        title: {
            text: "Số lượng tài khoản đăng ký theo tháng"
        },
        axisX:{
      	  title:"Tháng",
      	  valueFormatString: "Tháng #"
      	 },
        axisY:{
        	  title:"Số lượng tài khoản",
        	  valueFormatString: "#"
        	 },
        data: [
        {
            type: "column",
            dataPoints:${dataPoints}
        }
        ]
    });
  
    chart.render();
});
</script>
</div>

