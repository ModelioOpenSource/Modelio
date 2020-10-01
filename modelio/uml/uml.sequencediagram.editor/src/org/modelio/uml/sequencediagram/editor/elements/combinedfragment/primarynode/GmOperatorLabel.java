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

package org.modelio.uml.sequencediagram.editor.elements.combinedfragment.primarynode;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.base.GmElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.interactionModel.CombinedFragment;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Label for the operator of the Combined Fragment.
 * 
 * @author fpoyer
 */
@objid ("d8ce215e-55b6-11e2-877f-002564c97630")
public class GmOperatorLabel extends GmElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d8cfa7b9-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d8cfa7bc-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d8cfa7be-55b6-11e2-877f-002564c97630")
    public GmOperatorLabel() {
        super();
    }

    /**
     * Main c'tor
     * 
     * @param diagram the diagram in which this gm is created.
     * @param relatedRef a reference to the represented element.
     */
    @objid ("d8cfa7c1-55b6-11e2-877f-002564c97630")
    public GmOperatorLabel(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("d8cfa7cc-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeLabel() {
        CombinedFragment combinedFragment = (CombinedFragment) getRelatedElement();
        if (combinedFragment != null && combinedFragment.isValid()) {
            switch (combinedFragment.getOperator()) {
            case ALTOP:
                return "alt";
            case ASSERTOP:
                return "assert";
            case BREAKOP:
                return "break";
            case CONSIDEROP:
                return "consider";
            case CRITICALOP:
                return "critical";
            case IGNOREOP:
                return "ignore";
            case LOOPOP:
                return "loop";
            case NEGOP:
                return "neg";
            case OPTOP:
                return "opt";
            case PAROP:
                return "par";
            case SEQOP:
                return "seq";
            case STRICTOP:
                return "strict";
            }
        }
        // Fail safe, should not happen.
        return "";
    }

    @objid ("d8cfa7d1-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmOperatorLabel.");
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

    @objid ("d8cfa7d7-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmOperatorLabel.", GmOperatorLabel.MINOR_VERSION);
    }

    @objid ("d8cfa7dd-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("d8cfa7e2-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
