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
package org.modelio.module.modelermodule.api.default_.standard.package_;

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
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Package} with << facade >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("de0809b5-09b8-4993-822d-cd90c93c207d")
public class Facade {
    @objid ("d9246ce8-2cdc-4342-9abf-b1867e3cfa3f")
    public static final String STEREOTYPE_NAME = "facade";

    /**
     * The underlying {@link Package} represented by this proxy, never null.
     */
    @objid ("9098038a-047c-4b3d-bc02-825686aec054")
    protected final Package elt;

    /**
     * Tells whether a {@link Facade proxy} can be instantiated from a {@link MObject} checking it is a {@link Package} stereotyped << facade >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("68ae476e-c20f-435e-88c3-e5fe521dbd70")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Package) && ((Package) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Facade.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Package} stereotyped << facade >> then instantiate a {@link Facade} proxy.
     * 
     * @return a {@link Facade} proxy on the created {@link Package}.
     */
    @objid ("ed75bb1b-aab2-4e95-9c58-c04107dde193")
    public static Facade create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.Package");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Facade.STEREOTYPE_NAME);
        return Facade.instantiate((Package)e);
    }

    /**
     * Tries to instantiate a {@link Facade} proxy from a {@link Package} stereotyped << facade >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Package
     * @return a {@link Facade} proxy or <i>null</i>.
     */
    @objid ("53e95ba2-6780-49c0-b86a-0cac61f2f823")
    public static Facade instantiate(Package obj) {
        return Facade.canInstantiate(obj) ? new Facade(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Facade} proxy from a {@link Package} stereotyped << facade >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Package}
     * @return a {@link Facade} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("909b1535-e6fc-4fe7-b08f-4545934744a4")
    public static Facade safeInstantiate(Package obj) throws IllegalArgumentException {
        if (Facade.canInstantiate(obj))
        	return new Facade(obj);
        else
        	throw new IllegalArgumentException("Facade: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("3b3de6eb-618a-4efd-9c5d-148b916ac2d0")
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
        Facade other = (Facade) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Package}. 
     * @return the Package represented by this proxy, never null.
     */
    @objid ("519219bf-daaf-41cd-a75b-4be553e744e6")
    public Package getElement() {
        return this.elt;
    }

    @objid ("fe155da6-471a-4677-a050-f5659bf6b41f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("e2698a3c-2195-4def-8cf5-d111bf545af6")
    protected  Facade(Package elt) {
        this.elt = elt;
    }

    @objid ("5a57592c-acc8-457a-899c-a8d0cc2b60df")
    public static final class MdaTypes {
        @objid ("c21cffd5-cd38-4c87-8025-495a6972ca7a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("28eb5256-3236-460c-a774-fb1ca6873be3")
        private static Stereotype MDAASSOCDEP;

        @objid ("c5cf34c6-55b6-4d04-baac-802187337bb4")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9c701e4d-45e7-469d-9b23-1275675b28cf")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01d5-0000-000000000000");
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
