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
    - Timestamp
   - Are updated by visiting the corresponding pages
 -->

<#-- Dice Tracker -->
<#if allDiceLogs?has_content>
    <table style="margin-left: auto;margin-right: auto">
        <caption>Dice Tracker Logs</caption>
        <tr>
            <th>ID</th>
            <th>Date</th>
        </tr>
        <#list allDiceLogs as diceLog>
            <tr>
                <td>${diceLog.id}</td>
                <td>${diceLog.visitDate}</td>
            </tr>
        </#list>
    </table>

<#else>
    <caption>There is no activity in Dice Tracker!</caption>

</#if>

<hr>
<hr>

<#-- Animal Tracker -->

<#if allAnimalLogs?has_content>
    <table style="margin-left: auto;margin-right: auto">
        <caption>Animal Tracker Logs</caption>
        <tr>
            <th>ID</th>
            <th>Date</th>
        </tr>

        <#list allAnimalLogs as animalLog>
            <tr>

                <td>${animalLog.id}</td>
                <td>${animalLog.visitDate}</td>
            </tr>
        </#list>
    </table>

<#else>
    <caption>There is no activity in Animal Tracker!</caption>

</#if>

</body>
</html>