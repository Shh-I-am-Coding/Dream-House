<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>매매가 검색</title>
    <link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
    <style type="text/css">
      table {
        padding-right: 30px;
      }
      th,
      td {
        text-align: center;
      }
    </style>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=701aa07cd37df685df5060f9332e9db2&libraries=services"
    ></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="../js/kakaomap.js" ></script>

    <!-- Font Awesome icons (free version)-->
    <script
      src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
      crossorigin="anonymous"
    ></script>
    <script type="text/javascript" src="../js/dealInfo_dong.js"></script>

    <!-- Google fonts-->
    <link
      href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"
      rel="stylesheet"
      type="text/css"
    />
    <link
      href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
      rel="stylesheet"
      type="text/css"
    />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../css/styles.css" rel="stylesheet" />
  </head>
  <body>
  <%@ include file="../include/header.jsp" %>
    <div>
      <nav class="navbar navbar-expand-lg w-40 py-5 px-5">
        <a class="navbar-brand" href="${root}/"><h3>행복한 우리 집</h3></a>
        <div class="container-fluid justify-content-end">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="nav-link text-white" href="#">공지사항</a>
            </li>
            <li class="nav-item">
              <a class="nav-link text-white" href="#">오늘의 뉴스</a>
            </li>
          </ul>
        </div>
      </nav>

      <div class="container-drop p-5 bg-dark">
        <select class="drop-child" name="sido" id="sido"></select>
        <select class="drop-child" name="gugun" id="gugun">
          <option value="">시/구/군</option>
        </select>
        <select class="drop-child" name="dong" id="dong">
          <option value="">동</option>
        </select>
        <select class="drop-child" name="sortBy" id="sortBy">
          <option value="dealAmount">금액 순</option>
          <option value="date">날짜 순</option>
          <option value="aptName" selected>이름 순</option>
        </select>
        <select class="drop-child" name="sortOrder" id="sortOrder">
          <option value="asc" selected>오름차순</option>
          <option value="desc">내림차순</option>
        </select>
      </div>
      <div class="">
        <h3>거래 정보</h3>
        <div style="float: left; width: 40%; height: 500px; overflow: auto; text-align: center;">
          <table>
            <colgroup>
              <col width="40%"/>
              <col width="20%"/>
              <col width="20%"/>
              <col width="20%"/>
              <col width="20%"/>
            </colgroup>
            <tr>
              <th>아파트</th>
              <th>법정동</th>
              <th>전용면적</th>
              <th>거래금액</th>
              <th>날짜</th>
            </tr>
            <tbody id="aptinfo"></tbody>
          </table>
        </div>
        <div id="map" style="float: left; width: 58%; height: 500px; padding-left: 30px"></div>
      </div>
    </div>
    <br>
  <br>
    <footer class="footer py-4 flo" style="float: bottom">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-4 text-lg-start">Copyright &copy; 2022 SSAFY</div>
          <div class="col-lg-4 my-3 my-lg-0">
            <a class="btn btn-dark btn-social mx-2" href="#!"
              ><i class="fab fa-twitter"></i
            ></a>
            <a class="btn btn-dark btn-social mx-2" href="#!"
              ><i class="fab fa-facebook-f"></i
            ></a>
            <a class="btn btn-dark btn-social mx-2" href="#!"
              ><i class="fab fa-linkedin-in"></i
            ></a>
          </div>
          <div class="col-lg-4 text-lg-end">
            <a class="link-dark text-decoration-none me-3" href="#!"
              >Privacy Policy</a
            >
            <a class="link-dark text-decoration-none" href="#!">Terms of Use</a>
          </div>
        </div>
      </div>
    </footer>
  </body>
</html>
