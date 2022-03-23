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
package org.modelio.uml.statikdiagram.editor.elements.namespacinglink;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLinkable;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.IStyleProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.ProxyStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * GmUsed to represents the composition dependency between a model tree and a model element owned by it.
 */
@objid ("35ac8b6f-55b7-11e2-877f-002564c97630")
public class GmCompositionLink extends GmLink {
    @objid ("35ae11bb-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35ae11bd-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty constructor for deserialization.
     */
    @objid ("35ae11bf-55b7-11e2-877f-002564c97630")
    public  GmCompositionLink() {
        
    }

    /**
     * Constructor.
     * @param diagram The diagram in which this link will be unmasked.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("35ae11c2-55b7-11e2-877f-002564c97630")
    public  GmCompositionLink(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("35ae11cd-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        if (getFromElement() != null) {
            return getFrom().getStyleKeys();
        }
        return Collections.emptyList();
    }

    @objid ("35ae11d6-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        if (getFrom() != null) {
            return getFrom().getStyleKey(metakey);
        }
        return null;
    }

    @objid ("35ae11e1-55b7-11e2-877f-002564c97630")
    @Override
    public ModelElement getFromElement() {
        if (getFrom() != null) {
            return (ModelElement) getFrom().getRelatedElement();
        }
        return null;
    }

    @objid ("35ae11e8-55b7-11e2-877f-002564c97630")
    @Override
    public ModelElement getRelatedElement() {
        if (getFrom() != null) {
            return (ModelElement) getFrom().getRelatedElement();
        }
        return null;
    }

    @objid ("35ae11ef-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        if (getTo() != null) {
            return getTo().getRelatedElement();
        }
        return null;
    }

    @objid ("35ae11f6-55b7-11e2-877f-002564c97630")
    @Override
    protected IStyle createStyle(final IGmDiagram aDiagram) {
        return new ProxyStyle(aDiagram.getPersistedStyle());
    }

    @objid ("35af9861-55b7-11e2-877f-002564c97630")
    private void refreshStyle(final IStyleProvider provider) {
        // Modify the style
        getPersistedStyle().setCascadedStyle(provider.getPersistedStyle());
        
    }

    @objid ("35af9867-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(final IDiagramReader in) {
        super.readLink(in);
        if (getFrom() != null) {
            refreshStyle(getFrom());
        } else {
            refreshStyle(getDiagram());
        }
        
    }

    @objid ("35af986e-55b7-11e2-877f-002564c97630")
    @Override
    public void setFrom(final IGmLinkable from) {
        super.setFrom(from);
        if (from != null) {
            refreshStyle(from);
        }
        
    }

    @objid ("35af9875-55b7-11e2-877f-002564c97630")
    @Override
    public void write(final IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmCompositionLink.", GmCompositionLink.MINOR_VERSION);
        
    }

    @objid ("35af987c-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("35af9881-55b7-11e2-877f-002564c97630")
    @Override
    protected void refreshFromObModel() {
        super.refreshFromObModel();
        // If the link is somehow incoherent with Ob model, delete it (this is usually the result of a reparenting of the "to" (=child) element.
        if (getFromElement() == null ||
            !getFromElement().isValid() ||
            (getToElement() != null && getToElement().isValid() && !getFromElement().equals(getToElement().getCompositionOwner()))) {
            delete();
        }
        
    }

    @objid ("d626421d-72f5-4027-a2f3-b6cace1a840b")
    @Override
    protected void read_GmLinkV0_roles() {
        // noop
    }

}
