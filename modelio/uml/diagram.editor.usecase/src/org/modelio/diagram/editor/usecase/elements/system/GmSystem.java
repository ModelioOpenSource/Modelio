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

package org.modelio.diagram.editor.usecase.elements.system;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.usecase.elements.usecase.GmUseCase;
import org.modelio.diagram.editor.usecase.elements.usecasediagram.GmUseCaseDiagramStyleKeys;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.common.header.GmModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.diagrams.UseCaseDiagram;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("5e4bb69a-55b7-11e2-877f-002564c97630")
public class GmSystem extends GmCompositeNode {
    @objid ("5e4bb6a5-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 1;

    @objid ("5e4bb6a8-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("d9d0586d-55c2-11e2-9337-002564c97630")
    private GmModelElementHeader header;

    @objid ("7bf88b3a-5eff-11e2-b9cc-001ec947c8cc")
    private static final GmSystemStyleKeys KEYS = new GmSystemStyleKeys();

    @objid ("7bf88b3c-5eff-11e2-b9cc-001ec947c8cc")
    private GmSystemFreeZone body;

    @objid ("5e4bb6aa-55b7-11e2-877f-002564c97630")
    public GmSystem(final IGmDiagram diagram, final MRef ref) {
        super(diagram, ref);
        this.header = new GmDefaultModelElementHeader(diagram, ref);
        this.header.setRoleInComposition("header");
        
        this.body = new GmSystemFreeZone(diagram, ref);
        this.body.setRoleInComposition("body");
        
        super.addChild(this.header);
        super.addChild(this.body);
    }

    @objid ("5e4bb6b5-55b7-11e2-877f-002564c97630")
    public GmSystem() {
        // empty constructor for the serialization
    }

    @objid ("5e4bb6b8-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(final Class<? extends MObject> type) {
        return UseCase.class.isAssignableFrom(type);
    }

    @objid ("5e4bb6c1-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(final MObject el) {
        return el instanceof UseCase &&
                                                                               el.isValid() &&
                                                                               el.getCompositionOwner().equals(getRepresentedElement());
    }

    @objid ("5e4d3d40-55b7-11e2-877f-002564c97630")
    @Override
    public GmCompositeNode getCompositeFor(final Class<? extends MObject> metaclass) {
        return this;
    }

    @objid ("5e4d3d4b-55b7-11e2-877f-002564c97630")
    @Override
    public ModelElement getRepresentedElement() {
        UseCaseDiagram diagram = (UseCaseDiagram) this.getDiagram().getRelatedElement();
        if (diagram != null && diagram.isValid()) {
            return diagram.getOrigin();
        }
        return null;
    }

    @objid ("5e4d3d52-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("5e4d3d59-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(final MetaKey metakey) {
        return KEYS.getStyleKey(metakey);
    }

    @objid ("5e4d3d64-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return KEYS.getStyleKeys();
    }

    @objid ("5e4d3d6d-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmSystem.");
        switch (readVersion) {
            case 1: {
                read_1(in);
                break;
            }
            case 0: {
                read_0(in);
                break;
            }
            default: {
                assert (false) : "version number not covered!";
                // reading as last handled version: 1
                read_1(in);
                break;
            }
        }
    }

    @objid ("5e4d3d74-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        // Nothing specific to do.
    }

    @objid ("5e4d3d77-55b7-11e2-877f-002564c97630")
    @Override
    public void addChild(final GmNodeModel child) {
        if (child instanceof GmUseCase) {
            this.body.addChild(child);
            setVisible(true);
        } else {
            super.addChild(child);
        }
    }

    @objid ("5e4ec3d9-55b7-11e2-877f-002564c97630")
    @Override
    public void removeChild(final GmNodeModel child) {
        if (child instanceof GmUseCase) {
            this.body.removeChild(child);
            setVisible(isVisible());
        } else {
            super.removeChild(child);
        }
    }

    @objid ("5e4ec3e0-55b7-11e2-877f-002564c97630")
    private List<GmNodeModel> getChildUseCases() {
        List<GmNodeModel> ret = new ArrayList<>();
        
        for (GmNodeModel node : this.body.getChildren()) {
            if (node instanceof GmUseCase) {
                GmUseCase useCaseNode = (GmUseCase) node;
                MObject theUseCase = useCaseNode.getRepresentedElement();
                if (theUseCase == null ||
                    (theUseCase.isValid() && theUseCase.getCompositionOwner()
                                                       .equals(getRepresentedElement()))) {
                    ret.add(useCaseNode);
                }
            }
        }
        return ret;
    }

    @objid ("5e4ec3e9-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isVisible() {
        final boolean isSystemVisible = getDisplayedStyle().getProperty(GmUseCaseDiagramStyleKeys.SHOW_SYSTEM);
        return isSystemVisible && (!getChildUseCases().isEmpty());
    }

    @objid ("5e4ec3ee-55b7-11e2-877f-002564c97630")
    public GmSystemFreeZone getBody() {
        return this.body;
    }

    @objid ("5e4ec3f3-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmSystem.", GmSystem.MINOR_VERSION);
    }

    @objid ("5e4ec3f9-55b7-11e2-877f-002564c97630")
    @Override
    public void fireChildVisibilityChanged(final GmNodeModel child) {
        fireVisibilityChanged();
        firePropertyChange(PROPERTY_CHILDREN, null, child);
    }

    @objid ("5e4ec400-55b7-11e2-877f-002564c97630")
    private void read_1(final IDiagramReader in) {
        super.read(in);
        this.header = (GmModelElementHeader) getFirstChild("header");
        this.body = (GmSystemFreeZone) getFirstChild("body");
    }

    @objid ("5e4ec406-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        // roles were introduced with version 1, we have to do positional reading here.
        super.read(in);
        this.header = (GmModelElementHeader) getChildren().get(0);
        this.header.setRoleInComposition("header");
        this.body = (GmSystemFreeZone) getChildren().get(1);
        this.body.setRoleInComposition("body");
    }

    @objid ("5e4ec40c-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
