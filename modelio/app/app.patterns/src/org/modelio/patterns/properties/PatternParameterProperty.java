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
package org.modelio.patterns.properties;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.patterns.model.ProfileUtils;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerStereotypes;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerTagTypes;
import org.modelio.patterns.plugin.Patterns;

@objid ("36879355-24bb-4646-8a86-64dcc3b7152f")
public class PatternParameterProperty implements IPropertyContent {
    @objid ("a09bca0b-9d14-4075-8e12-f8acb5ff17d2")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        try {
            if (row == 1) {
                element.removeStereotypes(ProfileUtils.MODULE_NAME, PatternDesignerStereotypes.PATTERNPARAMETER);
            } else if (row == 2) {
                List<String> param = new ArrayList<>();
                param.add(value);
                element.putTagValues(ProfileUtils.MODULE_NAME,PatternDesignerTagTypes.PATTERNPARAMETER_PATTERNPARAMETER_NAME, param);
            } else if (row == 3) {
                List<String> param = new ArrayList<>();
                param.add(value);
                element.putTagValues(ProfileUtils.MODULE_NAME,PatternDesignerTagTypes.PATTERNPARAMETER_PATTERNPARAMETER_LABEL, param);
            }
        } catch (Exception e) {
            Patterns.LOG.debug(e);
        }
        
    }

    @objid ("6f0693b6-632a-4e40-bb9d-e7e5508520d9")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        table.addProperty(Patterns.I18N.getString("PropertyDefinition.IsPatternParameter"), element.isStereotyped(ProfileUtils.MODULE_NAME,PatternDesignerStereotypes.PATTERNPARAMETER));
        
        String name = element.getTagValue(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERNPARAMETER_PATTERNPARAMETER_NAME);
        if (name == null || name.equals("")) {
            name = element.getName();
        }
        table.addProperty(Patterns.I18N.getString("PropertyDefinition.Name"), name);
        
        String label = element.getTagValue(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERNPARAMETER_PATTERNPARAMETER_LABEL);
        if (label == null || label.equals("")) {
            label = element.getName();
        }
        
        table.addProperty(Patterns.I18N.getString("PropertyDefinition.Label"), label);
        
    }

}
