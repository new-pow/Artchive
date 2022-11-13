
/**
 * edit.js
 */

 $(document).ready(function(){
  	
	// 전체 수정
  $("#editFrm").on('submit', function(){
	  // 기본 기능 중단
	  event.preventDefault();
  
	  var userId = $('#userId').val();
	  var userName = $('#userName').val();
	  var userNickname = $('#userNickname').val();
	  var userEmail1 = $('#userEmail1').val();
	  var userEmail2 = $('#userEmail2').val();
	  var userEmail = userEmail1 + userEmail2;
	  
	  
	   $.ajax({
		   type:"post",
		   url:"/myPage/updateUser/",
		   data: {
			  "userName":userName,
			  "nickname": userNickname,
			  "userEmail1":userEmail1,
			  "userEmail2":userEmail2
		   },
		  success:function(result){
			  // 성공 시 결과 받음
			  if(result == "SUCCESS"){
				  alert("수정되었습니다.");
				  location.href="/myPage/edit/"
			  }
		  },
		  error:function(){
			  // 오류있을 경우 수행 되는 함수
			  alert("전송 실패");
		  }
	   }); 
   });
   
   
// 이름 버튼 클릭 시 유효성 검사
$('#nameBtn').on('click', function () {

  var userName = $('#userName').val();
  
  if ($('#nameBtn').val() == "수정") {
	  $('.nameBox').slideToggle();
	  $('#nameBtn').val("확인");
	  
  }else if ($('#nameBtn').val() == "확인") {
		 console.log(userName);
	  if(!userName){
		 alert("이름을 입력해주세요.");
		 return ;
	 } else if(!validNameCheck(userName)){
		 alert("이름은 한글과 영문으로만 입력해주세요.");
		 return ;
	 } else {
		 $.ajax({
		  type: "post",
		  url: "/myPage/updateUser",
		  data: {
			  "userName":userName,
			  "nickname": "false",
			  "userEmail1":"false",
			  "userEmail2":"false",
		  },
		  success: function (result) {
			  
			  if(result){
			  	alert("수정되었습니다.");
			  	location.href="/myPage/edit";
			  }
			
		  },
		  error: function () {
			  alert('오류!')
		  }
	  }); //ajax
	 }
  }
});
  
// 닉네임 버튼 클릭시 중복 체크
$('#nicknameBtn').on('click', function () {

  var userNickname = $('#userNickname').val();
  
  if ($('#nicknameBtn').val() == "수정") {
	  $('.nicknameBox').slideToggle();
	  $('#nicknameBtn').val("확인");
  }else if ($('#nicknameBtn').val() == "확인") {
	// 닉네임 유효성 검사
	  if(!userNickname){
		 alert("닉네임을 입력해주세요.");
		 return ;
	 } else if(!validNickNameCheck(userNickname)){
		 alert("유효하지 않은 닉네임입니다. 한글, 영문, 숫자만 가능하며 2~10자 내로 입력해주세요.");
		 return ;
	 } else {
	  $.ajax({
		  type: "post",
		  url: "/user/nicknameCheck",
		  data: {
			  "nickname": userNickname
		  },
		  success: function (result) {
				  if(result){
					  if(confirm("사용할 수 있는 닉네임입니다. 수정하시겠습니까?")){
						  $.ajax({
						  type: "post",
						  url: "/myPage/updateUser",
						  data: {
							  "userName":"false",
							  "nickname": userNickname,
							  "userEmail1":"false",
							  "userEmail2":"false",
						  },
						  success: function (result) {
							  if(result){
								  alert("수정되었습니다.");
								  location.href="/myPage/edit";
							  }
							
						  },
						  error: function () {
							  alert('오류!')
					  }
				  }); //ajax
					  }
					  
			  } else{
				  alert("중복된 닉네임입니다.");
			  }
		  },
		  error: function () {
			  alert('오류!')
		  }
	  });
	  }
  }
});


var email_check = false;

// 이메일 버튼 클릭시 중복 체크
$('#emailBtn').on('click', function () {

  var userEmail1 = $('#userEmail1').val();
  var userEmail2 = $('#userEmail2').val();
  var userEmail = userEmail1 + "@" + userEmail2;

  console.log(userEmail);

  if ($('#emailBtn').val() == "수정") {
	  $('.emailBox').slideToggle();
	  $('#emailBtn').val("확인");
  } else if ($('#emailBtn').val() == "확인") {
	  // 이메일 유효성 검사
	  if(!userEmail1){
		 alert("이메일을 입력해주세요.");
		 return ;
	 } else if(!validEmailCheck(userEmail1)){
		 alert("유효하지 않은 이메일입니다. 영문과 숫자를 조합하여 6~14자 내로 입력해주세요.");
		 return ;
	 } else { 
	  if (email_check) {
		  if (!$('#userEmailNum').val()) {
		  alert("인증 번호를 입력하세요.");
		  return;
	  }
		  $.ajax({
			  type: "post",
			  url: "/user/emailNumCheck",
			  data: {
				  "email": userEmail,
				  "email_check": $('#userEmailNum').val()
			  },
			  success: function (result) {
				  if (result) {
					  $.ajax({
					  type: "post",
					  url: "/myPage/updateUser",
					  data: {
						  "userName":"false",
						  "nickname": "false",
						  "userEmail1":userEmail1,
						  "userEmail2":userEmail2
					  },
					  success: function (result) {
						  if(result){
						  	 alert("이메일이 갱신되었습니다.");
						  	location.href="/myPage/edit";
						  }
						
					  },
					  error: function () {
						  alert('오류!')
					  }
				  }); //ajax
				  } else {
					  $('#emailInput').attr("readonly", false);
				  }
				  // alert(result);
			  },
			  error: function () {
				  alert('오류!')
			  },
		  });
	  } else {
		  $('#emailInput').attr("readonly", true);
		  $('#emailBtn').attr("disabled", true);
		  $.ajax({
			  type: "post",
			  url: "/user/emailCheck",
			  data: {
				  "email": userEmail
			  },
			  success: function (result) {
				  if (result) {
				  email_check=true;
					  $('.emailNumBox').slideToggle();
					  $('#emailBtn').attr("disabled", false);
					  alert("사용 가능한 이메일입니다."); // 결과 창 반환
				  } else {
					  $('#emailInput').attr("readonly", false);
					  alert("중복된 이메일입니다.");
				  }
				  // alert(result);
			  },
			  error: function () {
				  alert('오류!')
			  },
		  });
	  } // else      
	 } // else  
  }
});

  
  // 글자 수 제한
   	$('#userName').on('keyup', function(){
		$(this).attr('maxlength', '12');
	});
	
	$('#userNickname').on('keyup', function(){
		$(this).attr('maxlength', '12');
	});
	
	$('#userEmail1').on('keyup', function(){
		$(this).attr('maxlength', '12');
	});


// 이메일 유효성 검사
function validEmailCheck(email) {
	var reg = /^[a-z]+[a-z0-9]{5,14}$/g;
	
	if (false === reg.test(email)) {
	  console.log('유효하지 않은 이메일입니다. 영문과 숫자를 조합하여 6~14자 내로 입력해주세요.');
	  return false;
  } else {
	  console.log("통과");
	  return true;
  }
  
}


// 닉네임 유효성 검사
function validNickNameCheck(nickname) {
	var reg = /^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|]{2,10}$/g;
	
	if (false === reg.test(nickname)) {
	  console.log('유효하지 않은 닉네임입니다. 한글, 영문, 숫자만 가능하며 2~10자 내로 입력해주세요.');
	  return false;
  } else {
	  console.log("통과");
	  return true;
  }
  
}


// 이름 유효성 검사
function validNameCheck(name) {

  var reg = /^[a-zA-Zㄱ-힣][a-zA-Zㄱ-힣 ]*$/;

  if (false === reg.test(name)) {
	  console.log('이름은 한글과 영문으로만 입력해주세요.');
	  return false;
  } else {
	  console.log("통과");
	  return true;
  }

} 
  
  
}); // document.ready