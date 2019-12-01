<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- 页面meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>数据 - AdminLTE2定制版</title>
<meta name="description" content="AdminLTE2定制版">
<meta name="keywords" content="AdminLTE2定制版">

<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"
	name="viewport">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/morris/morris.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/select2/select2.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
</head>

<body class="hold-transition skin-purple sidebar-mini">

	<div class="wrapper">

		<!-- 页面头部 -->
		<jsp:include page="header.jsp"></jsp:include>
		<!-- 页面头部 /-->
		<!-- 导航侧栏 -->
		<jsp:include page="aside.jsp"></jsp:include>
		<!-- 导航侧栏 /-->

		<!-- 内容区域 -->
		<div class="content-wrapper">

			<!-- 内容头部 -->
			<section class="content-header">
			<h1>
				产品选择 <small>产品选择表单</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/index.jsp"><i
						class="fa fa-dashboard"></i> 首页</a></li>
				<li><a
					href="${pageContext.request.contextPath}/pages/orders-add.jsp">新建订单</a></li>
				<li class="active">产品选择表单</li>
			</ol>
			</section>
			<!-- 内容头部 /-->

			<form action="${pageContext.request.contextPath}/orders/addProductToProduct.do" method="post">
				<!-- 正文区域 -->
				<section class="content"> 
				
				<input type="hidden" name="ordersId" value="${orders.id}">

					<table id="dataList"
							class="table table-bordered table-striped table-hover dataTable">
							<thead>
								<tr>
									<th class="text-center" style="padding-right: 0px">选择</th>
									<th class="sorting_asc">ID</th>
									<th class="sorting">编号</th>
									<th class="sorting">产品名称</th>
									<th class="sorting">出发城市</th>
									<th class="sorting">出发时间</th>
									<th class="sorting">产品价格</th>
									<th class="sorting">产品描述</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${productList}" var="product">
									<tr>
										<%--显示产品状态为开启的产品信息--%>
										<c:if test="${product.productStatus==1}">
										<td class="text-center">
											<input name="ids" type="radio" value="${product.id}" onclick="checkOnclick(this)">
										</td>
										<td>${product.id}</td>
										<td>${product.productNum}</td>
										<td>${product.productName}</td>
										<td>${product.cityName}</td>
										<td>${product.departureTimeStr}</td>
										<td>${product.productPrice}</td>
										<td>${product.productDesc}</td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					<div class="box-footer">
						<div class="pull-left">
							<div class="form-group form-inline">
								总共${pageCount} 页，共${pageInfo.total} 条数据。 每页
								<select class="form-control" id="changePageSize" onchange="changePageSizes()">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
								</select> 条
							</div>
						</div>

						<div class="box-tools pull-right">
							<ul class="pagination">
								<li <c:if test="${pageInfo.pageNum == 1}">class="disabled" </c:if>>
									<a href="${pageContext.request.contextPath}/orders/selectProduct.do?page=1&size=${pageInfo.pageSize}" aria-label="Previous">首页</a>
								</li>
								<li <c:if test="${pageInfo.pageNum == 1}">class="disabled" </c:if>>
									<a href="${pageContext.request.contextPath}/orders/selectProduct.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a>
								</li>
								<c:if test="${pageInfo.pageNum<=pageCount}">
									<c:forEach begin="${pageInfo.pageNum}" end="${pageInfo.pageNum<=pageCount-5?pageInfo.pageNum+5:pageCount}" var="pageNum">
										<li <c:if test="${pageInfo.pageNum == pageNum}">class="active" </c:if>><%--显示选中的页数--%>
											<a href="${pageContext.request.contextPath}/orders/selectProduct.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a>
										</li>
									</c:forEach>
								</c:if>
								<li <c:if test="${pageInfo.pageNum == pageCount}">class="disabled" </c:if>>
									<a href="${pageContext.request.contextPath}/orders/selectProduct.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a></li>
								<li <c:if test="${pageInfo.pageNum == pageCount}">class="disabled" </c:if>>
									<a href="${pageContext.request.contextPath}/orders/selectProduct.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}" aria-label="Next">尾页</a>
								</li>
							</ul>
						</div>

					</div>
					<!-- /.box-footer-->

					<!--订单信息/--> <!--工具栏-->
					<div class="box-tools text-center">
						<button type="submit" class="btn bg-maroon" id="saveId">选择</button>
						<button type="button" class="btn bg-default"
								onclick="history.back(-1);">返回</button>
					</div>
				<!--工具栏/-->
				</section>
				<!-- 正文区域 /-->
			</form>
		</div>
		<!-- 内容区域 /-->

		<!-- 底部导航 -->
		<footer class="main-footer">
		<div class="pull-right hidden-xs">
			<b>Version</b> 1.0.8
		</div>
		<strong>Copyright &copy; 2014-2017 <a
			href="http://www.itcast.cn">研究院研发部</a>.
		</strong> All rights reserved. </footer>
		<!-- 底部导航 /-->

	</div>


	<script
		src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
	<script>
		$.widget.bridge('uibutton', $.ui.button);
	</script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min(1).js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown(1).js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions(1).js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
	<script
		src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>

	<script>

		//改变页数操作
		function changePageSizes() {
			//获取下拉框的值
			var pageSize = $("#changePageSize").val();

			//向服务器发送请求，改变每页显示条数
			location.href = "${pageContext.request.contextPath}/orders/selectProduct.do?page=1&size="+ pageSize;

		}

		//选择事件
		function selectP() {
			var arrId = [];
			//判断是否选中
			if ($("input[name='ids']").is(':checked')){
				//循环是否选中，若选中则将该值存入数组arrId中
				$("input[name='ids']:checked").each(function () {
					arrId.push($(this).val());
					window.location.href="${pageContext.request.contextPath}/orders/getProductById.do?pid="+arrId;
				})
			} else {
				alert("未选中！");
			}
		}

		$(document).ready(function() {
			// 选择框
			$(".select2").select2();

			// WYSIHTML5编辑器
			$(".textarea").wysihtml5({
				locale : 'zh-CN'
			});
			// 全选操作 
			$("#selall").click(function() {

				var clicks = $(this).is(':checked');
				if (!clicks) {
					$("#dataList td input[type='checkbox']").iCheck("uncheck");
					//保存按钮禁用
					$("#saveId").attr("disabled","disabled");
				} else {
					$("#dataList td input[type='checkbox']").iCheck("check");
					//保存按钮开启
					$("#saveId").removeAttr("disabled");
				}
				$(this).data("clicks", !clicks);
			});
		});

		//复选框事件 选择按钮禁用及开启
		function checkOnclick(ids) {
			if (ids.checked == true){
				$("#saveId").removeAttr("disabled");
			}else {
				$("#saveId").attr("disabled","disabled");
			}
		}
		//选择按钮禁用
		$(function () {
			$("#saveId").attr("disabled","disabled");
		})

		// 设置激活菜单
		function setSidebarActive(tagUri) {
			var liObj = $("#" + tagUri);
			if (liObj.length > 0) {
				liObj.parent().parent().addClass("active");
				liObj.addClass("active");
			}
		}
	</script>
	<script type="text/javascript">
		//显示选择每页显示多少条数据
		$(function () {
			$("#changePageSize option").each(function () {
				<%--${pageInfo.pageSize} 显示条数 --%>
				if($(this).val() == ${pageInfo.pageSize}){
					<%--alert(${pageInfo.pageSize});--%>
					//$("#changePageSize option[value='3']").attr("selected",true);
					$("select option").eq(${pageInfo.pageSize}-1).prop("selected",'selected');
				}
			})

		})
	</script>


</body>

</html>