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

@objid ("80dab5c9-1dec-11e2-8cad-001ec947c8cc")
public class ModelElementDropRequest extends Request {
    @objid ("9367ac37-1e83-11e2-8cad-001ec947c8cc")
    public static final String TYPE = "DROPELEMENT";

    @objid ("936a0e83-1e83-11e2-8cad-001ec947c8cc")
    private static final String SMART = "SMART";

    @objid ("80dab5ce-1dec-11e2-8cad-001ec947c8cc")
    private MObject[] droppedElements;

    @objid ("68205c75-1e83-11e2-8cad-001ec947c8cc")
    private Point dropLocation;

    @objid ("80dab5d6-1dec-11e2-8cad-001ec947c8cc")
    public  ModelElementDropRequest() {
        super(TYPE);
        this.droppedElements = new MObject[0];
        
    }

    @objid ("80dab5d8-1dec-11e2-8cad-001ec947c8cc")
    public void setLocation(Point dropLocation) {
        this.dropLocation = dropLocation;
    }

    @objid ("80dab5dd-1dec-11e2-8cad-001ec947c8cc")
    public MObject[] getDroppedElements() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.droppedElements;
    }

    @objid ("80dd181b-1dec-11e2-8cad-001ec947c8cc")
    public void setDroppedElements(MObject[] value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.droppedElements = value;
        
    }

    @objid ("80dd1820-1dec-11e2-8cad-001ec947c8cc")
    public Point getDropLocation() {
        return this.dropLocation;
    }

    @objid ("80dd1826-1dec-11e2-8cad-001ec947c8cc")
    public boolean isSmart() {
        Object value = getExtendedData().get(ModelElementDropRequest.SMART);
        if (value == null || (Boolean) value) {
            return true;
        }
        return false;
    }

    @objid ("80dd182a-1dec-11e2-8cad-001ec947c8cc")
    public void setSmart(final boolean value) {
        getExtendedData().put(ModelElementDropRequest.SMART, new Boolean(value));
    }

}
