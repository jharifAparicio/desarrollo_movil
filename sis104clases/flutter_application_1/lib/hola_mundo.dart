import 'package:flutter/material.dart';

class HolaView extends StatelessWidget {
  const HolaView({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Hola Mundo')),
      body: const Center(
        child: Text(
          '¡Hola Mundo!',
          style: TextStyle(fontSize: 24),
        ),
      ),
    );
  }
}