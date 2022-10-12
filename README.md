<div align="center">
  <br />
  <img src="https://user-images.githubusercontent.com/60915285/195348807-af629327-507d-4c1b-9e9f-8f3ad557d27c.png" width="45%" />
  <br />

</div>

<div align="center">
 <h3><b>꿈의집 🏡</b></h3>
아파트 매매 관련 정보 제공 웹사이트<br><br>
  2022.05.18 ~ 2022.05.27
  <br><br>

  [Notion](https://sunset-offer-934.notion.site/ab0600e5db484974b5d21485a34d4583?v=3090e8271bb845d4a48c25ba61371bf1)  |  [UCC](https://www.youtube.com/watch?v=-wafC3AMRKM)

</div>
<br/><hr><br>

## 서비스 소개 🖥
아파트 매매 거래 정보를 쉽게 검색할 수 있는 서비스입니다.<br>
"꿈의집"은 가장 편안하게 쉬어야할 꿈의 집을 찾아주는 서비스라는 의미에서 이름을 지었습니다.
지도를 통해 주변 아파트 정보와 각 아파트의 거래 정보를 확인할 수 있습니다.
백엔드는 Spring-boot, 프론트엔드는 Vue를 사용하여 RESTful 서비스를 구현하였습니다.

<br><hr><br>

## 개발 환경 및 기술 스택 🔨

### FE
`HTML`  <br>
`CSS`  <br>
`JavaScript` <br>
`Node.js` <br>
`Vue` <br>
`Vuex`  <br>
`Bootstrap-Vue` <br>

### BE
`Java`  <br>
`Spring Boot`  <br>
`Maven` <br>
`Mybatis`  <br>
`MySQL` <br>

### 버전/이슈 관리

`GitLab` <br>
`Sourcetree` <br>

### TOOL

`Postman` <br>

### 협업

`Cisco Webex Meetings` <br>
`Mattermost` <br>
`Notion` <br>

### IDE

`Visual Studio Code` <br>
`IntelliJ`

<br><hr><br>

## 서비스 구조도 🔍
<img width="680" alt="서비스 구조도" src="https://user-images.githubusercontent.com/60915285/195379713-bbd2ba8c-5da5-47e5-96ee-b5b6ca00d40d.png">
<br><hr><br>


## 주요 기능 📚

### 1. 로그인
<img width="844" alt="로그인" src="https://user-images.githubusercontent.com/60915285/195380367-09446ed2-20cb-40f7-b7a1-14a204c764ba.png">
일반 로그인 및 카카오 로그인이 가능합니다.


<br><br>
        
### 2. 오늘의 뉴스
<img width="845" alt="오늘의 뉴스" src="https://user-images.githubusercontent.com/60915285/195381046-3a05fec5-92c9-418f-be21-f6cbe372d2d8.png">

메인 페이지에서 오늘의 뉴스 목록을 확인할 수 있습니다.<br>
네이버 검색 API를 사용하여 아파트 부동산에 관한 최근 10개의 기사들을 보여줍니다.<br>
기사 제목은 실제 기사 페이지로 링크되어 있어 기사 내용을 확인할 수 있습니다.

<br><br>

### 3. 매매가 검색 - 지역 선택
<img width="853" alt="지역 선택" src="https://user-images.githubusercontent.com/60915285/195384491-6690167c-9183-40e1-b226-28f1bed76984.png">
지도는 카카오맵 API 사용을 하였으며, 매매가 검색 페이지에 들어가면 지역을 선택할 수 있습니다.<br>
지역은 시, 구/군, 동 을 선택하게끔 되어있습니다.<br>
지역을 선택하면 해당 지역의 아파트 목록이 지도에 핀으로 찍히고, 핀에 마우스를 올리면 아파트 이름과 평균 매매가 팝업을 볼 수 있습니다.


<br><br>

### 4. 매매가 검색 - 아파트 리스트 
<img width="858" alt="아파트 리스트" src="https://user-images.githubusercontent.com/60915285/195384958-35497cb6-3be4-49c0-ad5e-d556726852d8.png">
지역을 선택한 후, 왼쪽에 아파트 리스트 버튼을 누르면 해당 지역의 아파트 리스트들을 볼 수 있으며, 평균 거래 금액을 볼 수 있습니다.<br>
이름순, 평균금액순을 오름차순/내림차순으로 선택하여 볼수도 있습니다.<br>
<br><br>

### 5. 매매가 검색 - 아파트 매매 상세 정보
<img width="847" alt="아파트 매매 상세 정보" src="https://user-images.githubusercontent.com/60915285/195397465-e685c74c-89b7-48ca-bcf4-e5275b0b118b.png">
<img width="847" alt="로그인 시" src="https://user-images.githubusercontent.com/60915285/195398344-371b3347-fc2f-47e5-9951-4df7d410a5ad.png">
아파트 리스트에서 아파트를 선택하여 누르면, 해당 아파트의 매매 내역 상세 정보가 나타납니다.<br>
아파트 사진 및 최근 거래 내역이 나타나며, 로그인한 유저는 아파트를 찜하여 관심아파트를 등록할 수 있고 매매 정보 차트를 볼 수 있습니다.<br>
<br><br>

### 6. 매매가 검색 - 주변 상권 정보
<img width="847" alt="상권정보" src="https://user-images.githubusercontent.com/60915285/195405669-dd7c09d0-8e2e-4e05-8b43-a6bb6f6ac4e8.png">
주변 상권 정보(은행/마트/병원/약국/학교/카페/편의점/주차장/주유소)를 확인할 수 있습니다.<br>
보고싶은 상권을 누르면 해당하는 상권의 위치들을 지도에 핀으로 표시합니다.
<br><br>
  
### 7. 문의 게시판
<img width="851" alt="문의 게시판" src="https://user-images.githubusercontent.com/60915285/195407425-1b398db3-743f-4264-a548-a93f39a1363e.png">
문의 게시판은 로그인을 해야만 글을 쓸 수 있으며, 특정 글을 수정하거나 삭제하려면 해당 글의 작성자여야만 가능합니다.
<br><br>


### 8. 공지사항 게시판
<img width="855" alt="공지사항" src="https://user-images.githubusercontent.com/60915285/195407777-c45a043e-86c0-4ba6-a14d-4353747e4c11.png">
<img width="851" alt="스크린샷 2022-10-13 오전 2 23 43" src="https://user-images.githubusercontent.com/60915285/195408178-ae5ac5c9-50e6-4bb4-8667-c8f2246866c9.png">

공지사항 게시판은 관리자만 글을 쓰고 관리할 수 있습니다.

<br>
<hr><br>



## 설계 문서 📝
### 요구사항 정의서
<img width="449" alt="스크린샷 2022-10-13 오전 2 27 59" src="https://user-images.githubusercontent.com/60915285/195409190-ab50cd2a-8f76-4f89-ae99-60adaabd0f77.png">
<img width="449" alt="스크린샷 2022-10-13 오전 2 29 09" src="https://user-images.githubusercontent.com/60915285/195409273-d7d96cd1-0dbd-4d84-9193-4b0b81ec140b.png">

<br><br>
### ERD
<img width="465" alt="image" src="https://user-images.githubusercontent.com/60915285/195409445-17235a96-bb71-468a-80b9-a325d327a8cd.png">
<br><br>

### 클래스 다이어그램
<img width="500" alt="image" src="https://user-images.githubusercontent.com/60915285/195409721-a267a86c-b28a-4370-b91f-55b3fc6c9784.png">


<br><hr><br>
## 팀원 소개 👪
<table>
    <tr>
        <td height="140px" align="center"> <a href="https://github.com/Qulip">
            <img src="https://avatars.githubusercontent.com/Qulip" width="140px" /> <br><br> 유일권 <br>(Backend, Frontend) </a> <br></td>
        <td height="140px" align="center"> <a href="https://github.com/choijoohee213">
            <img src="https://avatars.githubusercontent.com/choijoohee213" width="140px" /> <br><br> 최주희 <br>(Backend, Frontend) </a> <br></td>
    </tr>
</table>

<br><br><br>
