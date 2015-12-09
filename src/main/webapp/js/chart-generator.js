var chartResult;

function generateChart(chartType) {
	if($j("#tree input[type='checkbox']").size() == 0) {
		$j("#linechart").html("No build data retrieved.	 You may need to select a Module.  Make sure you have configured your Job to publish JUnit Test Reports.");
		return;
	}

	if (reevaluateChartData){
		chartResult = getChartData(getSelectedRows(), chartType.type);
		reevaluateChartData = false;
	}
	resetCharts();

	if(chartType.type === "runtime") {
		console.log("showing runtimes");

		if (chartType.line) {
			generateRuntimeLineChart();
		}
		if (chartType.pie) {
			generateRuntimePieChart();
		}
	} else if(chartType.type === "passfail") {
		if (chartType.line) {
			generateLineChart();
		}

		if (chartType.bar) {
			generateBarChart();
		}

		if (chartType.pie) {
			generatePieChart();
		}
	} else {
		if (chartType.line) {
			generateCoverageLineChart();
		}
	}
}

function resetCharts() {
	$j("#linechart").html("");
	$j("#barchart").html("");
	$j("#piechart").html("");
}

function generateRuntimeLineChart() {
	var chartCategories = [];
	var chartData = {
		"Runtime" : []
	};

	for(var key in chartResult) {
		if(chartResult.hasOwnProperty(key)){
			var buildResult = chartResult[key];
			chartCategories.push(key);
			chartData["Runtime"].push(Math.round(eval(buildResult["Runtime"])*1000)/1000);
		}
	}
	$j(function () {
		$j("#linechart").highcharts(getRuntimeLineChartConfig(chartCategories, chartData))
	});
}

function generateRuntimePieChart(inputData) {
	var pieChartResult = getChartData(getTestRows(), "runtime");
	var buildNumber = inputData == undefined ? Object.keys(pieChartResult).pop() : inputData;
	var resultTitle = "Tests runtime details for " + buildNumber;

	var slow = 0;
	var medi = 0;
	var fast = 0;

	var runtimeArray = pieChartResult[buildNumber]["RuntimeArray"]
	var lowThreshold = parseFloat($j("#runTimeLowThreshold").val());
	var highThreshold = parseFloat($j("#runTimeHighThreshold").val());

	runtimeArray.each(function (time) {
		if (time < lowThreshold) {
			fast ++;
		} else if (time >= highThreshold) {
			slow ++;
		} else {
			medi ++;
		}
	});
	inputData = [
		['fast',   fast],
		['slow',   slow],
		['medium', medi]
	];
	$j("#piechart").highcharts(getRuntimePieChartConfig(inputData, resultTitle))
}

function getRuntimeLineChartConfig(chartCategories, chartData) {
	var seriesVar = [
		{
			name: 'Runtime',
			data: chartData["Runtime"]
		}
	];
	var colorsVar = [statusColors["runtime"]];
	var clickFunc = {
		click: function (e) {
			generateRuntimePieChart(this.category);
		}
	};
	var titleVar = {
		text: 'Build Runtimes',
		x:-20
	};
	var yAxisVar = {
		title: {
			text: 'Runtime'
		},
		plotLines: [
			{
				value: 0,
				width: 1,
				color: '#808080'
			}
		],
		floor: 0
	};
	var linechart = {
		title: titleVar,
		xAxis: {
			title: {
				text: 'Build number'
			},
			categories: chartCategories,
			allowDecimals: false
		},
		yAxis: yAxisVar,
		credits: {
			enabled: false
		},
		tooltip: {
			headerFormat: '<b>Build no: {point.x}</b><br>',
			valueSuffix: ' sec',
			shared: true,
			crosshairs: true
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle',
			borderWidth: 0
		},
		colors : colorsVar,
		plotOptions: {
			series: {
				cursor: 'pointer',
				point: {
					events: clickFunc
				}
			}
		},
		series: seriesVar
	};
	return linechart;
}

function generateLineChart() {
	var chartCategories = [];
	var chartData = {
		"Failed" : [],
		"Passed" : [],
		"Flaked" : [],
		"Skipped" : [],
		"Total" : []
	};

	for(var key in chartResult) {
		if(chartResult.hasOwnProperty(key)){
			var buildResult = chartResult[key];
			chartCategories.push(key);
			chartData["Failed"].push(buildResult["Failed"]);
			chartData["Passed"].push(buildResult["Passed"]);
			chartData["Flaked"].push(buildResult["Flaked"]);
			chartData["Skipped"].push(buildResult["Skipped"]);
			chartData["Total"].push(buildResult["Total"]);
		}
	}
	$j(function () {
		$j("#linechart").highcharts(getLineChartConfig(chartCategories, chartData))
	});
}

