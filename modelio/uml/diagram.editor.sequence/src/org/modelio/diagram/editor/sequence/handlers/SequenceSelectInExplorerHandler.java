/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.diagram.editor.sequence.handlers;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.diagram.editor.handlers.SelectInExplorerHandler;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("d99f2ccf-55b6-11e2-877f-002564c97630")
public class SequenceSelectInExplorerHandler extends SelectInExplorerHandler {
    @objid ("d99f2cd3-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canExecute(ISelection selection) {
        List<MObject> listSelection = getSelection(selection);
        for (MObject selectedElement : listSelection) {
            if (selectedElement instanceof AbstractDiagram) {
                return false;
            }
        }
        return listSelection.size() > 0;
    }

}
