<header>
    <h1><span>Restaurant</span></h1>
    <nav>
        <ul>
            <li ${param.page eq 'Home'?'id="actual"':""}>
                <a href="Controller">Home</a>
            </li>
            <li ${param.page eq 'User Overview'?'id="actual"':""}>
                <a href="Controller?action=Overview">Users</a>
            </li>
            <li ${param.page eq 'Contact Overview'?'id="actual"':""}>
                <a href="Controller?action=ContactOverview">Contacts</a>
            </li>
            <li ${param.page eq 'Search'?'id="actual"':""}>
                <a href="Controller?action=Search">Search</a>
            </li>
            <li ${param.page eq 'Sign up'?'id="actual"':""}>
                <a href="Controller?action=Register">Sign up</a>
            </li>
        </ul>
    </nav>
    <h2>${param.page}</h2>
</header>
