<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				新建订单 <small>订单表单</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="${pageContext.request.contextPath}/index.jsp"><i
						class="fa fa-dashboard"></i> 首页</a></li>
				<li><a
					href="${pageContext.request.contextPath}/orders/findAll.do">订单管理</a></li>
				<li class="active">新建订单表单</li>
			</ol>
			</section>
			<!-- 内容头部 /-->

			<form action="${pageContext.request.contextPath}/orders/save.do" method="post" id="form_add">
				<!-- 正文区域 -->
				<section class="content">

				<div class="panel panel-default">
					<div class="panel-heading">联系人信息</div>
					<div class="row data-type">
							<%--<div class="col-md-2 title">会员</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control"
                                       placeholder="会员" name="name" value="">
                            </div>

                            <div class="col-md-2 title">联系人</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control"
                                       placeholder="联系人" name="nickname" value="">
                            </div>

                            <div class="col-md-2 title">手机号</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control"
                                       placeholder="手机号" name="phoneNum" value="">
                            </div>

                            <div class="col-md-2 title">邮箱</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control"
                                       placeholder="邮箱" name="email" value="">
                            </div>--%>

						<div class="col-md-2 title">联系人</div>
							<div class="col-md-4 data">
								<a href="${pageContext.request.contextPath}/orders/selectMember.do?page=1&size=8"
								   type="button" class="btn btn-default">选择联系人</a>
						</div>
						<div class="col-md-2 title">所选择的联系人为：</div>
							<div class="col-md-2 data">
								<input type="text" class="form-control" value="${member.nickname}" readonly="readonly">
								<input type="hidden" name="memberId" value="${member.id}">
							</div>

						</div>
				</div>

				<%--<div class="panel panel-default">
					<div class="panel-heading">订单产品信息</div>
					<div class="row data-type">

						<div class="col-md-2 title">产品信息</div>
						<div class="col-md-4 data">
								&lt;%&ndash;<select class="form-control" style="width: 100%"
										name="productStatus">
									<option value="0" selected="selected">产品1</option>
									<option value="1">产品2</option>
								</select>&ndash;%&gt;
							<a href="${pageContext.request.contextPath}/orders/selectProduct.do?page=1&size=8" type="button" class="btn btn-default">选择产品</a>
						</div>
						<div class="col-md-2 title">所选择的产品名称为：</div>
						<div class="col-md-2 data">
							<input type="text" class="form-control" value="${product.productName}" readonly="readonly">
							<input type="hidden" name="productId" value="${product.id}">
						</div>
					</div>
				</div>--%>

				<div class="panel panel-default">
					<div class="panel-heading">订单信息</div>
					<div class="row data-type">

						<div class="col-md-2 title">订单编号</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control" name="orderNum"
								placeholder="订单编号" value="">
						</div>
						<div class="col-md-2 title">出游人数</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control" name="peopleCount"
								   placeholder="出游人数" value="">
						</div>
						<div class="col-md-2 title">下单时间</div>
						<div class="col-md-4 data">
							<div class="input-group date">
								<div class="input-group-addon">
									<i class="fa fa-calendar"></i>
								</div>
								<input type="text" class="form-control pull-right"
                                       id="datepicker-a3" name="orderTime">
							</div>
						</div>


						<div class="col-md-2 title">订单描述</div>
						<div class="col-md-4 data">
							<input type="text" class="form-control" name="orderDesc"
								placeholder="订单描述" value="">
						</div>

						<div class="col-md-2 title">支付方式</div>
						<div class="col-md-4 data">
							<select class="form-control" style="width: 100%" name="payType">
								<option value="0" selected="selected">支付宝</option>
								<option value="1">微信</option>
								<option value="2">其他</option>
							</select>
						</div>

						<div class="col-md-2 title">订单状态</div>
						<div class="col-md-4 data">
							<select class="form-control" style="width: 100%" name="orderStatus">
								<option value="0" selected="selected">未支付</option>
								<option value="1">已支付</option>
							</select>
						</div>

					</div>
				</div>

				<div class="panel panel-default">
					<div class="panel-heading">游客信息</div>
						<div class="row data-type">

							<div class="col-lg-10 col-lg-offset-1 data">
								姓名 <input type="text" size="12" name="name" id="name" />&nbsp;&nbsp;
								性别 <input type="text" size="8" name="sex" id="sex" />&nbsp;&nbsp;
								电话 <input type="text" size="20" name="phoneNum" id="phoneNum"/>&nbsp;&nbsp;
								证件类型
								<select name="credentialsType" id="credentialsType">
									<option value="0" selected="selected">身份证</option>
									<option value="1">护照</option>
									<option value="2">军官证</option>
								</select>&nbsp;&nbsp;
								证件号码 <input type="text" size="21" name="credentialsNum" id="credentialsNum"/>&nbsp;&nbsp;
								人群
								<select name="travellerType" id="travellerType">
									<option value="0" selected="selected">成人</option>
									<option value="1">儿童</option>
								</select>&nbsp;&nbsp;
								<input type="button" value="添加" onclick="addInfo()">
							</div>
							<!--数据列表-->
							<table id="dataList" class="table table-bordered table-striped table-hover dataTable">
								<thead>
								<tr>
									<th class="">姓名</th>
									<th class="">性别</th>
									<th class="">手机号码</th>
									<th class="">证件类型</th>
									<th class="">证件号码</th>
									<th class="">人群</th>
								</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
				</div>

				<!--订单信息/--> <!--工具栏-->
				<div class="box-tools text-center">
					<button type="submit" class="btn bg-primary">保存</button>
					<a href="${pageContext.request.contextPath}/orders/findAll.do">
						<button type="button" class="btn bg-default">返回</button>
					</a>
				</div>
				<!--工具栏/--> </section>
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

	<script type="text/javascript">
		/*点击添加按钮把input的value值加入表单中，表单实现动态显示*/
		function addInfo() {
			var name = $("#name").val();
			var sex = $("#sex").val();
			var phone = $("#phoneNum").val();
			var credentialsNum = $("#credentialsNum").val();
			//要获取选中的显示值使用 text();
			var credentialsType = $("#credentialsType option:checked").val();
			var travellerType = $("#travellerType option:checked").val();

			var credentialsTypet = $("#credentialsType option:checked").text();
			var travellerTypet = $("#travellerType option:checked").text();

			if (name != null && name != "" && sex != null && sex != "" &&
				phone != null && phone != "" && credentialsNum != null && credentialsNum != "" &&
				credentialsType != null && credentialsType != "" && travellerType!=null && travellerType!=""){

				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/orders/saveTraveller.do",
					data:$("#form_add").serialize(),
					success:function (data) {
						$("#name").val(data.name);
						$("#sex").val(data.sex);
						$("#phoneNum").val(data.phoneNum);
						$("#credentialsType").val(data.credentialsType);
						$("#credentialsNum").val(data.credentialsNum);
						$("#travellerType").val(data.travellerType);
					}
				});

				var tr = $("<tr><td><input type='text' name='name1' size='10' value='"+name+"'> </td>" +
						"<td><input type='text' name='sex1' size='5' value='"+sex+"'> </td>" +
						"<td><input type='text' name='phoneNum1' size='12' value='"+phone+"'> </td>" +
						"<td><input type='text' size='8' value='"+credentialsTypet+"'> " +
						"<input type='hidden' name='credentialsType1' value='"+credentialsType+"'> </td>" +
						"<td><input type='text' name='credentialsNum1' size='15' value='"+credentialsNum+"'> </td>" +
						"<td><input type='text' size='6' value='"+travellerTypet+"'>" +
						"<input type='hidden' name='travellerType1' value='"+travellerType+"'> </td></tr>");
				/!*把内容追加到tbody中*!/
				$("tbody").append(tr);
				//把输入框的内容清空
				$("#name").val('');
				$("#sex").val('');
				$("#phoneNum").val('');
				$("#credentialsNum").val('');

			} else {
				alert("请把信息填写完整！");
			}

		}
		
	</script>

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
		$(document).ready(function() {
			// 选择框
			$(".select2").select2();

			// WYSIHTML5编辑器
			$(".textarea").wysihtml5({
				locale : 'zh-CN'
			});
		});

		// 设置激活菜单
		function setSidebarActive(tagUri) {
			var liObj = $("#" + tagUri);
			if (liObj.length > 0) {
				liObj.parent().parent().addClass("active");
				liObj.addClass("active");
			}
		}

		$(document).ready(function() {
			$('#datepicker-a3').datetimepicker({
				format : "yyyy-mm-dd hh:ii",
				autoclose : true,
				todayBtn : true,
				language : "zh-CN"
			});
		});

		$(document).ready(function() {
			// 激活导航位置
			setSidebarActive("order-manage");
			$("#datepicker-a3").datetimepicker({
				format : "yyyy-mm-dd hh:ii",

			});

		});
	</script>


</body>

</html>