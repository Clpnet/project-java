<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Factura</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            color: #333;
        }
        .header {
            text-align: center;
            margin-bottom: 20px;
        }
        .header h1 {
            font-size: 36px;
            color: #003366;
        }
        .info {
            margin-bottom: 20px;
        }
        .info div {
            margin: 5px 0;
        }
        .info .label {
            font-weight: bold;
        }
        .details {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .details th, .details td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: left;
        }
        .details th {
            background-color: #f2f2f2;
        }
        .total {
            text-align: right;
            font-weight: bold;
            margin-top: 20px;
        }
        .signature {
            text-align: right;
            margin-top: 50px;
        }
        .signature img {
            width: 150px;
            height: auto;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1>FACTURA</h1>
        <div>Cristhian Lopez</div>
    </div>
    <div class="info">
        <div>
            <span class="label">FACTURAR A:</span>
            <span>Banco ${name} ${lastname}</span>
        </div>
        <div>
            <span class="label">N° DE FACTURA:</span>
            <span>100</span>
        </div>
        <div>
            <span class="label">FECHA:</span>
            <span>${paymentDate}</span>
        </div>
    </div>
    <table class="details">
        <thead>
            <tr>
                <th>DESCRIPCIÓN</th>
                <th>IMPORTE</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>${debt}</td>
            </tr>
            <tr>
                
            </tr>
        </tbody>
    </table>
    <div class="total">
        TOTAL: S/. ${debt}
    
    </div>
</body>
</html>
