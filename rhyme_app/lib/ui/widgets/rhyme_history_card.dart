import 'package:flutter/material.dart';

import '../ui.dart';

class RhymesHistoryCard extends StatelessWidget {
  const RhymesHistoryCard({
    super.key,
    required this.rhymes,
  });

  final List<String> rhymes;

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return ContainerTheme(
      width: 200,
      padding: const EdgeInsets.all(16),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Text(
            'Слово',
            style: theme.textTheme.bodyLarge
                ?.copyWith(fontWeight: FontWeight.w700),
          ),
          Flexible(
            child: Text(rhymes.map((e) => '$e, ').join(),
            overflow: TextOverflow.ellipsis,
            maxLines: 3,),
          ),

         /* Wrap(
              children: rhymes
                  .map(
                    (element) =>
                    Padding(
                      padding: const EdgeInsets.only(right: 4),
                      child: Text(element),
                    ),
              )
                  .toList()),*/
        ],
      ),
    );
  }
}