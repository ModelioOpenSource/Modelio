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

package org.modelio.diagram.editor.statik.elements.instancelink;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Link name label.
 */
@objid ("fd6e2d6b-2168-4d4c-8724-0a1436ca97bd")
public class GmLinkLabel extends GmDefaultModelElementLabel {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("7a656576-1fd4-443f-b795-c12d76a8c4fb")
    private static final int MINOR_VERSION = 0;

    @objid ("90588ef5-1ad6-4702-ba0f-7d876af242c4")
    private static final int MAJOR_VERSION = 0;

    @objid ("73db8791-78c6-4167-a2d2-d0018ce7b729")
    private Link link;

    /**
     * Constructor for deserialization only.
     */
    @objid ("96ea4ca9-eca8-4656-8d89-8042de900172")
    public GmLinkLabel() {
        // Nothing to do.
    }

    /**
     * Creates an association name label.
     * @param diagram the owning diagram.
     * @param linkRef the represented link reference, must not be null.
     */
    @objid ("a1a3efe5-7fe3-40f6-bbe2-73d5ce36e7de")
    public GmLinkLabel(IGmDiagram diagram, MRef linkRef) {
        super(diagram, linkRef);
        this.link = (Link) resolveRef(linkRef);
    }

    @objid ("fecb5c2b-3819-4dcb-8e33-3b7bae1149e5")
    @Override
    public ModelElement getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("cecd700f-b156-4435-80c8-25ffd74f59ef")
    @Override
    public ModelElement getRepresentedElement() {
        return this.link;
    }

    @objid ("cdca8c00-e123-4281-925e-4fbc2743cf21")
    @Override
    public void read(IDiagramReader in) {
        // Read version, defaults to 0 if not found
        int readVersion = readMinorVersion(in, "GmLinkLabel.");
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

    @objid ("be0ee1a1-7a57-4d4b-9f3b-34471cb9e381")
    @Override
    protected String computeMainLabel() {
        return this.link != null ? this.link.getName() : "";
    }

    @objid ("c4fb045d-ad1c-42fd-9a4a-0fd022b40bef")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmLinkLabel.", GmLinkLabel.MINOR_VERSION);
    }

    @objid ("adb8183d-3379-4f26-8766-36b3919b027c")
    private void read_0(IDiagramReader in) {
        super.read(in);
        this.link = (Link) resolveRef(this.getRepresentedRef());
    }

    @objid ("5960d84a-25d9-43c5-bd67-9586e396a325")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

}
