/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.properties.builders;

import net.sourceforge.pmd.properties.MultiValuePropertyDescriptor;


/**
 * For multi-value numeric properties.
 *
 * @param <V> Element type of the list
 * @param <T> Concrete type of the underlying builder
 */
public abstract class MultiNumericPropertyBuilder<V, T extends MultiNumericPropertyBuilder<V, T>>
    extends MultiValuePropertyBuilder<V, T> {


    protected V lowerLimit;
    protected V upperLimit;


    protected MultiNumericPropertyBuilder(String name) {
        super(name);
        multiValueDelimiter = MultiValuePropertyDescriptor.DEFAULT_NUMERIC_DELIMITER;
    }


    /**
     * Specify the range of acceptable values.
     *
     * @param min Lower bound
     * @param max Upper bound
     *
     * @return The same builder
     */
    @SuppressWarnings("unchecked")
    public T range(V min, V max) {
        this.lowerLimit = min;
        this.upperLimit = max;
        return (T) this;
    }

}
