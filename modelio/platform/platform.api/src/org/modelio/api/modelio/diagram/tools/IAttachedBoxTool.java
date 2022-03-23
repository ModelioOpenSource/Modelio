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
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.ILinkRoute;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface for creating graphic attached boxes in a diagram.
 * <p>
 * 
 * In a diagram, graphic boxes attached are graphic objects that display diagram nodes linked to another diagram
 * element. For example Notes and Constraints are graphic attached boxes.
 * <code>IAttachedBoxCommand</code> is the interface that drives the interaction of creating a box attached to another
 * diagram element
 * <p>
 * 
 * The interaction is started when the user clicks on the button in the diagram palette. Once the interaction is started
 * the user can use the mouse to create a new graphic attached box in the diagram. The
 * {@link #acceptElement(IDiagramHandle, IDiagramGraphic) acceptElement} method is called to know if the element
 * flown over by the mouse is valid for the command. If it is not valid the user cannot finish the interaction. If the
 * element is valid the user can terminates the interaction.<br>
 * If the user click in the diagram background
 * {@link #actionPerformedInDiagram(IDiagramHandle, Rectangle) actionPerformedInDiagram} is called.<br>
 * If the user click on the element to attach then in the diagram background
 * {@link #actionPerformed(IDiagramHandle, IDiagramGraphic, LinkRouterKind, ILinkPath, Point)
 * actionPerformed} is called.
 * <p>
 * 
 * The
 * {@link #actionPerformed(IDiagramHandle, IDiagramGraphic, LinkRouterKind, ILinkPath, Point)
 * actionPerformed} and {@link #actionPerformedInDiagram(IDiagramHandle, Rectangle)
 * actionPerformedInDiagram} methods are in charge of creating the model element in the model and of unmasking the
 * element in the diagram.
 * 
 * This interface is intended to be implemented by MDA Components in order to add commands in a diagram palette.
 */
@objid ("01ec00d8-0000-5a5f-0000-000000000000")
public interface IAttachedBoxTool extends IDiagramTool {
    /**
     * This method accept or refuse the interaction in the diagram.
     * <p>
     * 
     * This method is called until the interaction is validated by the user.<br>
     * If the interaction is allowed the method <code>return new InteractionStatus(true, "");</code><br>
     * If the interaction is not allowed the method <code>return new InteractionStatus(false, "Tooltip message");</code>
     * . In this case the mouse pointer is changed to a "forbiden" icon and the tooltip message is displayed in a
     * tooltip near the mouse pointer<br>
     * @param diagramHandle the representation of the diagram in which the interaction occurs.
     * @param targetNode the graphic that is below the mouse pointer.
     * @return an InteractionStatus that represents the result of the acceptElement method.
     */
    @objid ("01ec00d8-0000-5a65-0000-000000000000")
    boolean acceptElement(final IDiagramHandle diagramHandle, IDiagramGraphic targetNode);

    /**
     * This method is called when the user click on an existing diagram element.
     * <p>
     * 
     * The method is in charge of creating the model element in the model and of unmasking the element in the diagram.
     * <p>
     * 
     * The originNode argument contains the diagram node container corresponding to the origin graphic box.
     * <p>
     * 
     * The rect argument is the rectangle that result from the user interaction with the diagram. This rect should be
     * used to manage the unmasking of the model element in the diagram.
     * @see IDiagramHandle#unmask(MObject, int, int)
     * @param diagramHandle the representation of the diagram where the command has been triggered.
     * @param originNode the graphic parent where the user has clicked.
     * @param routerType the router type that is currently defined to compute the path of the link.
     * @param path the link path deduced from the user interactions.
     * @param point the point of the second user click.
     */
    @objid ("01ec00d8-0000-5a6e-0000-000000000000")
    void actionPerformed(final IDiagramHandle diagramHandle, IDiagramGraphic originNode, LinkRouterKind routerType, ILinkRoute path, Point point);

    /**
     * This method is called when the user click in the diagram background.
     * <p>
     * 
     * The method is in charge of creating the model element in the model and of unmasking the element in the diagram.
     * <p>
     * 
     * The rect argument is the rectangle that result from the user interaction with the diagram background. This rect
     * should be used to manage the unmasking of the model element in the diagram.
     * @see IDiagramHandle#unmask(MObject, int, int)
     * @param diagramHandle the representation of the diagram where the command has been triggered.
     * @param rect the rectangle of the object to create.
     */
    @objid ("01ec00d8-0000-5d0a-0000-000000000000")
    void actionPerformedInDiagram(final IDiagramHandle diagramHandle, Rectangle rect);

}
