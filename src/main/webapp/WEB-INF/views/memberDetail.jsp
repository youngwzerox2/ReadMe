<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

            <!doctype html>
            <!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
            <!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
            <!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
            <!--[if gt IE 8]><!-->
            <html class="no-js" lang=""> 

            <head>
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <title>회원상세정보</title>
                <meta name="description" content="Ela Admin - HTML5 Admin Template">
                <meta name="viewport" content="width=device-width, initial-scale=1">

                <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
                <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">

                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/normalize.css@8.0.0/normalize.min.css">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/font-awesome@4.7.0/css/font-awesome.min.css">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/lykmapipo/themify-icons@0.1.2/css/themify-icons.css">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/pixeden-stroke-7-icon@1.2.3/pe-icon-7-stroke/dist/pe-icon-7-stroke.min.css">
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.2.0/css/flag-icon.min.css">
                <link rel="stylesheet" href="assets/css/cs-skin-elastic.css">
                <link rel="stylesheet" href="assets/css/style.css">

                <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>


            </head>

            <body>
                <!-- 사이드바 -->
                <%@ include file="sidebar.jsp" %>

                    <!-- Right Panel -->

                    <div id="right-panel" class="right-panel">

                        <!-- 헤더 -->
                        <%@ include file="header.jsp" %>

                            <div class="content">
                                <div class="animated fadeIn">

                                    <div class="row justify-content-center">

                                        <div class="col-lg-6">
                                            <div class="card">
                                                <div class="card-header"><strong>회원상세정보</strong></div>
                                                <form action="updateMember" method="post">
                                                    <div class="card-body card-block">
                                                        <div class="form-group"><label for="company"
                                                                class=" form-control-label">회원아이디</label><input
                                                                value="${member.memberId}" class="form-control"
                                                                name="memberId">
                                                        </div>
                                                        <div class="form-group"><label for="company"
                                                                class=" form-control-label">이름</label><input type="text"
                                                                value="${member.memberName}" class="form-control">
                                                        </div>
                                                        <div class="form-group"><label for="company"
                                                                class=" form-control-label">이메일</label><input
                                                                type="text" value="${member.memberEmail}"
                                                                class="form-control">
                                                        </div>
                                                        <div class="form-group"><label for="company"
                                                                class=" form-control-label">닉네임</label><input
                                                                type="text" value="${member.memberNickname}"
                                                                class="form-control">
                                                        </div>
                                                        <div class="form-group"><label for="company"
                                                                class=" form-control-label">성별</label><input type="text"
                                                                value="${member.memberGender}" class="form-control">
                                                        </div>
                                                        <div class="form-group"><label for="company"
                                                                class=" form-control-label">생년월일</label><input
                                                                type="text"
                                                                value="<fmt:formatDate value='${member.memberBirthday}' pattern='yyyy-MM-dd' />"
                                                                class="form-control">
                                                        </div>
                                                        <div class="form-group"><label for="company"
                                                                class=" form-control-label">전화번호</label><input
                                                                type="text" value="${member.memberTel}"
                                                                class="form-control">
                                                        </div>
                                                        <div class="form-group"><label for="company"
                                                                class=" form-control-label">가입일</label><input
                                                                type="text"
                                                                value="<fmt:formatDate value='${member.joinDate}' pattern='yyyy-MM-dd' />"
                                                                class="form-control">
                                                        </div>
                                                        <div class="form-group"><label for="company"
                                                                class=" form-control-label">탈퇴일</label><input
                                                                type="text"
                                                                value="<fmt:formatDate value='${member.quitDate}' pattern='yyyy-MM-dd' />"
                                                                class="form-control">
                                                        </div>
                                                        <div class="form-group"><label for="company"
                                                                class=" form-control-label">책장방문허용여부</label>
                                                            <input
                                                                value="${member.memberVisitAgree eq 1 ? 'yes' : 'no'}"
                                                                class="form-control">
                                                        </div>
                                                        <div class="form-group"><label for="company"
                                                                class=" form-control-label">회원상태</label>
                                                            <select class="form-control" name="memberGrade">
                                                                <option value="admin" <c:if
                                                                    test="${member.memberGrade eq 'admin'}">selected
                                                                    </c:if>>admin</option>
                                                                <option value="basic" <c:if
                                                                    test="${member.memberGrade eq 'basic'}">selected
                                                                    </c:if>>basic</option>
                                                                <option value="withdrawal" <c:if
                                                                    test="${member.memberGrade eq 'withdrawal'}">
                                                                    selected</c:if>>withdrawal</option>
                                                                <option value="blacklist" <c:if
                                                                    test="${member.memberGrade eq 'blacklist'}">selected
                                                                    </c:if>>blacklist</option>
                                                            </select>
                                                        </div>
                                                    <div class="form-group row justify-content-end">
                                                        <div class="col-sm-3">
                                                            <button type="submit"
                                                                class="btn btn-outline-primary">수정</button>
                                                            <a href="member" class="btn btn-outline-secondary">뒤로가기</a>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div><!-- .animated -->
                            </div><!-- .content -->

                            <div class="clearfix"></div>

                            <%@ include file="footer.jsp" %>

                    </div><!-- /#right-panel -->

                    <!-- Right Panel -->

                    <!-- Scripts -->
                    <script src="https://cdn.jsdelivr.net/npm/jquery@2.2.4/dist/jquery.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.4/dist/umd/popper.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"></script>
                    <script
                        src="https://cdn.jsdelivr.net/npm/jquery-match-height@0.7.2/dist/jquery.matchHeight.min.js"></script>
                    <script src="assets/js/main.js"></script>

            </body>

            </html>