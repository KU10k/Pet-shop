<#import "parts/common.ftlh" as c>
<@c.page>
    <h1 style="text-align: center">Личный кабинет</h1>
    <hr><br>
    <div style="text-align: center">
        Имя: <b>${user.nickName}</b>
        <br>
        Электоронная почта: <b>${user.email}</b>
        <br>
        <br>
        <button type="button" class="btn btn-dark" onclick="window.location.href = '/my/products'">Мои товары</button>
        <br>
        <br>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger">
                Выйти из аккаунта
            </button>
        </form>
    </div>


</@c.page>
