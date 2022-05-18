// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkers(map) {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

$(document).ready(function() {
	callAptFromDb("숭인동", true);
});

function callAptFromDb(dong, setCenter) {
	$.ajax({
		url: "/HappyHouse/deal/getAptList",
		type: "get",
		dataType: "json",
		data: { "dong" : dong, "sortBy" : "aptName", "sortOrder" : "asc" },
		success: function(response) {
			makeListByJson(response, setCenter);
			setMarkers(null);
		},
		error: function(xhr, status, msg) {
			console.log("상태값 : " + status + " Http에러메시지 : " + msg);
		},
	});
}

function makeListByJson(data, setCenter) {
	for(var i=0; i<data.length; i++) {
		let current = data[i].dongName;
		let address = current + " " + data[i].jibun.trim();
		addressSearch(address, this, setCenter);
	}
}