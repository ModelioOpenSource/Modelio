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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
 * Proxy class to handle a {@link Class} with << design pattern >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0485dbda-681e-4361-bcdb-4b0adb5be546")
public class DesignPattern {
    @objid ("7b6dab07-5078-430b-9a6d-2a10000fe4d3")
    public static final String STEREOTYPE_NAME = "design pattern";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("6be9f82f-2400-41a4-880d-7ae04f81df84")
    protected final Class elt;

    /**
     * Tells whether a {@link DesignPattern proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << design pattern >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("2a53d7f6-24d2-4ddd-9909-33b934d33f54")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, DesignPattern.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << design pattern >> then instantiate a {@link DesignPattern} proxy.
     * 
     * @return a {@link DesignPattern} proxy on the created {@link Class}.
     */
    @objid ("a9d681b2-a4ad-4cb5-aa42-3f5b96bc83e1")
    public static DesignPattern create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, DesignPattern.STEREOTYPE_NAME);
        return DesignPattern.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link DesignPattern} proxy from a {@link Class} stereotyped << design pattern >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link DesignPattern} proxy or <i>null</i>.
     */
    @objid ("b8edb5ed-203f-4ae2-859d-f283bdd103ef")
    public static DesignPattern instantiate(Class obj) {
        return DesignPattern.canInstantiate(obj) ? new DesignPattern(obj) : null;
    }

    /**
     * Tries to instantiate a {@link DesignPattern} proxy from a {@link Class} stereotyped << design pattern >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link DesignPattern} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6f83ffd8-4c39-441d-83e8-5aa96b83a9f4")
    public static DesignPattern safeInstantiate(Class obj) throws IllegalArgumentException {
        if (DesignPattern.canInstantiate(obj))
        	return new DesignPattern(obj);
        else
        	throw new IllegalArgumentException("DesignPattern: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5fe4e59b-3467-4b03-b999-212241b8d025")
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
        DesignPattern other = (DesignPattern) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}. 
     * @return the Class represented by this proxy, never null.
     */
    @objid ("db2bfc62-311f-4239-859e-6bfac6a1f721")
    public Class getElement() {
        return this.elt;
    }

    @objid ("b121b16d-d0ea-438b-883d-0adbb7a35815")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("eb40e03a-86c2-4773-b011-3283113ec21e")
    protected DesignPattern(Class elt) {
        this.elt = elt;
    }

    @objid ("c58585f8-0f0c-4e0b-82c0-d99f524b2dfc")
    public static final class MdaTypes {
        @objid ("b5f036f1-205c-4b82-989e-e796836a2065")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("0509a06d-83cf-40c8-ab2e-f354e01edf39")
        private static Stereotype MDAASSOCDEP;

        @objid ("4873bbc7-0d8e-428a-9320-a5c115a87e88")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("131c418c-5fba-43bc-b73a-f39cbb68c272")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "007006dc-0000-0139-0000-000000000000");
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
