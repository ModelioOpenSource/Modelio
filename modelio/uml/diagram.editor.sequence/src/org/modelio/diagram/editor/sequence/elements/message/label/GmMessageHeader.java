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

package org.modelio.diagram.editor.sequence.elements.message.label;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.core.ui.swt.images.ElementImageService;
import org.modelio.diagram.editor.sequence.elements.message.GmMessageStyleKeys;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.interactionModel.Message;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("d958fbfa-55b6-11e2-877f-002564c97630")
public class GmMessageHeader extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d958fbfd-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d958fc00-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d958fc02-55b6-11e2-877f-002564c97630")
    public GmMessageHeader() {
        super();
    }

    /**
     * Default c'tor.
     * 
     * @param diagram the diagram in which this Gm is created.
     * @param ref a reference to the related element.
     */
    @objid ("d958fc05-55b6-11e2-877f-002564c97630")
    public GmMessageHeader(final IGmDiagram diagram, final MRef ref) {
        super(diagram, ref);
    }

    @objid ("d95a829f-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        final Message inst = (Message) getRelatedElement();
        return MessageLabelProvider.computeSimpleLabel(inst, getDisplayedStyle().getBoolean(GmMessageStyleKeys.SHOWSEQUENCE));
    }

    @objid ("d95a82a4-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        Message message = (Message) getRelatedElement();
        if (message != null && message.isValid()) {
            setShowMetaclassIcon(message.getInvoked() != null || message.getSignalSignature() != null);
        } else {
            setShowMetaclassIcon(false);
        }
        
        super.refreshFromObModel();
    }

    /**
     * For a lifeline, the metaclass icon is the one from its represented instance's base.
     */
    @objid ("d95a82a7-55b6-11e2-877f-002564c97630")
    @Override
    public Image getMetaclassIcon() {
        Message message = (Message) getRelatedElement();
        if (message.getInvoked() != null) {
            return ElementImageService.getIcon(message.getInvoked());
        } else if (message.getSignalSignature() != null) {
            return ElementImageService.getIcon(message.getSignalSignature());
        }
        return null;
    }

    @objid ("d95a82ac-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmMessageHeader.");
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

    @objid ("d95a82b2-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmMessageHeader.", GmMessageHeader.MINOR_VERSION);
    }

    @objid ("d95a82b8-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("d95a82bd-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
