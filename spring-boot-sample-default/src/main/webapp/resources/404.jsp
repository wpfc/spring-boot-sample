<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="<%=request.getContextPath()%>"></c:set>
<div class="content-wrapper">
  	<!-- content here -->
  	
  	<section class="content-header">
      <h1>
        	系统错误
      </h1>
      <ol class="breadcrumb">
        <li><a href="${root }/"><i class="fa fa-dashboard"></i> 主页</a></li>
        <li class="active">系统错误</li>
      </ol>
    </section>
    
    <section class="content clearfix">
	 	<div style="text-align: center; margin-top: 30px;">
	 		<img src="${root }/static/img/404.jpg" />
	 	</div>
    </section>
  
  </div>
 

<script>
</script>  
