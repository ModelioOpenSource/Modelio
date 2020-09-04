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

package org.modelio.diagram.elements.common.abstractdiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.swt.graphics.Color;
import org.modelio.diagram.elements.style.ElementsAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * This class provides the StyleKey constants for a GmActivityDiagram when its representation mode is
 * RepresentationMode.IMAGE
 */
@objid ("7e1dc282-1dec-11e2-8cad-001ec947c8cc")
public class GmAbstractDiagramStyleKeys extends ElementsAbstractStyleKeyProvider {
    /**
     * StyleKey enabling/disabling the geometry snap feature. Boolean value.
     */
    @objid ("d4d3d968-fbd0-4928-b668-8e29aafa315c")
    public static final StyleKey SNAPTOGEOMETRY = createStyleKey("ABSTRACT_DIAGRAM_SNAPTOGEOMETRY", Boolean.class);

    /**
     * StyleKey defining the visibility of the grid. Boolean value.
     */
    @objid ("7e1dc284-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey VIEWGRID = createStyleKey("ABSTRACT_DIAGRAM_VIEWGRID", Boolean.class);

    /**
     * StyleKey enabling/disabling the grid snpa feature. Boolean value.
     */
    @objid ("7e1dc287-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey SNAPTOGRID = createStyleKey("ABSTRACT_DIAGRAM_SNAPTOGRID", Boolean.class);

    /**
     * StyleKey defining the grid size (grid cells are squares). Integer value.
     */
    @objid ("7e1dc28a-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey GRIDSPACING = createStyleKey("ABSTRACT_DIAGRAM_GRIDSPACING", Integer.class);

    /**
     * This StyleKey defines the grid color. Color value.
     */
    @objid ("7e1dc28d-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey GRIDCOLOR = createStyleKey("ABSTRACT_DIAGRAM_GRIDCOLOR", Color.class);

    /**
     * This StyleKey defines the grid transparency. Integer value from 0 (full transaprency) to 255 (no transparency)
     */
    @objid ("7e1dc290-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey GRIDALPHA = createStyleKey("ABSTRACT_DIAGRAM_GRIDALPHA", Integer.class);

    /**
     * This StyleKey defines the background filling color. Color value.
     */
    @objid ("7e1dc293-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey FILLCOLOR = createStyleKey("ABSTRACT_DIAGRAM_FILLCOLOR", MetaKey.FILLCOLOR);

    /**
     * This StyleKey defines the backgroundimage ressource URL. String value. If PAGE_SIZE is defined, the image is
     * stretched to fill PAGE_SIZE
     */
    @objid ("7e1dc296-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey FILLIMAGE = createStyleKey("ABSTRACT_DIAGRAM_FILLIMAGE", String.class);

    /**
     * This StyleKey defines the fill transparency. the value applies to both the fill image and the fill color. Integer
     * value from 0 (full transaprency) to 255 (no transparency)
     */
    @objid ("7e1dc299-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey FILLALPHA = createStyleKey("ABSTRACT_DIAGRAM_FILLALPHA", Integer.class);

    /**
     * This StyleKey defines the page boundaries lines visibility. Boolean value.
     */
    @objid ("7e1dc29c-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey SHOW_PAGES = createStyleKey("ABSTRACT_DIAGRAM_SHOW_PAGES", Boolean.class);

    /**
     * This StyleKey defines the page size. String value.
     * <p>
     * Supported string format:<ul>
     * <li> discrete values: A5H,A4H,A3H,A2H,A1H,A0H, A5V,A4V,A3V,A2V,A1V,A0V
     * <li> inches values: '8.5" x 3.4"'
     * <li> mm values: '210 mm x 297mm'
     * </ul>
     */
    @objid ("7e2024bd-1dec-11e2-8cad-001ec947c8cc")
    public static final StyleKey PAGE_SIZE = createStyleKey("ABSTRACT_DIAGRAM_PAGE_SIZE", String.class);

    /**
     * Layout assistant style keys group.
     */
    @objid ("cd4882b3-f952-4da8-9642-f93fbd1d1e25")
    public static final LayoutAssistantStyleKeys autoLayoutStyle = new LayoutAssistantStyleKeys();

    /**
     * Display or not "smart link" handle on hover. Boolean value.
     */
    @objid ("d072d771-929e-4daa-966b-a7296ef70a13")
    public static final StyleKey SHOW_SMARTLINK_HANDLE = createStyleKey("ABSTRACT_DIAGRAM_SHOW_SMARTLINK_HANDLE", MetaKey.SHOWSMARTLINKHANDLE);

}
