let sortBy = "aptName";
let sortOrder = "asc";

// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkers(map) {
	for (var i = 0; i < markers.length; i++) {
		markers[i].setMap(map);
	}
}

function showApart(data) {
	let aptlist = ``;

	for(let i=0; i<data.length; i++) {
		aptlist += `<tr>
				<td>${data[i].aptName}</td>
				<td>${data[i].dongName}</td>
				<td>${data[i].area}</td>
				<td>${data[i].dealAmount}</td>
				<td>${data[i].dealYear}`+"/"+`${data[i].dealMonth}`+"/"+`${data[i].dealDay}</td>
				</tr>`;
	}
	$("#aptinfo").empty().append(aptlist);
	$("tr:first").css("background", "darkgray").css("color", "white");
	$("tr:even").css("background", "lightgray");
}

$(document).ready(function() {
	callAptFromDb("숭인동", true);
	$("#sortBy").change(function(){
		sortBy = $("#sortBy option:selected").val();
		callAptFromDb(selectDong);
	});
	$("#sortOrder").change(function(){
		sortOrder = $("#sortOrder option:selected").val();
		callAptFromDb(selectDong,);
	});
});

function callAptFromDb(dong) {
	console.log(dong);
	$("#sortBy").off();
	$("#sortOrder").off();
	$("#sortBy").change(function(){
		sortBy = $("#sortBy option:selected").val();
		callAptFromDb(selectDong);
	});
	$("#sortOrder").change(function(){
		sortOrder = $("#sortOrder option:selected").val();
		callAptFromDb(selectDong);
	});
	$.ajax({
		url: "/HappyHouse/deal/getAptList",
		type: "get",
		dataType: "json",
		data: { "dong" : dong, "sortBy" : sortBy, "sortOrder" : sortOrder },
		success: function(response) {
			makeListByJson(response);
			setMarkers(null);
			setCenter(address);
		},
		error: function(xhr, status, msg) {
			console.log("상태값 : " + status + " Http에러메시지 : " + msg);
		},
	});
}

function makeListByJson(data) {
	let aptlist = ``;
	let address;

	for(var i=0; i<data.length; i++) {
		let current = data[i].dongName;
		aptlist += `<tr>
				<td>${data[i].aptName}</td>
				<td>${data[i].dongName}</td>
				<td>${data[i].area}</td>
				<td>${data[i].dealAmount}</td>
				<td>${data[i].dealYear}`+"/"+`${data[i].dealMonth}`+"/"+`${data[i].dealDay}</td>
				</tr>`;
		address = current + " " + data[i].jibun.trim();
		addressSearch(address, data[i].aptName);
	}
	if(data.length > 0) setCenter(data[0].dongName + " " + data[0].jibun.trim());
	else setCenter(address);
	$("#aptinfo").empty().append(aptlist);
	$("tr:first").css("background", "darkgray").css("color", "white");
	$("tr:even").css("background", "lightgray");
}

