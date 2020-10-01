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

package org.modelio.diagram.elements.core.link.createhandle;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreationFactory;

/**
 * {@link CreationFactory} to put on a {@link CreateConnectionRequest}
 * to ask for a popup menu giving the user choice of the connection to create.
 * 
 * @author cma
 * @since 3.6
 */
@objid ("4d9ad735-8093-483d-b36f-ac793f8643f9")
public class UserChoiceLinkCreationFactory implements CreationFactory {
    @objid ("fcfda971-f14f-466f-9027-98400313ad2e")
    @Override
    public Object getNewObject() {
        return null;
    }

    @objid ("014c0961-b065-4f77-aa93-b38cfa78b952")
    @Override
    public Object getObjectType() {
        return getClass();
    }

}
