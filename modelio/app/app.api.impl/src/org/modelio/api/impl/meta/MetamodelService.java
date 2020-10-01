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

package org.modelio.api.impl.meta;

import java.util.ArrayList;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.meta.IMetamodelI18nSupport;
import org.modelio.api.modelio.meta.IMetamodelService;
import org.modelio.platform.model.ui.MetamodelLabels;
import org.modelio.vcore.smkernel.mapi.MAttribute;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Implementation of {@link IMetamodelService} delegating its work to an {@link MMetamodel} instance.
 */
@objid ("bd05b9fd-c038-4e22-bc58-45428ddd2ccf")
public class MetamodelService implements IMetamodelService {
    @objid ("d23ec973-aba2-4d21-9cef-02549dc42b38")
    private MMetamodel metamodel;

    /**
     * Implementation using {@link MetamodelLabels} for translations.
     */
    @objid ("90e1bcd7-209a-4a88-b785-f710ad2d7e23")
    private static final IMetamodelI18nSupport I18N_SUPPORT = new IMetamodelI18nSupport() {
        @Override
        public String getLabel(MAttribute mAtt) {
            return MetamodelLabels.getString(mAtt.getName());
        }
        @Override
        public String getLabel(MDependency mDep) {
            return MetamodelLabels.getString(mDep.getName());
        }
        @Override
        public String getLabel(MClass mClass) {
            return MetamodelLabels.getString(mClass.getName());
        }
    };

    /**
     * C'tor.
     * 
     * @param metamodel the current metamodel.
     */
    @objid ("22dc64bb-736b-4647-aba2-ebcfbe9a86d4")
    public MetamodelService(MMetamodel metamodel) {
        this.metamodel = metamodel;
    }

    /**
     * Returns the textual name of a metaclass.<br>
     * <em>Note: The returned name is <u>NOT</u> i18n'd.</em>
     * 
     * @param metaclassName the metaclass whose name is sought.
     * @return the textual name of the metaclass. Might be <code>null</code> if no metaclass matches.
     */
    @objid ("f86306c6-33de-466b-9bd8-6854f3af3a54")
    @Override
    public Class<? extends MObject> getMetaclass(final String metaclassName) {
        MClass metaclass = this.metamodel.getMClass(metaclassName);
        if (metaclass != null) {
            return metaclass.getJavaInterface();
        }
        return null;
    }

    /**
     * Returns the textual name of a metaclass.<br>
     * <em>Note: The returned name is <u>NOT</u> i18n'd.</em>
     * 
     * @param metaclass the metaclass whose name is sought, or <code>null</code> if the given class is not a metaclass.
     * @return the textual name of the metaclass.
     */
    @objid ("7becb93f-7a41-4d9b-bb38-c734451a41d5")
    @Override
    public String getMetaclassName(final Class<? extends MObject> metaclass) {
        return this.metamodel.getMClass(metaclass).getName();
    }

    /**
     * Get the metaclasses that inherit from the given metaclass.
     * <p>
     * The given metaclass will in the result list.
     * 
     * @param javaMetaclass The parent metaclass of the wanted metaclasses.
     * @return A list of metaclasses that inherit from the given metaclass.
     */
    @objid ("7ea438fc-d17d-411d-959d-103735c97d3e")
    @Override
    public List<Class<? extends MObject>> getInheritingMetaclasses(final Class<? extends MObject> javaMetaclass) {
        List<Class<? extends MObject>> ret = new ArrayList<>();
        
        MClass metaclass = this.metamodel.getMClass(javaMetaclass);
        if (metaclass != null) {
            for (MClass childMClass : metaclass.getSub(true)) {
                ret.add(childMClass.getJavaInterface());
            }
        }
        return ret;
    }

    @objid ("a610bded-6d22-4865-b11d-2a7789ce505c")
    @Override
    public MMetamodel getMetamodel() {
        return this.metamodel;
    }

    @objid ("96841292-89f8-493e-8099-ed872ade253b")
    @Override
    public IMetamodelI18nSupport getI18nSupport() {
        return I18N_SUPPORT;
    }

}
