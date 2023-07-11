<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <link rel="shortcut icon" href="/assets/img/logo.png" type="image/x-icon">
    <title>Page Palette</title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
    rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="assets/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="assets/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="assets/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="assets/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="assets/css/style.css" type="text/css">
</head>
<body>
	<!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3">
                    <a href="?language=vi">
                        <img style="margin-top: 25px" alt="" src="https://s2.o7planning.com/templates/o7planning/resources/images/languages/vi.png">
                    </a>
                    <a href="?=en">
                        <img style="margin-top: 25px" alt="" src="https://s2.o7planning.com/templates/o7planning/resources/images/languages/en.png">
                    </a>
                </div>
                <div class="col-lg-6 col-md-6">
                    <nav class="header__menu mobile-menu">
                        <ul>
                            <li class="active"><a href="/"><s:message code="label.home.home"/></a></li>
                            <li><a href="/shop"><s:message code="label.home.shop"/></a></li>
                            <c:if test="${not empty user}">
                            	<li><a href="/account/index">${user.fullname}</a></li>
                            </c:if>
                            <c:if test="${user.admin}">
                            	<li><a href="/admin/product"><s:message code="label.home.admin"/></a></li>
                            </c:if>
                            <c:if test="${empty user}">
                            	<li><a href="/sign-in">Login</a></li>
                            </c:if>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3 col-md-3">
                    <div class="header__nav__option">
                        <a href="#" class="search-switch"><img src="assets/img/icon/search.png" alt=""></a>
                        <a href="shop/cart"><img src="assets/img/icon/cart.png" alt=""> <span>${numberCart}</span></a>
                        <div class="price"><fmt:formatNumber value="${totalCart}" pattern="#,###"/> &#8363;</div>
                    </div>
                </div>
            </div>
            <div class="canvas__open"><i class="fa fa-bars"></i></div>
        </div>
    </header>
    <!-- Header Section End -->

    <!-- Hero Section Begin -->
    <section class="hero">
        <div class="hero__slider owl-carousel">
            <div class="hero__items set-bg" data-setbg="https://picx.zhimg.com/v2-f20fc2d48e7f447051bd6169e677824e_r.jpg?source=172ae18b">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-5 col-lg-7 col-md-8">
                            <div class="hero__text">
                                <h2 style="color: yellow">Fall Collections ${year}</h2>
<%--                                <p style="color: white">A specialist label creating luxury essentials. Ethically crafted with an unwavering--%>
<%--                                commitment to exceptional quality.</p>--%>
                                <a href="/shop" class="primary-btn">Shop now <span class="arrow_right"></span></a>
                                <div class="hero__social">
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-pinterest"></i></a>
                                    <a href="#"><i class="fa fa-instagram"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="hero__items set-bg" data-setbg="https://a-static.besthdwallpaper.com/one-piece-go-to-new-world-wallpaper-2400x1350-22163_50.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-xl-5 col-lg-7 col-md-8">
                            <div class="hero__text">
<%--                                <h6 style="color: black">Summer Collection</h6>--%>
                                <h2 style="color: yellow">Winter Collections ${year}</h2>
