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

package org.modelio.diagram.editor.bpmn.elements.diagrams;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.editor.bpmn.elements.style.BpmnAbstractStyleKeyProvider;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagramStyleKeys;
import org.modelio.diagram.elements.common.abstractdiagram.LayoutAssistantStyleKeys;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmBpmnProcessCollaborationDiagramStyleKeys when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("61f02316-55b6-11e2-877f-002564c97630")
public class GmBpmnDiagramStyleKeys extends BpmnAbstractStyleKeyProvider {
    @objid ("2b9df74c-04b5-4484-8889-3ebd140432b0")
    public static final StyleKey SNAPTOGEOMETRY = createStyleKey("DIAGRAM_SNAPTOGEOMETRY", Boolean.class);

    @objid ("7269344b-55c1-11e2-9337-002564c97630")
    public static final StyleKey VIEWGRID = createStyleKey("DIAGRAM_VIEWGRID", Boolean.class);

    @objid ("7269344d-55c1-11e2-9337-002564c97630")
    public static final StyleKey SNAPTOGRID = createStyleKey("DIAGRAM_SNAPTOGRID", Boolean.class);

    @objid ("72693450-55c1-11e2-9337-002564c97630")
    public static final StyleKey GRIDSPACING = createStyleKey("DIAGRAM_GRIDSPACING", Integer.class);

    @objid ("72693452-55c1-11e2-9337-002564c97630")
    public static final StyleKey GRIDCOLOR = createStyleKey("DIAGRAM_GRIDCOLOR", Color.class);

    @objid ("72693454-55c1-11e2-9337-002564c97630")
    public static final StyleKey GRIDALPHA = createStyleKey("DIAGRAM_GRIDALPHA", Integer.class);

    @objid ("72693456-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = createStyleKey("DIAGRAM_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("72693458-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLIMAGE = createStyleKey("DIAGRAM_FILLIMAGE", String.class);

    @objid ("726abaea-55c1-11e2-9337-002564c97630")
    public static final StyleKey FILLALPHA = createStyleKey("DIAGRAM_FILLALPHA", Integer.class);

    @objid ("726abaec-55c1-11e2-9337-002564c97630")
    public static final StyleKey SHOW_PAGES = createStyleKey("DIAGRAM_SHOW_PAGES", Boolean.class);

    @objid ("726abaee-55c1-11e2-9337-002564c97630")
    public static final StyleKey PAGE_SIZE = createStyleKey("DIAGRAM_PAGE_SIZE", String.class);

    @objid ("e4270e05-8c53-48c6-82e0-7c97b29e069b")
     static final LayoutAssistantStyleKeys autoLayoutStyle = GmAbstractDiagramStyleKeys.autoLayoutStyle;

    @objid ("7cf03d78-b8c6-4815-851b-afcf88176cdb")
     static final StyleKey SHOW_SMARTLINK_HANDLE = createStyleKey("DIAGRAM_SHOW_SMARTLINK_HANDLE", MetaKey.SHOWSMARTLINKHANDLE);

    /**
     * Whether Lanes are displayed horizontally or vertically in the diagram.
     */
    @objid ("5f47ee95-f5be-4b87-ac64-e09fb6e7d6fb")
    public static final StyleKey HORIZONTAL_LANES = createStyleKey("DIAGRAM_HORIZONTAL_LANES", Boolean.class);

}
