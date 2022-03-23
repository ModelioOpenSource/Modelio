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
package org.modelio.linkeditor.panel.model;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.Node;
import org.modelio.linkeditor.panel.ILinkEditorConfiguration;
import org.modelio.linkeditor.panel.ILinkEditorFilter;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * This class is in charge of building the tree by exploring the model.
 */
@objid ("1bb9b8f2-5e33-11e2-b81d-002564c97630")
class TreeBuilder {
    @objid ("657e314d-3d03-4eb9-8bee-c28201945125")
    private ILinkEditorConfiguration config;

    @objid ("ea5c0e67-4bc9-4638-9fa5-af71c4f17584")
    private List<MClass> linkMetaclasses;

    @objid ("1bbe7b90-5e33-11e2-b81d-002564c97630")
    private Edge createEdge(final BackgroundModel graph, final MObject link, final GraphNode sourceNode, final GraphNode targetNode) {
        Node realFrom = sourceNode;
        Node realTo = targetNode;
        if (sourceNode.outgoing.size() > 0) {
            // If there is at least one edge already
            if (sourceNode.outgoing.getEdge(0).target instanceof EdgeBus) {
                // If it leads to a bus use it
                realFrom = sourceNode.outgoing.getEdge(0).target;
            } else {
                // otherwise create it
                Edge modifiedEdge = sourceNode.outgoing.getEdge(0);
                EdgeBus bus = new EdgeBus();
                Edge busEdge = new Edge(sourceNode, bus);
                modifiedEdge.setSource(bus);
                graph.addNode(bus);
                graph.addEdge(busEdge);
                realFrom = bus;
            }
        }
        if (targetNode.incoming.size() > 0) {
            // If there is at least one edge already
            if (targetNode.incoming.getEdge(0).source instanceof EdgeBus) {
                // If it leads to a bus use it
                realTo = targetNode.incoming.getEdge(0).source;
            } else {
                // otherwise create it
                Edge modifiedEdge = targetNode.incoming.getEdge(0);
                EdgeBus bus = new EdgeBus();
                Edge busEdge = new Edge(bus, targetNode);
                modifiedEdge.setTarget(bus);
                graph.addNode(bus);
                graph.addEdge(busEdge);
                realTo = bus;
            }
        }
        return new Edge(link, realFrom, realTo);
    }

    /**
     * Left tree is about links whose target is 'node' representing 'element'
     */
    @objid ("118a1ed8-6027-4ecd-8b55-5ae0552313bd")
    private void doBuildLeftTree(BackgroundModel graph, GraphNode node, MObject element, int remainingDepth) {
        // Stop recursion when finished
        if (remainingDepth <= 0) {
            return;
        }
        
        // Elements on the left
        // For all the meta model meta classes that represent 'links'
        for (MClass mc : this.linkMetaclasses) {
            // For all the meta model SmDepndencies that can play the role of a target for 'mc typed' links
            for (MDependency dep : mc.getLinkMetaclassTargets()) {
                // If the meta class targeted by the SmDep is compatible with 'element', the link type is qualified for further investigation
                if (element.getMClass().hasBase(dep.getTarget())) {
                    for (MObject link : element.mGet(dep.getSymetric())) {
                        // If the link instance passes the configuration filter
                        if (link.getMClass().equals(mc) && isDisplayed(link)) {
                            addLeftNode(graph, node, link, remainingDepth);
                        }
                    }
                }
            }
        }
        
    }

    /**
     * Right tree is about links whose source is 'node' representing 'element'
     */
    @objid ("495f9e4d-088d-45de-8c90-0818ec3f04d6")
    private void doBuildRightTree(BackgroundModel graph, GraphNode node, MObject element, int remainingDepth) {
        // Stop recursion when finished
        if (remainingDepth <= 0) {
            return;
        }
        
        // Elements on the right
        // For all the meta model meta classes that represent 'links'
        for (MClass mc : this.linkMetaclasses) {
            // For all the meta model SmDepndencies that can play the role of a source for 'mc typed' links
            for (MDependency dep : mc.getLinkMetaclassSources()) {
                // If the meta class targeted by the SmDep is compatible with 'element', the link type is qualified for further investigation
                if (element.getMClass().hasBase(dep.getTarget())) {
                    for (MObject link : element.mGet(dep.getSymetric())) {
                        // If the link instance passes the configuration filter
                        if (link.getMClass().equals(mc) && isDisplayed(link)) {
                            addRightNode(graph, node, link, remainingDepth);
                        }
                    }
                }
            }
        }
        
    }

