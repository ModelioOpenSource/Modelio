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

package org.modelio.diagram.editor.statik.elements.ports;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Style keys for {@link GmPort}.
 * 
 * @author cmarin
 */
@objid ("448cd5ce-7b72-4154-b067-a6bd12898fff")
public class GmPortUserImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("0ab07995-853b-4f2b-b6ca-6e7e054a80bd")
     static final StyleKey REPMODE = createStyleKey("PORT_REPMODE", MetaKey.REPMODE);

    @objid ("27609545-8e45-4f31-b1dc-b976368b2355")
     static final StyleKey FONT = GmPortStructuredStyleKeys.FONT;

    @objid ("2ef5acbb-c59c-4a0b-ad5b-6045f183f21f")
     static final StyleKey TEXTCOLOR = GmPortStructuredStyleKeys.TEXTCOLOR;

    @objid ("8a64db5b-21e7-4feb-ac34-abe72f17d463")
     static final StyleKey SHOWSTEREOTYPES = GmPortStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("4d6d8aa0-1870-4c3e-992f-7dc9485a97fe")
     static final StyleKey SHOWTAGS = GmPortStructuredStyleKeys.SHOWTAGS;

    @objid ("aa1ed794-f3d2-45ff-983c-b2b508ba639d")
     static final StyleKey SHOWLABEL = GmPortStructuredStyleKeys.SHOWLABEL;

}
