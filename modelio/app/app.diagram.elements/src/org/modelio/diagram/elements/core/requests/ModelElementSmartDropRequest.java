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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.Request;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("80dd1831-1dec-11e2-8cad-001ec947c8cc")
public class ModelElementSmartDropRequest extends Request {
    @objid ("936c70d8-1e83-11e2-8cad-001ec947c8cc")
    public static final String TYPE = "SMARTDROPELEMENT";

    @objid ("80dd1836-1dec-11e2-8cad-001ec947c8cc")
    private ModelElementDropRequest dropRequest;

    @objid ("80dd1837-1dec-11e2-8cad-001ec947c8cc")
    public  ModelElementSmartDropRequest(ModelElementDropRequest dropRequest) {
        super(TYPE);
        this.dropRequest = dropRequest;
        
    }

    @objid ("80dd183a-1dec-11e2-8cad-001ec947c8cc")
    public ModelElementDropRequest getDropRequest() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.dropRequest;
    }

    @objid ("80dd183e-1dec-11e2-8cad-001ec947c8cc")
    public void setLocation(Point dropLocation) {
        this.dropRequest.setLocation(dropLocation);
    }

    @objid ("80dd1843-1dec-11e2-8cad-001ec947c8cc")
    public Point getDropLocation() {
        return this.dropRequest.getDropLocation();
    }

    @objid ("80dd1849-1dec-11e2-8cad-001ec947c8cc")
    public MObject[] getDroppedElements() {
        return this.dropRequest.getDroppedElements();
    }

}
