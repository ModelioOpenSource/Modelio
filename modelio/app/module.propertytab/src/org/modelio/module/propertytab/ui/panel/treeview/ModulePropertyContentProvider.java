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

package org.modelio.module.propertytab.ui.panel.treeview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.modelio.module.propertytab.model.ModuleProperty;
import org.modelio.module.propertytab.model.ModulePropertyModel;

@objid ("c8996411-1eba-11e2-9382-bc305ba4815c")
public class ModulePropertyContentProvider implements ITreeContentProvider {
    @objid ("c899b231-1eba-11e2-9382-bc305ba4815c")
    @Override
    public Object[] getElements(Object inputElement) {
        List<Object> result = new ArrayList<>();
        HashMap<String, Category> categories =  new HashMap<>();
        
        ModulePropertyModel model = (ModulePropertyModel) inputElement;
        
        for (ModuleProperty property : model.getProperties()) {
            String propCategory = property.getCategory();
            if (propCategory != null) {
                Category cat = categories.get(propCategory);
                if (cat == null) {
                    cat = new Category();
                    cat.label = property.getCategoryLabel();
                    categories.put(propCategory, cat);
                    result.add(cat);
                }
        
                cat.childrens.add(property);
                
            } else {
                result.add(property);
            }
        }
        return result.toArray();
    }

    @objid ("c899d942-1eba-11e2-9382-bc305ba4815c")
    @Override
    public Object[] getChildren(Object parentElement) {
        if(parentElement instanceof Category){
            return ((Category)parentElement).childrens.toArray();
        }
        return null;
    }

    @objid ("c89a0055-1eba-11e2-9382-bc305ba4815c")
    @Override
    public Object getParent(Object element) {
        return null;
    }

    @objid ("c89a2764-1eba-11e2-9382-bc305ba4815c")
    @Override
    public boolean hasChildren(Object element) {
        if(element instanceof Category){
            return ((Category)element).childrens.size() > 0 ;
        }
        return false;
    }

    @objid ("c89a4e74-1eba-11e2-9382-bc305ba4815c")
    public static class Category {
        @objid ("045857e3-1ebb-11e2-9382-bc305ba4815c")
        public String label;

        @objid ("c89a7580-1eba-11e2-9382-bc305ba4815c")
        public final List<ModuleProperty> childrens = new ArrayList<>();

    }

}
