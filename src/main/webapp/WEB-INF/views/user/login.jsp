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
    <title>로그인</title>
    <link rel="icon" type="image/x-icon" href="../assets/favicon.ico"/>
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- RSA 자바스크립트 라이브러리 -->
    <script type="text/javascript" src="../js/rsa/jsbn.js"></script>
    <script type="text/javascript" src="../js/rsa/rsa.js"></script>
    <script type="text/javascript" src="../js/rsa/prng4.js"></script>
    <script type="text/javascript" src="../js/rsa/rng.js"></script>
    <!-- RSA 암호화 처리 스크립트 -->
    <script type="text/javascript" src="../js/login.js"></script>

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
<script type="text/javascript">
    let msg = "${msg}";
    if (msg) {
        alert(msg);
    }
</script>


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
    <div class="container h-80">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card shadow-2-strong" style="border-radius: 1rem">
                    <div class="card-body p-5 text-center">
                        <h3 class="mb-5">로그인</h3>
                        <form id="login-form">
                            <input type="hidden" id="RSAModulus" value="${RSAModulus}"/>
                            <input type="hidden" id="RSAExponent" value="${RSAExponent}"/>

                            <div class="form-outline mb-4">
                                <input type="text" id="id" class="form-control form-control-lg"
                                       name="id" placeholder="아이디" required/>
                            </div>

                            <div class="form-outline mb-4">
                                <input type="password" id="password"
                                       class="form-control form-control-lg" name="password"
                                       placeholder="비밀번호" required/>
                            </div>

                            <button type="button" class="btn btn-primary btn-lg btn-block" id="loginBtn">로그인</button><br><br>
                            <a href="${root}/user/findPassword">비밀번호 찾기</a>
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
            <div class="col-lg-4 text-lg-start">Copyright &copy; 2022
                SSAFY
            </div>
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
