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

package org.modelio.diagram.editor.statik.elements.instanceinternalstructure;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.geometry.Rectangle;
import org.modelio.diagram.editor.statik.elements.instance.GmInstanceStructuredStyleKeys;
import org.modelio.diagram.elements.common.freezone.GmFreeZone;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.InternalsViewMode;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the {@link Instance} internal structure as a free zone.
 */
@objid ("3552846a-55b7-11e2-877f-002564c97630")
public final class GmInstanceInternalStructureZone extends GmFreeZone {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3552846e-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35528471-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates the zone.
     * @param diagram The diagram
     * @param relatedRef a reference to the element this zone is related to, must not be null.
     */
    @objid ("35528473-55b7-11e2-877f-002564c97630")
    public GmInstanceInternalStructureZone(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("3552847c-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        if (!(el instanceof Instance) || el instanceof Port || !el.isValid())
            return false;
        
        // Cannot unmask a instance class (not belonging to the class)
        if (!el.getCompositionOwner().equals(this.getRelatedElement()))
            return false;
        return true;
    }

    /**
     * The internal structure zone can be visible only if the {@link GmInstanceStructuredStyleKeys#INTERNALSVIEWMODE}
     * property is {@link InternalsViewMode#DIAGRAM}.
     */
    @objid ("35528484-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        if (getParent() != null && getParent().getRepresentationMode() == RepresentationMode.STRUCTURED) {
            InternalsViewMode v = getDisplayedStyle().getProperty(GmInstanceStructuredStyleKeys.INTERNALSVIEWMODE);
            return (v == InternalsViewMode.DIAGRAM);
        }
        return false;
    }

    @objid ("35540ade-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("35540ae4-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == GmInstanceStructuredStyleKeys.INTERNALSVIEWMODE)
            fireVisibilityChanged();
        else
            super.styleChanged(property, newValue);
    }

    @objid ("35540aeb-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return Instance.class.isAssignableFrom(type) && !Port.class.isAssignableFrom(type);
    }

    /**
     * For deserialization only.
     */
    @objid ("35540af3-55b7-11e2-877f-002564c97630")
    public GmInstanceInternalStructureZone() {
        // Nothing to do yet.
    }

    @objid ("35540af6-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible) {
            getDisplayedStyle().setProperty(GmInstanceStructuredStyleKeys.INTERNALSVIEWMODE, InternalsViewMode.DIAGRAM);
            ((GmInstanceInternalStructure) getParent()).setVisible(true);
        } else {
            getDisplayedStyle().setProperty(GmInstanceStructuredStyleKeys.INTERNALSVIEWMODE, InternalsViewMode.NONE);
        }
    }

    @objid ("35540afa-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidChild(GmNodeModel node) {
        MObject relatedIElement = node.getRelatedElement();
        return relatedIElement == null || !relatedIElement.isValid() || canUnmask(relatedIElement);
    }

    @objid ("35540b02-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        final MObject relatedIElement = getRelatedElement();
        
        if (relatedIElement == null || !relatedIElement.isValid())
            return;
        
        StyleKey styleKey = getStyleKey(MetaKey.InternalGroup.INTAUTOUNMASK);
        if (styleKey == null) {
            return;
        }
        final Boolean mode = this.getDisplayedStyle().getProperty(styleKey);
        if (mode) {
            final Rectangle constraint = new Rectangle(5, 5, -1, -1);
            for (BindableInstance part : ((Instance) relatedIElement).getPart()) {
                if (!(part instanceof Port) && getChild(new MRef(part)) == null) {
                    getDiagram().unmask(this, part, constraint.getCopy());
                    constraint.translate(10, 10);
                }
            }
        }
    }

    @objid ("35540b05-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInstanceInternalStructureZone.");
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

    @objid ("35540b0b-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmInstanceInternalStructureZone.", GmInstanceInternalStructureZone.MINOR_VERSION);
    }

    @objid ("35540b11-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("35540b16-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
