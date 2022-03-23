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
package org.modelio.script.macro.catalogdialog;

import java.util.ArrayList;
import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.modelio.script.macro.catalog.Catalog;
import org.modelio.script.macro.catalog.Macro;

@objid ("006965e0-c497-106a-bf4f-001ec947cd2a")
class CatalogContentProvider implements ITreeContentProvider {
    @objid ("0082a1f4-ca64-106a-bf4f-001ec947cd2a")
    private final ArrayList<Catalog> catalogs = new ArrayList<>();

    @objid ("006a0fd6-c497-106a-bf4f-001ec947cd2a")
    @Override
    public void dispose() {
        this.catalogs.clear();
    }

    @objid ("0069b5b8-c497-106a-bf4f-001ec947cd2a")
    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        // Empty
    }

    @objid ("0069b64e-c497-106a-bf4f-001ec947cd2a")
    @Override
    public Object[] getElements(Object inputElement) {
        return this.catalogs.toArray();
    }

    @objid ("0069b6e4-c497-106a-bf4f-001ec947cd2a")
    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof Catalog) {
            Catalog c = (Catalog) parentElement;
            return c.getMacros().toArray();
        }
        return new Object[0];
    }

    @objid ("0069b810-c497-106a-bf4f-001ec947cd2a")
    @Override
    public Object getParent(Object element) {
        if (element instanceof Catalog) {
            return null;
        }
        if (element instanceof Macro) {
            return ((Macro) element).getCatalog();
        } else {
            return null;
        }
        
    }

    @objid ("0069b77a-c497-106a-bf4f-001ec947cd2a")
    @Override
    public boolean hasChildren(Object element) {
        if (element instanceof Catalog) {
            return !((Catalog) element).getMacros().isEmpty();
        }
        if (element instanceof Macro) {
            return false;
        } else {
            return false;
        }
        
    }

    @objid ("00697a62-c497-106a-bf4f-001ec947cd2a")
    public  CatalogContentProvider() {
        
    }

    @objid ("006a1062-c497-106a-bf4f-001ec947cd2a")
    public void save() {
        // this.provider.save();
        //
        // // Notify Catalogs observers
        // this.provider.getObservable().notifyObservers(this.provider);
        
    }

    @objid ("006a10f8-c497-106a-bf4f-001ec947cd2a")
    public void reload() {
        // this.provider.refresh();
        //
        // // Notify Catalogs observers
        // this.provider.getObservable().notifyObservers(this.provider);
        
    }

    @objid ("0083ccf0-ca64-106a-bf4f-001ec947cd2a")
    public void addCatalog(Catalog catalog) {
        this.catalogs.add(catalog);
    }

    @objid ("003dca2a-eada-106a-bf4f-001ec947cd2a")
    public Collection<Catalog> getCatalogs() {
        return this.catalogs;
    }

}
