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

package org.modelio.diagram.editor.statik.elements.informationconveyed;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ShowNameMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.informationFlow.InformationItem;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Relates a information item conveyed by an information flow.
 * <p>
 * Extends {@link GmConveyedClassifierLabel}.
 */
@objid ("35001ec2-55b7-11e2-877f-002564c97630")
public class GmConveyedInformationItemLabel extends GmConveyedClassifierLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3501a51a-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3501a51d-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("3501a51f-55b7-11e2-877f-002564c97630")
    public GmConveyedInformationItemLabel() {
    }

    /**
     * Create an attribute representation.
     * @param diagram The diagram
     * @param el The represented InformationItem, may be null.
     * @param ref The represented InformationItem reference, may not be null.
     */
    @objid ("3501a522-55b7-11e2-877f-002564c97630")
    public GmConveyedInformationItemLabel(IGmDiagram diagram, InformationItem el, MRef ref) {
        super(diagram, el, ref);
        
        setShowMetaclassKeyword(false);
        setShowMetaclassIcon(true);
    }

    @objid ("3501a52e-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        return computeSignature((InformationItem) getRelatedElement());
    }

    @objid ("3501a533-55b7-11e2-877f-002564c97630")
    private String computeSignature(InformationItem att) {
        switch (getNameMode()) {
            case NONE:
                return "";
            case SIMPLE:
            default:
                return att.getName();
            case QUALIFIED:
            case FULLQUALIFIED:
                StringBuilder s = new StringBuilder(100);
                s.append(att.getName());
        
                final List<Classifier> types = att.getRepresented();
                boolean first = true;
                for (Classifier c : types) {
                    if (first) {
                        s.append(":");
                        first = false;
                    } else {
                        s.append(", ");
                    }
                    s.append(c.getName());
                }
                return s.toString();
        }
    }

    @objid ("3501a539-55b7-11e2-877f-002564c97630")
    private ShowNameMode getNameMode() {
        final StyleKey nameKey = getStyleKey(MetaKey.SHOWNAME);
        
        if (nameKey == null)
            return ShowNameMode.QUALIFIED;
        else
            return getDisplayedStyle().getProperty(nameKey);
    }

    @objid ("3501a53e-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmConveyedInformationItemLabel.");
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

    @objid ("3501a544-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmConveyedInformationItemLabel.", GmConveyedInformationItemLabel.MINOR_VERSION);
    }

    @objid ("3501a54a-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("3501a54f-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
