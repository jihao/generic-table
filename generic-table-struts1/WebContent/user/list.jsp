<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="css/common.css" rel="stylesheet" type="text/css"></link>
<link href="css/pandora.css" rel="stylesheet" type="text/css"></link>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {

	
});
function deleteUser(event, obj)
{
	var id = $(obj).parent().prev().find('input').first().val();
	alert("Demonstrage we can get anything with powerful jQuery [id="+id+"]");
};
</script>
<title>UserList</title>
</head>
<body>



	<div id="bodyContainer">
		<div id="container" class="create">
		
			<div id="servicebox" class="clearfix columns">	
			
			<jsp:include page="../common/uniform_table_list.jsp"></jsp:include>
			
			</div>
		</div>
	</div>
</body>
</html>