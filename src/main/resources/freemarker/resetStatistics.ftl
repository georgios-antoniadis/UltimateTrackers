<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reset Tables</title>
</head>
<body>
<a href="/">Index</a>
|
<b><a href="/emptyTables">Reset Tables</a></b>
<hr>
<h2>Buttons to reset tracker tables individually</h2>

<hr>
<hr>

<div style="text-align: center">
    <form action="/emptyDice" method="post">
        <input type="submit" value="Empty Dice Logs">
    </form>
    <form action="/emptyDiceStats" method="post">
        <input type="submit" value="Empty Dice Tracker Table">
    </form>
</div>

<hr>

<div style="text-align: center">
    <form action="/emptyAnimal" method="post">
        <input type="submit" value="Empty Animal Logs">
    </form>
    <form action="/emptyAnimalStats" method="post">
        <input type="submit" value="Empty Animal Tracker Table">
    </form>
</div>

<hr>

<div style="text-align: center">
    <form action="/emptyShape" method="post">
        <input type="submit" value="Empty Shape Logs">
    </form>
    <form action="/emptyShapeStats" method="post">
        <input type="submit" value="Empty Shape Tracker Table">
    </form>
</div>


</body>
</html>