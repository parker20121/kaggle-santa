package nparker.gapi.structure;

import java.util.*;

/**
 * Marker interface used by populations for genetic algorithms to indicate that
 * they are such structures. This also forces classes that implement this
 * interface to implement the methods in the <code>Collection</code> interface
 * and the <code>List</code> interface. By also forcing these methods to be
 * implemented, we can, in turn, use the <code>Collections</code> class for
 * its algorithms in manipulating our populations.
 *
 * @author Nathan Parker
 * @version 1.0
 */

public interface Population extends List
{
}