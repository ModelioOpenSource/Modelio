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

package org.modelio.diagram.elements.common.label.modelelement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("cd000a31-41f9-41d6-b3fe-7efeba868850")
public class GmDefaultModelElementLabel extends GmModelElementLabel {
    @objid ("c42ca2a2-9409-4a3d-a030-56cf7c83db8c")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("9d560e33-baec-424c-9253-190c312228ea")
    private static final int MINOR_VERSION = 0;

    /**
     * Create an header label
     * 
     * @param diagram the diagram.
     * @param relatedRef reference to the diagram.
     */
    @objid ("fd10b176-7aaa-49b0-b8d0-dc2811851e6d")
    public GmDefaultModelElementLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("1fd4100c-e727-4934-ac55-b0e89edd462b")
    public GmDefaultModelElementLabel() {
        // serialization
    }

    @objid ("3f5d6980-2d5e-4737-9ca2-746f48a12d2b")
    @Override
    public List<Stereotype> filterStereotypes(List<Stereotype> stereotypes) {
        // Check the current representation mode
        final StyleKey key = getStyleKey(MetaKey.REPMODE);
        if (key != null) {
            // For image mode, filter the first image stereotype
            if (getDisplayedStyle().getProperty(key) == RepresentationMode.IMAGE) {
                for (Stereotype stereo : stereotypes) {
                    if (!stereo.getIcon().isEmpty()) {
                        List<Stereotype> ret = new ArrayList<>(stereotypes);
                        ret.remove(stereo);
                        return ret;
                    }
                }
            }
        }
        return stereotypes;
    }

    @objid ("f15246ad-0b62-4ff2-aaae-d9102bf428e8")
    @Override
    public List<TaggedValue> filterTags(List<TaggedValue> taggedValues) {
        return taggedValues;
    }

    @objid ("a4b64101-5419-4366-8ba6-0f57def21720")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("9fbc4f35-eeb5-4d77-87f1-12061741a8c8")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (getParent() != null) {
            return getParent().getStyleKey(metakey);
        } else {
            return null;
        }
    }

    @objid ("5b2939ae-f6ab-43e8-b3c6-e8944e570f86")
    @Override
    public List<StyleKey> getStyleKeys() {
        if (getParent() == null) {
            return Collections.emptyList();
        } else {
            return getParent().getStyleKeys();
        }
    }

    /**
     * By default labels are flat only in GmGroups.
     */
    @objid ("00dc76bb-5fa3-4257-b278-edc4a0ad5962")
    @Override
    public boolean isFlat() {
        return getParentNode() instanceof GmGroup;
    }

    @objid ("08a7f953-99f2-4f4f-aa9c-e5bb7037d643")
    @Override
    public void read(IDiagramReader in) {
        // this may serve as test for migration
        //Object migVersionProperty = in.readProperty("GmDefaultFlatHeader." + MINOR_VERSION_PROPERTY);
        
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmDefaultModelElementLabel.");
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

    @objid ("a94efea3-de13-44a4-a3b4-9dcb8f505b5d")
    @Override
    public void setParentLink(GmLink parentLink) throws IllegalStateException {
        if (getParentLink() != parentLink) {
            super.setParentLink(parentLink);
        
            if (parentLink != null) {
                getPersistedStyle().setCascadedStyle(parentLink.getPersistedStyle());
            }
        }
    }

    @objid ("3ffd9da3-56a7-4a6d-81e8-5bee20ecde62")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmDefaultFlatHeader.", Integer.valueOf(MINOR_VERSION));
    }

    /**
     * Create a proxy style that points to the diagram style.
     * <p>
     * The created proxy style will be modified when the owner is set to make it point
     * to the owner model style.
     */
    @objid ("3389c0dc-93d8-44fc-9b44-9d1ed9827551")
    @Override
    protected IStyle createStyle(IGmDiagram diagram) {
        return new ProxyStyle(diagram.getPersistedStyle());
    }

    /**
     * Redefined to set its own style cascading from the new parent node style.
     */
    @objid ("8cc1e96f-978f-4b9b-acb0-d4df2f2543fe")
    @Override
    protected void setParent(GmCompositeNode parent) {
        if (getParent() != parent) {
            super.setParent(parent);
        
            if (parent != null) {
                getPersistedStyle().setCascadedStyle(parent.getPersistedStyle());
            }
        }
    }

    @objid ("1a76b3d1-9fc5-4c74-916e-01b421e15ad5")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

}
