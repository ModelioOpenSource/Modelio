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
 * Proxy class to handle a {@link ElementImport} with << import >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("450c1690-12d6-42fc-958f-edcb65fd95bc")
public class Import {
    @objid ("069482cc-90fc-4ee8-9954-c4ef7faa0914")
    public static final String STEREOTYPE_NAME = "import";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("b3d52012-d11a-4b8f-ada2-8e1fc174649b")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Import proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << import >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("14ef3927-ccdc-4580-9dfe-d7128d3bf494")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Import.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << import >> then instantiate a {@link Import} proxy.
     * 
     * @return a {@link Import} proxy on the created {@link ElementImport}.
     */
    @objid ("ccb9cd79-fff0-4848-989a-0011679e4eb6")
    public static Import create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Import.STEREOTYPE_NAME);
        return Import.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Import} proxy from a {@link ElementImport} stereotyped << import >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Import} proxy or <i>null</i>.
     */
    @objid ("953f68ec-10a8-42fe-8cc9-fde59e06e81e")
    public static Import instantiate(ElementImport obj) {
        return Import.canInstantiate(obj) ? new Import(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Import} proxy from a {@link ElementImport} stereotyped << import >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Import} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("61ee6279-6874-4d32-8a3d-ea2412951d51")
    public static Import safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Import.canInstantiate(obj))
        	return new Import(obj);
        else
        	throw new IllegalArgumentException("Import: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("610563dd-62d7-4799-ade9-a16e27e646bc")
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
        Import other = (Import) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("24b5bf40-4921-4b64-8754-969cf4ff9cf0")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("c844a61d-841f-4905-bb7d-80f0f17e00f5")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("00678cc7-de81-4e59-b9c7-7407f8051494")
    protected  Import(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("abc33775-3a58-4320-bd1b-1809fc8c7925")
    public static final class MdaTypes {
        @objid ("2189f9f8-b56b-408c-a21a-c3e912579712")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e18773c9-529b-48be-9955-487922b1a627")
        private static Stereotype MDAASSOCDEP;

        @objid ("0f6027cb-a41b-445c-a97e-fbd5a0c89b41")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c9a90bb9-d10f-4b88-99b6-e5c8b4fdfa06")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01ce-0000-000000000000");
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
