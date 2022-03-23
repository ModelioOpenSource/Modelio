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
package org.modelio.diagram.elements.core.link;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.modelio.diagram.elements.core.link.path.RawPathData;
import org.modelio.diagram.elements.plugin.DiagramElements;

/**
 * A Request to create a new Connection with bend points.
 * 
 * @author cmarin
 */
@objid ("7fe523a7-1dec-11e2-8cad-001ec947c8cc")
public class CreateBendedConnectionRequest extends CreateConnectionRequest {
    /**
     * Bend point list, in absolute coordinates.
     */
    @objid ("7fe523ab-1dec-11e2-8cad-001ec947c8cc")
    private RawPathData data = new RawPathData();

    /**
     * The list of objects
     */
    @objid ("7fe523ad-1dec-11e2-8cad-001ec947c8cc")
    private List<Object> createdObjectsToSelect = new ArrayList<>();

    /**
     * Get the raw path data.
     * @return the raw data.
     */
    @objid ("7fe523b1-1dec-11e2-8cad-001ec947c8cc")
    public RawPathData getData() {
        return this.data;
    }

    /**
     * Returns the list of objects created by the execution of a command emitted for this request that should be
     * selected.
     * 
     * This list should only be filled by a creation command's execute method and read by the emitting tool.
     * @return the list of objects that should be created.
     */
    @objid ("7fe523c0-1dec-11e2-8cad-001ec947c8cc")
    public List<Object> getCreatedObjectsToSelect() {
        return this.createdObjectsToSelect;
    }

    @objid ("62a94038-4272-472e-8ea2-420a4f230ffb")
    @Override
    public String toString() {
        if (DiagramElements.LOG.isDebugEnabled()) {
            return String.format("%s[%n\tsource=%s%n\ttarget=%s%n\tlocation=%s%n\traw path data=%s%n]",
                    getClass().getSimpleName(),
                    this.getSourceEditPart(),
                    this.getTargetEditPart(),
                    this.getLocation(),
                    this.data);
        } else {
            return super.toString();
        }
        
    }

}
