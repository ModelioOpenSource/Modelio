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

package org.modelio.diagram.editor.statik.elements.classifier.style;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.style.StaticAbstractStyleKeyProvider;
import org.modelio.diagram.styles.core.MetaKey;
import org.modelio.diagram.styles.core.StyleKey;

/**
 * Attributes style keys.
 * 
 * @author cmarin
 */
@objid ("1756be64-1cfd-46ad-a40c-9efeb77c1e90")
public final class ClassifierAttributeKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Attributes stereotype display mode.
     */
    @objid ("5a4bd692-64db-4b1f-8035-df83dacddcb4")
    public final StyleKey SHOWSTEREOTYPES;

    /**
     * Display attributes tagged values.
     */
    @objid ("174cbc9d-3e6f-49e7-9571-5c8f64539c2d")
    public final StyleKey SHOWTAGS;

    /**
     * Display attributes visibilities.
     */
    @objid ("75d1766d-9177-4559-b8eb-a6296fcbecc8")
    public final StyleKey SHOWVISIBILITY;

    /**
     * Display attributes.
     */
    @objid ("a5373a19-55c2-11e2-9337-002564c97630")
    public final StyleKey ATTGROUPVISIBLE;

    /**
     * Instantiates a Classifier attributes group style key provider.
     * 
     * @param prefix a prefix for style key names.
     * It is advised to use the upper case metaclass name as prefix.
     */
    @objid ("f15c562c-940f-49b7-98c1-d37590612ee6")
    public ClassifierAttributeKeys(String prefix) {
        this.SHOWSTEREOTYPES = createStyleKey(prefix+"_ATT_SHOWSTEREOTYPES", MetaKey.AttGroup.ATTSHOWSTEREOTYPES);
        this.SHOWTAGS = createStyleKey(prefix+"_ATT_SHOWTAGS", MetaKey.AttGroup.ATTSHOWTAGS);
        this.SHOWVISIBILITY = createStyleKey(prefix+"_ATT_SHOWVISIBILITY", MetaKey.AttGroup.ATTSHOWVISIBILITY); 
        this.ATTGROUPVISIBLE = createStyleKey(prefix+"_ATT_GROUPVISIBLE", MetaKey.AttGroup.ATTSHOWGROUP);
    }

}
