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

package org.modelio.diagram.editor.statik.elements.informationconveyed;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.informationitem.GmInformationItemLabel;
import org.modelio.diagram.editor.statik.elements.namespacelabel.GmNameSpaceLabel;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * {@linkplain InformationFlow information flow} {@linkplain GmInformationItemLabel items} group.
 * 
 * @author cmarin
 */
@objid ("34fe97e8-55b7-11e2-877f-002564c97630")
public class GmConveyedClassifiersGroup extends GmGroup {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("34fe97ec-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("34fe97ef-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("34fe97f1-55b7-11e2-877f-002564c97630")
    public GmConveyedClassifiersGroup() {
    }

    /**
     * Creates a binding group.
     * 
     * @param diagram The diagram.
     * @param relatedRef The related element reference, must not be null.
     */
    @objid ("34fe97f4-55b7-11e2-877f-002564c97630")
    public GmConveyedClassifiersGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * The binding group only support {@link GmInformationItemLabel} and {@link GmNameSpaceLabel} nodes.
     */
    @objid ("34fe97fd-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return (GmInformationItemLabel.class.isAssignableFrom(nodeClass) || GmNameSpaceLabel.class.isAssignableFrom(nodeClass));
    }

    /**
     * Only Classifiers can be created here.
     */
    @objid ("34fe9806-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return Classifier.class.isAssignableFrom(type);
    }

    @objid ("34fe980f-55b7-11e2-877f-002564c97630")
    @Override
    public InformationFlow getRelatedElement() {
        return (InformationFlow) super.getRelatedElement();
    }

    /**
     * The information items group can be visible only if the {@link MetaKey.InformationItemGroup#INFSHOWGROUP} property
     * is true.
     */
    @objid ("34fe9816-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        return getParent() != null && (Boolean) getDisplayedStyle().getProperty(getViewModeStyleKey());
    }

    @objid ("34fe981c-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        final InformationFlow flow = getRelatedElement();
        if (flow != null && flow.isValid()) {
            final List<Classifier> conveyedClassifiers = flow.getConveyed();
        
            // Remove obsolete nodes
            for (GmNodeModel c : getChildren()) {
                if (!isValidElement(c.getRelatedElement())) {
                    c.delete();
                }
            }
        
            // Add missing nodes
            for (Classifier part : conveyedClassifiers) {
                if (getChild(new MRef(part)) == null) {
                    getDiagram().unmask(this, part, null);
                }
            }
        }
    }

    @objid ("34fe981f-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == getViewModeStyleKey()) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
    }

    @objid ("35001e7f-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        refreshFromObModel();
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("35001e85-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        getDisplayedStyle().setProperty(getViewModeStyleKey(), visible);
    }

    @objid ("35001e89-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidElement(MObject el) {
        // Cannot unmask anything else than a valid InformationItem or a Classifier
        if (!(el instanceof Classifier) || !el.isValid()) {
            return false;
        }
        
        // Cannot unmask a foreign InformationItem (not conveyed by the information flow)
        return getRelatedElement().getConveyed().contains(el);
    }

    @objid ("35001e91-55b7-11e2-877f-002564c97630")
    @Override
    protected void updateHiddenFeatures() {
        // Nothing to do
    }

    /**
     * Get the style key for the {@link MetaKey.InformationItemGroup#INFSHOWGROUP} meta key.
     */
    @objid ("35001e94-55b7-11e2-877f-002564c97630")
    private StyleKey getViewModeStyleKey() {
        return getStyleKeyStrict(MetaKey.InformationItemGroup.INFSHOWGROUP);
    }

    @objid ("35001e9b-55b7-11e2-877f-002564c97630")
    @Override
    public void obElementsUpdated() {
        refreshFromObModel();
    }

    @objid ("35001e9e-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmConveyedClassifiersGroup.");
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

    @objid ("35001ea4-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmConveyedClassifiersGroup.", GmConveyedClassifiersGroup.MINOR_VERSION);
    }

    @objid ("35001eaa-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("35001eaf-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmConveyedClassifiersGroup.MAJOR_VERSION;
    }

}
