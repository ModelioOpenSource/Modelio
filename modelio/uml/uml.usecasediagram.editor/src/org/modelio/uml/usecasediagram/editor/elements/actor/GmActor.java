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

package org.modelio.uml.usecasediagram.editor.elements.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.draw2d.PositionConstants;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.portcontainer.GmPortContainer;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.usecaseModel.Actor;
import org.modelio.metamodel.uml.statik.Port;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("5e34d37a-55b7-11e2-877f-002564c97630")
public class GmActor extends GmPortContainer {
    @objid ("5e3659e1-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("5e3659e4-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("5e3659db-55b7-11e2-877f-002564c97630")
    private static final GmActorSimpleStyleKeys SIMPLE_KEYS = new GmActorSimpleStyleKeys();

    @objid ("7b7f2481-5eff-11e2-b9cc-001ec947c8cc")
    private Actor element;

    @objid ("7b7f2482-5eff-11e2-b9cc-001ec947c8cc")
     static final GmActorStructuredStyleKeys STRUCTURED_KEYS = new GmActorStructuredStyleKeys();

    @objid ("7b7f2484-5eff-11e2-b9cc-001ec947c8cc")
    private static final GmActorImageStyleKeys IMAGE_KEYS = new GmActorImageStyleKeys();

    @objid ("b476c24a-1b66-4193-8b9a-28e75437d643")
    private static final GmActorUserImageStyleKeys USERIMAGE_KEYS = new GmActorUserImageStyleKeys();

    @objid ("5e3659e6-55b7-11e2-877f-002564c97630")
    public GmActor() {
        // Nothing specific to do.
    }

    @objid ("5e3659e9-55b7-11e2-877f-002564c97630")
    public GmActor(IGmDiagram diagram, Actor el, MRef ref) {
        super(diagram, ref);
        
        GmActorPrimaryNode mainNode = new GmActorPrimaryNode(diagram, ref);
        mainNode.setRoleInComposition(MAIN_NODE_ROLE);
        addChild(mainNode);
        
        this.element = el;
        
        final GmDefaultModelElementLabel actorLabel = new GmDefaultModelElementLabel(diagram, ref);
        actorLabel.setRoleInComposition(SATELLITE_ROLE);
        actorLabel.setLayoutData(Integer.valueOf(PositionConstants.SOUTH));
        
        addChild(actorLabel);
    }

    @objid ("5e3659f5-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canCreate(Class<? extends MObject> type) {
        return (Port.class.isAssignableFrom(type));
    }

    @objid ("5e3659fd-55b7-11e2-877f-002564c97630")
    @Override
    public boolean canUnmask(MObject el) {
        return (Port.class.isAssignableFrom(el.getClass()) && el.getCompositionOwner().equals(this.element));
    }

    @objid ("5e365a05-55b7-11e2-877f-002564c97630")
    @Override
    public Actor getRepresentedElement() {
        return this.element;
    }

    @objid ("5e365a0c-55b7-11e2-877f-002564c97630")
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

    @objid ("5e365a16-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        switch (getRepresentationMode()) {
            case IMAGE:
                return IMAGE_KEYS.getStyleKeys();
            case USER_IMAGE:
                return USERIMAGE_KEYS.getStyleKeys();
            case SIMPLE:
                return SIMPLE_KEYS.getStyleKeys();
            case STRUCTURED:
                return STRUCTURED_KEYS.getStyleKeys();
            default:
                return Collections.emptyList();
        }
    }

    @objid ("5e365a1e-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmActor.");
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

    @objid ("5e37e07e-55b7-11e2-877f-002564c97630")
    @Override
    public List<GmNodeModel> getVisibleChildren() {
        // Returned result depends on current representation mode of the main node:
        GmNodeModel firstChild = getMainNode();
        if (firstChild != null && firstChild.getRepresentationMode() == RepresentationMode.STRUCTURED) {
            List<GmNodeModel> ret = new ArrayList<>(1);
            ret.add(firstChild);
            return ret;
        }
        return super.getVisibleChildren();
    }

    @objid ("5e37e087-55b7-11e2-877f-002564c97630")
    @Override
    public Actor getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("5e37e08e-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmActor.", GmActor.MINOR_VERSION);
    }

    @objid ("5e37e094-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (Actor) resolveRef(this.getRepresentedRef());
    }

    @objid ("5e37e099-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("5e37e0a6-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isPort(final GmNodeModel childNode) {
        return GmPortContainer.PORT_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("5e37e0b0-55b7-11e2-877f-002564c97630")
    @Override
    public boolean isSatellite(final GmNodeModel childNode) {
        return GmPortContainer.SATELLITE_ROLE.equals(childNode.getRoleInComposition());
    }

    @objid ("5e396719-55b7-11e2-877f-002564c97630")
    @Override
    public void addStartingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addStartingLink(link);
        } else {
            super.addStartingLink(link);
        }
    }

    @objid ("5e396720-55b7-11e2-877f-002564c97630")
    @Override
    public void addEndingLink(final IGmLink link) {
        if (getMainNode() != null) {
            getMainNode().addEndingLink(link);
        } else {
            super.addEndingLink(link);
        }
    }

    @objid ("7ef0a52b-13ba-44ba-a5e4-c8d067c83039")
    @Override
    public boolean isMainSatelliteLabel(GmNodeModel childNode) {
        String role = childNode.getRoleInComposition();
        return role.equals(SATELLITE_ROLE);
    }

}
