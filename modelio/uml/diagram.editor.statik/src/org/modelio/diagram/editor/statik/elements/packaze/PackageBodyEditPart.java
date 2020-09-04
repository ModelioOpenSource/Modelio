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

package org.modelio.diagram.editor.statik.elements.packaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.IFigure;
import org.modelio.diagram.elements.common.freezone.FreeZoneEditPart;
import org.modelio.diagram.elements.core.policies.DefaultElementDropEditPolicy;
import org.modelio.diagram.elements.core.requests.ModelElementDropRequest;

/**
 * Package body edit part.
 * <p>
 * This class seems to exist only to set the initial size of the package body zone.
 * 
 * @author phv
 */
@objid ("36269d51-55b7-11e2-877f-002564c97630")
public class PackageBodyEditPart extends FreeZoneEditPart {
    /**
     * Constructor
     */
    @objid ("36269d55-55b7-11e2-877f-002564c97630")
    public PackageBodyEditPart() {
        super();
    }

    @objid ("362823bb-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSelectable() {
        return false;
    }

    @objid ("362823c0-55b7-11e2-877f-002564c97630")
    @Override
    protected void createEditPolicies() {
        super.createEditPolicies();
        installEditPolicy(ModelElementDropRequest.TYPE, new DefaultElementDropEditPolicy());
        //installEditPolicy(ModelElementDropRequest.TYPE+"2", new DefaultSmartDropEditPolicy());
    }

    @objid ("362823c3-55b7-11e2-877f-002564c97630")
    @Override
    protected IFigure createFigure() {
        final IFigure aFigure = super.createFigure();
        
        //aFigure.setPreferredSize(new Dimension(100, 100));
        //aFigure.setMinimumSize(new Dimension(100, 100));
        //aFigure.setSize(new Dimension(100, 100));
        return aFigure;
    }

}
