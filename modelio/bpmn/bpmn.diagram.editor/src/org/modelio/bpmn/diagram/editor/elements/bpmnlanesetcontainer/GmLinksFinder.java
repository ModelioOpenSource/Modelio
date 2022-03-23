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
package org.modelio.bpmn.diagram.editor.elements.bpmnlanesetcontainer;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;

/**
 * Helper service that finds all Gm links between all given nodes and their children.
 * <p>
 * Links that go from/to a node not included are filtered out.
 */
@objid ("106967a8-0881-422a-bad7-56786432f0eb")
class GmLinksFinder {
    /**
     * @param nodes a collection containing node models
     * @return the set that will be filled with found links
     */
    @objid ("34af130d-895b-44fe-bd0d-c8ec70f7680b")
    public static Set<IGmLinkable> computeAllLinksFor(final Collection<? extends IGmLinkable> nodes) {
        final Set<IGmLinkable> linksToAdd = new HashSet<IGmLinkable>();
        BiConsumer<IGmLinkable, Consumer<IGmLinkable> > transitions = (ep, graphWalker)
                -> walkConnectedLinks(graphWalker, ep, nodes, linksToAdd);
        traverseGraph2(nodes, transitions);
        return linksToAdd;
    }

    @objid ("12e01c53-8d6a-4773-937d-fee1938fd7bd")
    private static void walkConnectedLinks(Consumer<IGmLinkable> graphWalker, IGmLinkable ep, Collection<? extends IGmLinkable> operationSet, Collection<IGmLinkable> linkEditParts) {
        if (ep instanceof GmCompositeNode) {
            for (GmNodeModel child : ((GmCompositeNode)ep).getChildren()) {
                graphWalker.accept(child);
            }
        }
        
        List<IGmLink> links = ep.getStartingLinks();
        for (IGmLink link : links) {
            if (isLinkToInclude(link, operationSet) && linkEditParts.add(link)) {
                graphWalker.accept(link);
            }
        }
        
        links = ep.getEndingLinks();
        for (IGmLink link : links) {
            if (isLinkToInclude(link, operationSet) && linkEditParts.add(link)) {
                graphWalker.accept(link);
            }
        
        }
        
    }

    @objid ("aba728c1-b850-45ee-915e-e852c32905bf")
    private static <A> void traverseGraph2(final Collection<? extends A> roots, BiConsumer<A, Consumer<A>> transitions) {
        // initialize a current roots list from the passed root elements
        final Set<A> traversed = new HashSet<>();
        final Deque<A> queue = new ArrayDeque<>(roots);
        
        // Loop until there is no root nodes
        while (!queue.isEmpty()) {
            A o = queue.poll();
            transitions.accept(o, (A n) -> {
                if (traversed.add(n)) {
                    queue.add(n);
                }
            });
        
        }
        
    }

    @objid ("21511320-7cef-4888-bf45-3cacc2c6091d")
    private static boolean isLinkToInclude(IGmLink link, final Collection<? extends IGmLinkable> operationSet) {
        if (!operationSet.contains(link)) {
            IGmLink linkEditPart = link;
            IGmLinkable linkSource = linkEditPart.getFrom();
            IGmLinkable linkTarget = linkEditPart.getTo();
            boolean sourceInSet = linkSource == null || isAncestorContainedIn(operationSet,
                    linkSource);
            boolean targetInSet = linkTarget == null || isAncestorContainedIn(operationSet,
                    linkTarget);
        
            if (sourceInSet && targetInSet) {
                return true;
            }
        }
        return false;
    }

    @objid ("16a12977-0faf-455a-861d-ac65eaabce9e")
    private static boolean isAncestorContainedIn(Collection<? extends IGmLinkable> operationSet, IGmLinkable linkTarget) {
        if (operationSet.contains(linkTarget))
            return true;
        
        if (linkTarget instanceof GmModel) {
            GmModel parent = ((GmModel) linkTarget).getParent();
            if (parent instanceof IGmLinkable)
                return isAncestorContainedIn(operationSet, (IGmLinkable) parent );
        }
        return false;
    }

}
