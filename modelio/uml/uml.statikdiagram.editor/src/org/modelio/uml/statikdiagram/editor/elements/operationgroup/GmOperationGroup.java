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
package org.modelio.uml.statikdiagram.editor.elements.operationgroup;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Feature;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Operations group model.
 */
@objid ("96e36f3b-55b6-11e2-877f-002564c97630")
public class GmOperationGroup extends GmGroup {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("35fef0dc-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35fef0df-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("35fef0e1-55b7-11e2-877f-002564c97630")
    public  GmOperationGroup() {
        super();
    }

    /**
     * Creates an operation group.
     * @param diagram The diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("35fef0e4-55b7-11e2-877f-002564c97630")
    public  GmOperationGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        refreshFromObModel();
        
    }

    @objid ("35fef0ed-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return type == Operation.class;
    }

    /**
     * The operations group can be visible only if the {@link MetaKey.OperationGroup#OPSHOWGROUP} property is true.
     */
    @objid ("35fef0f5-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        if (getParent() != null && getParent().getRepresentationMode() == RepresentationMode.STRUCTURED) {
            return getDisplayedStyle().getProperty(getGroupVisibleStyleKey());
        }
        return false;
    }

    @objid ("35fef0fb-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        //TODO : move this in parent class
        firePropertyChange(PROP_REFRESH_FROM_OBMODEL, null, this);
        
    }

    @objid ("35fef0fe-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == getFilterStyleKey()) {
            refreshFromObModel();
        } else if (property == getGroupVisibleStyleKey()) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
        
    }

    @objid ("35fef105-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        refreshFromObModel();
        fireVisibilityChanged();
        super.styleChanged(style);
        
    }

    @objid ("3600777c-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        if (visible) {
            getParent().getDisplayedStyle().setProperty(getStyleKey(MetaKey.REPMODE), RepresentationMode.STRUCTURED);
        }
        getDisplayedStyle().setProperty(getGroupVisibleStyleKey(), visible);
        
    }

    @objid ("36007780-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidElement(MObject el) {
        // Cannot unmask anything else than a valid operation
        if (!(el instanceof Operation) || !el.isValid()) {
            return false;
        }
        
        // Cannot unmask a foreign operation (not belonging to the class)
        if (!el.getCompositionOwner().equals(getRelatedElement())) {
            return false;
        }
        
        final Operation op = (Operation) el;
        
        StyleKey.UmaskByVisibilityStragegy unmaskmode = getVisibilityFilter(StyleKey.UmaskByVisibilityStragegy.ALL);
        switch (unmaskmode) {
        case ALL:
            return true;
        case ALL_PUBLIC:
            return op.getVisibility() == VisibilityMode.PUBLIC;
        case ALL_NON_PRIVATE:
            return op.getVisibility() != VisibilityMode.PRIVATE;
        case MANUAL:
            return true;
        }
        return false;
    }

    @objid ("36007788-55b7-11e2-877f-002564c97630")
    @Override
    protected void updateHiddenFeatures() {
        final Classifier classifier = (Classifier) getRelatedElement();
        if (classifier != null && classifier.isValid()) {
            boolean hasHiddenFeature = false;
        
            final StyleKey.UmaskByVisibilityStragegy mode = getVisibilityFilter(StyleKey.UmaskByVisibilityStragegy.ALL);
            switch (mode) {
            case ALL:
                break;
        
            case ALL_PUBLIC:
                for (Feature part : classifier.getOwnedOperation()) {
                    if (part.getVisibility() != VisibilityMode.PUBLIC) {
                        hasHiddenFeature = true;
                    }
                }
                break;
        
            case ALL_NON_PRIVATE:
                for (Feature part : classifier.getOwnedOperation()) {
                    if (part.getVisibility() == VisibilityMode.PRIVATE) {
                        hasHiddenFeature = true;
                    }
                }
                break;
            case MANUAL:
                hasHiddenFeature = classifier.getOwnedOperation().size() != getChildren().size();
                break;
            }
        
            setHiddenFeature(hasHiddenFeature);
        
        }
        
    }

    /**
     * This group can contain only labels.
     */
    @objid ("3600778b-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmElementLabel.class.isAssignableFrom(nodeClass);
    }

    @objid ("36007794-55b7-11e2-877f-002564c97630")
    private StyleKey getGroupVisibleStyleKey() {
        return getStyleKey(MetaKey.OperationGroup.OPSHOWGROUP);
    }

    @objid ("3600779a-55b7-11e2-877f-002564c97630")
    private StyleKey getFilterStyleKey() {
        return getStyleKey(MetaKey.VISIBILITYFILTER);
    }

    @objid ("360077a0-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmOperationGroup.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        default: {
            assert (false) :readVersion + " version number not covered!";
            // reading as last handled version: 0
            read_0(in);
            break;
        }
        }
        
    }

    @objid ("360077a6-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmOperationGroup.", GmOperationGroup.MINOR_VERSION);
        
    }

    @objid ("360077ac-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("360077b1-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmOperationGroup.MAJOR_VERSION;
    }

    @objid ("93ab42a9-daeb-46ed-86e0-1de1ccf338b2")
    public org.modelio.diagram.styles.core.StyleKey.UmaskByVisibilityStragegy getVisibilityFilter(org.modelio.diagram.styles.core.StyleKey.UmaskByVisibilityStragegy ifNull) {
        StyleKey styleKey = getStyleKey(MetaKey.VISIBILITYFILTER);
        if (styleKey == null) {
            return ifNull;
        }
        return getDisplayedStyle().getProperty(styleKey);
    }

}
