import 'dart:math';

import 'package:flutter/material.dart';

void main() {
  runApp(CartecianoApp());
}

class CartecianoApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Graficador de Funciones')),
      body: Center(
        child: CustomPaint(
          painter: PlanoCartesianoPainter(),
          size: Size(400, 400),
        ),
      ),
    );
  }
}

class PlanoCartesianoPainter extends CustomPainter {
  @override
  void paint(Canvas canvas, Size size) {
    final Paint ejePaint = Paint()
      ..color = Colors.grey
      ..strokeWidth = 1;

    final double centroX = size.width / 2;
    final double centroY = size.height / 2;

    // Dibujar ejes X y Y
    canvas.drawLine(Offset(0, centroY), Offset(size.width, centroY), ejePaint);
    canvas.drawLine(Offset(centroX, 0), Offset(centroX, size.height), ejePaint);

    // Pintar la función y = sin(x)
    final Paint functionPaint = Paint()
      ..color = Colors.blue
      ..strokeWidth = 2;

    Path path = Path();
    double escala = 20; // Pixels por unidad

    for (double x = -centroX; x < centroX; x += 1) {
      double xReal = x / escala;
      double yReal = sin(xReal); // <-- Cambia aquí tu función
      double xCanvas = centroX + x;
      double yCanvas = centroY - yReal * escala;

      if (x == -centroX) {
        path.moveTo(xCanvas, yCanvas);
      } else {
        path.lineTo(xCanvas, yCanvas);
      }
    }

    canvas.drawPath(path, functionPaint);
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) => true;
}
