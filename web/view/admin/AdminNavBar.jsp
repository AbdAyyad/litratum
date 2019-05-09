<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/admin/">Literatum Admin</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/admin/content/">content</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    admins
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="/admin/new/">Create Admin</a>
                    <a class="dropdown-item" href="/admin/admin/all/">Delete Admin</a>
                    <a class="dropdown-item" href="/admin/backstage/all/">Delete Backstage Admin</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/users/">users</a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <form method="post" action="/admin/sign-out/">
                    <button type="submit" class="btn btn-outline-danger">Sign out</button>
                </form>
            </li>
        </ul>
    </div>
</nav>