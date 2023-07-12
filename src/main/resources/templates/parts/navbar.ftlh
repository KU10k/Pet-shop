<#include "security.ftlh"/>
<#macro navbar>
    <nav class="navbar navbar-dark bg-dark">
        <div class="container-fluid">
            <a href="/">
                <span class="navbar-brand mb-0 h1">Pet Shop</span>
            </a>
            <#if auth>

                    <button class="btn btn-light my-2 my-sm-0" type="button"
                            onclick="window.location.href = '/profile'">
                        Профиль
                    </button>


            <#else>
                <button class="btn btn-light my-2 my-sm-0" type="button"
                        onclick="window.location.href = '/login'">
                    Войти
                </button>
            </#if>
        </div>
    </nav>
</#macro>