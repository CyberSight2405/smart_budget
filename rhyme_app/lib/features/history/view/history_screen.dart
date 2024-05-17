import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';

import '../../search/widgets/widgets.dart';

@RoutePage()
class HistoryScreen extends StatelessWidget {
  const HistoryScreen({
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
          SliverPadding(
            padding: const EdgeInsets.symmetric(horizontal: 16),
            sliver: SliverGrid(
              gridDelegate: const SliverGridDelegateWithMaxCrossAxisExtent(
                maxCrossAxisExtent: 200.0,
                mainAxisSpacing: 10.0,
                crossAxisSpacing: 10.0,
                childAspectRatio: 1.6,
              ),
              delegate: SliverChildBuilderDelegate(
                (BuildContext context, int index) {
                  return const RhymesHistoryCard(
                    rhymes: [
                      'Rhyme',
                      'Хуй',
                      'Пизда',
                      'Звезда',
                    ],
                  );
                },
                childCount: 20,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
