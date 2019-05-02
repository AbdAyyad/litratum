<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/admin/">Literatum Admin</a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/admin/content/">content</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/new/">admins</a>
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