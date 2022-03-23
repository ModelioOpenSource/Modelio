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
@objid ("a44c1983-05ca-48a7-8757-3ddc697d70bf")
public class RequestProperty<T> {
    /**
     * Request property telling group layout policies the index of the item to move, create or delete .
     * <p>
     * The edit policy and the caller may then skip horrible index-&gt;location-&gt;index computations.
     */
    @objid ("e7e5f67f-bad2-4682-86d6-13163d69a395")
    public static final RequestProperty<Integer> PROP_GROUP_ITEM_INDEX = new RequestProperty<>("GmGroup item index");

    /**
     * Request property telling the reconnect commands to change the Gm model only and not the Ob model.
     */
    @objid ("53c26829-227c-4758-88a7-42296e92f035")
    public static final RequestProperty<Boolean> PROP_SKIP_MODELCHANGE = new RequestProperty<>("Skip model change");

    @objid ("33c55d07-1cab-498e-a272-12a1c89f35dd")
    private final Object key;

    @objid ("5cd4ac27-6985-432d-a1ef-fe4c1a41d0e1")
    private  RequestProperty(Object key) {
        this.key = key;
    }

    /**
     * @param req the request to read
     * @return the property value;
     */
    @objid ("b3f72445-393b-451f-97b6-b4bb8f1804ac")
    @SuppressWarnings ("unchecked")
    public T get(Request req) {
        return (T) req.getExtendedData().get(this.key);
    }

    /**
     * @param req the request to modify
     * @param val the property value.
     */
    @objid ("56588559-4152-459d-847c-9531785d92fd")
    public void set(Request req, T val) {
        req.getExtendedData().put(this.key, val);
    }

}
