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

package org.modelio.uml.statikdiagram.editor.elements.packaze;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey.UmaskByVisibilityStragegy;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * Style key provider for {@link GmPackage}.
 * 
 * @author cmarin
 */
@objid ("362516c1-55b7-11e2-877f-002564c97630")
public class GmPackageStructuredStyleKeys extends StaticAbstractStyleKeyProvider {
    @objid ("a5534d99-55c2-11e2-9337-002564c97630")
     static final StyleKey REPMODE = createStyleKey("PACKAGE_REPMODE", MetaKey.REPMODE);

    @objid ("a55374a9-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLCOLOR = createStyleKey("PACKAGE_FILLCOLOR", MetaKey.FILLCOLOR);

    @objid ("a55374ab-55c2-11e2-9337-002564c97630")
     static final StyleKey FILLMODE = createStyleKey("PACKAGE_FILLMODE", MetaKey.FILLMODE);

    @objid ("a55374ad-55c2-11e2-9337-002564c97630")
     static final StyleKey LINECOLOR = createStyleKey("PACKAGE_LINECOLOR", MetaKey.LINECOLOR);

    @objid ("a5539bba-55c2-11e2-9337-002564c97630")
     static final StyleKey LINEWIDTH = createStyleKey("PACKAGE_LINEWIDTH", MetaKey.LINEWIDTH);

    @objid ("a5539bbc-55c2-11e2-9337-002564c97630")
     static final StyleKey FONT = createStyleKey("PACKAGE_FONT", MetaKey.FONT);

    @objid ("a553c2c9-55c2-11e2-9337-002564c97630")
     static final StyleKey TEXTCOLOR = createStyleKey("PACKAGE_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("a553c2cb-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWNAME = createStyleKey("PACKAGE_SHOWNAME", MetaKey.SHOWNAME);

    @objid ("a553c2cd-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWSTEREOTYPES = createStyleKey("PACKAGE_SHOWSTEREOTYPES", MetaKey.SHOWSTEREOTYPES);

    @objid ("a553e9da-55c2-11e2-9337-002564c97630")
     static final StyleKey SHOWTAGS = createStyleKey("PACKAGE_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("a553e9dc-55c2-11e2-9337-002564c97630")
     static final StyleKey INSTANCEUNMASKING = createStyleKey("PACKAGE_INSTANCEUNMASKING", Boolean.class);

    @objid ("a55410e9-55c2-11e2-9337-002564c97630")
     static final StyleKey UNMASKINGSTRATEGY = createStyleKey("PACKAGE_CONTENTS",
                                                             UmaskByVisibilityStragegy.class);

}
