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

package org.modelio.uml.statikdiagram.editor.elements.elementRealization;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.header.GmDefaultModelElementHeader;
import org.modelio.diagram.elements.core.link.ExtensionLocation;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.persistence.IDiagramReader;
import org.modelio.diagram.persistence.IDiagramWriter;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.metamodel.uml.statik.ElementRealization;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Graphic model for {@link ElementRealization}.
 * 
 * @author cma
 */
@objid ("eb530083-57e7-420d-b3d2-3724eab3d05e")
public class GmElementRealization extends GmLink {
    /**
     * Current version of this Gm. Defaults to 0.
     */
    @objid ("2200a8b6-5bd8-44a3-a0e5-c91ee4d5bcc5")
    private static final int MINOR_VERSION = 0;

    @objid ("fd6d0d85-5d1b-4eea-8da0-5574d0c0e5f7")
    private static final int MAJOR_VERSION = 0;

    @objid ("fa462767-4a7a-4554-a6ce-1d5e32450144")
    private ElementRealization dependency;

    /**
     * Style keys.
     */
    @objid ("5d3f4e61-6c90-4fc0-9991-55268b8de833")
    public static final ElementRealizationStyleKeys styleKeyProvider = new ElementRealizationStyleKeys();

    /**
     * Initialize a control flow graphic model.
     * 
     * @param diagram The owning diagram
     * @param dependency The reference flow, may be null
     * @param ref The referenced flow reference, may not be null
     */
    @objid ("10ab6553-9427-47bd-8c9d-cdc425ad4e01")
    public GmElementRealization(IGmDiagram diagram, ElementRealization dependency, MRef ref) {
        super(diagram, ref);
        this.dependency = dependency;
        
        final GmDefaultModelElementHeader header = new GmDefaultModelElementHeader(diagram, ref);
        addExtension(ExtensionLocation.MiddleNW, ROLE_MAIN_LABEL, header);
    }

    @objid ("3c216b90-b374-4b7b-99d2-caf53c4a5ad3")
    @Override
    public StyleKey getStyleKey(MetaKey metakey) {
        return styleKeyProvider.getStyleKey(metakey);
    }

    @objid ("6ce457ad-5548-4295-aa9c-b5ee5e775833")
    @Override
    public List<StyleKey> getStyleKeys() {
        return styleKeyProvider.getStyleKeys();
    }

    /**
     * For deserialization only.
     */
    @objid ("132d4c2e-9153-484d-a413-1c528986010f")
    public GmElementRealization() {
    }

    @objid ("f8d548e8-2d69-44a4-bae3-ccb706b45ec3")
    @Override
    protected void readLink(IDiagramReader in) {
        super.readLink(in);
        this.dependency = (ElementRealization) resolveRef(this.getRepresentedRef());
    }

    @objid ("07fdaa4d-f1c6-4fee-8fa2-2537c55fcb17")
    @Override
    public MObject getFromElement() {
        return this.dependency.getImpacted();
    }

    @objid ("63152d89-e9b6-4836-beac-0a9986457d19")
    @Override
    public MObject getToElement() {
        return this.dependency.getDependsOn();
    }

    @objid ("d60d3389-4656-487c-9ec7-255128f867c2")
    @Override
    public MObject getRepresentedElement() {
        return this.dependency;
    }

    @objid ("1da3a69b-ae9a-4afe-b6ba-55d13f3bbe19")
    @Override
    public MObject getRelatedElement() {
        return getRepresentedElement();
    }

    @objid ("86eb8b8e-525f-4dde-b994-185aa5caa01a")
    @Override
    public void write(IDiagramWriter out) {
        super.write(out);
        
        // Write version of this Gm if different of 0
        writeMinorVersion(out, "GmElementRealization.", GmElementRealization.MINOR_VERSION);
    }

    @objid ("8bfcacd2-10a0-4aea-9a15-59e529b895ad")
    @Override
    public int getMajorVersion() {
        return MAJOR_VERSION;
    }

    @objid ("dc92e391-58d4-4234-a46d-6731417a1e37")
    @Override
    protected void read_GmLinkV0_roles() {
        read_GmLinkV0_roles_one_main_label();
    }

}
