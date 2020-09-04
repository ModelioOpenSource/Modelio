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

package org.modelio.diagram.editor.statik.elements.packaze.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.packaze.GmPackage;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmPackage}.
 * 
 * @author cmarin
 */
@objid ("363458c7-55b7-11e2-877f-002564c97630")
class GmPackageSimpleStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a7488df6-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmPackageStructuredStyleKeys.REPMODE;

    @objid ("a7488df8-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = GmPackageStructuredStyleKeys.FILLCOLOR;

    @objid ("a7488dfa-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = GmPackageStructuredStyleKeys.FILLMODE;

    @objid ("a7488dfc-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = GmPackageStructuredStyleKeys.LINECOLOR;

    @objid ("a7488dfe-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = GmPackageStructuredStyleKeys.LINEWIDTH;

    @objid ("a7488e00-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmPackageStructuredStyleKeys.FONT;

    @objid ("a7488e02-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmPackageStructuredStyleKeys.TEXTCOLOR;

    @objid ("a7488e04-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = GmPackageStructuredStyleKeys.SHOWNAME;

    @objid ("a7488e06-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmPackageStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a7488e08-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmPackageStructuredStyleKeys.SHOWTAGS;

}
