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

package org.modelio.diagram.elements.common.genericlink;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.label.modelelement.GmDefaultModelElementLabel;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.IGmLink;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.diagram.styles.core.view.ISymbolViewModel;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for any {@link MObject} referenced as a link at metamodel level.
 */
@objid ("5f80ed42-4066-4e07-8403-5d9eb151bba5")
public class GmGenericLink extends GmLink {
    /**
     * Current version of this Gm.
     */
    @objid ("18917239-667b-49ec-be05-48791fb5033c")
    private static final int MINOR_VERSION = 0;

    @objid ("a4ceead9-2ee3-402f-9c50-26ce4145fe25")
    private static final int MAJOR_VERSION = 0;

    /**
     * Style keys for {@link GmGenericLink}.
     */
    @objid ("f5dcc9ab-e8e7-4ecb-81e2-584cb42eeb00")
    public static final GmGenericLinkStyleKeys styleKeyProvider = new GmGenericLinkStyleKeys();

    @objid ("0e4b6c0e-0b7e-4d87-81e8-b9fab0477634")
    private ModelElement link;

    /**
     * Initialize a link graphic model.
     * 
     * @param diagram The owning diagram
     * @param link The reference link, may be <code>null</code>.
     * @param ref The reference link, may not be <code>null</code>
     */
    @objid ("6001de5f-3a7d-49e9-ba0c-d7a169b44eff")
    public GmGenericLink(IGmDiagram diagram, ModelElement link, MRef ref) {
        super(diagram, ref);
        this.link = link;
        
        addExtension(ExtensionLocation.MiddleNW, IGmLink.ROLE_MAIN_LABEL, new GmDefaultModelElementLabel(diagram, ref));
    }

    /**
     * For deserialization only.
     */
    @objid ("5b156941-8041-43db-b6c3-86643f52eff4")
    public GmGenericLink() {
        super();
    }

    @objid ("47d2dfcd-5cdd-4656-85d6-c6215321e38c")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return GmGenericLink.styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("b7b4a2dc-1df1-4851-961e-83e849aaf9b0")
    @Override
    public List<StyleKey> getStyleKeys() {
        return GmGenericLink.styleKeyProvider.getStyleKeys();
    }

    @objid ("133a92a6-bb71-4e18-95fe-0b60e68dd4d8")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        
        int readVersion = readMinorVersion(in, "GmGenericLink.");
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

    @objid ("457f7ad7-404c-4d6d-bb2f-32558fd93e12")
    @Override
    public MObject getFromElement() {
        // Make sure there is no error when encountering a ghost dependency...
        return this.link != null ? this.link.getMClass().getMetamodel().getMExpert().getSource(this.link) : null;
    }

    @objid ("784df1bc-6153-4cd8-a696-f563ba4d0e5d")
    @Override
    public MObject getToElement() {
        // Make sure there is no error when encountering a ghost dependency...
        return this.link != null ? this.link.getMClass().getMetamodel().getMExpert().getTarget(this.link) : null;
    }

    @objid ("698adefd-cec3-4888-b678-88e930d2116e")
    @Override
    public MObject getRepresentedElement() {
        return this.link;
    }

    @objid ("0bc57e72-0913-428c-a43d-a9d969fa504b")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        writeMinorVersion(out, "GmGenericLink.", GmGenericLink.MINOR_VERSION);
    }

    @objid ("5273b8d1-209d-47d7-8f87-d9609a14b4fc")
    @Override
    public int getMajorVersion() {
        return GmGenericLink.MAJOR_VERSION;
    }

    @objid ("d7fa9dc7-c1e0-4c18-b9de-38fee5e4ca8b")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

    /**
     * Standard read initializing fields.
     */
    @objid ("41428e15-dc29-427b-8ade-6e7c00a2edca")
    private void read_0(@SuppressWarnings ("unused") IDiagramReader in) {
        this.link = (ModelElement) resolveRef(getRepresentedRef());
    }

    @objid ("fecde45b-93a3-4f48-93fb-37da5d1a7371")
    @Override
    public ISymbolViewModel getSymbolViewModel() {
        return GenericLinkSymbolViewModel.create(getPersistedStyle(), this);
    }

}
