<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>행복한 우리집</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=701aa07cd37df685df5060f9332e9db2&libraries=services">
	
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="js/kakaomap.js"></script>
<script type="text/javascript" src="js/dealInfo.js"></script>

<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
</head>
<body id="page-top">
	<%@ include file="./include/header.jsp"%>
	<script type="text/javascript">
		let msg = "${msg}";
		if(msg) {
			alert(msg);
		}
	</script>

	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
		<div class="container">
			<a class="navbar-brand" href="#page-top"><h3>행복한 우리 집</h3></a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fas fa-bars ms-1"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
					<li class="nav-item"><a class="nav-link">공지 사항</a></li>
					<li class="nav-item"><a class="nav-link" href="#portfolio">오늘의
							뉴스</a></li>
					<li class="nav-item"><a class="nav-link" href="#about">주변
							탐방</a></li>
					<c:if test="${!empty user}">
						<li class="nav-item"><a class="nav-link" href="#team">관심
								지역 설정</a></li>
						<li class="nav-item"><a class="nav-link"
							href="#contact">관심 지역 둘러보기</a></li>
					</c:if>
					<li class="nav-item"><a class="nav-link"
						href="${root}/deal/searchApt">지역 검색</a></li>
					<li class="nav-item"><a class="nav-link" href="${root}/vue/index.html">이름을 뭐라고 할까요</a></li>

					<c:if test="${empty user}">
						<li class="nav-item">
							<button class="btn-info btn-outline-light"
								onclick="location.href='${root}/user/join'">회원가입</button>
						</li>
						<li class="nav-item">
							<button class="btn-info btn-outline-light"
								onclick="location.href='${root}/user/login'">로그인</button>
						</li>
					</c:if>
					<c:if test="${!empty user}">
						<li class="nav-item">
							<button class="btn-info btn-outline-light"
								onclick="location.href='${root}/user/myInfo'">마이페이지</button>
						</li>
						<li class="nav-item">
							<button class="btn-info btn-outline-light"
								onclick="location.href='${root}/user/logout'">로그아웃
							</button>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Masthead-->
	<header class="masthead">
		<div class="container">
			<div class="masthead-heading text-uppercase">Happy House</div>
			<div class="masthead-subheading">행복한 우리 집</div>
		</div>
	</header>
	<div class="container-drop p-5 bg-dark">
		<select class="drop-child" name="sido" id="sido"></select> <select
			class="drop-child" name="gugun" id="gugun">
			<option value="">시/구/군</option>
		</select> <select class="drop-child" name="dong" id="dong">
			<option value="">동</option>
		</select>
	</div>
	</div>
	<!-- Services-->
	<section class="page-section" id="services">
		<div class="container">
			<div id="map" style="width: 100%; height: 600px"></div>

		</div>
	</section>
	<!-- Portfolio Grid-->
	<section class="page-section bg-light" id="portfolio">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">오늘의 뉴스</h2>
				<h3 class="section-subheading text-muted">서울 아파트 매매 전세 다른 양상..
					상승.. 아시아 경제</h3>
				<div class="ul-list">
					<li><a href="#">서울 입주 2년차 아파트 전셋값 1억 4천만원</a> 한겨레</li>
					<li><a href="#">12.16 거래한파..매수자가 사라졌다</a> 아시아경제</li>
					<li><a href="#">재건축 대안 또는 증축 리모델링</a> 동아일보</li>
					<li><a href="#">고가기준 9억, 서울 아파트 중간값 됐다.</a> 동아일보</li>
				</div>
			</div>
		</div>
	</section>
	<!-- About-->
	<section class="page-section" id="about">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">주변 탐방</h2>
			</div>
		</div>
	</section>
	<!-- Team-->
	<section class="page-section bg-light" id="team">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">관심 지역 설정</h2>
			</div>
		</div>
	</section>
	<!-- Contact-->
	<section class="page-section" id="contact">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">관심 지역 둘러보기</h2>
			</div>
		</div>
	</section>
	<!-- Footer-->
	<footer class="footer py-4">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-4 text-lg-start">Copyright &copy; 2022
					SSAFY</div>
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
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

	<script type="text/javascript">
		//로그인 상태일 경우 보여줄 엘리먼트 구분하기
		checkLoginState();
	</script>
</body>
</html>
