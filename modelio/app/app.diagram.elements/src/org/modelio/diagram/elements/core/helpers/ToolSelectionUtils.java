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
package org.modelio.diagram.elements.core.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.tools.ToolUtilities;
import org.modelio.diagram.elements.core.link.LinkEditPart;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkObject;
import org.modelio.diagram.elements.core.model.IGmObject;

/**
 * Utilities to compute edit parts to move with other ones, connections particularly.
 * 
 * @since 5.0.2
 */
@objid ("de19a0ae-d695-4df8-8564-e1b5c72683aa")
public class ToolSelectionUtils {
    /**
     * Add to the operation set all links:
     * <ul>
     * <li>Understanding the request</li>
     * <li>Whose end(s) are in the operation set or have an ancestor in it</li>
     * </ul>
     * @param editParts the bag of edit parts to complete.
     * @param request a request.
     * @param bothEndInRequest indicates if both source and target of the link must be part of the operation set or if one is enough.
     */
    @objid ("1dd8997e-cf75-4dad-a4a1-4bdc06de7211")
    public static void addAllLinksFor(final Collection<GraphicalEditPart> editParts, final GroupRequest request, boolean bothEndInRequest) {
        ILinkTester linkTester = bothEndInRequest ? ToolSelectionUtils::bothEndsInRequest : ToolSelectionUtils::oneEndInRequest;
        
        Set<GraphicalEditPart> linksToAdd = new HashSet<>();
        do {
            linksToAdd.clear();
            ToolSelectionUtils.computeAllLinksFor(editParts, request, linksToAdd, linkTester);
            editParts.addAll(linksToAdd);
            // Do it again if some links were added (this might trigger some new links, because of links on links!).
        } while (!linksToAdd.isEmpty());
        
    }

    /**
     * Returns the transitive child edit part set of the given parent <code>GraphicalEditPart</code>.
     * <p>
     * Prunes embedded diagram edit parts from the result.
     * @param editPart the parent graphical edit part for which to retrieve the transitive child edit part set.
     * @param transitiveChildren the set to fill with the transitive child edit parts
     * @return the transitive child edit part set
     */
    @objid ("eade2183-e87c-47d4-b1e0-aa70f2eccc34")
    public static Set<GraphicalEditPart> getAllChildrenInDiagram(GraphicalEditPart editPart, Set<GraphicalEditPart> transitiveChildren) {
        List<GraphicalEditPart> children = editPart.getChildren();
        transitiveChildren.addAll(children);
        for (GraphicalEditPart child : children) {
            if (!(child.getModel() instanceof IGmDiagram)) {
                getAllChildrenInDiagram(child, transitiveChildren);
            }
        }
        return transitiveChildren;
    }

    /**
     * @param linkEditPart edit part of a link.
     * @param request a request.
     * @return <code>true</code> if the link's source <b>and</b> target are part of the operation set, including its ancestors in the search.
     */
    @objid ("690f0f25-907d-4c53-9a9d-8da6b5417531")
    public static boolean bothEndsInRequest(ConnectionEditPart linkEditPart, GroupRequest request) {
        return sourceInRequest(linkEditPart, request) && targetInRequest(linkEditPart, request);
    }

    /**
     * @param linkEditPart edit part of a link.
     * @param editParts a bunch of edit parts.
     * @return <code>true</code> if the link's source <b>and</b> target are part of the operation set, including its ancestors in the search.
     */
    @objid ("98e2c71d-cb1f-4ee0-9297-e3550c79d0fb")
    public static boolean bothEndsInRequest(ConnectionEditPart linkEditPart, Collection<GraphicalEditPart> editParts) {
        EditPart linkSource = linkEditPart.getSource();
        EditPart linkTarget = linkEditPart.getTarget();
        return isEditPartOrAncestorContainedIn(linkSource, editParts) && isEditPartOrAncestorContainedIn(linkTarget, editParts);
    }

    /**
     * @param linkEditPart edit part of a link.
     * @param request a request.
     * @return <code>true</code> if the link's source <b>or</b> target is part of the operation set, including its ancestors in the search.
     */
    @objid ("489022a8-ffd1-4d08-a50b-8ae15e37d963")
    public static boolean oneEndInRequest(ConnectionEditPart linkEditPart, GroupRequest request) {
        return oneEndInRequest(linkEditPart, getEditParts(request));
    }

    /**
     * @param linkEditPart edit part of a link.
     * @param editParts a bunch of edit parts.
     * @return <code>true</code> if the link's source <b>or</b> target is part of the operation set, including its ancestors in the search.
     */
    @objid ("47e7f8ee-1c34-49de-b0c2-06b1566b5f3c")
    public static boolean oneEndInRequest(ConnectionEditPart linkEditPart, Collection<GraphicalEditPart> editParts) {
        EditPart linkSource = linkEditPart.getSource();
        if (isEditPartOrAncestorContainedIn(linkSource, editParts)) {
            return true;
        }
        
        EditPart linkTarget = linkEditPart.getTarget();
        return isEditPartOrAncestorContainedIn(linkTarget, editParts);
    }

    /**
     * @param linkEditPart edit part of a link.
     * @param request a request.
     * @return <code>true</code> if the link's source is part of the operation set, including its ancestors in the search.
     */
    @objid ("2f0d693d-0bd4-4827-a58f-525f304fc6c0")
    public static boolean sourceInRequest(ConnectionEditPart linkEditPart, GroupRequest request) {
        EditPart linkSource = linkEditPart.getSource();
        return isEditPartOrAncestorContainedIn(linkSource, getEditParts(request));
    }

