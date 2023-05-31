<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- myPhysiqueList -->
<%@ include file="../common/common.jsp"%>
<%@ include file="../common/top.jsp"%>
<%@ include file="../common/adminBootTop.jsp"%>
<%@ include file="myHealthTop.jsp"%>
<body style="background-color: #FEF9E7">
	<div class="pagetitle">
		<h1>
			<i class="bi bi-list toggle-sidebar-btn"></i> 신체정보
		</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="health.ht">Home</a></li>
				<li class="breadcrumb-item active">신체정보</li>
			</ol>
		</nav>
	</div>

	<div class="row">
		<div class="col-lg-12" >
			<!-- Default Card -->
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">신체정보</h5>
				</div>
			</div>
		</div>
		
		<div class="col-lg-12" >
			<!-- Default Card -->
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">이번달 체중 변화</h5>
				</div>
			</div>
		</div>
		
		<div class="col-lg-12" >
			<!-- Default Card -->
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">이번달 골격근량 변화</h5>
				</div>
			</div>
		</div>
		
		<div class="col-lg-12" >
			<!-- Default Card -->
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">이번달 체지방률 변화</h5>
				</div>
			</div>
		</div>
	</div><!-- row -->

</body>
<%@ include file="myHealthBottom.jsp"%>
<%@ include file="../common/adminBootBottom.jsp"%>
<%@ include file="../common/bottom.jsp"%>