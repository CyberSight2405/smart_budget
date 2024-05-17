import 'package:flutter/material.dart';

class SearchButton extends StatelessWidget {
  const SearchButton({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Container(
      margin: const EdgeInsets.all(12),
      padding: const EdgeInsets.symmetric(vertical: 2),
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(16),
          color: theme.hintColor.withOpacity(0.1)),
      child: Row(
        children: <Widget>[
          IconButton(
            onPressed: () {},
            icon: Icon(
              Icons.search_rounded,
              color: theme.hintColor,
            ),
          ),
          const SizedBox(
            width: 10,
          ),
          Text(
            'Введите слово',
            style: TextStyle(
                fontSize: 18,
                fontWeight: FontWeight.w400,
                color: theme.hintColor),
          )
        ],
      ),
    );
  }
}