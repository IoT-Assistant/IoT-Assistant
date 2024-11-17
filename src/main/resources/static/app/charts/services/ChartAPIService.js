
chartsModule.service ("ChartAPIService",function(RestAPIService, $q){
	
	let self = this;	
	
	let chartsBaseUri = "charts/";

	self.getCharts = function () {
		let deferred = $q.defer();
		RestAPIService.get(chartsBaseUri).then(function(objectResponse) {
			deferred.resolve(getChartsFromResponse(objectResponse));
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	

	function getChartsFromResponse(objectResponse) {
		let charts = [];
		objectResponse.charts.forEach(chartObject => {
			let chart = getChartFromObjectResponse(chartObject);
			charts.push(chart);
		})
		return charts;
	}
	
	function getChartFromObjectResponse(chartObject) {
		let chart = new SensorChart();
		chart.setSensorName(chartObject.sensorName);
		let property = PropertyMapper.map(chartObject.propertyObserved);
		chart.setSensorProperty(property);
		chart.setType(chartObject.type);
		chart.setId(chartObject.id);
		chart.setTotalInterval(chartObject.totalInterval);
		chart.setSampleInterval(chartObject.sampleInterval);
		let sensorChartSamples = [];
		chartObject.sensorsChartSamples.forEach(sampleObject => {
			let sample = new SensorChartSample(sampleObject.value, sampleObject.date);
			sensorChartSamples.push(sample);
		})
		chart.setSensorChartSamples(sensorChartSamples); 
		return chart;
	}
	
	
	self.installChart = function (chart) {
		let deferred = $q.defer();
		let newChart = createNewChartObjRequest(chart);
		RestAPIService.post(chartsBaseUri, newChart).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	function createNewChartObjRequest(chart) {
		let newChartObject = {};
		newChartObject.sensorName = chart.getSensorName();
		newChartObject.propertyObserved = chart.getSensorProperty();
		newChartObject.type = chart.getType();
		newChartObject.totalInterval = chart.getTotalInterval();
		newChartObject.sampleInterval = chart.getSampleInterval();
		return newChartObject;
	}
	
	self.deleteChart = function (chartId) {
		let deferred = $q.defer();
		RestAPIService.delete(chartsBaseUri.concat(chartId)).then(function(objectResponse) {
			deferred.resolve(objectResponse);
		}, function errorCallback(errorResponse) {
			deferred.reject(errorResponse);
		});
		return deferred.promise ;
	}
	
	
	

});

