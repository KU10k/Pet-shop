<!DOCTYPE html>
<html>
<head>
    <title>Pet-Shop</title>
</head>
<body>
<h2>Pet-Shop</h2>
<#list  products as product>
    <p><b>${product.title}</b> | ${product.nameProduct} | ${product.price}</p>
</#list>
<hr>
<h4>Добавить товар</h4>
<from action="/create" method="post">
    Заголовок: <input type="text" name="title"/><br><br>
    Цена: <input type="number" name="price"/><br><br>
    Описание: <textarea name="caption"></textarea><br><br>
    Название продукта: <input type="text" name="nameProduct"/><br><br>
<button type="submit">Создать</button>
</from>
</body>
</html>