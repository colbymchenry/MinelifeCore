package com.minelife.core.asm_transformer;

import java.util.ArrayList;

import java.util.Collection;

import java.util.Iterator;



/**

 * <p>

 * A tuple of eight elements.

 * </p>

 *

 * @author Daniel Fern&aacute;ndez

 * @since 1.0

 */

public final class Octet<A, B, C, D, E, F, G, H>

        extends Tuple

        implements IValue0<A>, IValue1<B>, IValue2<C>, IValue3<D>, IValue4<E>, IValue5<F>, IValue6<G>, IValue7<H>

{



    private static final long serialVersionUID = -1187955276020306879L;

    private static final int SIZE = 8;



    private final A val0;

    private final B val1;

    private final C val2;

    private final D val3;

    private final E val4;

    private final F val5;

    private final G val6;

    private final H val7;



    public static <A, B, C, D, E, F, G, H> Octet<A, B, C, D, E, F, G, H> with(final A value0, final B value1, final C value2, final D value3, final E value4,

                                                                              final F value5, final G value6, final H value7) {

        return new Octet<>(value0, value1, value2, value3, value4, value5, value6, value7);

    }



    /**

     * <p>

     * Create tuple from array. Array has to have exactly eight elements.

     * </p>

     *

     * @param <X>   the array component type

     * @param array the array to be converted to a tuple

     * @return the tuple

     */

    public static <X> Octet<X, X, X, X, X, X, X, X> fromArray(final X[] array) {

        if( array == null ) {

            throw new IllegalArgumentException("Array cannot be null");

        }



        if( array.length != 8 ) {

            throw new IllegalArgumentException("Array must have exactly 8 elements in order to create an Octet. Size is " + array.length);

        }



        return new Octet<>(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);

    }



    /**

     * <p>

     * Create tuple from collection. Collection has to have exactly eight elements.

     * </p>

     *

     * @param <X>        the collection component type

     * @param collection the collection to be converted to a tuple

     * @return the tuple

     */

    public static <X> Octet<X, X, X, X, X, X, X, X> fromCollection(final Collection<X> collection) {

        return fromIterable(collection);

    }



    /**

     * <p>

     * Create tuple from iterable. Iterable has to have exactly eight elements.

     * </p>

     *

     * @param <X>      the iterable component type

     * @param iterable the iterable to be converted to a tuple

     * @return the tuple

     */

    public static <X> Octet<X, X, X, X, X, X, X, X> fromIterable(final Iterable<X> iterable) {

        return fromIterable(iterable, 0, true);

    }



    /**

     * <p>

     * Create tuple from iterable, starting from the specified index. Iterable

     * can have more (or less) elements than the tuple to be created.

     * </p>

     *

     * @param <X>      the iterable component type

     * @param iterable the iterable to be converted to a tuple

     * @return the tuple

     */

    public static <X> Octet<X, X, X, X, X, X, X, X> fromIterable(final Iterable<X> iterable, int index) {

        return fromIterable(iterable, index, false);

    }



    private static <X> Octet<X, X, X, X, X, X, X, X> fromIterable(final Iterable<X> iterable, int index, final boolean checkSize) {

        if( iterable == null ) {

            throw new IllegalArgumentException("Iterable cannot be null");

        }



        X element;

        ArrayList<X> elements = new ArrayList<>(8);

        final Iterator<X> iter = iterable.iterator();

        int lastIndex = index + SIZE - 1;



        for( int i = 0; i <= lastIndex; i++ ) {

            if( iter.hasNext() ) {

                element = iter.next();

                if( i >= index ) {

                    if( checkSize && i == lastIndex && iter.hasNext() ) {

                        throw new IllegalArgumentException("Iterable must have exactly 8 elements in order to create a Octet.");

                    }



                    elements.add(element);

                }

            } else {

                if( i < index ) {

                    throw new IllegalArgumentException(String.format("Iterable has not enough elements to grab a value from index %d", index));

                } else {

                    throw new IllegalArgumentException(String.format("Not enough elements for creating a Octet (8 needed, %d given)", i));

                }

            }

        }



        return new Octet<>(elements.get(0), elements.get(1), elements.get(2), elements.get(3), elements.get(4), elements.get(5), elements.get(6), elements.get(7));

    }



    public Octet(final A value0, final B value1, final C value2, final D value3, final E value4, final F value5, final G value6, final H value7) {

        super(value0, value1, value2, value3, value4, value5, value6, value7);

        this.val0 = value0;

        this.val1 = value1;

        this.val2 = value2;

        this.val3 = value3;

        this.val4 = value4;

        this.val5 = value5;

        this.val6 = value6;

        this.val7 = value7;

    }



    @Override

    public A getValue0() {

        return this.val0;

    }



    @Override

    public B getValue1() {

        return this.val1;

    }



    @Override

    public C getValue2() {

        return this.val2;

    }



    @Override

    public D getValue3() {

        return this.val3;

    }



    @Override

    public E getValue4() {

        return this.val4;

    }



    @Override

    public F getValue5() {

        return this.val5;

    }



    @Override

    public G getValue6() {

        return this.val6;

    }



    @Override

    public H getValue7() {

        return this.val7;

    }



    @Override

    public int getSize() {

        return SIZE;

    }

}