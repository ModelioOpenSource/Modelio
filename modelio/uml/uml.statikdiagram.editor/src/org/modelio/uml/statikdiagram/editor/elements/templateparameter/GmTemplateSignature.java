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

package org.modelio.uml.statikdiagram.editor.elements.templateparameter;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TaggedValue;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.TemplateParameter;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link TemplateParameter template parameters} on any element.
 */
@objid ("36e6dfda-55b7-11e2-877f-002564c97630")
public class GmTemplateSignature extends GmDefaultModelElementLabel {
    @objid ("36e6dfde-55b7-11e2-877f-002564c97630")
    private NameSpace related;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("36e6dfe1-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("36e6dfe4-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Initializes a model element header.
     * 
     * @param diagram the owning diagram.
     * @param related the related namespace, may be null.
     * @param relatedRef a reference to the element this GmModel is related to.
     */
    @objid ("36e6dfe6-55b7-11e2-877f-002564c97630")
    public GmTemplateSignature(final IGmDiagram diagram, final NameSpace related, final MRef relatedRef) {
        super(diagram, relatedRef);
        this.related = related;
    }

    /**
     * Empty constructor to use only for deserialization.
     * <p>
     * Use {@link #GmTemplateSignature(GmAbstractDiagram, NameSpace, MRef)} for regular instantiation.
     */
    @objid ("36e6dff5-55b7-11e2-877f-002564c97630")
    public GmTemplateSignature() {
        super();
    }

    /**
     * No stereotype display for template signature.
     */
    @objid ("36e8667b-55b7-11e2-877f-002564c97630")
    @Override
    public List<Stereotype> filterStereotypes(final List<Stereotype> stereotypes) {
        return Collections.emptyList();
    }

    /**
     * No tagged value display for template signature.
     */
    @objid ("36e8668b-55b7-11e2-877f-002564c97630")
    @Override
    public List<TaggedValue> filterTags(final List<TaggedValue> taggedValues) {
        return Collections.emptyList();
    }

    /**
     * Template signature is not editable.
     */
    @objid ("36e8669b-55b7-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        return null;
    }

    @objid ("36e866a3-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        StringBuilder s = new StringBuilder(50);
        
        for (TemplateParameter t : this.related.getTemplate()) {
            if (s.length() > 0)
                s.append(", ");
            computeLabel(t, s);
        }
        return s.toString();
    }

    /**
     * Compute label of the given template parameter and add it to the given string builder.
     * 
     * @param t the template parameter
     * @param s the string builder
     */
    @objid ("36e866a8-55b7-11e2-877f-002564c97630")
    private void computeLabel(final TemplateParameter t, final StringBuilder s) {
        if (t.isIsValueParameter()) {
            computeValueLabel(t, s);
        } else if (t.getOwnedParameterElement() != null) {
            computeParameterLabel(t, s);
        } else {
            s.append(t.getName());
        
            ModelElement type = t.getType();
            if (type != null) {
                if (type instanceof Class) {
                    s.append(" ^ ");
                    s.append(type.getName());
                } else {
                    s.append(" : ");
                    s.append(type.getMClass().getName());
                }
            }
        
            if (t.getDefaultType() != null) {
                s.append(" = ");
                s.append(t.getDefaultType().getName());
        
            } else if (!t.getDefaultValue().isEmpty()) {
                s.append(" = ");
                s.append(t.getDefaultValue());
            }
        }
    }

    @objid ("36e866b1-55b7-11e2-877f-002564c97630")
    private void computeParameterLabel(final TemplateParameter t, final StringBuilder s) {
        ModelElement paramElement = t.getOwnedParameterElement();
        if (paramElement instanceof NameSpace) {
            NameSpace ns = (NameSpace) paramElement;
            s.append(ns.getName());
        
            if (ns.getParent().size() > 0) {
                s.append("^");
                for (Generalization g : ns.getParent()) {
                    s.append(g.getSuperType().getName());
                }
            }
        
        } else {
            s.append(paramElement.getName());
        }
        
        if (t.getDefaultType() != null) {
            s.append(" = ");
            s.append(t.getDefaultType().getName());
        
        } else if (!t.getDefaultValue().isEmpty()) {
            s.append(" = ");
            s.append(t.getDefaultValue());
        }
    }

    /**
     * Compute label of the given template parameter and add it to the given string builder.
     * 
     * @param t the template parameter
     * @param s the string builder
     */
    @objid ("36e9ed1b-55b7-11e2-877f-002564c97630")
    private void computeValueLabel(final TemplateParameter t, final StringBuilder s) {
        s.append(t.getName());
        s.append(":");
        if (t.getType() != null)
            s.append(t.getType().getName());
        
        if (!t.getDefaultValue().isEmpty()) {
            s.append("=");
            s.append(t.getDefaultValue());
        }
    }

    @objid ("36e9ed24-55b7-11e2-877f-002564c97630")
    @Override
    public void read(final IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmTemplateSignature.");
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

    /**
     * Refresh the signature if template parameters are added.
     */
    @objid ("36e9ed2b-55b7-11e2-877f-002564c97630")
    @Override
    public void obElementAdded(final MObject addedEl) {
        if (addedEl instanceof TemplateParameter) {
            refreshFromObModel();
        } else {
            super.obElementAdded(addedEl);
        }
    }

    @objid ("36e9ed33-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmTemplateSignature.", GmTemplateSignature.MINOR_VERSION);
    }

    @objid ("36e9ed39-55b7-11e2-877f-002564c97630")
    private void read_0(final IDiagramReader in) {
        super.read(in);
        this.related = (NameSpace) resolveRef(getRepresentedRef());
    }

    @objid ("36e9ed3f-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
