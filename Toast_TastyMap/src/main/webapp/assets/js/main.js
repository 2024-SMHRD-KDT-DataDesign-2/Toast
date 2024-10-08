// 사이드바 열기/닫기 기능
document.getElementById("menuBtn").addEventListener("click", function() {
	document.getElementById("mySidebar").style.width = "250px"; // 사이드바 열기
});

document.getElementById("closeBtn").addEventListener("click", function() {
	document.getElementById("mySidebar").style.width = "0"; // 사이드바 닫기
});

// 지도 초기화
var map = new naver.maps.Map('map', {
	center: new naver.maps.LatLng(37.5666805, 126.9784147),
	zoom: 13,
	mapTypeId: naver.maps.MapTypeId.NORMAL
});

// 현재 위치 성공 시
function onSuccessGeolocation(position) {
	var location = new naver.maps.LatLng(position.coords.latitude, position.coords.longitude);
	map.setCenter(location);  // 위치 중심으로 지도 이동
	map.setZoom(13);  // 줌 레벨 설정

	// 현재 위치에 마커 추가
	var marker = new naver.maps.Marker({
		position: location,
		map: map,
	});

	// 현재 위치 정보 콘솔 출력
	console.log('Coordinates: ' + location.toString());
}

// 위도, 경도 사용해 지도에 마커 표시
$(function() {

	initMap();

});

