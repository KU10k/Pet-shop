<!DOCTYPE html>
<html>
<head>

</head>
<body>
<h2>Pet-Shop</h2>

<#list  title as prod>
    <p><b>${prod.title}</b>| ${prod.price} | ${prod.caption} | ${prod.nameProduct} | </p>
</#list>

<button type="button" class="btn btn-primary" onclick="window.location.href ='/'">Back to Shop</button>
</body>
</html>