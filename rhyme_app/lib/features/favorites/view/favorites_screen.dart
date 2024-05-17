import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';

import '../../../ui/widgets/widgets.dart';

@RoutePage()
class FavoritesScreen extends StatelessWidget {
  const FavoritesScreen({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Scaffold(
      body: CustomScrollView(
        slivers: [
          SliverAppBar(
            title: const Text("Избранное"),
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
          SliverList.builder(
            itemBuilder: (context, index) => const RhymeListCard(isFavorite: true,),
          ),
        ],
      ),
    );
  }
}