    /**
     * C'Tor
     */
    @objid ("6a7c54ad-07df-4693-9f28-3e97afec2ef6")
     TreeBuilder(ILinkEditorConfiguration config) {
        this.config = config;
    }

    @objid ("eb7c3a7b-cbc0-4f81-a553-303954ed19c4")
    public void buildGraph(BackgroundModel graph, MObject element) {
        // Get all 'link' metaclasses
        initLinkMetaclasses(element);
        
        // Build central node
        // Set up central node
        GraphNode centralNode = new GraphNode(element);
        centralNode.setCentral(true);
        graph.addNode(centralNode);
        graph.setCenter(centralNode);
        
        // Build left tree
        doBuildLeftTree(graph, centralNode, element, this.config.getLeftDepth());
        
        // Build right tree
        doBuildRightTree(graph, centralNode, element, this.config.getRightDepth());
        
    }

    /**
     * Find and add the node that is the source of link (where link is targeted to 'targetNode').
     * 
     * For the added left node recurse with doBuildLeftTree().
     */
    @objid ("22ad9f1c-8819-4508-b6df-af0e808776c0")
    private void addLeftNode(BackgroundModel graph, GraphNode targetNode, MObject link, int remainingDepth) {
        for (MDependency symDep : link.getMClass().getLinkMetaclassSources()) {
            for (MObject source : link.mGet(symDep)) {
                addLeftNode(graph, targetNode, link, remainingDepth, source);
            }
        }
        
    }

    /**
     * Find and add the node that is the target of link (where link has source from 'sourceNode').
     * 
     * For the added right node recurse with doBuildRightTree().
     */
    @objid ("ab9c84ad-2f57-41dd-843f-b365c2a66eaa")
    private void addRightNode(BackgroundModel graph, GraphNode sourceNode, MObject link, int remainingDepth) {
        for (MDependency symDep : link.getMClass().getLinkMetaclassTargets()) {
            for (MObject target : link.mGet(symDep)) {
                addRightNode(graph, sourceNode, link, remainingDepth, target);
            }
        }
        
    }

    /**
     * Checks the link instance passes the configuration filter.
     */
    @objid ("de3395ad-1eb3-436b-acef-b7732d2cf21b")
    private boolean isDisplayed(MObject link) {
        MClass mc = link.getMClass();
        if (link instanceof ModelElement && !((ModelElement) link).getExtension().isEmpty()) {
            for (Stereotype st : ((ModelElement) link).getExtension()) {
                if (this.config.getLinkFilter().accept(mc, st)) {
                    return true;
                }
            }
        } else {
            if (this.config.getLinkFilter().accept(mc, null)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Collect link metaclasses currently enabled by the {@link ILinkEditorFilter}.
     */
    @objid ("69d70f9f-c8d4-4b7c-8c9f-c42b242de73d")
    private void initLinkMetaclasses(MObject element) {
        this.linkMetaclasses = new ArrayList<>();
        for (MClass mc : element.getMClass().getMetamodel().getRegisteredMClasses()) {
            if (mc.isLinkMetaclass() && this.config.getLinkFilter().isLinkTypeEnabled(mc)) {
                this.linkMetaclasses.add(mc);
            }
        }
        
    }

    @objid ("01167238-6baf-44cc-a271-c285118c9d36")
    private void addLeftNode(BackgroundModel graph, GraphNode targetNode, MObject link, int remainingDepth, MObject source) {
        GraphNode sourceNode = new GraphNode(source);
        Edge newEdge = createEdge(graph, link, sourceNode, targetNode);
        // Add to the graph
        graph.addNode(sourceNode);
        graph.addEdge(newEdge);
        
        // decrement remainingDepth and recurse.
        doBuildLeftTree(graph, sourceNode, source, remainingDepth - 1);
        
    }

    @objid ("ba3102f1-703f-4219-9e5b-ae744725eed2")
    private void addRightNode(BackgroundModel graph, GraphNode sourceNode, MObject link, int remainingDepth, MObject target) {
        GraphNode newNode = new GraphNode(target);
        Edge newEdge = createEdge(graph, link, sourceNode, newNode);
        // Add to the graph
        graph.addNode(newNode);
        graph.addEdge(newEdge);
        // decrement remainingDepth and recurse.
        doBuildRightTree(graph, newNode, target, remainingDepth - 1);
        
    }

}
