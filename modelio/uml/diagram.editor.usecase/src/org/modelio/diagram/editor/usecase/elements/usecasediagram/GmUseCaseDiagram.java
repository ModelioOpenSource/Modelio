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

package org.modelio.diagram.editor.usecase.elements.usecasediagram;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.association.GmAssocStructuredStyleKeys;
import org.modelio.diagram.editor.usecase.elements.system.GmSystem;
import org.modelio.diagram.editor.usecase.elements.usecase.GmUseCase;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagram;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * This class represents the Gm of a {@link UseCaseDiagramEditPart}.
 */
@objid ("5e82a51a-55b7-11e2-877f-002564c97630")
public class GmUseCaseDiagram extends GmAbstractDiagram {
    @objid ("5e82a524-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("5e82a527-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("7b7a61bb-5eff-11e2-b9cc-001ec947c8cc")
    private UseCaseDiagram element;

    @objid ("7b7a61bc-5eff-11e2-b9cc-001ec947c8cc")
    private static final GmUseCaseDiagramStyleKeys STYLEKEYS = new GmUseCaseDiagramStyleKeys();

    @objid ("7b7a61be-5eff-11e2-b9cc-001ec947c8cc")
    private GmSystem system;

    /**
     * Default constructor.
     * 
     * @param manager the manager needed make the link between the Ob and Gm models.
     * @param theUseCaseDiagram the diagram itself.
     * @param diagramRef a reference to the diagram.
     */
    @objid ("5e82a529-55b7-11e2-877f-002564c97630")
    public GmUseCaseDiagram(IModelManager manager, UseCaseDiagram theUseCaseDiagram, MRef diagramRef) {
        super(manager, diagramRef);
        this.element = theUseCaseDiagram;
    }

    @objid ("5e82a538-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (Actor.class.isAssignableFrom(type)) || (UseCase.class.isAssignableFrom(type)) || (InformationItem.class.isAssignableFrom(type));
    }

    @objid ("5e82a540-55b7-11e2-877f-002564c97630")
    @Override
    public boolean doCanUnmask(MObject el) {
        return true;
    }

    @objid ("5e842bbd-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(Class<? extends MObject> metaclass) {
        if (canCreate(metaclass)) {
            return this;
        }
        // else
        return null;
    }

    @objid ("5e842bc7-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("5e842bce-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmUseCaseDiagram.STYLEKEYS.getStyleKey(metakey);
    }

    @objid ("5e842bd8-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmUseCaseDiagram.STYLEKEYS.getStyleKeys();
    }

    @objid ("5e842be1-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, "GmUseCaseDiagram.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version
            read_1(in);
            break;
        }
        }
    }

    @objid ("5e842be7-55b7-11e2-877f-002564c97630")
    @Override
    public UseCaseDiagram getRepresentedElement() {
        return this.element;
    }

    @objid ("5e842bee-55b7-11e2-877f-002564c97630")
    @Override
    public UseCaseDiagram getRelatedElement() {
        return getRepresentedElement();
    }

    /**
     * @return the system boundaries.
     */
    @objid ("5e842bf5-55b7-11e2-877f-002564c97630")
    public GmSystem getSystem() {
        if (this.system == null) {
            this.system = new GmSystem(this, getRepresentedRef());
            addChild(getSystem());
        }
        return this.system;
    }

    @objid ("5e842bfa-55b7-11e2-877f-002564c97630")
    @Override
    public void addChild(final GmNodeModel child) {
        if (child instanceof GmUseCase &&
                ((Boolean) getDisplayedStyle().getProperty(GmUseCaseDiagramStyleKeys.SHOW_SYSTEM)).booleanValue()) {
            MObject theUseCase = child.getRepresentedElement();
            GmSystem s = getSystem();
            if (theUseCase != null &&
                    theUseCase.getCompositionOwner().equals(s.getRepresentedElement())) {
                s.addChild(child);
                return;
            }
        }
        // Child is not a Use case,
        // or System is set "not visible" by the style,
        // or use case doesn't belong to system's element
        super.addChild(child);
    }

    @objid ("5e85b25d-55b7-11e2-877f-002564c97630")
    @Override
    public void removeChild(final GmNodeModel child) {
        super.removeChild(child);
        if (child == this.system) {
            this.system = null;
        }
    }

    @objid ("5e85b264-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final IStyle changedStyle) {
        super.styleChanged(changedStyle);
        
        if (getSystem() != null) {
            fireChildVisibilityChanged(getSystem());
        }
    }

    @objid ("5e85b26b-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(final StyleKey property, final Object newValue) {
        if (property == GmUseCaseDiagramStyleKeys.SHOW_SYSTEM) {
            if (Boolean.TRUE.equals(newValue)) {
                if (getSystem() != null) {
                    fireChildVisibilityChanged(getSystem());
                }
                super.styleChanged(property, newValue);
            } else {
                super.styleChanged(property, newValue);
                if (getSystem() != null) {
                    fireChildVisibilityChanged(getSystem());
                }
            }
        } else {
            super.styleChanged(property, newValue);
        }
    }

    @objid ("5e85b274-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, "GmUseCaseDiagram.", GmUseCaseDiagram.MINOR_VERSION);
    }

    @objid ("5e85b27a-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        read_1(in);
        
        initStyleKeys(getPersistedStyle());
    }

    @objid ("5e85b27f-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmUseCaseDiagram.MAJOR_VERSION;
    }

    @objid ("96f07fc0-5d3a-496d-b7ca-ae77142064f8")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return UseCaseDiagramSymbolViewModelProvider.create(getPersistedStyle(), this);
    }

    /**
     * Creates the diagram style from the style manager default style with some more properties to hide association decorations.
     */
    @objid ("af4107bf-2b0f-4fd5-91e7-4b6534caa825")
    @Override
    protected IStyle createStyle(IGmDiagram aDiagram) {
        IStyle style = super.createStyle(aDiagram);
        initStyleKeys(style);
        return style;
    }

    /**
     * Force a few properties in the style.
     */
    @objid ("679b7621-43fe-419d-aa44-7acc4a30aff3")
    private void initStyleKeys(IStyle style) {
        style.setProperty(GmAssocStructuredStyleKeys.SHOWCARD, false);
        style.setProperty(GmAssocStructuredStyleKeys.SHOWLABEL, false);
        style.setProperty(GmAssocStructuredStyleKeys.SHOWNAVIGABILITY, false);
        style.setProperty(GmAssocStructuredStyleKeys.SHOWROLES, false);
        style.setProperty(GmAssocStructuredStyleKeys.CONNECTIONROUTER, ConnectionRouterId.DIRECT);
    }

    @objid ("12e3b787-fa10-481f-8ac0-fbb3f9301f7e")
    private void read_1(IDiagramReader in) {
        super.read(in);
        this.element = (UseCaseDiagram) resolveRef(getRepresentedRef());
        for (GmNodeModel child : getChildren()) {
            if (child instanceof GmSystem) {
                this.system = (GmSystem) child;
            }
        }
    }

    @objid ("fab01c4c-add5-4b79-a2e2-0c5baaf3f901")
    @Override
    public String getFactoryIdentifier() {
        return UseCaseDiagram.MNAME;
    }

    @objid ("43d17dfe-ee70-4e6e-96c9-c808bb77f091")
    @Override
    public boolean canUnmaskGenericElements() {
        return true;
    }

}
