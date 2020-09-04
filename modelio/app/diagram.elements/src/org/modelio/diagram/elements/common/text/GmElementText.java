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

package org.modelio.diagram.elements.common.text;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.GmModel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmSimpleNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a multiline text.
 * 
 * @author cmarin
 */
@objid ("7f28c9c2-1dec-11e2-8cad-001ec947c8cc")
public abstract class GmElementText extends GmSimpleNode {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7f28c9c5-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("7f28c9c8-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    @objid ("919b864e-1e83-11e2-8cad-001ec947c8cc")
    protected String label = null;

    /**
     * Constructor for deserialization only.
     */
    @objid ("7f28c9ca-1dec-11e2-8cad-001ec947c8cc")
    public GmElementText() {
        // empty for the serialization
    }

    /**
     * Creates a text node.
     * 
     * @param diagram The diagram
     */
    @objid ("7f28c9cd-1dec-11e2-8cad-001ec947c8cc")
    public GmElementText(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * Get the label to display.
     * <p>
     * If the label is not initialized yet, calls {@link #refreshFromObModel()}.
     * 
     * @return The label to display.
     */
    @objid ("7f28c9d2-1dec-11e2-8cad-001ec947c8cc")
    public String getText() {
        if (this.label == null) {
            updateTextFromModel();
        }
        return this.label;
    }

    @objid ("7f28c9d7-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public final RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("7f28c9dc-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (getParent() == null) {
            return null;
        }
        return getParent().getStyleKey(metakey);
    }

    @objid ("7f28c9e2-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public List<StyleKey> getStyleKeys() {
        if (getParent() == null) {
            return Collections.emptyList();
        }
        return getParent().getStyleKeys();
    }

    @objid ("7f28c9e9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void refreshFromObModel() {
        if (updateTextFromModel()) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, null, this.label);
        }
    }

    @objid ("7f28c9ec-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void setParentLink(GmLink parentLink) throws IllegalStateException {
        if (parentLink != null && getParent() != parentLink) {
            getPersistedStyle().setCascadedStyle(parentLink.getPersistedStyle());
        }
        super.setParentLink(parentLink);
    }

    /**
     * Computes the displayed element symbol.
     * <p>
     * This method is called by {@link #refreshFromObModel()}.
     * 
     * @return the displayed main label.
     */
    @objid ("7f28c9f0-1dec-11e2-8cad-001ec947c8cc")
    protected abstract String computeText();

    @objid ("7f28c9f3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected IStyle createStyle(IGmDiagram aDiagram) {
        return new ProxyStyle(aDiagram.getPersistedStyle());
    }

    @objid ("7f28c9f9-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected void setParent(GmCompositeNode newParent) throws IllegalStateException {
        final GmModel oldParent = getParent();
        
        super.setParent(newParent);
        
        if (newParent != null && oldParent != newParent) {
            getPersistedStyle().setCascadedStyle(newParent.getPersistedStyle());
        }
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
    @objid ("7f2b2c1b-1dec-11e2-8cad-001ec947c8cc")
    private boolean updateTextFromModel() {
        if (getRelatedElement() != null) {
            final String newLabel = computeText();
            if (newLabel.equals(this.label)) {
                return false;
            }
        
            this.label = newLabel;
            return true;
        }
        return false;
    }

    @objid ("7f2b2c20-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public abstract IEditableText getEditableText();

    @objid ("7f2b2c23-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmElementText.");
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

    @objid ("7f2b2c27-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmElementText.", MINOR_VERSION);
    }

    @objid ("7f2b2c2b-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("7f2b2c2e-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
