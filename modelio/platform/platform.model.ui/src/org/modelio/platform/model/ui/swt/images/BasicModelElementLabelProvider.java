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

package org.modelio.platform.model.ui.swt.images;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.modelio.platform.model.ui.swt.labelprovider.UniversalLabelProvider;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Basic label provider for model elements.
 * <p>
 * Provides the element name as label optionally completed by a '(from namespace)' clause, the metaclass icon as image and apply
 * text standard decoration (static, abstract).<br>
 * This provider can typically be used as a base provider for a {@link ElementDecoratedStyledLabelProvider}.
 * 
 * 
 * 
 * @author phv
 */
@objid ("39807b29-9409-41a9-a853-8482f2952d74")
public class BasicModelElementLabelProvider extends AbstractModelioElementLabelProvider {
    @objid ("13eee7c4-4e33-4d6d-9c18-0811b20d4af6")
    private boolean withFromClause;

    /**
     * Label provider computing an element's label in a 'standard' way.
     */
    @objid ("e84bf694-9f52-4775-881c-20d963ef2975")
    private UniversalLabelProvider baseLabelProvider = new UniversalLabelProvider();

    /**
     * C'tor
     * 
     * <p>
     * The label provider does not add a '(from namespace)' clause to the element name.<br/>
     * </p>
     */
    @objid ("e78018ef-762d-4be3-aff3-a6449ca76c56")
    public BasicModelElementLabelProvider() {
        this(false);
    }

    /**
     * C'tor
     * 
     * @param withFromClause if <code>true</code>,  the label provider adds a '(from namespace)' clause to the element name.
     */
    @objid ("73f4d743-12ab-4e8d-ae04-6d91d6e13b58")
    public BasicModelElementLabelProvider(boolean withFromClause) {
        this.withFromClause = withFromClause;
    }

    @objid ("11090afc-b3ae-436d-8f40-4c29daf4e13f")
    @Override
    public Image getImage(Object obj) {
        final MObject element = (MObject) obj;
        return this.baseLabelProvider.getImage(element);
        //return ElementImageService.getIcon(element);
    }

    @objid ("85f45c86-cba0-482f-a733-f1a25aedfe50")
    @Override
    public StyledString getStyledText(Object element) {
        StyledString mainLabel = this.baseLabelProvider.getStyledText(element);
        if (!this.withFromClause) {
            return mainLabel;
        }
        
        MObject parentEl = (((MObject) element).getCompositionOwner());
        if (parentEl != null) {
            mainLabel.append("  (from ");
            mainLabel.append(this.baseLabelProvider.getStyledText(parentEl));
            mainLabel.append(")");
        }
        return mainLabel;
    }

    @objid ("88cbf065-4df5-4c34-a6a8-802db16452c3")
    @Override
    public String getText(Object element) {
        StringBuilder label = new StringBuilder(this.baseLabelProvider.getText(element));
        if (this.withFromClause) {
            MObject parentEl = ((MObject) element).getCompositionOwner();
            if (parentEl != null) {
                label.append("  (from ");
                label.append(parentEl.getName());
                label.append(")");
            }
        }
        return label.toString();
    }

}
