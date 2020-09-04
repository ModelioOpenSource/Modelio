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

package org.modelio.diagram.editor.statik.elements.elementRealization;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * MObject import header displayed on the node link.
 * 
 * @author cmarin
 */
@objid ("b32f9733-4171-4873-ada9-98f03f2f8168")
public class GmElementRealizationHeader extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d3f0330c-a84b-423a-9840-5f21a2201a28")
    private static final int MINOR_VERSION = 0;

    @objid ("066b0614-25a6-4ba2-8c7b-0866678012f2")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor.
     * 
     * @param diagram the diagram
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("698c39d6-1d08-4466-a3ac-01fdd7ace723")
    public GmElementRealizationHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("b1efe566-a898-4085-88ac-ce51565c3d25")
    public GmElementRealizationHeader() {
    }

    @objid ("34f9ca58-43c2-4f5d-9194-128007f98493")
    @Override
    public List<Stereotype> filterStereotypes(List<Stereotype> stereotypes) {
        return stereotypes;
    }

    @objid ("deba1e4c-b5e7-4c11-a488-7b2ed799244a")
    @Override
    public List<StyleKey> getStyleKeys() {
        return Collections.emptyList();
    }

    @objid ("eafefd5b-3f92-430c-8389-cc105cd796c1")
    @Override
    protected String computeMainLabel() {
        if (getRelatedElement() != null)
            return "<<use>> " + (getRelatedElement().getName());
        else
            return "<<use>>";
    }

    @objid ("a7f51762-b44f-4ae1-a977-c7036afb1ade")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmUsageHeader.");
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

    @objid ("c0959b6c-3668-4218-b009-85599a2d864b")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmUsageHeader.", Integer.valueOf(GmElementRealizationHeader.MINOR_VERSION));
    }

    @objid ("e226f778-81cf-4887-abf1-19d6e48f31e9")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("ed265b3f-83bb-4c91-89ee-2d512946e437")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
