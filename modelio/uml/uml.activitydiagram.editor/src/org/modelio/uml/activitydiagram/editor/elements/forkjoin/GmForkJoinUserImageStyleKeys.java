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
package org.modelio.uml.activitydiagram.editor.elements.forkjoin;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.activitydiagram.editor.style.ActivityAbstractStyleKeyProvider;

/**
 * This class provides the StyleKey constants for a GmForkjoin when its representation mode is RepresentationMode.IMAGE
 */
@objid ("9c5e54b5-2e42-436f-a9ba-69c2d26f9ba7")
public class GmForkJoinUserImageStyleKeys extends ActivityAbstractStyleKeyProvider {
    @objid ("f8bd88ac-9ad9-4dfe-9fe8-2b78d9ecd7bc")
    static final StyleKey REPMODE = createStyleKey("FORKJOIN_REPMODE", MetaKey.REPMODE);

    @objid ("c82a78e5-9685-4d48-aae4-1277580b112c")
    static final StyleKey FONT = createStyleKey("FORKJOIN_FONT", MetaKey.FONT);

    @objid ("c9f3e414-0559-4346-b3b4-5b02ab6c2074")
    static final StyleKey TEXTCOLOR = createStyleKey("FORKJOIN_TEXTCOLOR", MetaKey.TEXTCOLOR);

    @objid ("7c022e1e-a2b9-455c-9378-532f4968d9c4")
    static final StyleKey SHOWSTEREOTYPES = createStyleKey("FORKJOIN_SHOWSTEREOTYPES",
                MetaKey.SHOWSTEREOTYPES);

    @objid ("f8d77fa4-3e0d-4603-8d30-a25f82de1254")
    static final StyleKey SHOWTAGS = createStyleKey("FORKJOIN_SHOWTAGS", MetaKey.SHOWTAGS);

    @objid ("8002596b-4285-4922-9ae7-6824cde47992")
    static final StyleKey SHOWLABEL = createStyleKey("FORKJOIN_SHOWLABEL", MetaKey.SHOWLABEL);

}
