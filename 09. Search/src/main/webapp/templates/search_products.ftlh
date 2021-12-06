<#ftl encoding="UTF-8"/>
<html lang="en">
<head>
    <title>Search Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<script>
    function searchProduct(query) {
        let request = new XMLHttpRequest();

        request.open('POST', '/search/product?q=' + query, false);

        request.send();

        if (request.status !== 200) {
            alert('Ошибка!');
        } else {
            let html =
                '<tr>' +
                '<th>Name</th>' +
                '<th>Description</th>' +
                '<th>Price</th>' +
                '<th>Available amount</th>' +
                '</tr>'

            let response = JSON.parse(request.response);

            for (let i = 0; i < response.length; i++) {
                html += '<tr>';
                html += '<td>' + response[i]['name'] + '</td>';
                html += '<td>' + response[i]['description'] + '</td>';
                html += '<td>' + response[i]['price'] + '</td>';
                html += '<td>' + response[i]['amount'] + '</td>';
                html += '</tr>';
            }

            document.getElementById('products_table').innerHTML = html;
        }
    }
</script>
<div class="container">
    <div class="row">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <h1>Search page</h1>
            <label>
                <input id="name" name="name" placeholder="Name" onkeyup="searchProduct(document.getElementById('name').value)">
                Search by product's name
            </label>
            <br>
            <table class="table" id="products_table">

            </table>
        </div>
    </div>
</div>
</body>
</html>
