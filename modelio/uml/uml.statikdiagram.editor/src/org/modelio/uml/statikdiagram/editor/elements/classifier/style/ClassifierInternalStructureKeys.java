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

package org.modelio.uml.statikdiagram.editor.elements.classifier.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;
import org.modelio.uml.statikdiagram.editor.style.StaticAbstractStyleKeyProvider;

/**
 * Style keys for all internal structure group members.
 * <p>
 * INTERNALSVIEWMODE and INTERNALS are used for internal structure zone too.
 * 
 * @author cmarin
 */
@objid ("4b8e5227-6967-4fa8-9f56-2c7b811922f3")
public class ClassifierInternalStructureKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Internal structure visualization mode: labels, diagram or none.
     */
    @objid ("aef79256-65f6-4a79-8ada-d2b40e309362")
    public final StyleKey INTERNALSVIEWMODE;

    /**
     * Text color.
     */
    @objid ("47ea4dce-54fb-46e5-8f00-7fa73c438571")
    public final StyleKey TEXTCOLOR;

    /**
     * Font
     */
    @objid ("51db4d98-b4fb-4dff-bb67-4c31c702653e")
    public final StyleKey FONT;

    /**
     * Stereotype display mode.
     */
    @objid ("1fed876c-beb4-4004-beb8-1708ec22f72b")
    public final StyleKey SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("fb3d369b-cf51-4d9e-8afb-0f684e2000e8")
    public final StyleKey SHOWTAGS;

    /**
     * Auto unmask internal structure content. Boolean type.
     */
    @objid ("49b5fe55-d908-4045-8e9e-3d60e5c7c9f0")
    public final StyleKey AUTOUNMASK;

    /**
     * Instantiates a Classifier attributes group style key provider.
     * 
     * @param prefix a prefix for style key names.
     * It is advised to use the upper case metaclass name as prefix.
     */
    @objid ("bb785cf7-8956-4ad0-8b1c-daa9d7b414e4")
    public ClassifierInternalStructureKeys(String prefix) {
        this.INTERNALSVIEWMODE = createStyleKey(prefix+"_INTERNALSVIEWMODE", MetaKey.InternalGroup.INTVIEWMODE);
        this.TEXTCOLOR = createStyleKey(prefix+"_INTERNAL_TEXTCOLOR", MetaKey.InternalGroup.INTTEXTCOLOR);
        this.FONT = createStyleKey(prefix+"_INTERNAL_FONT", MetaKey.InternalGroup.INTFONT);
        this.SHOWSTEREOTYPES = createStyleKey(prefix+"_INTERNAL_SHOWSTEREOTYPES",
                    MetaKey.InternalGroup.INTSHOWSTEREOTYPES);
        this.SHOWTAGS = createStyleKey(prefix+"_INTERNAL_SHOWTAGS", MetaKey.InternalGroup.INTSHOWTAGS);
        this.AUTOUNMASK = createStyleKey(prefix+"_INTERNAL_AUTOUNMASK", MetaKey.InternalGroup.INTAUTOUNMASK);
    }

}
