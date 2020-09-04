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

package org.modelio.diagram.editor.sequence.elements.message;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.sequence.elements.message.label.GmMessageHeader;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.GmInfoFlowsGroup;
import org.modelio.diagram.editor.statik.elements.informationflowgroup.GmInformationFlowArrow;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.link.extensions.GmFractionalConnectionLocator;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageKind;
import org.modelio.metamodel.uml.behavior.interactionModel.MessageSort;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents a sequence diagram message.
 */
@objid ("d954683a-55b6-11e2-877f-002564c97630")
public class GmMessage extends GmLink {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d9546842-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d9546845-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("18430d91-0495-4536-94db-a70513f8bfc8")
    public static final String ROLE_INFOFLOW_ARROW = "infoflow_arrow";

    @objid ("6709571e-0f73-4562-8c84-9912264bd712")
    public static final String ROLE_INFOFLOW_GRP = "infoflow_group";

    @objid ("d954683e-55b6-11e2-877f-002564c97630")
    private static GmMessageStyleKeys KEYS = new GmMessageStyleKeys();

    @objid ("aee1409c-ce6b-433c-a404-be7f8d3ff3d5")
    private Message element;

    /**
     * Create a message link
     * 
     * @param diagram The owning diagram
     * @param obMessage The represented message, may be null.
     * @param ref The represented message reference, may not be null.
     */
    @objid ("d9546847-55b6-11e2-877f-002564c97630")
    public GmMessage(IGmDiagram diagram, Message obMessage, MRef ref) {
        super(diagram, ref);
        
        this.element = obMessage;
        
        GmFractionalConnectionLocator constraint;
        constraint = new GmFractionalConnectionLocator(0.25, 0, -20);
        addExtension(new GmInfoFlowsGroup(diagram, getRepresentedRef()), ROLE_INFOFLOW_GRP, constraint);
        
        constraint = new GmFractionalConnectionLocator(0.25, 0, 0, true);
        addExtension(new GmInformationFlowArrow(diagram, getRepresentedRef()), ROLE_INFOFLOW_ARROW, constraint);
        
        addExtension(ExtensionLocation.MiddleSE, ROLE_MAIN_LABEL, new GmMessageHeader(diagram, ref));
    }

    /**
     * For deserialization only.
     */
    @objid ("d9546853-55b6-11e2-877f-002564c97630")
    public GmMessage() {
        // Nothing to do.
    }

    @objid ("d9546856-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.element.getSendEvent();
    }

    @objid ("d954685d-55b6-11e2-877f-002564c97630")
    @Override
    public Message getRelatedElement() {
        return this.element;
    }

    @objid ("d955eeba-55b6-11e2-877f-002564c97630")
    @Override
    public Message getRepresentedElement() {
        return this.element;
    }

    @objid ("d955eec1-55b6-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmMessage.KEYS.getStyleKey(metakey);
    }

    @objid ("d955eecb-55b6-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmMessage.KEYS.getStyleKeys();
    }

    @objid ("d955eed4-55b6-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.element.getReceiveEvent();
    }

    @objid ("d955eedb-55b6-11e2-877f-002564c97630")
    MessageKind getKind() {
        return this.element.getKindOfMessage();
    }

    @objid ("d955eee1-55b6-11e2-877f-002564c97630")
    MessageSort getSort() {
        return this.element.getSortOfMessage();
    }

    @objid ("d955eee7-55b6-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.element = (Message) resolveRef(this.getRepresentedRef());
    }

    @objid ("d955eeed-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmMessage.", GmMessage.MINOR_VERSION);
    }

    @objid ("d955eef3-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("8ce29013-6de8-4f68-bf1b-cd6c4b2edc58")
    @Override
    protected void read_GmLinkV0_roles() {
        for (GmNodeModel n : getExtensions()) {
            if (n instanceof GmInfoFlowsGroup) {
                n.setRoleInComposition(ROLE_INFOFLOW_GRP);
            } else if (n instanceof GmInformationFlowArrow) {
                n.setRoleInComposition(ROLE_INFOFLOW_ARROW);
            } else {
                n.setRoleInComposition(ROLE_MAIN_LABEL);
            }
        }
    }

}
