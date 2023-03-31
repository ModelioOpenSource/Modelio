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
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.ILinkPath;
import org.modelio.api.modelio.diagram.ILinkRoute;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface for creating graphic links in a diagram.
 * <p>
 * 
 * In a diagram, graphic links are graphic objects that display diagram links. For example an ElementImport is a graphic link.
 * <code>ILinkCommand</code> is the interface that drives the interaction of creating a link
 * <p>
 * 
 * The interaction is started when the user clicks on the button in the diagram palette. Once the interaction is started
 * the user can use the mouse to create a new graphic link in the diagram. The
 * {@link #acceptFirstElement(IDiagramHandle, IDiagramGraphic) acceptFirstElement} method is called to know if the
 * element flown over by the mouse is valid to be the origin of the link. If it is not valid the user cannot click. If
 * the element is valid the interaction moves on to the next step. The
 * {@link #acceptSecondElement(IDiagramHandle, IDiagramGraphic, IDiagramGraphic) acceptSecondElement} is called
 * until the user click on the destination of the link. When the user clicks on the destination the
 * {@link #actionPerformed(IDiagramHandle, IDiagramGraphic, IDiagramGraphic, LinkRouterKind, ILinkPath)
 * actionPerformed} method is called.
 * <p>
 * 
 * The
 * {@link #actionPerformed(IDiagramHandle, IDiagramGraphic, IDiagramGraphic, LinkRouterKind, ILinkPath)
 * actionPerformed} is in charge of creating the link in the model and of unmasking the element in the diagram.
 * 
 * This interface is intended to be implemented by MDA Components in order to add commands in a diagram palette.
 */
@objid ("01e402b4-0000-68da-0000-000000000000")
public interface ILinkTool extends IDiagramTool {
    /**
     * This method accept or refuse the interaction in the diagram for the origin of the link.
     * <p>
     * 
     * This method is called until the user clicks on an element.<br>
     * If the interaction is allowed the method <code>return new InteractionStatus(true, "");</code><br>
     * If the interaction is not allowed the method <code>return new InteractionStatus(false, "Tooltip message");</code>
     * . In this case the mouse pointer is changed to a "forbidden" icon and the tooltip message is displayed in a
     * tooltip near the mouse pointer<br>
     * @param diagramHandle the representation of the diagram in which the interaction occurs.
     * @param targetNode the graphic that is below the mouse pointer.
     * @return an InteractionStatus that represents the result of the acceptFirstElement method.
     */
    @objid ("01e402b4-0000-68db-0000-000000000000")
    boolean acceptFirstElement(final IDiagramHandle diagramHandle, IDiagramGraphic targetNode);

    /**
     * This method accept or refuse the interaction with the diagram for the destination element of the link.
     * <p>
     * 
     * This method is called after the user has choosen the origin of the link.<br>
     * If the interaction is allowed the method <code>return new InteractionStatus(true, "");</code><br>
     * If the interaction is not allowed the method <code>return new InteractionStatus(false, "Tooltip message");</code>
     * . In this case the mouse pointer is changed to a "forbidden" icon and the tooltip message is displayed in a
     * tooltip near the mouse pointer<br>
     * The acceptSecondElement method will be called until the user choose the destination of the link.
     * @param diagramHandle the representation of the diagram in which the interaction occurs.
     * @param originNode the graphic that is the origin of the link.
     * @param targetNode the graphic the is below the mouse pointer.
     * @return an InteractionStatus that represents the result of the acceptSecondElement method.
     */
    @objid ("01e402b4-0000-6901-0000-000000000000")
    boolean acceptSecondElement(final IDiagramHandle diagramHandle, IDiagramGraphic originNode, IDiagramGraphic targetNode);

    /**
     * This method is invoked when the command is launched.
     * <p>
     * 
     * The actionPerformed is called after user has choosen the destination of the link.<br>
     * 
     * The method is in charge of creating the link in the model and of unmasking it in the diagram.
     * <p>
     * 
     * <code>originNode</code> and <code>targetNode</code> are the origin and the destination of the link.
     * @see IDiagramHandle#unmask(MObject, int, int)
     * @param diagramHandle the representation of the diagram where the command has been triggered.
     * @param originNode the origin graphic of the link to create.
     * @param targetNode the destination graphic of the link to create.
     * @param routerType the router type that is currently defined to compute the path of the link.
     * @param path the link path deduced from the user interactions.
     */
    @objid ("01e402b4-0000-68dc-0000-000000000000")
    void actionPerformed(final IDiagramHandle diagramHandle, IDiagramGraphic originNode, IDiagramGraphic targetNode, LinkRouterKind routerType, ILinkRoute path);
}

