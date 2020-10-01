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

package org.modelio.uml.statikdiagram.editor.elements.informationconveyed;

import java.util.Collections;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.common.label.modelelement.GmModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Represents the label of a {@link Classifier} conveyed by an information flow.
 * <p>
 * Contrary to GmNameSpacelabel, GmConveyedClassifierLabel does <b>not</b> represents the classifier, it only relates
 * it.
 * <p>
 * Extends {@link GmModelElementLabel}.
 */
@objid ("34fb8aa2-55b7-11e2-877f-002564c97630")
public class GmConveyedClassifierLabel extends GmDefaultModelElementLabel {
    @objid ("34fb8aa6-55b7-11e2-877f-002564c97630")
    private Classifier element = null;

    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("34fb8aa9-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("34fb8aac-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    /**
     * Empty constructor needed for (de-)serialization.
     */
    @objid ("34fb8aae-55b7-11e2-877f-002564c97630")
    public GmConveyedClassifierLabel() {
        // Empty constructor needed for (de-)serialization.
    }

    /**
     * Default constructor.
     * 
     * @param diagram the diagram in which this gm is unmasked.
     * @param el the represented element, may be <i>null</i>.
     * @param ref a reference to the represented element.
     */
    @objid ("34fb8ab1-55b7-11e2-877f-002564c97630")
    public GmConveyedClassifierLabel(IGmDiagram diagram, Classifier el, MRef ref) {
        super(diagram, ref);
        this.element = el;
    }

    @objid ("34fd113a-55b7-11e2-877f-002564c97630")
    @Override
    public Classifier getRelatedElement() {
        return this.element;
    }

    @objid ("34fd1141-55b7-11e2-877f-002564c97630")
    @Override
    public Classifier getRepresentedElement() {
        return null;
    }

    @objid ("34fd1148-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        if (metakey == MetaKey.FONT) {
            return super.getStyleKey(MetaKey.InformationItemGroup.INFFONT);
        } else if (metakey == MetaKey.SHOWSTEREOTYPES) {
            return super.getStyleKey(MetaKey.InformationItemGroup.INFSHOWSTEREOTYPES);
        } else if (metakey == MetaKey.SHOWTAGS) {
            return super.getStyleKey(MetaKey.InformationItemGroup.INFSHOWTAGS);
        } else if (metakey == MetaKey.TEXTCOLOR) {
            return super.getStyleKey(MetaKey.InformationItemGroup.INFTEXTCOLOR);
        }
        return null;
    }

    /**
     * No own style key.
     * <p>
     * Everything is defined on the owner class.
     */
    @objid ("34fd1152-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return Collections.emptyList();
    }

    @objid ("34fd115c-55b7-11e2-877f-002564c97630")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmConveyedClassifierLabel.");
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

    @objid ("34fd116e-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmConveyedClassifierLabel.", MINOR_VERSION);
    }

    @objid ("34fd1174-55b7-11e2-877f-002564c97630")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.element = (Classifier) resolveRef(getRepresentedRef());
    }

    @objid ("34fd1179-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("316e3c7e-bae6-4b77-a15d-47109b80a186")
    @Override
    public boolean isVisible() {
        return true;
    }

}
