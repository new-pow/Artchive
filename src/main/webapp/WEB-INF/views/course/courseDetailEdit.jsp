<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>arTchive / {코스 제목(글제목)}</title>
		<link rel="stylesheet" type="text/css" href="<c:url value='/tools/reset.css'/>"/>
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>">
		<!-- icon-kit -->
		<script src="https://kit.fontawesome.com/50d21a2bed.js" crossorigin="anonymous"></script>

		<link rel="stylesheet" type="text/css" href="<c:url value='/css/course/courseDetail.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/course/tagBox.css'/>">
		<link rel="stylesheet" type="text/css" href="<c:url value='/css/toggle.css'/>">
		<script src="<c:url value='/tools/jquery-3.6.0.min.js'/>"></script>
		<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
		<script src="<c:url value='/js/course/course.js'/>"></script>
		<script src="<c:url value='/js/course/autocomplete.js'/>"></script>
		<script src="<c:url value='/js/course/courseEdit.js'/>"></script>
	</head>
	<body id="courseDetailEdit">
		<!-- top으로 이동 -->
		<c:import url="/WEB-INF/views/layout/top.jsp" />
		
		<main id="courseDetail-wrap">
			<!-- headerTextBox -->
			<section id="courseHeader" class="courseHeaderImg">
				<div id="headerTextBox" style="background-image : url('https://cdn.imweb.me/thumbnail/20220405/fb85ea780e919.jpeg');">
					<div id="header-exhbnTitle" class="headerText">
						<input class="exhbn-title editInput" type="text" value="전시회명" readonly> <i class="fa-solid fa-magnifying-glass"></i>
					</div>
					<div id="header-postTitle" class="headerText">
						<input id="post-title" class="editInput h1" type="text" placeholder="제목을 입력하세요.">
					</div>
					<div id="header-postDate" class="headerText">
<%--						<input id="exhbn-date" class="">--%>
					</div>
           		</div><!-- .headerTextBox -->
			</section><!-- .courseHeader -->

			<!-- courseMain -->
			<section id="courseMenu">
				<div></div>
           		<div id="courseMainText">
					<input type="reset" id="editBtn"  class="white-btn" value="삭제">
					<input type="submit" id="deleteBtn"  class="black-btn" value="저장">
           		</div><!-- courseMainText -->
			</section><!-- courseMenu -->

			<!-- courseMain -->
			<article id="courseMain">
				<section class="tag-box-view">
					<h3>관련 태그</h3>
					<div class="tag-box-edit">
						<ul id="tagList">
							<input class="inner-searchbar" id="inner-searchbar" type="text">
						</ul>
					</div>
				</section> <!-- editCourseBtn -->
				<section id="course">
					<section id="timeline-container">
						<ul id="sortable" class="timeline-course-container">
							<div class="route-row" id="startPoint">
								<div class="line down"></div>
								<div class="left">
									<div class="dot"></div>
								</div>
								<div class="content explain">Start ...</div>
							</div>
							<li draggable="true" class="route-row ui-state-default">
								<div class="moveHandler">
									<i class="fa-solid fa-bars moveHandlerBtn"></i>
								</div>
								<div class="line"></div>
								<div class="left">
									<div class="mainCourse-dot">1</div>
								</div>
								<div class="content">
									<div class="where">
										<h3 class="where-title">전시관</h3>
										<div class="address">주소주소주소</div>
									</div>
								</div>
								<div class="content memo-box">
									<textarea class="place-memo-input" placeholder="메모를 입력하세요."></textarea>
								</div>
								<div class="delete">
									<img src="<c:url value='/image/minus.png'/>">
								</div>
							</li>
							<li draggable="true" class="route-row ui-state-default">
								<div class="moveHandler">
									<i class="fa-solid fa-bars moveHandlerBtn"></i>
								</div>
								<div class="line"></div>
								<div class="left">
									<div class="subCourse-dot">2</div>
								</div>
								<div class="content">
									<div class="where">
										<h3 class="where-title">식당</h3>
										<div class="address">주소주소주소</div>
									</div>
								</div>
								<div class="content memo-box">
									<textarea class="place-memo-input" placeholder="메모를 입력하세요."></textarea>
								</div>
								<div class="delete">
									<img src="<c:url value='/image/minus.png'/>">
								</div>
							</li>
							<li draggable="true" class="route-row ui-state-default">
								<div class="moveHandler">
									<i class="fa-solid fa-bars moveHandlerBtn"></i>
								</div>
								<div class="line"></div>
								<div class="left">
									<div class="subCourse-dot">3</div>
								</div>
								<div class="content">
									<div class="where">
										<h3 class="where-title">산책로</h3>
										<div class="address">주소주소주소</div>
									</div>
								</div>
								<div class="content memo-box">
										<textarea class="place-memo-input" placeholder="메모를 입력하세요."></textarea>
								</div>
								<div class="delete">
									<img src="<c:url value='/image/minus.png'/>">
								</div>
							</li>
							<li draggable="true" class="route-row ui-state-default">
								<div class="moveHandler">
									<i class="fa-solid fa-bars moveHandlerBtn"></i>
								</div>
								<div class="line"></div>
								<div class="left">
									<div class="subCourse-dot">4</div>
								</div>
								<div class="content">
									<div class="where">
										<h3 class="where-title">카페</h3>
										<div class="address">주소주소주소</div>
									</div>
								</div>
								<div class="delete">
									<i class="fa-solid fa-pen portlet-toggle"></i>
									<img src="<c:url value='/image/minus.png'/>">
								</div>
							</li>
							<div class="route-row" id="endPoint">
								<div class="line up"></div>
								<div class="left">
									<div class="dot"></div>
								</div>
								<div class="content explain">End ...</div>
							</div>
						</ul>
					</section>
					<section id="courseMap">
						map
					</section><!-- courseMap -->
				</section><!-- Course -->
			</article><!-- courseMain -->

			<section id="searchMap-container">
				<div id="searchCourse">
					<ul>
						<li>Search.</li>
					</ul>
					<input type="text" id="courseSearch" name="searchMessage" size="120"  placeholder="검색어를 입력하세요."/>
					<input type="button" id="CourseSearchBtn" value="검색">
				</div>
				
				<div id = "recommendCourse">
				 <div class="courseAdd">
				 	<div class="courseAddBtn">
				 		<input type="button" class="white-btn" id="courseAddBtn1" value="장소 추가">
				 	</div>
				 </div>
				 
				  <div class="courseAdd">
				 	<div class="courseAddBtn">
				 		<input type="button" class="white-btn" id="courseAddBtn1" value="장소 추가">
				 	</div>
				 </div>
				 
				  <div class="courseAdd">
				 	<div class="courseAddBtn">
				 		<input type="button" class="white-btn" id="courseAddBtn1" value="장소 추가">
				 	</div>
				 </div>
				 
				  <div class="courseAdd">
				 	<div class="courseAddBtn">
				 		<input type="button" class="white-btn" id="courseAddBtn1" value="장소 추가">
				 	</div>
				 </div>
				</div>
			</section>
		</main>

		 <!-- bottom 이동 -->
         <c:import url="/WEB-INF/views/layout/bottom.jsp" />
	</body>
</html>