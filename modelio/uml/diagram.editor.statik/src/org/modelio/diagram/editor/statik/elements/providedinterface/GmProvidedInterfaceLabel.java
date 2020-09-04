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

package org.modelio.diagram.editor.statik.elements.providedinterface;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmCompositeNode;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.statik.Interface;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Label representing an {@link ProvidedInterface}.
 * 
 * @author cmarin
 */
@objid ("3651569a-55b7-11e2-877f-002564c97630")
public class GmProvidedInterfaceLabel extends GmDefaultModelElementLabel {
    @objid ("3651569e-55b7-11e2-877f-002564c97630")
    private ProvidedInterface element;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("365156a1-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("365156a4-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Constructor for deserialization only.
     */
    @objid ("365156a6-55b7-11e2-877f-002564c97630")
    public GmProvidedInterfaceLabel() {
    }

    /**
     * Constructor.
     * 
     * @param diagram The diagram
     * @param el The represented element, may be null.
     * @param ref The represented element reference, may not be null.
     */
    @objid ("365156a9-55b7-11e2-877f-002564c97630")
    public GmProvidedInterfaceLabel(IGmDiagram diagram, ProvidedInterface el, MRef ref) {
        super(diagram, ref);
        this.element = el;
        init();
    }

    @objid ("3652dd58-55b7-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        return null;
    }

    @objid ("3652dd5f-55b7-11e2-877f-002564c97630")
    @Override
    public ProvidedInterface getRelatedElement() {
        return this.element;
    }

    @objid ("3652dd78-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmProvidedInterfaceLabel.");
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

    @objid ("3652dd7e-55b7-11e2-877f-002564c97630")
    @Override
    protected void setParent(GmCompositeNode parent) {
        if (getParent() != parent) {
            super.setParent(parent);
        
            if (parent != null)
                getPersistedStyle().setCascadedStyle(parent.getPersistedStyle());
        }
    }

    @objid ("365463dc-55b7-11e2-877f-002564c97630")
    private String computeSignature(ProvidedInterface att) {
        final List<Interface> types = att.getProvidedElement();
        
        String typename = ""; //"<none>";
        
        if (!types.isEmpty()) {
            StringBuilder s = new StringBuilder();
            for (Interface t : types) {
                if (s.length() > 0)
                    s.append(", ");
                s.append(t.getName());
            }
            typename = s.toString();
        }
        return typename;
    }

    @objid ("365463e3-55b7-11e2-877f-002564c97630")
    private void init() {
        setShowMetaclassKeyword(false);
        setShowMetaclassIcon(false);
    }

    @objid ("365463e5-55b7-11e2-877f-002564c97630")
    @Override
    protected String computeMainLabel() {
        return computeSignature(getRelatedElement());
    }

    @objid ("365463ea-55b7-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        return RepresentationMode.STRUCTURED;
    }

    @objid ("365463f1-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmProvidedInterfaceLabel.", GmProvidedInterfaceLabel.MINOR_VERSION);
    }

    @objid ("365463f7-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (ProvidedInterface) resolveRef(this.getRepresentedRef());
    }

    @objid ("365463fc-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
