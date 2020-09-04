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

package org.modelio.diagram.editor.bpmn.wizard;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ISelection;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("226fc484-541d-48f4-936f-001598bb4e5b")
public interface IModelTransformer {
    /**
     * Return true is the transformer is theoretically able to carry out a transformation given the diagram and the selection. This check will make the transformation command appear or not in the GUI. Tips: - try to keep this evaluation as light and fast
     * as possible - consider <code>canExecute()</code> alternative that only "grays" the transformation command. A greyed command has some indicative value to the end-user which is sometimes more clear than an 'not visible' command.
     * @param diagram
     * @param selection @return
     */
    @objid ("69a68988-4d52-4b43-a1af-85ce4dbec250")
    boolean isAvailable(AbstractDiagram diagram, ISelection selection);

    /**
     * Execute the transformation.
     * @param diagram
     * @param selection @return
     */
    @objid ("f7f95faf-a32f-47c1-87c5-f4c082076c5b")
    List<MObject> transform(AbstractDiagram diagram, ISelection selection);

    /**
     * Return true is the transformer can be run given the diagram, the selection and the model current state. Typically this method checks read only conditions, model links, values and so on. The effect of returnign false is a 'greyed' command (ie disable
     * but visible) that is an indication to the end-user that some transformation could be possible but is not executable immediately.
     * @param diagram
     * @param selection @return
     */
    @objid ("4ce3f725-c370-4701-b388-9d5365606a2f")
    boolean canExecute(AbstractDiagram diagram, ISelection selection);

}
