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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.graph.CompoundDirectedGraph;
import org.eclipse.draw2d.graph.Edge;
import org.eclipse.draw2d.graph.Node;
import org.modelio.linkeditor.panel.ILinkEditorConfiguration;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Model of the background of the Link Editor.
 */
@objid ("1b8a1da3-5e33-11e2-b81d-002564c97630")
public class BackgroundModel extends CompoundDirectedGraph {
    @objid ("1b8a1da8-5e33-11e2-b81d-002564c97630")
    public static final String CONTENT = "Content";

    @objid ("1b8a1da7-5e33-11e2-b81d-002564c97630")
    private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

    @objid ("1b8ee013-5e33-11e2-b81d-002564c97630")
    private GraphNode center;

    @objid ("11182580-11a4-48b1-be45-a4211ba708de")
    private ILinkEditorConfiguration config;

    @objid ("7f309335-8efb-46f7-8ed9-5bd9cde33bae")
    private MObject input;

    /**
     * C'tor.
     */
    @objid ("1b8ee014-5e33-11e2-b81d-002564c97630")
    public  BackgroundModel() {
        
    }

    @objid ("1b8ee017-5e33-11e2-b81d-002564c97630")
    public void fireContentChanged() {
        this.listeners.firePropertyChange(new PropertyChangeEvent(this, BackgroundModel.CONTENT, null, this.nodes));
    }

    @objid ("1b8ee019-5e33-11e2-b81d-002564c97630")
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        // Making sure a listener is only added once.
        this.listeners.removePropertyChangeListener(listener);
        this.listeners.addPropertyChangeListener(listener);
        
    }

    @objid ("1b8ee01d-5e33-11e2-b81d-002564c97630")
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.listeners.removePropertyChangeListener(listener);
    }

    /**
     * Adds the given node to the graph. Does not add its incoming and outgoing edges.
     * @param node the node to add to the graph.
     */
    @objid ("1b8ee021-5e33-11e2-b81d-002564c97630")
    public void addNode(final Node node) {
        // Make sure a node is not twice in the list.
        this.nodes.remove(node);
        this.nodes.add(node);
        
    }

    /**
     * Adds the given edge to the graph. Does not add its source and target nodes NOR its virtual nodes (if any).
     * @param edge the edge to add to the graph.
     */
    @objid ("1b8ee031-5e33-11e2-b81d-002564c97630")
    public void addEdge(final Edge edge) {
        // Make sure the edge is not twice in the list.
        this.edges.remove(edge);
        this.edges.add(edge);
        
    }

    @objid ("1b8ee041-5e33-11e2-b81d-002564c97630")
    public GraphNode getCenter() {
        return this.center;
    }

    @objid ("1b8ee045-5e33-11e2-b81d-002564c97630")
    public void setCenter(final GraphNode center) {
        this.center = center;
    }

    @objid ("36861806-5ce0-4806-9536-bf4b587ed4ad")
    public ILinkEditorConfiguration getConfiguration() {
        return this.config;
    }

    @objid ("d0053789-ce8b-494f-b34c-6671bcf10c2a")
    public void setConfiguration(ILinkEditorConfiguration config) {
        this.config = config;
        
        rebuild();
        
    }

    @objid ("ff058861-250c-457a-813d-0c48d1a0fc89")
    public void setInput(final MObject element) {
        this.input = element;
        rebuild();
        
    }

    @objid ("c483f8cb-a596-45e9-8334-c3f68d8dd1e6")
    public MObject getInput() {
        return this.input;
    }

    /**
     * Rebuild the graph .
     */
    @objid ("057b1cf5-cf70-49f1-9865-775f8351cb7f")
    public void rebuild() {
        this.nodes.clear();
        this.edges.clear();
        
        if (this.input != null && this.input.isValid() && this.config != null) {
            TreeBuilder builder = new TreeBuilder(this.config);
            builder.buildGraph(this, this.input);
        } else {
            setCenter(null);
        }
        
    }

}
