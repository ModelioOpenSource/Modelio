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

package org.modelio.diagram.elements.common.label.base;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmSimpleNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents labels around connection links.
 * 
 * @author cmarin
 */
@objid ("7e8dd0e8-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmElementLabel extends GmSimpleNode {
    @objid ("7e8dd0ee-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("9073e2bb-1e83-11e2-8cad-001ec947c8cc")
    protected String label = null;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7e8dd0eb-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("7e8dd0f0-1dec-11e2-8cad-001ec947c8cc")
    public GmElementLabel() {
        // empty for the serialization
    }

    /**
     * Creates a label
     * 
     * @param diagram The diagram
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("7e8dd0f3-1dec-11e2-8cad-001ec947c8cc")
    public GmElementLabel(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Get the label to display.
     * <p>
     * If the label is not initialized yet, calls {@link #refreshFromObModel()}.
     * 
     * @return The label to display.
     */
    @objid ("7e8dd0f8-1dec-11e2-8cad-001ec947c8cc")
    public String getLabel() {
        if (this.label == null) {
            updateLabelFromModel();
        }
        return this.label;
    }

    @objid ("7e903375-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return GmElementLabel.MAJOR_VERSION;
    }

    @objid ("7e8dd0fd-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("7e903344-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (getParent() == null) {
            return null;
        }
        return getParent().getStyleKey(metakey);
    }

    @objid ("7e90334a-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<StyleKey> getStyleKeys() {
        if (getParent() == null) {
            return Collections.emptyList();
        } else {
            return getParent().getStyleKeys();
        }
    }

    @objid ("7e903351-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmElementLabel.");
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

    @objid ("7e903355-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void refreshFromObModel() {
        if (updateLabelFromModel()) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, null, this.label);
        }
    }

    @objid ("7e903358-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setParentLink(GmLink parentLink) throws IllegalStateException {
        if (parentLink != null && getParent() != parentLink) {
            getPersistedStyle().setCascadedStyle(parentLink.getPersistedStyle());
        }
        super.setParentLink(parentLink);
    }

    @objid ("7e90336e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmElementLabel.", GmElementLabel.MINOR_VERSION);
    }

    /**
     * Computes the displayed element symbol.
     * <p>
     * This method is called by {@link #refreshFromObModel()}.
     * 
     * @return the displayed main label.
     */
    @objid ("7e90335c-1dec-11e2-8cad-001ec947c8cc")
    protected abstract String computeLabel();

// @objid ("7e90335f-1dec-11e2-8cad-001ec947c8cc")
// @Override
// protected IStyle createStyle(GmAbstractDiagram aDiagram) {
// return new ProxyStyle(aDiagram.getStyle());
// }
    @objid ("7e903365-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void setParent(GmCompositeNode newParent) throws IllegalStateException {
        final GmModel oldParent = getParent();
        
        super.setParent(newParent);
        
        if (newParent != null && oldParent != newParent) {
            getPersistedStyle().setCascadedStyle(newParent.getPersistedStyle());
        }
    }

    @objid ("7e903372-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    /**
     * Sets the displayed label text.
     * <p>
     * To be called by {@link #refreshFromObModel()}.
     * <p>
     * Do not fire property change event.
     * @param newLabel
     * The new label, must not be null.
     */
    @objid ("7e903369-1dec-11e2-8cad-001ec947c8cc")
    private boolean updateLabelFromModel() {
        if (getRelatedElement() != null) {
            final String newLabel = computeLabel();
            if (newLabel.equals(this.label)) {
                return false;
            }
        
            this.label = newLabel;
            return true;
        }
        return false;
    }

    /**
     * @return <code>true</code> if the label can be wrapped.
     */
    @objid ("95d372bd-b8b2-485b-a6a9-d933286c091e")
    public boolean isWrapped() {
        return true;
    }

}
