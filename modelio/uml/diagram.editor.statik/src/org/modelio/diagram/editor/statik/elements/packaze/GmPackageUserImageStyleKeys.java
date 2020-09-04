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

package org.modelio.diagram.editor.statik.elements.packaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style key provider for {@link GmPackage}.
 * 
 * @author cmarin
 */
@objid ("356b5bfd-75c9-4c74-94f0-8fd768b6c359")
public class GmPackageUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("85459d78-144e-4ea1-826d-271149de4e1a")
     static final StyleKey REPMODE = GmPackageStructuredStyleKeys.REPMODE;

    @objid ("2302980e-0fb6-4590-b5c0-442859725e58")
     static final StyleKey FONT = GmPackageStructuredStyleKeys.FONT;

    @objid ("99e93e0b-5fc8-4b7e-a407-571b2fd64aba")
     static final StyleKey TEXTCOLOR = GmPackageStructuredStyleKeys.TEXTCOLOR;

    @objid ("7bbba5b5-4a4c-4883-947a-069245fb7832")
     static final StyleKey SHOWSTEREOTYPES = GmPackageStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("b44347cb-1ae5-457e-bc97-3fb884f3116a")
     static final StyleKey SHOWTAGS = GmPackageStructuredStyleKeys.SHOWTAGS;

}
