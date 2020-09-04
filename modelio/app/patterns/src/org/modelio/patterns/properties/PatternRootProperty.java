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

package org.modelio.patterns.properties;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerStereotypes;
import org.modelio.patterns.model.ProfileUtils;
import org.modelio.patterns.plugin.Patterns;

@objid ("e8144402-5ba0-4f78-bdba-1daa5c8858f9")
public class PatternRootProperty implements IPropertyContent {
    @objid ("e472bc6f-d00c-45c8-9f36-78a5f048f13c")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        try {
            if (row == 1) {
                element.removeStereotypes(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERNROOT);
            } else if (row == 2) {
                element.setName(value);
            }
        } catch (Exception e) {
            Patterns.LOG.debug(e);
        }
    }

    @objid ("4043c58a-68a6-4cb8-8261-4dce72b72ec0")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        table.addProperty(Patterns.I18N.getString("PropertyDefinition.IsPatternRoot"), element.isStereotyped(ProfileUtils.MODULE_NAME,PatternDesignerStereotypes.PATTERNROOT));
        table.addProperty(Patterns.I18N.getString("PropertyDefinition.Name"), element.getName());
    }

}
