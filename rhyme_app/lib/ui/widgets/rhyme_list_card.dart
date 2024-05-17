import 'package:flutter/material.dart';

import '../themes/themes.dart';


class RhymeListCard extends StatelessWidget {
  const RhymeListCard({super.key, this.isFavorite = false});
  final bool isFavorite;


  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return ContainerTheme(
      margin: const EdgeInsets.symmetric(horizontal: 16).copyWith(bottom: 10),
      width: double.infinity,
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Text(
            'Рифма',
            style: theme.textTheme.bodyLarge?.copyWith(
              fontWeight: FontWeight.w400,
            ),
          ),
          IconButton(
            onPressed: () {},
            icon: const Icon(Icons.favorite),
            color: isFavorite ? theme.primaryColor :theme.hintColor.withOpacity(0.2),
          )
        ],
      ),
    );
  }
}
