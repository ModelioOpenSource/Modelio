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

package org.modelio.diagram.elements.core.helpers.palapi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.modelio.diagram.elements.core.commands.ModelioCreationContext;
import org.modelio.diagram.elements.core.link.CreateBendedConnectionRequest;
import org.modelio.diagram.elements.core.link.ModelioLinkCreationContext;
import org.modelio.diagram.elements.core.link.createhandle.ICreationActionDescriptor;
import org.modelio.diagram.elements.core.link.createhandle.ICreationActionProvider;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.requests.CreateLinkConstants;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("cbb89e5e-4c24-4180-94b8-efded5b707b9")
public class PaletteActionProvider implements ICreationActionProvider {
    @objid ("65232dae-eaac-4bfc-a94f-3783e1ec4867")
    private EditPart editPart;

    @objid ("7b0d8cb1-c46f-4338-a04a-035adf807ac8")
     Predicate<PaletteEntry> filter;

    @objid ("4ff8d2c5-6439-4589-b909-64283cc51693")
     List<PaletteEntry> tools = new ArrayList<>();

    @objid ("7ef0268b-1099-47cb-91d3-1a85329e8fd9")
    public static final Predicate<PaletteEntry> IS_LINK_TOOL = entry -> ((entry instanceof ConnectionCreationToolEntry)
			&& ((ConnectionCreationToolEntry) entry).getToolProperty(CreationTool.PROPERTY_CREATION_FACTORY) instanceof ModelioLinkCreationContext);

    @objid ("bcf3d35a-f695-4148-b547-7bb363e2888d")
    public static final Predicate<PaletteEntry> IS_NODE_TOOL = entry -> ((entry instanceof CreationToolEntry)
			&& ((CreationToolEntry) entry).getToolProperty(CreationTool.PROPERTY_CREATION_FACTORY) instanceof ModelioCreationContext);

    @objid ("9f95e194-5bb5-4e13-b76a-1f44c56ed353")
    public static final Predicate<PaletteEntry> IS_ACCEPTABLE_METACLASS(Collection<Class<? extends MObject>> metaclasses) {
        return entry -> {
        
                    ModelioCreationContext nodeCtx = getNodeCreationContext(entry);
                    if (nodeCtx != null) {
                        return metaclasses.contains(nodeCtx.getJavaClass());
                    }
        
                    ModelioLinkCreationContext linkCtx = getLinkCreationContext(entry);
                    if (linkCtx != null) {
                        return metaclasses.contains(linkCtx.getJavaClass());
                    }
        
                    return false;
                };
    }

    /**
     * Convenience method proposed to PaleteActionProvider users to help them building 'filters'.
     * @param entry a palette entry
     * @return the metaclass of the object that this entry creates (whatever the tool kind: link or node, excepted module tools) , null otherwise.
     */
    @objid ("b1e9ab73-af10-43a1-97d4-c0a3e7adbc12")
    public static MClass getPaletteEntryMetaclass(PaletteEntry entry) {
        if (entry.getClass().getName().startsWith("org.modelio.diagram.api.tools")) {
            return null;
        }
        
        ModelioCreationContext nodeCtx = getNodeCreationContext(entry);
        if (nodeCtx != null) {
            return nodeCtx.getMetaclass();
        }
        
        ModelioLinkCreationContext linkCtx = getLinkCreationContext(entry);
        if (linkCtx != null) {
            return linkCtx.getMetaclass();
        }
        return null;
    }

    /**
     * Convenience method proposed to PaleteActionProvider users to help them building 'filters'.
     * @param entry a palette entry
     * @return the link creation context that this entry uses to create an object, null if the entry does not create a link
     */
    @objid ("975ae6aa-c703-42e3-8448-bd35c8a0700e")
    public static ModelioLinkCreationContext getLinkCreationContext(PaletteEntry entry) {
        if (entry instanceof ConnectionCreationToolEntry) {
            ConnectionCreationToolEntry ccentry = (ConnectionCreationToolEntry) entry;
            Object ofactory = ccentry.getToolProperty(CreationTool.PROPERTY_CREATION_FACTORY);
            if (ofactory instanceof ModelioLinkCreationContext) {
                return (ModelioLinkCreationContext) ofactory;
            }
        }
        return null;
    }

