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

package org.modelio.diagram.editor.statik.elements.packaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmPackage}.
 * 
 * @author cmarin
 */
@objid ("362082a7-55b7-11e2-877f-002564c97630")
public class GmPackageImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a737c50a-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = GmPackageStructuredStyleKeys.REPMODE;

    @objid ("a737c50c-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmPackageStructuredStyleKeys.FONT;

    @objid ("a737c50e-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmPackageStructuredStyleKeys.TEXTCOLOR;

    @objid ("a737c510-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmPackageStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a737c512-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmPackageStructuredStyleKeys.SHOWTAGS;

}
