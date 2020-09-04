/* 
 * Copyright 2013-2019 Modeliosoft
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
package org.modelio.module.modelermodule.api.analyst.standard.staticdiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.diagrams.StaticDiagram;
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
 * Proxy class to handle a {@link StaticDiagram} with << risk_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("7610605a-be93-4205-972e-98cbcf23a5a6")
public class RiskDiagram {
    @objid ("bddb6c5c-9a44-4b49-92c4-774d15cf7613")
    public static final String STEREOTYPE_NAME = "risk_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("7e549469-3df1-46c5-945e-afd2fead8015")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link RiskDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << risk_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1e2e5cfd-bacf-446e-a1d1-91a3cbdb3466")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RiskDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << risk_diagram >> then instantiate a {@link RiskDiagram} proxy.
     * 
     * @return a {@link RiskDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("aea6f8ec-0517-4314-b8fa-fd9717ede683")
    public static RiskDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RiskDiagram.STEREOTYPE_NAME);
        return RiskDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link RiskDiagram} proxy from a {@link StaticDiagram} stereotyped << risk_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link RiskDiagram} proxy or <i>null</i>.
     */
    @objid ("7bede2a3-38e2-48eb-b6f6-ccdbfeeeb685")
    public static RiskDiagram instantiate(StaticDiagram obj) {
        return RiskDiagram.canInstantiate(obj) ? new RiskDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RiskDiagram} proxy from a {@link StaticDiagram} stereotyped << risk_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link RiskDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("85e78e8b-42e9-42dc-a9f4-094b811c7c08")
    public static RiskDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (RiskDiagram.canInstantiate(obj))
        	return new RiskDiagram(obj);
        else
        	throw new IllegalArgumentException("RiskDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5365aad5-8edd-4bf2-ab11-89e358acdb21")
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
        RiskDiagram other = (RiskDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("cfc07766-5e19-401e-8a5c-1d9a91beef8f")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("55ebdad4-fa3f-4659-afb7-cb2c52beb1e0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("888860fd-5584-485f-ab33-e79573b853b6")
    protected RiskDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("1d8c991a-287c-416d-a3d1-46202e80f109")
    public static final class MdaTypes {
        @objid ("411000ee-e8aa-467f-a9c5-5cf1b0e92058")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("860b6051-dc12-49f5-afd1-ab8bcd52d271")
        private static Stereotype MDAASSOCDEP;

        @objid ("83a92c11-4bec-4bd0-9c9e-9b7d8058d1f8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2b48dd52-f916-427e-a659-842b9aae3e92")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "61222898-ff82-4681-a82f-a8f14c479dd1");
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
