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
 * Operations style keys.
 * 
 * @author cmarin
 */
@objid ("c937442b-ab94-43e4-bd1b-aab9049444e5")
public class ClassifierOperationKeys extends StaticAbstractStyleKeyProvider {
    /**
     * Display operations signature.
     */
    @objid ("aa8413b6-e959-4300-ba7e-03c38117aac8")
    public final StyleKey SHOWSIGNATURE;

    /**
     * Operations stereotype display mode.
     */
    @objid ("0f0f6001-7ab4-48a8-8199-baa1c6429e55")
    public final StyleKey SHOWSTEREOTYPES;

    /**
     * Display operations tagged values.
     */
    @objid ("096a9722-74f8-459e-a332-3a633d5e9ecc")
    public final StyleKey SHOWTAGS;

    /**
     * Display operations visibilities.
     */
    @objid ("cc1a168c-23e5-4bf5-a6b3-7df0a9506405")
    public final StyleKey SHOWVISIBILITY;

    /**
     * Display operations visibilities.
     */
    @objid ("f77fcac3-5259-4eeb-931c-75b6997261ac")
    public final StyleKey WRAPLABEL;

    /**
     * Display operations group.
     */
    @objid ("04a014c5-d453-44fe-8d14-44651b712252")
    public final StyleKey OPERATIONGROUPVISIBLE;

    /**
     * Instantiates a Classifier attributes group style key provider.
     * @param prefix a prefix for style key names.
     * It is advised to use the upper case metaclass name as prefix.
     */
    @objid ("552e91f8-9ffa-411d-9b21-e20fee1438f0")
    public  ClassifierOperationKeys(String prefix) {
        this.SHOWSIGNATURE = createStyleKey(prefix+"_OP_SHOWSIGNATURE", MetaKey.OperationGroup.OPSHOWSIGNATURE);
        this.SHOWSTEREOTYPES = createStyleKey(prefix+"_OP_SHOWSTEREOTYPES", MetaKey.OperationGroup.OPSHOWSTEREOTYPES);
        this.SHOWTAGS = createStyleKey(prefix+"_OP_SHOWTAGS", MetaKey.OperationGroup.OPSHOWTAGS);
        this.SHOWVISIBILITY = createStyleKey(prefix+"_OP_SHOWVISIBILITY", MetaKey.OperationGroup.OPSHOWVISIBILITY);
        this.WRAPLABEL = createStyleKey(prefix+"_OP_WRAPLABEL", MetaKey.OperationGroup.OPWRAPLABEL);
        this.OPERATIONGROUPVISIBLE = createStyleKey(prefix+"_OP_GROUPVISIBLE", MetaKey.OperationGroup.OPSHOWGROUP);
        
    }

}
