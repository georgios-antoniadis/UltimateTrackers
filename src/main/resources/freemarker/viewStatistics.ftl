<#--Giorgos Antoniadis-->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Statistics Report</title>
</head>
<body>
<a href="/">Index</a>
|
<b><a href="/viewStatistics">Statistics Report for Ultimate Trackers</a></b>
<hr>
<h2>Statistics report about trackers.</h2>
<hr>

<#-- Algorithm description:
   - Checks is the tables are empty or not (individually)
   - Depicts data in a table. Each table has a caption indicating
   which tracker it represents
   - If the tables are empty it returns a corresponding message

   Tables:
   - Have the same three columns
    - ID
    - Die result
    - Timestamp
   - Are updated by playing the random game in their corresponding pages
 -->

<#-- Dice Tracker -->
<#if allDieResults?has_content>
    <table style="margin-left: auto;margin-right: auto">
        <caption>Dice Tracker</caption>
        <tr>
            <th>ID</th>
            <th>Result</th>
            <th>Date</th>
        </tr>
        <#list allDieResults as dieResult>
            <tr>
                <td>${dieResult.id}</td>
                <td>${dieResult.result}</td>
                <td>${dieResult.throwDate}</td>
            </tr>
        </#list>
    </table>

<#else>
    <caption>There is no data in Dice Tracker!</caption>

</#if>

<hr>
<hr>

<#-- Animal Tracker -->

<#if allAnimalResults?has_content>
    <table style="margin-left: auto;margin-right: auto">
        <caption>Animal Tracker</caption>
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

<#else>
    <caption>There is no data in Animal Tracker!</caption>

</#if>

</body>
</html>