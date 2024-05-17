import 'package:flutter/material.dart';
import 'package:get_it/get_it.dart';
import 'package:rhyme_app/router/router.dart';
import 'package:talker_flutter/talker_flutter.dart';

void main() {
  final talker = TalkerFlutter.init();
  GetIt.I.registerSingleton(talker).info('App started');

  runTalkerZonedGuarded(talker, () {
    FlutterError.onError =
        (details) => GetIt.I<Talker>().handle(details.exception, details.stack);

    runApp(const RjymerApp());
  }, (error, stack) {});
}

class RjymerApp extends StatefulWidget {
  const RjymerApp({super.key});

  @override
  State<RjymerApp> createState() => _RjymerAppState();
}

class _RjymerAppState extends State<RjymerApp> {
  final _router = AppRouter();

  @override
  Widget build(BuildContext context) {
    const primaryColor = Color(0xFFF82B10);

    return MaterialApp.router(
      title: 'Rhymer',
      theme: ThemeData(
          primaryColor: primaryColor,
          colorScheme: ColorScheme.fromSeed(seedColor: primaryColor),
          scaffoldBackgroundColor: const Color(0xFFEFF1F3),
          useMaterial3: true),
      routerConfig: _router.config(),
    );
  }
}


