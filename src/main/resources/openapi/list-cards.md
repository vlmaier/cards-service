### Returns all available Marvel SNAP cards.

---

If no cards are available, an empty list is returned.

#### Available query parameters

- `page` - If not set, the first (`0`) page is returned.
- `size` - If not set, maximum `5` first cards are returned.
- `sort` - If not set, the cards are sorted by name in ascending order.  
  Example: `...&sort=power%2Cdesc&sort=name=%2Casc` - returns all cards (for the current page) sorted by their power: strongest first. If there are multiple cards of the same strength, alphabetical order wins.
