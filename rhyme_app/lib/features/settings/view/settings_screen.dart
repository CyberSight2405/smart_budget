import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';
import 'package:rhyme_app/ui/ui.dart';

@RoutePage()
class SettingsScreen extends StatelessWidget {
  const SettingsScreen({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Scaffold(
      body: CustomScrollView(
        slivers: [
          SliverAppBar(
            title: Text(
              "История",
              style: theme.textTheme.bodyLarge
                  ?.copyWith(fontWeight: FontWeight.w600, fontSize: 30),
            ),
            centerTitle: true,
            floating: true,
            pinned: true,
            snap: true,
            elevation: 0,
            backgroundColor: theme.cardColor,
            surfaceTintColor: Colors.transparent,
          ),
          const SliverToBoxAdapter(
            child: SizedBox(
              height: 16,
            ),
          ),
          SliverToBoxAdapter(
            child: Padding(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              child: ContainerTheme(
                child: Text('Темная Тема', style: theme.textTheme.bodyLarge,),
                padding: EdgeInsets.all(16),
                width: double.infinity,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
