/* 
 * Copyright 2013-2019 Modeliosoft
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

package org.modelio.diagram.elements.drawings.core.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.drawings.core.GmDrawing;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.IGmNodeDrawing;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;

/**
 * Graphic model representing a node that does not represent a model element.
 */
@objid ("8076930d-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmNodeDrawing extends GmDrawing implements IGmNodeDrawing {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("8076930f-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("80769312-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Optional node label content.
     */
    @objid ("e1d5b06d-76cc-44d5-840b-846f22a02846")
    private String label = "";

    @objid ("b4fdfa32-3d9d-4f12-b879-26d7128556dc")
    private IGmDrawingLayer parent;

    @objid ("92693cee-ce0e-4dda-b4bb-dae29e8ee31a")
    private final transient List<IGmDrawingLink> startingLinks = new ArrayList<>();

    @objid ("ede10576-5d94-4a1a-94c1-ca3d4a601550")
    private final transient List<IGmDrawingLink> endingLinks = new ArrayList<>();

    /**
     * The {@link IGmDrawingLink} owning this node.
     * <p>
     * A node may be owned either by a {@link GmNodeDrawing} or a {@link IGmDrawingLink} (as a link extension) but not both.
     */
    @objid ("0280fd0b-7cc0-4305-867f-b282ccd8e7fa")
    private IGmDrawingLink parentLink = null;

    @objid ("80769314-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNodeDrawing.");
        switch (readVersion) {
            case 0: {
                read_0(in);
                break;
            }
            default: {
                assert (false) : "version number not covered!";
                // reading as last handled version: 0
                read_0(in);
                break;
            }
        }
    }

    @objid ("80769318-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        /*if (GmNodeDrawing.MINOR_VERSION != 0) {
            out.writeProperty("GmNodeDrawing." + MINOR_VERSION_PROPERTY, Integer.valueOf(GmNodeDrawing.MINOR_VERSION));
        }*/
        
        out.writeProperty("label", this.label);
    }

    @objid ("8076931c-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.label = (String) in.readProperty("label");
    }

    @objid ("8076931f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * Default constructor.
     * 
     * @param diagram the owner diagram.
     * @param identifier the drawing identifier, must be unique in the diagram.
     */
    @objid ("05b0526a-7ec7-424a-adfa-860148d59fe0")
    public GmNodeDrawing(IGmDiagram diagram, String identifier) {
        super(diagram, identifier);
    }

    /**
     * Deserialization constructor.
     */
    @objid ("d7824371-a25c-45a8-a7b6-6231cc5294e3")
    public GmNodeDrawing() {
        super();
    }

    @objid ("bd7f575a-2abb-4230-b05c-5f7d12207a3f")
    @Override
    public void delete() {
        for (IGmDrawingLink l : new ArrayList<>(this.startingLinks)) {
            l.delete();
        }
        for (IGmDrawingLink l : new ArrayList<>(this.endingLinks)) {
            l.delete();
        }
        
        
        this.parent.removeChild(this);
        
        super.delete();
    }

    /**
     * Set the parent layer
     * 
     * @param gmDrawingLayer the parent layer
     */
    @objid ("f01a67cd-6b1d-4c60-b03c-ef849d7695e3")
    @Override
    public void setParent(IGmDrawingLayer gmDrawingLayer) {
        this.parent = gmDrawingLayer;
        
        updateDiagram();
    }

    /**
     * @return the node label
     */
    @objid ("16c60db3-c16f-42ec-ace7-611eecf980b5")
    @Override
    public String getLabel() {
        return this.label;
    }

    /**
     * @param label the node label
     */
    @objid ("f0bb86fd-85e9-482b-98b5-d189d3b8dcd1")
    @Override
    public void setLabel(String label) {
        if (! Objects.equals(label, this.label)) {
            String oldLabel = this.label;
            this.label = label;
        
            firePropertyChange(PROPERTY_LABEL, oldLabel, this.label);
        }
    }

    @objid ("d06b2d81-31b4-43da-bf6e-639307d192c9")
    public void setParentLink(IGmDrawingLink parentLink) {
        if (this.parent != null) {
            throw new IllegalStateException("The node has already " + this.parent + " as parent node");
        }
        this.parentLink = parentLink;
        
        updateDiagram();
    }

    /**
     * Add a link going to this element.
     * 
     * @param link the ongoing link.
     */
    @objid ("046a51f5-1ccc-4578-849f-3e893ec5cc7c")
    @Override
    public void addEndingDrawingLink(IGmDrawingLink link) {
        this.endingLinks.add(link);
        link.setTo(this);
        firePropertyChange(IGmObject.PROPERTY_LINK_TARGET, null, link);
    }

    /**
     * Add a link starting from this node.
     * 
     * @param link The starting link.
     */
    @objid ("1429c94b-660a-43c2-a6f9-2670a496ddb3")
    @Override
    public void addStartingDrawingLink(IGmDrawingLink link) {
        this.startingLinks.add(link);
        link.setFrom(this);
        firePropertyChange(IGmObject.PROPERTY_LINK_SOURCE, null, link);
    }

    /**
     * Get the links going to this node.
     * 
     * @return the ongoing links.
     */
    @objid ("54626cda-7325-4988-84cb-41e98c0f34bb")
    @Override
    public List<IGmDrawingLink> getEndingDrawingLinks() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.endingLinks;
    }

    /**
     * Remove a link going to this node.
     * 
     * @param gmLink the link to remove.
     */
    @objid ("f075e689-6b7b-4e5b-bc3f-861c937ce3cc")
    @Override
    public void removeEndingDrawingLink(IGmDrawingLink gmLink) {
        this.endingLinks.remove(gmLink);
        gmLink.setTo(null);
        firePropertyChange(IGmObject.PROPERTY_LINK_TARGET, gmLink, null);
    }

    /**
     * Remove a link starting from this node.
     * 
     * @param gmLink the link to remove.
     */
    @objid ("d833688f-b721-461c-8d98-8d068d76a471")
    @Override
    public void removeStartingDrawingLink(IGmDrawingLink gmLink) {
        this.startingLinks.remove(gmLink);
        gmLink.setFrom(null);
        firePropertyChange(IGmObject.PROPERTY_LINK_SOURCE, gmLink, null);
    }

    /**
     * @return the layer on which this node is.
     */
    @objid ("6358180b-ece7-491d-8090-894f73d5b1c6")
    @Override
    public final IGmDrawingLayer getLayer() {
        return this.parent;
    }

    @objid ("a13078e7-54d7-40c7-ad80-c18cbd3ff84e")
    @Override
    public java.util.List<IGmDrawingLink> getStartingDrawingLinks() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.startingLinks;
    }

    @objid ("a65b6a60-2885-4b2a-bdb4-4e4333022d64")
    @Override
    public boolean updateDiagram() {
        if (getLayer() == null) {
            return false;
        }
        
        IGmDiagram oldDiagram = getDiagram();
        IGmDiagram newDiagram = getLayer().getDiagram();
        
        if (newDiagram==null || oldDiagram==newDiagram) {
            return false;
        }
        
        // Change diagram
        moveToDiagram(newDiagram);
        
        // Propagate to links
        if (this.startingLinks != null ) {
            this.startingLinks.forEach(r -> {
                r.updateDiagram();
            });
        }
        if (this.endingLinks != null) {
            this.endingLinks.forEach(r -> {
                r.updateDiagram();
            });
        }
        return true;
    }

}
