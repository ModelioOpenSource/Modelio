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

package org.modelio.diagram.editor.usecase.elements.usecasedependency;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCaseDependency;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

@objid ("5e7e1147-55b7-11e2-877f-002564c97630")
public class GmUseCaseDependency extends GmLink {
    @objid ("5e7e114d-55b7-11e2-877f-002564c97630")
    private UseCaseDependency theUseCaseDependency;

    @objid ("5e7e1150-55b7-11e2-877f-002564c97630")
    private static final int MINOR_VERSION = 0;

    @objid ("5e7e1153-55b7-11e2-877f-002564c97630")
    private static final int MAJOR_VERSION = 0;

    @objid ("8c71cd46-25d0-4da9-bd7c-7fdc995da23b")
    public static final String ROLE_EXTENSIONPOINTS = "extension_points";

    @objid ("7c12ba7c-5eff-11e2-b9cc-001ec947c8cc")
    private static final GmUseCaseDependencyStyleKeys styleKeyProvider = new GmUseCaseDependencyStyleKeys();

    @objid ("5e7e1155-55b7-11e2-877f-002564c97630")
    public GmUseCaseDependency(IGmDiagram diagram, UseCaseDependency usecasedependency, MRef ref) {
        super(diagram, ref);
        this.theUseCaseDependency = usecasedependency;
        
        GmDefaultModelElementLabel extension = new GmDefaultModelElementLabel(diagram, ref);
        extension.setShowLabel(false);
        addExtension(ExtensionLocation.MiddleNW, IGmLink.ROLE_MAIN_LABEL, extension);
        
        GmExtensionPointLabel extensionPointLabel = new GmExtensionPointLabel(diagram, ref);
        addExtension(ExtensionLocation.MiddleSE, GmUseCaseDependency.ROLE_EXTENSIONPOINTS, extensionPointLabel);
    }

    @objid ("5e7e1161-55b7-11e2-877f-002564c97630")
    public GmUseCaseDependency() {
    }

    @objid ("5e7e1164-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getFromElement() {
        return this.theUseCaseDependency.getOrigin();
    }

    @objid ("5e7e116b-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("5e7e1172-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getRepresentedElement() {
        return this.theUseCaseDependency;
    }

    @objid ("5e7e1179-55b7-11e2-877f-002564c97630")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmUseCaseDependency.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("5e7f97d9-55b7-11e2-877f-002564c97630")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmUseCaseDependency.styleKeyProvider.getStyleKeys();
    }

    @objid ("5e7f97e2-55b7-11e2-877f-002564c97630")
    @Override
    public MObject getToElement() {
        return this.theUseCaseDependency.getTarget();
    }

    @objid ("5e7f97e9-55b7-11e2-877f-002564c97630")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        
        this.theUseCaseDependency = (UseCaseDependency) resolveRef(getRepresentedRef());
    }

    @objid ("5e7f97ef-55b7-11e2-877f-002564c97630")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmUseCaseDependency.", GmUseCaseDependency.MINOR_VERSION);
    }

    @objid ("5e7f97f5-55b7-11e2-877f-002564c97630")
    @Override
    public int getMajorVersion() {
        return GmUseCaseDependency.MAJOR_VERSION;
    }

    @objid ("dc15ec7b-a8db-439d-8809-081bedf3dac6")
    @Override
    protected void read_GmLinkV0_roles() {
        for (GmNodeModel n : getExtensions()) {
            if (n instanceof GmDefaultModelElementLabel) {
                n.setRoleInComposition(IGmLink.ROLE_MAIN_LABEL);
            } else {
                n.setRoleInComposition(GmUseCaseDependency.ROLE_EXTENSIONPOINTS);
            }
        }
    }

}
