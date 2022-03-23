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
package org.modelio.uml.sequencediagram.editor.elements.sequencediagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.elements.common.abstractdiagram.GmAbstractDiagramStyleKeys;
import org.modelio.diagram.elements.common.abstractdiagram.LayoutAssistantStyleKeys;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.sequencediagram.editor.elements.message.GmMessageStyleKeys;

/**
 * This class provides the StyleKey constants for a GmSequenceDiagram.
 */
@objid ("d975fa2a-55b6-11e2-877f-002564c97630")
@SuppressWarnings ("hiding")
public class GmSequenceDiagramStyleKeys extends GmAbstractDiagramStyleKeys {
    /**
     * Snap to geometry.
     */
    @objid ("a0214990-5d68-443f-a94b-a4468d0a8d72")
    static final StyleKey SNAPTOGEOMETRY = GmAbstractDiagramStyleKeys.SNAPTOGEOMETRY;

    @objid ("4fc54b8a-55c2-11e2-9337-002564c97630")
    static final StyleKey VIEWGRID = GmAbstractDiagramStyleKeys.VIEWGRID;

    @objid ("4fc54b8c-55c2-11e2-9337-002564c97630")
    static final StyleKey SNAPTOGRID = GmAbstractDiagramStyleKeys.SNAPTOGRID;

    @objid ("4fc54b8e-55c2-11e2-9337-002564c97630")
    static final StyleKey GRIDSPACING = GmAbstractDiagramStyleKeys.GRIDSPACING;

    @objid ("4fc54b90-55c2-11e2-9337-002564c97630")
    static final StyleKey GRIDCOLOR = GmAbstractDiagramStyleKeys.GRIDCOLOR;

    @objid ("4fc54b92-55c2-11e2-9337-002564c97630")
    static final StyleKey GRIDALPHA = GmAbstractDiagramStyleKeys.GRIDALPHA;

    @objid ("4fc54b94-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLCOLOR = GmAbstractDiagramStyleKeys.FILLCOLOR;

    @objid ("4fc54b96-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLIMAGE = GmAbstractDiagramStyleKeys.FILLIMAGE;

    @objid ("4fc54b98-55c2-11e2-9337-002564c97630")
    static final StyleKey FILLALPHA = GmAbstractDiagramStyleKeys.FILLALPHA;

    @objid ("4fc54b9a-55c2-11e2-9337-002564c97630")
    static final StyleKey SHOW_PAGES = GmAbstractDiagramStyleKeys.SHOW_PAGES;

    @objid ("4fc54b9c-55c2-11e2-9337-002564c97630")
    static final StyleKey PAGE_SIZE = GmAbstractDiagramStyleKeys.PAGE_SIZE;

    @objid ("d65dcc86-d736-4204-a22f-9b12011238f6")
    static final LayoutAssistantStyleKeys layoutAssistantStyle = GmAbstractDiagramStyleKeys.autoLayoutStyle;

    /**
     * Show message sequence. Links to {@link GmMessageStyleKeys#SHOWSEQUENCE}.
     */
    @objid ("d8c65fb8-6aee-403d-b69b-92b922ccbe57")
    static final StyleKey SHOWSEQUENCE = GmMessageStyleKeys.SHOWSEQUENCE;

}
