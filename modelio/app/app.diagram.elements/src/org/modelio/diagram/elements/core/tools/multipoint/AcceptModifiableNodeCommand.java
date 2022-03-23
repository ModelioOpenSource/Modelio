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
package org.modelio.diagram.elements.core.tools.multipoint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.vcore.model.api.MTools;

/**
 * Command that does nothing and that is executable only if the diagram and represented element is modifiable.
 * 
 * @author cmarin
 */
@objid ("80e6a180-1dec-11e2-8cad-001ec947c8cc")
public class AcceptModifiableNodeCommand extends Command {
    @objid ("80e6a184-1dec-11e2-8cad-001ec947c8cc")
    private GmModel model;

    /**
     * Constructor
     * @param m the accepted node model.
     */
    @objid ("80e6a185-1dec-11e2-8cad-001ec947c8cc")
    public  AcceptModifiableNodeCommand(final GmModel m) {
        this.model = m;
    }

    /**
     * Convenience constructor with an edit part.
     * @param editPart the accepted edit part. The edit part must have a {@link GmModel} as model.
     */
    @objid ("80e6a18a-1dec-11e2-8cad-001ec947c8cc")
    public  AcceptModifiableNodeCommand(final EditPart editPart) {
        this.model = (GmModel) editPart.getModel();
    }

    @objid ("80e6a191-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canExecute() {
        return (MTools.getAuthTool().canModify(this.model.getDiagram().getRelatedElement()) && MTools.getAuthTool().canModify(this.model.getRelatedElement()));
    }

}
