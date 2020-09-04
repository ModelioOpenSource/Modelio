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

package org.modelio.patterns.properties;

import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.IModule;
import org.modelio.api.module.propertiesPage.AbstractModulePropertyPage;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerStereotypes;
import org.modelio.patterns.model.ProfileUtils;
import org.modelio.vcore.smkernel.mapi.MObject;

@objid ("51548822-22c4-40ee-935c-2240ef5fd947")
public class PatternPropertyPage extends AbstractModulePropertyPage {
    @objid ("cc9eca8a-b143-42b6-88f8-ba18e9fea600")
    public PatternPropertyPage(IModule module, String name, String label, String bitmap) {
        super(module, name, label, bitmap);
    }

    /**
     * This method is called when a value has been editd in the property box in
     * the 'row' row. The 'selectedElements' parameter contains the list of the
     * currently selected elements. The 'row' parameter is the row number of the
     * modified value. The 'value' parameter is the new value the user has set
     * to the given row.
     */
    @objid ("96c16209-7bae-43c9-ac38-46c30253d1a6")
    @Override
    public void changeProperty(List<MObject> selectedElements, int row, String value) {
        if (!selectedElements.isEmpty() && selectedElements.get(0) instanceof ModelElement) {
            ModelElement element = (ModelElement) selectedElements.get(0);
            getContent(element).changeProperty(element, row, value);
        }
    }

    /**
     * This method is called when the current selection changes and the property
     * box content requires an update. The 'selectedElements' parameter contains
     * the list of the newly selected elements. The 'table' parameter is the
     * table that must be filled with the updated contents of the property box
     * before returning.
     */
    @objid ("fe0c0388-0d7d-4002-ac56-482724451aeb")
    @Override
    public void update(List<MObject> selectedElements, IModulePropertyTable table) {
        if (!selectedElements.isEmpty() && selectedElements.get(0) instanceof ModelElement) {
            ModelElement element = (ModelElement) selectedElements.get(0);
            getContent(element).update(element, table);
        }
    }

    @objid ("32ccb059-1e69-4241-b0d6-5940c1eaf04a")
    private IPropertyContent getContent(ModelElement element) {
        if (element.isStereotyped(ProfileUtils.MODULE_NAME,PatternDesignerStereotypes.PATTERN)) {
            return new PatternProperty();
        } else if (element.isStereotyped(ProfileUtils.MODULE_NAME,PatternDesignerStereotypes.PATTERNPARAMETER)) {
            return new PatternParameterProperty();
        } else if (element.isStereotyped(ProfileUtils.MODULE_NAME,PatternDesignerStereotypes.PATTERNROOT)) {
            return new PatternRootProperty();
        }
        return new DefaultProperty();
    }

}
