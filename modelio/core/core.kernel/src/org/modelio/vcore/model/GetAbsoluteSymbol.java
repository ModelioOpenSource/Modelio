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
package org.modelio.vcore.model;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.SmObjectImpl;

/**
 * Service class to get the absolute name of an element.
 */
@objid ("41a4785e-5ba5-11e1-a296-001ec947ccaf")
public class GetAbsoluteSymbol {
    /**
     * Get the absolute name of an element.
     * @param obj an element
     * @return the absolute name .
     */
    @objid ("134ff522-5bcb-11e1-a296-001ec947ccaf")
    public static String get(final SmObjectImpl obj) {
        StringBuilder s = new StringBuilder();
        get(obj, s);
        return s.toString();
    }

    @objid ("134ff528-5bcb-11e1-a296-001ec947ccaf")
    private static void get(final SmObjectImpl obj, final StringBuilder s) {
        SmObjectImpl parent = obj.getCompositionOwner();
        if (parent != null) {
            get (parent, s);
            s.append('.');
        }
        s.append(obj.getName());
        
    }

}
