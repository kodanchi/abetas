/**
 * Created by Mojahed on 3/2/2016.
 */
google.charts.load("current", {packages:['corechart']});
google.charts.setOnLoadCallback(drawChart);
function drawChart(sNum) {

var view = new google.visualization.DataView(data);
view.setColumns([0, 1,
{ calc: "stringify",
    sourceColumn: 1,
    type: "string",
    role: "annotation" },
2]);


    /*var formatter = new google.visualization.NumberFormat({pattern: '##%'});
     // format column 1 of the DataTable
     formatter.format(data, 1);*/

var formatter = new google.visualization.NumberFormat(
{suffix: '%', negativeColor: 'red', negativeParens: true, pattern:'#.#'});
formatter.format(data, 1); // Apply formatter to second column

var options = {
    title: "Percentage of students in each rubrics for this PI,total No. of students : "+sNum,
    width: "100%",
    height: 400,
    bar: {groupWidth: "95%"},
    legend: { position: "right" },
    vAxis: {
    minValue: 0,
    maxValue: 100,
    format: '#\'%\''
},
    animation: {
    duration: 1000,
    easing: 'out'
}
};
var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
                        chart.draw(view, options);

                    }