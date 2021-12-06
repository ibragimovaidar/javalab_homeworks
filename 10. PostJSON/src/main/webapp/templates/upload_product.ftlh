<#ftl encoding="UTF-8"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Upload product</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <p class="h3">Add product</p>
            <form>
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name">
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <input type="text" class="form-control" id="description" name="description">
                </div>
                <div class="mb-3">
                    <label for="price" class="form-label">Price</label>
                    <input type="text" class="form-control" id="price" name="price">
                </div>
                <div class="mb-3">
                    <label for="amount" class="form-label">Amount</label>
                    <input type="text" class="form-control" id="amount" name="amount">
                </div>
                <button type="button" class="btn btn-primary" onclick="uploadProduct(
                    document.getElementById('name').value,
                    document.getElementById('description').value,
                    parseInt(document.getElementById('price').value),
                    parseInt(document.getElementById('amount').value)
                )">
                    Add product
                </button>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-10 offset-md-1">
            <table class="table" id="products_table">

            </table>
        </div>
    </div>
</div>
<script>
    renderProductsList();
    function renderProductsList() {
        let request = new XMLHttpRequest();
        request.open('GET', '/product', false);
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

    function uploadProduct(name, description, price, amount) {
        let body = {
            "name" : name,
            "description" : description,
            "price": price,
            "amount": amount
        };
        let request = new XMLHttpRequest();
        request.open('POST', '/product', false);
        request.setRequestHeader('Content-Type', 'application/json');
        request.send(JSON.stringify(body));

        if (request.status !== 200) {
            alert('Ошибка!');
        } else {
            renderProductsList();
        }
    }
</script>
</body>
</html>