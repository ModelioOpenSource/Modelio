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
package org.modelio.uml.sequencediagram.editor.elements.lifeline.header;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Image;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.model.IEditableText;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.StyleKey.RepresentationMode;
import org.modelio.metamodel.uml.behavior.interactionModel.Lifeline;
import org.modelio.metamodel.uml.statik.Instance;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.platform.model.ui.swt.images.ElementImageService;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Specialisation of the default header to handle representation mode 'Image'.
 * 
 * @author fpoyer
 */
@objid ("d942189a-55b6-11e2-877f-002564c97630")
public class GmLifelineHeader extends GmDefaultModelElementHeader {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("d942189e-55b6-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("d94218a1-55b6-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty c'tor for deserialisation.
     */
    @objid ("d94218a3-55b6-11e2-877f-002564c97630")
    public  GmLifelineHeader() {
        super();
    }

    /**
     * Default c'tor.
     * @param diagram the diagram in which this Gm is created.
     * @param ref a reference to the related element.
     */
    @objid ("d94218a6-55b6-11e2-877f-002564c97630")
    public  GmLifelineHeader(final IGmDiagram diagram, final MRef ref) {
        super(diagram, ref);
    }

    @objid ("d94218b1-55b6-11e2-877f-002564c97630")
    @Override
    public RepresentationMode getRepresentationMode() {
        if (getParent() != null) {
            return getParent().getRepresentationMode();
        } else {
            return RepresentationMode.STRUCTURED;
        }
        
    }

    @objid ("d94218b7-55b6-11e2-877f-002564c97630")
    @Override
    public String computeMainLabel() {
        final Lifeline inst = (Lifeline) getRelatedElement();
        return LifelineSymbolProvider.computeSimpleLabel(inst);
    }

    @objid ("d94218bc-55b6-11e2-877f-002564c97630")
    @Override
    public void refreshFromObModel() {
        Lifeline lifeline = (Lifeline) getRelatedElement();
        if (lifeline != null && lifeline.isValid() && lifeline.getRepresented() != null) {
            setShowMetaclassIcon(lifeline.getRepresented().getBase() != null);
        } else {
            setShowMetaclassIcon(false);
        }
        super.refreshFromObModel();
        
    }

    /**
     * For a lifeline, the metaclass icon is the one from its represented instance's base.
     */
    @objid ("d94218bf-55b6-11e2-877f-002564c97630")
    @Override
    public Image getMetaclassIcon() {
        Lifeline lifeline = (Lifeline) getRelatedElement();
        if (lifeline.getRepresented() != null) {
            NameSpace base = lifeline.getRepresented().getBase();
            if (base != null) {
                return ElementImageService.getIcon(base);
            }
        }
        return null;
    }

    /**
     * Computes the secondary label of the header.
     * <p>
     * The secondary label of a Lifeline is the represented PartDecomposition if any.
     * <p>
     * This method may be redefined by subclasses.
     * @return The secondary label of the header.
     */
    @objid ("d94218c5-55b6-11e2-877f-002564c97630")
    public String computeSecondaryLabel() {
        Lifeline lifeline = (Lifeline) getRelatedElement();
        if (lifeline != null && lifeline.isValid() && lifeline.getDecomposedAs() != null) {
            return "ref " + lifeline.getDecomposedAs().getName();
        }
        return "";
    }

    @objid ("d94218ca-55b6-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmLifelineHeader.");
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

    @objid ("d9439f3c-55b6-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmLifelineHeader.", GmLifelineHeader.MINOR_VERSION);
        
    }

    @objid ("d9439f42-55b6-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
    }

    @objid ("d9439f47-55b6-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("d9439f4c-55b6-11e2-877f-002564c97630")
    @Override
    public IEditableText getEditableText() {
        IEditableText editor = new IEditableText() {
            @Override
            public String getText() {
                final Lifeline c = (Lifeline) getRelatedElement();
                final Instance represented = c.getRepresented();
                if (represented != null) {
                    return represented.getName();
                } else {
                    return c.getName();
                }
            }
        
            @Override
            public void setText(String text) {
                final Lifeline c = (Lifeline) getRelatedElement();
                final Instance represented = c.getRepresented();
                if (represented != null) {
                    represented.setName(text);
                }
                c.setName(text);
            }
        };
        return editor;
    }

}
