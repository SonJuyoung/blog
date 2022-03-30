<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="layout/header.jsp"%>

<div class="container">
    <c:forEach var="board" items="${boards.content}"> <%-- 컨트롤러에서 boards로 담아서 보내줬지만 paging 처리 했기때문에 boards.content로 불러와야 오류 안남 --%>
    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">${board.title}</h4>
            <a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
        </div>
    </div>
    </c:forEach>

    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${boards.first}">  <%-- 첫번째 페이지라면 --%>
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${boards.last}">
                <li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
                <li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
<div id="map" style="width:750px;height:350px;margin:auto !important;"></div>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a584728120e818fe29164943cc496c04"></script>
<script>
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(35.892534,128.598740), // 지도의 중심좌표
            level: 2, // 지도의 확대 레벨
            mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
        };

    // 지도를 생성한다
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 지도에 마커를 생성하고 표시한다
    var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(35.892534,128.598740), // 마커의 좌표
        map: map // 마커를 표시할 지도 객체
    });
    var infowindow = new kakao.maps.InfoWindow({
        content : '<div style="padding:5px;">무지개연구소❤</div>' // 인포윈도우에 표시할 내용
    });

    // 인포윈도우를 지도에 표시한다
    infowindow.open(map, marker);

</script>

<%@ include file="layout/footer.jsp"%>