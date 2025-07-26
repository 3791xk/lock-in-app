import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Lock-In App',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blueGrey),
        useMaterial3: true,
      ),
      home: const HomePage(title: 'Lock-In Home Page'),
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({super.key, required this.title});
  final String title;

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  bool _isBlocking = false;
  final platform = const MethodChannel('com.yourapp.blocker');

  void _toggleBlocking(bool enabled) async {
    setState(() => _isBlocking = enabled);
    try {
      await platform.invokeMethod(enabled ? 'startBlock' : 'stopBlock');
    } catch (e) {
      // ignore: avoid_print
      print("Failed to toggle: $e");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.primaryContainer,
        title: Text(widget.title),
      ),
      body: Center(
        child: SwitchListTile(
              title: const Text("Block Instagram scrolling"),
              value: _isBlocking,
              onChanged: _toggleBlocking,
            )
      ),
    );
  }
}