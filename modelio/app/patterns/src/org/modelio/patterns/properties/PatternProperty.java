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

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.module.propertiesPage.IModulePropertyTable;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.patterns.model.ProfileUtils.PatternDesignerTagTypes;
import org.modelio.patterns.model.ProfileUtils;
import org.modelio.patterns.plugin.Patterns;

@objid ("eb6d0df5-85ac-4b35-b81c-7d0dd5ce45e0")
public class PatternProperty implements IPropertyContent {
    @objid ("2a4e8903-6edb-41d9-be38-a78be2ce40db")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        try {
            if (row == 1) {
                element.setName(value);
            } else if (row == 2) {
                List<String> param = new ArrayList<>();
                param.add(value);
                element.putTagValues(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERN_TEMPLATE_VERSION, param);
            } else if (row == 3) {
                List<String> param = new ArrayList<>();
                param.add(value);
                element.putTagValues(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERN_TEMPLATE_IMAGE, param);
            }
        } catch (Exception e) {
            Patterns.LOG.debug(e);
        }
    }

    @objid ("51ee05f9-7467-4ca8-bdd8-bc56e076896c")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        table.addProperty(Patterns.I18N.getString("PropertyDefinition.Name"), element.getName());
        
        String version = element.getTagValue(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERN_TEMPLATE_VERSION);
        if (version == null || version.equals("")) {
            version = "1.0.00";
        }
        table.addProperty(Patterns.I18N.getString("PropertyDefinition.Version"), version);
        
        String image = element.getTagValue(ProfileUtils.MODULE_NAME, PatternDesignerTagTypes.PATTERN_TEMPLATE_IMAGE);
        if (image == null) {
            image = "";
        }
        table.addProperty(Patterns.I18N.getString("PropertyDefinition.Image"), image);
    }

}
