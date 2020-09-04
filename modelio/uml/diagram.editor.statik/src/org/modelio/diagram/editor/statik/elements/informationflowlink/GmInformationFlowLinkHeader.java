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

package org.modelio.diagram.editor.statik.elements.informationflowlink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Information flow label.
 * 
 * @author cmarin
 */
@objid ("35063928-55b7-11e2-877f-002564c97630")
public class GmInformationFlowLinkHeader extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3506392c-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3506392f-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor.
     * @param diagram the diagram
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("35063931-55b7-11e2-877f-002564c97630")
    public GmInformationFlowLinkHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        this.setShowMetaclassIcon(false);
        this.setShowMetaclassKeyword(true);
    }

    /**
     * For deserialization only.
     */
    @objid ("3506393a-55b7-11e2-877f-002564c97630")
    public GmInformationFlowLinkHeader() {
    }

    /**
     * The label is not editable: the "editor" should be the picking mode.
     */
    @objid ("3507bfb0-55b7-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        return super.getEditableText();
    }

    @objid ("3507bfb8-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    /**
     * The label is "flow" instead of "InformationFlow".
     */
    @objid ("3507bfd1-55b7-11e2-877f-002564c97630")
    @Override
    protected String getMetaclassKeyword() {
        return "flow";
    }

    @objid ("3507bfd7-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmInformationFlowLinkHeader.");
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

    @objid ("3507bfdd-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmInformationFlowLinkHeader.", Integer.valueOf(MINOR_VERSION));
    }

    @objid ("3509463d-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("35094642-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
