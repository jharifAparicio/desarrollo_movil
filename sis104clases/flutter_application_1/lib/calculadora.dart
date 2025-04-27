class Calculadora {
  int sumar(int a, int b) {
    return a + b;
  }

  int restar(int a, int b) {
    return a - b;
  }

  int multiplicar(int a, int b) {
    return a * b;
  }

  double dividir(int a, int b) {
    if (b == 0) {
      throw Exception('Error: No se permite dividir entre 0.');
    }
    return a / b;
  }
}
