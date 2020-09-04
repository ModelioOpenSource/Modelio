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

package org.modelio.diagram.editor.usecase.elements.usecase;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.usecase.style.UseCaseAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

@objid ("5e6420b3-55b7-11e2-877f-002564c97630")
public class GmUseCaseSimpleStyleKeys extends UseCaseAbstractStyleKeyProvider {
    @objid ("d9be08f8-55c2-11e2-9337-002564c97630")
    public static final StyleKey REPMODE = GmUseCaseStructuredStyleKeys.REPMODE;

    @objid ("d9be08fb-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLCOLOR = GmUseCaseStructuredStyleKeys.FILLCOLOR;

    @objid ("d9be08fe-55c2-11e2-9337-002564c97630")
    public static final StyleKey FILLMODE = GmUseCaseStructuredStyleKeys.FILLMODE;

    @objid ("d9be0901-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINECOLOR = GmUseCaseStructuredStyleKeys.LINECOLOR;

    @objid ("d9be0904-55c2-11e2-9337-002564c97630")
    public static final StyleKey LINEWIDTH = GmUseCaseStructuredStyleKeys.LINEWIDTH;

    @objid ("d9be0907-55c2-11e2-9337-002564c97630")
    public static final StyleKey FONT = GmUseCaseStructuredStyleKeys.FONT;

    @objid ("d9be090a-55c2-11e2-9337-002564c97630")
    public static final StyleKey TEXTCOLOR = GmUseCaseStructuredStyleKeys.TEXTCOLOR;

    @objid ("d9be090d-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWNAME = GmUseCaseStructuredStyleKeys.SHOWNAME;

    @objid ("d9be0910-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWSTEREOTYPES = GmUseCaseStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("d9be0913-55c2-11e2-9337-002564c97630")
    public static final StyleKey SHOWTAGS = GmUseCaseStructuredStyleKeys.SHOWTAGS;

}
