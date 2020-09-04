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

package org.modelio.diagram.editor.activity.elements.changeevent;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.text.GmElementText;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.behavior.activityModel.AcceptChangeEventAction;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the expression text of an accept change event.
 */
@objid ("29fb2f79-55b6-11e2-877f-002564c97630")
public class GmChangeEventExpression extends GmElementText {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("29fb2f7d-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("29fb2f80-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Creates the node.
     * 
     * @param diagram The diagram
     * @param relatedRef related element reference, must not be <code>null</code>.
     */
    @objid ("29fb2f82-55b6-11e2-877f-002564c97630")
    public GmChangeEventExpression(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    /**
     * For deserialization only.
     */
    @objid ("29fb2f8d-55b6-11e2-877f-002564c97630")
    public GmChangeEventExpression() {
    }

    @objid ("29fb2f90-55b6-11e2-877f-002564c97630")
    @Override
    protected String computeText() {
        return getRelatedElement().getChangeExpresion();
    }

    @objid ("29fb2f95-55b6-11e2-877f-002564c97630")
    @Override
    public AcceptChangeEventAction getRelatedElement() {
        return (AcceptChangeEventAction) super.getRelatedElement();
    }

    @objid ("29fb2f9c-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        AcceptChangeEventAction theAction = getRelatedElement();
        
        if (theAction == null)
            return null;
        return new IEditableText() {
        
                    @Override
                    public void setText(String text) {
                        getRelatedElement().setChangeExpresion(text);
                    }
        
                    @Override
                    public String getText() {
                        return getRelatedElement().getChangeExpresion();
                    }
                };
    }

    @objid ("29fb2fa3-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmChangeEventExpression.");
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

    @objid ("29fb2fa9-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmChangeEventExpression.", GmChangeEventExpression.MINOR_VERSION);
    }

    @objid ("29fb2faf-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("29fb2fb4-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
