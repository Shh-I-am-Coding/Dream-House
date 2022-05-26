<template>
  <div>
    <div id="map"></div>
    <ul id="category">
      <!-- <li v-for="(place, index) in placeHolder" :key=index v-html="place" @click="onClickCategory(place.id)" :class="[currCategory === searchPlaces.id ? 'on' : 'off']"/> -->
      <li v-html="placeHolder[0].tags" @click="onClickCategory(placeHolder[0].id)" :class="[currCategory === placeHolder[0].id ? 'on' : 'off']" />
      <li v-html="placeHolder[1].tags" @click="onClickCategory(placeHolder[1].id)" :class="[currCategory === placeHolder[1].id ? 'on' : 'off']" />
      <li v-html="placeHolder[2].tags" @click="onClickCategory(placeHolder[2].id)" :class="[currCategory === placeHolder[2].id ? 'on' : 'off']" />
      <li v-html="placeHolder[3].tags" @click="onClickCategory(placeHolder[3].id)" :class="[currCategory === placeHolder[3].id ? 'on' : 'off']" />
      <li v-html="placeHolder[4].tags" @click="onClickCategory(placeHolder[4].id)" :class="[currCategory === placeHolder[4].id ? 'on' : 'off']" />
      <li v-html="placeHolder[5].tags" @click="onClickCategory(placeHolder[5].id)" :class="[currCategory === placeHolder[5].id ? 'on' : 'off']" />
      <li v-html="placeHolder[6].tags" @click="onClickCategory(placeHolder[6].id)" :class="[currCategory === placeHolder[6].id ? 'on' : 'off']" />
      <li v-html="placeHolder[7].tags" @click="onClickCategory(placeHolder[7].id)" :class="[currCategory === placeHolder[7].id ? 'on' : 'off']" />
      <li v-html="placeHolder[8].tags" @click="onClickCategory(placeHolder[8].id)" :class="[currCategory === placeHolder[8].id ? 'on' : 'off']" />
    </ul>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const dealStore = "dealStore";

