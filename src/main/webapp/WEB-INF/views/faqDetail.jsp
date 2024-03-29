<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>FAQ 상세 페이지</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>

<body>

    <!-- 사이드바 -->
    <%@ include file="sidebar.jsp" %>

    <!-- Right Panel -->
    <div id="right-panel" class="right-panel">

        <!-- 헤더 -->
        <%@ include file="header.jsp" %>

        <div class="content">
            <div class="container mt-5">
                <h2>FAQ 상세 페이지</h2>

                <div class="card">
                    <form action="updateFaq" method="post">
                        <input name="noticeNumber" type="hidden" value="${faq.noticeNumber}" />
                        <!-- <input name="noticeDate" type="hidden" value="${faq.noticeDate}" /> -->
                        <div class="card-body card-block">
                            <div class="form-group"><label for="company"
                                    class=" form-control-label">질문</label><input type="text"
                                    value="${faq.noticeTitle}" class="form-control" name="noticeTitle">
                            </div>
                            <div class="form-group">
                                <label for="company" class="form-control-label">답변</label>
                                <textarea class="form-control" name="noticeContent" style="height: 200px;">${faq.noticeContent}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="company" class="form-control-label">처리담당자</label>
                                <select class="form-control" name="memberId">
                                    <c:forEach var="adminId" items="${adminMemberIds}">
                                        <option value="${adminId}" <c:if test="${adminId eq faq.memberId}">selected</c:if>>${adminId}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row justify-content-end">
                            <div class="col-sm-3">
                                <button type="submit" class="btn btn-outline-primary">수정</button>
                                <a href="deleteFaq?noticeNumber=${faq.noticeNumber}"
                                    class="btn btn-outline-danger"
                                    onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</a>
                                <a href="faq" class="btn btn-outline-secondary">뒤로가기</a>
                            </div>
                        </div>
                    </form>
                </div>

            </div>
        </div>

        <!-- 푸터 -->
        <%@ include file="footer.jsp" %>

    </div><!-- /#right-panel -->

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>

</html>
