import java.rmi.server.RemoteObjectInvocationHandler;
import java.util.List;
import java.util.function.ObjIntConsumer;
/**
 * Simple hash table implementation using linear probing.
 *
 * Luke Reinbach
 * 10/05/2023
 */
public class LPHashTable extends HashTable {

    /**
     * Create an LPHashTable with DEFAULT_SIZE table.
     */
    public LPHashTable() { super(); }

    /**
     * Create an LPHashTable with the given default size table.
     */
    public LPHashTable(final int size) { super(size); }

    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     *
     */
    protected int findIndex(String key) {
		// Implement using linear probing.
      int value = hashFunction(key);
      int h = value;
      boolean found = false;
      boolean openSpotFound = false;

      while (LPHashTable.super.table[h] != null) {
          if (LPHashTable.super.table[h].equals(key)) {
            found = true;
              break;
          }
          h = (h + 1) % LPHashTable.super.tableSize();
          incProbeCount();
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
      while (LPHashTable.super.table[h] != null) {
          h = (h + 1) % LPHashTable.super.tableSize();
          incProbeCount();
          if (h == value) {
              // table is full
              break;
          }
      }

      if (LPHashTable.super.table[h] == null) {
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







     
     
      /*  for (int i = 0; i < LPHashTable.super.tableSize(); i++) {
        if ((LPHashTable.super.table[i] != null) && (LPHashTable.super.table[i].equals(key))) {
          incProbeCount();
          return hash;
        }
      }

      for (int i = 0; i < LPHashTable.super.tableSize(); i++) {
       if (LPHashTable.super.table[i] == null) {
          incProbeCount();
          return (hash + i) % LPHashTable.super.tableSize();
        }
      } 

      incProbeCount();
      return -1;
    }*/
