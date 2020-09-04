<meta http-equiv="Cache-Control" content="no-store" />
<!DOCTYPE html>
<html>
    <head>
        <meta content="charset=UTF-8">
        <title>Graficas</title>
        
        <script src="https://cdn.plot.ly/plotly-latest.min.js" crossorigin="anonymous"></script>

    </head>
    <body>
        <section>
            <h3>Table:</h3>
            <div id="myDiv"></div>
                <script>
var values = [
      ['Salaries', 'Office', 'Merchandise', 'Legal', '<b>TOTAL</b>'],
      [1200000, 20000, 80000, 2000, 12120000],
      [1300000, 20000, 70000, 2000, 130902000],
      [1300000, 20000, 120000, 2000, 131222000],
      [1400000, 20000, 90000, 2000, 14102000]]

var data = [{
  type: 'table',
  header: {
    values: [["<b>EXPENSES</b>"], ["<b>Q1</b>"],
				 ["<b>Q2</b>"], ["<b>Q3</b>"], ["<b>Q4</b>"]],
    align: "center",
    line: {width: 1, color: 'black'},
    fill: {color: "grey"},
    font: {family: "Arial", size: 12, color: "white"}
  },
  cells: {
    values: values,
    align: "center",
    line: {color: "black", width: 1},
    font: {family: "Arial", size: 11, color: ["black"]}
  }
}]

Plotly.newPlot('myDiv', data);
                </script>                

            <h3>Pie:</h3>
            <div id="pieChart"></div>
                <script>
                    var data = [{
                        values: [19, 26, 55],
                        labels: ['Residential', 'Non-Residential', 'Utility'],
                        type: 'pie'
                    }];
                    var layout = {
                        height: 400,
                        width: 500
                    };
            
                    Plotly.newPlot('pieChart', data, layout);
                </script>                
        
            <h3>Scatter:</h3>
            <div id="scatterChart"></div>
                <script>
                    var trace1 = {
                        x: [1, 2, 3, 4],
                        y: [10, 15, 13, 17],
                        type: 'scatter'
                    };

                    var trace2 = {
                        x: [1, 2, 3, 4],
                        y: [16, 5, 11, 9],
                        type: 'scatter'
                    };

                    var data = [trace1, trace2];

                    Plotly.newPlot('scatterChart', data);
                </script>        
        
            <h3>Bar:</h3>
            <div id="barChart"></div>
                <script>
                    var data = [{
                        x: ['java', 'python', 'c#'],
                        y: [20, 12, 6],
                        type: 'bar'
                    }];

                    Plotly.newPlot('barChart', data);            
                </script>        
        </section>
        <a href="${pageContext.request.contextPath}/ServletControlador?accion=listar&lista=control">Regresar al inicio</a>
    </body>
</html>
