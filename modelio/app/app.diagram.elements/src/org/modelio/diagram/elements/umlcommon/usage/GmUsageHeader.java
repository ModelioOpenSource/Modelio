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

package org.modelio.diagram.elements.umlcommon.usage;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * MObject import header displayed on the node link.
 * 
 * @author cmarin
 */
@objid ("dc127d4c-3c2b-434e-8e5e-cdf80da41d4f")
public class GmUsageHeader extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("9a93ccb7-18a5-4ed6-8230-8319f693ba7c")
    private static final int MINOR_VERSION = 0;

    @objid ("a3bd2d77-6fd4-4243-b4e4-611ff1fc070a")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor.
     * 
     * @param diagram the diagram
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("2c7df7a8-0930-4216-9d14-4f244f3591ee")
    public GmUsageHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("f24e49a9-87ba-44a7-9cf0-09f8af75f253")
    public GmUsageHeader() {
    }

    @objid ("15907487-e84f-4496-be36-4bfccf366bc4")
    @Override
    protected String computeMainLabel() {
        if (getRelatedElement() != null) {
            return "<<use>> " + (getRelatedElement().getName());
        } else {
            return "<<use>>";
        }
    }

    @objid ("50638e08-2ce2-4f32-8356-6e62a949be53")
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

    @objid ("707f2853-dc3e-4e6a-85ec-53bf32c52290")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmUsageHeader.", MINOR_VERSION);
    }

    @objid ("c1e59850-79ed-4703-9386-df77bb25553a")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("286e9200-f02d-4d1c-b089-6c78f8a1b77d")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
