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
package org.modelio.diagram.elements.common.embeddeddiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;

/**
 * Embedded diagrams layer manager.
 * <p>
 * Embedded diagrams have their own layers except the handle layer and the feed back layers.
 * The root diagram layer is used for the last ones.
 * 
 * @author cma
 * @since 3.7
 */
@objid ("5482b4cf-57ba-4941-836c-5dab0b39d86c")
class EmbeddedLayerManager implements LayerManager {
    @objid ("f00f19f9-1bc5-4462-b4e1-7696811e4ecf")
    private final LayerManager parentLayerManager;

    @objid ("75074a72-0282-4a60-82db-caa3fcedc076")
    private final EmbeddedDiagramRootEditPart rootEp;

    @objid ("9d9b2c3d-c598-43fe-93ff-9fad143e3028")
    public  EmbeddedLayerManager(EmbeddedDiagramRootEditPart rootEp) {
        this.rootEp = rootEp;
        this.parentLayerManager = LayerManager.Helper.find(this.rootEp.getParent());
        
    }

    @objid ("f623ee0b-d62a-4359-9182-28fc009ec312")
    @Override
    public IFigure getLayer(Object key) {
        if (key == LayerConstants.HANDLE_LAYER 
                || key == LayerConstants.FEEDBACK_LAYER
                || key == LayerConstants.SCALED_FEEDBACK_LAYER) {
            return this.parentLayerManager.getLayer(key);
        }
        
        IFigure layer = this.rootEp.getLayer(key);
        return layer;
    }

}
