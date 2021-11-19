<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add product</title>
</head>
<body>
    <h1>Add product</h1>
    <form method="post" action="/product">
        <label for="name">Name</label>
        <input id="name" name="name" placeholder="Milk">
        <br>
        <label for="price">Price</label>
        <input id="price" name="price" placeholder="100">
        <br>
        <label for="availableAmount">Available amount</label>
        <input id="availableAmount" name="availableAmount" placeholder="20">
        <br>
        <label for="description">Description</label>
        <textarea id="description" name="description" placeholder="description"></textarea>
        <br>
        <br>
        <input type="submit" value="Add product">
    </form>

</body>
</html>