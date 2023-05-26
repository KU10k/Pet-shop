<!DOCTYPE html>
<html>
<head>
    <title>Pet-Shop</title>
</head>
<body>
<h2>Pet-Shop</h2>
<form method="get" action="/search">
    Поиск <input type="text" name="search_request">
    <button type="submit">Поиск</button>
</form>
<#list  products as product>
    <p><b>${product.title}</b> | ${product.nameProduct} | ${product.price}</p>
</#list>
<hr>
<h4>Добавить товар</h4>
<from action="/create" method="post">
    Title: <input type="text" name="title"/><br><br>
    Name Product: <input type="text" name="nameProduct"/><br><br>
    Price: <input type="number" name="price"/><br><br>
    Caption: <textarea name="caption"></textarea><br><br>
    <button type="submit">Создать</button>
</from>

</body>
</html>