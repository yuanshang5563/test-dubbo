<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="/commons/jsp/top.jsp" %>
<!DOCTYPE html>
<html>
  <head>
    <title>core_user_list</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
  </head>
  
  <body>
	<div class="container">
		<div class="panel panel-primary">
			<div class="panel-body">
				<ol class="breadcrumb">
				  <li><a href="#">首页</a></li>
				  <li class="active">用户管理</li>
				</ol>
				<button type="button" class="btn btn-primary"  onclick="addUser()">新增</button>
				<form action="/userController/userList" method="post" class="navbar-form navbar-right" role="search">
				  <div class="form-group">
				  	<label for="userName">用户名:</label>
				    <input id="userName" name="userName" type="text" class="form-control" value="${userName}">
				    <label for="phone">电话:</label>
				    <input id="phone" name="phone" type="text" class="form-control" value="${phone}">
				  </div>
				  <button type="submit" class="btn btn-default">搜索</button>
				</form>		
				<table class="table table-bordered table-striped table-hover">
					<thead>
						<tr>
							<th class="text-center">用户名</th>
							<th class="text-center">密码</th>
							<th class="text-center">电话</th>
							<th class="text-center" style="width: 200px">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:foreach item={pageInfo.list} var="user">
						<tr>
							<td class="text-center">${user.userName}</td>
							<td class="text-center">${user.password}</td>
							<td class="text-center">${user.phone}</td>
							<td class="text-center">
								<input type="button" class="btn btn-info btn-xs" value="查看" onclick="${user.userId}">
								<input type="button" class="btn btn-primary btn-xs" value="修改">
								<input type="button" class="btn btn-warning btn-xs" value="删除">
							</td>				
						</tr>
						</c:foreach>
					</tbody>
				</table><span id="${sb}"></span>
				<div class="col-sm-offset-4 col-sm-4">	
					<nav aria-label="Page navigation">
					  <ul class="pagination">
					    <li><a href="@{/userController/userList?pageNum=1}+${sb}">首页</a></li>
					    <li class="${pageInfo.hasPreviousPage==false}?'disabled'"><a href="@{/userController/userList?pageNum=}+${pageInfo.pageNum-1}+${sb}">上一页</a></li>
					    <li class="${pageInfo.hasNextPage==false}?'disabled'"><a href="@{/userController/userList?pageNum=}+${pageInfo.pageNum+1}+${sb}">下一页</a></li>
					    <li><a href="@{/userController/userList?pageNum=}+${pageInfo.pages}+${sb}">末页</a></li>
					  </ul>
					</nav>
				</div>
			</div>
		</div>
		
	<!-- 模态框（Modal） -->
	<div class="modal fade" tabindex="-1" role="dialog" id="myModal">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title">Modal title</h4>
	      </div>
	      <div class="modal-body">
	        <p>One fine body&hellip;</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary">Save changes</button>
	      </div>
	    </div>
	  </div>
	</div>
</div>
  </body>
</html>
