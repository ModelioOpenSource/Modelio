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

package org.modelio.diagram.editor.usecase.elements.usecasediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.usecase.style.UseCaseAbstractStyleKeyProvider;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagramStyleKeys;
import org.modelio.diagram.elements.common.abstractdiagram.LayoutAssistantStyleKeys;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a {@link GmUseCaseDiagram}.
 */
@objid ("5e85b297-55b7-11e2-877f-002564c97630")
public class GmUseCaseDiagramStyleKeys extends UseCaseAbstractStyleKeyProvider {
    /**
     * StyleKey showing/hiding the system boundaries. Boolean value.
     */
    @objid ("d9934f6d-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOW_SYSTEM = createStyleKey("USECASE_DIAGRAM_SHOW_SYSTEM", Boolean.class);

    /**
     * Snap to geometry.
     */
    @objid ("a19699d1-d9f3-461f-b4d9-7e267577f881")
     static final StyleKey SNAPTOGEOMETRY = GmAbstractDiagramStyleKeys.SNAPTOGEOMETRY;

    /**
     * StyleKey defining the visibility of the grid. Boolean value.
     */
    @objid ("d9934f70-55c2-11e2-9337-002564c97630")
    public static final StyleKey VIEWGRID = GmAbstractDiagramStyleKeys.VIEWGRID;

    /**
     * StyleKey enabling/disabling the grid snap feature. Boolean value.
     */
    @objid ("d9934f73-55c2-11e2-9337-002564c97630")
    public static final StyleKey SNAPTOGRID = GmAbstractDiagramStyleKeys.SNAPTOGRID;

    /**
     * StyleKey defining the grid size (grid cells are squares). Integer value.
     */
    @objid ("d9934f76-55c2-11e2-9337-002564c97630")
    public static final StyleKey GRIDSPACING = GmAbstractDiagramStyleKeys.GRIDSPACING;

    /**
     * This StyleKey defines the grid color. Color value.
     */
    @objid ("d9934f79-55c2-11e2-9337-002564c97630")
    public static final StyleKey GRIDCOLOR = GmAbstractDiagramStyleKeys.GRIDCOLOR;

    /**
     * This StyleKey defines the grid transparency. Integer value from 0 (full transaprency) to 255 (no transparency)
     */
    @objid ("d994d60b-55c2-11e2-9337-002564c97630")
    public static final StyleKey GRIDALPHA = GmAbstractDiagramStyleKeys.GRIDALPHA;

    /**
     * This StyleKey defines the background filling color. Color value.
     */
    @objid ("d994d60e-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = GmAbstractDiagramStyleKeys.FILLCOLOR;

    /**
     * This StyleKey defines the backgroundimage ressource URL. String value. If PAGE_SIZE is defined, the image is
     * stretched to fill PAGE_SIZE
     */
    @objid ("d994d611-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLIMAGE = GmAbstractDiagramStyleKeys.FILLIMAGE;

    /**
     * This StyleKey defines the fill transparency. the value applies to both the fill image and the fill color. Integer
     * value from 0 (full transaprency) to 255 (no transparency)
     */
    @objid ("d994d614-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLALPHA = GmAbstractDiagramStyleKeys.FILLALPHA;

    /**
     * This StyleKey defines the page boundaries lines visibility. Boolean value.
     */
    @objid ("d994d617-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOW_PAGES = GmAbstractDiagramStyleKeys.SHOW_PAGES;

    /**
     * This StyleKey defines the page size. String value.
     * <p>
     * Supported string format:
     * <ul>
     * <li>discrete values: A5H,A4H,A3H,A2H,A1H,A0H, A5V,A4V,A3V,A2V,A1V,A0V
     * <li>inches values: '8.5" x 3.4"'
     * <li>mm values: '210 mm x 297mm'
     * </ul>
     */
    @objid ("d994d61a-55c2-11e2-9337-002564c97630")
    public static final StyleKey PAGE_SIZE = GmAbstractDiagramStyleKeys.PAGE_SIZE;

    /**
     * Layout assistant style keys group.
     */
    @objid ("50e47e9e-7719-41f6-978b-115616deb84a")
    public static final LayoutAssistantStyleKeys autoLayoutStyle = GmAbstractDiagramStyleKeys.autoLayoutStyle;

    /**
     * Display or not "smart link" handle on hover. Boolean value.
     */
    @objid ("8754bb8e-422d-46bb-be0b-0b93ca125032")
     static final StyleKey SHOW_SMARTLINK_HANDLE = GmAbstractDiagramStyleKeys.SHOW_SMARTLINK_HANDLE;

}
