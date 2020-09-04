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

package org.modelio.diagram.editor.bpmn.elements.bpmnnodeheader;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
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
 * Represents the classifier header.
 * <p>
 * Contains for the moment the class name.<br>
 * Will contain in the future:<br>
 * - its visibility <br>
 * - tagged values <br>
 * - &lt;&lt;stereotypes names>> <br>
 * - a stereotype icons bar <br>
 */
@objid ("617c2b89-55b6-11e2-877f-002564c97630")
public class GmBpmnNodeHeader extends GmModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("617c2b90-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("617c2b93-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("617c2b8d-55b6-11e2-877f-002564c97630")
    private List<Image> images;

    @objid ("617c2b95-55b6-11e2-877f-002564c97630")
    public GmBpmnNodeHeader(IGmDiagram diagram, MRef relatedRef, boolean showIcone) {
        super(diagram, relatedRef);
        this.setStackedStereotypes(true);
        this.setShowMetaclassIcon(showIcone);
    }

    /**
     * Redefined to set its own style cascading from the new parent node style.
     */
    @objid ("617c2b9e-55b6-11e2-877f-002564c97630")
    @Override
    protected void setParent(GmCompositeNode parent) {
        if (parent != null && getParent() != parent) {
            getPersistedStyle().setCascadedStyle(parent.getPersistedStyle());
        }
        
        super.setParent(parent);
    }

    @objid ("617c2ba5-55b6-11e2-877f-002564c97630")
    @Override
    public List<Stereotype> filterStereotypes(List<Stereotype> stereotypes) {
        return stereotypes;
    }

    @objid ("617c2bb3-55b6-11e2-877f-002564c97630")
    @Override
    public List<TaggedValue> filterTags(List<TaggedValue> taggedValues) {
        return taggedValues;
    }

    /**
     * Nothing can be unmasked here.
     */
    @objid ("617db21c-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return false;
    }

    /**
     * Nothing can be created here.
     */
    @objid ("617db225-55b6-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    /**
     * Delegates to the parent.
     */
    @objid ("617db22e-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (getParent() == null)
            return null;
        return getParent().getStyleKey(metakey);
    }

    /**
     * The header does not have own style keys.
     */
    @objid ("617db239-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return Collections.emptyList();
    }

    /**
     * Delegates to the parent.
     */
    @objid ("617db243-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        if (getParent() == null)
            return RepresentationMode.STRUCTURED;
        return getParent().getRepresentationMode();
    }

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("617db24b-55b6-11e2-877f-002564c97630")
    public GmBpmnNodeHeader() {
        // empty constructor for the serialization
    }

    @objid ("617db24e-55b6-11e2-877f-002564c97630")
    public List<Image> getHeaderImage() {
        return this.images;
    }

    @objid ("617db254-55b6-11e2-877f-002564c97630")
    public void addHeaderImage(final List<Image> headerImages) {
        this.images = headerImages;
    }

    @objid ("617f38bd-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBpmnNodeHeader.");
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

    @objid ("617f38c3-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBpmnNodeHeader.", MINOR_VERSION);
    }

    @objid ("617f38c9-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("617f38ce-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
