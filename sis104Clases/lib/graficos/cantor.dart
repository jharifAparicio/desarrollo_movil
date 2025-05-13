import 'package:flutter/material.dart';

void main() => runApp(MaterialApp(home: CantorSetDrawer()));

class CantorSetDrawer extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Conjunto de Cantor")),
      body: CustomPaint(
        painter: CantorPainter(),
        size: Size.infinite,
      ),
    );
  }
}

class CantorPainter extends CustomPainter {
  final int niveles = 5; // Número de niveles de recursión

  @override
  void paint(Canvas canvas, Size size) {
    final paint = Paint()
      ..color = Colors.black
      ..strokeWidth = 2;

    double startX = 20;
    double endX = size.width - 20;
    double startY = 40;
    double spacing = 30;

    dibujarCantor(canvas, paint, startX, endX, startY, spacing, niveles);
  }

  void dibujarCantor(Canvas canvas, Paint paint, double x1, double x2, double y,
      double spacing, int nivel) {
    if (nivel == 0) return;

    canvas.drawLine(Offset(x1, y), Offset(x2, y), paint);

    double ter = (x2 - x1) / 3;
    double nextY = y + spacing;

    // Llamadas recursivas en el primer y tercer tercio
    dibujarCantor(canvas, paint, x1, x1 + ter, nextY, spacing, nivel - 1);
    dibujarCantor(canvas, paint, x2 - ter, x2, nextY, spacing, nivel - 1);
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) => false;
}
