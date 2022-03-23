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
package org.modelio.uml.activitydiagram.editor.elements.activitydiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagramStyleKeys;
import org.modelio.diagram.elements.common.abstractdiagram.LayoutAssistantStyleKeys;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmActivityDiagram when its representation mode is RepresentationMode.STRUCTURED
 */
@objid ("29967a58-55b6-11e2-877f-002564c97630")
@SuppressWarnings ("hiding")
public class GmActivityDiagramStyleKeys extends GmAbstractDiagramStyleKeys {
    /**
     * Snap to geometry.
     */
    @objid ("6933d357-4a6f-42be-854c-8ed400cfe5ba")
    static final StyleKey SNAPTOGEOMETRY = GmAbstractDiagramStyleKeys.SNAPTOGEOMETRY;

    @objid ("d14fbfca-55c0-11e2-9337-002564c97630")
    static final StyleKey VIEWGRID = GmAbstractDiagramStyleKeys.VIEWGRID;

    @objid ("d14fbfcc-55c0-11e2-9337-002564c97630")
    static final StyleKey SNAPTOGRID = GmAbstractDiagramStyleKeys.SNAPTOGRID;

    @objid ("d14fbfce-55c0-11e2-9337-002564c97630")
    static final StyleKey GRIDSPACING = GmAbstractDiagramStyleKeys.GRIDSPACING;

    @objid ("d14fbfd0-55c0-11e2-9337-002564c97630")
    static final StyleKey GRIDCOLOR = GmAbstractDiagramStyleKeys.GRIDCOLOR;

    @objid ("d14fbfd2-55c0-11e2-9337-002564c97630")
    static final StyleKey GRIDALPHA = GmAbstractDiagramStyleKeys.GRIDALPHA;

    @objid ("d14fbfd4-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = GmAbstractDiagramStyleKeys.FILLCOLOR;

    @objid ("d14fbfd6-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLIMAGE = GmAbstractDiagramStyleKeys.FILLIMAGE;

    @objid ("d14fbfd8-55c0-11e2-9337-002564c97630")
    static final StyleKey FILLALPHA = GmAbstractDiagramStyleKeys.FILLALPHA;

    @objid ("d14fbfda-55c0-11e2-9337-002564c97630")
    static final StyleKey SHOW_PAGES = GmAbstractDiagramStyleKeys.SHOW_PAGES;

    @objid ("d14fbfdc-55c0-11e2-9337-002564c97630")
    static final StyleKey PAGE_SIZE = GmAbstractDiagramStyleKeys.PAGE_SIZE;

    @objid ("bdd9dd42-50a1-4423-87c3-820724ee8415")
    static final LayoutAssistantStyleKeys autoLayoutStyle = GmAbstractDiagramStyleKeys.autoLayoutStyle;

    @objid ("97211f50-ad89-4ae3-8012-e563aabf8660")
    static final StyleKey SHOW_SMARTLINK_HANDLE = GmAbstractDiagramStyleKeys.SHOW_SMARTLINK_HANDLE;

}
