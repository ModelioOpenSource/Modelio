/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.core.policies;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.modelio.diagram.elements.core.commands.FitToContentCommand;

/**
 * The host will try to resizes itself to fits its content when a child node is added, removed or changed.
 * <p>
 * This policy should be installed with the {@link AutoFitToContentEditPolicy#ROLE} role.
 * 
 * @author cmarin
 */
@objid ("80b49023-1dec-11e2-8cad-001ec947c8cc")
public class AutoFitToContentEditPolicy extends GraphicalEditPolicy {
    /**
     * Role to use when installing this edit policy.
     */
    @objid ("924e56ac-1e83-11e2-8cad-001ec947c8cc")
    public static final String ROLE = "Auto fit to content";

    @objid ("4e8b3e40-3231-47ec-93c3-e670958cfaf3")
    private boolean fitHorizontal;

    @objid ("91dcf457-3846-4479-8ff9-32b1511b5f1a")
    private boolean fitVertical;

    @objid ("80b4902a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Command getCommand(final Request request) {
        final Object reqType = request.getType();
        
        if (REQ_ADD.equals(reqType)) {
            return getFitCommand();
        }
        
        if (REQ_ORPHAN_CHILDREN.equals(reqType)) {
            return getFitCommand();
        }
        
        if (REQ_MOVE_CHILDREN.equals(reqType)) {
            return getFitCommand();
        }
        
        if (REQ_RESIZE_CHILDREN.equals(reqType)) {
            return getFitCommand();
        }
        
        if (REQ_CREATE.equals(reqType)) {
            return getFitCommand();
        }
        return null;
    }

    @objid ("80b49035-1dec-11e2-8cad-001ec947c8cc")
    private Command getFitCommand() {
        return new FitToContentCommand((GraphicalEditPart) getHost(), this.fitHorizontal, this.fitVertical);
    }

    /**
     * @param fitHorizontal whether to fit horizontally
     * @param fitVertical whether to fit vertically
     */
    @objid ("8a371f8b-c41a-45d0-a97e-2a7ed48541c3")
    public AutoFitToContentEditPolicy(boolean fitHorizontal, boolean fitVertical) {
        this.fitHorizontal = fitHorizontal;
        this.fitVertical = fitVertical;
    }

}
