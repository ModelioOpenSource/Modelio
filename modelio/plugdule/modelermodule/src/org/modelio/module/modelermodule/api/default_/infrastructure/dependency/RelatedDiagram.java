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
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << related_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fca5b424-314d-4acd-b390-d416296df62e")
public class RelatedDiagram {
    @objid ("37c5708f-1837-4cde-920a-b369decbc831")
    public static final String STEREOTYPE_NAME = "related_diagram";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("011dd9f1-c08a-4096-b1d3-330f3b92cf56")
    protected final Dependency elt;

    /**
     * Tells whether a {@link RelatedDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << related_diagram >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("46ff347f-e87c-4b5b-af01-61ee7fd01845")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RelatedDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << related_diagram >> then instantiate a {@link RelatedDiagram} proxy.
     * 
     * @return a {@link RelatedDiagram} proxy on the created {@link Dependency}.
     */
    @objid ("34812749-e723-439e-9e47-edaf3c3e409f")
    public static RelatedDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RelatedDiagram.STEREOTYPE_NAME);
        return RelatedDiagram.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link RelatedDiagram} proxy from a {@link Dependency} stereotyped << related_diagram >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link RelatedDiagram} proxy or <i>null</i>.
     */
    @objid ("a3929ec2-dd3b-4bd7-b223-b2b12ff59ed1")
    public static RelatedDiagram instantiate(Dependency obj) {
        return RelatedDiagram.canInstantiate(obj) ? new RelatedDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RelatedDiagram} proxy from a {@link Dependency} stereotyped << related_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link RelatedDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0e3da5dc-2aff-4829-9e72-8ef32c40d0c9")
    public static RelatedDiagram safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (RelatedDiagram.canInstantiate(obj))
        	return new RelatedDiagram(obj);
        else
        	throw new IllegalArgumentException("RelatedDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("9214c665-8269-4ba7-b747-726530d42197")
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
        RelatedDiagram other = (RelatedDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("c29cb630-582f-49fa-8a3f-9353859d49ce")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("98aec9b6-a106-4156-9a4a-e51667662b64")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b3c348a8-ff63-4973-8d75-aacc07e58b55")
    protected RelatedDiagram(Dependency elt) {
        this.elt = elt;
    }

    @objid ("7c15b3f3-14bd-419f-b399-40c4a720d7a2")
    public static final class MdaTypes {
        @objid ("e7c44df1-98cf-41c3-b6b8-d5ab2fd54fd5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("42b99355-d803-4a38-8993-be55c3ea35cf")
        private static Stereotype MDAASSOCDEP;

        @objid ("f961c416-8dce-4545-ae67-2040a27885e3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e77dc470-c4e0-4fda-af93-7ed590df2afa")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "2961d57b-5120-11de-bbaf-00218648fa3d");
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
