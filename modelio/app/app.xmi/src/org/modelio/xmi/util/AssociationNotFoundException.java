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

package org.modelio.xmi.util;

import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("3bc732bb-d5cd-4f90-b943-c2d74ba3c121")
public class AssociationNotFoundException extends Exception {
    @objid ("23afe5d9-ed08-4aed-a136-a9b3315d603f")
    public AssociationNotFoundException(String message) {
        super(message);
    }

    @objid ("1f717c1a-2d5d-4360-9120-7da8706c71c6")
    public AssociationNotFoundException() {
        super();
    }

}
