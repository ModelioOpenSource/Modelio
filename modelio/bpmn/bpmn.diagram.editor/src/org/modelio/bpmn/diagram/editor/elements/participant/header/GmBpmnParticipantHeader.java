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

package org.modelio.bpmn.diagram.editor.elements.participant.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.GmAbstractObject;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.factories.StandardEditPartFactory;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.bpmn.processCollaboration.BpmnParticipant;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * <p>
 * Specialisation of the default model element header: uses a "special" style that returns a darker shade of the partition background colour.
 * </p>
 * <p>
 * Also this class is needed so that the {@link StandardEditPartFactory} instantiate the correct EditPart to have the correct selection behaviours (very specific to partition: only header is "click-able" but clicking on it selects the whole partition).
 * </p>
 */
@objid ("7660e25e-3fac-44ca-9fcf-fa8148d2e494")
public class GmBpmnParticipantHeader extends GmDefaultModelElementHeader {
    @objid ("6d803926-3ca6-4f0b-b8cd-e7e92f818c86")
    private static final int MAJOR_VERSION = 0;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3cc2a65a-ca7d-4916-a446-a3fce47de455")
    private static final int MINOR_VERSION = 0;

    @objid ("f9743d88-0828-4cf7-a987-25803f1254d2")
    private static final String MINOR_VERSION_PREFIX = GmBpmnParticipantHeader.class.getSimpleName() + ".";

    @objid ("c74ee272-20f7-4ae1-b08d-3d94fb1fc2a3")
    private boolean isHorizontal = false;

    /**
     * C'tor without args for deserialization.
     */
    @objid ("a5771ec2-4da0-46da-ae90-e3ace1be568e")
    public GmBpmnParticipantHeader() {
        // Nothing to do.
    }

    /**
     * C'tor.
     * 
     * @param diagram the owning diagram.
     * @param relatedRef the element reference.
     */
    @objid ("94d142b0-a668-4060-9d81-7e2c748fa1b6")
    public GmBpmnParticipantHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("a52836e0-4c9e-4031-9401-eb8d4797596e")
    @Override
    public int getMajorVersion() {
        return GmBpmnParticipantHeader.MAJOR_VERSION;
    }

    @objid ("83f44d5f-fa14-4d27-a343-b13bb9f0b17a")
    @Override
    public Image getMetaclassIcon() {
        return ElementImageService.getIcon(getRelatedElement());
    }

    @objid ("fbb635c2-3a30-4979-967d-2a5b6d090850")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = GmAbstractObject.readMinorVersion(in, GmBpmnParticipantHeader.MINOR_VERSION_PREFIX);
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

    @objid ("cb66d838-2a4d-4bd4-b644-5cccb7a2c04e")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        // Write version of this Gm if different of 0
        GmAbstractObject.writeMinorVersion(out, GmBpmnParticipantHeader.MINOR_VERSION_PREFIX, GmBpmnParticipantHeader.MINOR_VERSION);
    }

    @objid ("26e4705b-78b1-41a0-8e85-cb0c6642f2ed")
    @Override
    protected String computeMainLabel() {
        final BpmnParticipant participant = getRelatedElement();
        return ParticipantSymbolProvider.computeLabel(participant);
    }

    @objid ("7e013a34-96ed-486e-9926-f632387e229d")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("94f6825a-7306-40cb-8ccc-9a788b4c139a")
    @Override
    public IEditableText getEditableText() {
        if (isReadonly()) {
            return null;
        }
        return new IEditableText() {
        
                    @Override
                    public String getText() {
                        return computeMainLabel();
                    }
        
                    @Override
                    public void setText(String text) {
                        final BpmnParticipant participant = getRelatedElement();
                        participant.setName(text);
                    }
                };
    }

    @objid ("ac4b1fc6-0fa1-48ed-8199-31c71f5922a7")
    @Override
    public BpmnParticipant getRelatedElement() {
        return (BpmnParticipant) super.getRelatedElement();
    }

    /**
     * @return <code>true</code> if the participant is shell or null.
     */
    @objid ("a765439f-f118-48b3-99ce-97a880874622")
    private boolean isReadonly() {
        BpmnParticipant participant = getRelatedElement();
        if (participant == null || !participant.isValid()) {
            // Assume that a shell participant is extern
            return true;
        }
        return false;
    }

    @objid ("8a815121-469f-42e5-93d5-1eaf6ed71b06")
    public boolean isHorizontal() {
        return this.isHorizontal;
    }

    @objid ("0b00c13b-22d7-4be2-af91-10be32272760")
    public void setHorizontal(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
    }

}
