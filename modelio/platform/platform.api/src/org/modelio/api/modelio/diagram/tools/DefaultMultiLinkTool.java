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

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.api.modelio.diagram.IDiagramGraphic;
import org.modelio.api.modelio.diagram.IDiagramHandle;
import org.modelio.api.modelio.diagram.IDiagramLink.LinkRouterKind;
import org.modelio.api.modelio.diagram.ILinkRoute;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Interface for complex graphic creation interaction in a diagram. <br>
 * <br>
 * The interaction is started when the user clicks on the button in the diagram palette. Once the interaction is started the user
 * can use the mouse to create a new graphic multi link in the diagram.<br>
 * The {@link #acceptFirstElement(IDiagramHandle, IDiagramGraphic) acceptFirstElement} method is called to know if the element flown
 * over by the mouse is valid to be the first element of the interaction. If it is not valid as first element, then the
 * {@link #acceptLastElement(IDiagramHandle, List, IDiagramGraphic) acceptLastElement} method is called to know if the element flown
 * over by the mouse is valid as last element of the interaction (in which case the interaction is done with a single click, as is
 * the case for IBoxCommand for example). <br>
 * If the element is valid as first element the interaction moves on to the next step. The
 * {@link #acceptAdditionalElement(IDiagramHandle, List, IDiagramGraphic) acceptAdditionalElement} method is called to know if the
 * element flown over by the mouse can be added to the current interaction. If it is not valid to be added to the current
 * interaction, then the {@link #acceptLastElement(IDiagramHandle, List, IDiagramGraphic) acceptLastElement} method is called to
 * know if the element flown over by the mouse is valid as last element of the interaction.<br>
 * When the user clicks on an element for which the {@link #acceptAdditionalElement(IDiagramHandle, List, IDiagramGraphic)
 * acceptAdditionalElement} method doesn't accept the element to be added to the interaction but the
 * {@link #acceptLastElement(IDiagramHandle, List, IDiagramGraphic) acceptLastElement} method accepts the element as last element of
 * the interaction, the {@link #actionPerformed(IDiagramHandle, IDiagramGraphic, List, List, List, Rectangle) actionPerformed}
 * method is called. <br>
 * <br>
 * The {@link #actionPerformed(IDiagramHandle, IDiagramGraphic, List, List, List, Rectangle) actionPerformed} method is in charge of
 * creating the elements in the model and of unmasking them in the diagram.
 * 
 * This interface is intended to be implemented by MDA Components in order to add commands in a diagram palette.
 */
@objid ("a48286f8-251b-11de-81ed-8000600fe800")
public abstract class DefaultMultiLinkTool extends DefaultDiagramTool implements IMultiLinkTool {
    /**
     * This method accept or refuse the interaction in the diagram for the first element.
     * <p>
     * 
     * This method is called until the user clicks on an element.<br>
     * If the interaction is allowed the method return <code>new InteractionStatus(true, "");</code><br>
     * If the interaction is not allowed the method return <code>new InteractionStatus(false, "Tooltip message");</code> . In this
     * case the acceptLastElement method is called with the same arguments, to check if the pointed element could be the last of the
     * interaction.<br>
     * @param diagramHandle the representation of the diagram in which the interaction occurs.
     * @param targetNode the graphic that is below the mouse pointer.
     * @return an InteractionStatus that represents the result of the acceptFirstElement method.
     */
    @objid ("af03c918-251b-11de-81ed-8000600fe800")
    @Override
    public abstract boolean acceptFirstElement(final IDiagramHandle diagramHandle, IDiagramGraphic targetNode);

    /**
     * This method accept or refuse the interaction with the diagram for an additional element of the interaction. <br>
     * <br>
     * This method is called after the method acceptFirstElement returned true, to check whether the target element could be valid
     * as an additional element of the interaction.<br>
     * If the interaction is allowed the method return <code>new InteractionStatus(true, "");</code><br>
     * If the interaction is not allowed the method return <code>new InteractionStatus(false, "Tooltip message");</code> . In this
     * case the acceptLastElement method is called with the same arguments, to check if the pointed element could be the last of the
     * interaction.<br>
     * @param diagramHandle the representation of the diagram in which the interaction occurs.
     * @param previousNodes the graphics accepted until now (might be empty).
     * @param targetNode the graphic that is below the mouse pointer.
     * @return an InteractionStatus that represents the result of the acceptAdditionalElement method.
     */
    @objid ("af03c91d-251b-11de-81ed-8000600fe800")
    @Override
    public abstract boolean acceptAdditionalElement(final IDiagramHandle diagramHandle, List<IDiagramGraphic> previousNodes, IDiagramGraphic targetNode);

    /**
     * This method accept or refuse the interaction with the diagram for the "last" element of the interaction. <br>
     * <br>
     * This method is called after the method acceptFirstElement (or acceptAdditionalElement, depending on the case) returned false,
     * to check whether the target element could be valid as the last element of the interaction.<br>
     * If the interaction is allowed the method return <code>new InteractionStatus(true, "");</code><br>
     * If the interaction is not allowed the method return <code>new InteractionStatus(false, "Tooltip message");</code> . In this
     * case the mouse pointer is changed to a "forbidden" icon and the tooltip message is displayed in a tooltip near the mouse
     * pointer<br>
     * @param diagramHandle the representation of the diagram in which the interaction occurs.
     * @param otherNodes the graphics accepted until now (might be empty).
     * @param targetNode the graphic that is below the mouse pointer.
     * @return an InteractionStatus that represents the result of the acceptLastElement method.
     */
    @objid ("af062b6d-251b-11de-81ed-8000600fe800")
    @Override
    public abstract boolean acceptLastElement(final IDiagramHandle diagramHandle, List<IDiagramGraphic> otherNodes, IDiagramGraphic targetNode);

    /**
     * Construct a DefaultMultiLinkCommand .
     */
    @objid ("a3e4e361-0ecc-11e2-96c4-002564c97630")
    public  DefaultMultiLinkTool() {
        
    }

    /**
     * This method is invoked when the command is launched. <br>
     * <br>
     * The actionPerformed is called after user has choosen the last destination.<br>
     * 
     * The method is in charge of creating the element(s) in the model and of unmasking it (them) in the diagram. <br>
     * 
     * <code>lastNode</code> is the graphic accepted by the acceptLastElement method.
     * 
     * <code>otherNodes</code> are all the graphics accepted by calls to acceptFirstNode and acceptAdditionalNode.
     * @see IDiagramHandle#unmask(MObject, int, int)
     * @param diagramHandle the representation of the diagram where the command has been triggered.
     * @param lastNode the last graphic accepted.
     * @param otherNodes all previous accepted graphics.
     * @param routerKinds the router types that are currently defined to compute the path of the links from the previously accepted graphics
     * to the element to create.
     * @param paths the link paths deduced from the user interactions.
     */
    @objid ("a3e57fa5-0ecc-11e2-96c4-002564c97630")
    @Override
    public abstract void actionPerformed(final IDiagramHandle diagramHandle, IDiagramGraphic lastNode, List<IDiagramGraphic> otherNodes, List<LinkRouterKind> routerKinds, List<ILinkRoute> paths, Rectangle rectangle);

}
