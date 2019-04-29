<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/admin/">Literatum Admin</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <form id="content-form" method="post" action="/admin/content/">
                    <input type="hidden" name="adminEmail" value="${adminEmail}"/>
                    <a class="nav-link" onclick="document.getElementById('content-form').submit();">content</a>
                </form>
            </li>
            <li class="nav-item">
                <form id="admin-form" method="post" action="/admin/new/">
                    <input type="hidden" name="adminEmail" value="${adminEmail}"/>
                    <a class="nav-link" onclick="document.getElementById('admin-form').submit();">admins</a>
                </form>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <form method="post" action="/sign-out/">
                    <input type="hidden" name="userEmail" value="${adminEmail}">
                    <button type="submit" class="btn btn-outline-danger">Sign out</button>
                </form>
            </li>
        </ul>
    </div>
</nav>