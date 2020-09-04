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

package org.modelio.diagram.editor.statik.elements.abstraction;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.elements.umlcommon.dependency.GmDependencyStyleKeys;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.IStyle;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.ConnectionRouterId;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link Abstraction}.
 * 
 * @author cma
 */
@objid ("1c16a4ee-d543-4be4-bf2f-adec81b59ebd")
public class GmAbstraction extends GmLink {
    /**
     * Current version of this Gm.
     */
    @objid ("df3cec7e-ba2f-4751-9508-1fdbe9a36fae")
    private static final int MINOR_VERSION = 1;

    @objid ("d093d07f-b820-4a35-8b36-bdac8315d591")
    private static final int MAJOR_VERSION = 0;

    @objid ("da975ed6-1d44-4129-b1a0-638663aeb864")
    private Abstraction dependency;

    /**
     * Style keys.
     */
    @objid ("c068cf7d-b24e-4963-a185-131553fde125")
    public static final GmDependencyStyleKeys styleKeyProvider = new GmDependencyStyleKeys("ABSTRACTION");

    /**
     * Initialize a control flow graphic model.
     * 
     * @param diagram The owning diagram
     * @param dependency The reference flow, may be null
     * @param ref The referenced flow reference, may not be null
     */
    @objid ("169d9afc-fa98-475c-ab7a-cb1647bd09b2")
    public GmAbstraction(IGmDiagram diagram, Abstraction dependency, MRef ref) {
        super(diagram, ref);
        this.dependency = dependency;
        
        final GmAbstractionHeader header = new GmAbstractionHeader(diagram, ref);
        header.setShowMetaclassKeyword(true);
        addExtension(ExtensionLocation.MiddleNW, IGmLink.ROLE_MAIN_LABEL, header);
    }

    @objid ("672c0909-e819-47c7-a0f8-fa24b3f46afd")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmAbstraction.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("045cd87f-e386-4d41-a69c-a4fcc4c2bd0b")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmAbstraction.styleKeyProvider.getStyleKeys();
    }

    /**
     * For deserialization only.
     */
    @objid ("fcbeaf67-d70a-480f-bd3f-1895aa7c8f2b")
    public GmAbstraction() {
    }

    @objid ("8c1a2593-53ae-476c-807e-9cd954141961")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        
        int readVersion = readMinorVersion(in, "GmAbstraction.");
        switch (readVersion) {
        case 0: {
            read_0(in);
            break;
        }
        case 1: {
            read_1(in);
            break;
        }
        default: {
            assert (false) : "version number not covered!";
            // reading as last handled version: 1
            read_1(in);
            break;
        }
        }
    }

    @objid ("bc2ea3a6-b4ec-43bb-8854-796df1db5b4e")
    @Override
    public MObject getFromElement() {
        return this.dependency.getImpacted();
    }

    @objid ("24929a49-9677-444d-95a8-d8d07fe856cd")
    @Override
    public MObject getToElement() {
        return this.dependency.getDependsOn();
    }

    @objid ("f6031b01-85fd-4302-8493-c4b9ca819ec6")
    @Override
    public MObject getRepresentedElement() {
        return this.dependency;
    }

    @objid ("ab057b36-0c64-4ea6-80e0-271f6d066aed")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("1d85a277-6e19-454d-8709-c440095cbb10")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        writeMinorVersion(out, "GmAbstraction.", GmAbstraction.MINOR_VERSION);
    }

    @objid ("218c3ca0-65ed-469d-b3c6-2030a97e0b9c")
    @Override
    public int getMajorVersion() {
        return GmAbstraction.MAJOR_VERSION;
    }

    @objid ("d878d42f-d5bd-44a4-bd9b-ac8c0e4475e4")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

    /**
     * Modelio 3.7.0 -> Modelio 3.7.1 migration.
     * Deal with default router change.
     */
    @objid ("92470867-7187-48b9-8642-49b3c1b0d636")
    private void read_0(IDiagramReader in) {
        read_1(in);
        
        IStyle style = getPersistedStyle();
        StyleKey styleKey = GmAbstraction.styleKeyProvider.getStyleKey(MetaKey.CONNECTIONROUTER);
        if (styleKey != null && !style.isLocal(styleKey)) {
            // Before 3.7.1, default value was "orthogonal router"
            style.setProperty(styleKey, ConnectionRouterId.ORTHOGONAL);
        }
    }

    /**
     * Standard read initializing fields.
     */
    @objid ("8f711261-7e60-4941-ada2-00d1a5a90583")
    private void read_1(@SuppressWarnings ("unused") IDiagramReader in) {
        this.dependency = (Abstraction) resolveRef(getRepresentedRef());
    }

}
