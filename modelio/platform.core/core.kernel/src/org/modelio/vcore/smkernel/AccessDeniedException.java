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

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Tells the access to a model object has been denied.
 */
@objid ("000e83c8-702c-1f21-85a5-001ec947cd2a")
public class AccessDeniedException extends RuntimeException {
    @objid ("00121e16-4fda-1f32-b43f-001ec947cd2a")
    private static final long serialVersionUID = 1L;

    @objid ("c79e3185-d58f-11e1-b069-001ec947ccaf")
    private MObject related;

    /**
     * Initialize the exception.
     * @param message a user friendly error message
     * @param related the object whose access was denied.
     */
    @objid ("dc142713-8fb5-11e1-81e9-001ec947ccaf")
    public AccessDeniedException(final String message, final MObject related) {
        super(message);
        this.related = related;
    }

    /**
     * Get the the object whose access was denied.
     * @return the read only object.
     */
    @objid ("c79e3188-d58f-11e1-b069-001ec947ccaf")
    public MObject getRelated() {
        return this.related;
    }

}
