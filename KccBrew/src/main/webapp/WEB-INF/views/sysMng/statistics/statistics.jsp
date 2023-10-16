<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="<c:url value="resources/js/sysMng/statistics.js"/>"></script>
<link rel="stylesheet" href="/resources/css/statistics/statistics.css" />
<link rel="stylesheet" href="/resources/css/asMng/content-template.css" />
</head>
<body>
<div class="contents_warp">
<div id="page-mask">
	<div id="page-container" class="">
		<div id="page-content" class="clearfix">
			<div id="page-content-wrap">
			
				<div class="page-content-navigation">
				<h2 class="heading">통계</h2>
					<ol class="breadcrumb">
								<li>
								<div class="header-icon-background">
								<a href="/">
									<img
											src="<c:url value='/img/common/free-icon-house.png' />"
											alt="Check List" class="header-icon" />
										</a>
								</div>
							</li>
							<li>
									<div class="header-icon-background">
										<img
											src="<c:url value='/img/common/free-icon-arrow-right.png' />"
											alt="Check List" class="header-icon" />
									</div>
								</li>
								
						<li class="breadcrumb-home"><a href="#">시스템 관리</a></li>
							<li>
									<div class="header-icon-background">
										<img
											src="<c:url value='/img/common/free-icon-arrow-right.png' />"
											alt="Check List" class="header-icon" />
									</div>
								</li>
						<li><a href="<c:url value='/statistics' />">통계</a></li>
					</ol>
				</div>
				
				<div class="navi">
					<img src="/resources/img/asMng/free-icon-left-arrow-271220.png" alt="left" onclick="moveBeforeYear()" class="navi-img"/>
					<input type="text" value="${year}" name="year" readonly>
					<img src="/resources/img/asMng/free-icon-right-arrow-271228.png" alt="right"onclick="moveNextYear()" class="navi-img"/>
				</div>
				<div>
					<p class="download-excel" onclick="downExcel()">통계 다운로드</p>
				</div>
				<div class="statistics">
					<div>
						<div>
							<div id="machineByYear" style="width:100%; height:400px;"></div>
						</div>
						<div>
							<div id="reapplyRateByMachine" style="width:100%; height:400px;"></div>
						</div>
					</div>
					<div>
						<div>
							<div id="highRankMecha" style="width:100%; height:400px;"></div>
						</div>
						<div>
							<div id="lowRankMecha" style="width:100%; height:400px;"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>