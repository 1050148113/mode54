<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	

<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>修改栏目 - 异清轩博客管理系统</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">
<link rel="apple-touch-icon-precomposed" href="images/icon/icon.png">
<link rel="shortcut icon" href="images/icon/favicon.ico">
<script src="js/jquery-2.1.4.min.js"></script>
<!--[if gte IE 9]>
  <script src="js/jquery-1.11.1.min.js" type="text/javascript"></script>
  <script src="js/html5shiv.min.js" type="text/javascript"></script>
  <script src="js/respond.min.js" type="text/javascript"></script>
  <script src="js/selectivizr-min.js" type="text/javascript"></script>
<![endif]-->
<!--[if lt IE 9]>
  <script>window.location.href='upgrade-browser.html';</script>
<![endif]-->
</head>

<body class="user-select">
<section class="container-fluid">
  
  <jsp:include page="/public/head.jsp"></jsp:include>
  
  <div class="row">
    <jsp:include page="/public/menu.jsp"></jsp:include>
    
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
      <h1 class="page-header">修改栏目</h1>
      <form action="/Category/update" method="post">
        <div class="form-group">
          <label for="category-name">栏目名称</label>
          <input type="text" id="category-name" name="name" value="" class="form-control" placeholder="在此处输入栏目名称" required autocomplete="off">
          <span class="prompt-text">这将是它在站点上显示的名字。</span> </div>
        <div class="form-group">
          <label for="category-alias">栏目别名</label>
          <input type="text" id="category-alias" name="category-alias" value="" class="form-control" placeholder="在此处输入栏目别名" required autocomplete="off">
          <span class="prompt-text">“别名”是在URL中使用的别称，它可以令URL更美观。通常使用小写，只能包含字母，数字和连字符（-）。</span> </div>
        <div class="form-group">
          <label for="category-fname">父节点</label>
          <select id="category-fname" class="form-control" name="fid">
              <c:forEach items="${userlm}" var="u">
                <option value="">${u.columnName}</option>
               </c:forEach>
          </select>
          <span class="prompt-text">栏目是有层级关系的，您可以有一个“音乐”分类目录，在这个目录下可以有叫做“流行”和“古典”的子目录。</span> </div>
        <div class="form-group">
          <label for="category-keywords">关键字</label>
          <input type="text" id="category-keywords" name="category-keywords" value="" class="form-control" placeholder="在此处输入栏目关键字" autocomplete="off">
          <span class="prompt-text">关键字会出现在网页的keywords属性中。</span> </div>
        <div class="form-group">
          <label for="category-describe">描述</label>
          <textarea class="form-control" id="category-describe" name="describe" rows="4" autocomplete="off"></textarea>
          <span class="prompt-text">描述会出现在网页的description属性中。</span> </div>
          <input type="hidden" id="columnsid" value="">
        <button class="btn btn-primary" type="button" name="submit" onclick="savea()">更新</button>
      </form>
    </div>
  </div>
</section>
<script>
function savea() {
	
	var data={};
	data.columnName=$('#category-name').val();
	data.aliasName=$('#category-alias').val();
	data.parentId=$('#category-fname').val();
	data.keyWords=$('#category-keywords').val();
	data.description=$('#category-describe').val();
	
	
	
	$.post("user.s?op=savea",data,
	     function(data){
		    alert(data);
	      }
	);
	
	
	 window.location.replace("category.jsp") 
}
$(function () {
    $("#main table tbody tr td a").click(function () {
        var name = $(this);
        var id = name.attr("rel"); //对应id   
        if (name.attr("name") === "see") {
            $.ajax({
                type: "POST",
                url: "user.s?op=finda",
                data: "id=" + id,
                cache: false, //不缓存此页面   
                success: function (data) {
                    var data = JSON.parse(data);
					$('#category-name').val(data.columnName);
					$('#category-alias').val(data.aliasName);
					$('#category-fname').val(data.parentId);
					$('#category-keywords').val(data.keyWords);
					$('#category-describe').val(data.description);
                    $('#main').modal('show');
                }
           
            });
        } else if (name.attr("name") === "delete") {
            if (window.confirm("此操作不可逆，是否确认？")) {
                $.ajax({
                    type: "POST",
                    url: "/User/delete",
                    data: "id=" + id,
                    cache: false, //不缓存此页面   
                    success: function (data) {
                        window.location.reload();
                    }
                });
            };
        };
    });
});
</script>
<c:if test="${ ! empty msg }">
<script type="text/javascript">
      
   alert('${msg }');
   </script>
</c:if>
</body>

</html>
                      
