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

package org.modelio.diagram.editor.statik.elements.componentRealization;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.node.GmNodeModel;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link ComponentRealization}.
 */
@objid ("f29b63a9-8141-4295-b827-73c57a9b5095")
public class GmComponentRealization extends GmLink {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("12a69c4c-b77f-4550-a147-2d5b2c459c1f")
    private static final int MINOR_VERSION = 0;

    @objid ("1fb1946e-9b6b-49e0-9f36-130cda3ace65")
    private static final int MAJOR_VERSION = 0;

    @objid ("1bf69a08-ba5a-4f3b-bdd2-622bf02bcd26")
    private ComponentRealization dependency;

    /**
     * Style keys.
     */
    @objid ("1726f880-d200-472e-9d96-493780c4bab6")
    public static final ComponentRealizationStyleKeys styleKeyProvider = new ComponentRealizationStyleKeys();

    /**
     * Initialize a control flow graphic model.
     * @param diagram The owning diagram
     * @param dependency The reference flow, may be null
     * @param ref The referenced flow reference, may not be null
     */
    @objid ("ef00cdd2-0e6e-4ca0-8f7c-ada3b51c1a4e")
    public GmComponentRealization(IGmDiagram diagram, ComponentRealization dependency, MRef ref) {
        super(diagram, ref);
        this.dependency = dependency;
        
        final GmNodeModel header = new GmComponentRealizationHeader(diagram, ref);
        addExtension(ExtensionLocation.MiddleNW, ROLE_MAIN_LABEL, header);
    }

    @objid ("0d24c59a-19f5-49b9-bf8f-54910b2daafd")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("17afdb58-f96a-4cc6-8d24-05b7d4e34660")
    @Override
    public List<StyleKey> getStyleKeys() {
        return styleKeyProvider.getStyleKeys();
    }

    /**
     * For deserialization only.
     */
    @objid ("97ced59b-bdd2-4125-8126-a6a771f0ddb7")
    public GmComponentRealization() {
    }

    @objid ("39341e38-b962-47ed-bec1-08b86424df8c")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.dependency = (ComponentRealization) resolveRef(this.getRepresentedRef());
    }

    @objid ("d46348bf-460a-477d-b9a1-9327fdb18b41")
    @Override
    public MObject getFromElement() {
        return this.dependency.getRealizingClassifier();
    }

    @objid ("38395b5a-a5ea-4fc6-bd28-a7e4248448ac")
    @Override
    public MObject getToElement() {
        return this.dependency.getAbstraction();
    }

    @objid ("5c45c0f3-2e38-431c-a7ea-0b4f0c6d2b68")
    @Override
    public MObject getRepresentedElement() {
        return this.dependency;
    }

    @objid ("0c238284-713a-4998-987f-a2c076286532")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("7af59915-fa31-4615-a581-e84cebda4c0b")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmComponentRealization.", GmComponentRealization.MINOR_VERSION);
    }

    @objid ("d5ae162a-fb1c-426b-b154-67678cc89b84")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("20ce3be5-bb94-4ecc-ab7a-dfb25804a04b")
    @Override
    protected void read_GmLinkV0_roles() {
        for (GmNodeModel n : getExtensions()) {
            n.setRoleInComposition(ROLE_MAIN_LABEL);
        }
    }

}
