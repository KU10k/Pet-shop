<#import "parts/common.ftlh" as c>
<@c.page>
    <form method="get" action="/">
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="searchCity">Город</label>
                <select id="searchCity" name="" class="form-control">
                    <option value="">Город не выбран</option>
                    <#--                    <#list towns as t>-->
                    <#--                        <option <#if searchCity == t>selected</#if> value="${t}">${t}</option>-->
                    <#--                    </#list>-->
                </select>
            </div>
            <div class="form-group col-md-10">
                <label for="search_request">Поиск по объявлениям</label>
                <input type="text" name="search_request" <#if title??>value="${title}"</#if> class="form-control" id="search_request">
            </div>
            <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
        </div>
    </form>
    <div class="card-columns">
        <#list products as product>
            <a href="/${product.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">
                    <img src="/image/${product.image.id}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${product.title}</h5>
                        <p class="card-text">${product.price}</p>
                    </div>
                    <#--                    <div class="card-footer">-->
                    <#--                        ${product.city}-->
                    <#--                    </div>-->
                    <div class="card-footer">
                        <#--                        ${product.author}-->
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
    <hr>
    <h3>Создание нового товара:</h3>
    <form action="/" method="post" enctype="multipart/form-data">
        Наименование товара: <input type="text" name="title">
        <br><br>
        Цена: <input type="number" name="price">
        <br><br>
        Описание:
        <textarea name="description"></textarea>
        <br><br>
        Фотография: <input type="file" name="file"><br><br>
        <button type="submit">Создать</button>
    </form>

</@c.page>
<#--<form method="get" action="/">-->
<#--    Поиск <input type="text" name="title" value="${title?if_exists}">-->
<#--    <button type="submit">Поиск</button>-->
<#--</form>-->
<#--<#list  products as product>-->
<#--    <p><a href="/${product.id}"><b>${product.title}</b></a> | ${product.nameProduct} | ${product.price}</p>-->
<#--</#list>-->
<#--<hr>-->
<#--<h4>Добавить товар</h4>-->

<#--<form action="/" method="post" enctype="multipart/form-data">-->
<#--    Наименование товара: <input type="text" name="title"><br><br>-->

<#--    Бренд: <input type="text" name="nameProduct"><br><br>-->

<#--    Цена: <input type="number" name="price"><br><br>-->

<#--    Описание <textarea name="caption"></textarea><br><br>-->

<#--    Фотография:<input type="file" name="image-file"><br><br>-->

<#--    <button type="submit">Создать</button>-->

<#--</form>-->