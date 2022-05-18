<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <meta name="description" content=""/>
    <meta name="author" content=""/>
    <title>마이페이지</title>
    <link rel="icon" type="image/x-icon" href="../assets/favicon.ico"/>
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
            crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link
            href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"
            rel="stylesheet" type="text/css"/>
    <link
            href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800"
            rel="stylesheet" type="text/css"/>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="../css/styles.css" rel="stylesheet"/>
</head>
<body>
<%@ include file="../include/header.jsp" %>

<nav class="navbar navbar-expand-lg w-40 py-5 px-5">
    <a class="navbar-brand" href="${root}/"><h3>행복한 우리 집</h3></a>
    <div class="container-fluid justify-content-end">
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link text-white" href="#">공지사항</a>
            </li>
            <li class="nav-item"><a class="nav-link text-white" href="#">오늘의
                뉴스</a></li>
        </ul>
    </div>
</nav>

<section class="vh-100">
    <div class="container">
        <div
                class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem">
                    <div class="card-body p-5 text-center">
                        <h3 class="mb-5">마이페이지</h3>
                        <form id="login-form" method="post" action="${root}/user/update">
                            <table class="mx-5 w-100">
                                <tr>
                                    <td>아이디</td>
                                    <td>
                                        <div class="form-outline mb-2">
                                            <input type="text" id="id" class="form-check-inline mx-5"
                                                   name="id" value="${user.id}" required readonly/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>비밀번호</td>
                                    <td>
                                        <div class="form-outline mb-2">
                                            <input type="text" id="password"
                                                   class="form-check-inline mx-5" name="password"
                                                   value="${user.password}" required/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>이름</td>
                                    <td>
                                        <div class="form-outline mb-2">
                                            <input type="text" id="name" class="form-check-inline mx-5"
                                                   name="name"
                                                   value="${user.name}" required/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>주소</td>
                                    <td>
                                        <div class="form-outline mb-2">
                                            <input type="text" id="email"
                                                   class="form-check-inline mx-5" name="email"
                                                   value="${user.email}" required/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>전화번호</td>
                                    <td>
                                        <div class="form-outline mb-2">
                                            <input type="text" id="phone" class="form-check-inline mx-5"
                                                   name="phone" value="${user.phone}" required/>
                                        </div>
                                    </td>
                                </tr>
                            </table>

                            <button class="btn btn-primary btn-lg btn-block mt-5"
                                    type="button"
                                    onclick="location.href='${root}/'">
                                확인
                            </button>
                            <input type="submit" name="action"
                                   class="btn btn-primary btn-lg btn-block mt-5" value="수정">
                            <button class="btn btn-danger btn-lg btn-block mt-5"
                                    type="button"
                                    onclick="location.href='${root}/user/delete?id=${user.id}'">
                                탈퇴
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<footer class="footer py-4">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-4 text-lg-start">Copyright © 2022 SSAFY</div>
            <div class="col-lg-4 my-3 my-lg-0">
                <a class="btn btn-dark btn-social mx-2" href="#!"><i
                        class="fab fa-twitter"></i></a> <a
                    class="btn btn-dark btn-social mx-2" href="#!"><i
                    class="fab fa-facebook-f"></i></a> <a
                    class="btn btn-dark btn-social mx-2" href="#!"><i
                    class="fab fa-linkedin-in"></i></a>
            </div>
            <div class="col-lg-4 text-lg-end">
                <a class="link-dark text-decoration-none me-3" href="#!">Privacy
                    Policy</a> <a class="link-dark text-decoration-none" href="#!">Terms
                of Use</a>
            </div>
        </div>
    </div>
</footer>

</body>
</html>