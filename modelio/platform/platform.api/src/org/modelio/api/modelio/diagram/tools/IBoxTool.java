/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.modelio.api.modelio.diagram.tools;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface for creating graphic boxes in a diagram.
 * <p>
 * 
 * In a diagram, graphic boxes are graphic objects that display diagram nodes. For example Packages and Classes are
 * graphic boxes. <code>IBoxCommand</code> is the interface that drives the interaction of creating a box
 * <p>
 * 
 * The interaction is started when the user clicks on the button in the diagram palette. Once the interaction is started
 * the user can use the mouse to create a new graphic box in the diagram. The
 * {@link #acceptElement(IDiagramHandle, IDiagramGraphic) acceptElement} method is called to know if the element flown
 * over by the mouse is valid for the command. If it is not valid the user cannot finish the interaction. If the element
 * is valid the user can terminates the interaction by either clicking or by pressing the left mouse button doing a drag
 * and drop and releasing the mouse button. Once this is done the
 * {@link #actionPerformed(IDiagramHandle, IDiagramGraphic, Rectangle) actionPerformed} is called.
 * <p>
 * 
 * The {@link #actionPerformed(IDiagramHandle, IDiagramGraphic, Rectangle) actionPerformed} is in charge of creating the
 * model element in the model and of unmasking the element in the diagram.
 * 
 * This interface is intended to be implemented by MDA Components in order to add commands in a diagram palette.
 */
@objid ("01e402b4-0000-68bb-0000-000000000000")
public interface IBoxTool extends IDiagramTool {
    /**
     * This method accepts or refuses the interaction in the diagram.
     * <p>
     * 
     * This method is called until the interaction is validated by the user.<br>
     * If the interaction is allowed the method returns <code>true</code><br>
     * If the interaction is not allowed the method return <code>false</code>. In this case the mouse pointer is changed
     * to a "forbidden" icon<br>
     * 
     * @param diagramHandle a handle of the diagram in which the interaction occurs.
     * @param targetNode the graphic that is below the mouse pointer.
     * @return a boolean
     */
    @objid ("01e402b4-0000-68bc-0000-000000000000")
    boolean acceptElement(final IDiagramHandle diagramHandle, IDiagramGraphic targetNode);

    /**
     * This method is called when the interaction is validated by the user.
     * <p>
     * 
     * The method is in charge of creating the model element in the model and of unmasking the element in the diagram.
     * <p>
     * 
     * If the user click or click and drag in a graphic box the parent argument contains the diagram node container
     * corresponding to graphic box. If the user click or click and drag in the diagram background, parent is null.
     * <p>
     * 
     * The rect argument is the rectangle that result from the user interaction with the diagram. This rect should be
     * used to manage the unmasking of the model element in the diagram.
     * @see IDiagramHandle#unmask(MObject, int, int)
     * 
     * @param diagramHandle the representation of the diagram where the command has been triggered.
     * @param parent the graphic parent where the user has clicked.
     * @param rect the rectangle of the object to create.
     */
    @objid ("a3ead6f8-0ecc-11e2-96c4-002564c97630")
    void actionPerformed(final IDiagramHandle diagramHandle, IDiagramGraphic parent, Rectangle rect);

}