function generateCoverageLineChart() {
	var chartCategories = [];
	var chartData = {
		"Classes" : [],
		"Conditionals" : [],
		"Files" : [],
		"Lines" : [],
		"Methods" : [],
		"Packages" : []
	};

	for(var key in coverageData) {
		if(coverageData.hasOwnProperty(key)){
			var buildResult = coverageData[key];
			chartCategories.push(key);
			chartData["Classes"].push(Math.round(eval(buildResult["classes"])*10000)/100);;
			chartData["Conditionals"].push(Math.round(eval(buildResult["conditionals"])*10000)/100);
			chartData["Files"].push(Math.round(eval(buildResult["files"])*10000)/100);
			chartData["Lines"].push(Math.round(eval(buildResult["lines"])*10000)/100);
			chartData["Methods"].push(Math.round(eval(buildResult["methods"])*10000)/100);
			chartData["Packages"].push(Math.round(eval(buildResult["packages"])*10000)/100);
		}
	}
	$j(function () {
		$j("#linechart").highcharts(getCoverageLineChartConfig(chartCategories, chartData))
	});
}

function generateBarChart() {
	var chartCategories = [];
	var chartData = {
		"Failed" : [],
		"Passed" : [],
		"Flaked" : [],
		"Skipped" : []
	};

	for(var key in chartResult) {
		if(chartResult.hasOwnProperty(key)) {
			var buildResult = chartResult[key];
			chartCategories.push(key);
			chartData["Failed"].push(buildResult["Failed"]);
			chartData["Passed"].push(buildResult["Passed"]);
			chartData["Flaked"].push(buildResult["Flaked"]);
			chartData["Skipped"].push(buildResult["Skipped"]);
		}
	}

	var barChartData = [
		{
			name: "Passed",
			data: chartData["Passed"]
		},
		{
			name: "Failed",
			data: chartData["Failed"]
		},
		{
			name: "Flaked",
			data: chartData["Flaked"]
		},
		{
			name: "Skipped",
			data: chartData["Skipped"]
		},
	];
	$j(function () {
		$j("#barchart").highcharts(getBarChartConfig(chartCategories, barChartData))
	});
}

function generatePieChart(inputData, resultTitle) {
	if(inputData == undefined){
		var total = 0;
		var passed = 0;
		var failed = 0;
		var flaked = 0;
		var skipped = 0;

		for(var key in chartResult) {
			if(chartResult.hasOwnProperty(key)) {
				total++;
				var buildResult = chartResult[key];
				if(buildResult["Failed"] > 0){
					failed ++;
				} else if(buildResult["Flaked"] > 0) {
					flaked++;
				} else if(buildResult["Passed"] > 0) {
					passed++;
				} else {
					skipped ++;
				}

				inputData = calculatePercentage(passed,failed,flaked,skipped,total);
			}
		}
	}
	$j(function () {$j("#piechart").highcharts(getPieChartConfig(inputData,resultTitle))})
}

