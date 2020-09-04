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
@objid ("3642149e-55b7-11e2-877f-002564c97630")
public class GmPortImageStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a751b5a9-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("PORT_REPMODE", MetaKey.REPMODE);

    @objid ("a751b5ab-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = GmPortStructuredStyleKeys.FONT;

    @objid ("a751b5ad-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = GmPortStructuredStyleKeys.TEXTCOLOR;

    @objid ("a751b5af-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = GmPortStructuredStyleKeys.SHOWSTEREOTYPES;

    @objid ("a751b5b1-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = GmPortStructuredStyleKeys.SHOWTAGS;

    @objid ("a751b5b3-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWLABEL = GmPortStructuredStyleKeys.SHOWLABEL;

}
