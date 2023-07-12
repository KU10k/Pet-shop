<#import "parts/common.ftlh" as c>
<@c.page>
    <img src="/image/${product.image.id}" height="150px"><br>
    <b>Название:</b> ${product.title}<br><br>
    <b>Цена:</b> ${product.price}<br><br>
    <b>Описание:</b> ${product.description}<br><br>
    <b>Город:</b> ${product.city}

</@c.page>