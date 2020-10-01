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
import org.modelio.patterns.model.ProfileUtils.PatternDesignerStereotypes;
import org.modelio.patterns.model.ProfileUtils;
import org.modelio.patterns.plugin.Patterns;

@objid ("12788111-9d33-4214-882a-dbb80de31b14")
public class DefaultProperty implements IPropertyContent {
    @objid ("bc55c339-3c74-4a98-a18a-dfaf60f1880a")
    @Override
    public void changeProperty(ModelElement element, int row, String value) {
        if (row == 1) {
            try {
                element.addStereotype(ProfileUtils.MODULE_NAME,PatternDesignerStereotypes.PATTERNPARAMETER);
            } catch (Exception e) {
                Patterns.LOG.debug(e);
            }
        } else if (row == 2) {
            setRootStereotype(element);
        }
    }

    @objid ("90668832-8b84-4305-ad1e-cd49c3e9feb0")
    @Override
    public void update(ModelElement element, IModulePropertyTable table) {
        table.addProperty(Patterns.I18N.getString("PropertyDefinition.IsPatternParameter"), element.isStereotyped(ProfileUtils.MODULE_NAME,PatternDesignerStereotypes.PATTERNPARAMETER));
        table.addProperty(Patterns.I18N.getString("PropertyDefinition.IsPatternRoot"), element.isStereotyped(ProfileUtils.MODULE_NAME,PatternDesignerStereotypes.PATTERNROOT));
    }

    @objid ("538f1ad3-8b62-4670-83bd-9a579e86574a")
    private void setRootStereotype(ModelElement element) {
        try {
            List<ModelElement> roots = new ArrayList<>();
            roots.add(element);
        
            ModelElement owner = (ModelElement) element.getCompositionOwner();
            while (owner != null && !owner.isStereotyped(ProfileUtils.MODULE_NAME,PatternDesignerStereotypes.PATTERN)) {
                roots.add(owner);
                owner = (ModelElement) owner.getCompositionOwner();
            }
        
            if (owner != null) {
                for (ModelElement sub : roots) {
                    sub.addStereotype(ProfileUtils.MODULE_NAME,PatternDesignerStereotypes.PATTERNROOT);
                }
            }
        } catch (Exception e) {
            Patterns.LOG.debug(e);
        }
    }

}
