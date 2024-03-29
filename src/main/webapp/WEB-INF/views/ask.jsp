<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>1:1문의 페이지</title>
    <link rel="icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <!-- <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"> -->
    <style>
        table {
            margin: 0 auto; /* 가운데 정렬 */
        }
        th {
            text-align: center;
            white-space: nowrap; /* 텍스트 줄 바꿈 방지 */
        }

        @media (max-width: 768px) {
            /* 창이 768px 이하일 때 적용되는 스타일 */
            th {
                white-space: nowrap; /* 긴 텍스트가 잘리지 않도록 설정 */
            }
        }

        .card-body {
            display: flex;
            height: 100%; /* 높이 100% 설정 */
        }
        
        .mr-3 {
            /* 기존 스타일 유지 */
        }
        
    </style>
</head>

<body>

    <!-- 사이드바 -->
    <%@ include file="sidebar.jsp" %>

    <!-- Right Panel -->
    <div id="right-panel" class="right-panel">

        <!-- 헤더 -->
        <%@ include file="header.jsp" %>
        
        <div class="content">
            <div class="col-md-12 mx-auto">
                <div class="card">
                    <div class="card-header">
                        <strong class="card-title">1:1 문의</strong>
                    </div>
                    <div class="card-body" style="display: flex;">
                        <div class="mr-3" style="margin-left: 10px;" >
                            <button class="btn btn-outline-primary btn-sm" id="showAll">전체 글보기</button>
                            <hr />
                            <button class="btn btn-outline-warning btn-sm" id="showUnanswered">답변 미완료 글 보기</button>
                            <hr />
                            <button class="btn btn-outline-success btn-sm" id="showAnswered">답변 완료한 글 보기</button>
                        </div>

                        <div style="max-height: 400px; overflow-y: auto; margin-left: 100px;">
                            <table id="askTable" class="table table-striped" >
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">문의 날짜</th>
                                        <th scope="col">문의 아이디</th>
                                        <th scope="col">문의 제목</th>
                                        <th scope="col">문의 처리 여부</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${askList}" var="ask">
                                        <tr class="${ask.askReplyDone eq 'yes' ? 'answered' : 'unanswered'}" style="background-color: white;">
                                            <td>${ask.askId}</td>
                                            <td><fmt:formatDate value="${ask.askDate}" pattern="yyyy-MM-dd" /></td>
                                            <td><a href="askDetail?askId=${ask.askId}">${ask.memberId}</a></td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${fn:length(ask.askTitle) > 15}">
                                                        <c:out value="${fn:substring(ask.askTitle, 0, 15)}..."/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${ask.askTitle}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${ask.askReplyDone}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

        </div><!-- .content -->
        <div style="margin-bottom: 20px;"></div>
        <!-- 푸터 -->
        <%@ include file="footer.jsp" %>
    </div><!-- /#right-panel -->

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function() {
            // 전체 행 표시
            $("#showAll").click(function() {
                $("#askTable tbody tr").show();
            });
    
            // 답변 미완료 행만 표시
            $("#showUnanswered").click(function() {
                $("#askTable tbody tr.answered").hide();
                $("#askTable tbody tr.unanswered").show();
            });
    
            // 답변 완료 행만 표시
            $("#showAnswered").click(function() {
                $("#askTable tbody tr.unanswered").hide();
                $("#askTable tbody tr.answered").show();
            });
        });
    </script>
</body>
</html>
