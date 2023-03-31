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
 * Proxy class to handle a {@link StaticDiagram} with << kpi_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("ca735868-68ed-46f2-b748-17695c64b56f")
public class KpiDiagram {
    @objid ("b4066b3f-89a5-41e3-82ce-7e2e64dfb3c4")
    public static final String STEREOTYPE_NAME = "kpi_diagram";

    /**
     * The underlying {@link StaticDiagram} represented by this proxy, never null.
     */
    @objid ("17ccfd50-ffe4-4821-9fed-100571d0127d")
    protected final StaticDiagram elt;

    /**
     * Tells whether a {@link KpiDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link StaticDiagram} stereotyped << kpi_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("447810cd-c84f-4b26-8814-a6b9035b481e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof StaticDiagram) && ((StaticDiagram) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, KpiDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link StaticDiagram} stereotyped << kpi_diagram >> then instantiate a {@link KpiDiagram} proxy.
     * 
     * @return a {@link KpiDiagram} proxy on the created {@link StaticDiagram}.
     */
    @objid ("02b30ba2-6529-400f-8a2b-5bab54e9d1c6")
    public static KpiDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Standard.StaticDiagram");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, KpiDiagram.STEREOTYPE_NAME);
        return KpiDiagram.instantiate((StaticDiagram)e);
    }

    /**
     * Tries to instantiate a {@link KpiDiagram} proxy from a {@link StaticDiagram} stereotyped << kpi_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a StaticDiagram
     * @return a {@link KpiDiagram} proxy or <i>null</i>.
     */
    @objid ("184c7b76-4113-459e-a8a2-eef0d38ee17b")
    public static KpiDiagram instantiate(StaticDiagram obj) {
        return KpiDiagram.canInstantiate(obj) ? new KpiDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link KpiDiagram} proxy from a {@link StaticDiagram} stereotyped << kpi_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link StaticDiagram}
     * @return a {@link KpiDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("26cc0a1c-56e0-4c41-83c6-f06396054004")
    public static KpiDiagram safeInstantiate(StaticDiagram obj) throws IllegalArgumentException {
        if (KpiDiagram.canInstantiate(obj))
        	return new KpiDiagram(obj);
        else
        	throw new IllegalArgumentException("KpiDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c960aa5a-3a58-4d2c-8dd3-c2073086c930")
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
        KpiDiagram other = (KpiDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link StaticDiagram}. 
     * @return the StaticDiagram represented by this proxy, never null.
     */
    @objid ("ec3249ca-aa98-4ffc-9643-a2313a5b20e0")
    public StaticDiagram getElement() {
        return this.elt;
    }

    @objid ("47be6543-7283-4683-b67a-90c526e42c1a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("1b37c0e8-4851-4a4a-ad54-70c66a3b46f5")
    protected  KpiDiagram(StaticDiagram elt) {
        this.elt = elt;
    }

    @objid ("14383e01-4c20-4ad8-b7c9-1c85c97c4c2e")
    public static final class MdaTypes {
        @objid ("b21c6690-e723-495e-a3b2-97776dc4b32f")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("702014ba-285d-4c4e-aad3-20dbb87ac5f6")
        private static Stereotype MDAASSOCDEP;

        @objid ("2a787493-155d-4771-ad88-c0cd2e480765")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e8a7118e-0762-4b47-bbec-fd5297edee0a")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "19d2deba-a7b7-4818-8d19-aa8b2d63dba1");
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
