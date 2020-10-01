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

package org.modelio.uml.statikdiagram.editor.elements.constraint;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.infrastructure.Constraint;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the default header to:
 * <ul>
 * <li>replace main label by the correct label for a constraint.</li>
 * </ul>
 * 
 * @author fpoyer
 */
@objid ("811d77c7-1dec-11e2-8cad-001ec947c8cc")
public class GmConstraintBodyLabel extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("811d77c9-1dec-11e2-8cad-001ec947c8cc")
    private static final int MINOR_VERSION = 0;

    @objid ("811d77cc-1dec-11e2-8cad-001ec947c8cc")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("811d77ce-1dec-11e2-8cad-001ec947c8cc")
    public GmConstraintBodyLabel() {
        super();
    }

    /**
     * C'tor.
     * 
     * @param diagram the diagram in which this gm is created.
     * @param relatedRef a reference to the represented constraint. Must NOT be null.
     */
    @objid ("811d77d1-1dec-11e2-8cad-001ec947c8cc")
    public GmConstraintBodyLabel(final IGmDiagram diagram, final MRef relatedRef) {
        super(diagram, relatedRef);
    }

    @objid ("811d77e3-1dec-11e2-8cad-001ec947c8cc")
    @Override
    protected String computeMainLabel() {
        return "{" + getRelatedElement().getName() + ":" + getRelatedElement().getBody() + "}";
    }

    @objid ("811d77e8-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public Constraint getRelatedElement() {
        return (Constraint) super.getRelatedElement();
    }

    @objid ("811fd9f0-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmConstraintBodyLabel.");
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

    @objid ("811fd9f4-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmConstraintBodyLabel.", GmConstraintBodyLabel.MINOR_VERSION);
    }

    @objid ("811fd9f8-1dec-11e2-8cad-001ec947c8cc")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("811fd9fb-1dec-11e2-8cad-001ec947c8cc")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("96351e3e-407b-4f1c-9125-e00a1a53f4be")
    @Override
    public IEditableText getEditableText() {
        return new IEditableText() {
                                                                            @Override
                                                                            public String getText() {
                                                                        return GmConstraintBodyLabel.this.getRelatedElement().getBody();
                                                                                    }
                                                                        
                                                                                    @Override
                                                                                    public void setText(String text) {
                                                                        GmConstraintBodyLabel.this.getRelatedElement().setBody(text);
                                                                                    }
                                                                                };
    }

}