function getChartData(selectedRows, type) {
	var chartResult = {};
	var baseRows;

	if(selectedRows.size() > 0) {
		baseRows = selectedRows;
	} else {
		baseRows = $j("[parentclass='base']");
	}

	$j.each(baseRows, function(index, baseRow) {
		var buildResults = $j(baseRow).find(".build-result.table-cell");
		$j.each(buildResults, function(index, buildResult){
			var jsonResult = $j.parseJSON($j(buildResult).attr("data-result"));
			var buildNumber = jsonResult["buildNumber"];

			if(type == "runtime") {
				var tempBuildResult = {
					"Runtime" : jsonResult["totalTimeTaken"] ? jsonResult["totalTimeTaken"] : 0,
					"RuntimeArray": jsonResult["totalTimeTaken"] !== undefined ? [jsonResult["totalTimeTaken"]] : []
				}
				if(chartResult[buildNumber]) {
					var tempChartBuildResult = chartResult[buildNumber];
					var result = {
						"Runtime": tempChartBuildResult["Runtime"] + tempBuildResult["Runtime"],
						"RuntimeArray": tempChartBuildResult["RuntimeArray"].concat(tempBuildResult["RuntimeArray"])
					}
					chartResult[buildNumber] = result;
				} else {
					chartResult[buildNumber] = tempBuildResult;
				}
			} else {
				var tempBuildResult = {
					"Failed" :   jsonResult["totalFailed"] ? jsonResult["totalFailed"] : 0,
					"Skipped" :   jsonResult["totalSkipped"] ? jsonResult["totalSkipped"] : 0,
					"Flaked" :   jsonResult["totalFlaky"] ? jsonResult["totalFlaky"] : 0,
					"Passed" :  (jsonResult["totalPassed"] - jsonResult["totalFlaky"]) ? (jsonResult["totalPassed"] - jsonResult["totalFlaky"]): 0,
					"Total" :   jsonResult["totalTests"] ? jsonResult["totalTests"] : 0
				};
				if(chartResult[buildNumber]) {
					var tempChartBuildResult = chartResult[buildNumber];
					var result = {
						"Failed": tempChartBuildResult["Failed"] + tempBuildResult["Failed"],
						"Skipped": tempChartBuildResult["Skipped"] + tempBuildResult["Skipped"],
						"Flaked": tempChartBuildResult["Flaked"] + tempBuildResult["Flaked"],
						"Passed": tempChartBuildResult["Passed"] + tempBuildResult["Passed"],
						"Total": tempChartBuildResult["Total"] + tempBuildResult["Total"]
					}
					chartResult[buildNumber] = result;
				} else {
					chartResult[buildNumber] = tempBuildResult;
				}
			}
		});
	});
	return chartResult;
}

function getSelectedRows() {
	var checkedRows = $j("#tree").find(":checked");
	var selectedRows = [];

	checkedRows.each(function () {
		var parentClass = $j(this).attr("parentclass");
		var parent = $j("." + parentClass).find("input[type='checkbox']")
		if($j.inArray(parent.get(0), checkedRows) < 0) {
			selectedRows.push($j(this).parent().parent());
		}
	});
	return selectedRows;
}

function getTestRows() {
	var $testRows = $j($j("#tree .table-row").filter(function(index, elem) {
		return !($j($j(elem).children().get(2)).children().length > 0);
	}));
	var isSomethingChecked = $j("#tree").find(":checked").length > 0;
	if(isSomethingChecked) {
		$testRows = $testRows.filter(function(index, elem) {
			return $j(elem).find(":checked").length > 0;
		});
	}
	return $testRows;
}

function getLineChartConfig(chartCategories, chartData) {
	var linechart = {
		title: {
			text: 'Build Status',
			x: -20 //center
		},
		xAxis: {
			title: {
				text: 'Build number'
			},
			categories: chartCategories,
			allowDecimals: false
		},
		yAxis: {
			title: {
				text: 'No of tests'
			},
			plotLines: [
				{
					value: 0,
					width: 1,
					color: '#808080'
				}
			],
			allowDecimals: false,
			floor: 0
		},
		credits: {
			enabled: false
		},
		tooltip: {
			headerFormat: '<b>Build no: {point.x}</b><br>',
			shared: true,
			crosshairs: true
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle',
			borderWidth: 0
		},
		colors : [
			statusColors["passed"],
			statusColors["failed"],
			statusColors["skipped"],
			statusColors["total"],
			statusColors["flaked"]
		],
		plotOptions: {
			series: {
				cursor: 'pointer',
				point: {
					events: {
						click: function (e) {
							var x1 = this.x;
							var category = this.category;
							var passed = this.series.chart.series[0].data[x1].y;
							var failed = this.series.chart.series[1].data[x1].y;
							var skipped = this.series.chart.series[2].data[x1].y;
							var total = this.series.chart.series[3].data[x1].y;
							var flaked = this.series.chart.series[4].data[x1].y;
							var resultTitle = 'Build details for build: '+category;
							generatePieChart(calculatePercentage(passed, failed, flaked, skipped, total), resultTitle);
						}
					}
				}
			}
		},
		series: [
			{
				name: 'Passed',
				data: chartData["Passed"]
			}, {
				name: 'Failed',
				data: chartData["Failed"]
			}, {
				name: 'Skipped',
				data: chartData["Skipped"]
			}, {
				name: 'Total',
				data:  chartData["Total"]
			}, {
				name: 'Flaked',
				data:  chartData["Flaked"]
			}
		]
	}

	return linechart;
}

