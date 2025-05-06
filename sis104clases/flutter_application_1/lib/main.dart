import 'package:flutter/material.dart';
import 'package:flutter_application/hola_mundo.dart';
import 'calculadora_view.dart';

void main() {
  runApp(const Principal());
}

class Principal extends StatelessWidget {
  const Principal({super.key});
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Principal',
      home: MyHomePage(),
      routes: <String, WidgetBuilder>{
        '/calculadora': (BuildContext context) => const Operaciones(),
        '/hola': (BuildContext context) => const HolaView(),
      },
    );
  }
}

class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('botones de navegacion'),
          backgroundColor: Colors.blue,
        ),
        body: Center(
            child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
                child: const Text('Calculadora de nuemros complejos'),
                onPressed: () =>
                    Navigator.of(context).pushNamed('/calculadora')),
            // añade otro botón aquí
            ElevatedButton(
              onPressed: () => Navigator.of(context).pushNamed('/hola'),
              child: const Text('Ver Hola Mundo'),
            ),
          ],
        )));
  }
}
