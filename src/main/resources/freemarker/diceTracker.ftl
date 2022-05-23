<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dice Tracker</title>
</head>
<body>
<a href="/">Index</a>
|
<b><a href="/diceTracker">Dice Tracker</a></b>
<hr>
<h2>Game of dice...</h2>
<hr>
<div style="text-align: center">
    <#--    if numberOfDice (added from the model) exists, then proceed with printing it, else print a default "static" image -->
    <#--    https://freemarker.apache.org/docs/dgui_template_exp.html#dgui_template_exp_missing_test -->
    <#if numberOfDice??>
        <img src="/images/${numberOfDice}.png">
    <#else>
        <img src="/images/questionMark.png">
    </#if>
    <form action="/throwDice" method="post">
        <#--        when the button is clicked, it requested endpoints "/throwDice"-->
        <input type="submit" value="Throw Dice">
    </form>
</div>
<hr>
<#-- checks if allDieResults which is a list, is empty or not -->
<#-- https://freemarker.apache.org/docs/ref_builtins_expert.html#ref_builtin_has_content -->
<#if allDieResults?has_content>
    <table style="margin-left: auto;margin-right: auto">
        <tr>
            <th>ID</th>
            <th>Result</th>
            <th>Date</th>
        </tr>
<#--        loop that submits an html table row for each element(die) that exists in the allDieResults list -->
<#--        https://freemarker.apache.org/docs/ref_directive_list.html#ref_list_simple -->
        <#list allDieResults as dieResult>
            <tr>
<#--                dieResult is the name given for each element of the allDieResults #list -->
<#--                and in this case,  each element represents the Java Die class, therefore we can simply access each  -->
<#--                field by using its respective Java field name -->
<#--                https://freemarker.apache.org/docs/pgui_datamodel_parent.html#autoid_32 -->
                <td>${dieResult.id}</td>
                <td>${dieResult.result}</td>
                <td>${dieResult.throwDate}</td>
            </tr>
        </#list>
    </table>
</#if>

</body>
</html>