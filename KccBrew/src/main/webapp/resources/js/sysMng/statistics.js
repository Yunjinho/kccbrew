/**
 * 장비 별 AS 건수
 * @param date 날짜
 * @returns
 */
function machineByYear(data){
	Highcharts.chart('machineByYear', {
		    chart: {
		        type: 'column'
		    },
		    title: {
		        align: 'left',
		        text: data[1].date+', 장비별  AS 건수'
		    },
		    accessibility: {
		        announceNewData: {
		            enabled: true
		        }
		    },
		    xAxis: {
		        type: 'category'
		    },
		    yAxis: {
		        title: {
		            text: 'AS 건수'
		        }
		    },
		    legend: {
		        enabled: false
		    },
		    plotOptions: {
		        series: {
		            borderWidth: 0,
		            dataLabels: {
		                enabled: true,
		                format: '{point.y}건'
		            }
		        }
		    },
		    series: [
		        {
		            name: 'AS 건수',
		            colorByPoint: true,
		            data: data[0]
		        }
		    ]
		});
}

/**
 * 장비 별 AS 건수
 * @param date 날짜
 * @returns
 */
function machineByYearAjax(date){
	$.ajax({
	    type : "POST",           // 타입 (get, post, put 등등)
	    url : "/machine-by-year",           // 요청할 서버url
	    dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
	    data : { 
			'date' : date,
		},
	    success : function(data) { // 결과 성공 콜백함수
	    	machineByYear(data);
	    }
	});
}
/**
 * 평점 상위 5명
 * @param date 날짜
 * @returns
 */
function highRankMecha(data){
	Highcharts.chart('highRankMecha', {
		chart: {
			type: 'column'
		},
		title: {
			align: 'left',
			text: data[1].date+', 평점 상위 기사 (이름 , ID)'
		},
		accessibility: {
			announceNewData: {
				enabled: true
			}
		},
		xAxis: {
			type: 'category'
		},
		yAxis: {
			title: {
				text: '평점'
			}
		},
		legend: {
			enabled: false
		},
		plotOptions: {
			series: {
				borderWidth: 0,
				dataLabels: {
					enabled: true,
					format: '{point.y}점'
				}
			}
		},
		series: [
			{
				name: '기사 평점',
				colorByPoint: true,
				data: data[0]
			}
			]
	});
}

/**
 * 평점 상위 5명
 * @param date 날짜
 * @returns
 */
function highRankMechaAjax(date){
	$.ajax({
		type : "POST",           // 타입 (get, post, put 등등)
		url : "/high-rank-mecha",           // 요청할 서버url
		dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
		data : { 
			'date' : date,
		},
		success : function(data) { // 결과 성공 콜백함수
			highRankMecha(data);
		}
	});
}
/**
 * 평점 하위 5명
 * @param date 날짜
 * @returns
 */
function lowRankMecha(data){
	Highcharts.chart('lowRankMecha', {
		chart: {
			type: 'column'
		},
		title: {
			align: 'left',
			text: data[1].date+', 평점 하위 기사 (이름 , ID) '
		},
		accessibility: {
			announceNewData: {
				enabled: true
			}
		},
		xAxis: {
			type: 'category'
		},
		yAxis: {
			title: {
				text: '평점'
			}
		},
		legend: {
			enabled: false
		},
		plotOptions: {
			series: {
				borderWidth: 0,
				dataLabels: {
					enabled: true,
					format: '{point.y}점'
				}
			}
		},
		series: [
			{
				name: '기사 평점',
				colorByPoint: true,
				data: data[0]
			}
			]
	});
}
/**
 * 평점 하위 5명
 * @param date 날짜
 * @returns
 */
function lowRankMechaAjax(date){
	$.ajax({
		type : "POST",           // 타입 (get, post, put 등등)
		url : "/low-rank-mecha",           // 요청할 서버url
		dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
		data : { 
			'date' : date,
		},
		success : function(data) { // 결과 성공 콜백함수
			lowRankMecha(data);
		}
	});
}
/**
 * 장비별 재접수율 변화
 * @param date
 * @returns
 */
function reapplyRateByMachine(data){
	Highcharts.chart('reapplyRateByMachine', {

	    title: {
	        text: data[1].date+', 재접수율 (%)',
	        align: 'left'
	    },

	    yAxis: {
	        title: {
	            text: '재접수율 (%)'
	        }
	    },

	    legend: {
	        layout: 'vertical',
	        align: 'right',
	        verticalAlign: 'middle'
	    },

	    plotOptions: {
	        series: {
	            label: {
	                connectorAllowed: false
	            },
	            pointStart: 01	
	        }
	    },

	    series: data[0],

	    responsive: {
	        rules: [{
	            condition: {
	                maxWidth: 500
	            },
	            chartOptions: {
	                legend: {
	                    layout: 'horizontal',
	                    align: 'center',
	                    verticalAlign: 'bottom'
	                }
	            }
	        }]
	    }

	});

}
/**
 * 장비별 재접수율 변화
 * @param date
 * @returns
 */
function reapplyRateByMachineAjax(date){
	$.ajax({
		type : "POST",           // 타입 (get, post, put 등등)
		url : "/reapply-rate-by-machine",           // 요청할 서버url
		dataType : "json",       // 데이터 타입 (html, xml, json, text 등등)
		data : { 
			'date' : date,
		},
		success : function(data) { // 결과 성공 콜백함수
			reapplyRateByMachine(data);
		}
	});
}
function moveBeforeYear(){
	var year=$("input[name=year]").val();
	year*=1;
	year-=1;
	$("input[name=year]").val(year);
	
	machineByYearAjax(year);
	highRankMechaAjax(year);
	lowRankMechaAjax(year);
	reapplyRateByMachineAjax(year);
}

function moveNextYear(){
	var year=$("input[name=year]").val();
	year*=1;
	year+=1;
	
	$("input[name=year]").val(year);
	machineByYearAjax(year);
	highRankMechaAjax(year);
	lowRankMechaAjax(year);
	reapplyRateByMachineAjax(year);
}
function downExcel(flag){
	
	var year=$("input[name=year]").val();
	
	$.ajax({
		type : "POST",           // 타입 (get, post, put 등등)
	    url : "/download-statistics",           // 요청할 서버url
	    dataType : "text",       // 데이터 타입 (html, xml, json, text 등등)
	    data : {
	    	'year': year,
		},
	    success : function(data) { // 결과 성공 콜백함수
	    	alert("다운로드가 완료되었습니다.")
	    }
	})
}
window.onload=function(){
	var now = new Date();
	dateFormat='YYYY';
	dateFormat=dateFormat
					.replace('YYYY',now.getFullYear())
	machineByYearAjax(dateFormat);
	highRankMechaAjax(dateFormat);
	lowRankMechaAjax(dateFormat);
	reapplyRateByMachineAjax(dateFormat);
	
}