<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Header Section Begin -->
<header class="header">
    <div class="container">
        <div class="row">
            <div class="col-lg-3 col-md-3">
                <div class="header__logo">
                    <a href="/"><img src="assets/img/logo.png" alt=""></a>
                </div>
            </div>
            <div class="col-lg-6 col-md-6">
                <nav class="header__menu mobile-menu">
                    <ul>
                        <li class="active"><a href="/"><s:message code="label.home"/></a></li>
                        <li><a href="/shop">Shop</a></li>
                        <c:if test="${not empty user}">
                            <li><a href="/account/index">${user.fullname}</a></li>
                        </c:if>
                        <c:if test="${user.admin}">
                            <li><a href="/admin/product"><s:message code="label.about"/></a></li>
                        </c:if>
                        <c:if test="${empty user}">
                            <li><a href="/sign-in"><s:message code="label.new"/></a></li>
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