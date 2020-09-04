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

package org.modelio.core.rcp.inputpart;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

@objid ("b9b73970-2fd8-11e2-a79f-bc305ba4815c")
public interface IInputPartService {
    @objid ("b9b73971-2fd8-11e2-a79f-bc305ba4815c")
    MPlaceholder createSharedInputPart(String id, String inputURI);

    @objid ("7913927a-3334-11e2-95fe-001ec947c8cc")
    MPart showInputPart(String id, String inputURI, PartState partState);

    /**
     * Hide an eclipse MPart.
     * <p>
     * Equivalent to <code>hideInputPart(part, false)</code>.
     * </p>
     * 
     * @param part the part to hide.
     */
    @objid ("59810458-c0d1-41f0-b514-a45cb0f5ecd3")
    void hideInputPart(MPart part);

    @objid ("39be28da-71d2-4290-b5cd-a3dc66f23992")
    Collection<? extends MPart> getInputParts(String id);

    @objid ("48c66047-8fcb-4cb8-83e0-dcc098b79ee2")
    MPart getInputPart(String id, String inputURI);

    /**
     * Hide an eclipse MPart.
     * 
     * @param part the part to hide.
     * @param forceVisible if <code>true</code>, force to show the part before hiding it (the part cannot be hidden if it is not visible in the current perspective)
     */
    @objid ("db6223fd-e739-45fb-8feb-07841843e0e1")
    void hideInputPart(MPart part, boolean forceVisible);

}
