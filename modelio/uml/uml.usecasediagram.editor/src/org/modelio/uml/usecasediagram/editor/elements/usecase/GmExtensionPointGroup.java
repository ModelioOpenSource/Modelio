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

package org.modelio.uml.usecasediagram.editor.elements.usecase;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.group.GmGroup;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.usecaseModel.ExtensionPoint;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.statik.VisibilityMode;
import org.modelio.uml.usecasediagram.editor.elements.extensionpoint.GmExtensionPoint;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("5e57eb9a-55b7-11e2-877f-002564c97630")
public class GmExtensionPointGroup extends GmGroup {
    @objid ("5e57eb9e-55b7-11e2-877f-002564c97630")
    private boolean isVisible = true;

    @objid ("5e57eb9f-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("5e57eba2-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("5e57eba4-55b7-11e2-877f-002564c97630")
    public GmExtensionPointGroup() {
        super();
    }

    @objid ("5e57eba7-55b7-11e2-877f-002564c97630")
    public GmExtensionPointGroup(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        refreshFromObModel();
    }

    @objid ("5e57ebb0-55b7-11e2-877f-002564c97630")
    @Override
    public void addChild(GmNodeModel toAdd) {
        super.addChild(toAdd);
        
        fireVisibilityChanged();
    }

    @objid ("5e57ebb6-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canContain(Class<? extends GmNodeModel> nodeClass) {
        return GmExtensionPoint.class.isAssignableFrom(nodeClass);
    }

    @objid ("5e57ebbf-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return ExtensionPoint.class.isAssignableFrom(type);
    }

    @objid ("5e57ebc8-55b7-11e2-877f-002564c97630")
    @Override
    public UseCase getRelatedElement() {
        return (UseCase) super.getRelatedElement();
    }

    @objid ("5e57ebd0-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        boolean styleVisible = getDisplayedStyle().getProperty(GmUseCaseStructuredStyleKeys.EXTENSIONPOINTGROUPVISIBLE);
        return styleVisible && this.isVisible;
    }

    @objid ("5e59723a-55b7-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(MObject movedEl) {
        refreshFromObModel();
    }

    @objid ("5e597240-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        
        final UseCase useCase = getRelatedElement();
        if (useCase == null || !useCase.isValid()) {
            return;
        }
        
        final StyleKey.UmaskByVisibilityStragegy mode = this.getDisplayedStyle().getProperty(GmUseCaseStructuredStyleKeys.FEATURES);
        switch (mode) {
            case ALL:
                for (ExtensionPoint part : useCase.getOwnedExtension(ExtensionPoint.class)) {
                    if (getChild(new MRef(part)) == null) {
                        getDiagram().unmask(this, part, null);
                    }
                }
                break;
        
            case ALL_PUBLIC:
                for (ExtensionPoint part : useCase.getOwnedExtension(ExtensionPoint.class)) {
                    if ((part.getVisibility() == VisibilityMode.PUBLIC) &&
                        getChild(new MRef(part)) == null) {
                        getDiagram().unmask(this, part, null);
                    }
                }
                break;
        
            case ALL_NON_PRIVATE:
                for (ExtensionPoint part : useCase.getOwnedExtension(ExtensionPoint.class)) {
                    if ((part.getVisibility() != VisibilityMode.PRIVATE) &&
                        getChild(new MRef(part)) == null) {
                        getDiagram().unmask(this, part, null);
                    }
                }
                break;
            case MANUAL:
            default:
                // unmask or hide nothing.
                break;
        }
    }

    @objid ("5e597243-55b7-11e2-877f-002564c97630")
    @Override
    public void removeChild(GmNodeModel child) {
        super.removeChild(child);
        
        // Hide the group when the last child is removed
        if (!hasChildren()) {
            this.isVisible = false;
        }
        
        fireVisibilityChanged();
    }

    @objid ("5e597249-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == GmUseCaseStructuredStyleKeys.FEATURES) {
            refreshFromObModel();
        } else if (property == GmUseCaseStructuredStyleKeys.EXTENSIONPOINTGROUPVISIBLE) {
            fireVisibilityChanged();
        } else {
            super.styleChanged(property, newValue);
        }
    }

    @objid ("5e597250-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle style) {
        refreshFromObModel();
        fireVisibilityChanged();
        super.styleChanged(style);
    }

    @objid ("5e597256-55b7-11e2-877f-002564c97630")
    @Override
    protected void doSetVisible(boolean visible) {
        getDisplayedStyle().setProperty(GmUseCaseStructuredStyleKeys.EXTENSIONPOINTGROUPVISIBLE, visible);
        this.isVisible = visible;
    }

    @objid ("5e59725a-55b7-11e2-877f-002564c97630")
    @Override
    protected boolean isValidElement(MObject el) {
        // Cannot unmask anything else than an extension point
        if (!(el instanceof ExtensionPoint) || !el.isValid()) {
            return false;
        }
        
        // Cannot unmask a foreign extension point (not belonging to the use case)
        if (!el.getCompositionOwner().equals(this.getRelatedElement())) {
            return false;
        }
        
        final ExtensionPoint ext = (ExtensionPoint) el;
        
        // Cannot unmask an attribute whose visibility does not match the current visualisation options.
        StyleKey.UmaskByVisibilityStragegy unmaskmode = getDisplayedStyle().getProperty(GmUseCaseStructuredStyleKeys.FEATURES);
        switch (unmaskmode) {
            case ALL:
                return true;
            case ALL_PUBLIC:
                return ext.getVisibility() == VisibilityMode.PUBLIC;
            case ALL_NON_PRIVATE:
                return ext.getVisibility() != VisibilityMode.PRIVATE;
            case MANUAL:
                return true;
            default:
                return false;
        }
    }

    @objid ("5e597263-55b7-11e2-877f-002564c97630")
    @Override
    protected void updateHiddenFeatures() {
        final UseCase useCase = getRelatedElement();
        final StyleKey.UmaskByVisibilityStragegy mode = this.getDisplayedStyle().getProperty(GmUseCaseStructuredStyleKeys.FEATURES);
        if (useCase == null || !useCase.isValid()) {
            return;
        }
        boolean hasHiddenFeature = false;
        
        switch (mode) {
            case ALL:
                break;
            case ALL_PUBLIC:
                for (ExtensionPoint part : useCase.getOwnedExtension()) {
                    if (part.getVisibility() != VisibilityMode.PUBLIC) {
                        hasHiddenFeature = true;
                    }
                }
                break;
            case ALL_NON_PRIVATE:
                for (ExtensionPoint part : useCase.getOwnedExtension()) {
                    if (part.getVisibility() == VisibilityMode.PRIVATE) {
                        hasHiddenFeature = true;
                    }
                }
                break;
            case MANUAL:
                hasHiddenFeature = useCase.getOwnedExtension().size() != getChildren().size();
                break;
            default:
                break;
        }
        
        setHiddenFeature(hasHiddenFeature);
    }

    @objid ("5e597266-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmExtensionPointGroup.");
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

    @objid ("5e59726c-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmExtensionPointGroup.", GmExtensionPointGroup.MINOR_VERSION);
    }

    @objid ("5e597272-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("5e597277-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
