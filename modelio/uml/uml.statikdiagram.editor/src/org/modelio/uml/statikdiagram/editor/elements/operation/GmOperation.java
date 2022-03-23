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
package org.modelio.uml.statikdiagram.editor.elements.operation;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.common.label.modelelement.GmModelElementLabel;
import org.modelio.diagram.elements.core.figures.labelum.CharSeqBreakTextLayouter;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.GeneralClass;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents an {@link Operation} label.
 * <p>
 * Extends {@link GmModelElementLabel}.
 * 
 * @author cmarin
 */
@objid ("35f74fba-55b7-11e2-877f-002564c97630")
public class GmOperation extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("35f74fbe-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("35f74fc1-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("35f74fc3-55b7-11e2-877f-002564c97630")
    private Operation element = null;

    @objid ("254c926b-a3b7-4da8-afd1-267285dcae5a")
    public static GmOperationStyleKeys OPERATION_KEYS = new GmOperationStyleKeys();

    /**
     * Creates a GmMethod.
     * @param diagram the owning diagram
     * @param op the represented operation, may be <tt>null</tt>.
     * @param ref the represented operation reference, may not be <tt>null</tt>.
     */
    @objid ("35f74fc6-55b7-11e2-877f-002564c97630")
    public  GmOperation(IGmDiagram diagram, Operation op, MRef ref) {
        super(diagram, ref);
        this.element = op;
        init();
        
    }

    @objid ("35f74fd2-55b7-11e2-877f-002564c97630")
    private String getParameterTypeName(Parameter p) {
        if (p == null) {
            return "void";
        }
        
        final GeneralClass type = p.getType();
        if (type != null) {
            return type.getName();
        } else {
            return "<no type>";
        }
        
    }

    @objid ("35f74fd8-55b7-11e2-877f-002564c97630")
    private String getParameterCard(final Parameter theParameter) {
        StringBuilder multiplicity = new StringBuilder();
        
        String multiplicityMinStr = theParameter.getMultiplicityMin();
        String multiplicityMaxStr = theParameter.getMultiplicityMax();
        String separator = "";
        
        if (multiplicityMinStr.equals("1") && multiplicityMaxStr.equals("1")) {
            return multiplicity.toString();
        }
        
        if (!multiplicityMinStr.equals("") || !multiplicityMaxStr.equals("")) {
            multiplicity.append(" [");
            // multiplicity.append("[");
            if (multiplicityMinStr.equals(multiplicityMaxStr)) {
                multiplicity.append(multiplicityMinStr);
            } else if (multiplicityMinStr.equals("0") && multiplicityMaxStr.equals("*")) {
                multiplicity.append("*");
            } else {
                if (!multiplicityMinStr.equals("") && !multiplicityMaxStr.equals("")) {
                    separator = "..";
                }
        
                multiplicity.append(multiplicityMinStr);
                multiplicity.append(separator);
                multiplicity.append(multiplicityMaxStr);
            }
            multiplicity.append("]");
        }
        return multiplicity.toString();
    }

    @objid ("35f8d65a-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        final Operation op = getRelatedElement();
        final StringBuilder s = new StringBuilder(100);
        //
        if (getParent() != null) {
            final String svis = computeVisibility(op);
            final boolean showSig = getDisplayedStyle().getBoolean(getStyleKey(MetaKey.OperationGroup.OPSHOWSIGNATURE));
        
            if (showSig) {
                s.append(svis);
                s.append(' ');
                s.append(op.getName());
                s.append('(');
                computeLabelSignature(op, s);
                s.append(')');
                final Parameter retp = op.getReturn();
                if (retp != null) {
                    s.append(": ");
                    s.append(getParameterTypeName(retp));
                    s.append(getParameterCard(retp));
                }
            } else {
                s.append(svis);
                s.append(' ');
                s.append(op.getName());
                s.append("()");
            }
        } else {
            s.append(super.computeMainLabel());
        }
        return s.toString();
    }

    @objid ("35f8d65f-55b7-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        return new IEditableText() {
        
                    @Override
                    public String getText() {
                        return getRelatedElement().getName();
                    }
        
                    @Override
                    public void setText(String text) {
                        getRelatedElement().setName(text);
                    }
        
                };
        
    }

    @objid ("35f8d66d-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (metakey == MetaKey.SHOWSTEREOTYPES) {
            return super.getStyleKey(MetaKey.OperationGroup.OPSHOWSTEREOTYPES);
        } else if (metakey == MetaKey.SHOWTAGS) {
            return super.getStyleKey(MetaKey.OperationGroup.OPSHOWTAGS);
        } else if (metakey == MetaKey.SHOWVISIBILITY) {
            return super.getStyleKey(MetaKey.OperationGroup.OPSHOWVISIBILITY);
        } else if (metakey == MetaKey.WRAPLABEL) {
            return super.getStyleKey(MetaKey.OperationGroup.OPWRAPLABEL);
        } else if (metakey == MetaKey.OperationGroup.OPSHOWSIGNATURE) {
            return super.getStyleKey(MetaKey.OperationGroup.OPSHOWSIGNATURE);
        } else {
            return GmOperation.OPERATION_KEYS.getStyleKey(metakey);
        }
        
    }

    /**
     * constructor to be used only for deserialization
     */
    @objid ("35f8d676-55b7-11e2-877f-002564c97630")
    public  GmOperation() {
        init();
    }

    @objid ("35fa5cfa-55b7-11e2-877f-002564c97630")
    @Override
    public Operation getRelatedElement() {
        return this.element;
    }

    /**
     * To be called by all constructors.
     */
    @objid ("35fa5d01-55b7-11e2-877f-002564c97630")
    private void init() {
        setShowMetaclassKeyword(false);
        setShowMetaclassIcon(false);
        
    }

    @objid ("35fa5d04-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.element;
    }

    @objid ("35fa5d0b-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmOperation.");
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
     * Compute the operation signature and append it to the given string builder.
     * @param op the operation
     * @param s the string builder
     */
    @objid ("35fa5d11-55b7-11e2-877f-002564c97630")
    private void computeLabelSignature(final Operation op, final StringBuilder s) {
        boolean first = true;
        for (Parameter p : op.getIO()) {
            if (first) {
                first = false;
            } else {
                s.append(", ");
            }
        
            s.append(CharSeqBreakTextLayouter.ZERO_WIDTH_SPACE);
            s.append(getParameterPassing(p));
            s.append(p.getName());
            s.append(": ");
            s.append(getParameterTypeName(p));
            s.append(getParameterCard(p));
        }
        
    }

    /**
     * Compute and return the visibility symbol of the operation.
     * <p>
     * Returns empty string if visibility display is disabled.
     * @param op an operation
     * @return the visibility symbol
     */
    @objid ("35fa5d1a-55b7-11e2-877f-002564c97630")
    private String computeVisibility(final Operation op) {
        String svis;
        StyleKey styleKey = getStyleKey(MetaKey.SHOWVISIBILITY);
        if (styleKey == null) {
            return "";
        }
        
        boolean property = getDisplayedStyle().getProperty(styleKey);
        if (property) {
            switch (op.getVisibility()) {
            case PUBLIC:
                svis = "+";
                break;
            case PROTECTED:
                svis = "#";
                break;
            case PRIVATE:
                svis = "-";
                break;
            case PACKAGEVISIBILITY:
                svis = "~";
                break;
            default:
                svis = " ";
            }
        } else {
            svis = "";
        }
        return svis;
    }

    @objid ("35fa5d23-55b7-11e2-877f-002564c97630")
    private String getParameterPassing(final Parameter p) {
        switch (p.getParameterPassing()) {
        case IN:
            return "in ";
        case OUT:
            return "out ";
        case INOUT:
            return "inout ";
        }
        return "";
    }

    @objid ("35fa5d2b-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmOperation.", GmOperation.MINOR_VERSION);
        
    }

    @objid ("35fa5d31-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (Operation) resolveRef(this.getRepresentedRef());
        
    }

    @objid ("35fa5d36-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmOperation.MAJOR_VERSION;
    }

    @objid ("dea6457b-ccd1-42b7-ac98-9006c5c45d0e")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmOperation.OPERATION_KEYS.getStyleKeys();
    }

}
