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

package org.modelio.diagram.editor.activity.elements.activitynodeheader;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the activity node header.
 * <p>
 * 
 * FIXME use {@link org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader} instead
 */
@objid ("29b06ae5-55b6-11e2-877f-002564c97630")
public class GmActivityNodeHeader extends GmModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29b06ae9-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29b06aec-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates a classifier header
     * @param diagram the owning diagram.
     * @param relatedRef related element reference, must not be <code>null</code>.
     */
    @objid ("29b06aee-55b6-11e2-877f-002564c97630")
    public GmActivityNodeHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        setStackedStereotypes(true);
    }

    /**
     * Redefined to set its own style cascading from the new parent node style.
     */
    @objid ("29b06af7-55b6-11e2-877f-002564c97630")
    @Override
    protected void setParent(GmCompositeNode parent) {
        if (parent != null && getParent() != parent) {
            getPersistedStyle().setCascadedStyle(parent.getPersistedStyle());
        }
        
        super.setParent(parent);
    }

    @objid ("29b06afe-55b6-11e2-877f-002564c97630")
    @Override
    public List<Stereotype> filterStereotypes(List<Stereotype> stereotypes) {
        return stereotypes;
    }

    @objid ("29b06b0c-55b6-11e2-877f-002564c97630")
    @Override
    public List<TaggedValue> filterTags(List<TaggedValue> taggedValues) {
        return taggedValues;
    }

    /**
     * Nothing can be unmasked here.
     */
    @objid ("29b1f184-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    /**
     * Nothing can be created here.
     */
    @objid ("29b1f18d-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    /**
     * Delegates to the parent.
     */
    @objid ("29b1f196-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (getParent() == null) {
            return null;
        }
        return getParent().getStyleKey(metakey);
    }

    /**
     * The header does not have own style keys.
     */
    @objid ("29b1f1a1-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return Collections.emptyList();
    }

    /**
     * Delegates to the parent.
     */
    @objid ("29b1f1ab-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        if (getParent() == null) {
            return RepresentationMode.STRUCTURED;
        }
        return getParent().getRepresentationMode();
    }

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("29b1f1b3-55b6-11e2-877f-002564c97630")
    public GmActivityNodeHeader() {
        // empty constructor for the serialization
    }

    @objid ("29b37819-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmActivityNodeHeader.");
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

    @objid ("29b3781f-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmActivityNodeHeader.", GmActivityNodeHeader.MINOR_VERSION);
    }

    @objid ("29b37825-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("29b3782a-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmActivityNodeHeader.MAJOR_VERSION;
    }

}
