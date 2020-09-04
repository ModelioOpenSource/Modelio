/* 
 * Copyright 2013-2018 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.vcore.smkernel;

import java.util.Arrays;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.meta.SmDependency;

/**
 * SmList whose content type is constrained by an array of classes.
 * @param <T> the base type of the list
 */
@objid ("005dca14-e963-1f86-ba49-001ec947cd2a")
public class SmConstrainedList<T> extends SmList<T> {
    @objid ("779cf9e8-9b64-11e1-94a3-001ec947ccaf")
    private Class<?>[] filter = { SmObjectImpl.class };

    /**
     * Constructor.
     * @param owner the list owner
     * @param dep the navigated model dependency
     * @param filter the filter
     */
    @objid ("0003d572-edd6-1f86-ba49-001ec947cd2a")
    public SmConstrainedList(final SmObjectImpl owner, final SmDependency dep, final Class<?>[] filter) {
        super(owner, dep);
        
        this.filter = filter;
    }

    @objid ("00043512-edd6-1f86-ba49-001ec947cd2a")
    @Override
    public boolean add(final T value) {
        for (Class<?> clazz : this.filter) {
            if (clazz.isInstance(value)) {
                return super.add(value);
            }
        }
        throw new IllegalArgumentException(value+" is not a " + Arrays.toString(this.filter));
    }

    @objid ("000466cc-edd6-1f86-ba49-001ec947cd2a")
    @Override
    public void add(final int index, final T value) {
        for (Class<?> clazz : this.filter) {
            if (clazz.isInstance(value)) {
                super.add(index, value);
                return;
            }
        }
        throw new IllegalArgumentException(value + " is not a " + Arrays.toString(this.filter));
    }

    @objid ("0004a5f6-edd6-1f86-ba49-001ec947cd2a")
    @Override
    public T set(final int index, final T value) {
        for (Class<?> clazz : this.filter) {
            if (clazz.isInstance(value)) {
                return super.set(index,  value);
            }
        }
        throw new IllegalArgumentException(value + " is not a " + Arrays.toString(this.filter));
    }

}
