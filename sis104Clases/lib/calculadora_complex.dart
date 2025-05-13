class CalculadoraComplex {
  String suma(int a, int b, int c, int d) {
    // (a + bi) + (c + di) => (a + c) + (b + d)i
    int real = a + c;
    int imaginario = b + d;
    return '($a+$c)+($b+$d)i => $real + ${imaginario}i';
  }

  String resta(int a, int b, int c, int d) {
    // resta (a + bi)-(c + di) => (a - c) + (b - d)i
    int real = a - c;
    int imaginario = b - d;
    return '($a-$c)+($b-$d)i => $real + ${imaginario}i';
  }

  String multiplicacion(int a, int b, int c, int d) {
    // multiplicacion (a + bi)(c + di) => (ac - bd) + (ad + bc)i
    int real = (a * c) - (b * d);
    int imaginario = (a * d) + (b * c);
    return '($a*$c-($b*$d))+($a*$d+($b*$c))i => $real + ${imaginario}i';
  }

  String division(int a, int b, int c, int d) {
    // division (a + bi)/(c + di) => ((ac + bd)/(c^2 + d^2)) + ((bc - ad)/(c^2 + d^2))i
    int real = ((a * c) + (b * d)) ~/ (c * c + d * d);
    int imaginario = ((b * c) - (a * d)) ~/ (c * c + d * d);
    return '($a+$b i) / ($c+$d i) => $real + ${imaginario}i';
  }
}

// suma (a + bi)+(c + di) => (a + c) + (b + d)i
// resta (a + bi)-(c + di) => (a - c) + (b - d)i
// multiplicacion (a + bi)(c + di) => (ac - bd) + (ad + bc)i
// division (a + bi)/(c + di) => ((ac + bd)/(c^2 + d^2)) + ((bc - ad)/(c^2 + d^2))i
