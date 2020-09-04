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
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Composite node that depends on its parent node for style handling.
 * <p>
 * This node has a proxy style that delegates all to its parent node. This node is always in
 * {@link RepresentationMode#STRUCTURED} representation mode.
 */
@objid ("809f1af3-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmNoStyleSimpleNode extends GmSimpleNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("809f1af5-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("809f1af8-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Create a no style composite node.
     * @param diagram The diagram.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("809f1afa-1dec-11e2-8cad-001ec947c8cc")
    public GmNoStyleSimpleNode(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("809f1aff-1dec-11e2-8cad-001ec947c8cc")
    public GmNoStyleSimpleNode() {
        super();
    }

    @objid ("809f1b02-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final StyleKey getStyleKey(MetaKey metakey) {
        if (getParent() == null) {
            return null;
        }
        return getParent().getStyleKey(metakey);
    }

    @objid ("809f1b08-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final List<StyleKey> getStyleKeys() {
        return Collections.emptyList();
    }

    @objid ("809f1b0f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setParentLink(final GmLink parentLink) throws IllegalStateException {
        if (getParent() != parentLink) {
            super.setParentLink(parentLink);
        
            if (parentLink != null) {
                getPersistedStyle().setCascadedStyle(parentLink.getPersistedStyle());
            }
        }
    }

    @objid ("809f1b14-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected final IStyle createStyle(IGmDiagram aDiagram) {
        return new ProxyStyle(aDiagram.getPersistedStyle());
    }

    @objid ("809f1b1a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void setParent(final GmCompositeNode parent) throws IllegalStateException {
        if (getParent() != parent) {
            super.setParent(parent);
        
            if (parent != null) {
                getPersistedStyle().setCascadedStyle(parent.getPersistedStyle());
            }
        }
    }

    @objid ("809f1b1f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmNoStyleSimpleNode.");
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

    @objid ("809f1b23-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmNoStyleSimpleNode.", MINOR_VERSION);
    }

    @objid ("809f1b27-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("809f1b2a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
