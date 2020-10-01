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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.class_;

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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Class} with << implementationClass >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9a8d3476-0e47-4713-8073-82df91f999f5")
public class ImplementationClass {
    @objid ("62e1289b-3c5f-44ba-b5a5-24a90eed4fb3")
    public static final String STEREOTYPE_NAME = "implementationClass";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("5d033718-9332-451c-9e8b-2e7bdcfae579")
    protected final Class elt;

    /**
     * Tells whether a {@link ImplementationClass proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << implementationClass >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("3406b98e-d882-4888-af62-5ec60bbd692f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImplementationClass.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << implementationClass >> then instantiate a {@link ImplementationClass} proxy.
     * 
     * @return a {@link ImplementationClass} proxy on the created {@link Class}.
     */
    @objid ("812a09f1-1dbb-44ff-a93f-0ca866f052d5")
    public static ImplementationClass create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImplementationClass.STEREOTYPE_NAME);
        return ImplementationClass.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link ImplementationClass} proxy from a {@link Class} stereotyped << implementationClass >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link ImplementationClass} proxy or <i>null</i>.
     */
    @objid ("6c7e946f-8153-40d0-8ac0-0aed1ea66658")
    public static ImplementationClass instantiate(Class obj) {
        return ImplementationClass.canInstantiate(obj) ? new ImplementationClass(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImplementationClass} proxy from a {@link Class} stereotyped << implementationClass >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link ImplementationClass} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("42d4dbb1-dfb2-426c-afb4-0431efbccc71")
    public static ImplementationClass safeInstantiate(Class obj) throws IllegalArgumentException {
        if (ImplementationClass.canInstantiate(obj))
        	return new ImplementationClass(obj);
        else
        	throw new IllegalArgumentException("ImplementationClass: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("6ff73129-a0e7-4db0-8a36-2cc278e4115d")
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
        ImplementationClass other = (ImplementationClass) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}. 
     * @return the Class represented by this proxy, never null.
     */
    @objid ("58e3d204-9eff-41a5-968d-84f23b81b90f")
    public Class getElement() {
        return this.elt;
    }

    @objid ("c92cbcec-53a4-4f2f-9210-1fb78f9fd1ef")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5b257d67-a353-4459-ae1f-c8d691318568")
    protected ImplementationClass(Class elt) {
        this.elt = elt;
    }

    @objid ("1b7811d3-b98b-4855-a8c5-b056228fbf8f")
    public static final class MdaTypes {
        @objid ("88c8a1ab-2078-4216-b183-b3782cc0657a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b1e238cb-3cff-4cfd-a1aa-944832de6e3d")
        private static Stereotype MDAASSOCDEP;

        @objid ("2581f69b-a70e-4834-bc90-3483225fc722")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c9c4756f-6077-491d-91c7-b5e281e20b1d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00bc0050-0000-006b-0000-000000000000");
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
