var selectId = '<c:url value="${param.selectId}" />';
$("#selectType").val(selectId);

function goSearch(){

	var selectType = $("#selectType").val();
	if (selectType==1){
	
		$("#productForm").submit();
	}else {
	
		$("#shopName").val($("#searchName").val());
		$("#shopForm").submit();
	}
}
function logout(){

	var url = '<c:url value="/user.do?method=logout" />';
	window.location.href = url ;
}
function goBack(){
	window.location.href = '<c:url value="/shop.do?method=shopList" />';
}
function login(){

	window.location.href = '<c:url value="/acegilogin.jsp" />';
}

function newUser(){

	window.location.href = '<c:url value="/newUser.jsp" />';
}