function getCoverageLineChartConfig(chartCategories, chartData) {
	var linechart = {
		title: {
			text: 'Code Coverage',
			x: -20 //center
		},
		xAxis: {
			title: {
				text: 'Build number'
			},
			categories: chartCategories
		},
		yAxis: {
			title: {
				text: 'Percentage of Coverage'
			},
			plotLines: [
				{
					value: 0,
					width: 1,
					color: '#808080'
				}
			]
		},
		credits: {
			enabled: false
		},
		tooltip: {
			headerFormat: '<b>Build no: {point.x}</b><br>',
			shared: true,
			valueSuffix: ' %',
			crosshairs: true
		},
		legend: {
			layout: 'vertical',
			align: 'right',
			verticalAlign: 'middle',
			borderWidth: 0
		},
		colors : [
			statusColors["passed"],
			statusColors["failed"],
			statusColors["skipped"],
			statusColors["total"],
			statusColors["methods"],
			statusColors["packages"]
		],
		series: [
			{
				name: 'Classes',
				data: chartData["Classes"]
				//color: '#FD0505'
			}, {
				name: 'Conditionals',
				data: chartData["Conditionals"]
				//color: '#24A516'
			}, {
				name: 'Files',
				data: chartData["Files"]
				//color: '#AEAEAE'
			}, {
				name: 'Lines',
				data:  chartData["Lines"]
				//color: '#FDED72'
			}, {
				name: 'Methods',
				data:  chartData["Methods"]
				//color: '#800080'
			}, {
				name: 'Packages',
				data:  chartData["Packages"]
				//color: '#FF8040'
			}
		]
	}

	return linechart;
}

function getBarChartConfig(chartCategories, chartData) {
	var barchart = {
		chart: {
			type: 'bar'
		},
		title: {
			text: 'Build Status',
			x: -20 //center
		},
		xAxis: {
			title: {
				text: 'Build number'
			},
			categories: chartCategories,
			allowDecimals: false
		},
		yAxis: {
			title: {
				text: 'No of tests'
			},
			plotLines: [
				{
					value: 0,
					width: 1,
					color: '#808080'
				}
			],
			allowDecimals: false
		},
		colors : [
			statusColors["passed"],
			statusColors["failed"],
			statusColors["flaked"],
			statusColors["skipped"]
		],
		credits: {
			enabled: false
		},
		tooltip: {
			headerFormat: '<b>Build no: {point.x}</b><br>',
			shared: true,
			crosshairs: true
		},
		legend: {
			reversed: true
		},
		plotOptions: {
			series: {
				stacking: 'normal'
			}
		},
		series: chartData
	}

	return barchart;
}

function calculatePercentage(passed, failed, flaked, skipped, total) {
	var failedPercentage = (failed * 100) / total;
	var skippedPercentage = (skipped * 100) / total;
	var passedPercentage = (passed * 100) / total;
	var flakedPercentage = (flaked * 100) / total;

	return [
		['Passed',	 passedPercentage],
		['Failed',	   failedPercentage],
		['Flaked',	   flakedPercentage],
		['Skipped',   skippedPercentage]
	];
}

function getPieChartConfig(inputData, resultTitle) {
	var pieChart = {
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: 1,
			plotShadow: false
		},
		credits: {
			enabled: false
		},
		title: {
			text: resultTitle ? resultTitle : 'Build details for all'
		},
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b>'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: true,
					format: '<b>{point.name}</b>: {point.percentage:.2f} %',
					style: {
						color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
					}
				}
			}
		},
		colors : [
			statusColors["passed"],
			statusColors["failed"],
			statusColors["flaked"],
			statusColors["skipped"],
			statusColors["total"]
		],
		series: [
			{
				type: 'pie',
				name: 'Build Detail',
				data: inputData
			}
		]
	}
	return pieChart;
}

function getRuntimePieChartConfig(inputData, resultTitle) {
	var pieChart = {
		chart: {
			plotBackgroundColor: null,
			plotBorderWidth: 1,
			plotShadow: false
		},
		credits: {
			enabled: false
		},
		title: {
			text: resultTitle ? resultTitle : 'Build details for all'
		},
		tooltip: {
			pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions: {
			pie: {
				allowPointSelect: true,
				cursor: 'pointer',
				dataLabels: {
					enabled: true,
					format: '<b>{point.name}</b>: {point.percentage:.1f} %',
					style: {
						color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
					}
				}
			}
		},
		colors : [
			statusColors["passed"],
			statusColors["failed"],
			statusColors["skipped"]
		],
		series: [
			{
				type: 'pie',
				name: 'Build Detail',
				data: inputData
			}
		]
	}
	return pieChart;
}
