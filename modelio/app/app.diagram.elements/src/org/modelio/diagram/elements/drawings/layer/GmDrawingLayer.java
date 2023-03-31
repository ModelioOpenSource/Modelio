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
package org.modelio.diagram.elements.drawings.layer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLayer;
import org.modelio.diagram.elements.drawings.core.IGmDrawingLink;
import org.modelio.diagram.elements.drawings.core.IGmNodeDrawing;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.Style;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Free drawings layer.
 * <p>
 * There are currently 3 layers:<ul>
 * <li>{@link #LAYER_ID_BACKGROUND} : the background layer
 * <li>{@link #LAYER_ID_MAIN} : the main element layers, there is currently no GmGraphic for it
 * <li>{@link #LAYER_ID_TOP} : the currently only foreground layer
 * </ul>
 */
@objid ("54dca595-4207-4687-992e-a019824d99c9")
public class GmDrawingLayer extends GmAbstractObject implements IGmDrawingLayer {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("a548c665-605f-4a8e-95c3-6f48002e261f")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current minor version of this Gm. Defaults to 0.
     */
    @objid ("13ae51ae-b7f2-49e7-b0f9-ef4dab97e4be")
    private static final int MINOR_VERSION = 0;

    @objid ("0b3c6c51-7193-4d51-bd15-4b43dba354a1")
    private static final String W_LAYER_CHILDREN = "Children";

    @objid ("53ae7e7b-e3a8-4b51-b5fb-fd8ea6f348d6")
    private static final String W_RELATED_REF = "relatedRef";

    @objid ("227750a1-6246-4e02-8ecf-3c25b81f7f66")
    private static final String W_DRAWING_ID = "id";

    /**
     * Identifier of the main layer
     */
    @objid ("26386191-8b2c-43cc-8151-635617b877e4")
    public static final String LAYER_ID_MAIN = "DG.LAYER_MAIN";

    /**
     * Identifier of the background drawing layer
     */
    @objid ("56c9100f-c031-4678-b47a-567ed0f8c326")
    public static final String LAYER_ID_BACKGROUND = "DG.LAYER_BACKGROUND";

    /**
     * Identifier of the foreground drawing layer.
     * <p>
     * May become useless the day many layers can be created at will.
     */
    @objid ("661e84a7-0183-46fc-8717-1b95ec72e1c3")
    public static final String LAYER_ID_TOP = "DG.LAYER_TOP";

    /**
     * Identifier of the layer.
     * <p>
     * Some special layer identifiers:<ul>
     * <li>{@link #LAYER_ID_MAIN} : the main element layers, there is currently no GmGraphic for it
     * <li>{@link #LAYER_ID_BACKGROUND} : the background layer
     * <li>{@link #LAYER_ID_TOP} : the currently only foreground layer
     * </ul>
     */
    @objid ("c4f04f50-f349-48ef-a3ae-9f8eeb133e91")
    private String identifier;

    @objid ("cc42a926-c1f8-47bf-82f7-eccdfa01b22c")
    private final List<IGmNodeDrawing> nodes = new ArrayList<>();

    @objid ("20f67336-2199-4573-90fe-214e7cb8af6f")
    private final List<IGmDrawingLink> startingLinks = new ArrayList<>();

    @objid ("1d4eeeb2-9e09-4a62-9bf9-e4aa4304eadc")
    private final List<IGmDrawingLink> endingLinks = new ArrayList<>();

    @objid ("a1ecf747-93c7-4389-b299-6ddbd5a132a7")
    private MRef relatedRef;

    @objid ("0a966955-25f9-49a8-99e7-2f1bc7d18154")
    private MObject relatedEl;

    /**
     * Deserialization only constructor.
     */
    @objid ("70d909bd-f7e4-48fa-b14f-c5c3d91f0126")
    public  GmDrawingLayer() {
        super();
    }

    /**
     * Default constructor.
     * @see #LAYER_ID_BACKGROUND
     * @see #LAYER_ID_TOP
     * @param diagram the parent diagram
     * @param relatedRef model object where the drawings will be saved in the future.
     * @param layerIdent the layer identifier.
     */
    @objid ("18b95a3b-54d8-42eb-9257-6b9b72fe54b5")
    public  GmDrawingLayer(IGmDiagram diagram, MRef relatedRef, String layerIdent) {
        super(diagram);
        this.relatedRef = relatedRef;
        this.identifier = layerIdent;
        
        init();
        
    }

    @objid ("f474430f-88dd-4dbb-8ce1-be34ea733204")
    @Override
    public List<StyleKey> getStyleKeys() {
        return Collections.emptyList();
    }

    @objid ("ea753d86-26cb-4716-a890-2236730ac86f")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return null;
    }

    /**
     * Add a child to the children list and fires a {@link IGmObject#PROPERTY_CHILDREN} property change event.
     * @param child The node to add
     */
    @objid ("feea2aa4-42f5-4fda-aa5e-c06517299a37")
    @Override
    public void addChild(IGmNodeDrawing child) {
        this.nodes.add(child);
        child.setParent(this);
        
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, child);
        
    }

    /**
     * Add a child to the children list at the given index and fires a {@link IGmObject#PROPERTY_CHILDREN} property change event.
     * @param child The node to add
     * @param index the index where the child will be added.
     */
    @objid ("1543c01d-f562-4e0b-a447-88e09b5d0937")
    @Override
    public void addChild(final IGmNodeDrawing child, final int index) {
        this.nodes.add(index, child);
        child.setParent(this);
        
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, child);
        
    }

    /**
     * Remove a child from the children list and fires a {@link IGmObject#PROPERTY_CHILDREN} property change event.
     * @param child The node to remove
     */
    @objid ("f5de75c2-3c4f-448e-9ec5-9cacbf236e44")
    @Override
    public void removeChild(IGmNodeDrawing child) {
        if (this.nodes.remove(child)) {
            firePropertyChange(IGmObject.PROPERTY_CHILDREN, child, null);
        
            child.setParent(null);
        } else {
            assert (false) : child + " is not owned by this layer";
        }
        
    }

    @objid ("ac5d6c12-5ffa-453e-a896-c645890378e0")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.relatedRef = (MRef) in.readProperty(W_RELATED_REF);
        this.identifier = (String) in.readProperty(W_DRAWING_ID);
        if (this.identifier == null) {
            this.identifier = UUID.randomUUID().toString();
        }
        
        init();
        
        final List<Object> listProperty = in.readListProperty(W_LAYER_CHILDREN);
        for (Object c : listProperty) {
            final IGmNodeDrawing childNode = (IGmNodeDrawing) c;
            this.nodes.add(childNode);
            childNode.setParent(this);
        }
        
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, this.nodes);
        
    }

    @objid ("1740fe3a-a4e6-4ed3-9578-3c21bd4b356d")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    /**
     * @return the layer nodes.
     */
    @objid ("15dee3fa-1f32-4995-a8c4-96d2d4809b1b")
    @Override
    public List<IGmNodeDrawing> getNodes() {
        return new ArrayList<>(this.nodes);
    }

    @objid ("4186de8e-926a-4d11-a21e-0acf12998a28")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        out.writeProperty(W_RELATED_REF, this.relatedRef);
        out.writeProperty(W_DRAWING_ID, this.identifier);
        out.writeProperty(W_LAYER_CHILDREN, getNodes());
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmDrawingLayer.", MINOR_VERSION);
        
    }

    @objid ("52778f17-d38f-4612-a7c7-2756cdbedbf4")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmDrawingLayer.");
        switch (readVersion) {
        case 0: 
            read_0(in);
            break;
        
        default: 
            assert (false) : "version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        
    }

    /**
     * Move the given child element to the given position.
     * <p>
     * The element at the given position and all subsequent elements are shifted after the moved element.
     * @param child the child node to move
     * @param index the new index. If -1 the element is placed at the end.
     */
    @objid ("0adfaecf-8655-4507-a0a8-c8b7e5f174dd")
    public final void moveChild(IGmNodeDrawing child, int index) {
        int oldIndex = this.nodes.indexOf(child);
        
        if (oldIndex == -1) {
            throw new IllegalArgumentException("The element is not in the children list");
        }
        
        // If child already at asked position do nothing
        if (index == oldIndex) {
            return;
        }
        
        // If child already at asked last position do nothing
        if (index == -1 && oldIndex == this.nodes.size() - 1) {
            return;
        }
        
        this.nodes.remove(child);
        
        if (index == -1) {
            // Add to the end
            this.nodes.add(child);
        } else if (index > oldIndex) {
            // Child moved after old position,
            // Fix the index after removal.
            this.nodes.add(index - 1, child);
        } else {
            // Child moved before old position
            this.nodes.add(index, child);
        }
        
        firePropertyChange(IGmObject.PROPERTY_CHILDREN, null, child);
        
    }

    /**
     * Delete the element from the diagram.
     * <p>
     * Delete all its children before deleting itself.
     */
    @objid ("e989d1c5-d280-4601-85ff-f5d35e53ab52")
    @Override
    public void delete() {
        final IGmDiagram diagram = getDiagram();
        
        for (IGmDrawingLink l : new ArrayList<>(this.startingLinks)) {
            l.delete();
        }
        for (IGmDrawingLink l : new ArrayList<>(this.endingLinks)) {
            l.delete();
        }
        
        /*for (IGmDrawingLink l : new ArrayList<>(this.links)) {
            l.delete();
        }*/
        
        // List children from the end to avoid reordering of the other GMs
        for (int i = this.nodes.size() - 1; i >= 0; i--) {
            IGmNodeDrawing child = this.nodes.get(i);
            child.delete();
        
            // When several elements have been deleted consecutively, fix the next index
            if (i > this.nodes.size()) {
                i = this.nodes.size();
            }
        }
        
        assert (this.nodes.isEmpty()) : "All children should have been deleted";
        
        diagram.removeLayer(this);
        
        diagram.removeGraphicModel(this);
        
        super.delete();
        
    }

    /**
     * Add a link going to this element.
     * @param link the ongoing link.
     */
    @objid ("d61c88c9-3675-4f8f-9d88-674041fa3d8b")
    @Override
    public void addEndingDrawingLink(IGmDrawingLink link) {
        this.endingLinks.add(link);
        link.setTo(this);
        firePropertyChange(IGmObject.PROPERTY_LINK_TARGET, null, link);
        
    }

    /**
     * Add a link starting from this node.
     * @param link The starting link.
     */
    @objid ("9733b2dc-1b33-4263-a3ab-625863d9ea1d")
    @Override
    public void addStartingDrawingLink(IGmDrawingLink link) {
        this.startingLinks.add(link);
        link.setFrom(this);
        firePropertyChange(IGmObject.PROPERTY_LINK_SOURCE, null, link);
        
    }

    /**
     * Get the links going to this node.
     * @return the ongoing links.
     */
    @objid ("721d725f-d3a9-440a-9a72-b30d57d1a398")
    @Override
    public List<IGmDrawingLink> getEndingDrawingLinks() {
        // Automatically generated method. Please delete this comment before
        // entering specific code.
        return this.endingLinks;
    }

    /**
     * Remove a link going to this node.
     * @param gmLink the link to remove.
     */
    @objid ("f3033955-fe45-4af3-8973-36c120cb0f54")
    @Override
    public void removeEndingDrawingLink(IGmDrawingLink gmLink) {
        this.endingLinks.remove(gmLink);
        gmLink.setTo(null);
        firePropertyChange(IGmObject.PROPERTY_LINK_TARGET, gmLink, null);
        
    }

    /**
     * Remove a link starting from this node.
     * @param gmLink the link to remove.
     */
    @objid ("3a6b12e0-614c-4111-8696-8ae099042c1f")
    @Override
    public void removeStartingDrawingLink(IGmDrawingLink gmLink) {
        this.startingLinks.remove(gmLink);
        gmLink.setFrom(null);
        firePropertyChange(IGmObject.PROPERTY_LINK_SOURCE, gmLink, null);
        
    }

    @objid ("baa7fb81-6673-4d9b-b5a3-8bda93419609")
    @Override
    public java.util.List<IGmDrawingLink> getStartingDrawingLinks() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.startingLinks;
    }

    @objid ("252d0a2a-0d75-4740-8f68-976b0c1a26bb")
    @Override
    public IGmDrawingLayer getLayer() {
        return this;
    }

    /**
     * <p>
     * Initialize the object.
     * </p>
     * <p>
     * Must be called before usage by and only by:
     * <ul>
     * <li>The {@link #GmDrawingLayer(GmAbstractDiagram, MRef, String)} constructor (but NOT by the parameter less constructor).
     * <li>and the {@link #read(IDiagramReader)} method
     * </ul>
     * </p>
     * <p>
     * The same method may exist in subclasses. In this case:
     * <ul>
     * <li>the child <em>init()</em> method must be private too,
     * <li>it must <strong>never</strong> call <em>super.init()</em>
     * <li>it must be called by the above 2 methods. they must be created if absent.
     * </ul>
     * </p>
     */
    @objid ("60a4d5bc-9876-4b55-a0a3-095ca1d5c7c7")
    private void init() {
        this.relatedEl = getDiagram().getModelManager().getModelServices().findByRef(this.relatedRef);
        
        if (getDiagram() != null) {
            getDiagram().addGraphicModel(this);
        }
        
    }

    @objid ("eb0b4448-124c-4a70-82b2-6ad54937c15b")
    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @objid ("c0cd3d76-6622-4fa6-b4ca-b2a6b86fbc9c")
    @Override
    public String getGhostLabel() {
        return this.identifier;
    }

    @objid ("77f1ed6d-f116-40c5-b40b-7c9320799025")
    @Override
    public String getGhostMetaclass() {
        return this.relatedRef.mc;
    }

    @objid ("596358f0-d618-48d6-aa0d-eb4b4a2885dc")
    @Override
    public String getGhostId() {
        return this.relatedRef.toString();
    }

    @objid ("8cf63aaa-4f32-4ca2-be8b-309e03a7af55")
    @Override
    public MObject getRelatedElement() {
        return this.relatedEl;
    }

    @objid ("11469ac6-8eda-45bb-b2e6-3bc14fb74a29")
    @Override
    public MObject getRepresentedElement() {
        if (Objects.equals(this.relatedEl, getDiagram().getRelatedElement())) {
            return null;
        }
        return this.relatedEl;
    }

    @objid ("ccc580cd-b8b9-43bf-8805-309dc7858d36")
    @Override
    public MRef getRepresentedRef() {
        if (Objects.equals(this.relatedRef, getDiagram().getRepresentedRef())) {
            return null;
        }
        return this.relatedRef;
    }

    @objid ("ff29096d-542e-403b-915c-158fa38d1e22")
    @Override
    protected IStyle createStyle(IGmDiagram aDiagram) {
        return new Style(aDiagram.getPersistedStyle());
    }

    @objid ("ae9283c5-f230-4130-a9b5-3f4876bd3693")
    @Override
    public boolean updateDiagram() {
        // A layer cannot move
        return false;
    }

    @objid ("d9a3ef14-68e4-45ef-9621-5e0a738fbe91")
    @Override
    public final MRef getHyperLink() {
        return null;
    }

    @objid ("ca2035a1-3f0c-4ebb-8b43-06f7b18b882f")
    @Override
    public void setHyperLink(MRef ref) {
        throw new UnsupportedOperationException();
    }

    /**
     * <p>
     * Get the metaclass of the element this {@link GmModel} is related to.
     * </p>
     * <p>
     * <strong>Note:</strong> This method should never return <code>null</code> and is not intended to be overridden.
     * </p>
     * @return the metaclass this GmModel is in charge of relating.
     */
    @objid ("302e8323-98de-4092-8f95-4897277ba2e6")
    public final MClass getRelatedMClass() {
        MObject el = getRelatedElement();
        if (el != null) {
            return el.getMClass();
        } else {
            MRef ref = getRepresentedRef();
            return getDiagram().getModelManager().getMetamodel().getMClass(ref.mc);
        }
        
    }

}
