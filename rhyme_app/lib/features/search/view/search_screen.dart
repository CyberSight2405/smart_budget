
import 'package:auto_route/auto_route.dart';
import 'package:flutter/material.dart';

import '../../../ui/ui.dart';
import '../widgets/widgets.dart';

@RoutePage()
class SearchScreen extends StatelessWidget {
  const SearchScreen({
    super.key,
  });


  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return CustomScrollView(
      slivers: [
        SliverAppBar(
          title: const Text("Rhymer"),
          centerTitle: true,
          floating: true,
          pinned: true,
          snap: true,
          backgroundColor: theme.cardColor,
          surfaceTintColor: Colors.transparent,
          bottom: const PreferredSize(
            preferredSize: Size.fromHeight(90),
            child: SearchButton(),
          ),
        ),
        const SliverToBoxAdapter(
          child: SizedBox(
            height: 10,
          ),
        ),
        SliverToBoxAdapter(
          child: SizedBox(
            height: 100,
            child: ListView.separated(
                padding: const EdgeInsets.symmetric(horizontal: 15),
                scrollDirection: Axis.horizontal,
                itemCount: 10,
                separatorBuilder: (BuildContext context, int index) =>
                const SizedBox(
                  width: 16,
                ),
                itemBuilder: (context, index) {
                  final rhymes = List.generate(4, (index) => 'Рифма $index');

                  return RhymesHistoryCard(rhymes: rhymes);
                }),
          ),
        ),
        const SliverToBoxAdapter(
          child: SizedBox(
            height: 10,
          ),
        ),
        SliverList.builder(
          itemBuilder: (context, index) => const RhymeListCard(),
        ),
      ],
    );
  }
}




