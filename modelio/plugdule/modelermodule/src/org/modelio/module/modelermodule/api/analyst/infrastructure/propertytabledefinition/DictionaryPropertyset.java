/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.infrastructure.propertytabledefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link PropertyTableDefinition} with << dictionary_propertyset >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("a701ba85-699e-41ed-a46b-eb90d18ebaa6")
public class DictionaryPropertyset {
    @objid ("61106dca-441b-4c0d-940f-6412b6b9fb17")
    public static final String STEREOTYPE_NAME = "dictionary_propertyset";

    /**
     * The underlying {@link PropertyTableDefinition} represented by this proxy, never null.
     */
    @objid ("4ae5abeb-1253-425e-8755-5029272b1085")
    protected final PropertyTableDefinition elt;

    /**
     * Tells whether a {@link DictionaryPropertyset proxy} can be instantiated from a {@link MObject} checking it is a {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("655841ef-4ced-48de-a7e7-012ea3bf9828")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof PropertyTableDefinition) && ((PropertyTableDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, DictionaryPropertyset.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >> then instantiate a {@link DictionaryPropertyset} proxy.
     * 
     * @return a {@link DictionaryPropertyset} proxy on the created {@link PropertyTableDefinition}.
     */
    @objid ("3280fc6a-43c3-4993-a814-7817719a6437")
    public static DictionaryPropertyset create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.PropertyTableDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, DictionaryPropertyset.STEREOTYPE_NAME);
        return DictionaryPropertyset.instantiate((PropertyTableDefinition)e);
    }

    /**
     * Tries to instantiate a {@link DictionaryPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a PropertyTableDefinition
     * @return a {@link DictionaryPropertyset} proxy or <i>null</i>.
     */
    @objid ("35298f1f-2321-47c4-b306-a3c7b1bc4a62")
    public static DictionaryPropertyset instantiate(PropertyTableDefinition obj) {
        return DictionaryPropertyset.canInstantiate(obj) ? new DictionaryPropertyset(obj) : null;
    }

    /**
     * Tries to instantiate a {@link DictionaryPropertyset} proxy from a {@link PropertyTableDefinition} stereotyped << dictionary_propertyset >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link PropertyTableDefinition}
     * @return a {@link DictionaryPropertyset} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("4d8b4b2b-1665-4dc0-a643-bc7b0cea441b")
    public static DictionaryPropertyset safeInstantiate(PropertyTableDefinition obj) throws IllegalArgumentException {
        if (DictionaryPropertyset.canInstantiate(obj))
        	return new DictionaryPropertyset(obj);
        else
        	throw new IllegalArgumentException("DictionaryPropertyset: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3ed9f2bc-7d63-49d4-a695-579314a11897")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        DictionaryPropertyset other = (DictionaryPropertyset) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link PropertyTableDefinition}. 
     * @return the PropertyTableDefinition represented by this proxy, never null.
     */
    @objid ("a8d65aaf-c63f-4c09-a647-0adb5270a003")
    public PropertyTableDefinition getElement() {
        return this.elt;
    }

    @objid ("ff149719-7422-4227-afa0-6e573b1d26be")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("85c9e8c8-0149-4b3c-a26f-bb392428024e")
    protected  DictionaryPropertyset(PropertyTableDefinition elt) {
        this.elt = elt;
    }

    @objid ("d95109a9-38e2-4ad3-8d22-511e555e6dcc")
    public static final class MdaTypes {
        @objid ("bdb96e63-0c52-4a17-8628-041c4eff311f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ca24211d-a2c7-402f-8d76-f40276ef9abd")
        private static Stereotype MDAASSOCDEP;

        @objid ("c99ac13b-6f05-4a3e-b3d0-0b4bcd378165")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0d895bf8-cfda-4cdc-9fb0-e695d24ef4ca")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec141c-0000-12fc-0000-000000000000");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
            
        }

	static {
        		if(ModelerModuleModule.getInstance() != null) {
        			init(ModelerModuleModule.getInstance().getModuleContext());
        		}
        	}
        
    }

}
