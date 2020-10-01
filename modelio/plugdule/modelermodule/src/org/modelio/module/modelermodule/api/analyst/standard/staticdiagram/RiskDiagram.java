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
    @objid ("ee7e1f38-7610-4efc-b843-396adf99c18d")
    public static final String STEREOTYPE_NAME = "risk_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("3c8e1286-c247-41f9-a193-7003c8ae3f8d")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link RiskDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << risk_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("9fdb69eb-2321-417d-8711-5b447c8f34f6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RiskDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << risk_diagram >> then instantiate a {@link RiskDiagram} proxy.
     * 
     * @return a {@link RiskDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("a763622f-c403-4e13-babb-0b3c60bbbf6e")
    public static RiskDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RiskDiagram.STEREOTYPE_NAME);
        return RiskDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link RiskDiagram} proxy from a {@link StaticDiagram} stereotyped << risk_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link RiskDiagram} proxy or <i>null</i>.
     */
    @objid ("98586621-28a3-46aa-bea4-c460efe548e5")
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
    @objid ("22e1caf2-51fe-422e-b8ac-fa471342131d")
    public static RiskDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (RiskDiagram.canInstantiate(obj))
        	return new RiskDiagram(obj);
        else
        	throw new IllegalArgumentException("RiskDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2c75044b-d49d-464f-8a29-847bcea4d278")
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
    @objid ("f5e38fcf-4e3b-4d0b-a045-b3e84b6e2530")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("54bef9d1-c6f2-42ac-8845-5f0ed4fdac8a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f0dc35d3-7d5f-48ef-bdbc-eddcaff1f51d")
    protected RiskDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("1d8c991a-287c-416d-a3d1-46202e80f109")
    public static final class MdaTypes {
        @objid ("58ae0098-8300-41ce-ac16-d92bd22e7093")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2a32c1d4-3fc5-439c-bec8-21536cb07336")
        private static Stereotype MDAASSOCDEP;

        @objid ("0f0780b1-7b1e-46fd-85db-6be8b053ca62")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("32a13a5d-50b6-4e82-b6f9-4230fd72e975")
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
