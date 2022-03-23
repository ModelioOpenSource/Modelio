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
package org.modelio.uml.statikdiagram.editor.elements.binding;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * {@linkplain CollaborationUse Collaboration use} {@linkplain GmBindingLabelGroup role bindings} group.
 * 
 * @author cmarin
 */
@objid ("340bfa96-55b7-11e2-877f-002564c97630")
public class GmBindingLabelGroup extends GmGroup {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("340bfa9a-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("340bfa9d-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("340bfa9f-55b7-11e2-877f-002564c97630")
    public  GmBindingLabelGroup() {
        
    }

    /**
     * Creates a binding group.
     * @param diagram The diagram.
     * @param relatedRef The related element reference, must not be null.
     */
    @objid ("340bfaa2-55b7-11e2-877f-002564c97630")
    public  GmBindingLabelGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * The binding group only support {@link GmBindingLabel} nodes.
     */
    @objid ("340bfaab-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmBindingLabel.class.isAssignableFrom(nodeClass);
    }

    /**
     * Only attributes can be created here.
     */
    @objid ("340bfab4-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return Binding.class.isAssignableFrom(type);
    }

    @objid ("340bfabd-55b7-11e2-877f-002564c97630")
    @Override
    public CollaborationUse getRelatedElement() {
        return (CollaborationUse) super.getRelatedElement();
    }

    /**
     * The inner class zone can be visible only if the {@link MetaKey.AttGroup#ATTSHOWGROUP} property is true.
     */
    @objid ("340d8119-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return (Boolean) getDisplayedStyle().getProperty(getViewModeStyleKey());
    }

    @objid ("340d8125-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        final CollaborationUse classifier = getRelatedElement();
        if (classifier != null) {
            for (Binding part : classifier.getRoleBinding()) {
                if (getChild(new MRef(part)) == null) {
                    getDiagram().unmask(this, part, null);
                }
            }
        }
        
    }

    @objid ("340d8128-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == getViewModeStyleKey()) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
        
    }

    @objid ("340d812f-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        refreshFromObModel();
        fireVisibilityChanged();
        super.styleChanged(style);
        
    }

    @objid ("340d8135-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        getDisplayedStyle().setProperty(getViewModeStyleKey(), visible);
    }

    /**
     * Checks whether the given model element can be and still be displayed here.
     * <p>
     * Check all conditions except the case where it is already unmasked.
     * @param el The element to unmask
     * @return true if it satisfies all conditions, else false.
     */
    @objid ("340d8139-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidElement(MObject el) {
        // Cannot unmask anything else than a role binding
        if (!(el instanceof Binding) || !el.isValid()) {
            return false;
        }
        
        // Cannot unmask a foreign binding (not belonging to the collaboration use)
        return (el.getCompositionOwner().equals(getRelatedElement()));
    }

    @objid ("340d8142-55b7-11e2-877f-002564c97630")
    @Override
    protected void updateHiddenFeatures() {
        // Nothing to do
    }

    /**
     * Get the style key for the {@link MetaKey.AttGroup#ATTSHOWGROUP} meta key.
     */
    @objid ("340d8145-55b7-11e2-877f-002564c97630")
    private StyleKey getViewModeStyleKey() {
        return getStyleKeyStrict(MetaKey.AttGroup.ATTSHOWGROUP);
    }

    @objid ("340d814c-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmBindingLabelGroup.");
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

    @objid ("340d8152-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmBindingLabelGroup.", GmBindingLabelGroup.MINOR_VERSION);
        
    }

    @objid ("340f07b9-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("340f07be-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
