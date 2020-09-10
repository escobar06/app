<%@page import="dominio.Pivot"%>
<%@page import="java.util.List"%>
<%@page import="datos.ConexionDaoImpl"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS - contenido responsivo-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <!-- Font Awesome -->
        <script src="https://kit.fontawesome.com/c4d206fcd7.js" crossorigin="anonymous"></script>
        <script src="https://cdn.plot.ly/plotly-latest.min.js" crossorigin="anonymous"></script>
        <title>Gráfica 2</title>
    </head>
    <body>
        <%-- Pagina cabeceros --%>
        <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"/>
        <%-- Botones de navegacion --%>
        <jsp:include page="/WEB-INF/paginas/comunes/botonesgrafica.jsp"/>

        <%!
            ConexionDaoImpl cnn = new ConexionDaoImpl();
            int n0 = cnn.listarGrafica2_0();
            int n1 = cnn.listarGrafica2_1();
            int n2 = cnn.listarGrafica2_2();
            int n3 = cnn.listarGrafica2_3();
            int n4 = cnn.listarGrafica2_4();
            int n5 = cnn.listarGrafica2_5();
        %>
        <div class="div_centrado">
            <div id="barChart"></div>
            <script>
                var a = <%= n0%>;
                var b = <%= n1%>;
                var c = <%= n2%>;
                var d = <%= n3%>;
                var e = <%= n4%>;
                var f = <%= n5%>;
                var data = [{
                        x: ['Nivel 0', 'Nivel 1', 'Nivel 2', 'Nivel 3', 'Nivel 4', 'Nivel 5'],
                        y: [a, b, c, d, e, f],
                        type: 'bar'
                    }];

                Plotly.newPlot('barChart', data);
            </script>        
        </div>
        <style>
            html,body{ margin: 0;}
            .div_contenedor{
                background: crimson;
                height: 100vh;       
            }
            .div_centrado{
                background: yellow;
                width: 200px;       
                height: 200px;
                position: absolute;
                top:35%;
                left: 50%;           
                margin-top: -100px;
                margin-left: -685px;
            }
        </style>                    
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>
