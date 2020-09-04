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

package org.modelio.diagram.editor.usecase.elements.usecase;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.editor.usecase.elements.usecase.v0._GmUseCase;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("5e5af8e7-55b7-11e2-877f-002564c97630")
public class GmUseCase extends GmPortContainer {
    @objid ("5e5af8eb-55b7-11e2-877f-002564c97630")
    private UseCase useCase;

    @objid ("5e5af8f4-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("5e5af8f7-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 1;

    @objid ("5e5af8f9-55b7-11e2-877f-002564c97630")
    private static final String IMAGE_MODE_HEADER = "image mode header";

    @objid ("7b9490fc-5eff-11e2-b9cc-001ec947c8cc")
     static final GmUseCaseSimpleStyleKeys SIMPLE_KEYS = new GmUseCaseSimpleStyleKeys();

    @objid ("7b9490fe-5eff-11e2-b9cc-001ec947c8cc")
    private static final GmUseCaseStructuredStyleKeys STRUCTURED_KEYS = new GmUseCaseStructuredStyleKeys();

    @objid ("7b949100-5eff-11e2-b9cc-001ec947c8cc")
    private static final GmUseCaseImageStyleKeys IMAGE_KEYS = new GmUseCaseImageStyleKeys();

    @objid ("48927e75-bd7c-4a15-bddd-aac4f00df231")
    private static final GmUseCaseUserImageStyleKeys USERIMAGE_KEYS = new GmUseCaseUserImageStyleKeys();

    @objid ("5e5af8fb-55b7-11e2-877f-002564c97630")
    public GmUseCase(IGmDiagram diagram, UseCase theUseCase, MRef ref) {
        super(diagram, ref);
        this.useCase = theUseCase;
        
        GmUseCasePrimaryNode primary = new GmUseCasePrimaryNode(diagram, ref);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(diagram, ref);
        imageModeHeader.setRoleInComposition(IMAGE_MODE_HEADER);
        imageModeHeader.setLayoutData(PositionConstants.SOUTH);
        
        addChild(imageModeHeader);
    }

    @objid ("5e5af907-55b7-11e2-877f-002564c97630")
    public GmUseCase() {
        // empty constructor for the serialization
    }

    @objid ("5e5af90a-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return false;
    }

    @objid ("5e5af912-55b7-11e2-877f-002564c97630")
    @Override
    public UseCase getRepresentedElement() {
        return this.useCase;
    }

    @objid ("5e5af919-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        StyleKey ret = STRUCTURED_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = SIMPLE_KEYS.getStyleKey(metakey);
        if (ret != null) {
            return ret;
        }
        
        ret = IMAGE_KEYS.getStyleKey(metakey);
        return ret;
    }

    @objid ("5e5c7f79-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        if (getMainNode() != null) {
            switch (getMainNode().getRepresentationMode()) {
                case SIMPLE:
                    return SIMPLE_KEYS.getStyleKeys();
                case STRUCTURED:
                    return STRUCTURED_KEYS.getStyleKeys();
                case USER_IMAGE: 
                    return USERIMAGE_KEYS.getStyleKeys();
                case IMAGE:
                    return IMAGE_KEYS.getStyleKeys();
                default:
                    return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    @objid ("5e5c7f81-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmUseCase.");
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

    @objid ("5e5c7f87-55b7-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        super.refreshFromObModel();
        // forcing visual refresh in case Image changed
        firePropertyChange(PROPERTY_LAYOUTDATA, null, getLayoutData());
    }

    @objid ("5e5c7f8a-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode:
           List<GmNodeModel> ret = super.getVisibleChildren();
           if (getMainNode() != null) {
               switch (getMainNode().getRepresentationMode()) {
                   case STRUCTURED:
                   case SIMPLE:
                       GmNodeModel imageModeHeader = getFirstChild(IMAGE_MODE_HEADER);
                       ret.remove(imageModeHeader);
                       break;
                   case IMAGE:
                   default:
                       break;
        
               }
           }
        return ret;
    }

    @objid ("5e5c7f93-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("5e5c7f9a-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmUseCase.", GmUseCase.MINOR_VERSION);
    }

    @objid ("5e5c7fa0-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("5e5c7fa5-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        
        this.useCase = (UseCase) resolveRef(getRepresentedRef());
    }

    @objid ("5e5c7fb1-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return IMAGE_MODE_HEADER.equals(childNode.getRoleInComposition());
    }

    @objid ("5e5e0619-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return false;
    }

    @objid ("5e5e0623-55b7-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
    }

    @objid ("5e5e062a-55b7-11e2-877f-002564c97630")
    @Override
    public void addEndingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addEndingLink(link);
        } else {
            super.addEndingLink(link);
        }
    }

    @objid ("5e5e0631-55b7-11e2-877f-002564c97630")
    GmUseCase(final _GmUseCase oldVersionGm) {
        super(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        this.useCase = oldVersionGm.getRepresentedElement();
        
        GmUseCasePrimaryNode primary = new GmUseCasePrimaryNode(oldVersionGm);
        primary.setRoleInComposition(GmPortContainer.MAIN_NODE_ROLE);
        addChild(primary);
        
        GmDefaultModelElementLabel imageModeHeader = new GmDefaultModelElementLabel(oldVersionGm.getDiagram(), oldVersionGm.getRepresentedRef());
        imageModeHeader.setRoleInComposition(IMAGE_MODE_HEADER);
        imageModeHeader.setLayoutData(PositionConstants.SOUTH);
        
        addChild(imageModeHeader);
    }

    @objid ("3fcd97f7-7c85-4512-89e7-e0895a8854ec")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(IMAGE_MODE_HEADER);
    }

}
