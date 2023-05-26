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
<form action="/" method="post">
    Название: <input type="text" name="title"><br><br>
    Цена: <input type="number" name="price"><br><br>
    Описание <textarea name="caption"></textarea><br><br>
    Бренд : <input type="text" name="nameProduct"><br><br>
    <button type="submit">Create</button>

</form>

</body>
</html>