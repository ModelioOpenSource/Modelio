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
package org.modelio.diagram.elements.core.node;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Composite node that depends on its parent node for style handling.
 * <p>
 * This node has a proxy style that delegates all to its parent node. This node is always in
 * {@link RepresentationMode#STRUCTURED} representation mode.
 */
@objid ("809a5678-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmNoStyleCompositeNode extends GmCompositeNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("809a567a-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("809a567d-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Create a no style composite node.
     * @param diagram The diagram.
     * @param relatedRef a reference to the element this GmModel is related to. Must not be <tt>null</tt>.
     */
    @objid ("809cb89a-1dec-11e2-8cad-001ec947c8cc")
    public  GmNoStyleCompositeNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("809cb89f-1dec-11e2-8cad-001ec947c8cc")
    public  GmNoStyleCompositeNode() {
        super();
    }

    @objid ("809cb8a2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public RepresentationMode getRepresentationMode() {
        if (getParent() != null) {
            return getParent().getRepresentationMode();
        }
        return RepresentationMode.STRUCTURED;
    }

    @objid ("809cb8a7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (getParent() == null) {
            return null;
        }
        return getParent().getStyleKey(metakey);
    }

    /**
     * A no style node has no own style key by default.
     */
    @objid ("809cb8ad-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<StyleKey> getStyleKeys() {
        return Collections.emptyList();
    }

    @objid ("809cb8b4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setParentLink(final GmLink parentLink) throws IllegalStateException {
        if (getParent() != parentLink) {
            super.setParentLink(parentLink);
        
            if (parentLink != null) {
                getPersistedStyle().setCascadedStyle(parentLink.getPersistedStyle());
            }
        }
        
    }

    @objid ("809cb8b9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final IStyle createStyle(IGmDiagram aDiagram) {
        return new ProxyStyle(aDiagram.getPersistedStyle());
    }

    @objid ("809cb8bf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void setParent(final GmCompositeNode parent) throws IllegalStateException {
        if (getParent() != parent) {
            super.setParent(parent);
        
            if (parent != null) {
                getPersistedStyle().setCascadedStyle(parent.getPersistedStyle());
            }
        }
        
    }

    @objid ("809cb8c4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNoStyleCompositeNode.");
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

    @objid ("809cb8c8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNoStyleCompositeNode.", MINOR_VERSION);
        
    }

    @objid ("809cb8cc-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("809cb8cf-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
