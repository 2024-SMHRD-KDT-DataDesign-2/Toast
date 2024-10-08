function resetFilters() {
	const districtValue = '광주전체';
	const nullValue = null;

	// 필터 값 초기화
	document.getElementById('district').value = districtValue;
	document.getElementById('filter_parking').value = '';
	document.getElementById('filter_time').value = '';
	document.getElementById('filter_space').value = '';
	document.getElementById('filter_atmosphere').value = '';
	document.getElementById('filter_else').value = '';

	// 서버에 필터 초기화 요청 전송
	$.ajax({
		url: 'PlaceListService',  // 데이터를 처리할 서버
		method: 'GET',
		data: {
			district: districtValue,
			filter_parking: nullValue,
			filter_time: nullValue,
			filter_space: nullValue,
			filter_atmosphere: nullValue,
			filter_else: nullValue,
			reset: true
		},
		success: function(response) {
			// 필터링된 결과를 리스트에 업데이트
			$('#restaurant-list').html($(response).find('.restaurant-list').html());
		},
		error: function() {
			console.error("error: 필터값 초기화");
		}
	});
}


function loadDistrictData(district) {
	updateFilterState('district', district);
}

function filterParkingData(filter_parking) {
	updateFilterState('filter_parking', filter_parking);
}

function filterTimeData(filter_time) {
	updateFilterState('filter_time', filter_time);
}

function filterSpaceData(filter_space) {
	updateFilterState('filter_space', filter_space);
}

function filterAtmosphereData(filter_atmosphere) {
	updateFilterState('filter_atmosphere', filter_atmosphere);
}

function filterElseData(filter_else) {
	updateFilterState('filter_else', filter_else);
}


// 필터 상태 업데이트
function updateFilterState(filterName, filterValue) {
	$.ajax({
		url: 'PlaceListService',
		method: 'GET',
		data: { [filterName]: filterValue },  // 선택된 필터 값 전달
		success: function(response) {
			$('#restaurant-list').html($(response).find('.restaurant-list').html());
		},
		error: function() {
			console.error("error: 필터 상태 업데이트");
		}
	});
}

function goBack() {
	window.location.href = "main.jsp";
}
