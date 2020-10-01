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
 * Style keys for all inner classifiers group members.
 * 
 * @author cmarin
 * @since 3.7
 */
@objid ("826d6082-f6a7-4a38-b37b-0dfa7f734d09")
public class ClassifierInnerGroupKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Text color.
     */
    @objid ("80bb2fa3-1bab-4093-9c2d-107949dc0327")
    public final StyleKey TEXTCOLOR;

    /**
     * Font
     */
    @objid ("0552a3a4-b875-44ac-8d68-b4e290da771e")
    public final StyleKey FONT;

    /**
     * Name display mode
     */
    @objid ("d0a3a0dc-6b11-4045-92db-1c27dde1e224")
    public final StyleKey SHOWNAME;

    /**
     * Stereotype display mode.
     */
    @objid ("64fcb199-445c-40b4-8948-06a3e6ebcfff")
    public final StyleKey SHOWSTEREOTYPES;

    /**
     * Display tagged values.
     */
    @objid ("9c312b34-beed-4a90-b55d-1211cf3f1d19")
    public final StyleKey SHOWTAGS;

    /**
     * Display visibility
     */
    @objid ("28b6fe97-576c-44e5-b5c6-7479ab26d440")
    public final StyleKey SHOWVISIBILITY;

    /**
     * Inner classes visualization mode: labels, diagram or none.
     * type: {@link MetaKey.InnerGroup#INNERVIEWMODE}
     */
    @objid ("158bbb8e-29fa-4afd-93bf-f5537e1b2715")
    public final StyleKey INNERVIEWMODE;

    /**
     * Instantiates a Classifier inner group style key provider
     * 
     * @param prefix a prefix for style key names.
     * It is advised to use the upper case metaclass name as prefix.
     */
    @objid ("551640e5-4d24-48bc-b9bb-21189aea62f5")
    public ClassifierInnerGroupKeys(String prefix) {
        this.TEXTCOLOR = createStyleKey(prefix+"_INNER_TEXTCOLOR", MetaKey.InnerGroup.INNERTEXTCOLOR);
        this.FONT = createStyleKey(prefix+"_INNER_FONT", MetaKey.InnerGroup.INNERFONT);
        this.SHOWNAME = createStyleKey(prefix+"_INNER_SHOWNAME", MetaKey.InnerGroup.INNERSHOWNAME);
        this.SHOWSTEREOTYPES = createStyleKey(prefix+"_INNER_SHOWSTEREOTYPES", MetaKey.InnerGroup.INNERSHOWSTEREOTYPES);
        this.SHOWTAGS = createStyleKey(prefix+"_INNER_SHOWTAGS", MetaKey.InnerGroup.INNERSHOWTAGS);
        this.SHOWVISIBILITY = createStyleKey(prefix+"_INNER_SHOWVISIBILITY", MetaKey.InnerGroup.INNERSHOWVISIBILITY);
        this.INNERVIEWMODE = createStyleKey(prefix+"_INNERVIEWMODE",
                MetaKey.InnerGroup.INNERVIEWMODE);
    }

}
