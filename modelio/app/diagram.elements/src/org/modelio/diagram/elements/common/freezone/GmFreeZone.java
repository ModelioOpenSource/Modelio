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

package org.modelio.diagram.elements.common.freezone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a zone where elements can be unmasked.
 * <p>
 * This class must be subclassed , {@link #canUnmask(MObject)} and {@link #canCreate(Class)} must be defined.
 * 
 * @author cmarin
 */
@objid ("7e3cc0f8-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmFreeZone extends GmCompositeNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7e3cc0fa-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("7e3cc0fd-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Initializes a free zone.
     * @param diagram The diagram
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("7e3cc0ff-1dec-11e2-8cad-001ec947c8cc")
    public GmFreeZone(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Constructor for deserialization only.
     */
    @objid ("7e3cc104-1dec-11e2-8cad-001ec947c8cc")
    public GmFreeZone() {
        super();
    }

    @objid ("7e3cc107-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        //else
        return null;
    }

    @objid ("7e3cc10f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    /**
     * Redefined to ask the question to the parent.
     */
    @objid ("7e3cc114-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (getParent() == null) {
            return null;
        }
        return getParent().getStyleKey(metakey);
    }

    /**
     * A free zone does not have own style keys.
     */
    @objid ("7e3cc11b-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<StyleKey> getStyleKeys() {
        return Collections.emptyList();
    }

    @objid ("7e3cc123-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public abstract boolean isVisible();

    @objid ("7e3cc126-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IStyle createStyle(IGmDiagram diagram) {
        return new ProxyStyle(diagram.getPersistedStyle());
    }

    @objid ("7e3cc12c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected abstract void doSetVisible(boolean visible);

    @objid ("7e3cc12f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected abstract boolean isValidChild(GmNodeModel node);

    /**
     * Convenience method that remove child nodes whose parent model element is not this node represented element.
     * <p>
     * To be called in {@link #refreshFromObModel()} when needed.
     */
    @objid ("7e3cc133-1dec-11e2-8cad-001ec947c8cc")
    protected void removeObsoleteChildren() {
        final MObject el = getRelatedElement();
        
        // Do nothing if invalid itself
        if (el == null || el.isShell() || el.isDeleted()) {
            return;
        }
        
        // Get the children to delete
        final List<GmNodeModel> toDel = new ArrayList<>();
        
        for (GmNodeModel child : getChildren()) {
            MObject childEl = child.getRelatedElement();
            if (childEl != null) {
                if (childEl.isDeleted() || childEl.getCompositionOwner() != el) {
                    toDel.add(child);
                }
            }
        }
        
        // Delete found obsolete children
        for (GmNodeModel child : toDel) {
            child.delete();
        }
    }

    /**
     * Redefined to set its own style cascading from the new parent node style.
     */
    @objid ("7e3cc136-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void setParent(GmCompositeNode parent) {
        GmModel oldParent = getParent();
        // Call inherited
        super.setParent(parent);
        
        // Modify the style
        if (parent != null && !parent.equals(oldParent)) {
            getPersistedStyle().setCascadedStyle(parent.getPersistedStyle());
        }
    }

    /**
     * A free zone does not support {@link GmElementLabel GmElementLabel}.
     */
    @objid ("7e3f234f-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return !GmElementLabel.class.isAssignableFrom(nodeClass);
    }

    @objid ("7e3f2358-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmFreeZone.");
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

    @objid ("7e3f235c-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmFreeZone.", MINOR_VERSION);
    }

    @objid ("7e3f2360-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("7e3f2363-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
