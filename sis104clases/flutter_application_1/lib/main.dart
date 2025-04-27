import 'package:flutter/material.dart';
//import 'calculadora.dart';
import 'calculadora_complex.dart';

void main() {
  runApp(const Principal());
}

class Principal extends StatelessWidget {
  const Principal({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(home: Operaciones());
  }
}

class Operaciones extends StatefulWidget {
  const Operaciones({super.key});

  @override
  _OperacionesState createState() => _OperacionesState();
}

class _OperacionesState extends State<Operaciones> {
  final TextEditingController _caja1 = TextEditingController();
  final TextEditingController _caja2 = TextEditingController();
  final TextEditingController _caja3 = TextEditingController();
  final TextEditingController _caja4 = TextEditingController();

  String _resultadoSuma = "";
  String _resultadoResta = "";
  String _resultadoMultiplicacion = "";
  String _resultadoDivision = "";
  String _mensajeError = '';

  void _calcular() {
    final int numero1 = int.tryParse(_caja1.text) ?? 0;
    final int numero2 = int.tryParse(_caja2.text) ?? 0;
    final int numero3 = int.tryParse(_caja3.text) ?? 0;
    final int numero4 = int.tryParse(_caja4.text) ?? 0;
    final calculadora = CalculadoraComplex();

    setState(() {
      _resultadoSuma = calculadora.suma(numero1, numero3, numero2, numero4);
      _resultadoResta = calculadora.resta(numero1, numero3, numero2, numero4);
      _resultadoMultiplicacion =
          calculadora.multiplicacion(numero1, numero3, numero2, numero4);
      _resultadoDivision = "";

      try {
        _resultadoDivision =
            calculadora.division(numero1, numero3, numero2, numero4);
        _mensajeError = ''; // No hay error en la división
      } catch (e) {
        _resultadoDivision = "";
        _mensajeError = e.toString(); // Aquí se captura el error de división
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Calculadora copleja')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: <Widget>[
            TextField(
              controller: _caja1,
              decoration: const InputDecoration(labelText: 'Número 1'),
              keyboardType: TextInputType.number,
            ),
            TextField(
              controller: _caja2,
              decoration:
                  const InputDecoration(labelText: 'Número 2 imaginario'),
              keyboardType: TextInputType.number,
            ),
            TextField(
              controller: _caja3,
              decoration: const InputDecoration(labelText: 'Número 3'),
              keyboardType: TextInputType.number,
            ),
            TextField(
              controller: _caja4,
              decoration:
                  const InputDecoration(labelText: 'Número 4 imaginario'),
              keyboardType: TextInputType.number,
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: _calcular,
              child: const Text('Calcular Complejos'),
            ),
            const SizedBox(height: 20),
            Text('Suma Compleja: $_resultadoSuma'),
            Text('Resta Compleja: $_resultadoResta'),
            Text('Multiplicación Compleja: $_resultadoMultiplicacion'),
            Text('División Compleja: $_resultadoDivision'),
            if (_mensajeError.isNotEmpty)
              Padding(
                padding: const EdgeInsets.only(top: 10),
                child: Text(
                  _mensajeError,
                  style: const TextStyle(color: Colors.red),
                ),
              ),
          ],
        ),
      ),
    );
  }
}
