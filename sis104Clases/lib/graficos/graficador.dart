import 'package:flutter/material.dart';

void main() {
  runApp(const DibujoApp());
}

class DibujoApp extends StatelessWidget {
  const DibujoApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Dibujo de Línea y Círculo')),
      body: Center(
        child: CustomPaint(size: Size(300, 300), painter: FiguraPainter()),
      ),
    );
  }
}

class FiguraPainter extends CustomPainter {
  @override
  void paint(Canvas canvas, Size size) {
    final paintLinea = Paint()
      ..color = Colors.blue
      ..strokeWidth = 4;

    final paintCirculo = Paint()
      ..color = Colors.red
      ..style = PaintingStyle.stroke
      ..strokeWidth = 3;

    // Dibuja una línea diagonal de esquina a esquina
    canvas.drawLine(Offset(0, 0), Offset(size.width, size.height), paintLinea);

    // Dibuja un círculo en el centro
    final centro = Offset(size.width / 2, size.height / 2);
    canvas.drawCircle(centro, 50, paintCirculo);
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) => false;
}