function initMap() {

	var areaArr = new Array();

	$.ajax({
		url: 'http://localhost:8081/TastyMap/JsonService',
		method: 'GET',
		dataType: 'json',
		success: function(data) {


			for (let i = 0; i < data.length; i++) {
				let place = data[i];

				let placeInfo = {
					location: place.place_name,
					category: place.category,
					address: place.place_addr,
					tel: place.place_tel,
					lat: place.place_lat.toString(),
					lng: place.place_lon.toString()
				};
				areaArr.push(placeInfo);
			}
			console.log(areaArr);
			let markers = new Array(); // 마커 정보를 담는 배열
			let infoWindows = new Array(); // 정보창을 담는 배열

			for (var i = 0; i < areaArr.length; i++) {
				// 위치를 담은 배열의 길이만큼 for문으로 마커와 정보창 채우기

				// 마커
				var marker = new naver.maps.Marker({
					map: map,
					title: areaArr[i].location, // 식당명
					position: new naver.maps.LatLng(areaArr[i].lat, areaArr[i].lng), // 위치의 위도 경도 넣기
					icon: {
						url: 'images/blue_ribbon.png',			// 이미지 경로	
						scaledSize: new naver.maps.Size(30, 30), // 마커 크기
					}
				});

				// 정보창 내용
				var contentString = [
					'<div style="width:400px;text-align:center;padding:10px;">' + '<a href="#"><b>'
					+ areaArr[i].location + '</b></a>' + ' ' + '<span style="font-size:12px">' + areaArr[i].category + '</span>' + '<br>'
					+ '📍 ' + areaArr[i].address + '<br>'
					+ '📞 ' + areaArr[i].tel + '<br>'
					+ '</div>'
				].join('');

				// 정보창
				var infoWindow = new naver.maps.InfoWindow({
					content: contentString,
					borderWidth: 3,
					anchorSize: new naver.maps.Size(30, 30),
					backgroundColor: "#EFF8FB",
					borderColor: "#0040FF",
					anchorColor: "#EFF8FB"
				}); // 클릭했을 때 띄워줄 정보 HTML 작성

				markers.push(marker); // 생성한 마커를 배열에 담는다.
				infoWindows.push(infoWindow); // 생성한 정보창을 배열에 담는다.
			}

			function getClickHandler(seq) {

				return function(e) {  // 마커를 클릭하는 부분
					var marker = markers[seq], // 클릭한 마커의 시퀀스로 찾는다.
						infoWindow = infoWindows[seq]; // 클릭한 마커의 시퀀스로 찾는다.

					if (infoWindow.getMap()) {
						infoWindow.close();
					} else {
						infoWindow.open(map, marker); // 표출
					}
				}
			}

			for (var i = 0, ii = markers.length; i < ii; i++) {
				console.log(markers[i], getClickHandler(i));
				naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i)); // 클릭한 마커 핸들러
			}
		},
		error: function(xhr, status, error) {
			console.error("AJAX 요청 실패:", error);
		}
	});

	/*var areaArr = new Array();
	areaArr.push(
		{location : '광주광역시청' , category : '', address : '', open : '', close : '', tel : '', lat : '35.160011' , lng : '126.851312'},  // 광주광역시청 중심좌표
						 {location : '송원대학교' , category : '', address : '', open : '', close : '', tel : '', lat : '35.108666' , lng : '126.874722'},  // 송원대학교 중심좌표
						 {location : '돌매순두부' , category : '한식', address : '광주광역시 남구 송암로 57-1 (송하동)', open : '', close : '', tel : '062-674-3355', lat : '35.109999' , lng : '126.876567'},  // 돌매순두부 중심좌표
						 {location : 'CGI센터' , category : '', address : '', open : '', close : '', tel : '', lat : '35.110854' , lng : '126.877339'},  // CGI센터 중심좌표
						 {location : '남구청 차량사업소' , category : '', address : '', open : '', close : '', tel : '', lat : '35.111676' , lng : '126.877023'}  // 남구청 차량사업소 중심좌표
		
	);
	console.log(areaArr)*/

	/*let markers = new Array(); // 마커 정보를 담는 배열
	let infoWindows = new Array(); // 정보창을 담는 배열

	for (var i = 0; i < areaArr.length; i++) {
		// 위치를 담은 배열의 길이만큼 for문으로 마커와 정보창 채우기

		// 마커
		var marker = new naver.maps.Marker({
			map: map,
			title: areaArr[i].location, // 식당명
			position: new naver.maps.LatLng(areaArr[i].lat, areaArr[i].lng), // 위치의 위도 경도 넣기
			icon: {
				url: 'images/blue_ribbon.png',			// 이미지 경로	
				scaledSize: new naver.maps.Size(30, 30), // 마커 크기
			}
		});

		// 정보창 내용
		var contentString = [
			'<div style="width:400px;text-align:center;padding:10px;">' + '<a href="#"><b>'
			+ areaArr[i].location + '</b></a>' + ' ' + '<span style="font-size:12px">' + areaArr[i].category + '</span>' + '<br>'
			+ '📍 ' + areaArr[i].address + '<br>'
			+ '📞 ' + areaArr[i].tel + '<br>'
			+ '</div>'
		].join('');

		// 정보창
		var infoWindow = new naver.maps.InfoWindow({
			content: contentString,
			borderWidth: 3,
			anchorSize: new naver.maps.Size(30, 30),
			backgroundColor: "#EFF8FB",
			borderColor: "#0040FF",
			anchorColor: "#EFF8FB"
		}); // 클릭했을 때 띄워줄 정보 HTML 작성

		markers.push(marker); // 생성한 마커를 배열에 담는다.
		infoWindows.push(infoWindow); // 생성한 정보창을 배열에 담는다.
	}

	function getClickHandler(seq) {

		return function(e) {  // 마커를 클릭하는 부분
			var marker = markers[seq], // 클릭한 마커의 시퀀스로 찾는다.
				infoWindow = infoWindows[seq]; // 클릭한 마커의 시퀀스로 찾는다.

			if (infoWindow.getMap()) {
				infoWindow.close();
			} else {
				infoWindow.open(map, marker); // 표출
			}
		}
	}

	for (var i = 0, ii = markers.length; i < ii; i++) {
		console.log(markers[i], getClickHandler(i));
		naver.maps.Event.addListener(markers[i], 'click', getClickHandler(i)); // 클릭한 마커 핸들러
	}*/
}

// 위치 정보 오류 처리
function onErrorGeolocation() {
	var center = map.getCenter();
	console.error('Geolocation failed.');
}

// 브라우저에서 Geolocation API 사용
$(window).on("load", function() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(onSuccessGeolocation, onErrorGeolocation);
	} else {
		console.error("Geolocation not supported");
	}
});