    /**
     * Convenience method proposed to PaleteActionProvider users to help them building 'filters'.
     * @param entry a palette entry
     * @return the node creation context that this entry uses to create an object, null if the entry does not create a node
     */
    @objid ("1779bea2-e994-43be-8538-ba8923af1bb8")
    public static ModelioCreationContext getNodeCreationContext(PaletteEntry entry) {
        if (entry instanceof CreationToolEntry) {
            Object ofactory = ((CreationToolEntry) entry).getToolProperty(CreationTool.PROPERTY_CREATION_FACTORY);
            if (ofactory instanceof ModelioCreationContext) {
                return (ModelioCreationContext) ofactory;
            }
        }
        return null;
    }

    @objid ("665c8658-cfb6-41d9-b08e-af344b5a0fa2")
    public PaletteActionProvider(EditPart editPart) {
        this(editPart, null);
    }

    @objid ("ea5a4b66-8571-4f2e-90a9-b0ea9ad3785f")
    public PaletteActionProvider(EditPart editPart, Predicate<PaletteEntry> filter) {
        this.editPart = editPart;
        this.filter = filter != null ? filter : e -> true;
    }

    @objid ("6f56f15c-8fc5-4516-aa66-e10ed204cedf")
    @Override
    public final Stream<ICreationActionDescriptor> getPaletteActions(CreateConnectionRequest req) {
        if (this.tools.isEmpty()) {
            collectPaletteActions();
        }
        return this.tools.stream()
                        .map(entry -> createAction(entry, this.editPart, req))
                        .filter(action -> action != null);
    }

    @objid ("dd067cd7-a0bb-4de7-9e32-9bbc5a79c066")
    private Stream<ICreationActionDescriptor> getPaletteActions(CreateConnectionRequest req, PaletteContainer paletteContainer) {
        return getSubEntryRec(paletteContainer)
                        .map(entry -> createAction(entry, this.editPart, req))
                        .filter(action -> action != null);
    }

    @objid ("08b892c0-361b-40e9-8858-10bafb37c0a4")
    private Stream<PaletteEntry> getSubEntryRec(PaletteContainer paletteContainer) {
        return paletteContainer
                        .getChildren()
                        .stream()
                        .flatMap(e -> (e instanceof PaletteContainer) ? getSubEntryRec((PaletteContainer) e) : Stream.of(e));
    }

    @objid ("c93cb5e6-f3a6-4bb0-98cb-df9b6e9b7385")
    private ICreationActionDescriptor createAction(PaletteEntry entry, EditPart ep, CreateConnectionRequest req) {
        // TODO hack to remove module tools, they should be properly taken into account instead...
        // We remove them because the behavior coming from module-custom tools is currently ignored.
        // Maybe the action's actual tool should be used instead of emulating a "fake" tool in createAction.
        if (entry.getClass().getName().startsWith("org.modelio.diagram.api.tools")) {
            return null;
        }
        
        ModelioLinkCreationContext linkCreationContext = getLinkCreationContext(entry);
        if (linkCreationContext != null) {
            return createLinkAction(entry, ep, req, linkCreationContext);
        }
        
        ModelioCreationContext nodeCreationContext = getNodeCreationContext(entry);
        if (nodeCreationContext != null) {
            return createNodeAction(entry, ep, req, nodeCreationContext);
        }
        return null;
    }

    @objid ("e6f441a6-50e1-4aa8-b0a4-7bdc6e0ed8bf")
    private ConnectionRouterId getDefaultRoutingMode(EditPart ep, ModelioLinkCreationContext ctx) {
        IGmObject gmDiagram = (IGmObject) ep.getViewer().getContents().getModel();
        final StyleKey routingModeKey = ctx.getDefaultRoutingModeKey();
        
        ConnectionRouterId primaryRoutingMode = null;
        if (routingModeKey != null) {
            primaryRoutingMode = gmDiagram.getDisplayedStyle().getProperty(routingModeKey);
        }
        
        if (primaryRoutingMode == null) {
            primaryRoutingMode = ConnectionRouterId.ORTHOGONAL;
        }
        return primaryRoutingMode;
    }

    @objid ("f9d929f3-170d-475a-aebf-017e453f6ae1")
    private PaletteViewer getPaletteViewer() {
        return this.editPart.getViewer().getEditDomain().getPaletteViewer();
    }