<%--                                <p style="color: white">A specialist label creating luxury essentials. Ethically crafted with an unwavering--%>
<%--                                commitment to exceptional quality.</p>--%>
                                <a href="/shop" class="primary-btn">Shop now <span class="arrow_right"></span></a>
                                <div class="hero__social">
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-pinterest"></i></a>
                                    <a href="#"><i class="fa fa-instagram"></i></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->

    <!-- Banner Section Begin -->
    <section class="banner spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-7 offset-lg-4">
                    <div class="banner__item">
                        <div class="banner__item__pic">
                            <img width="340px" height="600px" src="https://freepngimg.com/thumb/one_piece/23158-2-one-piece-luffy-transparent.png" alt="">
                        </div>
                        <div class="banner__item__text">
                            <h2>Newest ${year}</h2>
                            <a href="#">Shop now</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-5">
                    <div class="banner__item banner__item--middle">
                        <div class="banner__item__pic">
                            <img width="340px" height="440px" src="https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/38917b49-103f-4ff8-8e6b-a1adefff3a94/ddfih0c-d9646369-1536-42b6-8246-4a39f14610c1.png/v1/fill/w_829,h_964/son_goku_dbz_transparent_png_by_michiruyami_ddfih0c-pre.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9MTE5MCIsInBhdGgiOiJcL2ZcLzM4OTE3YjQ5LTEwM2YtNGZmOC04ZTZiLWExYWRlZmZmM2E5NFwvZGRmaWgwYy1kOTY0NjM2OS0xNTM2LTQyYjYtODI0Ni00YTM5ZjE0NjEwYzEucG5nIiwid2lkdGgiOiI8PTEwMjQifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6aW1hZ2Uub3BlcmF0aW9ucyJdfQ.7IjJqD-xa4BZR8GKvgCqYHgKzi1gqf9Sr6omX6F3hvQ" alt="">
                        </div>
                        <div class="banner__item__text">
                            <h2>Many different types</h2>
                            <a href="#">Shop now</a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-7">
                    <div class="banner__item banner__item--last">
                        <div class="banner__item__pic">
                            <img width="440px" height="540px" src="assets/img/banner/banner.png" alt="">
                        </div>
                        <div class="banner__item__text">
                            <h2>Full color</h2>
                            <a href="#">Shop now</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Banner Section End -->

    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <ul class="filter__controls">
                        <li class="active" data-filter="*">Best Sellers</li>
                        <li data-filter=".new-arrivals">New Arrivals</li>
                        <li data-filter=".hot-sales">Hot Sales</li>
                    </ul>
                </div>
            </div>
            <div class="row product__filter">
            	<c:forEach items="${bestSellers}" var="item">
            		<div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix ${item.value[4] ?'hot-sales':'new-arrivals'}">
	                    <div class="product__item">
	                        <div class="product__item__pic set-bg" onclick="detail(${item.key})" data-setbg="/assets/img/product/${item.value[3][0]}">
	                            <span class="label">${item.value[4] ?'sale':'new'}</span>
	                            <ul class="product__hover">
	                                <li><a href="#"><img src="assets/img/icon/heart.png" alt=""></a></li>
	                                <li><a href="#"><img src="assets/img/icon/compare.png" alt=""> <span>Compare</span></a></li>
	                                <li><a href="#"><img src="assets/img/icon/search.png" alt=""></a></li>
	                            </ul>
	                        </div>
	                        <div class="product__item__text">
	                            <h6>${item.value[0]}</h6>
	                            <div onclick="addToCartOne('${item.key}')" class="add-cart">+ Add To Cart</div>
	                            <div class="rating">
	                                <i class="fa fa-star-o"></i>
	                                <i class="fa fa-star-o"></i>
	                                <i class="fa fa-star-o"></i>
	                                <i class="fa fa-star-o"></i>
	                                <i class="fa fa-star-o"></i>
	                            </div>
	                            <h5><fmt:formatNumber value="${item.value[1]}" pattern="#,###"/> &#8363;</h5>
	                        </div>
	                    </div>
	                </div>
            	</c:forEach>
            </div>
        </div>
    </section>
    <!-- Product Section End -->

    <!-- Categories Section Begin -->
    <section class="categories spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-2">
                    <div class="categories__text">
                        <h2>Hot Sale <br /> <span>Manga Collection</span> <br /> Bonus deal</h2>
                    </div>
                </div>
                <div class="col-lg-5">
                    <div class="categories__hot__deal">
                        <img src="https://cdn.wallpapersafari.com/67/0/Bf0DFA.jpg" style="height: 300px; width: 1000px">
                        <div class="hot__deal__sticker">
                            <span>Sale Off</span>
                            <h5>50%</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 offset-lg-1">
                    <div class="categories__deal__countdown">
                        <span>Deal Of The Week</span>
                        <h2>Multi-pocket Chest Bag Black</h2>
                        <div class="categories__deal__countdown__timer" id="countdown">
                            <div class="cd-item">
                                <span>3</span>
                                <p>Days</p>
                            </div>
                            <div class="cd-item">
                                <span>1</span>
                                <p>Hours</p>
                            </div>
                            <div class="cd-item">
                                <span>50</span>
                                <p>Minutes</p>
                            </div>
                            <div class="cd-item">
                                <span>18</span>
                                <p>Seconds</p>
                            </div>
                        </div>
                        <a href="#" class="primary-btn">Shop now</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Categories Section End -->

<%--    <!-- Instagram Section Begin -->--%>
<%--    <section class="instagram spad">--%>
<%--        <div class="container">--%>
<%--            <div class="row">--%>
<%--                <div class="col-lg-8">--%>
<%--                    <div class="instagram__pic">--%>
<%--                        <div class="instagram__pic__item set-bg" data-setbg="assets/img/instagram/instagram-1.jpg"></div>--%>
<%--                        <div class="instagram__pic__item set-bg" data-setbg="assets/img/instagram/instagram-2.jpg"></div>--%>
<%--                        <div class="instagram__pic__item set-bg" data-setbg="assets/img/instagram/instagram-3.jpg"></div>--%>
<%--                        <div class="instagram__pic__item set-bg" data-setbg="assets/img/instagram/instagram-4.jpg"></div>--%>
<%--                        <div class="instagram__pic__item set-bg" data-setbg="assets/img/instagram/instagram-5.jpg"></div>--%>
<%--                        <div class="instagram__pic__item set-bg" data-setbg="assets/img/instagram/instagram-6.jpg"></div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="col-lg-4">--%>
<%--                    <div class="instagram__text">--%>
<%--                        <h2>Instagram</h2>--%>
<%--                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut--%>
<%--                        labore et dolore magna aliqua.</p>--%>
<%--                        <h3>#Female_Fashion</h3>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </section>--%>
<%--    <!-- Instagram Section End -->--%>

    <%@include file="templates/users/_footer.jsp" %>

    <!-- Search Begin -->
    <div class="search-model">
        <div class="h-100 d-flex align-items-center justify-content-center">
            <div class="search-close-switch">+</div>
            <form class="search-model-form">
                <input type="text" id="search-input" placeholder="Search here.....">
            </form>
        </div>
    </div>
    <!-- Search End -->
    
    <script type="text/javascript">
	    function detail(id) {
	    	location.assign("http://localhost:8080/shop/detail/"+id);
		}
	    async function addToCartOne(id) {
		    if(${not empty user}){
	    	  const response = await fetch("http://localhost:8080/shop/cart/add?id="+id+"&qty=1");
			}
		    else {
	    	  location.assign("http://localhost:8080/sign-in");
			}
	    }
    </script>

    <!-- Js Plugins -->
    <script src="assets/js/jquery-3.3.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.nice-select.min.js"></script>
    <script src="assets/js/jquery.nicescroll.min.js"></script>
    <script src="assets/js/jquery.magnific-popup.min.js"></script>
    <script src="assets/js/jquery.countdown.min.js"></script>
    <script src="assets/js/jquery.slicknav.js"></script>
    <script src="assets/js/mixitup.min.js"></script>
    <script src="assets/js/owl.carousel.min.js"></script>
    <script src="assets/js/main.js"></script>
</body>
</html>