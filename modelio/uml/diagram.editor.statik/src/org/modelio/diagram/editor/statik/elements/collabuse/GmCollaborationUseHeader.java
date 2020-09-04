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

package org.modelio.diagram.editor.statik.elements.collabuse;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmObject;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ShowNameMode;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the {@link CollaborationUse} header.
 */
@objid ("3479d79a-55b7-11e2-877f-002564c97630")
public class GmCollaborationUseHeader extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("3479d79e-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("3479d7a1-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("3479d7a3-55b7-11e2-877f-002564c97630")
    public GmCollaborationUseHeader() {
        init();
    }

    /**
     * Initialize a classifier header
     * 
     * @param diagram the owning diagram.
     * @param relatedRef a reference to the element this GmModel is related to, must not be null.
     */
    @objid ("3479d7a6-55b7-11e2-877f-002564c97630")
    public GmCollaborationUseHeader(IGmDiagram diagram, MRef relatedRef) {
        super(diagram, relatedRef);
        init();
    }

    @objid ("3479d7af-55b7-11e2-877f-002564c97630")
    @Override
    public CollaborationUse getRelatedElement() {
        return (CollaborationUse) super.getRelatedElement();
    }

    @objid ("3479d7b6-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(IStyle changedStyle) {
        if (updateMainLabelFromObModel()) {
            firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
        }
        
        super.styleChanged(changedStyle);
    }

    @objid ("3479d7bc-55b7-11e2-877f-002564c97630")
    @Override
    public void styleChanged(StyleKey property, Object newValue) {
        if (property == getStyleKey(MetaKey.SHOWNAME)) {
            if (updateMainLabelFromObModel()) {
                firePropertyChange(IGmObject.PROPERTY_LABEL, this, null);
            }
        }
        
        super.styleChanged(property, newValue);
    }

    @objid ("347b5e1c-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        final CollaborationUse c = getRelatedElement();
        StyleKey showNameKey = getStyleKey(MetaKey.SHOWNAME);
        final ShowNameMode nameMode = (ShowNameMode) (showNameKey == null ? ShowNameMode.NONE
                : getDisplayedStyle().getProperty(showNameKey));
        switch (nameMode) {
            case NONE:
                return "";
            case FULLQUALIFIED:
                return computeFullQualifiedLabel(c);
            case QUALIFIED:
                return computeQualifiedLabel(c);
            case SIMPLE:
            default:
                return computeSimpleLabel(c);
        
        }
    }

    /**
     * Qualified label : return only the name and the type
     * 
     * @param c A collaboration use
     * @return The collaboration use label
     */
    @objid ("347b5e20-55b7-11e2-877f-002564c97630")
    private String computeFullQualifiedLabel(CollaborationUse c) {
        // FIXME: CMA please implement this with something meaningful.
        return computeQualifiedLabel(c);
    }

    /**
     * Qualified label : return only the name and the type
     * 
     * @param c A collaboration use
     * @return The collaboration use label
     */
    @objid ("347b5e28-55b7-11e2-877f-002564c97630")
    private String computeQualifiedLabel(CollaborationUse c) {
        String stype = "";
        
        final Collaboration type = c.getType();
        if (type != null) {
            stype = type.getName();
        }
        return c.getName() + " : " + stype;
    }

    /**
     * Simple label : return only the name
     * 
     * @param c A collaboration use
     * @return The collaboration use label
     */
    @objid ("347b5e30-55b7-11e2-877f-002564c97630")
    private String computeSimpleLabel(CollaborationUse c) {
        return c.getName();
    }

    @objid ("347b5e38-55b7-11e2-877f-002564c97630")
    private void init() {
        setStackedStereotypes(true);
    }

    @objid ("347b5e3a-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmCollaborationUseHeader.");
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

    @objid ("347b5e40-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0.
        writeMinorVersion(out, "GmCollaborationUseHeader.", Integer.valueOf(GmCollaborationUseHeader.MINOR_VERSION));
    }

    @objid ("347b5e46-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("347b5e4b-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
