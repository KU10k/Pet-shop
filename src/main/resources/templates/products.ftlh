<#import "parts/common.ftlh" as c>
<@c.page>
<form method="get" action="/">
    <div class="form-row">
        <div class="form-group col-md-2">
            <label for="searchCity">Город</label>
            <select id="searchCity" name="searchCity" class="form-control">
                <option value="">Город не выбран</option>
                <#list cities as c>
                    <option <#if searchCity == c>selected</#if> value="${c}">${c}</option>
                </#list>
            </select>
        </div>
        <div class="form-group col-md-10">
            <label for="searchWord">Поиск по объявлениям</label>
            <input type="text" name="searchWord" <#if searchWord??>value="${searchWord}"</#if> class="form-control" id="searchWord">
        </div>
        <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
    </div>
</form>

<div class="card-columns">
    <#list products as product>
        <a href="/product/${product.id}" class="product-link">
            <div class="card text-white bg-dark my-3 product">

                <img src="/image/${product.image.id}"
                     class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title">${product.title}</h5>
                    <p class="card-text">${product.price} ₽</p>
                </div>
                <div class="card-footer">
                    ${product.city}
                </div>
                <div class="card-footer">
                    ${product.user.nickName}
                </div>
            </div>
        </a>
    <#else>
        <h1 style="text-align: center">Не найдено</h1>
    </#list>
</div>
</@c.page>