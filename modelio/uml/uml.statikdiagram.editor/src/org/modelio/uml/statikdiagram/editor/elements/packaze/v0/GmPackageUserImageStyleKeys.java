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

package org.modelio.uml.statikdiagram.editor.elements.packaze.v0;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.elements.packaze.GmPackage;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * Style key provider for {@link GmPackage}.
 * 
 * @author cmarin
 */
@objid ("816823bf-f61d-40ec-b458-8a0a1b0f4728")
class GmPackageUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("92298e0c-0a73-4345-b971-5694cabb8737")
     static final StyleKey REPMODE = GmPackageStructuredStyleKeys.REPMODE;

    @objid ("9ffc349a-0ac3-4f32-91a2-fa74f3287e65")
     static final StyleKey FONT = GmPackageStructuredStyleKeys.FONT;

    @objid ("c86b2028-34eb-42c8-94b3-b2d64117458c")
     static final StyleKey TEXTCOLOR = GmPackageStructuredStyleKeys.TEXTCOLOR;

    @objid ("20826f9f-57fa-4f91-b954-be10cb630237")
     static final StyleKey SHOWNAME = GmPackageStructuredStyleKeys.SHOWNAME;

    @objid ("69407e07-d601-4b9e-82d0-0a02ebbc4879")
     static final StyleKey SHOWSTEREOTYPES = GmPackageStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("e9d0a687-8752-46b5-83e1-f415a047ae61")
     static final StyleKey SHOWTAGS = GmPackageStructuredStyleKeys.SHOWTAGS;

}
