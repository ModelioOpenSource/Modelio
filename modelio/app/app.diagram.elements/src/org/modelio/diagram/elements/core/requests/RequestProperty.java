/* 
 * Copyright 2013-2020 Modeliosoft
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
package org.modelio.diagram.elements.core.requests;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.Request;

/**
 * Typed request extended data.
 * 
 * @param <T> the property type
 * @since 5.1.0
 * @author cma
 */
@objid ("69d074be-a885-4d96-b876-84561f702ca7")
public class RequestProperty<T> {
    /**
     * Request property telling group layout policies the index of the item to move, create or delete .
     * <p>
     * The edit policy and the caller may then skip horrible index-&gt;location-&gt;index computations.
     */
    @objid ("b386fc02-8da2-49b1-a675-3a3a1883d355")
    public static final RequestProperty<Integer> PROP_GROUP_ITEM_INDEX = new RequestProperty<>("GmGroup item index");

    /**
     * Request property telling the reconnect commands to change the Gm model only and not the Ob model.
     */
    @objid ("7212865d-d2aa-4fe9-9f0f-f91f74c5f4b5")
    public static final RequestProperty<Boolean> PROP_SKIP_MODELCHANGE = new RequestProperty<>("Skip model change");

    @objid ("6958a5dc-d162-4daa-91b2-749c9668d990")
    private final Object key;

    @objid ("a9c9fb84-8b31-4314-906f-c993d57d7483")
    private  RequestProperty(Object key) {
        this.key = key;
    }

    /**
     * @param req the request to read
     * @return the property value;
     */
    @objid ("f885ad55-9371-4832-8bc2-ca3d3f314263")
    @SuppressWarnings ("unchecked")
    public T get(Request req) {
        return (T) req.getExtendedData().get(this.key);
    }

    /**
     * @param req the request to modify
     * @param val the property value.
     */
    @objid ("4fba970d-4a6b-4446-a3e5-03b03a1ed260")
    public void set(Request req, T val) {
        req.getExtendedData().put(this.key, val);
    }

}
