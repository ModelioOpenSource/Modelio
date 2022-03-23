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
package org.modelio.uml.sequencediagram.editor.elements.stateinvariant;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.interactionModel.StateInvariant;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link StateInvariant} in the diagram model.
 */
@objid ("d9947e75-55b6-11e2-877f-002564c97630")
public class GmStateInvariant extends GmCompositeNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d9947e7d-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d9947e80-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("3eb3cb83-9a8e-4293-a583-fe5a254174ca")
    private static GmStateInvariantStructuredStyleKeys KEYS = new GmStateInvariantStructuredStyleKeys();

    @objid ("1978745f-393b-4870-87c7-b02857252e9d")
    private StateInvariant element;

    /**
     * Create an StateInvariant
     * @param diagram The owning diagram
     * @param element the represented StateInvariant, may be null
     * @param ref the represented StateInvariant reference, may not be null
     */
    @objid ("d9947e82-55b6-11e2-877f-002564c97630")
    public  GmStateInvariant(IGmDiagram diagram, StateInvariant element, MRef ref) {
        super(diagram, ref);
        this.element = element;
        GmStateInvariantBodyText body = new GmStateInvariantBodyText(diagram, ref);
        this.addChild(body);
        
    }

    /**
     * For deserialization only
     */
    @objid ("d9947e8e-55b6-11e2-877f-002564c97630")
    public  GmStateInvariant() {
        // Nothing to do
    }

    @objid ("d9947e91-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("d99604fd-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    @objid ("d9960505-55b6-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        return null;
    }

    @objid ("d996050f-55b6-11e2-877f-002564c97630")
    @Override
    public StateInvariant getRelatedElement() {
        return this.element;
    }

    @objid ("d9960516-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("d996051d-55b6-11e2-877f-002564c97630")
    @Override
    public StateInvariant getRepresentedElement() {
        return this.element;
    }

    @objid ("d9960524-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmStateInvariant.KEYS.getStyleKey(metakey);
    }

    @objid ("d996052e-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmStateInvariant.KEYS.getStyleKeys();
    }

    @objid ("d9960537-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmStateInvariant.");
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

    @objid ("d9978b99-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        if (this.element != null && this.element.isValid()) {
            firePropertyChange(IGmObject.PROPERTY_LAYOUTDATA, this.getLayoutData(), null);
        }
        
    }

    @objid ("d9978b9c-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmStateInvariant.", GmStateInvariant.MINOR_VERSION);
        
    }

    @objid ("d9978ba2-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (StateInvariant) resolveRef(this.getRepresentedRef());
        
    }

    @objid ("d9978ba7-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