export default {
  name: "DealDetail",
  data() {
    return {
      markers: [],
      placeMarkers: [],
      geocoder: null,
      ps: null,
      infoWindow: null,
      currCategory: "",
      placeOverlay: null,
      contentNode: null,
      map: null,
      count: 0,
      pinImg: null,
      placeHolder: [
        {
          id: "BK9",
          tags: '<span class="category_bg bank"></span>은행',
        },
        {
          id: "MT1",
          tags: '<span class="category_bg mart"></span>마트',
        },
        {
          id: "HP8",
          tags: '<span class="category_bg hospital"></span>병원',
        },
        {
          id: "PM9",
          tags: '<span class="category_bg pharmacy"></span>약국',
        },
        {
          id: "SC4",
          tags: '<span class="category_bg school"></span>학교',
        },
        {
          id: "CE7",
          tags: '<span class="category_bg cafe"></span>카페',
        },
        {
          id: "CS2",
          tags: '<span class="category_bg store"></span>편의점',
        },
        {
          id: "PK6",
          tags: '<span class="category_bg parking"></span>주차장',
        },
        {
          id: "OL7",
          tags: '<span class="category_bg oil"></span>주유소',
        },
      ],
      isInMarker: null,
      socialPins: [],
      imgSize: null,
      imgOptions: { offset: new kakao.maps.Point(13, 40) },
    };
  },
  computed: {
    ...mapState(dealStore, ["deal", "deals", "sortBy", "sortOrder", "topHits"]),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      const SERVICE_KEY = process.env.VUE_APP_KAKAO_MAP_API_KEY;
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=" + decodeURIComponent(SERVICE_KEY) + "&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    ...mapActions(dealStore, ["getAptList"]),
    ...mapMutations(dealStore, ["SET_DEAL_LIST"]),
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        draggable: true,
        level: 1,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);
      this.geocoder = new kakao.maps.services.Geocoder();
      this.SET_DEAL_LIST(this.topHits);
      this.imgSize = new kakao.maps.Size(40, 40);
      this.pinImg = new kakao.maps.MarkerImage(require("@/assets/img/pins/pin08.png"), this.imgSize, this.imgOptions);

      this.infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 }); // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
      this.contentNode = document.createElement("div"); // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
      this.ps = new kakao.maps.services.Places(this.map);

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);

      this.contentNode.className = "placeinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      this.addEventHandle(this.contentNode, "mousedown", kakao.maps.event.preventMap);
      this.addEventHandle(this.contentNode, "touchstart", kakao.maps.event.preventMap);

      // 커스텀 오버레이 컨텐츠를 설정합니다
      this.placeOverlay.setContent(this.contentNode);
      this.isInMarker = new Set();

      this.socialPins.push(require("@/assets/img/pins/money1.png"));
      this.socialPins.push(require("@/assets/img/pins/supermarket1.png"));
      this.socialPins.push(require("@/assets/img/pins/hospital1.png"));
      this.socialPins.push(require("@/assets/img/pins/pharmacy1.png"));
      this.socialPins.push(require("@/assets/img/pins/school1.png"));
      this.socialPins.push(require("@/assets/img/pins/cafe1.png"));
      this.socialPins.push(require("@/assets/img/pins/shop1.png"));
      this.socialPins.push(require("@/assets/img/pins/parking1.png"));
      this.socialPins.push(require("@/assets/img/pins/gas1.png"));
    },
    mkMarker(deal) {
      if (!this.isInMarker.has(deal.aptCode)) {
        this.isInMarker.add(deal.aptCode);
        //console.log(deal.aptCode, " ", deal.aptName);
        const coords = new kakao.maps.LatLng(deal.lat, deal.lng);
        // 결과값으로 받은 위치를 마커로 표시합니다
        const marker = new kakao.maps.Marker({
          map: this.map,
          position: coords,
          image: this.pinImg,
        });
        this.markers.push(marker);
        kakao.maps.event.addListener(marker, "click", () => {
          // 마커 위에 인포윈도우를 표시합니다
          console.log("아파트명 : " + deal.aptName);
          console.log("동코드 : " + deal.dongCode);

          //아파트명을 통해 아파트 검색
          this.searchByAptName(deal.aptName, deal.dongCode);
        });
        kakao.maps.event.addListener(marker, "mouseover", () => {
          // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
          let amount = Math.round(deal.avgAmount / 100) / 100;
          this.infowindow.setContent(
            '<div style="padding:5px;font-size:1.1rem;font-weight: bold;">' + deal.aptName + "</div>" + '<div style="padding:5px;font-size:0.9rem;">평균 :' + amount + "억</div>"
          );
          this.infowindow.open(this.map, marker);
        });

        // 마커에 마우스아웃 이벤트를 등록합니다
        kakao.maps.event.addListener(marker, "mouseout", () => {
          // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
          this.infowindow.close();
        });
      }
    },
    mkMarkers(deals) {
      this.isInMarker.clear();
      deals.forEach((deal) => {
        this.mkMarker(deal);
      });
      this.setCenter();
    },
    removePins() {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
      this.markers = [];
    },
    setCenter() {
      const positions = this.markers.map((marker) => marker.getPosition());

      const bounds = positions.reduce((bounds, latlng) => bounds.extend(latlng), new kakao.maps.LatLngBounds());

      this.map.setBounds(bounds);
    },
    searchByAptName(aptName, dongCode) {
      const params = {
        aptName,
        dongCode,
      };
      console.log(params);
      this.getAptList(params);
    },
    addEventHandle(target, type, callback) {
      // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removePlaceMarker();

      this.ps.categorySearch(this.currCategory, this.placesSearchCB, { useMapBounds: true });
    },
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        console.log(status);
      }
    },
    displayPlaces(places) {
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      let order = 0;
      for (let i = 0; i < this.placeHolder.length; i++) {
        if (this.placeHolder[i].id == this.currCategory) {
          order = i;
          break;
        }
      }
      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        let marker = this.addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

        // 마커와 검색결과 항목을 클릭 했을 때
        // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        ((marker, place) => {
          kakao.maps.event.addListener(marker, "click", () => {
            this.displayPlaceInfo(place);
          });
        })(marker, places[i]);
      }
    },
    addMarker(position, order) {
      const markerImage = new kakao.maps.MarkerImage(this.socialPins[order], this.imgSize, this.imgOptions);
      const marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage,
      });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.placeMarkers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
    removePlaceMarker() {
      for (let i = 0; i < this.placeMarkers.length; i++) {
        this.placeMarkers[i].setMap(null);
      }
      this.placeMarkers = [];
    },
    displayPlaceInfo(place) {
      let content = '<div class="placeinfo">' + '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + "</a>";

      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content += '    <span title="' + place.address_name + '">' + place.address_name + "</span>";
      }

      content += '    <span class="tel">' + place.phone + "</span>" + "</div>" + '<div class="after"></div>';

      this.contentNode.innerHTML = content;
      this.contentNode.set;
      this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.map);
    },
    onClickCategory(id) {
      console.log(id);
      this.placeOverlay.setMap(null);

      if (this.currCategory === id) {
        this.currCategory = "";
        this.removePlaceMarker();
      } else {
        this.currCategory = id;
        this.searchPlaces();
      }
    },
  },
  watch: {
    deal(newValue) {
      if (newValue) {
        this.isInMarker.clear();
        this.removePins();
        this.mkMarker(newValue);
        this.setCenter();
      }
    },
    deals(newValue) {
      if (newValue.length > 0) {
        this.removePins();
        this.mkMarkers(newValue);
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
@import "@/assets/css/DealDetail.css";
</style>
