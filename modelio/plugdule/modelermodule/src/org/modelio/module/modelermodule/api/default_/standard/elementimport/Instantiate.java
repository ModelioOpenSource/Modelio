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
package org.modelio.module.modelermodule.api.default_.standard.elementimport;

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
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link ElementImport} with << instantiate >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1f52a2d6-89b9-4630-b892-b28a159590b9")
public class Instantiate {
    @objid ("336d33ae-3352-4c1f-891a-beda116dfc05")
    public static final String STEREOTYPE_NAME = "instantiate";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("a356f5f3-f0bb-46fd-bf01-78aa6fb8f0ce")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Instantiate proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << instantiate >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("a3347d93-4de8-4c48-a213-232b6ccb4b5e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Instantiate.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << instantiate >> then instantiate a {@link Instantiate} proxy.
     * 
     * @return a {@link Instantiate} proxy on the created {@link ElementImport}.
     */
    @objid ("57b7608e-edc8-44c6-8d6e-d435495dd717")
    public static Instantiate create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Instantiate.STEREOTYPE_NAME);
        return Instantiate.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Instantiate} proxy from a {@link ElementImport} stereotyped << instantiate >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Instantiate} proxy or <i>null</i>.
     */
    @objid ("cd9e2f55-95e0-4560-9dfb-04fedbaeaf15")
    public static Instantiate instantiate(ElementImport obj) {
        return Instantiate.canInstantiate(obj) ? new Instantiate(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Instantiate} proxy from a {@link ElementImport} stereotyped << instantiate >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Instantiate} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f2c60507-926f-4ab8-b5ef-fb07f3931f1e")
    public static Instantiate safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Instantiate.canInstantiate(obj))
        	return new Instantiate(obj);
        else
        	throw new IllegalArgumentException("Instantiate: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("49764e29-c220-4a30-aa81-bb206fe031de")
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
        Instantiate other = (Instantiate) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("2332d317-1644-423e-8287-51a4140088e2")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("d723eae2-1bd7-41d7-99cd-e52c4b0ca930")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("d672dcd5-fe23-459f-8c22-0c12edbbff1a")
    protected  Instantiate(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("5005c1af-f43a-4d74-9d9a-355357fdf406")
    public static final class MdaTypes {
        @objid ("8d26bfe5-56f7-4816-998d-312fc89c7088")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("82d97440-b7b4-4cbd-ac67-27f33463aa8f")
        private static Stereotype MDAASSOCDEP;

        @objid ("507518ea-126d-41d6-bb99-c2a92daed429")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a389963b-c1a0-4c18-93e0-2c37b8d1b7b8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01e5-0000-000000000000");
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
