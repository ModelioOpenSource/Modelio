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

package org.modelio.diagram.elements.common.header;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
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

/**
 * Displays the represented element name as main label.
 * <p>
 * Has no own style, depends on its parent element.
 */
@objid ("7e60843a-1dec-11e2-8cad-001ec947c8cc")
public class GmDefaultModelElementHeader extends GmModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7e60843c-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("7e60843f-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * For deserialization only.
     */
    @objid ("7e608441-1dec-11e2-8cad-001ec947c8cc")
    public GmDefaultModelElementHeader() {
        // Empty c'tor for deserialization only.
    }

    /**
     * Initializes a model element header.
     * @param diagram the owning diagram.
     * @param relatedRef the element reference.
     */
    @objid ("7e608444-1dec-11e2-8cad-001ec947c8cc")
    public GmDefaultModelElementHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        setStackedStereotypes(true);
    }

    /**
     * Create a proxy style that points to the diagram style.
     * <p>
     * The created proxy style will be modified when the owner is set to make it point
     * to the owner model style.
     */
    @objid ("7e608449-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IStyle createStyle(IGmDiagram diagram) {
        return new ProxyStyle(diagram.getPersistedStyle());
    }

    /**
     * Redefined to set its own style cascading from the new parent node style.
     */
    @objid ("7e608450-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void setParent(GmCompositeNode parent) {
        if (parent != null && getParent() != parent) {
            getPersistedStyle().setCascadedStyle(parent.getPersistedStyle());
        }
        
        super.setParent(parent);
    }

    /**
     * Redefined to set its own style cascading from the new parent link style.
     */
    @objid ("7e608455-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setParentLink(GmLink parentLink) throws IllegalStateException {
        if (parentLink != null && getParent() != parentLink) {
            getPersistedStyle().setCascadedStyle(parentLink.getPersistedStyle());
        }
        
        super.setParentLink(parentLink);
    }

    @objid ("7e60845a-1dec-11e2-8cad-001ec947c8cc")
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

    @objid ("7e608464-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<TaggedValue> filterTags(List<TaggedValue> taggedValues) {
        return taggedValues;
    }

    @objid ("7e62e698-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (getParent() != null) {
            return getParent().getStyleKey(metakey);
        } else {
            return null;
        }
    }

    @objid ("7e62e6a4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmDefaultModelElementHeader.");
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

    @objid ("7e62e6a8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmDefaultModelElementHeader.", MINOR_VERSION);
    }

    @objid ("7e62e6ac-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("7e62e6af-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("7e62e69d-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<StyleKey> getStyleKeys() {
        if (getParent() == null) {
            return Collections.emptyList();
        } else {
            return getParent().getStyleKeys();
        }
    }

}
