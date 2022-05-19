<#--Vasilis Rozakos-->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Geometric Shape Tracker</title>
</head>
<body>
<a href="/">Index</a>
|
<b><a href="/geometricShapeTracker">Geometric Shape Tracker</a></b>
<hr>
<h2>Game of random geometric shape...</h2>
<hr>
<div style="text-align: center">
    <#if numberOfDice??>
        <img src="images/geometricShapes/${numberOfDice}.png">
    <#else>
        <img src="images/questionMark.png">
    </#if>
    <form action="/randomGeometricShape" method="post">
        <#--when the button is clicked, it requested endpoints "/throwDice"-->
        <input type="submit" value="Random Geometric Shape">
    </form>
</div>
<hr>
<#-- checks if allDieResults which is a list, is empty or not -->
<#-- https://freemarker.apache.org/docs/ref_builtins_expert.html#ref_builtin_has_content -->
<#if allGeometricShapeResults?has_content>
    <table style="margin-left: auto;margin-right: auto">
        <tr>
            <th>ID</th>
            <th>Result</th>
            <th>Date</th>
        </tr>
        <#list allGeometricShapeResults as geometricShapeResult>
            <tr>
                <td>${geometricShapeResult.id}</td>
                <td>${geometricShapeResult.result}</td>
                <td>${geometricShapeResult.throwDate}</td>
            </tr>
        </#list>
    </table>
</#if>

</body>
</html>