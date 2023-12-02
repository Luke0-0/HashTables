/**
 * Simple hash table implementation using quadratic probing.
 *
 * Luke Reinbach
 * 10/05/2023
 */
public class QPHashTable  extends HashTable {


    /**
     * Create an QPHashTable with DEFAULT_SIZE table.
     */
    public QPHashTable() { super(); }

    /**
     * Create an QPHashTable with the given default size table.
     */
    public QPHashTable(final int size) { super(size); }

    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     *
     */
    protected int findIndex(String key) {
		// Implement using quadratic probing.
      int value = hashFunction(key);
      int h = value;
      int qp = 1;
      boolean found = false;
      boolean openSpotFound = false;

      while (QPHashTable.super.table[h] != null) {
          if (QPHashTable.super.table[h].equals(key)) {
            found = true;
              break;
          }
          h = (h + qp*qp) % QPHashTable.super.tableSize();
          incProbeCount();
          qp++;
          if (h == value) {
              // table is full
              break;
          }
      }

      if (found) {
        incProbeCount();
          return h;
      }

      h = value;
      while (QPHashTable.super.table[h] != null) {
          h = (h + qp*qp) % QPHashTable.super.tableSize();
          incProbeCount();
          qp++;
          if (h == value) {
              // table is full
              break;
          }
      }

      if (QPHashTable.super.table[h] == null) {
        incProbeCount();
        openSpotFound = true;
      }

      if (openSpotFound) {
          return h;
      } else {
          return -1;
      }
  }
}
