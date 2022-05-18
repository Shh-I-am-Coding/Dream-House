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
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <title>회원가입</title>
    <link rel="icon" type="image/x-icon" href="../assets/favicon.ico" />
    <script type="text/javascript" src="../js/join.js"></script>
    <!-- Font Awesome icons (free version)-->
    <script
      src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
      crossorigin="anonymous"
    ></script>
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

    <div class="container mt-5 px-5 mb-lg-5">
      <h3>회원가입</h3>

      <form class="validation-form" id="validation-form" method="post" action="${root}/user/join">
          <input type="hidden" name="action" id="action" value="join">
        <div class="mb-3 mt-3">
          <label for="id" class="form-label">아이디 : </label>
          <input
            type="text"
            class="form-control w-50"
            id="id"
            name="id"
            required
          />
          <div class="valid-feedback">사용 가능합니다.</div>
          <div class="invalid-feedback">이 항목을 입력해주세요.</div>
        </div>
        <div class="mb-3">
          <label for="password" class="form-label">비밀번호 : </label>
          <input
            type="password"
            class="form-control w-50"
            id="password"
            placeholder="영문 숫자 포함 6자리 이상"
            name="password"
            required
          />
          <div class="valid-feedback">사용 가능합니다.</div>
          <div class="invalid-feedback">이 항목을 입력해주세요.</div>
        </div>
        <div class="mb-3">
          <label for="name" class="form-label">이름 : </label>
          <input
            type="text"
            class="form-control w-50"
            id="name"
            placeholder="User Name"
            name="name"
            required
          />
          <div class="valid-feedback">사용 가능합니다.</div>
          <div class="invalid-feedback">이 항목을 입력해주세요.</div>
        </div>
        <div class="mb-3">
          <label for="email" class="form-label">주소 : </label>
          <input
            type="text"
            class="form-control w-50"
            id="email"
            placeholder="Email"
            name="email"
            required
          />
          <div class="valid-feedback">사용 가능합니다.</div>
          <div class="invalid-feedback">이 항목을 입력해주세요.</div>
        </div>
        <div class="mb-3">
          <label for="phone" class="form-label">전화번호 : </label>
          <input
            type="tel"
            class="form-control w-50"
            id="phone"
            placeholder="010-xxxx-xxxx"
            name="phone"
            required
          />
          <div class="valid-feedback">사용 가능합니다.</div>
          <div class="invalid-feedback">이 항목을 입력해주세요.</div>
        </div>
        <input type="submit" class="btn btn-primary" value="등록">
      </form>
    </div>

    <footer class="footer py-4">
      <div class="container">
        <div class="row align-items-center">
          <div class="col-lg-4 text-lg-start">Copyright © 2022 SSAFY</div>
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