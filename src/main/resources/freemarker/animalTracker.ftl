<#--Giorgos Antoniadis-->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Animal Tracker</title>
</head>
<body>
<a href="/">Index</a>
|
<b><a href="/animalTracker">Animal Tracker</a></b>
<hr>
<h2>Game of random animal...</h2>
<hr>
<div style="text-align: center">
    <#if numberOfDice??>
        <img src="images/animals/${numberOfDice}.jpg">
    <#else>
        <img src="images/questionMark.png">
    </#if>
    <form action="/randomAnimal" method="post">
        <#--when the button is clicked, it requested endpoints "/throwDice"-->
        <input type="submit" value="Random Animal">
    </form>
</div>
<hr>
<#-- checks if allDieResults which is a list, is empty or not -->
<#-- https://freemarker.apache.org/docs/ref_builtins_expert.html#ref_builtin_has_content -->
<#if allAnimalResults?has_content>
    <table style="margin-left: auto;margin-right: auto">
        <tr>
            <th>ID</th>
            <th>Result</th>
            <th>Date</th>
        </tr>
        <#list allAnimalResults as animalResult>
            <tr>
                <td>${animalResult.id}</td>
                <td>${animalResult.result}</td>
                <td>${animalResult.throwDate}</td>
            </tr>
        </#list>
    </table>
</#if>

</body>
</html>