    @objid ("6efdb5fa-4946-4ab7-a87e-cad6ac08f60f")
    private void collectPaletteActions() {
        if (getPaletteViewer() != null) {
            this.tools = getSubEntryRec(getPaletteViewer().getPaletteRoot())
                    .filter(this.filter)
                    .distinct()
                    .collect(Collectors.toList());
        }
    }

    @objid ("b6003e8e-3690-4139-af2f-a61c71605e21")
    private ICreationActionDescriptor createNodeAction(PaletteEntry entry, EditPart ep, CreateRequest req, ModelioCreationContext nodeCreationContext) {
        CreateRequest createRequest = new CreateRequest();
        createRequest.setType(RequestConstants.REQ_CREATE);
        createRequest.setSize(new Dimension(-1, -1));
        createRequest.setLocation(req.getLocation());
        createRequest.setFactory(nodeCreationContext);
        Command cmd = ep.getCommand(createRequest);
        return new NodeToolActionDescriptor(entry, createRequest, cmd);
    }

    @objid ("a22414f3-049f-4582-912b-02b5a4b9c18c")
    private ICreationActionDescriptor createLinkAction(PaletteEntry entry, EditPart ep, CreateConnectionRequest req, ModelioLinkCreationContext linkCreationContext) {
        CreateBendedConnectionRequest createConReq = new CreateBendedConnectionRequest();
        Command finishCom = null;
        
        switch ((String) req.getType()) {
        case RequestConstants.REQ_CONNECTION_START:
            createConReq.setType(req.getType());
            createConReq.setFactory(linkCreationContext);
            createConReq.setSourceEditPart(ep);
            createConReq.setTargetEditPart(ep);
            createConReq.setLocation(req.getLocation().getCopy());
            createConReq.getData().setRoutingMode(getDefaultRoutingMode(ep, linkCreationContext));
            createConReq.getData().setLastPoint(req.getLocation().getCopy());
            finishCom = ep.getCommand(createConReq);
            createConReq.setStartCommand(finishCom);
            break;
            
        case RequestConstants.REQ_CONNECTION_END:
        case CreateLinkConstants.REQ_CONNECTION_CREATE_LINK_CHOOSENODE:
            // Branch both ends
            EditPart targetEditPart = req.getTargetEditPart();
            GraphicalEditPart srcEditPart = (GraphicalEditPart) req.getSourceEditPart();
        
            CreateBendedConnectionRequest startRequest = new CreateBendedConnectionRequest();
            startRequest.setType(RequestConstants.REQ_CONNECTION_START);
            startRequest.setFactory(linkCreationContext);
            startRequest.setSourceEditPart(srcEditPart);
            startRequest.setTargetEditPart(srcEditPart);
            
            Point pt = srcEditPart.getFigure().getBounds().getCenter();
            srcEditPart.getFigure().translateToAbsolute(pt);
            startRequest.setLocation(pt);
            startRequest.getData().setSrcPoint(pt);
            startRequest.getData().setRoutingMode(getDefaultRoutingMode(srcEditPart, linkCreationContext));
            startRequest.getData().setLastPoint(req.getLocation().getCopy());
            
            Command startCmd = srcEditPart.getCommand(startRequest);
            startRequest = null; // get rid of it
            
            // Now we have a valid startCommand
            // we can setup the final request to end the link creation
            createConReq.setType(req.getType());
            createConReq.setFactory(linkCreationContext);
            createConReq.setSourceEditPart(srcEditPart);
            createConReq.setTargetEditPart(targetEditPart);
            createConReq.setLocation(req.getLocation().getCopy());
            createConReq.getData().setSrcPoint(pt);
            createConReq.getData().setRoutingMode(getDefaultRoutingMode(srcEditPart, linkCreationContext));
            createConReq.getData().setLastPoint(req.getLocation().getCopy());
            if (req instanceof CreateBendedConnectionRequest)
                createConReq.getData().getPath().addAll( ((CreateBendedConnectionRequest)req).getData().getPath());
            
            createConReq.setStartCommand(startCmd);
            if (startCmd != null && targetEditPart != null) {
                finishCom = targetEditPart.getCommand(createConReq);
            }
            break;
        default: return null;
        }
        return new LinkToolActionDescriptor(entry, createConReq, finishCom);
    }

}