    /**
     * @return all the EditParts making this Request, including its parent Requests.
     */
    @objid ("d14f4b1d-04b8-4a2b-870a-d8ee9fadd3b6")
    private static Collection<GraphicalEditPart> getEditParts(GroupRequest request) {
        Collection<GraphicalEditPart> editParts = RequestHelper.getSharedEditParts(request);
        if (editParts == null) {
            editParts = new HashSet<>();
        }
        
        HashSet<GraphicalEditPart> ret = new HashSet<>(editParts);
        ret.addAll(request.getEditParts());
        return ret;
    }

    /**
     * @param linkEditPart edit part of a link.
     * @param request a request.
     * @return <code>true</code> if the link's target is part of the operation set, including its ancestors in the search.
     */
    @objid ("24e593a5-9c8e-4e37-86f3-2e4f4d2d599c")
    public static boolean targetInRequest(ConnectionEditPart linkEditPart, GroupRequest request) {
        EditPart linkTarget = linkEditPart.getTarget();
        return isEditPartOrAncestorContainedIn(linkTarget, getEditParts(request));
    }

    /**
     * Same as {@link #computeAllLinksFor(Collection, GroupRequest, Set, ILinkTester)} but by looking at all links in the diagrams
     * instead of scanning node children recursively.
     * @param editParts the edited edit pars
     * @param request a request the link connections must understand
     * @param linksToAdd the result
     * @param linkTester a filter to accept the connection edit part
     */
    @objid ("2bcecf45-494f-424c-8e1b-e880ca9453d0")
    private static void computeAllLinksInDiagramFor(final Collection<GraphicalEditPart> editParts, final GroupRequest request, final Set<GraphicalEditPart> linksToAdd, ILinkTester linkTester) {
        if (editParts.isEmpty())
            return;
        
        Map<Object,EditPart> epFactory = null;
        Collection<IGmDiagram> rootDiagrams = new ArrayList<>(1);
        
        for (GraphicalEditPart ep : editParts) {
            if (epFactory == null) {
                epFactory = ep.getViewer().getEditPartRegistry();
            }
        
            IGmObject gm = (IGmObject) ep.getModel();
            if (!rootDiagrams.contains(gm.getDiagram()))
                rootDiagrams.add(gm.getDiagram());
        }
        
        for (IGmDiagram gmDiagram : rootDiagrams) {
            for (IGmLinkObject gmLink : gmDiagram.getAllLinks()) {
                if (rootDiagrams.contains(gmLink.getDiagram())) {
                    GraphicalEditPart linkEp = (GraphicalEditPart) epFactory.get(gmLink);
                    if (isLinkToInclude(linkEp, request, editParts, linkTester)) {
                        linksToAdd.add(linkEp);
                    }
                }
            }
        }
        
    }

    @objid ("8090ccea-1dec-11e2-8cad-001ec947c8cc")
    private static void computeAllLinksFor(final Collection<GraphicalEditPart> editParts, final GroupRequest request, final Set<GraphicalEditPart> linksToAdd, ILinkTester linkTester) {
        Set<GraphicalEditPart> transitiveChildren = new HashSet<>(editParts);
        
        for (GraphicalEditPart editPart : editParts) {
            getAllChildrenInDiagram(editPart, transitiveChildren);
        }
        
        if (transitiveChildren.stream().anyMatch(ep -> ep.getModel() instanceof IGmDiagram)) {
            // There are embedded diagrams, change strategy
            computeAllLinksInDiagramFor(editParts, request, linksToAdd, linkTester);
            // Fast exit
            return;
        }
        
        for (GraphicalEditPart child : transitiveChildren) {
            List<GraphicalEditPart> links = child.getSourceConnections();
            for (GraphicalEditPart link : links) {
                if (isLinkToInclude(link, request, editParts, linkTester)) {
                    linksToAdd.add(link);
                }
            }
        
            links = child.getTargetConnections();
            for (GraphicalEditPart link : links) {
                if (isLinkToInclude(link, request, editParts, linkTester)) {
                    linksToAdd.add(link);
                }
        
            }
        
        }
        
    }

    @objid ("aec88b9c-1ad3-431f-b5e2-50900fded3d7")
    private static boolean isEditPartOrAncestorContainedIn(EditPart ep, Collection<GraphicalEditPart> editParts) {
        return ep == null || editParts.contains(ep) || ToolUtilities.isAncestorContainedIn(editParts, ep);
    }

    @objid ("9bcaea2f-ed47-4568-9d2d-5e4764c83a18")
    private static boolean isLinkToInclude(GraphicalEditPart link, final GroupRequest request, final Collection<GraphicalEditPart> editParts, ILinkTester linkTester) {
        if (link instanceof LinkEditPart && !editParts.contains(link)) {
            LinkEditPart linkEditPart = (LinkEditPart) link;
            return linkEditPart.understandsRequest(request) && linkTester.isLinkToInclude(linkEditPart, editParts);
        }
        return false;
    }

    @objid ("1f71d2e5-7220-4095-a307-f74114f61d81")
    private interface ILinkTester {
        @objid ("74031cc5-6c93-49b8-88a4-b3e0c627bc3c")
        boolean isLinkToInclude(LinkEditPart linkEditPart, final Collection<GraphicalEditPart> editParts);

    }

}
