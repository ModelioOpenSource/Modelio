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

package org.modelio.diagram.editor.sequence.elements.lifeline;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.emf.common.util.EList;
import org.modelio.diagram.editor.sequence.elements.lifeline.body.GmLifelineBody;
import org.modelio.diagram.editor.sequence.elements.lifeline.header.GmLifelineHeaderContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.interactionModel.InteractionFragment;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a sequence diagram life line.
 */
@objid ("d938f0e0-55b6-11e2-877f-002564c97630")
public class GmLifeline extends GmCompositeNode {
    @objid ("d938f0ee-55b6-11e2-877f-002564c97630")
     static final String BODY_ROLE = "body";

    @objid ("d938f0f0-55b6-11e2-877f-002564c97630")
     static final String HEADER_ROLE = "header";

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d93bfe1a-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d93bfe1d-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Image mode style keys.
     */
    @objid ("d938f0e4-55b6-11e2-877f-002564c97630")
    public static GmLifelineImageStyleKeys IMAGE_KEYS = new GmLifelineImageStyleKeys();

    /**
     * Structured mode style keys.
     */
    @objid ("d938f0e8-55b6-11e2-877f-002564c97630")
    public static GmLifelineStructuredStyleKeys STRUCTURED_KEYS = new GmLifelineStructuredStyleKeys();

    @objid ("d938f0ea-55b6-11e2-877f-002564c97630")
    private GmLifelineBody body;

    /**
     * Simple mode style keys.
     */
    @objid ("c077c8a5-671d-4718-945c-bfb048907d46")
    public static GmLifelineSimpleStyleKeys SIMPLE_KEYS = new GmLifelineSimpleStyleKeys();

    @objid ("2a7abec5-b052-4006-8f13-68a52582630d")
    private Lifeline element;

    /**
     * Image mode style keys.
     */
    @objid ("1bd06743-cffc-4c0a-950e-d8840f431678")
    public static GmLifelineUserImageStyleKeys USERIMAGE_KEYS = new GmLifelineUserImageStyleKeys();

    /**
     * C'tor.
     * @param diagram the diagram in which this gm is created.
     * @param obLifeline the represented lifeline
     * @param ref a reference to the represented lifeline.
     */
    @objid ("d93bfe1f-55b6-11e2-877f-002564c97630")
    public GmLifeline(IGmDiagram diagram, Lifeline obLifeline, MRef ref) {
        super(diagram, ref);
        this.element = obLifeline;
        GmLifelineHeaderContainer header = new GmLifelineHeaderContainer(diagram, ref);
        header.setRoleInComposition(GmLifeline.HEADER_ROLE);
        addChild(header);
        this.body = new GmLifelineBody(diagram, ref);
        this.body.setRoleInComposition(GmLifeline.BODY_ROLE);
        addChild(this.body);
    }

    /**
     * For deserialization only.
     */
    @objid ("d93bfe2b-55b6-11e2-877f-002564c97630")
    public GmLifeline() {
        // Empty c'tor for deserialization.
    }

    @objid ("d93bfe2e-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        // Creation is handled by the body part.
        if (this.body != null) {
            return this.body.canCreate(type);
        } else {
            return false;
        }
    }

    @objid ("d93bfe35-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        if (el == null || !canCreate(el.getClass()) || !(el instanceof InteractionFragment)) {
            return false;
        }
        EList<Lifeline> ll1 = ((InteractionFragment) el).getCovered();
        return !ll1.isEmpty() && ll1.get(0).equals(getRelatedElement());
    }

    @objid ("d93bfe3d-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this.body;
        } else {
            return null;
        }
    }

    @objid ("d93bfe46-55b6-11e2-877f-002564c97630")
    @Override
    public Lifeline getRelatedElement() {
        return this.element;
    }

    @objid ("d93bfe4d-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("d93bfe54-55b6-11e2-877f-002564c97630")
    @Override
    public Lifeline getRepresentedElement() {
        return this.element;
    }

    @objid ("d93bfe5b-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        RepresentationMode mode = this.getRepresentationMode();
        switch (mode) {
        case SIMPLE:
            return GmLifeline.SIMPLE_KEYS.getStyleKey(metakey);
        case STRUCTURED:
            return GmLifeline.STRUCTURED_KEYS.getStyleKey(metakey);
        case IMAGE:
        case USER_IMAGE:
            return GmLifeline.IMAGE_KEYS.getStyleKey(metakey);
        default:
            return null;
        }
    }

    @objid ("d93d84bf-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        RepresentationMode mode = this.getRepresentationMode();
        switch (mode) {
        case SIMPLE:
            return GmLifeline.SIMPLE_KEYS.getStyleKeys();
        case STRUCTURED:
            return GmLifeline.STRUCTURED_KEYS.getStyleKeys();
        case IMAGE:
        case USER_IMAGE:
            return GmLifeline.IMAGE_KEYS.getStyleKeys();
        default:
            return Collections.emptyList();
        }
    }

    @objid ("d93d84c7-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmLifeline.");
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

    @objid ("d93d84cd-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        if (this.element != null && this.element.isValid()) {
            firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, this.getLayoutData(), null);
        }
    }

    @objid ("d93d84d0-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmLifeline.", GmLifeline.MINOR_VERSION);
    }

    @objid ("d93d84d6-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        
        this.element = (Lifeline) resolveRef(this.getRepresentedRef());
        this.body = (GmLifelineBody) getFirstChild(GmLifeline.BODY_ROLE);
    }

    @objid ("d93d84db-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmLifeline.MAJOR_VERSION;
    }

}
